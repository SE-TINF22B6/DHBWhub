package de.tinf22b6.dhbwhub.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailVerificationTokenGenerator {

    public static String generateToken() {
        Random random = new Random();
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            tokenBuilder.append(random.nextInt(10));
        }
        return tokenBuilder.toString();
    }

}
