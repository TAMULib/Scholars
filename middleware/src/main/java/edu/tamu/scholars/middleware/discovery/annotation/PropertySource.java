package edu.tamu.scholars.middleware.discovery.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface PropertySource {

    String template();

    String predicate();

    // NOTE: extracts identifier from url, after last / or #
    boolean parse() default false;

    boolean unique() default false;

}
