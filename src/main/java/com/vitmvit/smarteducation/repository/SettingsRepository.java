package com.vitmvit.smarteducation.repository;

import com.vitmvit.smarteducation.model.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, String> {

    Optional<Settings> findByKey(String key);

    default Map<String, String> getSettings() {
        List<Settings> list = findAll();
        Map<String, String> map = new HashMap<>(list.size());
        for (Settings settings : list) {
            map.put(settings.getKey(), settings.getValue());
        }
        return map;
    }

    @Modifying
    @Query("DELETE FROM Settings s WHERE s.key = :key")
    void deleteByKey(String key);
}
