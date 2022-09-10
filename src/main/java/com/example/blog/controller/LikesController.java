package com.example.blog.controller;

import com.example.blog.dto.LikeDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.ILikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/like")
public class LikesController {
    private ILikeService likeService;

    @PostMapping
    public ResponseEntity<String>likePost(@RequestBody LikeDto likeDto) throws UserNotFoundException, PostNotFoundException {
        return likeService.likePost(likeDto);
    }
}
