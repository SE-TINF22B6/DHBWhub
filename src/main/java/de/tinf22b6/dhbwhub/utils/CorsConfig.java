package de.tinf22b6.dhbwhub.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Erlaubte Ursprünge
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaubte HTTP-Methoden
                        .allowCredentials(true); // Erlaube Cookies
            }
        };
    }
}
