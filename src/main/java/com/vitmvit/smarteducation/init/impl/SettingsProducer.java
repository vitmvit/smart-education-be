package com.vitmvit.smarteducation.init.impl;

import com.vitmvit.smarteducation.config.singleton.SettingsSingleton;
import com.vitmvit.smarteducation.constant.Constants;
import com.vitmvit.smarteducation.init.CommandLine;
import com.vitmvit.smarteducation.service.SettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @see "https://www.baeldung.com/spring-qualifier-annotation"
 */
@Slf4j
@Component
@Qualifier("settingsProducer")
public class SettingsProducer implements CommandLine {

    @Autowired
    private SettingsService settingsService;

    @Override
    public void command() {
        settingsService.getSettings();
        if (null == SettingsSingleton.getInstance().getConfigMap().get("file_storage")) {
            settingsService.addSettings("file_storage", Constants.FILE_STORAGE);
        }
        if (null == SettingsSingleton.getInstance().getConfigMap().get("file_deep")) {
            settingsService.addSettings("file_deep", Constants.FILE_DEEP);
        }
    }
}
