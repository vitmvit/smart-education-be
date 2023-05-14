package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.entity.AttendanceGroup;

public interface AttendanceGroupService {

    AttendanceGroup findOne(Long id);

    AttendanceGroup save(AttendanceGroup model);

    void remove(Long id);
}
