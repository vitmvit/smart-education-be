package com.vitmvit.smarteducation.scheduler;

import com.vitmvit.smarteducation.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RemoveOldUserScheduler {

    private final UserRepository userRepository;

    /**
     * стартует с задержкой в 60 секунд
     * отрабатывает 1 раз в сутки
     * удаляет всех пользователей по условиям:
     * 1 - роль USER
     * 2 - дата регистрации старше текущей на 7 дней
     */
    @Scheduled(initialDelay = 1000 * 60, fixedDelay = 1000 * 60 * 60 * 24)
    public void command() {

    }
}
