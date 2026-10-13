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

import dalvik.system.VMRuntime;

/**
 * Static utility methods for arrays that aren't already included in {@link java.util.Arrays}.
 * <p>
 * Test with:
 * <code>atest FrameworksUtilTests:com.android.internal.util.ArrayUtilsTest</code>
 * <code>atest FrameworksUtilTestsRavenwood:com.android.internal.util.ArrayUtilsTest</code>
 */
public class ArrayUtils {

    private ArrayUtils() { /* cannot be instantiated */ }

    public static int[] newUnpaddedIntArray(int minLen) {
        return (int[])VMRuntime.getRuntime().newUnpaddedArray(int.class, minLen);
    }

    public static Object[] newUnpaddedObjectArray(int minLen) {
        return (Object[])VMRuntime.getRuntime().newUnpaddedArray(Object.class, minLen);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] newUnpaddedArray(Class<T> clazz, int minLen) {
        return (T[])VMRuntime.getRuntime().newUnpaddedArray(clazz, minLen);
    }
}
