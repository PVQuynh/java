package com.example.java.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.java.model.Role.*;
import static com.example.java.model.Role.ADMIN;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        // user
                        .requestMatchers(GET, "/api/v1/user/**").hasAnyRole(ADMIN, MANAGER, USER)
                        .requestMatchers(POST, "/api/v1/user/**").hasAnyRole(ADMIN, USER)
                        .requestMatchers(PUT, "/api/v1/user/**").hasAnyRole(ADMIN, USER)
                        .requestMatchers(DELETE, "/api/v1/user/**").hasAnyRole(ADMIN, MANAGER)
                        // manager
                        .requestMatchers(GET, "/api/v1/management/**").hasAnyRole(ADMIN, MANAGER)
                        .requestMatchers(POST, "/api/v1/management/**").hasAnyRole(ADMIN, MANAGER)
                        .requestMatchers(PUT, "/api/v1/management/**").hasAnyRole(ADMIN, MANAGER)
                        .requestMatchers(DELETE, "/api/v1/management/**").hasRole(ADMIN)
                        // any
                        .anyRequest()
                        .authenticated()
                )
                .oauth2ResourceServer(auth -> auth.jwt(token -> token.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())));


        return http.build();
    }
}