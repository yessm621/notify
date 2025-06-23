package com.me.notify.service;

import com.me.notify.controller.response.AlarmResponse;
import com.me.notify.entity.Users;
import com.me.notify.entity.dto.UserDto;
import com.me.notify.repository.AlarmRepository;
import com.me.notify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AlarmRepository alarmRepository;

    @Transactional
    @Override
    public void join(UserDto dto) {
        //TODO:: 아이디 중복 체크
        Users user = Users.of(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getName());
        userRepository.save(user);
    }

    @Override
    public Long findUserId(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));
        return user.getId();
    }

    @Override
    public List<AlarmResponse> alarmList(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        return alarmRepository.findAllByTargetUserId(user.getId())
                .stream()
                .map(AlarmResponse::fromEntity)
                .toList();
    }
}
