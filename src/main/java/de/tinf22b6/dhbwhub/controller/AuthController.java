package de.tinf22b6.dhbwhub.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import de.tinf22b6.dhbwhub.model.*;
import de.tinf22b6.dhbwhub.payload.request.EmailVerificationRequest;
import de.tinf22b6.dhbwhub.payload.request.LoginRequest;
import de.tinf22b6.dhbwhub.payload.request.SignupRequest;
import de.tinf22b6.dhbwhub.payload.request.TokenValidationRequest;
import de.tinf22b6.dhbwhub.payload.response.JwtResponse;
import de.tinf22b6.dhbwhub.payload.response.MessageResponse;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.AdministratorRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.security.jwt.JwtUtils;
import de.tinf22b6.dhbwhub.security.services.UserDetailsImpl;
import de.tinf22b6.dhbwhub.service.AccountServiceImpl;
import de.tinf22b6.dhbwhub.service.EmailService;
import de.tinf22b6.dhbwhub.service.EmailVerificationTokenManager;
import de.tinf22b6.dhbwhub.service.interfaces.PictureService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = {"https://www.dhbwhub.de", "http://localhost:3000"})
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PictureService pictureService;
    private final AccountServiceImpl accountServiceImpl;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    // TODO: Save JWT in Database
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getAccountId(),
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    // TODO: Generate JWT Token for user after successful signup
    @PostMapping("signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (accountRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Account newAccount = new Account(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), null,true);

        User newUser = new User(null, "new User", null, newAccount);

        accountRepository.save(newAccount);

        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("email-verification")
    public ResponseEntity<?> emailVerification (@Valid @RequestBody EmailVerificationRequest emailVerificationRequest) {
        if (accountRepository.existsByEmail(emailVerificationRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        } else {
            String userMail = emailVerificationRequest.getEmail();

            String token = EmailVerificationTokenManager.generateToken(userMail);

            Map<String, Object> templateModel = new HashMap<>();
            templateModel.put("token", token);

            try {
                emailService.sendMessageUsingThymeleafTemplate(
                        emailVerificationRequest.getEmail(), "Email Verification", "mail-template.html", templateModel);
            } catch (MessagingException | IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MessageResponse("Failed to send email with token."));
            }

            return ResponseEntity.ok(new MessageResponse("Email with token sent successfully!"));
        }

    }

    @PostMapping("token-validation")
    public ResponseEntity<?> tokenValidation (@Valid @RequestBody TokenValidationRequest tokenValidationRequest) {
        if (EmailVerificationTokenManager.isTokenValid(tokenValidationRequest.getToken())) {
            EmailVerificationTokenManager.deleteEntry(tokenValidationRequest.getToken());
            return ResponseEntity.ok("Code is correct. Email verification successful!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code is incorrect. Email verification failed!");
        }
    }

    @PostMapping("google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String idTokenString = request.get("token");
        GoogleIdToken idToken = null;

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            idToken = verifier.verify(idTokenString);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There has been a serverside problem");
        }

        if (idToken == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        Picture picture = pictureService.getImageFromUrl(pictureUrl);

        Account account = accountServiceImpl.findByEmail(email);
        User user = null;

        if(account == null) {
            account = accountRepository.save(new Account(name, email, null, picture, true));
            user = userRepository.save(new User(0, null, null, account));
            accountRepository.saveOAuthEntry(new OAuthAccount(account,OAuthAccount.GOOGLE_ENTRY));
        } else if (!accountRepository.existsOAuthEntry(account.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied - No such account exists");
        } else {
            user = userRepository.findByAccountId(account.getId());
        }

        String jwt = jwtUtils.generateJwtToken(account.getUsername());
        UserDetailsImpl userDetails = UserDetailsImpl.build(user, administratorRepository);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getAccountId(),
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
}
