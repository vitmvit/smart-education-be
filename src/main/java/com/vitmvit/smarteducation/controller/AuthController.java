package com.vitmvit.smarteducation.controller;

import com.vitmvit.smarteducation.model.dto.auth.SignInDto;
import com.vitmvit.smarteducation.model.dto.auth.SignUpDto;
import com.vitmvit.smarteducation.model.dto.auth.TokenDto;
import com.vitmvit.smarteducation.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/open")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> registration(@Valid @RequestBody SignUpDto dto) {
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.OK);
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@Valid @RequestBody SignInDto dto) {
        return new ResponseEntity<>(authService.signIn(dto), HttpStatus.OK);
    }
}
