package com.home.sabir.spring.bean;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

public class RestTemplateBean {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Response: " + response.getBody());
    }
}