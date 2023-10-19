package de.tinf22b6.dhbwhub.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtils {
    private static Dotenv dotenv;

    /**
     * Gets the value of the specified environment variable.
     *
     * @param key The name of the environment variable
     * @return The string value of the variable, or {@code null} if the variable is not defined
     */
    public static String getEnv(String key) {
        if (dotenv == null) {
            dotenv = Dotenv.load();
        }
        return dotenv.get(key);
    }
}
