package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.User;
import Repository.UserRepository;


@RestController
public class AuthController {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public String register(@RequestBody User user){

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        repository.save(user);

        return "User Registered";

    }

}