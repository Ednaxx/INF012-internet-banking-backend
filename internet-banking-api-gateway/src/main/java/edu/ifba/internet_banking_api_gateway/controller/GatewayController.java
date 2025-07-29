package edu.ifba.internet_banking_api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayController {

    @GetMapping("/gateway/routes")
    public Mono<Map<String, Object>> getRoutes() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "Gateway is running");
        response.put("message", "Available routes: /api/** → internet-banking-api, /mail/** → internet-banking-mail-service");
        response.put("discovery", "Eureka service discovery enabled");
        return Mono.just(response);
    }

    @GetMapping("/")
    public Mono<Map<String, String>> home() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "Internet Banking API Gateway");
        response.put("status", "UP");
        response.put("description", "Gateway for Internet Banking microservices");
        return Mono.just(response);
    }
}
