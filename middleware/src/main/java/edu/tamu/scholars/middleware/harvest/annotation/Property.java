package edu.tamu.scholars.middleware.harvest.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface Property {

    String name();

    String key();

    String id() default "";

    // NOTE: extracts identifier from url, after last / or #
    boolean parse() default false;

    boolean unique() default false;

}