package com.me.notify.service;

import com.me.notify.controller.response.AlarmResponse;
import com.me.notify.entity.dto.UserDto;

import java.util.List;

public interface UserService {

    void join(UserDto dto);

    Long findUserId(String username);

    List<AlarmResponse> alarmList(String username);
}
