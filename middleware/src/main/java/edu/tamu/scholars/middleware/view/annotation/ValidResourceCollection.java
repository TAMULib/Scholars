package edu.tamu.scholars.middleware.view.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.tamu.scholars.middleware.view.validator.ResourceCollectionValidator;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ResourceCollectionValidator.class)
public @interface ValidResourceCollection {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
