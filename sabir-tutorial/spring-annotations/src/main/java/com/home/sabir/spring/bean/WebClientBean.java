package com.home.sabir.spring.bean;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientBean {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        Mono<String> response = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);
        response.subscribe(
                System.out::println,
                error -> System.err.println("Error occurred: " + error.getMessage())
        );
    }
}