package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.UuidCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see "https://www.baeldung.com/spring-boot-bean-validation"
 * @see "https://www.baeldung.com/spring-mvc-custom-validator"
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidCheckValidator.class)
public @interface UuidCheck {

    String message() default "The uuid is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
