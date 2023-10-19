package de.tinf22b6.dhbwhub.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
    private static Dotenv dotenv;

    public static String getEnv(String key) {
        if (dotenv == null) {
            dotenv = Dotenv.load();
        }
        return dotenv.get(key);
    }
}
