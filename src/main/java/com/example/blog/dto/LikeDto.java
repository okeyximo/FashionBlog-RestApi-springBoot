package com.example.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeDto {

    private long postId;
    private long count;
    private boolean isLiked;
}
