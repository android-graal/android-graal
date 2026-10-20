/*
 * Copyright (C) 2014 The Android Open Source Project
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

package android.util;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.ParcelableException;

import com.android.internal.util.Preconditions;

import org.androidgraal.hosted.annotate.Emulated;

import java.io.IOException;

/**
 * Utility methods for proxying richer exceptions across Binder calls.
 *
 * @hide
 */
@Emulated
public class ExceptionUtils {
    /**
     * Gets the root {@link Throwable#getCause() cause} of {@code t}
     */
    public static @NonNull Throwable getRootCause(@NonNull Throwable t) {
        while (t.getCause() != null) t = t.getCause();
        return t;
    }

    /**
     * Appends {@code cause} at the end of the causal chain of {@code t}
     *
     * @return {@code t} for convenience
     */
    public static @NonNull Throwable appendCause(@NonNull Throwable t, @Nullable Throwable cause) {
        if (cause != null) {
            getRootCause(t).initCause(cause);
        }
        return t;
    }


}