package com.me.notify.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String title;
    private String content;

    public static Post of(Users user, String title, String content) {
        Post post = new Post();
        post.user = user;
        post.title = title;
        post.content = content;
        return post;
    }
}
