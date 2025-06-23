package com.me.notify.controller;

import com.me.notify.controller.request.JoinForm;
import com.me.notify.controller.request.LoginForm;
import com.me.notify.controller.response.AlarmResponse;
import com.me.notify.entity.dto.UserDto;
import com.me.notify.service.AlarmService;
import com.me.notify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AlarmService alarmService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "user/join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user/login";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute JoinForm joinForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/join";
        }
        userService.join(UserDto.from(joinForm));
        return "redirect:/user/login";
    }

    @GetMapping("/alarm")
    public String alarm(Authentication authentication, Model model) {
        List<AlarmResponse> alarm = userService.alarmList(authentication.getName());
        for (AlarmResponse alarmResponse : alarm) {
            System.out.println("alarmResponse = " + alarmResponse);
        }
        model.addAttribute("alarms", alarm);
        return "alarm/list";
    }

    @GetMapping("/alarm/subscribe")
    public SseEmitter subscribe(Authentication authentication) {
        Long userId = userService.findUserId(authentication.getName());
        return alarmService.connectAlarm(userId);
    }
}
