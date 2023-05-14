package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.entity.CabinetStudent;

public interface CabinetStudentService {

    CabinetStudent findOne(Long id);

    CabinetStudent findOne(String login);

    CabinetStudent findOneByUserId(Long userId);

    CabinetStudent save(CabinetStudent model);
}
