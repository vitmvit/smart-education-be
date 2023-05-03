package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {

    boolean exists(String name);

    GroupResponse findOne(Long id, String name);

    List<GroupResponse> findAllActive();

    List<GroupResponse> findAllInActive();

    void numberUp(Long id);

    void numberDown(Long id);

    void remove(Long id);
}
