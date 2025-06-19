package com.me.notify.service;

import com.me.notify.controller.response.PostDetailResponse;
import com.me.notify.controller.response.PostResponse;
import com.me.notify.entity.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void create(String username, PostDto dto);

    Page<PostResponse> list(Pageable pageable);

    void like(Long postId, String username);

    void comment(Long postId, String username, String comment);

    PostDetailResponse detail(Long postId);

    void commentCreate(Long postId, String writer, String comment);
}
