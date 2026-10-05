package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A primitive array parameter the callee only writes and must not keep, copied back whole. It arrives as a fresh
 * zero-filled array per call unless it is a {@link StaticBuffer}.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface Out {
}
