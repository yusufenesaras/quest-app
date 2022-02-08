package com.quest.questapp.controller;

import com.quest.questapp.model.Post;
import com.quest.questapp.request.PostCreateRequest;
import com.quest.questapp.request.PostUpdateRequest;
import com.quest.questapp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){  //requestparam = bize gelen request içindeki parametreleri pars et sağında bulunan değişkenin içine at
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){//path deki değişkenin direkt değerini alıyor.
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest postRequest){
        return postService.createOnePost(postRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updateOnePostById(postId,postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }

}
