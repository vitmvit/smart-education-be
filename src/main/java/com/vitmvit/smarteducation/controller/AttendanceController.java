package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.facade.AttendanceFacade;
import com.vitmvit.smarteducation.model.dto.page.PageContentResponse;
import com.vitmvit.smarteducation.model.dto.request.AttendanceGroupRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceRequest;
import com.vitmvit.smarteducation.model.dto.request.AttendanceSearch;
import com.vitmvit.smarteducation.model.dto.response.AttendanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth/attendances")
public class AttendanceController {

    private final AttendanceFacade attendanceFacade;

    @GetMapping
    public ResponseEntity<AttendanceResponse> findOne(@RequestParam Long id) {
        return new ResponseEntity<>(attendanceFacade.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PageContentResponse<AttendanceResponse>> findOne(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                                                           @RequestParam(name = "sortField", required = false, defaultValue = "date") String sortField,
                                                                           @Valid @RequestBody AttendanceSearch dto) {
        return new ResponseEntity<>(attendanceFacade.findAll(pageNumber, sortField, dto), HttpStatus.OK);
    }

    @PostMapping(path = "/save/all")
    public ResponseEntity<AttendanceResponse> save(@Valid @RequestBody List<AttendanceRequest> list) {
        return new ResponseEntity<>(attendanceFacade.save(list), HttpStatus.OK);
    }

    @PostMapping(path = "/save/one")
    public ResponseEntity<AttendanceResponse> save(@Valid @RequestBody AttendanceGroupRequest dto) {
        return new ResponseEntity<>(attendanceFacade.save(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public void remove(@RequestParam Long id) {
        attendanceFacade.remove(id);
    }
}
