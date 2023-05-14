package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.request.StudentRequest;
import com.vitmvit.smarteducation.model.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {

    SubjectResponse findOne(Long id);

    SubjectResponse findOne(String name);

    SubjectResponse findOne(Long id, String name);

    List<SubjectResponse> findAll();

    SubjectResponse save(StudentRequest dto);

    void remove(Long id);
}
