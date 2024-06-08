package de.tinf22b6.dhbwhub.security;

import de.tinf22b6.dhbwhub.security.jwt.AuthEntryPointJwt;
import de.tinf22b6.dhbwhub.security.jwt.AuthTokenFilter;
import de.tinf22b6.dhbwhub.security.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    // Define all public endpoints here (ant matchers)
    private static final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/login",
            "/api/auth/signup",
            "/api/auth/google",
            "/api/auth/email-verification",
            "/api/auth/token-validation",

            "/post/homepage-preview-posts",
            "/post/homepage-preview-posts/{id:\\d+}",
            "/post/post-thread/{id:\\d+}",
            "/post/popular-tags",
            "/post/posts-by-tag/*",
            "/post/user-posts/{id:\\d+}",
            "/post/friend-posts/{id:\\d+}",
            "/post/posts-by-keyword/*",
            "/post/posts-by-tag-keyword/*",
            "/post/friend-posts-by-tag",
            "/post/posts-by-tag-keyword/*",
            "/event/homepage-preview-events",
            "/event/calendar-events",
            "/event/event-thread/{id:\\d+}",
            "/event/event-comments/{id:\\d+}",

            "user/{id:\\d+}",
            "/user-image",
            "/user/user-by-keyword/*",
    };

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            // CSRF protection is disabled as this is a stateless API. The application uses token-based authentication, making CSRF less relevant.
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                    .anyRequest().authenticated()
            ).exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .cors(Customizer.withDefaults());

        return http.build();
    }
}
