package com.example.blog.exception;

import java.util.function.Supplier;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String message) {
        super(message);
    }


}
