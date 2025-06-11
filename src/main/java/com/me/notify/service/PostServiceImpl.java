package com.me.notify.service;

import com.me.notify.controller.response.PostResponse;
import com.me.notify.entity.Post;
import com.me.notify.entity.Users;
import com.me.notify.entity.dto.PostDto;
import com.me.notify.repository.PostRepository;
import com.me.notify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void create(String username, PostDto dto) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));
        postRepository.save(Post.of(user, dto.title(), dto.content()));
    }

    @Override
    public Page<PostResponse> list(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostResponse::fromEntity);
    }

    @Transactional
    @Override
    public void like(Long postId, String username) {

    }

    @Transactional
    @Override
    public void comment(Long postId, String username, String comment) {

    }
}
