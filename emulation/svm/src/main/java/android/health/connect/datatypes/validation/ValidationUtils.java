/*
 * Copyright (C) 2023 The Android Open Source Project
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

package android.health.connect.datatypes.validation;

import org.androidgraal.hosted.annotate.Emulated;

import java.util.Set;

/**
 * An util class for HC datatype validation
 *
 * @hide
 */
@Emulated
public final class ValidationUtils {
    public static final String INTDEF_VALIDATION_ERROR_PREFIX = "Unknown Intdef value";

    /** Requires an integer value to be among the set of allowed values. */
    public static void validateIntDefValue(int value, Set<Integer> allowedValues, String name) {
        if (!allowedValues.contains(value)) {
            throw new IllegalArgumentException(
                    INTDEF_VALIDATION_ERROR_PREFIX + ": " + value + " for Intdef: " + name + ".");
        }
    }
}
