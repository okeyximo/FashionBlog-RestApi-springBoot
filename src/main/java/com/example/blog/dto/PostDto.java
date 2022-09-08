package com.example.blog.dto;

import com.example.blog.dto.Payload.CommentPayLoad;
import com.example.blog.dto.Payload.LikePayLoad;
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
    private String op; //original poster -> set to posters username.
    private long userId;
    private String title;
    private String description;
    private String postContent;
    private List<LikePayLoad> likes;
    private List<CommentPayLoad> comments;
//    private String featuredImage;
}
