package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private @NonNull String comment;
    private String commenter;
    private long userId;
    private long postId;
}
