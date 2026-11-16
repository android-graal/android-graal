// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import androidx.annotation.RequiresApi;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Handles;

@RequiresApi(24)
final class ContextEndPoints24 {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints24() {
    }

    @EndPoint
    private static long createDeviceProtectedStorageContext(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.createDeviceProtectedStorageContext());
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean deleteSharedPreferences(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.deleteSharedPreferences(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean isDeviceProtectedStorage(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.isDeviceProtectedStorage();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean moveDatabaseFrom(long handle, long h0, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v1 = view == null ? null : Transport.readStringBlock(view, 0);
            Context v0 = HANDLES.receive(h0, null);
            Context receiver = HANDLES.object(handle);
            return receiver.moveDatabaseFrom(v0, v1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean moveSharedPreferencesFrom(long handle, long h0, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v1 = view == null ? null : Transport.readStringBlock(view, 0);
            Context v0 = HANDLES.receive(h0, null);
            Context receiver = HANDLES.object(handle);
            return receiver.moveSharedPreferencesFrom(v0, v1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }
}
