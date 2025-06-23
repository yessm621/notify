package com.me.notify.repository;

import com.me.notify.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByTargetUserId(Long targetUserId);
}
