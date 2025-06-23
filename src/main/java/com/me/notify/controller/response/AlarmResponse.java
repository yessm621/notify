package com.me.notify.controller.response;

import com.me.notify.entity.Alarm;
import com.me.notify.entity.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlarmResponse {

    private Long id;
    private Long fromUserId;
    private Long targetId;
    private AlarmType alarmType;
    private String text;

    public static AlarmResponse fromEntity(Alarm alarm) {
        return new AlarmResponse(
                alarm.getId(),
                alarm.getFromUser().getId(),
                alarm.getTargetUser().getId(),
                alarm.getAlarmType(),
                alarm.getAlarmType().getAlarmText()
        );
    }
}
