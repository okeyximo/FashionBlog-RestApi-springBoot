package com.example.blog.utils;

import com.example.blog.pojos.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class ResponseGenerator<T> {

    public ResponseEntity<ApiResponse> OK(T response){
        ResponseEntity response1 = new ResponseEntity<>(new ApiResponse("Request successful", true, LocalDateTime.now(), response), OK);
        return response1;
    }

    public ResponseEntity<ApiResponse> created(T response){
        return new ResponseEntity<>(new ApiResponse("Request successful", true, LocalDateTime.now(), response), CREATED);
    }

    public ResponseEntity<ApiResponse> notFound(T response){
        return new ResponseEntity<>(new ApiResponse("Not found", true, LocalDateTime.now(), response), NOT_FOUND);
    }

    public ResponseEntity<ApiResponse> alreadyExist(String message){
        return new ResponseEntity<>(new ApiResponse(message, false, LocalDateTime.now(), null), CONFLICT);
    }

    public ResponseEntity<ApiResponse> unauthorised(String message){
        return new ResponseEntity<>(new ApiResponse(message, false, LocalDateTime.now(), null), UNAUTHORIZED);
    }
}
