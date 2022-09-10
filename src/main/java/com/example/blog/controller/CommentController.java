package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    private ICommentService commentService;

    @PostMapping
    public ResponseEntity postComment(@RequestBody CommentDto commentDto) throws UserNotFoundException, PostNotFoundException {
        return commentService.postComment(commentDto);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity updateComment(@RequestBody CommentDto commentDto, @PathVariable("commentId") Long commentId){
        return commentService.updateComment(commentDto, commentId);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId){
        return commentService.deleteComment(commentId);
    }
    @GetMapping("/{postId}")
    public ResponseEntity getCommentForAPost(@PathVariable("postId") Long postId) throws PostNotFoundException {
        return commentService.getAllCommentOfAPost(postId);
    }


}
