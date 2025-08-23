package com.ricardo.user.controllers;

import com.ricardo.user.dtos.UserRecordDto;
import com.ricardo.user.models.User;
import com.ricardo.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecord) {
        User user = new User();
        BeanUtils.copyProperties(userRecord, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
