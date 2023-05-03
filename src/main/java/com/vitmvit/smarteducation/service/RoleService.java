package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.entity.Role;

import java.util.List;

public interface RoleService {

    Role findOne(Long id);

    Role findOne(String name);

    List<Role> findAll();

    void save(Role role);
}
