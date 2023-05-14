package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.request.GroupRequest;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {

    boolean exists(String name);

    GroupResponse findOne(Long id);

    GroupResponse findOne(String name);

    GroupResponse findOne(Long id, String name, Long userId);

    List<GroupResponse> findAllActive();

    List<GroupResponse> findAllInActive();

    void numberUp(Long id);

    void numberDown(Long id);

    GroupResponse save(GroupRequest dto);

    GroupResponse update(GroupRequest dto);

    void remove(Long id);
}
