package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserClient userClient;

    private final String url = "https://jsonplaceholder.typicode.com/users";

    @GetMapping("/resttemplate")
    public ResponseEntity<List<User>> restTemplate() {
        final List<User> users = new RestTemplate().exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {}
        ).getBody();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/webclient")
    public ResponseEntity<List<User>> webClient() {
        final List<User> users = WebClient.create().get().uri(url).retrieve().bodyToMono(
                new ParameterizedTypeReference<List<User>>() {}
        ).block();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/feignclient")
    public ResponseEntity<List<User>> feignClient() {
        final List<User> users = userClient.getUsers();
        return ResponseEntity.ok(users);
    }

}
