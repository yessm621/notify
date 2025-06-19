package com.me.notify.controller;

import com.me.notify.controller.request.CommentForm;
import com.me.notify.controller.request.PostForm;
import com.me.notify.controller.response.PostDetailResponse;
import com.me.notify.controller.response.PostResponse;
import com.me.notify.entity.dto.PostDto;
import com.me.notify.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public String postList(Pageable pageable, Model model) {
        Page<PostResponse> list = postService.list(pageable);
        model.addAttribute("postList", list);
        return "post/list";
    }

    @GetMapping("/create")
    public String postCreate(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/create";
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult,
                             Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "post/create";
        }
        postService.create(authentication.getName(), PostDto.from(postForm));
        return "redirect:/post";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable("postId") Long postId, Model model) {
        PostDetailResponse post = postService.detail(postId);
        model.addAttribute("post", post);
        model.addAttribute("commentForm", new CommentForm());
        return "post/detail";
    }

    @PostMapping("/{postId}/comment")
    public String commentCreate(@PathVariable("postId") Long postId, @Valid @ModelAttribute CommentForm commentForm,
                                BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "post/detail";
        }
        postService.commentCreate(postId, authentication.getName(), commentForm.getComment());
        return "redirect:/post/" + postId;
    }

    //TODO:: 내가 작성한 포스트만 출력
}
