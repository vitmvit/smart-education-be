package com.vitmvit.smarteducation.config;

import com.vitmvit.smarteducation.init.AppInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see "https://www.baeldung.com/spring-boot-console-app"
 */
@Configuration
public class AppInitConfig {

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new AppInitializer();
    }
}