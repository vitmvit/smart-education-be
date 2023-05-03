package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.request.SubjectRequest;
import com.vitmvit.smarteducation.model.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {

    SubjectResponse findOne(Long id, String name);

    List<SubjectResponse> findAll();

    SubjectResponse save(SubjectRequest dto);

    void remove(Long id);
}
