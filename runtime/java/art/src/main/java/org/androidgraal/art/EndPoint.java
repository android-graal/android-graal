package org.androidgraal.art;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** On a static method the image calls through JNI by name and descriptor */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface EndPoint {
}
