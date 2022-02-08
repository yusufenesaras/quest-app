package com.quest.questapp.request;


import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id;
    Long userId;
    Long postId;
    String text;
}
