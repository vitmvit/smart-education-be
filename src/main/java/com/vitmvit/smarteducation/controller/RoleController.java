package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.facade.UserFacade;
import com.vitmvit.smarteducation.model.dto.response.RoleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin/roles")
public class RoleController {

    private final UserFacade userFacade;

    @GetMapping("/one")
    public ResponseEntity<RoleResponse> findOne(@RequestParam(name = "id", required = false) Long id,
                                                @RequestParam(name = "name", required = false) String name) {
        return new ResponseEntity<>(userFacade.findRole(id, name), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponse>> findAll() {
        return new ResponseEntity<>(userFacade.findAllRoles(), HttpStatus.OK);
    }
}
