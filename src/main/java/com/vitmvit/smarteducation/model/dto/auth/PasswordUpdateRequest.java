package com.vitmvit.smarteducation.model.dto.auth;

import com.vitmvit.smarteducation.validate.ItsMyLogin;
import com.vitmvit.smarteducation.validate.LoginExists;
import com.vitmvit.smarteducation.validate.OldPasswordCheck;
import com.vitmvit.smarteducation.validate.PasswordsComparing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@OldPasswordCheck
public class PasswordUpdateRequest {

    @NotNull
    @NotBlank
    @Email
    @LoginExists
    @ItsMyLogin
    private String login;

    // null or blank state is valid
    private String oldPassword;

    @Valid
    @NotNull
    @PasswordsComparing
    private PasswordDto passwords;
}
