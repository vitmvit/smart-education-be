package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.entity.CabinetTeacher;

public interface CabinetTeacherService {

    CabinetTeacher findOne(Long id);

    CabinetTeacher findOne(String login);

    CabinetTeacher findOneByUserId(Long userId);

    CabinetTeacher save(CabinetTeacher cabinetTeacher);
}
