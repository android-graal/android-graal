// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.hardware.OverlayProperties;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(35)
final class OverlayPropertiesEndPoints35 {
    private static final Handles HANDLES = Image.handles();

    private OverlayPropertiesEndPoints35() {
    }

    @EndPoint
    private static int describeContents(long handle) {
        try {
            OverlayProperties receiver = HANDLES.object(handle);
            return receiver.describeContents();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean isCombinationSupported(long handle, int a0, int a1) {
        try {
            OverlayProperties receiver = HANDLES.object(handle);
            return receiver.isCombinationSupported(a0, a1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean isMixedColorSpacesSupported(long handle) {
        try {
            OverlayProperties receiver = HANDLES.object(handle);
            return receiver.isMixedColorSpacesSupported();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }
}
