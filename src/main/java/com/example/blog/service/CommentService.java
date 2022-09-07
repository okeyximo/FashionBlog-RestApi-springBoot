package com.example.blog.service;

import com.example.blog.dto.CommentDto;

public interface CommentService {
    void postComment(CommentDto commentDto);
}
