package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface AttendanceService {

    Attendance findOne(Long id);

    Page<Attendance> findAll(Specification<Attendance> specification, Pageable pageable);

    Attendance save(Attendance model);

    void remove(Long id);
}
