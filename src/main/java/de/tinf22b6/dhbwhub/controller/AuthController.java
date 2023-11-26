package de.tinf22b6.dhbwhub.controller;

import de.tinf22b6.dhbwhub.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/auth")
public class AuthController {

    private AccountServiceImpl accountService;

    @PostMapping("login")
    public ResponseEntity<String> checkEmailAddress(@RequestBody String email) {
        boolean emailFound = accountService.checkIfEmailExists(email);
        if (emailFound) {
            return ResponseEntity.ok("Email found");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
