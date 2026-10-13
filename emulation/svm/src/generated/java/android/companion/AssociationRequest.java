package android.companion;

import android.os.Parcelable;

/**
 * A request for the user to select a companion device to associate with.
 *
 * You can optionally set {@link Builder#addDeviceFilter filters} for which devices to show to the
 * user to select from.
 * The exact type and fields of the filter you can set depend on the
 * medium type. See {@link Builder}'s static factory methods for specific protocols that are
 * supported.
 *
 * You can also set {@link Builder#setSingleDevice single device} to request a popup with single
 * device to be shown instead of a list to choose from
 */
public final class AssociationRequest implements Parcelable {
    /**
     * Device profile: a virtual display capable of rendering Android applications, and sending back
     * input events.
     * <p>
     * Only applications that have been granted
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_APP_STREAMING} are allowed to
     * request to be associated with such devices.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_APP_STREAMING = "android.app.role.COMPANION_DEVICE_APP_STREAMING";

    /**
     * Device profile: Android Automotive Projection
     *
     * Only applications that have been granted
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_AUTOMOTIVE_PROJECTION} are
     * allowed to request to be associated with such devices.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_AUTOMOTIVE_PROJECTION = "android.app.role.SYSTEM_AUTOMOTIVE_PROJECTION";

    /**
     * Device profile: Allows the companion app to access notification, recent photos and media for
     * computer cross-device features.
     *
     * Only applications that have been granted
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_COMPUTER} are allowed to
     * request to be associated with such devices.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_COMPUTER = "android.app.role.COMPANION_DEVICE_COMPUTER";

    /**
     * Device profile: glasses.
     *
     * If specified, the current request may have a modified UI to highlight that the device being
     * set up is a glasses device, and some extra permissions may be granted to the app
     * as a result.
     *
     * Using it requires declaring uses-permission
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_GLASSES} in the manifest.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_GLASSES = "android.app.role.COMPANION_DEVICE_GLASSES";

    /**
     * Device profile: a virtual device capable of rendering content from an Android host to a
     * nearby device.
     * <p>
     * Only applications that have been granted
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_NEARBY_DEVICE_STREAMING}
     * are allowed to request to be associated with such devices.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_NEARBY_DEVICE_STREAMING = "android.app.role.COMPANION_DEVICE_NEARBY_DEVICE_STREAMING";

    /**
     * Device profile: watch.
     *
     * If specified, the current request may have a modified UI to highlight that the device being
     * set up is a specific kind of device, and some extra permissions may be granted to the app
     * as a result.
     *
     * Using it requires declaring uses-permission
     * {@link android.Manifest.permission#REQUEST_COMPANION_PROFILE_WATCH} in the manifest.
     *
     * <a href="{@docRoot}about/versions/12/features#cdm-profiles">Learn more</a>
     * about device profiles.
     *
     * @see AssociationRequest.Builder#setDeviceProfile
     */
    public static final String DEVICE_PROFILE_WATCH = "android.app.role.COMPANION_DEVICE_WATCH";

    private AssociationRequest() {
    }
}
