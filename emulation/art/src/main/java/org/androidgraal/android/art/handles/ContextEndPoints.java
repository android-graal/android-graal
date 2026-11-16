// Generated from android.jar, its api-versions.xml, its sources and the proxy boundary.
package org.androidgraal.android.art.handles;

import android.content.Context;
import android.view.Display;
import java.nio.ByteBuffer;
import org.androidgraal.art.EndPoint;
import org.androidgraal.art.Image;
import org.androidgraal.art.Transport;
import org.androidgraal.shared.Blocks;
import org.androidgraal.shared.Handles;

final class ContextEndPoints {
    private static final Handles HANDLES = Image.handles();

    private ContextEndPoints() {
    }

    @EndPoint
    private static int checkCallingOrSelfPermission(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.checkCallingOrSelfPermission(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int checkCallingPermission(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.checkCallingPermission(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int checkPermission(long handle, long transport, int bytes, int a1, int a2) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.checkPermission(v0, a1, a2);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static void clearWallpaper(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            receiver.clearWallpaper();
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }

    @EndPoint
    private static long createDisplayContext(long handle, long h0) {
        try {
            Display v0 = HANDLES.receive(h0, null);
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.createDisplayContext(v0));
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean deleteDatabase(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.deleteDatabase(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static boolean deleteFile(long handle, long transport, int bytes) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view = transport == 0 ? null : Transport.view(thread, transport, bytes);
            String v0 = view == null ? null : Transport.readStringBlock(view, 0);
            Context receiver = HANDLES.object(handle);
            return receiver.deleteFile(v0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static void enforceCallingOrSelfPermission(long handle, long transport0, int bytes0, long transport1,
            int bytes1) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view0 = transport0 == 0 ? null : Transport.view(thread, transport0, bytes0);
            String v0 = view0 == null ? null : Transport.readStringBlock(view0, 0);
            ByteBuffer view1 = transport1 == 0 ? null : Transport.view(thread, transport1, bytes1);
            String v1 = view1 == null ? null : Transport.readStringBlock(view1, 0);
            Context receiver = HANDLES.object(handle);
            receiver.enforceCallingOrSelfPermission(v0, v1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }

    @EndPoint
    private static void enforceCallingPermission(long handle, long transport0, int bytes0, long transport1,
            int bytes1) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view0 = transport0 == 0 ? null : Transport.view(thread, transport0, bytes0);
            String v0 = view0 == null ? null : Transport.readStringBlock(view0, 0);
            ByteBuffer view1 = transport1 == 0 ? null : Transport.view(thread, transport1, bytes1);
            String v1 = view1 == null ? null : Transport.readStringBlock(view1, 0);
            Context receiver = HANDLES.object(handle);
            receiver.enforceCallingPermission(v0, v1);
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }

    @EndPoint
    private static void enforcePermission(long handle, long transport0, int bytes0, int a1, int a2, long transport3,
            int bytes3) {
        try {
            Image.ThreadData thread = Image.thread();
            ByteBuffer view0 = transport0 == 0 ? null : Transport.view(thread, transport0, bytes0);
            String v0 = view0 == null ? null : Transport.readStringBlock(view0, 0);
            ByteBuffer view3 = transport3 == 0 ? null : Transport.view(thread, transport3, bytes3);
            String v3 = view3 == null ? null : Transport.readStringBlock(view3, 0);
            Context receiver = HANDLES.object(handle);
            receiver.enforcePermission(v0, a1, a2, v3);
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }

    @EndPoint
    private static long getApplicationContext(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return HandleClasses.tagOf(receiver.getApplicationContext());
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static ByteBuffer getPackageCodePath(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getPackageCodePath();
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
    private static ByteBuffer getPackageName(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getPackageName();
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
    private static ByteBuffer getPackageResourcePath(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getPackageResourcePath();
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
    private static ByteBuffer getString(long handle, int a0) {
        try {
            Context receiver = HANDLES.object(handle);
            Image.ThreadData thread = Image.thread();
            String result = receiver.getString(a0);
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
    private static int getWallpaperDesiredMinimumHeight(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.getWallpaperDesiredMinimumHeight();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static int getWallpaperDesiredMinimumWidth(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.getWallpaperDesiredMinimumWidth();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return 0;
        }
    }

    @EndPoint
    private static boolean isRestricted(long handle) {
        try {
            Context receiver = HANDLES.object(handle);
            return receiver.isRestricted();
        } catch (Throwable t) {
            Image.raiseImageException(t);
            return false;
        }
    }

    @EndPoint
    private static void setTheme(long handle, int a0) {
        try {
            Context receiver = HANDLES.object(handle);
            receiver.setTheme(a0);
        } catch (Throwable t) {
            Image.raiseImageException(t);
        }
    }
}
