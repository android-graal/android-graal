// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.res.TypedArray;
import androidx.annotation.RequiresApi;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.shared.Handles;

@RequiresApi(22)
final class TypedArrayEndPoints22 {
    private static final Handles HANDLES = Image.handles();

    private TypedArrayEndPoints22() {
    }

    @EndPoint
    private static boolean hasValueOrEmpty(long handle, int a0) {
        try {
            TypedArray receiver = HANDLES.object(handle);
            return receiver.hasValueOrEmpty(a0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }
}
