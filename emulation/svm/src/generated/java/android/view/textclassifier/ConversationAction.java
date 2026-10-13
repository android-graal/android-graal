package android.view.textclassifier;

import android.os.Parcelable;

/**
 * Represents the action suggested by a {@link TextClassifier} on a given conversation.
 */
public final class ConversationAction implements Parcelable {
    /**
     * Indicates an action to call a phone number.
     */
    public static final String TYPE_CALL_PHONE = "call_phone";

    /**
     * Indicates an action to create a reminder.
     */
    public static final String TYPE_CREATE_REMINDER = "create_reminder";

    /**
     * Indicates an action to open an URL.
     */
    public static final String TYPE_OPEN_URL = "open_url";

    /**
     * Indicates an action to send an email.
     */
    public static final String TYPE_SEND_EMAIL = "send_email";

    /**
     * Indicates an action to send a SMS.
     */
    public static final String TYPE_SEND_SMS = "send_sms";

    /**
     * Indicates an action to reply with a location.
     */
    public static final String TYPE_SHARE_LOCATION = "share_location";

    /**
     * Indicates an action to reply with a text message.
     */
    public static final String TYPE_TEXT_REPLY = "text_reply";

    /**
     * Indicates an action to track a flight.
     */
    public static final String TYPE_TRACK_FLIGHT = "track_flight";

    /**
     * Indicates an action to view a calendar at a specified time.
     */
    public static final String TYPE_VIEW_CALENDAR = "view_calendar";

    /**
     * Indicates an action to view the map at a specified location.
     */
    public static final String TYPE_VIEW_MAP = "view_map";

    private ConversationAction() {
    }
}
