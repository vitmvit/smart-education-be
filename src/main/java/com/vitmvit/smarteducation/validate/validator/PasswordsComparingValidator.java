package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.model.dto.auth.PasswordDto;
import com.vitmvit.smarteducation.util.StringUtils;
import com.vitmvit.smarteducation.validate.PasswordsComparing;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
public class PasswordsComparingValidator implements ConstraintValidator<PasswordsComparing, PasswordDto> {

    @Override
    public boolean isValid(PasswordDto dto, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotEmpty(dto.getPassword()) && dto.getPassword().equals(dto.getPasswordConfirm());
    }
}