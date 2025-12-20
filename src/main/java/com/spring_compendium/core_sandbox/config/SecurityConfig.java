package com.spring_compendium.core_sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/**
 * 5.4 - Configurazione della catena di filtri.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disabilitiamo CSRF (fondamentale per POST/PUT da Bruno)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Politica delle Sessioni: STATELESS
                // Impedisce a Spring di usare i redirect alle pagine di login HTML
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Configurazione Frame per H2
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                // 4. Autorizzazioni
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                        // Proteggiamo tutto il resto
                        .anyRequest().authenticated()
                )

                // 5. Autenticazione Basic (Header Authorization: Basic ...)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
