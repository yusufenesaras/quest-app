package com.quest.questapp.controller;

import com.quest.questapp.model.Comment;
import com.quest.questapp.request.CommentCreateRequest;
import com.quest.questapp.request.CommentUpdateRequest;
import com.quest.questapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }

}
