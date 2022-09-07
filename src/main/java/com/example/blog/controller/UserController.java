package com.example.blog.controller;

import com.example.blog.dto.UserDto;
import com.example.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }


}
