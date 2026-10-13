package android.hardware.camera2;

/**
 * <p>Allows clients to query availability and supported resolutions of camera extensions.</p>
 *
 * <p>Camera extensions give camera clients access to device-specific algorithms and sequences that
 * can improve the overall image quality of snapshots in various cases such as low light, selfies,
 * portraits, and scenes that can benefit from enhanced dynamic range. Often such sophisticated
 * processing sequences will rely on multiple camera frames as input and will produce a single
 * output.</p>
 *
 * <p>Camera extensions are not guaranteed to be present on all devices so camera clients must
 * query for their availability via {@link CameraExtensionCharacteristics#getSupportedExtensions()}.
 * </p>
 *
 * <p>In order to use any available camera extension, camera clients must create a corresponding
 * {@link CameraExtensionSession} via
 * {@link CameraDevice#createExtensionSession(ExtensionSessionConfiguration)}</p>
 *
 * <p>Camera clients must be aware that device-specific camera extensions may support only a
 * subset of the available camera resolutions and must first query
 * {@link CameraExtensionCharacteristics#getExtensionSupportedSizes(int, int)} for supported
 * single high-quality request output sizes and
 * {@link CameraExtensionCharacteristics#getExtensionSupportedSizes(int, Class)} for supported
 * repeating request output sizes.</p>
 *
 * <p>The extension characteristics for a given device are expected to remain static under
 * normal operating conditions.</p>
 *
 * @see CameraManager#getCameraExtensionCharacteristics(String)
 */
public final class CameraExtensionCharacteristics {
    /**
     * Device-specific extension implementation for automatic selection of particular extension
     * such as HDR or NIGHT depending on the current lighting and environment conditions.
     */
    public static final int EXTENSION_AUTOMATIC = 0;

    /**
     * Device-specific extension implementation which tends to smooth the skin and apply other
     * cosmetic effects to people's faces.
     *
     * @deprecated Use {@link #EXTENSION_FACE_RETOUCH} instead.
     */
    @Deprecated
    public static final int EXTENSION_BEAUTY = 1;

    /**
     * Device-specific extension implementation which can blur certain regions of the final image
     * thereby "enhancing" focus for all remaining non-blurred parts.
     */
    public static final int EXTENSION_BOKEH = 2;

    /**
     * Device-specific extension implementation which tends to smooth the skin and apply other
     * cosmetic effects to people's faces.
     */
    public static final int EXTENSION_FACE_RETOUCH = 1;

    /**
     * Device-specific extension implementation for enhancing the dynamic range of the
     * final image.
     */
    public static final int EXTENSION_HDR = 3;

    /**
     * Device-specific extension implementation that aims to suppress noise and improve the
     * overall image quality under low light conditions.
     */
    public static final int EXTENSION_NIGHT = 4;

    private CameraExtensionCharacteristics() {
    }
}
