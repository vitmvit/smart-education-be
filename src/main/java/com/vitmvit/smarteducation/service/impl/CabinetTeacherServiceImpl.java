package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.model.entity.CabinetTeacher;
import com.vitmvit.smarteducation.repository.CabinetTeacherRepository;
import com.vitmvit.smarteducation.repository.UserRepository;
import com.vitmvit.smarteducation.service.CabinetTeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class CabinetTeacherServiceImpl implements CabinetTeacherService {

    private final CabinetTeacherRepository cabinetTeacherRepository;
    private final UserRepository userRepository;

    @Override
    public CabinetTeacher findOne(Long id) {
        return cabinetTeacherRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cabinet not found by id: " + id)
        );
    }

    @Override
    public CabinetTeacher findOne(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found by login: " + login)
                )
                .getCabinetTeacher();
    }

    @Override
    public CabinetTeacher findOneByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found by id: " + userId)
                )
                .getCabinetTeacher();
    }

    @Override
    public CabinetTeacher save(CabinetTeacher cabinetTeacher) {
        return cabinetTeacherRepository.save(cabinetTeacher);
    }
}
