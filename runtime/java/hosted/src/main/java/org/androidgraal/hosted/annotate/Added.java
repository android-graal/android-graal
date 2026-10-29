package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A declaration in a class copied from the SDK sources that the SDK source does not have. Usually  a binding, a helper,
 * an entry point, or similar.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface Added {

    String reason();
}
