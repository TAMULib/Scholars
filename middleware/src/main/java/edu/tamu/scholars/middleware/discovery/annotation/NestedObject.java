package edu.tamu.scholars.middleware.discovery.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target(FIELD)
@Retention(RUNTIME)
public @interface NestedObject {

    Reference[] value();

    @Documented
    @Target(FIELD)
    @Retention(RUNTIME)
    public @interface Reference {

        String value();

        String key();

    }

}
