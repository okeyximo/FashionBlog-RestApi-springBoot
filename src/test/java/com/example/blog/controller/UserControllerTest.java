package com.example.blog.controller;

import com.example.blog.dto.Payload.UserPayLoad;
import com.example.blog.dto.UserDto;
import com.example.blog.pojos.ApiResponse;
import com.example.blog.service.IUserService;
import com.example.blog.utils.ResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    IUserService userService;
    static UserDto userDto;
    static UserPayLoad userPayLoad;
    @MockBean
    ResponseGenerator responseGenerator;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
         userDto = new UserDto("okey@gmail.com","1234","okey");
//         userPayLoad = new UserPayLoad("okey@gmail.com");

      //   Mockito.when(responseGenerator1.created(userDto)).thenReturn()
    }

    @Test
    void createUser() throws Exception {

        Mockito.when(userService.createUser(userDto))
                .thenReturn(new ResponseEntity(new ApiResponse<>("Request successful", true, LocalDateTime.now(), userDto), CREATED));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON
                ).content("{\n" +
                        "    \"email\" : \"okey@gmail.com\",\n" +
                        "    \"password\" : \"1234\",\n" +
                        "    \"username\" : \"okey\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void loginUser() {
    }
}