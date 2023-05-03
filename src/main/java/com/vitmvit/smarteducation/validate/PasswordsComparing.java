package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.PasswordsComparingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsComparingValidator.class)
public @interface PasswordsComparing {

    String message() default "The passwords must be equals";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
