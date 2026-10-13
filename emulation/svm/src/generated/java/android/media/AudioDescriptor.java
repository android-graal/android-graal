package android.media;

import android.os.Parcelable;

/**
 * The AudioDescriptor contains the information to describe the audio playback/capture
 * capabilities. The capabilities are described by a byte array, which is defined by a
 * particular standard. This is used when the format is unrecognized to the platform.
 */
public final class AudioDescriptor implements Parcelable {
    /**
     * The Extended Display Identification Data (EDID) standard for a short audio descriptor.
     */
    public static final int STANDARD_EDID = 1;

    /**
     * The audio standard is not specified.
     */
    public static final int STANDARD_NONE = 0;

    /**
     * The standard for a Speaker Allocation Data Block (SADB).
     */
    public static final int STANDARD_SADB = 2;

    /**
     * The standard for a Vendor-Specific Audio Data Block (VSADB).
     */
    public static final int STANDARD_VSADB = 3;

    private AudioDescriptor() {
    }
}
