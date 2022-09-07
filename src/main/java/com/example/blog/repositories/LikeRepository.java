package com.example.blog.repositories;

import com.example.blog.model.Like;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    Long countAllByPost(Post post);
}
