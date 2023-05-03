package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.constant.RoleEnum;
import com.vitmvit.smarteducation.model.entity.User;

public interface UserService {

    boolean existsByLogin(String login);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    User save(User user);

    User addRole(String login, RoleEnum roleEnum);

    User delRole(String login, RoleEnum roleEnum);
}
