package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

//    @GetMapping("/")
//    public ResponseEntity<Boolean> exists(@RequestParam String name) {
//        return new ResponseEntity<>(groupService.exists(name), HttpStatus.OK);
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<GroupResponse> exists(@RequestParam(name = "id", required = false) Long id,
//                                                @RequestParam(name = "name", required = false) String name) {
//        return new ResponseEntity<>(groupService.findOne(id, name), HttpStatus.OK);
//    }
}
