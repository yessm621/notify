package com.me.notify.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private Users fromUser; // 댓글 또는 좋아요를 누른 사람 (알림을 발생시킨 사람)

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Users targetUser;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    public static Alarm of(Users fromUser, Users targetUser, AlarmType type) {
        Alarm alarm = new Alarm();
        alarm.fromUser = fromUser;
        alarm.targetUser = targetUser;
        alarm.alarmType = type;
        return alarm;
    }
}
