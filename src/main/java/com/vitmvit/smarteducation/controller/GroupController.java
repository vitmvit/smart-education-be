package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.request.GroupRequest;
import com.vitmvit.smarteducation.model.dto.response.GroupResponse;
import com.vitmvit.smarteducation.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/auth/groups/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String name) {
        return new ResponseEntity<>(groupService.exists(name), HttpStatus.OK);
    }

    @GetMapping("/auth/groups")
    public ResponseEntity<GroupResponse> findOne(@RequestParam(name = "id", required = false) Long id,
                                                 @RequestParam(name = "name", required = false) String name,
                                                 @RequestParam(name = "userId", required = false) Long userId) {
        return new ResponseEntity<>(groupService.findOne(id, name, userId), HttpStatus.OK);
    }

    @PostMapping("/admin/groups/save")
    public ResponseEntity<GroupResponse> create(@Valid @RequestBody GroupRequest dto) {
        return new ResponseEntity<>(groupService.save(dto), HttpStatus.OK);
    }
}
