package de.tinf22b6.dhbwhub.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationTokenManager {

    private static final Map<String, TokenData> tokenMap = new HashMap<>();

    public record TokenData(String userEmail, Date creationTime) {};

    public static String generateToken(String userEmail) {
        deleteExpiredTokens();
        String token = EmailVerificationTokenGenerator.generateToken();
        if (!isTokenAlreadyInMap(token)) {
            tokenMap.put(token, new TokenData(userEmail, new Date()));
        } else {
            generateToken(userEmail);
        }
        return token;
    }

    public static boolean isTokenValid(String token) {
        TokenData tokenData = tokenMap.get(token);
        return checkIfTokenIsNotExpired(tokenData);
    }

    public static void deleteEntry(String token) {
        tokenMap.remove(token);
    }

    private static boolean isTokenAlreadyInMap(String token) {
        return tokenMap.containsKey(token);
    }

    private static void deleteExpiredTokens() {
        for (var entry : tokenMap.entrySet()) {
            TokenData currentTokenData = entry.getValue();
            if (!checkIfTokenIsNotExpired(currentTokenData)) {
                tokenMap.remove(entry.getKey());
            }
        }
    }

    private static boolean checkIfTokenIsNotExpired(TokenData tokenData) {
        if (tokenData != null) {
            Date currentTime = new Date();
            long diffInMillies = Math.abs(currentTime.getTime() - tokenData.creationTime().getTime());
            long diffInMinutes = diffInMillies / (60 * 1000);
            return diffInMinutes <= 15;
        }
        return false;
    }

}
