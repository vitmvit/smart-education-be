package com.vitmvit.smarteducation.facade.impl;

import com.vitmvit.smarteducation.constant.RoleEnum;
import com.vitmvit.smarteducation.converter.RoleConverter;
import com.vitmvit.smarteducation.converter.UserConverter;
import com.vitmvit.smarteducation.facade.UserFacade;
import com.vitmvit.smarteducation.model.dto.auth.PasswordUpdateRequest;
import com.vitmvit.smarteducation.model.dto.parent.UserCreateDtoId;
import com.vitmvit.smarteducation.model.dto.request.*;
import com.vitmvit.smarteducation.model.dto.response.RoleResponse;
import com.vitmvit.smarteducation.model.dto.response.UserResponse;
import com.vitmvit.smarteducation.model.entity.CabinetTeacher;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.service.CabinetTeacherService;
import com.vitmvit.smarteducation.service.RoleService;
import com.vitmvit.smarteducation.service.UserService;
import com.vitmvit.smarteducation.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Component
public class UserFacadeImpl implements UserFacade {

    //------------------------------------------------------------------------------------------------------------------

    private final UserService userService;
    private final RoleService roleService;
    private final UserConverter userConverter;
    private final RoleConverter roleConverter;
    private final CabinetTeacherService cabinetTeacherService;
    private final PasswordEncoder passwordEncoder;

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean existsByLogin(String login) {
        return userService.existsByLogin(login);
    }

    @Override
    public UserResponse me() {
        return userConverter.convert(userService.findByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName())
        );
    }

    @Override
    public UserResponse findByLoginAndPassword(String login, String password) {
        if (password == null) {
            return userConverter.convert(userService.findByLogin(login));
        }
        return userConverter.convert(userService.findByLoginAndPassword(login, password));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public UserResponse createRoot(RootRequest dto) {
        User user = update(dto);
        user.setRoleList(List.of(roleService.findOne(RoleEnum.ROOT.getName())));
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse createAdmin(AdminRequest dto) {
        User user = update(dto);
        user.setRoleList(List.of(roleService.findOne(RoleEnum.ADMIN.getName())));
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse createSupervisor(SupervisorRequest dto) {
        User user = update(dto);
        user.setRoleList(List.of(roleService.findOne(RoleEnum.SUPERVISOR.getName())));
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse createTeacher(TeacherRequest dto) {
        User user = update(dto);
        user.setRoleList(List.of(roleService.findOne(RoleEnum.TEACHER.getName())));
        user.setCabinetTeacher(cabinetTeacherService.save(new CabinetTeacher()));
        return userConverter.convert(userService.save(user));
    }

    /**
     * @see "com.vitmvit.smarteducation.service.AuthServicee"
     */
    @Override
    public UserResponse createStudent(StudentRequest dto) {
        throw new RuntimeException("Already implemented");
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public UserResponse updateAvatar(AvatarUpdateRequest dto) {
        User user = userService.findByLogin(dto.login());
        user.setAvatarUuid(StringUtils.isEmpty(dto.avatarUuid()) ? "none" : dto.avatarUuid());
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse updateName(NameUpdateRequest dto) {
        User user = userService.findByLogin(dto.login());
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        user.setMiddleName(dto.middleName());
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse updatePhone(PhoneUpdateRequest dto) {
        User user = userService.findByLogin(dto.login());
        user.setPhoneNumber(dto.phoneNumber());
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse updatePassword(PasswordUpdateRequest dto) {
        User user = userService.findByLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPasswords().getPassword()));
        return userConverter.convert(userService.save(user));
    }

    @Override
    public UserResponse setStudentRole(String login) {
        User user = userService.findByLogin(login);
        if (user.getRoleList().size() == 1 & RoleEnum.USER.getName().equals(user.getRoleList().get(0).getName())) {
            user.setRoleList(List.of(roleService.findOne(RoleEnum.STUDENT.getName())));
            user.setActive(1);
            user = userService.save(user);
        }
        return userConverter.convert(user);
    }

    @Override
    public UserResponse addRole(RoleRequest dto) {
        return userConverter.convert(userService.addRole(dto.login(), dto.roleEnum()));
    }

    @Override
    public UserResponse delRole(RoleRequest dto) {
        return userConverter.convert(userService.delRole(dto.login(), dto.roleEnum()));
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public RoleResponse findRole(Long id, String name) {
        if (id != null) {
            return roleConverter.convert(roleService.findOne(id));
        } else if (name != null) {
            return roleConverter.convert(roleService.findOne(name));
        }
        throw new EntityNotFoundException("Role not found");
    }

    @Override
    public List<RoleResponse> findAllRoles() {
        return roleConverter.convert(roleService.findAll());
    }

    //------------------------------------------------------------------------------------------------------------------

    private User update(UserCreateDtoId dto) {
        User user = new User();
        user.setAvatarUuid("none");
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPasswords().getPassword());
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setPhoneNumber(dto.getPhone());
        user.setActive(1);
        user.setDescription("");
        return user;
    }
}
