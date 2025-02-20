package com.alabtaal.thecomputershop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Email(message = "Please provide a valid email address")
@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface ExtendedEmailValidator {

    String message() default "Please provide a valid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
