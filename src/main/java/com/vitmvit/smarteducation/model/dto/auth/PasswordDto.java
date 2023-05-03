package com.vitmvit.smarteducation.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordDto {

    @NotNull
    @NotBlank
    @Length(min = 8, message = "The 'password' must be greater than or equal to 8")
    private String password;

    private String passwordConfirm;
}
