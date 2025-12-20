package com.spring_compendium.core_sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * 5.4 - Configurazione per API REST e H2 Console.
 * Include una gestione condizionale basata sui profili (dev vs prod).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Versione PERMISSIVA della sicurezza.
     * Si attiva solo quando il profilo 'dev' è attivo.
     * Utile per testare con Bruno/Postman senza header di autenticazione.
     */
    @Bean
    @Profile("dev")
    public SecurityFilterChain filterChainDev(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth -> auth
                        // In DEV, permettiamo TUTTO
                        .anyRequest().permitAll()
                );

        System.out.println("[SECURITY] ⚠️ Profilo DEV attivo: Sicurezza bypassata per facilità di sviluppo.");
        return http.build();
    }

    /**
     * Versione PROTETTA della sicurezza.
     * Si attiva per qualsiasi profilo che NON sia 'dev'.
     */
    @Bean
    @Profile("!dev")
    public SecurityFilterChain filterChainProd(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        System.out.println("[SECURITY] ✅ Profilo di Produzione/Safe attivo: Autenticazione richiesta.");
        return http.build();
    }
}
