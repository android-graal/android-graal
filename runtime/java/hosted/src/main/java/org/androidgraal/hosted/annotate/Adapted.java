package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A declaration of a class copied from the SDK sources that is modified from Android's. On a class it covers
 * the header, which is the class Javadoc, the annotations, the modifiers, the type parameters and the {@code extends},
 * {@code implements} and {@code permits} clauses, and each member carries its own. A declaration whose body is also
 * Android's code from outside the SDK's Java text, such as an older level's layout, carries {@link Transcribed} too.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface Adapted {
    /**
     * What the declaration does differently from Android.
     */
    String reason();

    /**
     * The premise the difference is caused by.
     */
    Premise[] premise();
}
