package com.vitmvit.smarteducation.model.dto.request;

import com.vitmvit.smarteducation.config.constants.RoleEnum;
import com.vitmvit.smarteducation.validate.ItsMyLogin;
import com.vitmvit.smarteducation.validate.LoginExists;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record RoleRequest(

        @NotEmpty
        @Email
        @LoginExists
        @ItsMyLogin
        String login,

        @NotEmpty
        RoleEnum roleEnum) {
}
