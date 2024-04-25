package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.model.Account;
import de.tinf22b6.dhbwhub.model.User;
import de.tinf22b6.dhbwhub.payload.request.LoginRequest;
import de.tinf22b6.dhbwhub.payload.request.SignupRequest;
import de.tinf22b6.dhbwhub.payload.response.JwtResponse;
import de.tinf22b6.dhbwhub.payload.response.MessageResponse;
import de.tinf22b6.dhbwhub.repository.AccountRepository;
import de.tinf22b6.dhbwhub.repository.UserRepository;
import de.tinf22b6.dhbwhub.security.jwt.JwtUtils;
import de.tinf22b6.dhbwhub.security.services.UserDetailsImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    //@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
    //@ResponseBody
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println(loginRequest.getUsername());

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println("1");
        System.out.println("JWT: " + jwt);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (accountRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (accountRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Account newAccount = new Account(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), null,true);

        User newUser = new User(null, "new User", null, newAccount);

        accountRepository.save(newAccount);

        saveOrUpdateUser(newUser);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Transactional
    public void saveOrUpdateUser(User user) {
        // Überprüfen, ob die Entität bereits in der Datenbank vorhanden ist
        if (user.getId() != null && userRepository.find(user.getId()) != null) {
            // Falls vorhanden, aktualisiere die Entität
            userRepository.save(user);
        } else {
            // Falls nicht vorhanden, füge die Entität hinzu
            userRepository.save(user);
        }
    }

}
