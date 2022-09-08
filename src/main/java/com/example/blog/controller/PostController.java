package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/post")
public class PostController {
    private final IPostService postService;

    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody PostDto postDto) throws UserNotFoundException {
        return postService.createPost(postDto);
    }
    @PutMapping("/update")
    public ResponseEntity updatePost(@RequestBody PostDto postDto) throws UserNotFoundException, PostNotFoundException {
        return postService.updatePost(postDto);
    }
    @DeleteMapping ("/delete")
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
