package com.me.notify.controller.response;

import com.me.notify.entity.Post;

public record PostResponse(Long postId, Long userId, String name, String title, String content) {

    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent()
        );
    }
}
