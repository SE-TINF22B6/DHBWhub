package de.tinf22b6.dhbwhub.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailVerificationTokenGenerator {

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

}
