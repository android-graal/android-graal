package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A declaration of a class copied from the SDK sources whose content differs from the SDK source's. On a class it
 * covers the header, which is the class Javadoc, the annotations, the modifiers, the type parameters and the
 * {@code extends}, {@code implements} and {@code permits} clauses, and each member carries its own. On a class the SDK
 * source lacks it covers the whole class.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface Adapted {
    /**
     * The files outside the SDK sources the body is transcribed from, each the gitiles URL of the file at the
     * platform's tag, or at an older level's {@code android-*} tag for a body transcribing that level's code, with the
     * lines transcribed:
     * {@code https://android.googlesource.com/platform/<project>/+/refs/tags/<tag>/<path>#L<from>-L<to>}. Empty for an
     * edit of the SDK source's own text.
     */
    String[] value() default {};

    /**
     * What the declaration does differently from the SDK source.
     */
    String reason();
}
