package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.request.FacultyRequest;
import com.vitmvit.smarteducation.model.dto.response.FacultyResponse;
import com.vitmvit.smarteducation.service.CathedraService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CathedraController {

    private final CathedraService cathedraService;

    // admin

    @PostMapping
    public ResponseEntity<FacultyResponse> save(@RequestBody FacultyRequest dto) {
        return new ResponseEntity<>(cathedraService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public void remove(@RequestParam Long id) {
        cathedraService.remove(id);
    }

    // auth

    @GetMapping("/auth/faculties/one")
    public ResponseEntity<FacultyResponse> findOne(@RequestParam Long id,
                                                   @RequestParam String name) {
        return new ResponseEntity<>(cathedraService.findOne(id, name), HttpStatus.OK);
    }

    @GetMapping("/auth/faculties/all")
    public ResponseEntity<List<FacultyResponse>> findAll() {
        return new ResponseEntity<>(cathedraService.findAll(), HttpStatus.OK);
    }
}
