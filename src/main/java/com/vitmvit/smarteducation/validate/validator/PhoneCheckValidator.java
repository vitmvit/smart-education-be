package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.util.StringUtils;
import com.vitmvit.smarteducation.validate.PhoneCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
public class PhoneCheckValidator implements ConstraintValidator<PhoneCheck, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        phone = phone.replaceAll("[0-9]", "");
        // todo: уточнить проверки
        return StringUtils.isNotEmpty(phone) && phone.length() == 12;
    }
}