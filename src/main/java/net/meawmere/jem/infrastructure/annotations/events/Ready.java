package net.meawmere.jem.infrastructure.annotations.events;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface Ready {
}
