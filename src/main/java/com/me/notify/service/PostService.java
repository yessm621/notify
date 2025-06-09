package com.me.notify.service;

import com.me.notify.controller.response.PostResponse;
import com.me.notify.entity.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void create(PostDto dto);

    Page<PostResponse> list(Pageable pageable);
}
