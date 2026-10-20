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

package android.media;

import android.annotation.NonNull;
import android.annotation.Nullable;

import org.androidgraal.hosted.annotate.Emulated;

/**
 * Base class for MediaCas runtime exceptions
 */
@Emulated
public class MediaCasStateException extends IllegalStateException {
    private final int mErrorCode;
    private final String mDiagnosticInfo;

    private MediaCasStateException(int err, @Nullable String msg, @Nullable String diagnosticInfo) {
        super(msg);
        mErrorCode = err;
        mDiagnosticInfo = diagnosticInfo;
    }

    /**
     * Retrieve the associated error code
     *
     * @hide
     */
    public int getErrorCode() {
        return mErrorCode;
    }

    /**
     * Retrieve a developer-readable diagnostic information string
     * associated with the exception. Do not show this to end-users,
     * since this string will not be localized or generally comprehensible
     * to end-users.
     */
    @NonNull
    public String getDiagnosticInfo() {
        return mDiagnosticInfo;
    }
}
