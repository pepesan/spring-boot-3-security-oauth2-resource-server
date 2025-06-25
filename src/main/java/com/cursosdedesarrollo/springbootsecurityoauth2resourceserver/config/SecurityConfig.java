package com.cursosdedesarrollo.springbootsecurityoauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // si quieres usar @PreAuthorize en tus métodos
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Exige JWT para cualquier request
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()   // endpoints públicos
                        .anyRequest().authenticated()                // el resto autenticado
                )
                // Configura JWT como mecanismo de OAuth2 Resource Server
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
