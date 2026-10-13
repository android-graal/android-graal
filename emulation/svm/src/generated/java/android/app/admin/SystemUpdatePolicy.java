package android.app.admin;

import android.os.Parcelable;

/**
 * Determines when over-the-air system updates are installed on a device. Only a device policy
 * controller (DPC) running in device owner mode or in profile owner mode for an organization-owned
 * device can set an update policy for the device by calling the {@code DevicePolicyManager} method
 * {@link DevicePolicyManager#setSystemUpdatePolicy setSystemUpdatePolicy()}. An update
 * policy affects the pending system update (if there is one) and any future updates for the device.
 *
 * <p>If a policy is set on a device, the system doesn't notify the user about updates.</p>
 * <h3>Example</h3>
 *
 * <p>The example below shows how a DPC might set a maintenance window for system updates:</p>
 * <pre><code>
 * private final MAINTENANCE_WINDOW_START = 1380; // 11pm
 * private final MAINTENANCE_WINDOW_END = 120; // 2am
 *
 * // ...
 *
 * // Create the system update policy
 * SystemUpdatePolicy policy = SystemUpdatePolicy.createWindowedInstallPolicy(
 *     MAINTENANCE_WINDOW_START, MAINTENANCE_WINDOW_END);
 *
 * // Get a DevicePolicyManager instance to set the policy on the device
 * DevicePolicyManager dpm =
 *     (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
 * ComponentName adminComponent = getComponentName(context);
 * dpm.setSystemUpdatePolicy(adminComponent, policy);
 * </code></pre>
 *
 * <h3>Developer guide</h3>
 * To learn more, read <a href="{@docRoot}work/dpc/system-updates">Manage system updates</a>.
 * <p><strong>Note:</strong> <a href="https://source.android.com/docs/core/ota/modular-system">
 * Google Play system updates</a> (also called Mainline updates) are automatically downloaded
 * but require a device reboot to be installed. Refer to the mainline section in
 * <a href="{@docRoot}work/dpc/system-updates#mainline">Manage system
 * updates</a> for further details.</p>
 *
 * @see DevicePolicyManager#setSystemUpdatePolicy
 * @see DevicePolicyManager#getSystemUpdatePolicy
 */
public final class SystemUpdatePolicy implements Parcelable {
    /**
     * Installs system updates (without user interaction) as soon as they become available. Setting
     * this policy type immediately installs any pending updates that might be postponed or waiting
     * for a maintenance window.
     */
    public static final int TYPE_INSTALL_AUTOMATIC = 1;

    /**
     * Installs system updates (without user interaction) during a daily maintenance window. Set the
     * start and end of the daily maintenance window, as minutes of the day, when creating a new
     * {@code TYPE_INSTALL_WINDOWED} policy. See
     * {@link #createWindowedInstallPolicy createWindowedInstallPolicy()}.
     *
     * <p>No connectivity, not enough disk space, or a low battery are typical reasons Android might
     * not install a system update in the daily maintenance window. After 30 days trying to install
     * an update in the maintenance window (regardless of policy changes in this period), the system
     * prompts the device user to install the update.
     */
    public static final int TYPE_INSTALL_WINDOWED = 2;

    /**
     * Postpones the installation of system updates for 30 days. After the 30-day period has ended,
     * the system prompts the device user to install the update.
     *
     * <p>The system limits each update to one 30-day postponement. The period begins when the
     * system first postpones the update and setting new {@code TYPE_POSTPONE} policies won’t extend
     * the period. If, after 30 days the update isn't installed (through policy changes), the system
     * prompts the user to install the update.
     *
     * <p><strong>Note</strong>: Device manufacturers or carriers might choose to exempt important
     * security updates from a postponement policy. Exempted updates notify the device user when
     * they become available.
     */
    public static final int TYPE_POSTPONE = 3;

    private SystemUpdatePolicy() {
    }

    /**
     * An exception class that represents various validation errors thrown from
     * {@link SystemUpdatePolicy#setFreezePeriods} and
     * {@link DevicePolicyManager#setSystemUpdatePolicy}
     */
    public static final class ValidationFailedException implements Parcelable {
        /**
         * The device has been in a freeze period and some new freeze period to be set is less
         * than 60 days from the end of the last freeze period the device went through.
         */
        public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_CLOSE = 6;

        /**
         * The device has been in a freeze period and when combining with the new freeze period
         * to be set, it will result in the total freeze period being longer than 90 days.
         */
        public static final int ERROR_COMBINED_FREEZE_PERIOD_TOO_LONG = 5;

        /**
         * The freeze periods contains duplicates, periods that overlap with each
         * other or periods whose start and end joins.
         */
        public static final int ERROR_DUPLICATE_OR_OVERLAP = 2;

        /**
         * There exists some freeze period which starts within 60 days of the preceding period's
         * end time.
         */
        public static final int ERROR_NEW_FREEZE_PERIOD_TOO_CLOSE = 4;

        /**
         * There exists at least one freeze period whose length exceeds 90 days.
         */
        public static final int ERROR_NEW_FREEZE_PERIOD_TOO_LONG = 3;

        /**
         * Validation failed with unknown error.
         */
        public static final int ERROR_UNKNOWN = 1;

        private ValidationFailedException() {
        }
    }
}
