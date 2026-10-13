package android.ranging;

import android.os.Parcelable;

/**
 * Represents the configuration for data notifications in ranging operations.
 *
 * <p>This class holds the configuration settings for how notifications are sent
 * regarding the proximity of ranging devices.
 */
public final class DataNotificationConfig implements Parcelable {
    public static final int NOTIFICATION_CONFIG_DISABLE = 0;

    public static final int NOTIFICATION_CONFIG_ENABLE = 1;

    public static final int NOTIFICATION_CONFIG_PROXIMITY_EDGE = 3;

    public static final int NOTIFICATION_CONFIG_PROXIMITY_LEVEL = 2;

    private DataNotificationConfig() {
    }
}
