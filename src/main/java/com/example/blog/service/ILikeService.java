package com.example.blog.service;

import com.example.blog.dto.LikeDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface ILikeService {
    ResponseEntity<String>likePost(LikeDto likeDto) throws PostNotFoundException, UserNotFoundException;
}
