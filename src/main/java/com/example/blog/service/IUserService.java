package com.example.blog.service;

import com.example.blog.dto.UserDto;
import com.example.blog.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<User> login(UserDto userDto);
    ResponseEntity createUser(UserDto userdto);

}
