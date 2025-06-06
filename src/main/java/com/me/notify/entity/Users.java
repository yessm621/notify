package com.me.notify.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String name;

    public static Users of(String username, String password, String name) {
        Users users = new Users();
        users.username = username;
        users.password = password;
        users.name = name;
        return users;
    }
}
