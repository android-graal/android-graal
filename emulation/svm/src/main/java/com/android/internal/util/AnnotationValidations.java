/*
 * Copyright (C) 2019 The Android Open Source Project
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

import android.annotation.AppIdInt;
import android.annotation.ColorInt;
import android.annotation.FloatRange;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.Size;
import android.annotation.UserIdInt;

import org.androidgraal.hosted.annotate.Emulated;

import java.lang.annotation.Annotation;

/**
 * Validations for common annotations, e.g. {@link IntRange}, {@link UserIdInt}, etc.
 *
 * For usability from generated {@link DataClass} code, all validations are overloads of
 * {@link #validate} with the following shape:
 * {@code
 *      <A extends Annotation> void validate(
 *              Class<A> cls, A ignored, Object value[, (String, Object)... annotationParams])
 * }
 * The ignored {@link Annotation} parameter is used to differentiate between overloads that would
 * otherwise have the same jvm signature. It's usually null at runtime.
 */
@Emulated
public class AnnotationValidations {
    private AnnotationValidations() {}

    public static void validate(Class<NonNull> annotation, NonNull ignored, Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}
