package com.example.blog.service.serviceImpl;

import com.example.blog.dto.Payload.CommentPayLoad;
import com.example.blog.dto.Payload.LikePayLoad;
import com.example.blog.dto.Payload.PostPayLoad;
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
        post.setTitle(postDto.getTitle());
        PostPayLoad response = mapPostToPostPayLoad(postRepository.save(post));
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
        return responseGenerator.OK(mapPostToPostPayLoad(postRepository.save(post)));
    }

    @Override
    public ResponseEntity deletePost(Long id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
        postRepository.delete(post);
        String response = "Post have been deleted";
        return responseGenerator.OK(response);
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostPayLoad> postPayLoads = posts.stream().map(this::mapPostToPostPayLoad).toList();
        return responseGenerator.OK(postPayLoads);
    }

    @Override
    public ResponseEntity<PostDto> getPost(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post Not found"));
        return responseGenerator.OK(mapPostToDto(post));
    }
@Override
    public ResponseEntity searchPostByKeyWord(String keyWord){
        List<PostDto>response = postRepository.findAllByTitleContainingIgnoreCase(keyWord).stream().map(this::mapPostToDto).toList();
        return responseGenerator.OK(response);
    }

    private PostDto mapPostToDto(Post post) {
//       return modelMapper.map(post, PostDto.class);
        long postUserId = post.getUser().getId();
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getId());
        postDto.setOp(post.getUser().getUsername());
        postDto.setDescription(post.getDescription());
        postDto.setPostContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setLikes(post.getLikes().stream().map(this::mapLikeToLikePayLoad).toList());
        postDto.setComments(post.getComment().stream().map(this::mapCommentsToDTO).toList());
        return postDto;
    }


    // todo : move to utils mapper

    private PostPayLoad mapPostToPostPayLoad(Post post){
        PostPayLoad ppl = new PostPayLoad();
        ppl.setPostId(post.getId());
        ppl.setOp(post.getUser().getUsername());
        ppl.setTitle(post.getTitle());
        ppl.setPostContent(post.getContent());
        ppl.setDescription(post.getDescription());
        ppl.setComments(post.getComment().stream().map(this::mapCommentsToDTO).toList());
        ppl.setLikes(post.getLikes().stream().map(this::mapLikeToLikePayLoad).toList());
        return ppl;
    }

    private LikePayLoad mapLikeToLikePayLoad(Like like) {
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
}
