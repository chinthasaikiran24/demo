package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class DepartmentWebClient {

    private final WebClient webClient;

    public DepartmentWebClient(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getDepartment(long id) {

        return webClient
                .get()
                .uri("http://department-service/departments/" + id)
                .retrieve()
                .bodyToMono(String.class);
        
    }
}