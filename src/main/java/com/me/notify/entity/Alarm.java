package com.me.notify.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private Users fromUserId; // 댓글 또는 좋아요를 누른 사람 (알림을 발생시킨 사람)

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Users targetId;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;
}
