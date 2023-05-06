package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.facade.UserFacade;
import com.vitmvit.smarteducation.model.dto.auth.PasswordUpdateRequest;
import com.vitmvit.smarteducation.model.dto.request.*;
import com.vitmvit.smarteducation.model.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserFacade userFacade;

    // root

    @PostMapping("/root/users/create/root")
    public ResponseEntity<UserResponse> createRoot(@RequestBody RootRequest dto) {
        return new ResponseEntity<>(userFacade.createRoot(dto), HttpStatus.OK);
    }

    // admin

    @PostMapping("/admin/users/create/admin")
    public ResponseEntity<UserResponse> createAdmin(@RequestBody AdminRequest dto) {
        return new ResponseEntity<>(userFacade.createAdmin(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/users/create/supervisor")
    public ResponseEntity<UserResponse> createSupervisor(@RequestBody SupervisorRequest dto) {
        return new ResponseEntity<>(userFacade.createSupervisor(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/users/create/teacher")
    public ResponseEntity<UserResponse> createTeacher(@RequestBody TeacherRequest dto) {
        return new ResponseEntity<>(userFacade.createTeacher(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/users/setStudentRole")
    public ResponseEntity<UserResponse> setStudentRole(@RequestParam String login) {
        return new ResponseEntity<>(userFacade.setStudentRole(login), HttpStatus.OK);
    }

    @PostMapping("/admin/users/addRole")
    public ResponseEntity<UserResponse> addRole(@RequestBody RoleRequest dto) {
        return new ResponseEntity<>(userFacade.addRole(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/users/delRole")
    public ResponseEntity<UserResponse> delRole(@RequestBody RoleRequest dto) {
        return new ResponseEntity<>(userFacade.delRole(dto), HttpStatus.OK);
    }

    // auth

    @PostMapping("/auth/users/update/avatar")
    public ResponseEntity<UserResponse> updateAvatar(@RequestBody AvatarUpdateRequest dto) {
        return new ResponseEntity<>(userFacade.updateAvatar(dto), HttpStatus.OK);
    }

    // user

    @GetMapping("/user/users/me")
    public ResponseEntity<UserResponse> me() {
        return new ResponseEntity<>(userFacade.me(), HttpStatus.OK);
    }

    @PostMapping("/user/users/update/name")
    public ResponseEntity<UserResponse> updateName(@RequestBody NameUpdateRequest dto) {
        return new ResponseEntity<>(userFacade.updateName(dto), HttpStatus.OK);
    }

    @PostMapping("/user/users/update/phone")
    public ResponseEntity<UserResponse> updatePhone(@RequestBody PhoneUpdateRequest dto) {
        return new ResponseEntity<>(userFacade.updatePhone(dto), HttpStatus.OK);
    }

    @PostMapping("/user/users/update/password")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody PasswordUpdateRequest dto) {
        return new ResponseEntity<>(userFacade.updatePassword(dto), HttpStatus.OK);
    }

    // open

//    @GetMapping("/open/users/exists")
//    public ResponseEntity<Boolean> existsByLogin(@RequestParam String login) {
//        return new ResponseEntity<>(userFacade.existsByLogin(login), HttpStatus.OK);
//    }

//    @GetMapping("/auth/users/by")
//    public ResponseEntity<UserResponse> find(@RequestParam(name = "login") String login,
//                                             @RequestParam(name = "password", required = false) String password) {
//        return new ResponseEntity<>(userFacade.findByLoginAndPassword(login, password), HttpStatus.OK);
//    }
}