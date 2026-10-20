/*
 * Copyright (C) 2017 The Android Open Source Project
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

import static java.util.Collections.emptySet;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.ExceptionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utility methods for dealing with (typically {@code Nullable}) {@link Collection}s
 *
 * Unless a method specifies otherwise, a null value for a collection is treated as an empty
 * collection of that type.
 */
@android.ravenwood.annotation.RavenwoodKeepWholeClass
public class CollectionUtils {
    private CollectionUtils() { /* cannot be instantiated */ }

    /**
     * Returns the given list, or an immutable empty list if the provided list is null
     *
     * This can be used to guarantee null-safety without paying the price of extra allocations
     *
     * @see Collections#emptyList
     */
    public static @NonNull <T> List<T> emptyIfNull(@Nullable List<T> cur) {
        return cur == null ? Collections.emptyList() : cur;
    }
}
