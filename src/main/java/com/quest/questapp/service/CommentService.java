package com.quest.questapp.service;

import com.quest.questapp.model.Comment;
import com.quest.questapp.model.Post;
import com.quest.questapp.model.User;
import com.quest.questapp.repository.CommentRepository;
import com.quest.questapp.request.CommentCreateRequest;
import com.quest.questapp.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else{
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUserById(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());
        if(user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(commentCreateRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText((commentCreateRequest.getText()));
            return commentRepository.save(commentToSave);
        }else{
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }else{
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
