package com.vitmvit.smarteducation.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AttendanceSearch {

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;

    @NotNull
    private Long groupId;

    private Long subjectId;
    private Long teacherId;
    private Long studentId;
}
