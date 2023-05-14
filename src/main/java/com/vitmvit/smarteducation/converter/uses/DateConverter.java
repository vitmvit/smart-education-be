package com.vitmvit.smarteducation.converter.uses;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.vitmvit.smarteducation.config.constants.Constants.DATE_PATTERN;
import static com.vitmvit.smarteducation.config.constants.Constants.DATE_TIME_PATTERN;

public class DateConverter {

    public static String convert(Date date) {
        return date == null
                ? null
                : new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    public static String convert(LocalDate date) {
        return date == null
                ? null
                : date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String convert(LocalDateTime date) {
        return date == null
                ? null
                : date.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}
