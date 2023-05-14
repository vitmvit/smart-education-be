package com.vitmvit.smarteducation.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static boolean isEmpty(CharSequence source) {
        return source == null || source.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence source) {
        return !isEmpty(source);
    }
}
