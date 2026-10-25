/*
 * Copyright (C) 2015 The Android Open Source Project
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

package android.security.keystore;

import libcore.util.EmptyArray;

import org.androidgraal.hosted.annotate.Emulated;

import java.util.function.Consumer;

/**
 * @hide
 */
@Emulated
public final class ArrayUtils {
    private ArrayUtils() {}

    public static String[] nullToEmpty(String[] array) {
        return (array != null) ? array : EmptyArray.STRING;
    }

    public static String[] cloneIfNotEmpty(String[] array) {
        return ((array != null) && (array.length > 0)) ? array.clone() : array;
    }
}
