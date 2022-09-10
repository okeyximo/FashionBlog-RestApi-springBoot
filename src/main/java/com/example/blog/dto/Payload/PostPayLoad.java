package com.example.blog.dto.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPayLoad {
    private long postId;
    private String op; //original poster -> set to posters username.
    private String title;
    private String description;
    private String postContent;
    private List<LikePayLoad> likes;
    private List<CommentPayLoad> comments;
}
