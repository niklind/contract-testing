package com.example.consumer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class UserClient {

    private final RestTemplate restTemplate;

    UserClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    User post(String url, User user) {
        return restTemplate.postForObject(url, user, User.class);
    }

}
