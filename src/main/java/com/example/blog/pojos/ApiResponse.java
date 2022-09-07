package com.example.blog.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private LocalDateTime time;
    private T payload;
}
