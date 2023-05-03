package com.vitmvit.smarteducation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * для добавления новой роли, необходимо прописать ее здесь
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {

    // создание ROOT, ADMIN
    ROOT("ROOT"),
    // создание ADMIN, SUPERVISOR, TEACHER, установка STUDENT_LEAD
    ADMIN("ADMIN"),
    // наблюдение за всей статистикой
    SUPERVISOR("SUPERVISOR"),
    // наблюдение за группой, подтверждение посещений
    TEACHER("TEACHER"),
    // указание посещений
    STUDENT_LEAD("STUDENT_LEAD"),
    // просмотр своей статистики
    STUDENT("STUDENT"),
    // студент, ожидающий своей роли STUDENT
    USER("USER");

    private final String name;
}
