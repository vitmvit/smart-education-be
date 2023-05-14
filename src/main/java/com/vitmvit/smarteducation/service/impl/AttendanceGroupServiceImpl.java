package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.model.entity.AttendanceGroup;
import com.vitmvit.smarteducation.repository.AttendanceGroupRepository;
import com.vitmvit.smarteducation.service.AttendanceGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class AttendanceGroupServiceImpl implements AttendanceGroupService {

    private final AttendanceGroupRepository attendanceGroupRepository;

    @Override
    public AttendanceGroup findOne(Long id) {
        return attendanceGroupRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("AttendanceGroup not found by id: " + id)
        );
    }

    @Override
    public AttendanceGroup save(AttendanceGroup model) {
        return attendanceGroupRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        attendanceGroupRepository.deleteById(id);
    }
}
