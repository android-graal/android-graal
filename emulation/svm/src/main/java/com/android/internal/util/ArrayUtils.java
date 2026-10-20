/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.compat.annotation.UnsupportedAppUsage;
import android.os.Build;
import android.ravenwood.annotation.RavenwoodReplace;
import android.util.ArraySet;
import android.util.EmptyArray;

import dalvik.system.VMRuntime;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.IntFunction;

/**
 * Static utility methods for arrays that aren't already included in {@link java.util.Arrays}.
 * <p>
 * Test with:
 * <code>atest FrameworksUtilTests:com.android.internal.util.ArrayUtilsTest</code>
 * <code>atest FrameworksUtilTestsRavenwood:com.android.internal.util.ArrayUtilsTest</code>
 */
@android.ravenwood.annotation.RavenwoodKeepWholeClass
public class ArrayUtils {
    private static final int CACHE_SIZE = 73;
    private static Object[] sCache = new Object[CACHE_SIZE];

    private ArrayUtils() { /* cannot be instantiated */ }

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    public static int[] newUnpaddedIntArray(int minLen) {
        return (int[])VMRuntime.getRuntime().newUnpaddedArray(int.class, minLen);
    }

    public static boolean[] newUnpaddedBooleanArray(int minLen) {
        return (boolean[])VMRuntime.getRuntime().newUnpaddedArray(boolean.class, minLen);
    }

    public static Object[] newUnpaddedObjectArray(int minLen) {
        return (Object[])VMRuntime.getRuntime().newUnpaddedArray(Object.class, minLen);
    }

    @UnsupportedAppUsage(maxTargetSdk = Build.VERSION_CODES.R, trackingBug = 170729553)
    @SuppressWarnings("unchecked")
    public static <T> T[] newUnpaddedArray(Class<T> clazz, int minLen) {
        return (T[])VMRuntime.getRuntime().newUnpaddedArray(clazz, minLen);
    }

    /**
     * Returns an empty array of the specified type.  The intent is that
     * it will return the same empty array every time to avoid reallocation,
     * although this is not guaranteed.
     */
    @UnsupportedAppUsage
    @SuppressWarnings("unchecked")
    public static <T> T[] emptyArray(Class<T> kind) {
        if (kind == Object.class) {
            return (T[]) EmptyArray.OBJECT;
        }

        int bucket = (kind.hashCode() & 0x7FFFFFFF) % CACHE_SIZE;
        Object cache = sCache[bucket];

        if (cache == null || cache.getClass().getComponentType() != kind) {
            cache = Array.newInstance(kind, 0);
            sCache[bucket] = cache;

            // Log.e("cache", "new empty " + kind.getName() + " at " + bucket);
        }

        return (T[]) cache;
    }

    /**
     * Length of the given array or 0 if it's null.
     */
    public static int size(@Nullable Object[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * Throws {@link ArrayIndexOutOfBoundsException} if the range is out of bounds.
     * @param len length of the array. Must be non-negative
     * @param offset start index of the range. Must be non-negative
     * @param count length of the range. Must be non-negative
     * @throws ArrayIndexOutOfBoundsException if the range from {@code offset} with length
     * {@code count} is out of bounds of the array
     */
    public static void throwsIfOutOfBounds(int len, int offset, int count) {
        if (len < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative length: " + len);
        }

        if ((offset | count) < 0 || offset > len - count) {
            throw new ArrayIndexOutOfBoundsException(
                    "length=" + len + "; regionStart=" + offset + "; regionLength=" + count);
        }
    }

    public static String deepToString(Object value) {
        if (value != null && value.getClass().isArray()) {
            if (value.getClass() == boolean[].class) {
                return Arrays.toString((boolean[]) value);
            } else if (value.getClass() == byte[].class) {
                return Arrays.toString((byte[]) value);
            } else if (value.getClass() == char[].class) {
                return Arrays.toString((char[]) value);
            } else if (value.getClass() == double[].class) {
                return Arrays.toString((double[]) value);
            } else if (value.getClass() == float[].class) {
                return Arrays.toString((float[]) value);
            } else if (value.getClass() == int[].class) {
                return Arrays.toString((int[]) value);
            } else if (value.getClass() == long[].class) {
                return Arrays.toString((long[]) value);
            } else if (value.getClass() == short[].class) {
                return Arrays.toString((short[]) value);
            } else {
                return Arrays.deepToString((Object[]) value);
            }
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * Returns the {@code i}-th item in {@code items}, if it exists and {@code items} is not {@code
     * null}, otherwise returns {@code null}.
     */
    @Nullable
    public static <T> T getOrNull(@Nullable T[] items, int i) {
        return (items != null && items.length > i) ? items[i] : null;
    }
}
