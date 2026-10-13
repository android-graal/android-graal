package android.hardware.camera2.params;

/**
 * Immutable class with information about supported 10-bit dynamic range profiles.
 *
 * <p>An instance of this class can be queried by retrieving the value of
 * {@link android.hardware.camera2.CameraCharacteristics#REQUEST_AVAILABLE_DYNAMIC_RANGE_PROFILES}.
 * </p>
 *
 * <p>All camera devices supporting the
 * {@link android.hardware.camera2.CameraCharacteristics#REQUEST_AVAILABLE_CAPABILITIES_DYNAMIC_RANGE_TEN_BIT}
 * capability must advertise the supported 10-bit dynamic range profiles in
 * {@link #getSupportedProfiles}</p>
 *
 * <p>Some devices may not be able to support 8-bit and/or 10-bit output with different dynamic
 * range profiles within the same capture request. Such device specific constraints can be queried
 * by calling {@link #getProfileCaptureRequestConstraints}. Do note that unsupported
 * combinations will result in {@link IllegalArgumentException} when trying to submit a capture
 * request. Capture requests that only reference outputs configured using the same dynamic range
 * profile value will never fail due to such constraints.</p>
 *
 * @see OutputConfiguration#setDynamicRangeProfile
 */
public final class DynamicRangeProfiles {
    /**
     * <p>This is the camera mode for the default Dolby Vision capture mode for the
     * specific device. This would be tuned by each specific device for consumer
     * pleasing results that resonate with their particular audience. We expect
     * that each specific device would have a different look for their default
     * Dolby Vision capture.</p>
     */
    public static final long DOLBY_VISION_10B_HDR_OEM = 64L;

    /**
     * <p>This is the power optimized mode for 10-bit Dolby Vision HDR device specific capture
     * Mode.</p>
     */
    public static final long DOLBY_VISION_10B_HDR_OEM_PO = 128L;

    /**
     * <p>This is a camera mode for Dolby Vision capture optimized for a more scene
     * accurate capture. This would typically differ from what a specific device
     * might want to tune for a consumer optimized Dolby Vision general capture.</p>
     */
    public static final long DOLBY_VISION_10B_HDR_REF = 16L;

    /**
     * <p>This is the power optimized mode for 10-bit Dolby Vision HDR Reference Mode.</p>
     */
    public static final long DOLBY_VISION_10B_HDR_REF_PO = 32L;

    /**
     * <p>This is the 8-bit version of device specific tuned and optimized Dolby Vision
     * capture mode.</p>
     */
    public static final long DOLBY_VISION_8B_HDR_OEM = 1024L;

    /**
     * <p>This is the power optimized mode for 8-bit Dolby Vision HDR device specific
     * capture Mode.</p>
     */
    public static final long DOLBY_VISION_8B_HDR_OEM_PO = 2048L;

    /**
     * <p>This is the 8-bit version of the Dolby Vision reference capture mode optimized
     * for scene accuracy.</p>
     */
    public static final long DOLBY_VISION_8B_HDR_REF = 256L;

    /**
     * <p>This is the power optimized mode for 8-bit Dolby Vision HDR Reference Mode.</p>
     */
    public static final long DOLBY_VISION_8B_HDR_REF_PO = 512L;

    /**
     * 10-bit pixel samples encoded using the SMPTE ST 2084 transfer function.
     *
     * <p>This profile utilizes internal static metadata to increase the quality
     * of the capture.</p>
     */
    public static final long HDR10 = 4L;

    /**
     * 10-bit pixel samples encoded using the SMPTE ST 2084 transfer function.
     *
     * <p>In contrast to HDR10, this profile uses internal per-frame metadata
     * to further enhance the quality of the capture.</p>
     */
    public static final long HDR10_PLUS = 8L;

    /**
     * 10-bit pixel samples encoded using the Hybrid log-gamma transfer function
     *
     * <p>All 10-bit output capable devices are required to support this profile.</p>
     */
    public static final long HLG10 = 2L;

    public static final long PUBLIC_MAX = 4096L;

    /**
     * This the default 8-bit standard profile that will be used in case where camera clients do not
     * explicitly configure a supported dynamic range profile by calling
     * {@link OutputConfiguration#setDynamicRangeProfile}.
     */
    public static final long STANDARD = 1L;

    private DynamicRangeProfiles() {
    }
}
