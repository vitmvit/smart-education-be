package com.vitmvit.smarteducation.config.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * для добавления новой роли, необходимо прописать ее здесь
 */
@AllArgsConstructor
public enum RoleEnum {

    // создание ROOT, ADMIN
    ROOT(Constants.ROLE_PREFIX + "ROOT"),
    // создание ADMIN, SUPERVISOR, TEACHER, установка STUDENT_LEAD
    ADMIN(Constants.ROLE_PREFIX + "ADMIN"),
    // наблюдение за всей статистикой
    SUPERVISOR(Constants.ROLE_PREFIX + "SUPERVISOR"),
    // наблюдение за группой, подтверждение посещений
    TEACHER(Constants.ROLE_PREFIX + "TEACHER"),
    // указание посещений
    STUDENT_LEAD(Constants.ROLE_PREFIX + "STUDENT_LEAD"),
    // просмотр своей статистики
    STUDENT(Constants.ROLE_PREFIX + "STUDENT"),
    // студент, ожидающий своей роли STUDENT
    USER(Constants.ROLE_PREFIX + "USER");

    @Getter
    private final String name;
}
