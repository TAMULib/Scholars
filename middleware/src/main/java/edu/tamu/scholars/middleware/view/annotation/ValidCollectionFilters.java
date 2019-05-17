package edu.tamu.scholars.middleware.view.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.tamu.scholars.middleware.view.validator.CollectionFiltersValidator;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CollectionFiltersValidator.class)
public @interface ValidCollectionFilters {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
