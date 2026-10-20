package android.view.translation;

import android.os.Parcelable;

/**
 * Capability class holding information for a pair of {@link TranslationSpec}s.
 *
 * <p>Holds information and limitations on how to create a {@link TranslationContext} which can
 * be used by
 * {@link TranslationManager#createOnDeviceTranslator(TranslationContext, Executor, Consumer)}.
 */
public final class TranslationCapability implements Parcelable {
    /**
     * The translation service supports translation between the source and target specs, and it is
     * ready to be downloaded onto the device.
     */
    public static final int STATE_AVAILABLE_TO_DOWNLOAD = 1;

    /**
     * The translation service supports translation between the source and target specs, and it is
     * being downloaded onto the device currently.
     */
    public static final int STATE_DOWNLOADING = 2;

    /**
     * The translation service does not support translation between the source and target specs.
     *
     * <p>Note: This state is not returned from calling
     * {@link TranslationManager#getOnDeviceTranslationCapabilities}. This state will only appear as
     * part of capability updates from
     * {@link TranslationManager#addOnDeviceTranslationCapabilityUpdateListener} if existing support
     * was dropped.</p>
     */
    public static final int STATE_NOT_AVAILABLE = 4;

    /**
     * The translation service supports translation between the source and target specs, and it is
     * downloaded and ready to use on device.
     */
    public static final int STATE_ON_DEVICE = 3;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TranslationCapability() {
    }
}
