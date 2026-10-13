package android.app;

import android.os.Parcelable;

/**
 * A representation of settings that apply to a collection of similarly themed notifications.
 */
public final class NotificationChannel implements Parcelable {
    /**
     * The id of the default channel for an app. This id is reserved by the system. All
     * notifications posted from apps targeting {@link android.os.Build.VERSION_CODES#N_MR1} or
     * earlier without a notification channel specified are posted to this channel.
     */
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing conversation settings (demoting or restoring a channel to
     * be a Conversation, changing bubble behavior, or setting the priority of a conversation).
     */
    public static final String EDIT_CONVERSATION = "conversation";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing importance ({@link #setImportance(int)}) and/or conversation
     * priority.
     */
    public static final String EDIT_IMPORTANCE = "importance";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing launcher behavior (showing badges)}.
     */
    public static final String EDIT_LAUNCHER = "launcher";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing behavior on devices that are locked or have a turned off
     * display ({@link #setLockscreenVisibility(int)}, {@link #enableLights(boolean)},
     * {@link #setLightColor(int)}).
     */
    public static final String EDIT_LOCKED_DEVICE = "locked";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing sound, like a tone picker
     * ({@link #setSound(Uri, AudioAttributes)}).
     */
    public static final String EDIT_SOUND = "sound";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing vibration ({@link #enableVibration(boolean)},
     * {@link #setVibrationPattern(long[])}).
     */
    public static final String EDIT_VIBRATION = "vibration";

    /**
     * Extra value for {@link Settings#EXTRA_CHANNEL_FILTER_LIST}. Include to show fields
     * that have to do with editing do not disturb bypass {(@link #setBypassDnd(boolean)}) .
     */
    public static final String EDIT_ZEN = "zen";

    private NotificationChannel() {
    }
}
