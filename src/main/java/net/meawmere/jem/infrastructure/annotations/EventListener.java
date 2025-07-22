package net.meawmere.jem.infrastructure.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
public @interface EventListener {
    String group() default "__default__";
}
