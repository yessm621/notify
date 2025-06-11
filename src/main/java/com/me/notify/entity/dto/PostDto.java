package com.me.notify.entity.dto;

import com.me.notify.controller.request.PostForm;
import com.me.notify.entity.Post;

public record PostDto(Long userId, String title, String content) {

    public static PostDto from(PostForm form) {
        return new PostDto(
                form.getUserId(),
                form.getTitle(),
                form.getContent()
        );
    }

    public static PostDto fromEntity(Post post) {
        return new PostDto(
                post.getUser().getId(),
                post.getTitle(),
                post.getContent()
        );
    }
}
