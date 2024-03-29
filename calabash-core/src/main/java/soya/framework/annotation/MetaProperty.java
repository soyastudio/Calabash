package soya.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MetaProperty {
    String key();

    String value();

    String defaultValue() default "";

}
