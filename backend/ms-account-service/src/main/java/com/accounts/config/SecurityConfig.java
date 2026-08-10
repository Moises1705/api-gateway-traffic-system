package com.accounts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF para permitir peticiones POST desde herramientas externas/Gateway
                .csrf(csrf -> csrf.disable())
                // Configurar acceso público a los endpoints de cuentas y Swagger
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/accounts/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated());

        return http.build();
    }
}