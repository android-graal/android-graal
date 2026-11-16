// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
/**
 * Copyright (C) 2022 The Android Open Source Project
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

package android.hardware;

import android.annotation.FlaggedApi;
import android.annotation.NonNull;
import android.annotation.SuppressLint;
import android.hardware.flags.Flags;
import android.os.Parcel;
import android.os.Parcelable;

import android.os.Build;
import org.androidgraal.android.handles.OverlayPropertiesBinding;
import org.androidgraal.hosted.annotate.Added;
import org.androidgraal.hosted.annotate.Emulated;
import org.androidgraal.shared.Proxy;

/**
 * Provides supported overlay properties of the device.
 *
 * <p>
 * Hardware overlay is a technique to composite different buffers directly
 * to the screen using display hardware rather than the GPU.
 * The system compositor is able to assign any content managed by a
 * {@link android.view.SurfaceControl} onto a hardware overlay if possible.
 * Applications may be interested in the display hardware capabilities exposed
 * by this class as a hint to determine if their {@link android.view.SurfaceControl}
 * tree is power-efficient and performant.
 * </p>
 */
@FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
@Emulated
public final class OverlayProperties implements Parcelable, Proxy {
    /**
     * For virtual displays, we provide an overlay properties object
     * with RGBA 8888 only, sRGB only, true for mixed color spaces.
     * @hide
     */
    public static OverlayProperties getDefault() {
        throw new UnsupportedOperationException("hidden on ART");
    }

    /**
     * Returns the lut properties of the device.
     */
    @FlaggedApi(Flags.FLAG_LUTS_API)
    @SuppressLint("ArrayReturn")
    @NonNull
    public LutProperties[] getLutProperties() {
        if (Build.VERSION.SDK_INT < 36) {
            throw new NoSuchMethodError("getLutProperties()[Landroid/hardware/LutProperties;");
        }
        throw new UnsupportedOperationException(
                "getLutProperties()[Landroid/hardware/LutProperties;");
    }

    /**
     * Indicates that hardware composition of a buffer encoded with the provided {@link DataSpace}
     * and {@link HardwareBuffer.Format} is supported on the device.
     *
     * @return True if the device can support efficiently compositing the content described by the
     *         dataspace and format. False if GPU composition fallback is otherwise required.
     */
    @FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
    public boolean isCombinationSupported(@DataSpace.ColorDataSpace int dataspace,
            int format) {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("isCombinationSupported(II)Z");
        }
        return OverlayPropertiesBinding.isCombinationSupported(this, dataspace, format);
    }

    /**
     * Indicates that hardware composition of two or more overlays
     * with different colorspaces is supported on the device.
     *
     * @return True if the device can support mixed colorspaces efficiently,
     *         false if GPU composition fallback is otherwise required.
     */
    @FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
    public boolean isMixedColorSpacesSupported() {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("isMixedColorSpacesSupported()Z");
        }
        return OverlayPropertiesBinding.isMixedColorSpacesSupported(this);
    }

    @FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
    @Override
    public int describeContents() {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("describeContents()I");
        }
        return OverlayPropertiesBinding.describeContents(this);
    }

    @FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (Build.VERSION.SDK_INT < 35) {
            throw new NoSuchMethodError("writeToParcel(Landroid/os/Parcel;I)V");
        }
        throw new UnsupportedOperationException("writeToParcel(Landroid/os/Parcel;I)V");
    }

    @FlaggedApi(Flags.FLAG_OVERLAYPROPERTIES_CLASS_API)
    public static final @NonNull Parcelable.Creator<OverlayProperties> CREATOR =
            new Parcelable.Creator<OverlayProperties>() {
        public OverlayProperties createFromParcel(Parcel in) {
            throw new UnsupportedOperationException(
                    "createFromParcel(Landroid/os/Parcel;)Landroid/hardware/OverlayProperties;");
        }

        public OverlayProperties[] newArray(int size) {
            return new OverlayProperties[size];
        }
    };

    @Added(reason = "the id of the ART object this handle forwards to")
    private final long id;

    @Added(reason = "the handle of the ART object received under its id")
    public OverlayProperties(long id) {
        this.id = id;
    }

    @Added(reason = "the id this handle crosses to ART as")
    @Override
    public final long foreignId() {
        return id;
    }
}