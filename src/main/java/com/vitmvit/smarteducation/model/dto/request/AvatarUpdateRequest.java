package com.vitmvit.smarteducation.model.dto.request;

import com.vitmvit.smarteducation.validate.ItsMyLogin;
import com.vitmvit.smarteducation.validate.LoginExists;
import com.vitmvit.smarteducation.validate.UuidCheck;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public record AvatarUpdateRequest(

        @NotEmpty
        @Email
        @LoginExists
        @ItsMyLogin
        String login,

        @NotEmpty
        @UuidCheck
        String avatarUuid) {
}
