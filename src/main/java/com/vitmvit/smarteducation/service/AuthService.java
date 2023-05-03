package com.vitmvit.smarteducation.service;

import com.vitmvit.smarteducation.model.dto.auth.SignInDto;
import com.vitmvit.smarteducation.model.dto.auth.SignUpDto;
import com.vitmvit.smarteducation.model.dto.auth.TokenDto;

public interface AuthService {

    TokenDto signUp(SignUpDto signUpDto);

    TokenDto signIn(SignInDto signInDto);
}
