package com.me.notify.service;

import com.me.notify.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final static Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    private final static String ALARM_NAME = "alarm";
    private final EmitterRepository emitterRepository;

    public SseEmitter connectAlarm(Long userId) {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(userId, sseEmitter);

        sseEmitter.onCompletion(() -> emitterRepository.delete(userId));
        sseEmitter.onTimeout(() -> emitterRepository.delete(userId));

        try {
            sseEmitter.send(
                    SseEmitter.event()
                    .id("id")
                    .name(ALARM_NAME)
                    .data("SseEmitter Connect Complete")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sseEmitter;
    }

    public void send(Long alarmId, Long targetId) {
        emitterRepository.get(targetId).ifPresentOrElse(sseEmitter -> {
            try {
                sseEmitter.send(
                        SseEmitter.event()
                                .id(alarmId.toString())
                                .name(ALARM_NAME)
                                .data("New Alarm")
                );
            } catch (IOException e) {
                emitterRepository.delete(targetId);
                throw new RuntimeException(e);
            }
        }, () -> log.info("No Emitter Founded."));
    }
}
