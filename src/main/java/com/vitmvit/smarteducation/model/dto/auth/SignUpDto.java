package com.vitmvit.smarteducation.model.dto.auth;

import com.vitmvit.smarteducation.validate.LoginExists;
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
public class SignUpDto {

    private String name;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private Integer studentNumber;

    @NotNull
    @NotBlank
    @Email
    @LoginExists
    private String login;

    @Valid
    @NotNull
    @PasswordsComparing
    private PasswordDto passwords;
}
