package com.vitmvit.smarteducation.facade;

import com.vitmvit.smarteducation.model.dto.auth.PasswordUpdateRequest;
import com.vitmvit.smarteducation.model.dto.request.*;
import com.vitmvit.smarteducation.model.dto.response.RoleResponse;
import com.vitmvit.smarteducation.model.dto.response.UserResponse;

import java.util.List;

public interface UserFacade {

    //------------------------------------------------------------------------------------------------------------------

    boolean existsByLogin(String login);

    UserResponse me();

    UserResponse findByLoginAndPassword(String login, String password);

    //------------------------------------------------------------------------------------------------------------------

    // пагинация

    //------------------------------------------------------------------------------------------------------------------

    UserResponse createRoot(RootRequest dto);

    UserResponse createAdmin(AdminRequest dto);

    UserResponse createSupervisor(SupervisorRequest dto);

    UserResponse createTeacher(TeacherRequest dto);

    UserResponse createStudent(StudentRequest dto);

    //------------------------------------------------------------------------------------------------------------------

    UserResponse updateAvatar(AvatarUpdateRequest dto);

    UserResponse updateName(NameUpdateRequest dto);

    UserResponse updatePhone(PhoneUpdateRequest dto);

    UserResponse updatePassword(PasswordUpdateRequest dto);

    UserResponse setStudentRole(String login);

    UserResponse addRole(RoleRequest dto);

    UserResponse delRole(RoleRequest dto);

    //------------------------------------------------------------------------------------------------------------------

    RoleResponse findRole(Long id, String name);

    List<RoleResponse> findAllRoles();

    //------------------------------------------------------------------------------------------------------------------
}
