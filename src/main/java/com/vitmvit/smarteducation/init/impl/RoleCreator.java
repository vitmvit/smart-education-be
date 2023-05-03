package com.vitmvit.smarteducation.init.impl;

import com.vitmvit.smarteducation.init.CommandLine;
import com.vitmvit.smarteducation.model.entity.Role;
import com.vitmvit.smarteducation.model.entity.parent.IdNameEntity;
import com.vitmvit.smarteducation.service.RoleService;
import com.vitmvit.smarteducation.util.RoleEnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://www.baeldung.com/spring-qualifier-annotation"
 */
@Slf4j
@Component
@Qualifier("roleCreator")
public class RoleCreator implements CommandLine {

    @Autowired
    private RoleService roleService;

    @Override
    public void command() {
        createRoles();
    }

    /**
     * создаем недостающие роли если нет
     */
    private void createRoles() {
        List<String> enumList = new ArrayList<>(Arrays.asList(RoleEnumUtils.allRoles()));
        enumList.removeAll(roleService.findAll().stream().map(IdNameEntity::getName).toList());
        if (enumList.size() > 0) {
            enumList.forEach(name -> roleService.save(new Role(name)));
        }
    }
}
