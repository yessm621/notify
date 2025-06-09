package com.me.notify.controller.response;

import com.me.notify.entity.Post;

public record PostResponse(Long userId, String name, String title, String content) {

    public static PostResponse fromEntity(Post post) {
        return new PostResponse(
                post.getUser().getId(),
                post.getUser().getName(),
                post.getTitle(),
                post.getContent()
        );
    }
}
