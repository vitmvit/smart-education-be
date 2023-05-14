package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.request.StudentRequest;
import com.vitmvit.smarteducation.model.dto.response.SubjectResponse;
import com.vitmvit.smarteducation.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    // admin

    @PostMapping("/admin/subjects")
    public ResponseEntity<SubjectResponse> save(@RequestBody StudentRequest dto) {
        return new ResponseEntity<>(subjectService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping("/admin/subjects")
    public void remove(@RequestParam Long id) {
        subjectService.remove(id);
    }

    // auth

    @GetMapping("/auth/subjects")
    public ResponseEntity<SubjectResponse> findOne(@RequestParam(name = "id", required = false) Long id,
                                                   @RequestParam(name = "name", required = false) String name) {
        return new ResponseEntity<>(subjectService.findOne(id, name), HttpStatus.OK);
    }

    @GetMapping("/auth/subjects/all")
    public ResponseEntity<List<SubjectResponse>> findAll() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }
}
