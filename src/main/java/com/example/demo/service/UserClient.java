package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers();

}
