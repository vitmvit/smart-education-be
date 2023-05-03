package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.request.FacultyRequest;
import com.vitmvit.smarteducation.model.dto.response.FacultyResponse;

import java.util.List;

public interface CathedraService {

    FacultyResponse findOne(Long id, String name);

    List<FacultyResponse> findAll();

    FacultyResponse save(FacultyRequest dto);

    void remove(Long id);
}
