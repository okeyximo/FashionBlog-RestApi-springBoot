package com.example.blog.service;

import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface LikeService {
    ResponseEntity<String> likePost(long postId, long userId) throws PostNotFoundException, UserNotFoundException;
}
