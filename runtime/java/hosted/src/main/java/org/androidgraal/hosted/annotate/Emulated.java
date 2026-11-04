package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A class the image provides under a name of Android's class library, the framework ({@code android.*}), libcore,
 * dalvik and the internal packages alike. It goes on the outermost class and covers its nested classes.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Emulated {
}
