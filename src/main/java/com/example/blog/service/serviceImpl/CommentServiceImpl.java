package com.example.blog.service.serviceImpl;

import com.example.blog.dto.CommentDto;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.service.ICommentService;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements ICommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private ResponseGenerator responseGenerator;



    @Override
    public ResponseEntity postComment(CommentDto commentDto) throws UserNotFoundException, PostNotFoundException {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(()->new PostNotFoundException("Post not found"));
        User user = userRepository.findUserById(commentDto.getUserId()).orElseThrow(()->new UserNotFoundException("User not found"));
        Comment comment = new Comment();
        comment.setCommentBody(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        Comment response = commentRepository.save(comment);
        return responseGenerator.created(response);
    }

    @Override
    public ResponseEntity updateComment(CommentDto commentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CommentNotFoundException("Comment not Found"));
        comment.setCommentBody(commentDto.getComment());
        Comment response = commentRepository.save(comment);
        return responseGenerator.created(response);
    }

    @Override
    public ResponseEntity deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new CommentNotFoundException("Comment not found"));
        commentRepository.deleteById(commentId);
        String response = "Successfully deleted comment";
        return responseGenerator.OK(response);
    }

    @Override
    public ResponseEntity<List<CommentDto>> getAllCommentOfAPost(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFoundException(("Post not found")));
        List<Comment> comments = commentRepository.findAllByPost(post);
        List<CommentDto> response = comments.stream().map(this::mapToCommentDto).toList();
        return responseGenerator.OK(response);
    }

    private CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getCommentBody());
        commentDto.setPostId(comment.getPost().getId());
        commentDto.setUserId(comment.getUser().getId());
        return commentDto;
    }
}
