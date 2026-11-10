// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.view.Display;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(36)
final class DisplayEndPoints36 {
    private static final Handles HANDLES = Image.handles();

    private DisplayEndPoints36() {
    }

    @EndPoint
    private static float getHighestHdrSdrRatio(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getHighestHdrSdrRatio();
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static float getSuggestedFrameRate(long handle, int a0) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.getSuggestedFrameRate(a0);
        } catch (Throwable t) {
            Image.raiseException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean hasArrSupport(long handle) {
        try {
            Display receiver = HANDLES.object(handle);
            return receiver.hasArrSupport();
        } catch (Throwable t) {
            Image.raiseException(t);
            return false;
        }
    }
}
