package com.alabtaal.thecomputershop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AdjustmentReasonValidator.class})

public @interface ValidateAdjustmentReason {

    String message() default "{invalid.adjustment.reason}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
