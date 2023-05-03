package com.vitmvit.smarteducation.service;

import java.util.Map;
import java.util.Optional;

public interface SettingsService {

    Map<String, String> getSettings();

    Optional<String> getSettings(String key);

    void addSettings(String key, String value);

    void delSettings(String key);
}