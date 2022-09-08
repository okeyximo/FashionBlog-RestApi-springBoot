package com.example.blog.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private LocalDateTime time;
    private T payload;
}
