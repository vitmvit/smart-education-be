package com.vitmvit.smarteducation.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceGroupResponse {

    private UserResponse student;
    private Boolean visit;
    private Integer reason;
    private String description;
}
