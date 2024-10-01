package com.gatewayserver.gatewayserver.config;

import com.gatewayserver.gatewayserver.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class named {@link GatewayConfig} for setting up API Gateway routes.
 */
@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    // Define the list of public endpoints
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/v1/authentication/users/register",
            "/api/v1/authentication/users/login",
            "/api/v1/authentication/users/refresh-token",
            "/api/v1/authentication/users/logout"
    );

    /**
     * Configures the route locator to define the routing rules for the gateway.
     *
     * @param builder The RouteLocatorBuilder used to build the RouteLocator.
     * @return A RouteLocator with the defined routes.
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
               .route("microservice-user-device", r -> r.path("/user_device/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://user_device"))
                .route("microservice-authentification", r -> r.path("/api/v1/authentication/users/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-authentification"))
                .route("microservice-event", r -> r.path("/event/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-event"))
                .route("microservice-forum", r -> r.path("/forum/**")
                .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                        .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                .uri("lb://microservice-forum"))
                .route("microservice-commentaire", r -> r.path("/commentaire/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-commentaire"))
                .route("microservice-event-user", r -> r.path("/event_user/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-event-user"))
                .route("microservice-groupe", r -> r.path("/groupe/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-groupe"))
                .route("microservice-groupe-user", r -> r.path("/groupe_user/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-groupe-user"))
                .route("microservice-role", r -> r.path("/role/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-role"))
                .route("microservice-permission", r -> r.path("/permission/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-permission"))
                .route("microservice-role-permission", r -> r.path("/role_permission/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-role-permission"))
                .route("microservice-message", r -> r.path("/message/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-message"))
                .route("microservice-discussion", r -> r.path("/discussion/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-discussion"))
                .route("microservice-project", r -> r.path("/project/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-project"))
                .route("microservice-ticket", r -> r.path("/ticket/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-ticket"))
                .route("microservice-project-user", r -> r.path("/project-user/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-project-user"))
                .route("microservice-survey", r -> r.path("/survey/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-survey"))
                .route("microservice-survey-values", r -> r.path("/survey_value/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-survey-values"))
                .route("microservice-vote", r -> r.path("/vote/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-vote"))
                .route("microservice-rank", r -> r.path("/rank/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-rank"))
                .route("microservice-notification", r -> r.path("/notification/**")
                        .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthenticationFilter.Config()
                                .setPublicEndpoints(PUBLIC_ENDPOINTS))))
                        .uri("lb://microservice-notification"))
                .build();
    }

}