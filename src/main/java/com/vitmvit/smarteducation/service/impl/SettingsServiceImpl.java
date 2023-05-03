package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.config.singleton.SettingsSingleton;
import com.vitmvit.smarteducation.model.entity.Settings;
import com.vitmvit.smarteducation.repository.SettingsRepository;
import com.vitmvit.smarteducation.service.SettingsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;

    //------------------------------------------------------------------------------------------------------------------

    /**
     * если синглтон пуст, то заполняем его данными из БД
     * если полон, возвращаем данные из синглтона не обращаясь к БД (снижение нагрузки)
     */
    @Override
    public Map<String, String> getSettings() {
        if (SettingsSingleton.getInstance().getConfigMap().isEmpty()) {
            SettingsSingleton.setInstance(settingsRepository.getSettings());
        }
        return SettingsSingleton.getInstance().getConfigMap();
    }

    @Override
    public Optional<String> getSettings(String key) {
        return Optional.ofNullable(getSettings().get(key));
    }

    /**
     * при добавлении новой настройки, проверяем, есть ли такая в БД
     * если нет: записываем полностью (ключ и значение)
     * если есть: обновляем значение
     * обновляем синглтон
     */
    @Override
    @Transactional
    public void addSettings(String key, String value) {
        Settings settings = settingsRepository.findByKey(key).orElse(null);
        if (settings == null) {
            settingsRepository.save(new Settings(key, value));
        } else {
            settings.setValue(value.startsWith(",") ? value.substring(1) : value);
            settingsRepository.save(settings);
        }
        SettingsSingleton.setInstance(settingsRepository.getSettings());
    }

    /**
     * удаляем запись в БД
     * обновляем синглтон, чтобы метод getSettings() не обращался к БД (снижение нагрузки)
     */
    @Override
    @Transactional
    public void delSettings(String key) {
        settingsRepository.deleteByKey(key);
        SettingsSingleton.setInstance(settingsRepository.getSettings());
    }
}