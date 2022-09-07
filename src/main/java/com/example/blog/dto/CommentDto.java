package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentDto {
    private Long userId;
    private String comment;
}
