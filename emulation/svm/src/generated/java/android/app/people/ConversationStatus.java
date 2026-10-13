package android.app.people;

import android.os.Parcelable;

public final class ConversationStatus implements Parcelable {
    /**
     * Constant representing that the conversation user and the device user are celebrating
     * and anniversary today.
     */
    public static final int ACTIVITY_ANNIVERSARY = 2;

    /**
     * Constant representing that the conversation user is listening to music or other audio
     * like a podcast.
     */
    public static final int ACTIVITY_AUDIO = 4;

    /**
     * Constant representing that today is the conversation user's birthday.
     */
    public static final int ACTIVITY_BIRTHDAY = 1;

    /**
     * Constant representing that the conversation user is playing a game.
     */
    public static final int ACTIVITY_GAME = 6;

    /**
     * Constant representing that the conversation user is sharing status with the device user.
     * Use this to represent a general 'this person is sharing their location with you' status or
     * a more specific 'this is the current location of this person' status.
     */
    public static final int ACTIVITY_LOCATION = 7;

    /**
     * Constant representing that the conversation user has posted a new story.
     */
    public static final int ACTIVITY_NEW_STORY = 3;

    /**
     * Constant representing that the conversation user is engaged in an activity that cannot be
     * more specifically represented by another type.
     */
    public static final int ACTIVITY_OTHER = 0;

    /**
     * Constant representing that the conversation user's birthday is approaching soon.
     */
    public static final int ACTIVITY_UPCOMING_BIRTHDAY = 8;

    /**
     * Constant representing that the conversation user is watching video content.
     */
    public static final int ACTIVITY_VIDEO = 5;

    public static final int AVAILABILITY_AVAILABLE = 0;

    public static final int AVAILABILITY_BUSY = 1;

    public static final int AVAILABILITY_OFFLINE = 2;

    public static final int AVAILABILITY_UNKNOWN = -1;

    private ConversationStatus() {
    }
}
