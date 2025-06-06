package com.me.notify.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @GetMapping("/join")
    public String join() {
        return "/user/join";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
