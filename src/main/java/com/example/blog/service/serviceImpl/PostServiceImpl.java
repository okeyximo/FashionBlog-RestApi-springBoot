package com.example.blog.service.serviceImpl;

import com.example.blog.dto.PostDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import com.example.blog.service.PostService;
import com.example.blog.utils.ResponseGenerator;
import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ResponseGenerator responseGenerator;
//    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity createPost(PostDto postDto){
        long userId = postDto.getUserId();
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isPresent()){
            Post post = new Post();
            post.setUser(userOptional.get());
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
            post.setTitle(post.getTitle());
            Post response = postRepository.save(post);
            return responseGenerator.created(response);
        }else{
            return responseGenerator.unauthorised("UNAUTHORIZED");
        }
    }

    @Override
    public ResponseEntity updatePost(PostDto postDto) throws PostNotFoundException, UserNotFoundException {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(()-> new PostNotFoundException("post not found"));
        User user = userRepository.findUserById(postDto.getUserId()).orElseThrow(()-> new UserNotFoundException("user not found"));
        post.setTitle(postDto.getTitle());
        post.setContent(post.getContent());
        post.setDescription(postDto.getDescription());
        post.setUser(user);
        return responseGenerator.OK(postRepository.save(post));
    }

    @Override
    public ResponseEntity deletePost(PostDto postDto) throws PostNotFoundException {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(()-> new PostNotFoundException("Post not found"));
        postRepository.delete(post);
        String response = "Post have been deleted";
        return responseGenerator.OK(response);
    }

    @Override
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
       List<PostDto> postDtos = posts.stream().map(this::mapToDto).toList();
       return responseGenerator.OK(postDtos);
    }

    @Override
    public ResponseEntity<PostDto> getPost(Long postId) throws PostNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post Not found"));
        return responseGenerator.OK(mapToDto(post));
    }










    private PostDto mapToDto(Post post){
//       return modelMapper.map(post, PostDto.class);
        long postUserId = post.getUser().getId();
        PostDto postDto = new PostDto();
        postDto.setPostId(postUserId);
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setLikes(post.getLikes());
        postDto.setComments(post.getComment());
        return postDto;
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
