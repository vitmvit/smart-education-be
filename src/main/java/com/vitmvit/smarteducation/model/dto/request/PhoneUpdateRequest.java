package com.vitmvit.smarteducation.model.dto.request;

import com.vitmvit.smarteducation.validate.ItsMyLogin;
import com.vitmvit.smarteducation.validate.LoginExists;
import com.vitmvit.smarteducation.validate.PhoneCheck;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record PhoneUpdateRequest(

        @NotEmpty
        @Email
        @LoginExists
        @ItsMyLogin
        String login,

        @NotEmpty
        @PhoneCheck
        String phoneNumber) {
}
