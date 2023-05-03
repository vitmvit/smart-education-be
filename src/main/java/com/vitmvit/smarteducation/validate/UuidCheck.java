package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.UuidCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UuidCheckValidator.class)
public @interface UuidCheck {

    String message() default "The uuid is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
