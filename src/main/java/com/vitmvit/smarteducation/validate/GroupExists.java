package com.vitmvit.smarteducation.validate;

import com.vitmvit.smarteducation.validate.validator.GroupExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupExistsValidator.class)
public @interface GroupExists {

    String message() default "This group already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
