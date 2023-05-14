package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.model.entity.CabinetStudent;
import com.vitmvit.smarteducation.repository.CabinetStudentRepository;
import com.vitmvit.smarteducation.repository.UserRepository;
import com.vitmvit.smarteducation.service.CabinetStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class CabinetStudentServiceImpl implements CabinetStudentService {

    private final CabinetStudentRepository cabinetStudentRepository;
    private final UserRepository userRepository;

    @Override
    public CabinetStudent findOne(Long id) {
        return cabinetStudentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cabinet not found by id: " + id)
        );
    }

    @Override
    public CabinetStudent findOne(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found by login: " + login)
                )
                .getCabinetStudent();
    }

    @Override
    public CabinetStudent findOneByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User not found by id: " + userId)
                )
                .getCabinetStudent();
    }

    @Override
    public CabinetStudent save(CabinetStudent model) {
        return cabinetStudentRepository.save(model);
    }
}
