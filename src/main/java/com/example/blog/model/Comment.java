package com.example.blog.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
