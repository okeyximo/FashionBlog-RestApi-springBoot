package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity createPost(PostDto postDto);

    ResponseEntity updatePost(PostDto postDto) throws PostNotFoundException, UserNotFoundException;

    ResponseEntity deletePost(PostDto postDto) throws PostNotFoundException;

    ResponseEntity<List<PostDto>> getAllPosts();

    ResponseEntity<PostDto> getPost(Long postId) throws PostNotFoundException;
}
