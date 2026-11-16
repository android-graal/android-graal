// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Handles;

@RequiresApi(33)
final class ContextEndPoints33 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints33() {
    }

    @EndPoint
    private static void revokeSelfPermissionOnKill(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            receiver.revokeSelfPermissionOnKill(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }
}
