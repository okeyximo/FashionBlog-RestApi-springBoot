package com.example.blog.controller;

import com.example.blog.dto.UserDto;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;


    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.login(userDto);
    }

}
