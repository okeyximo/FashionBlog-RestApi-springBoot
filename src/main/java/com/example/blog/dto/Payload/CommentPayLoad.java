package com.example.blog.dto.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPayLoad {
    private String comment;
    private String commentOwner;
}
