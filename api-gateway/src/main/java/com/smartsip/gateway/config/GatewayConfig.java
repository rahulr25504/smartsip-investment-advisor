package com.smartsip.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Portfolio Service Routes
                .route("portfolio-service", r -> r
                        .path("/api/portfolio/**", "/api/sip/**", "/api/holdings/**")
                        .uri("lb://PORTFOLIO-SERVICE"))

                // Market Data Service Routes
                .route("market-data-service", r -> r
                        .path("/api/market/**")
                        .uri("lb://MARKET-DATA-SERVICE"))

                // Rebalancing Service Routes
                .route("rebalancing-service", r -> r
                        .path("/api/rebalancing/**")
                        .uri("lb://REBALANCING-SERVICE"))

                // Excel Service Routes
                .route("excel-service", r -> r
                        .path("/api/excel/**")
                        .uri("lb://EXCEL-SERVICE"))

                // Auth Routes (Portfolio Service handles auth)
                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .uri("lb://PORTFOLIO-SERVICE"))

                .build();
    }
}