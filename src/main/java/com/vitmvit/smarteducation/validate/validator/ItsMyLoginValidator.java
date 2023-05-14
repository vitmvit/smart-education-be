package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.config.constants.RoleEnum;
import com.vitmvit.smarteducation.repository.UserRepository;
import com.vitmvit.smarteducation.validate.ItsMyLogin;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@AllArgsConstructor
public class ItsMyLoginValidator implements ConstraintValidator<ItsMyLogin, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        // если операцию совершает администратор системы, то мы позволяем ему это безоговорочно
        if (RoleEnum.ROOT.getName().equals(currentLogin) || RoleEnum.ADMIN.getName().equals(currentLogin)) {
            return true;
        }
        // если операцию совершает не администратор системы, то проверяем,
        // чтобы логин залогинившигося человека совпадал с тем, в адрес которого совершается операция
        // т.е. только самому себе, но не чужому лицу
        return userRepository.findByLogin(login).map(user -> user.getLogin().equals(currentLogin)).orElse(false);
    }
}
