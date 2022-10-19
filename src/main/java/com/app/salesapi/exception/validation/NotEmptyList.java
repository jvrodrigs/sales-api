package com.app.salesapi.exception.validation;

import com.app.salesapi.exception.validation.constraintValidation.NotEmptyListValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidation.class)
public @interface NotEmptyList {
    String message() default "The list cannot be empty.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
