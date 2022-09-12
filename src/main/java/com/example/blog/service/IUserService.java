package com.example.blog.service;

import com.example.blog.dto.Payload.UserPayLoad;
import com.example.blog.dto.UserDto;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<User> login(UserDto userDto) throws UserNotFoundException;

    ResponseEntity createUser(UserDto userdto);


}
