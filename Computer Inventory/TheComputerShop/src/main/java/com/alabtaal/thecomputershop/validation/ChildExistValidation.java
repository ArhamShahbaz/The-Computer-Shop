package com.alabtaal.thecomputershop.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = ChildExistValidator.class)
public @interface ChildExistValidation {

    String message() default "Child.Exist.Validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
