package com.vitmvit.smarteducation.util;

import com.vitmvit.smarteducation.config.singleton.SettingsSingleton;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileStorageUtils {

    public static String fileStorage() {
        String fullPath = SettingsSingleton.getInstance().getConfigMap().getOrDefault("file_storage", "");
        return fullPath.endsWith("/") ? fullPath : fullPath.concat("/");
    }

    public static int fileDeep() {
        return Integer.parseInt(SettingsSingleton.getInstance().getConfigMap().getOrDefault("file_deep", "1"));
    }
}
