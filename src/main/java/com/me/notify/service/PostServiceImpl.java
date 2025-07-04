package com.me.notify.service;

import com.me.notify.controller.response.PostDetailResponse;
import com.me.notify.controller.response.PostResponse;
import com.me.notify.entity.*;
import com.me.notify.entity.dto.PostDto;
import com.me.notify.repository.AlarmRepository;
import com.me.notify.repository.CommentRepository;
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
    private final CommentRepository commentRepository;
    private final AlarmRepository alarmRepository;
    private final AlarmService alarmService;

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

    @Override
    public PostDetailResponse detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("post가 존재하지 않습니다."));
        return PostDetailResponse.fromEntity(post);
    }

    @Transactional
    @Override
    public void commentCreate(Long postId, String writer, String comment) {
        Users user = userRepository.findByUsername(writer).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        commentRepository.save(Comment.createComment(user, post, comment));

        Alarm alarm = alarmRepository.save(Alarm.of(post.getUser(), user, AlarmType.NEW_COMMENT_ON_POST));
        alarmService.send(alarm.getId(), post.getUser().getId());
    }

    public void sendAlarm(String type) {

    }
}
