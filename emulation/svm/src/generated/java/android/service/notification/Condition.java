package android.service.notification;

import android.os.Parcelable;

/**
 * The current condition of an {@link android.app.AutomaticZenRule}, provided by the
 * app that owns the rule. Used to tell the system to enter Do Not
 * Disturb mode and request that the system exit Do Not Disturb mode.
 */
public final class Condition implements Parcelable {
    public static final int FLAG_RELEVANT_ALWAYS = 2;

    public static final int FLAG_RELEVANT_NOW = 1;

    public static final String SCHEME = "condition";

    /**
     * The state is changing due to a change in context (such as detected driving or sleeping).
     */
    public static final int SOURCE_CONTEXT = 3;

    /**
     * The state is changing due to an automatic schedule (alarm, set time, etc).
     */
    public static final int SOURCE_SCHEDULE = 2;

    /**
     * The state is changing due to an unknown reason.
     */
    public static final int SOURCE_UNKNOWN = 0;

    /**
     * The state is changing due to an explicit user action.
     */
    public static final int SOURCE_USER_ACTION = 1;

    public static final int STATE_ERROR = 3;

    /**
     * Indicates that Do Not Disturb should be turned off. Note that all Conditions from all
     * {@link android.app.AutomaticZenRule} providers must be off for Do Not Disturb to be turned
     * off on the device.
     */
    public static final int STATE_FALSE = 0;

    /**
     * Indicates that Do Not Disturb should be turned on.
     */
    public static final int STATE_TRUE = 1;

    public static final int STATE_UNKNOWN = 2;

    private Condition() {
    }
}
