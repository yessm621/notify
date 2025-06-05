package com.me.notify.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String name;
}
