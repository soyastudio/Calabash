package soya.framework.domain;

import soya.framework.annotation.MetaProperty;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UDT {
    String name();

    Class<?> javaType();

    String defaultValue();

    MetaProperty[] properties() default {};

}
