package com.example.blog.service.serviceImpl;

import com.example.blog.dto.Payload.CommentPayLoad;
import com.example.blog.dto.Payload.LikePayLoad;
import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.model.Like;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.service.IPostService;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ResponseGenerator responseGenerator;
//    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity createPost(PostDto postDto) throws UserNotFoundException {
        long userId = postDto.getUserId();
        User user = userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Post post = new Post();
        post.setUser(user);
        post.setContent(postDto.getPostContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(post.getTitle());
        PostDto response = mapPostToDto(postRepository.save(post));
        return responseGenerator.created(response);

    }

    @Override
    public ResponseEntity updatePost(PostDto postDto) throws PostNotFoundException, UserNotFoundException {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(() -> new PostNotFoundException("post not found"));
        User user = userRepository.findUserById(postDto.getUserId()).orElseThrow(() -> new UserNotFoundException("user not found"));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getPostContent());
        post.setDescription(postDto.getDescription());
        post.setUser(user);
        return responseGenerator.OK(mapPostToDto(postRepository.save(post)));
    }

    @Override
    public ResponseEntity deletePost(PostDto postDto) throws PostNotFoundException {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(() -> new PostNotFoundException("Post not found"));
        postRepository.delete(post);
        String response = "Post have been deleted";
        return responseGenerator.OK(response);
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(this::mapPostToDto).toList();
        return responseGenerator.OK(postDtos);
    }

    @Override
    public ResponseEntity<PostDto> getPost(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post Not found"));
        return responseGenerator.OK(mapPostToDto(post));
    }

    public ResponseEntity searchPostByKeyWord(String keyword){
        List<PostDto>response = postRepository.findAllByTitleContainingIgnoreCase(keyword).stream().map(this::mapPostToDto).toList();
        return responseGenerator.OK(response);
    }

    private PostDto mapPostToDto(Post post) {
//       return modelMapper.map(post, PostDto.class);
        long postUserId = post.getUser().getId();
        PostDto postDto = new PostDto();
        postDto.setPostId(postUserId);
        postDto.setOp(post.getUser().getUsername());
        postDto.setDescription(post.getDescription());
        postDto.setPostContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setLikes(post.getLikes().stream().map(this::mapLikeToLikeDto).filter(LikePayLoad::isLiked).toList());
        postDto.setComments(post.getComment().stream().map(this::mapCommentsToDTO).toList());
        return postDto;
    }
    private LikePayLoad mapLikeToLikeDto(Like like) {
        LikePayLoad likePayLoad = new LikePayLoad();
        likePayLoad.setUserNameOfLiker(like.getUser().getUsername());
        return likePayLoad;
    }
    private CommentPayLoad mapCommentsToDTO(Comment comment){
        CommentPayLoad commentPayLoad = new CommentPayLoad();
        commentPayLoad.setComment(comment.getCommentBody());
        commentPayLoad.setCommentOwner(comment.getUser().getUsername());
        return commentPayLoad;
    }
//
//    private Post mapToEntity(PostDto postDto){
////       return modelMapper.map(postDto, Post.class );
//       Post post = new Post();
//       post.setTitle(postDto.getTitle());
//       post.setContent(post.getContent());
//       post.setDescription();
//    }

}
