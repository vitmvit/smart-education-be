package com.vitmvit.smarteducation.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AttendanceRequest {

    @NotNull
    private LocalDate date;

    @NotNull
    private Long groupId;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long teacherId;

    @NotNull
    private Integer lessonNumber;

    @NotNull
    private Long studentId;

    @NotNull
    private Boolean visit;

    private Integer reason;
    private String description;
}
