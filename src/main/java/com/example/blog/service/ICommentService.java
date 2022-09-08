package com.example.blog.service;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentService {

    ResponseEntity postComment(CommentDto commentDto) throws UserNotFoundException, PostNotFoundException;

    ResponseEntity updateComment(CommentDto commentDto, Long commentId);


    ResponseEntity deleteComment(Long commentId);

    ResponseEntity<List<CommentDto>> getAllCommentOfAPost(Long postId) throws PostNotFoundException;

}
