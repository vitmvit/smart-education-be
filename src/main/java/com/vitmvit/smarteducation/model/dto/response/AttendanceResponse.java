package com.vitmvit.smarteducation.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AttendanceResponse {

    private LocalDate date;
    private GroupResponse group;
    private SubjectResponse subject;
    private UserResponse teacher;
    private Integer lessonNumber;
    private List<AttendanceGroupResponse> attendanceGroup;
}
