package com.me.notify.controller.response;

import com.me.notify.entity.Post;

import java.util.List;

public record PostDetailResponse(Long postId, Long userId, String writer, String title, String content,
                                 List<CommentResponse> comments) {

    public static PostDetailResponse fromEntity(Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getComments().stream()
                        .map(CommentResponse::fromEntity)
                        .toList()
        );
    }
}
