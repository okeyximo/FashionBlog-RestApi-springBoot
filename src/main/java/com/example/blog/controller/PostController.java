package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }
    @PostMapping("/update")
    public ResponseEntity updatePost(@RequestBody PostDto postDto) throws UserNotFoundException, PostNotFoundException {
        return postService.updatePost(postDto);
    }
    @PostMapping("/delete")
    public ResponseEntity deletePost(@RequestBody PostDto postDto) throws PostNotFoundException {
        return postService.deletePost(postDto);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) throws PostNotFoundException {
        return postService.getPost(postId);
    }

}
