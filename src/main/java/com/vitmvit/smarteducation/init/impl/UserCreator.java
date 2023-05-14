package com.vitmvit.smarteducation.init.impl;

import com.vitmvit.smarteducation.init.CommandLine;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.service.RoleService;
import com.vitmvit.smarteducation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.vitmvit.smarteducation.config.constants.Constants.ROLE_PREFIX;

/**
 * @see "https://www.baeldung.com/spring-qualifier-annotation"
 */
@Slf4j
@Component
@Qualifier("userCreator")
public class UserCreator implements CommandLine {

    private static final List<String> ADM_LOGIN_LIST = List.of("root@localhost", "admin@localhost");

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void command() {
        createUsers();
    }

    /**
     * создаем пользователей ROOT и ADMIN если нет
     */
    private void createUsers() {
        StringBuilder loginPrefix = new StringBuilder();
        for (String login : ADM_LOGIN_LIST) {
            if (!userService.existsByLogin(login)) {
                loginPrefix.setLength(0);
                loginPrefix.append(login);
                userService.save(createUser(login, loginPrefix.substring(0, loginPrefix.indexOf("@"))));
            }
        }
    }

    private User createUser(String login, String loginPrefix) {
        User user = new User();
        user.setAvatarUuid("none");
        user.setLogin(login);
        user.setPassword(loginPrefix);
        user.setRoleList(List.of(roleService.findOne(ROLE_PREFIX + loginPrefix.toUpperCase())));
        user.setName(loginPrefix);
        user.setLastName(loginPrefix);
        user.setMiddleName(loginPrefix);
        user.setPhoneNumber("");
        user.setActive(1);
        user.setDescription("");
        return user;
    }
}
