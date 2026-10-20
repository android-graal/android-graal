package android.service.controls;

import android.os.Parcelable;

/**
 * Represents a physical object that can be represented by a {@link ControlTemplate} and whose
 * properties may be modified through a {@link ControlAction}.
 *
 * The information is provided by a {@link ControlsProviderService} and represents static
 * information (not current status) about the device.
 * <p>
 * Each control needs a unique (per provider) identifier that is persistent across reboots of the
 * system.
 * <p>
 * Each {@link Control} will have a name, a subtitle and will optionally belong to a structure
 * and zone. Some of these values are defined by the user and/or the {@link ControlsProviderService}
 * and will be used to display the control as well as group them for management.
 * <p>
 * Each object will have an associated {@link DeviceTypes}. This will determine the icons and colors
 * used to display it.
 * <p>
 * An {@link Intent} linking to the provider Activity that expands on this {@link Control} and
 * allows for further actions should be provided.
 */
public final class Control implements Parcelable {
    /**
     * The {@link Control} is currently disabled.  A default error message will be displayed in
     * place of any custom text that was set through {@link StatefulBuilder#setStatusText}.
     */
    public static final int STATUS_DISABLED = 4;

    /**
     * Used to indicate that there was a temporary error while loading the device state. A default
     * error message will be displayed in place of any custom text that was set through
     * {@link StatefulBuilder#setStatusText}.
     */
    public static final int STATUS_ERROR = 3;

    /**
     * The device corresponding to the {@link Control} cannot be found or was removed. The user
     * will be alerted and directed to the application to resolve.
     */
    public static final int STATUS_NOT_FOUND = 2;

    /**
     * Used to indicate that the state of the device was successfully retrieved. This includes
     * all scenarios where the device may have a warning for the user, such as "Lock jammed",
     * or "Vacuum stuck". Any information for the user should be set through
     * {@link StatefulBuilder#setStatusText}.
     */
    public static final int STATUS_OK = 1;

    /**
     * Reserved for use with the {@link StatelessBuilder}, and while loading. When state is
     * requested via {@link ControlsProviderService#createPublisherFor}, use other status codes
     * to indicate the proper device state.
     */
    public static final int STATUS_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Control() {
    }
}
