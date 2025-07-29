package edu.ifba.internet_banking_api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class GatewayConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Main Banking API routes (includes auth) - strip /api prefix
                .route("internet-banking-main-api", r -> r.path("/api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://internet-banking-api"))
                
                // Mail Service routes (strip /mail prefix)
                .route("internet-banking-mail-service", r -> r.path("/mail/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://internet-banking-mail-service"))
                
                // Health check route for gateway itself
                .route("gateway-health", r -> r.path("/gateway/health")
                        .uri("http://localhost:8080/actuator/health"))
                
                .build();
    }
}
