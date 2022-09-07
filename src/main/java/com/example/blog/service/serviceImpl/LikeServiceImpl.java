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
import com.example.blog.service.LikeService;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ResponseGenerator responseGenerator;


    @Override
    public ResponseEntity likePost(long postId, long userId) throws PostNotFoundException, UserNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post not found"));
        User user = userRepository.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        LikeDto response = new LikeDto();
        Optional<Like> likeOptional = likeRepository.findByPostAndUser(post, user);
        if (likeOptional.isEmpty()){
            Like like = new Like(post, user);
            likeRepository.save(like);
            long totalLikes = likeRepository.countAllByPost(post);
            response.setPostId(postId);
            response.setCount(totalLikes);
            response.setLiked(true);
            return responseGenerator.created(response);
        }else{
            likeRepository.delete(likeOptional.get());
            long totalLikes = likeRepository.countAllByPost(post);
            response.setLiked(false);
            response.setPostId(postId);
            response.setCount(totalLikes);
            return responseGenerator.OK(response);

        }

    }
}
