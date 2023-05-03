package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.model.entity.Role;
import com.vitmvit.smarteducation.repository.RoleRepository;
import com.vitmvit.smarteducation.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Service
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findOne(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("")
        );
    }

    @Override
    public Role findOne(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("")
        );
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
