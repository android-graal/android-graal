package android.content.res;

import android.os.Parcelable;

/**
 * Basic information about a Opaque Binary Blob (OBB) that reflects the info
 * from the footer on the OBB file. This information may be manipulated by a
 * developer with the <code>obbtool</code> program in the Android SDK.
 */
public final class ObbInfo implements Parcelable {
    /**
     * Flag noting that this OBB is an overlay patch for a base OBB.
     */
    public static final int OBB_OVERLAY = 1;

    private ObbInfo() {
    }
}
