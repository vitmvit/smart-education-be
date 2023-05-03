package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.LoginExistsValidator;

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
@Constraint(validatedBy = LoginExistsValidator.class)
public @interface LoginExists {

    String message() default "This login (email) already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
