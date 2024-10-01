package com.forum.mforum.configurations;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
@Configuration
public class EventFeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Récupérer le JWT à partir du contexte de sécurité
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
                Jwt jwt = (Jwt) authentication.getPrincipal();
                // Ajouter le token JWT dans l'en-tête Authorization
                requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
            }
        };
    }

}
