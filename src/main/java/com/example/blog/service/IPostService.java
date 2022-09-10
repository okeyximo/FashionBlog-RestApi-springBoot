package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPostService {
    ResponseEntity createPost(PostDto postDto) throws UserNotFoundException;

    ResponseEntity updatePost(PostDto postDto) throws PostNotFoundException, UserNotFoundException;


    ResponseEntity deletePost(Long id) throws PostNotFoundException;

    ResponseEntity<List<PostDto>> getAllPosts();

    ResponseEntity<PostDto> getPost(Long postId) throws PostNotFoundException;

    ResponseEntity searchPostByKeyWord(String keyword);
}
