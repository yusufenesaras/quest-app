package com.quest.questapp.service;

import com.quest.questapp.model.Post;
import com.quest.questapp.model.User;
import com.quest.questapp.repository.PostRepository;
import com.quest.questapp.request.PostCreateRequest;
import com.quest.questapp.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository,UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }else{
            return postRepository.findAll();
        }
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(Post post) {
        return postRepository.save(post);
    }
    public Post createOnePost(PostCreateRequest postRequest) {
        User user = userService.getOneUserById(postRequest.getUserId());
        if(user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(postRequest.getId());
        toSave.setText(postRequest.getText());
        toSave.setTitle(postRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId) {
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }
}
