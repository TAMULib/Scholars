package edu.tamu.scholars.middleware.harvest.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface Source {

    String key();

    Property[] properties() default {};

    Sparql[] sparql() default {};

    @Target({ TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface Sparql {

        String template();

        Property[] properties() default {};

    }

    @Target({ TYPE })
    @Retention(RUNTIME)
    @Documented
    public @interface Property {

        String name();

        String key();

        String id() default "";
        
        String schema() default "";

        boolean parse() default false;

    }

}
