package com.example.blog.service.serviceImpl;

import com.example.blog.dto.Payload.UserPayLoad;
import com.example.blog.dto.UserDto;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.User;
import com.example.blog.pojos.ApiResponse;
import com.example.blog.repositories.UserRepository;
import com.example.blog.service.IUserService;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepo;
//    private final ModelMapper modelMapper;
    private final ResponseGenerator responseGenerator;


    @Override
    public ResponseEntity login(UserDto userDto) throws UserNotFoundException {
        User user = userRepo.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).orElseThrow(()->new UserNotFoundException("Incorrect email or password"));
        String response = "Successfully loggedIn " + user.getUsername();
        return responseGenerator.OK(response);
    }


    @Override
    public ResponseEntity createUser(UserDto userdto) {
        String email = userdto.getEmail();
        if (userRepo.findByEmail(email).isEmpty()) {
            User user = userRepo.save(mapToEntity(userdto));
//            UserDto response = mapToDto(user);
            String response = "Successfully registered " + user.getUsername();
            return responseGenerator.created(response);
        } else {
            return responseGenerator.alreadyExist("user already exist");
        }
    }



    // convert entity to dto
    private UserDto mapToDto(User user) {
//        return modelMapper.map(user, UserDto.class);w
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        return userDto;

    }

    private User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
