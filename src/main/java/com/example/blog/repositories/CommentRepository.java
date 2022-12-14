package com.example.blog.repositories;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
     List<Comment> findAllByPost(Post post);
}
