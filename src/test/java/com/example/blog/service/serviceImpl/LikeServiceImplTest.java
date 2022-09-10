package com.example.blog.service.serviceImpl;

import com.example.blog.dto.LikeDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.Like;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.pojos.ApiResponse;
import com.example.blog.repositories.LikeRepository;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.utils.ResponseGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LikeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class LikeServiceImplTest {

    @MockBean
    private LikeRepository likeRepository;

    @Autowired
    private LikeServiceImpl likeServiceImpl;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private ResponseGenerator responseGenerator;

    @MockBean
    private UserRepository userRepository;

    @Test
    void likePost() {
    }

    /**
     * Method under test: {@link LikeServiceImpl#likePost(LikeDto)}
     */
    @Test
    void testLikePost() throws PostNotFoundException, UserNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");

        Like like = new Like();
        like.setUser(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUsername("janedoe");

        Like like1 = new Like();
        like1.setUser(user1);
        Optional<Like> ofResult = Optional.of(like1);
        doNothing().when(likeRepository).delete((Like) any());
        when(likeRepository.countAllByPost((Post) any())).thenReturn(3L);
        when(likeRepository.save((Like) any())).thenReturn(like);
        when(likeRepository.findByPostAndUser((Post) any(), (User) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUsername("janedoe");

        Post post = new Post();
        post.setComment(new ArrayList<>());
        post.setContent("Not all who wander are lost");
        post.setDescription("The characteristics of someone or something");
        post.setId(123L);
        post.setLikes(new ArrayList<>());
        post.setTitle("Dr");
        post.setUser(user2);
        Optional<Post> ofResult1 = Optional.of(post);
        when(postRepository.findById((Long) any())).thenReturn(ofResult1);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user3.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user3.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user3);
        when(userRepository.findUserById(anyLong())).thenReturn(ofResult2);
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(HttpStatus.CONTINUE);
        when(responseGenerator.OK((Object) any())).thenReturn(responseEntity);
        assertSame(responseEntity, likeServiceImpl.likePost(new LikeDto(123L, "janedoe", 123L, 3L, true)));
        verify(likeRepository).countAllByPost((Post) any());
        verify(likeRepository).findByPostAndUser((Post) any(), (User) any());
        verify(likeRepository).delete((Like) any());
        verify(postRepository).findById((Long) any());
        verify(userRepository).findUserById(anyLong());
        verify(responseGenerator).OK((Object) any());
    }

    /**
     * Method under test: {@link LikeServiceImpl#likePost(LikeDto)}
     */
    @Test
    void testLikePost2() throws PostNotFoundException, UserNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsername("janedoe");

        Like like = new Like();
        like.setUser(user);
        doNothing().when(likeRepository).delete((Like) any());
        when(likeRepository.countAllByPost((Post) any())).thenReturn(3L);
        when(likeRepository.save((Like) any())).thenReturn(like);
        when(likeRepository.findByPostAndUser((Post) any(), (User) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUsername("janedoe");

        Post post = new Post();
        post.setComment(new ArrayList<>());
        post.setContent("Not all who wander are lost");
        post.setDescription("The characteristics of someone or something");
        post.setId(123L);
        post.setLikes(new ArrayList<>());
        post.setTitle("Dr");
        post.setUser(user1);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepository.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setTimeCreated(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUsername("janedoe");
        Optional<User> ofResult1 = Optional.of(user2);
        when(userRepository.findUserById(anyLong())).thenReturn(ofResult1);
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(HttpStatus.CONTINUE);
        when(responseGenerator.created((Object) any())).thenReturn(responseEntity);
        when(responseGenerator.OK((Object) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertSame(responseEntity, likeServiceImpl.likePost(new LikeDto(123L, "janedoe", 123L, 3L, true)));
        verify(likeRepository).countAllByPost((Post) any());
        verify(likeRepository).save((Like) any());
        verify(likeRepository).findByPostAndUser((Post) any(), (User) any());
        verify(postRepository).findById((Long) any());
        verify(userRepository).findUserById(anyLong());
        verify(responseGenerator).created((Object) any());
    }
}