package com.example.blog.service.serviceImpl;

import com.example.blog.model.User;
import com.example.blog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    User user;

    @BeforeEach
    void setUp() {
        user.setUsername("okeyximo");
    }


    @Test
    void login() {
        when(userRepository.findByEmail("x@gmail.com")).thenReturn()

    }

    @Test
    void createUser() {
    }
}