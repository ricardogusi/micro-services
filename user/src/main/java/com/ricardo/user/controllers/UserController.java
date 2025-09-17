package com.ricardo.user.controllers;

import com.ricardo.user.dtos.UserRecordDto;
import com.ricardo.user.models.User;
import com.ricardo.user.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRecordDto dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        User created = service.save(user);
        return ResponseEntity.ok(created);
    }

    // ... demais endpoints ...
}
