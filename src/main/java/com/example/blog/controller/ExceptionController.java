package com.example.blog.controller;

import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionController {

    private ResponseGenerator responseGenerator;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> UserNotFoundException(UserNotFoundException ex) {
        return responseGenerator.notFound(ex.getMessage());
    }


    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> PostNotFoundException(PostNotFoundException ex) {
        return responseGenerator.notFound(ex.getMessage());
    }


    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> CommentNotFoundException(CommentNotFoundException ex) {
        return responseGenerator.notFound(ex.getMessage());
    }
}
