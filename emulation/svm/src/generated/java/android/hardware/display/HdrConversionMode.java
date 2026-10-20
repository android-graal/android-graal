package android.hardware.display;

import android.os.Parcelable;

/**
 * Describes the HDR conversion mode for a device.
 *
 * HDR conversion mode has a conversionMode and preferredHdrOutputType. </p><p>
 * The conversionMode can be one of:
 * {@link HdrConversionMode#HDR_CONVERSION_UNSUPPORTED} : HDR conversion is unsupported. In this
 * mode, preferredHdrOutputType should not be set.
 * {@link HdrConversionMode#HDR_CONVERSION_PASSTHROUGH} : HDR conversion is disabled. The output HDR
 * type will change dynamically to match the content. In this mode, preferredHdrOutputType should
 * not be set.
 * {@link HdrConversionMode#HDR_CONVERSION_SYSTEM}: The output HDR type is selected by the
 * implementation. In this mode, preferredHdrOutputType will be the mode preferred by the system
 * when querying. However, it should be set to HDR_TYPE_INVALID when setting the mode.
 * {@link HdrConversionMode#HDR_CONVERSION_FORCE}: The implementation converts all content to this
 * HDR type, when possible.
 * In this mode, preferredHdrOutputType should be set.
 * </p>
 */
public final class HdrConversionMode implements Parcelable {
    /**
     * HDR output conversion is set by the user. The preferred output type must be
     * set in this case.
     */
    public static final int HDR_CONVERSION_FORCE = 3;

    /**
     * HDR output conversion is disabled
     */
    public static final int HDR_CONVERSION_PASSTHROUGH = 1;

    /**
     * HDR output conversion is managed by the device manufacturer's implementation.
     */
    public static final int HDR_CONVERSION_SYSTEM = 2;

    /**
     * HDR output conversion is unsupported
     */
    public static final int HDR_CONVERSION_UNSUPPORTED = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private HdrConversionMode() {
    }
}
