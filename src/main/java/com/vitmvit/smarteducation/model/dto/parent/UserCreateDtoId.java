package com.vitmvit.smarteducation.model.dto.parent;

import com.vitmvit.smarteducation.model.dto.auth.PasswordDto;
import com.vitmvit.smarteducation.validate.LoginNotExists;
import com.vitmvit.smarteducation.validate.PasswordsComparing;
import com.vitmvit.smarteducation.validate.PhoneCheck;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class UserCreateDtoId extends IdNameDto {

    @NotEmpty
    @Email
    @LoginNotExists
    private String login;

    @Valid
    @NotNull
    @PasswordsComparing
    private PasswordDto passwords;

    @NotEmpty
    private String lastName;

    private String middleName;

    @NotEmpty
    @PhoneCheck
    private String phone;
}
