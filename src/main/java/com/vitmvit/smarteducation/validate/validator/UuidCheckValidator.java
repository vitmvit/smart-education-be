package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.validate.UuidCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidCheckValidator implements ConstraintValidator<UuidCheck, String> {

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}