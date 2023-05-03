package com.vitmvit.smarteducation.model.constant;

import lombok.Getter;

public enum GroupStatus {

    OPEN("O"),
    CLOSE("C");

    @Getter
    private final String name;

    GroupStatus(String name) {
        this.name = name;
    }
}
