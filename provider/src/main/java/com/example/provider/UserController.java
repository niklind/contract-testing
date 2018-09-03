package com.example.provider;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User post(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setPhone("0707123456");
        return user;
    }

}
