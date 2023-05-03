package com.vitmvit.smarteducation.config.singleton;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SettingsSingleton {

    private static SettingsSingleton instance;

    @Getter
    private final Map<String, String> configMap;

    private SettingsSingleton(Map<String, String> configMap) {
        this.configMap = configMap;
    }

    public static SettingsSingleton getInstance() {
        return setInstance(null);
    }

    public static SettingsSingleton setInstance(Map<String, String> configMap) {
        if (instance == null) {
            instance = new SettingsSingleton(new HashMap<>(0));
        }
        if (configMap != null) {
            instance = new SettingsSingleton(configMap);
        }
        return instance;
    }
}
