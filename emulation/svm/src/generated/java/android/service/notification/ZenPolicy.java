package android.service.notification;

import android.os.Parcelable;

/**
 * ZenPolicy determines whether to allow certain notifications and their corresponding sounds to
 * play when a device is in Do Not Disturb mode.
 * ZenPolicy also dictates the visual effects of notifications that are intercepted when
 * a device is in Do Not Disturb mode.
 */
public final class ZenPolicy implements Parcelable {
    /**
     * Used to indicate all conversations can bypass dnd.
     */
    public static final int CONVERSATION_SENDERS_ANYONE = 1;

    /**
     * Used to indicate important conversations can bypass dnd.
     */
    public static final int CONVERSATION_SENDERS_IMPORTANT = 2;

    /**
     * Used to indicate no conversations can bypass dnd.
     */
    public static final int CONVERSATION_SENDERS_NONE = 3;

    /**
     * Used to indicate no preference for the type of conversations that can bypass dnd.
     */
    public static final int CONVERSATION_SENDERS_UNSET = 0;

    /**
     * Used to indicate all calls or messages can bypass dnd.
     */
    public static final int PEOPLE_TYPE_ANYONE = 1;

    /**
     * Used to indicate calls or messages from contacts can bypass dnd.
     */
    public static final int PEOPLE_TYPE_CONTACTS = 2;

    /**
     * Used to indicate no calls or messages can bypass dnd.
     */
    public static final int PEOPLE_TYPE_NONE = 4;

    /**
     * Used to indicate calls or messages from starred contacts can bypass dnd.
     */
    public static final int PEOPLE_TYPE_STARRED = 3;

    /**
     * Used to indicate no preference for the type of people that can bypass dnd for either
     * calls or messages.
     */
    public static final int PEOPLE_TYPE_UNSET = 0;

    /**
     * Indicates a type of sound or visual effect is allowed to play/show when DND is active.
     */
    public static final int STATE_ALLOW = 1;

    /**
     * Indicates a type of sound or visual effect is not allowed to play/show when DND is active.
     */
    public static final int STATE_DISALLOW = 2;

    /**
     * Indicates no preference for whether a type of sound or visual effect is or isn't allowed
     * to play/show when DND is active.  Will default to the current set policy.
     */
    public static final int STATE_UNSET = 0;

    private ZenPolicy() {
    }
}
