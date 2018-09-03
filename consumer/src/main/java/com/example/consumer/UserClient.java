package com.example.consumer;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
class UserClient {

    private final RestTemplate restTemplate;

    UserClient() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(new GsonHttpMessageConverter()));
    }

    User post(String url, User user) {
        return restTemplate.postForObject(url, user, User.class);
    }

}
