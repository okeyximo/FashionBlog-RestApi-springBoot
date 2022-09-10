package com.example.blog.service.serviceImpl;

import com.example.blog.BlogApplication;
import com.example.blog.dto.UserDto;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.User;
import com.example.blog.pojos.ApiResponse;
import com.example.blog.repositories.UserRepository;
import com.example.blog.service.IUserService;
import com.example.blog.utils.ResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;


@SpringBootTest(classes = BlogApplication.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ResponseGenerator responseGenerator;
    @Autowired
    private IUserService userService;
    static UserDto userDto;

    static User user;

    @Autowired
    private ResponseGenerator responseGenerator1;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("Urch")
                .password("1234")
                .email("y@gmail.com").build();
        userDto = new UserDto("y@gmail.com", "1234", "Urch");
    }


    @Test
    void login() throws UserNotFoundException {
        Mockito.when(responseGenerator.OK(userDto)).thenReturn(new ResponseEntity(new ApiResponse<>("Request successful", true, LocalDateTime.now(), user), CREATED));
//        Mockito.when(responseGenerator.OK(user)).thenReturn()
        Mockito.when(userRepository.findUserByEmailAndPassword("y@gmail.com", "1234")).thenReturn(Optional.ofNullable(user));
        ResponseEntity actual = userService.login(userDto);
        User user1 = new User();
        user1.setUsername("Urch");
        user1.setPassword("1234");
        user1.setEmail("y@gmail.com");
        ResponseEntity expected = responseGenerator1.OK(user);
        assertEquals(expected, actual);

    }

    @Test
    void UserLogingShouldThrowUserNotFoundExeption() {
        Mockito.when(userRepository.findUserByEmailAndPassword(
                userDto.getEmail(), userDto.getPassword()
        )).thenReturn(Optional.empty());
        Exception message = assertThrows(UserNotFoundException.class, () ->
                userService.login(userDto)
        );
        assertEquals("Incorrect email or password", message.getMessage());
    }

    @Test
    void createUser() {
    }
}