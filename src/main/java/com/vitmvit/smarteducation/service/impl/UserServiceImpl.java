package com.vitmvit.smarteducation.service.impl;

import com.vitmvit.smarteducation.constant.RoleEnum;
import com.vitmvit.smarteducation.model.entity.Role;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.repository.RoleRepository;
import com.vitmvit.smarteducation.repository.UserRepository;
import com.vitmvit.smarteducation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new EntityNotFoundException("User not found by login: " + login)
        );
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new EntityNotFoundException("User not found by login: " + login);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // если роли не определены, то по умолчанию назначается роль USER
            if (user.getRoleList() == null || user.getRoleList().size() == 0) {
                Role role = roleRepository.findByName(RoleEnum.USER.getName()).orElseThrow(
                        () -> new EntityNotFoundException("Role not found by name: " + RoleEnum.USER)
                );
                user.setRoleList(List.of(role));
            }
            return userRepository.save(user);
        } else {
            User target = findByLogin(user.getLogin());
            target.setName(user.getName());
            target.setLastName(user.getLastName());
            target.setMiddleName(user.getMiddleName());
            // пароль при обновлении сущности остается прежним see PasswordChangeDto
            //target.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(target);
        }
    }

    @Override
    public User addRole(String login, RoleEnum roleEnum) {
        User user = findByLogin(login);
        Set<Role> roleSet = new HashSet<>(user.getRoleList());
        if (roleSet.contains(new Role(RoleEnum.USER.getName()))) {
            delRole(login, RoleEnum.USER);
        }
        roleSet.add(roleRepository.findByName(roleEnum.getName()).orElseThrow(
                () -> new EntityNotFoundException("Role not found by name: " + roleEnum.getName())
        ));
        user.setRoleList(new ArrayList<>(roleSet));
        return userRepository.save(user);
    }

    @Override
    public User delRole(String login, RoleEnum roleEnum) {
        User user = findByLogin(login);
        Set<Role> roleSet = new HashSet<>(user.getRoleList());
        roleSet.remove(roleRepository.findByName(roleEnum.getName()).orElseThrow(
                () -> new EntityNotFoundException("Role not found by name: " + roleEnum.getName())
        ));
        user.setRoleList(new ArrayList<>(roleSet));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new EntityNotFoundException("User not found by login: " + login)
        );
    }
}
