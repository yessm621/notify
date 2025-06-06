package com.me.notify.service;

import com.me.notify.entity.Users;
import com.me.notify.entity.dto.UserDto;
import com.me.notify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void join(UserDto dto) {
        Users user = Users.of(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getName());
        userRepository.save(user);
    }
}
