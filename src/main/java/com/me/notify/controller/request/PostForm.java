package com.me.notify.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private Long userId;

    @NotBlank(message = "제목은 필수 입력 값 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 값 입니다.")
    private String content;
}
