package com.vitmvit.smarteducation.validate.validator;

import com.vitmvit.smarteducation.constant.RoleEnum;
import com.vitmvit.smarteducation.model.dto.auth.PasswordUpdateRequest;
import com.vitmvit.smarteducation.model.entity.Role;
import com.vitmvit.smarteducation.model.entity.User;
import com.vitmvit.smarteducation.service.UserService;
import com.vitmvit.smarteducation.validate.OldPasswordCheck;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@AllArgsConstructor
public class OldPasswordCheckValidator implements ConstraintValidator<OldPasswordCheck, PasswordUpdateRequest> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(PasswordUpdateRequest dto, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        for (Role role : user.getRoleList()) {
            if (role.getName().equals(RoleEnum.ROOT.getName()) || role.getName().equals(RoleEnum.ADMIN.getName())) {
                return true;
            }
        }
        return userService.existsByLogin(dto.getLogin())
                && passwordEncoder.matches(dto.getOldPassword(), userService.findByLogin(dto.getLogin()).getPassword());
    }
}
