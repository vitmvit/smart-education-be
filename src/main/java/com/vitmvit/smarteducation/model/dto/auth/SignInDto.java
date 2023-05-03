package com.vitmvit.smarteducation.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInDto {

    @NotNull
    @NotBlank
    @Email
    private String login;

    @NotNull
    @NotBlank
    private String password;
}
