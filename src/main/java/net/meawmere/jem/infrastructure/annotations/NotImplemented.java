package net.meawmere.jem.infrastructure.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The elements marked with this annotation are currently under development and are not being executed. You can use them for automatic use in future versions, but we do not guarantee that their operation will not be changed.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NotImplemented {
}
