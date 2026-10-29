package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A declaration of a class copied from the SDK sources whose body carries Android's code with Android's behaviour,
 * either code from outside the SDK's carried into Java (a JNI function, a helper of libutils, libbase or
 * libnativehelper, a bionic constant's value, an older level's layout) or the SDK source's own text over an equivalent
 * callee.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
public @interface Transcribed {
    /**
     * The lines transcribed, each the gitiles URL of the file at the platform's tag, or at an older level's
     * {@code android-*} tag for a body transcribing that level's code, with the lines:
     * {@code https://android.googlesource.com/platform/<project>/+/refs/tags/<tag>/<path>#L<from>-L<to>}.
     */
    String[] value() default {};
}
