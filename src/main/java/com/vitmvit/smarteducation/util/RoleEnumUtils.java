package com.vitmvit.smarteducation.util;

import com.vitmvit.smarteducation.constant.RoleEnum;

import java.util.Arrays;

public class RoleEnumUtils {

    public static String[] allAdminRoles() {
        return new String[]{RoleEnum.ROOT.getName(), RoleEnum.ADMIN.getName()};
    }

    public static String[] allAuthRoles() {
        RoleEnum[] roleArray = RoleEnum.values();
        String[] array = new String[roleArray.length - 1];
        int idx = 0;
        for (RoleEnum roleEnum : roleArray) {
            if (!RoleEnum.USER.getName().equals(roleEnum.getName())) {
                array[idx++] = roleEnum.getName();
            }
        }
        return array;
    }

    public static String[] allRoles() {
        return Arrays.stream(RoleEnum.values()).map(RoleEnum::getName).toArray(String[]::new);
    }
}
