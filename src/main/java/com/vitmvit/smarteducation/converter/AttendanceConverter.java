package com.vitmvit.smarteducation.converter;

import com.vitmvit.smarteducation.model.dto.response.AttendanceGroupResponse;
import com.vitmvit.smarteducation.model.dto.response.AttendanceResponse;
import com.vitmvit.smarteducation.model.entity.Attendance;
import com.vitmvit.smarteducation.model.entity.AttendanceGroup;

public interface AttendanceConverter {

    AttendanceResponse convert(Attendance source);

    AttendanceGroupResponse convert(AttendanceGroup source);
}
