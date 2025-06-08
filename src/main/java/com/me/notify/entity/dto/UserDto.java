package com.me.notify.entity.dto;

import com.me.notify.controller.request.JoinForm;
import com.me.notify.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String name;

    public static UserDto from(JoinForm form) {
        return new UserDto(
                form.getUsername(),
                form.getPassword(),
                form.getName()
        );
    }

    public static UserDto fromEntity(Users users) {
        return new UserDto(
                users.getUsername(),
                users.getPassword(),
                users.getName()
        );
    }

    public Users toEntity() {
        return Users.of(username, password, name);
    }
}
