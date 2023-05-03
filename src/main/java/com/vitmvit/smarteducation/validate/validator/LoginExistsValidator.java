package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.repository.UserRepository;
import com.vitmvit.smarteducation.validate.LoginExists;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@AllArgsConstructor
public class LoginExistsValidator implements ConstraintValidator<LoginExists, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsByLogin(login);
    }
}
