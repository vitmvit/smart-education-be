package com.vitmvit.smarteducation.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;

/**
 * @see "https://www.baeldung.com/spring-boot-console-app"
 */
public class AppInitializer implements CommandLineRunner {

    @Autowired
    @Qualifier("roleCreator")
    private CommandLine roleCreator;

    @Autowired
    @Qualifier("userCreator")
    private CommandLine userCreator;

    @Autowired
    @Qualifier("settingsProducer")
    private CommandLine settingsProducer;

    @Override
    public void run(String... args) {
        roleCreator.command();
        userCreator.command();
        settingsProducer.command();
    }
}
