package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.config.constants.RoleEnum;
import com.vitmvit.smarteducation.model.entity.User;

import java.util.List;

public interface UserService {

    boolean existsByLogin(String login);

    User findById(Long id);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    List<User> findAllTeachers();

    List<User> findAllByGroup(Long groupId);

    List<User> findAllByGroup(String groupName);

    User save(User model);

    User addRole(String login, RoleEnum roleEnum);

    User delRole(String login, RoleEnum roleEnum);
}
