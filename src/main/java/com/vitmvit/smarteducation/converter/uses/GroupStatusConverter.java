package com.vitmvit.smarteducation.converter.uses;

import com.vitmvit.smarteducation.model.constant.GroupStatus;

public class GroupStatusConverter {

    public static String convert(GroupStatus source) {
        if (source.getName().equalsIgnoreCase("O")) {
            return "Открыта";
        } else if (source.getName().equalsIgnoreCase("C")) {
            return "Закрыта";
        }
        return "Неизвестен";
    }

    public static GroupStatus convert(String source) {
        if (source.equalsIgnoreCase("Открыта")) {
            return GroupStatus.OPEN;
        } else if (source.equalsIgnoreCase("Закрыта")) {
            return GroupStatus.CLOSE;
        }
        return GroupStatus.valueOf(source);
    }
}
