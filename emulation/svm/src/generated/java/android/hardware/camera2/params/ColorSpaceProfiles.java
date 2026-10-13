package android.hardware.camera2.params;

/**
 * Immutable class with information about supported color space profiles.
 *
 * <p>An instance of this class can be queried by retrieving the value of
 * {@link android.hardware.camera2.CameraCharacteristics#REQUEST_AVAILABLE_COLOR_SPACE_PROFILES}.
 * </p>
 *
 * <p>All camera devices supporting the
 * {@link android.hardware.camera2.CameraCharacteristics#REQUEST_AVAILABLE_CAPABILITIES_COLOR_SPACE_PROFILES}
 * capability must advertise the supported color space profiles in
 * {@link #getSupportedColorSpaces}</p>
 *
 * @see SessionConfiguration#setColorSpace
 */
public final class ColorSpaceProfiles {
    public static final int UNSPECIFIED = -1;

    private ColorSpaceProfiles() {
    }
}
