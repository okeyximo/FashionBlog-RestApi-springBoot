package com.example.blog.controller;

import com.example.blog.dto.LikeDto;
import com.example.blog.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/like")
public class LikesController {

    private LikeService likeService;

    @PostMapping("/{postId}/{userId}")
    public ResponseEntity<String> likePost(@PathVariable("postId") long postId, @PathVariable("userId") long userId){
        return likeService.likePost(postId, userId);
    }
}
