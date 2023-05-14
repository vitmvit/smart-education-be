package com.vitmvit.smarteducation.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AttendanceGroupRequest {

    @NotNull
    private Long attendanceId;

    @NotNull
    private Long studentId;

    @NotNull
    private Boolean visit;

    private Integer reason;
    private String description;
}
