package com.me.notify.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm {

    @NotBlank(message = "내용을 입력하세요.")
    private String comment;
}
