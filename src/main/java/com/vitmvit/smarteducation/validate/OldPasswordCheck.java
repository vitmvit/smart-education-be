package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.OldPasswordCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldPasswordCheckValidator.class)
public @interface OldPasswordCheck {

    String message() default "Old password is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
