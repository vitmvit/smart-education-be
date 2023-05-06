package com.vitmvit.smarteducation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.vitmvit.smarteducation.constant.Constants.ROLE_PREFIX;

/**
 * для добавления новой роли, необходимо прописать ее здесь
 */
@AllArgsConstructor
public enum RoleEnum {

    // создание ROOT, ADMIN
    ROOT(ROLE_PREFIX + "ROOT"),
    // создание ADMIN, SUPERVISOR, TEACHER, установка STUDENT_LEAD
    ADMIN(ROLE_PREFIX + "ADMIN"),
    // наблюдение за всей статистикой
    SUPERVISOR(ROLE_PREFIX + "SUPERVISOR"),
    // наблюдение за группой, подтверждение посещений
    TEACHER(ROLE_PREFIX + "TEACHER"),
    // указание посещений
    STUDENT_LEAD(ROLE_PREFIX + "STUDENT_LEAD"),
    // просмотр своей статистики
    STUDENT(ROLE_PREFIX + "STUDENT"),
    // студент, ожидающий своей роли STUDENT
    USER(ROLE_PREFIX + "USER");

    @Getter
    private final String name;
}
