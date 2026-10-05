package org.androidgraal.hosted.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A primitive array or {@code String}, as a parameter or a result, that crosses in a block the calling thread keeps
 * instead of a fresh copy per call. An array has exactly {@link #capacity} elements, a string at most that many chars.
 * The sender throws an {@link IllegalArgumentException} otherwise. The receiver reads into an array it reuses per
 * thread, never cleared, so an {@link Out} parameter arrives holding what the last call on that thread left there. The
 * block and the reused array are one per thread.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface StaticBuffer {
    /**
     * The elements or chars the position holds, at least 1. Primitive arrays must match exact, for a String it's an
     * upper bound.
     */
    int capacity();
}
