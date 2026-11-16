// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;

@RequiresApi(30)
final class ContextEndPoints30 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints30() {
    }

    @EndPoint
    private static long createAttributionContext(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.createAttributionContext(v0));
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getAttributionTag(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getAttributionTag();
            if (result == null) {
                return null;
            }
            ByteBuffer r = Transport.allocBlock(thread, result.length(), 2);
            Transport.write(result, r, Blocks.HEADER);
            return r;
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return null;
        }
    }

    @EndPoint
    private static long getDisplay(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.getDisplay());
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }
}
