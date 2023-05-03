package com.vitmvit.smarteducation.init.impl;

import com.vitmvit.smarteducation.init.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Qualifier("subjectCreator")
public class SubjectCreator implements CommandLine {

    private static final List<String> SUBJECT_LIST = List.of(
            "Высшая математика",
            "Физика"
    );

    @Override
    public void command() {

    }
}
