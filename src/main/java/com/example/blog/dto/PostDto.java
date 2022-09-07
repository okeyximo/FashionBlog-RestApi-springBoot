package com.example.blog.dto;

import com.example.blog.model.Comment;
import com.example.blog.model.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostDto {
    private long postId;
    private long userId;
    private String title;
    private String description;
    private String content;
    private List<Like> likes;
    private List<Comment> comments;
//    private String featuredImage;
}
