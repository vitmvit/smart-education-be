package com.vitmvit.smarteducation.util;

import com.vitmvit.smarteducation.constant.RoleEnum;

import java.util.Arrays;

import static com.vitmvit.smarteducation.constant.Constants.ROLE_PREFIX;

public class RoleEnumUtils {

    // for creating roles into DB
    public static String[] allRolesArray() {
        return Arrays.stream(RoleEnum.values()).map(RoleEnum::getName).toArray(String[]::new);
    }

    // ROOT + ADMIN
    public static String allRootRoles() {
        return getRolesForSecurity(new String[]{RoleEnum.ROOT.getName(), RoleEnum.ADMIN.getName()});
    }

    // all roles without USER
    public static String allAuthRoles() {
        RoleEnum[] roleArray = RoleEnum.values();
        String[] array = new String[roleArray.length - 1];
        int idx = 0;
        for (RoleEnum roleEnum : roleArray) {
            if (!RoleEnum.USER.getName().equals(roleEnum.getName())) {
                array[idx++] = roleEnum.getName();
            }
        }
        return getRolesForSecurity(array);
    }

    // all roles with USER
    public static String allUserRoles() {
        RoleEnum[] roleArray = RoleEnum.values();
        String[] array = new String[roleArray.length];
        int idx = 0;
        for (RoleEnum roleEnum : roleArray) {
            array[idx++] = roleEnum.getName();
        }
        return getRolesForSecurity(array);
    }

    private static String getRolesForSecurity(String[] roleArray) {
        StringBuilder stringBuilder = new StringBuilder("hasAnyRole(");
        for (String role : roleArray) {
            stringBuilder.append("'").append(role.substring(ROLE_PREFIX.length())).append("', ");
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
