/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.hardware;

import androidx.annotation.NonNull;

import org.androidgraal.hosted.annotate.Emulated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Provides Lut properties of the device.
 *
 * <p>
 * A Lut (Look-Up Table) is a pre-calculated table for color correction.
 * Applications may be interested in the Lut properties exposed by
 * this class to determine if the Lut(s) they select using
 * {@link android.view.SurfaceControl.Transaction#setLuts} are by the HWC.
 * </p>
 */
@Emulated
public final class LutProperties {
    private final @Dimension int mDimension;
    private final int mSize;
    private final @SamplingKey int[] mSamplingKeys;

    /** @hide */
    @Retention(RetentionPolicy.SOURCE)
    public @interface SamplingKey {
    }

    /** use r,g,b channel as the gain value of a Lut */
    public static final int SAMPLING_KEY_RGB = 0;

    /** use max of r,g,b channel as the gain value of a Lut */
    public static final int SAMPLING_KEY_MAX_RGB = 1;

    /** use y of CIE XYZ as the gain value of a lut */
    public static final int SAMPLING_KEY_CIE_Y = 2;

    /** @hide */
    @Retention(RetentionPolicy.SOURCE)
    public @interface Dimension {
    }

    /** The Lut is one dimensional */
    public static final int ONE_DIMENSION = 1;

    /** The Lut is three dimensional */
    public static final int THREE_DIMENSION = 3;

    public @Dimension int getDimension() {
        return mDimension;
    }

    /**
     * @return the size of the Lut for each dimension
     */
    public int getSize() {
        return mSize;
    }

    /**
     * @return the list of sampling keys
     */
    @NonNull
    public @SamplingKey int[] getSamplingKeys() {
        if (mSamplingKeys.length == 0) {
            throw new IllegalStateException("no sampling key!");
        }
        return mSamplingKeys;
    }

    /* use in the native code */
    private LutProperties(@Dimension int dimension, int size, @SamplingKey int[] samplingKeys) {
        if (dimension != ONE_DIMENSION || dimension != THREE_DIMENSION) {
            throw new IllegalArgumentException("The dimension is either 1 or 3!");
        }
        mDimension = dimension;
        mSize = size;
        mSamplingKeys = samplingKeys;
    }
}
