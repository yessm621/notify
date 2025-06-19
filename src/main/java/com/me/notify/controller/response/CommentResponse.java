package com.me.notify.controller.response;

import com.me.notify.entity.Comment;

public record CommentResponse(Long commentId, String writer, String comment) {

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getComment()
        );
    }
}
