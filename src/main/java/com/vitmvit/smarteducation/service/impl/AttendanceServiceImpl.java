package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.model.entity.Attendance;
import com.vitmvit.smarteducation.repository.AttendanceRepository;
import com.vitmvit.smarteducation.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance findOne(Long id) {
        return attendanceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Attendance not found by id: " + id)
        );
    }

    @Override
    public Page<Attendance> findAll(Specification<Attendance> specification, Pageable pageable) {
        return attendanceRepository.findAll(specification, pageable);
    }

    @Override
    public Attendance save(Attendance model) {
        return attendanceRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        attendanceRepository.deleteById(id);
    }
}
