package com.vitmvit.smarteducation.model.dto.request;

import com.vitmvit.smarteducation.validate.ItsMyLogin;
import com.vitmvit.smarteducation.validate.LoginExists;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record NameUpdateRequest(

        @NotEmpty
        @Email
        @LoginExists
        @ItsMyLogin
        String login,

        @NotEmpty
        String name,

        @NotEmpty
        String lastName,

        String middleName) {
}
