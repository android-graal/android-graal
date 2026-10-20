package android.app;

import android.os.Parcelable;

/**
 * A class that represents how a persistent notification is to be presented to
 * the user using the {@link android.app.NotificationManager}.
 *
 * <p>The {@link Notification.Builder Notification.Builder} has been added to make it
 * easier to construct Notifications.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For a guide to creating notifications, read the
 * <a href="{@docRoot}guide/topics/ui/notifiers/notifications.html">Status Bar Notifications</a>
 * developer guide.</p>
 * </div>
 */
public final class Notification implements Parcelable {
    /**
     * If this notification is being shown as a badge, use the {@link #getLargeIcon()} to
     * represent this notification.
     */
    public static final int BADGE_ICON_LARGE = 2;

    /**
     * If this notification is being shown as a badge, always show as a number.
     */
    public static final int BADGE_ICON_NONE = 0;

    /**
     * If this notification is being shown as a badge, use the {@link #getSmallIcon()} to
     * represent this notification.
     */
    public static final int BADGE_ICON_SMALL = 1;

    /**
     * Notification category: alarm or timer.
     */
    public static final String CATEGORY_ALARM = "alarm";

    /**
     * Notification category: incoming call (voice or video) or similar synchronous communication request.
     */
    public static final String CATEGORY_CALL = "call";

    /**
     * Notification category: asynchronous bulk message (email).
     */
    public static final String CATEGORY_EMAIL = "email";

    /**
     * Notification category: error in background operation or authentication status.
     */
    public static final String CATEGORY_ERROR = "err";

    /**
     * Notification category: calendar event.
     */
    public static final String CATEGORY_EVENT = "event";

    /**
     * Notification category: temporarily sharing location.
     */
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";

    /**
     * Notification category: incoming direct message (SMS, instant message, etc.).
     */
    public static final String CATEGORY_MESSAGE = "msg";

    /**
     * Notification category: missed call.
     */
    public static final String CATEGORY_MISSED_CALL = "missed_call";

    /**
     * Notification category: map turn-by-turn navigation.
     */
    public static final String CATEGORY_NAVIGATION = "navigation";

    /**
     * Notification category: progress of a long-running background operation.
     */
    public static final String CATEGORY_PROGRESS = "progress";

    /**
     * Notification category: promotion or advertisement.
     */
    public static final String CATEGORY_PROMO = "promo";

    /**
     * Notification category: a specific, timely recommendation for a single thing.
     * For example, a news app might want to recommend a news story it believes the user will
     * want to read next.
     */
    public static final String CATEGORY_RECOMMENDATION = "recommendation";

    /**
     * Notification category: user-scheduled reminder.
     */
    public static final String CATEGORY_REMINDER = "reminder";

    /**
     * Notification category: indication of running background service.
     */
    public static final String CATEGORY_SERVICE = "service";

    /**
     * Notification category: social network or sharing update.
     */
    public static final String CATEGORY_SOCIAL = "social";

    /**
     * Notification category: ongoing information about device or contextual status.
     */
    public static final String CATEGORY_STATUS = "status";

    /**
     * Notification category: running stopwatch.
     */
    public static final String CATEGORY_STOPWATCH = "stopwatch";

    /**
     * Notification category: system or device status update.  Reserved for system use.
     */
    public static final String CATEGORY_SYSTEM = "sys";

    /**
     * Notification category: media transport control for playback.
     */
    public static final String CATEGORY_TRANSPORT = "transport";

    /**
     * Notification category: voicemail.
     */
    public static final String CATEGORY_VOICEMAIL = "voicemail";

    /**
     * Notification category: tracking a user's workout.
     */
    public static final String CATEGORY_WORKOUT = "workout";

    /**
     * Special value of {@link #color} telling the system not to decorate this notification with
     * any special color but instead use default colors when presenting this notification.
     */
    public static final int COLOR_DEFAULT = 0;

    /**
     * Use all default values (where applicable).
     */
    public static final int DEFAULT_ALL = -1;

    /**
     * Use the default notification lights. This will ignore the
     * {@link #FLAG_SHOW_LIGHTS} bit, and {@link #ledARGB}, {@link #ledOffMS}, or
     * {@link #ledOnMS}.
     *
     * @see #defaults
     */
    public static final int DEFAULT_LIGHTS = 4;

    /**
     * Use the default notification sound. This will ignore any given
     * {@link #sound}.
     *
     * <p>
     * A notification that is noisy is more likely to be presented as a heads-up notification.
     * </p>
     *
     * @see #defaults
     */
    public static final int DEFAULT_SOUND = 1;

    /**
     * Use the default notification vibrate. This will ignore any given
     * {@link #vibrate}. Using phone vibration requires the
     * {@link android.Manifest.permission#VIBRATE VIBRATE} permission.
     *
     * <p>
     * A notification that vibrates is more likely to be presented as a heads-up notification.
     * </p>
     *
     * @see #defaults
     */
    public static final int DEFAULT_VIBRATE = 2;

    /**
     * {@link #extras} key: the color used as a hint for the Answer action button of a
     * {@link android.app.Notification.CallStyle} notification. This extra is a {@code ColorInt}.
     */
    public static final String EXTRA_ANSWER_COLOR = "android.answerColor";

    /**
     * {@link #extras} key: the intent to be sent when the users answers a
     * {@link android.app.Notification.CallStyle} notification. This extra is a
     * {@link PendingIntent}.
     */
    public static final String EXTRA_ANSWER_INTENT = "android.answerIntent";

    /**
     * {@link #extras} key: the audio contents of this notification.
     *
     * This is for use when rendering the notification on an audio-focused interface;
     * the audio contents are a complete sound sample that contains the contents/body of the
     * notification. This may be used in substitute of a Text-to-Speech reading of the
     * notification. For example if the notification represents a voice message this should point
     * to the audio of that message.
     *
     * The data stored under this key should be a String representation of a Uri that contains the
     * audio contents in one of the following formats: WAV, PCM 16-bit, AMR-WB.
     *
     * This extra is unnecessary if you are using {@code MessagingStyle} since each {@code Message}
     * has a field for holding data URI. That field can be used for audio.
     * See {@code Message#setData}.
     *
     * Example usage:
     * <pre>
     * {@code
     * Notification.Builder myBuilder = (build your Notification as normal);
     * myBuilder.getExtras().putString(EXTRA_AUDIO_CONTENTS_URI, myAudioUri.toString());
     * }
     * </pre>
     */
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";

    /**
     * {@link #extras} key:
     * flat {@link String} representation of a {@link android.content.ContentUris content URI}
     * pointing to an image that can be displayed in the background when the notification is
     * selected. Used on television platforms. The URI must point to an image stream suitable for
     * passing into {@link android.graphics.BitmapFactory#decodeStream(java.io.InputStream)
     * BitmapFactory.decodeStream}; all other content types will be ignored.
     */
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";

    /**
     * {@link #extras} key: this is the longer text shown in the expanded form of a
     * {@link BigTextStyle} notification, as supplied to
     * {@link BigTextStyle#bigText(CharSequence)}.
     */
    public static final String EXTRA_BIG_TEXT = "android.bigText";

    /**
     * {@link #extras} key: whether the  {@link android.app.Notification.CallStyle} notification
     * is for a call that will activate video when answered. This extra is a boolean.
     */
    public static final String EXTRA_CALL_IS_VIDEO = "android.callIsVideo";

    /**
     * {@link #extras} key: the person to be displayed as calling for the
     * {@link android.app.Notification.CallStyle} notification. This extra is a {@link Person}.
     */
    public static final String EXTRA_CALL_PERSON = "android.callPerson";

    /**
     * {@link #extras} key: the type of call represented by the
     * {@link android.app.Notification.CallStyle} notification. This extra is an int.
     */
    public static final String EXTRA_CALL_TYPE = "android.callType";

    /**
     * Optional extra for {@link #INTENT_CATEGORY_NOTIFICATION_PREFERENCES}. If provided, will
     * contain a {@link NotificationChannelGroup#getId() group id} that can be used to narrow down
     * what settings should be shown in the target app.
     */
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";

    /**
     * Optional extra for {@link #INTENT_CATEGORY_NOTIFICATION_PREFERENCES}. If provided, will
     * contain a {@link NotificationChannel#getId() channel id} that can be used to narrow down
     * what settings should be shown in the target app.
     */
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";

    /**
     * {@link #extras} key: whether the chronometer set on the notification should count down
     * instead of counting up. Is only relevant if key {@link #EXTRA_SHOW_CHRONOMETER} is present.
     * This extra is a boolean. The default is false.
     */
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";

    /**
     * {@link #extras} key: whether the notification should be colorized as
     * supplied to {@link Builder#setColorized(boolean)}.
     */
    public static final String EXTRA_COLORIZED = "android.colorized";

    /**
     * {@link #extras} key: the indices of actions to be shown in the compact view,
     * as supplied to (e.g.) {@link MediaStyle#setShowActionsInCompactView(int...)}.
     */
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";

    /**
     * {@link #extras} key: a {@link CharSequence} to be displayed as the title to a conversation
     * represented by a {@link android.app.Notification.MessagingStyle}
     */
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";

    /**
     * {@link #extras} key: the color used as a hint for the Decline or Hang Up action button of a
     * {@link android.app.Notification.CallStyle} notification. This extra is a {@code ColorInt}.
     */
    public static final String EXTRA_DECLINE_COLOR = "android.declineColor";

    /**
     * {@link #extras} key: the intent to be sent when the users declines a
     * {@link android.app.Notification.CallStyle} notification. This extra is a
     * {@link PendingIntent}.
     */
    public static final String EXTRA_DECLINE_INTENT = "android.declineIntent";

    /**
     * {@link #extras} key: the intent to be sent when the users hangs up a
     * {@link android.app.Notification.CallStyle} notification. This extra is a
     * {@link PendingIntent}.
     */
    public static final String EXTRA_HANG_UP_INTENT = "android.hangUpIntent";

    /**
     * {@link #extras} key: an array of
     * {@link android.app.Notification.MessagingStyle#addHistoricMessage historic}
     * {@link android.app.Notification.MessagingStyle.Message} bundles provided by a
     * {@link android.app.Notification.MessagingStyle} notification. This extra is a parcelable
     * array of bundles.
     */
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";

    /**
     * {@link #extras} key: this is a small piece of additional text as supplied to
     * {@link Builder#setContentInfo(CharSequence)}.
     */
    public static final String EXTRA_INFO_TEXT = "android.infoText";

    /**
     * {@link #extras} key: whether the {@link android.app.Notification.MessagingStyle} notification
     * represents a group conversation.
     */
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";

    /**
     * {@link #extras} key: this is a bitmap to be used instead of the small icon when showing the
     * notification payload, as
     * supplied to {@link Builder#setLargeIcon(android.graphics.Bitmap)}.
     *
     * @deprecated Use {@link #getLargeIcon()}, which supports a wider variety of icon sources.
     */
    @Deprecated
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";

    /**
     * {@link #extras} key: this is a bitmap to be used instead of the one from
     * {@link Builder#setLargeIcon(android.graphics.Bitmap)} when the notification is
     * shown in its expanded form, as supplied to
     * {@link BigPictureStyle#bigLargeIcon(android.graphics.Bitmap)}.
     */
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";

    /**
     * {@link #extras} key: A
     * {@link android.media.session.MediaSession.Token} associated with a
     * {@link android.app.Notification.MediaStyle} notification.
     */
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";

    /**
     * {@link #extras} key: an array of {@link android.app.Notification.MessagingStyle.Message}
     * bundles provided by a
     * {@link android.app.Notification.MessagingStyle} notification. This extra is a parcelable
     * array of bundles.
     */
    public static final String EXTRA_MESSAGES = "android.messages";

    /**
     * {@link #extras} key: the person to be displayed for all messages sent by the user including
     * direct replies
     * {@link android.app.Notification.MessagingStyle} notification. This extra is a
     * {@link Person}
     */
    public static final String EXTRA_MESSAGING_PERSON = "android.messagingUser";

    /**
     * Optional extra for {@link #INTENT_CATEGORY_NOTIFICATION_PREFERENCES}. If provided, will
     * contain the id provided to {@link NotificationManager#notify(String, int, Notification)}
     * that can be used to narrow down what settings should be shown in the target app.
     */
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";

    /**
     * Optional extra for {@link #INTENT_CATEGORY_NOTIFICATION_PREFERENCES}. If provided, will
     * contain the tag provided to {@link NotificationManager#notify(String, int, Notification)}
     * that can be used to narrow down what settings should be shown in the target app.
     */
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";

    /**
     * {@link #extras} key: A String array containing the people that this notification relates to,
     * each of which was supplied to {@link Builder#addPerson(String)}.
     *
     * @deprecated the actual objects are now in {@link #EXTRA_PEOPLE_LIST}
     */
    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";

    /**
     * {@link #extras} key: An arrayList of {@link Person} objects containing the people that
     * this notification relates to.
     */
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";

    /**
     * {@link #extras} key: this is a bitmap to be shown in {@link BigPictureStyle} expanded
     * notifications, supplied to {@link BigPictureStyle#bigPicture(android.graphics.Bitmap)}.
     */
    public static final String EXTRA_PICTURE = "android.picture";

    /**
     * {@link #extras} key: this is a content description of the big picture supplied from
     * {@link BigPictureStyle#bigPicture(Bitmap)}, supplied to
     * {@link BigPictureStyle#setContentDescription(CharSequence)}.
     */
    public static final String EXTRA_PICTURE_CONTENT_DESCRIPTION = "android.pictureContentDescription";

    /**
     * {@link #extras} key: this is an {@link Icon} of an image to be
     * shown in {@link BigPictureStyle} expanded notifications, supplied to
     * {@link BigPictureStyle#bigPicture(Icon)}.
     */
    public static final String EXTRA_PICTURE_ICON = "android.pictureIcon";

    /**
     * {@link #extras} key: this is the progress value supplied to
     * {@link Builder#setProgress(int, int, boolean)}.
     */
    public static final String EXTRA_PROGRESS = "android.progress";

    /**
     * {@link #extras} key: whether the progress bar is indeterminate, supplied to
     * {@link Builder#setProgress(int, int, boolean)}.
     */
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";

    /**
     * {@link #extras} key: this is the maximum value supplied to
     * {@link Builder#setProgress(int, int, boolean)}.
     */
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";

    /**
     * If the notification contained an unsent draft for a RemoteInput when the user clicked on it,
     * we're adding the draft as a String extra to the {@link #contentIntent} using this key.
     *
     * <p>Apps may use this extra to prepopulate text fields in the app, where the user usually
     * sends messages.</p>
     */
    public static final String EXTRA_REMOTE_INPUT_DRAFT = "android.remoteInputDraft";

    /**
     * {@link #extras} key: this is the remote input history, as supplied to
     * {@link Builder#setRemoteInputHistory(CharSequence[])}.
     *
     * Apps can fill this through {@link Builder#setRemoteInputHistory(CharSequence[])}
     * with the most recent inputs that have been sent through a {@link RemoteInput} of this
     * Notification and are expected to clear it once the it is no longer relevant (e.g. for chat
     * notifications once the other party has responded).
     *
     * The extra with this key is of type CharSequence[] and contains the most recent entry at
     * the 0 index, the second most recent at the 1 index, etc.
     *
     * @see Builder#setRemoteInputHistory(CharSequence[])
     */
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";

    /**
     * {@link #extras} key: the username to be displayed for all messages sent by the user including
     * direct replies
     * {@link android.app.Notification.MessagingStyle} notification. This extra is a
     * {@link CharSequence}
     *
     * @deprecated use {@link #EXTRA_MESSAGING_PERSON}
     */
    @Deprecated
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";

    /**
     * {@link #extras} key: this is a boolean to indicate that the
     * {@link BigPictureStyle#bigPicture(Bitmap) big picture} is to be shown in the collapsed state
     * of a {@link BigPictureStyle} notification.  This will replace a
     * {@link Builder#setLargeIcon(Icon) large icon} in that state if one was provided.
     */
    public static final String EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED = "android.showBigPictureWhenCollapsed";

    /**
     * {@link #extras} key: whether {@link #when} should be shown as a count-up timer (specifically
     * a {@link android.widget.Chronometer}) instead of a timestamp, as supplied to
     * {@link Builder#setUsesChronometer(boolean)}.
     */
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";

    /**
     * {@link #extras} key: whether {@link #when} should be shown,
     * as supplied to {@link Builder#setShowWhen(boolean)}.
     */
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";

    /**
     * {@link #extras} key: this is the resource ID of the notification's main small icon, as
     * supplied to {@link Builder#setSmallIcon(int)}.
     *
     * @deprecated Use {@link #getSmallIcon()}, which supports a wider variety of icon sources.
     */
    @Deprecated
    public static final String EXTRA_SMALL_ICON = "android.icon";

    /**
     * {@link #extras} key: this is a third line of text, as supplied to
     * {@link Builder#setSubText(CharSequence)}.
     */
    public static final String EXTRA_SUB_TEXT = "android.subText";

    /**
     * {@link #extras} key: this is a line of summary information intended to be shown
     * alongside expanded notifications, as supplied to (e.g.)
     * {@link BigTextStyle#setSummaryText(CharSequence)}.
     */
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";

    /**
     * {@link #extras} key: A string representing the name of the specific
     * {@link android.app.Notification.Style} used to create this notification.
     */
    public static final String EXTRA_TEMPLATE = "android.template";

    /**
     * {@link #extras} key: this is the main text payload, as supplied to
     * {@link Builder#setContentText(CharSequence)}.
     */
    public static final String EXTRA_TEXT = "android.text";

    /**
     * {@link #extras} key: An array of CharSequences to show in {@link InboxStyle} expanded
     * notifications, each of which was supplied to {@link InboxStyle#addLine(CharSequence)}.
     */
    public static final String EXTRA_TEXT_LINES = "android.textLines";

    /**
     * {@link #extras} key: this is the title of the notification,
     * as supplied to {@link Builder#setContentTitle(CharSequence)}.
     */
    public static final String EXTRA_TITLE = "android.title";

    /**
     * {@link #extras} key: this is the title of the notification when shown in expanded form,
     * e.g. as supplied to {@link BigTextStyle#setBigContentTitle(CharSequence)}.
     */
    public static final String EXTRA_TITLE_BIG = "android.title.big";

    /**
     * {@link #extras} key: the icon to be displayed as a verification status of the caller on a
     * {@link android.app.Notification.CallStyle} notification. This extra is an {@link Icon}.
     */
    public static final String EXTRA_VERIFICATION_ICON = "android.verificationIcon";

    /**
     * {@link #extras} key: the text to be displayed as a verification status of the caller on a
     * {@link android.app.Notification.CallStyle} notification. This extra is a
     * {@link CharSequence}.
     */
    public static final String EXTRA_VERIFICATION_TEXT = "android.verificationText";

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if the notification should be canceled when it is clicked by the
     * user.
     */
    public static final int FLAG_AUTO_CANCEL = 16;

    /**
     * Bit to be bitswised-ored into the {@link #flags} field that should be
     * set by the system if this notification is showing as a bubble.
     *
     * Applications cannot set this flag directly; they should instead call
     * {@link Notification.Builder#setBubbleMetadata(BubbleMetadata)} to
     * request that a notification be displayed as a bubble, and then check
     * this flag to see whether that request was honored by the system.
     */
    public static final int FLAG_BUBBLE = 4096;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if this notification represents a currently running service.  This
     * will normally be set for you by {@link Service#startForeground}.
     */
    public static final int FLAG_FOREGROUND_SERVICE = 64;

    /**
     * Bit to be bitswise-ored into the {@link #flags} field that should be
     * set if this notification is the group summary for a group of notifications.
     * Grouped notifications may display in a cluster or stack on devices which
     * support such rendering. Requires a group key also be set using {@link Builder#setGroup}.
     */
    public static final int FLAG_GROUP_SUMMARY = 512;

    /**
     * Obsolete flag indicating high-priority notifications; use the priority field instead.
     *
     * @deprecated Use {@link #priority} with a positive value.
     */
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that if set,
     * the audio will be repeated until the notification is
     * cancelled or the notification window is opened.
     */
    public static final int FLAG_INSISTENT = 4;

    /**
     * Bit to be bitswise-ored into the {@link #flags} field that should be
     * set if this notification is relevant to the current device only
     * and it is not recommended that it bridge to other devices.
     */
    public static final int FLAG_LOCAL_ONLY = 256;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if the notification should not be canceled when the user clicks
     * the Clear all button.
     */
    public static final int FLAG_NO_CLEAR = 32;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if this notification is in reference to something that is ongoing,
     * like a phone call.  It should not be set if this notification is in
     * reference to something that happened at a particular point in time,
     * like a missed phone call.
     */
    public static final int FLAG_ONGOING_EVENT = 2;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if you would only like the sound, vibrate and ticker to be played
     * if the notification was not already showing.
     *
     * Note that using this flag will stop any ongoing alerting behaviour such
     * as sound, vibration or blinking notification LED.
     */
    public static final int FLAG_ONLY_ALERT_ONCE = 8;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set by the system if this notification is a promoted ongoing notification, both because it
     * {@link #hasPromotableCharacteristics()} and the user has not disabled the feature for this
     * app.
     *
     * Applications cannot set this flag directly, but the posting app and
     * {@link android.service.notification.NotificationListenerService} can read it.
     */
    public static final int FLAG_PROMOTED_ONGOING = 262144;

    /**
     * Bit to be bitwise-ored into the {@link #flags} field that should be
     * set if you want the LED on for this notification.
     * <ul>
     * <li>To turn the LED off, pass 0 in the alpha channel for colorARGB
     *      or 0 for both ledOnMS and ledOffMS.</li>
     * <li>To turn the LED on, pass 1 for ledOnMS and 0 for ledOffMS.</li>
     * <li>To flash the LED, pass the number of milliseconds that it should
     *      be on and off to ledOnMS and ledOffMS.</li>
     * </ul>
     * <p>
     * Since hardware varies, you are not guaranteed that any of the values
     * you pass are honored exactly.  Use the system defaults if possible
     * because they will be set to values that work on any given hardware.
     * <p>
     * The alpha channel must be set for forward compatibility.
     *
     * @deprecated use {@link NotificationChannel#shouldShowLights()}.
     */
    @Deprecated
    public static final int FLAG_SHOW_LIGHTS = 1;

    /**
     * If the Notification associated with starting a foreground service has been
     * built using setForegroundServiceBehavior() with this behavior, display of
     * the notification will usually be suppressed for a short time to avoid visual
     * disturbances to the user.
     * @see Notification.Builder#setForegroundServiceBehavior(int)
     * @see #FOREGROUND_SERVICE_IMMEDIATE
     * @see #FOREGROUND_SERVICE_DEFERRED
     */
    public static final int FOREGROUND_SERVICE_DEFAULT = 0;

    /**
     * If the Notification associated with starting a foreground service has been
     * built using setForegroundServiceBehavior() with this behavior, display of
     * the notification will usually be suppressed for a short time to avoid visual
     * disturbances to the user.
     * @see Notification.Builder#setForegroundServiceBehavior(int)
     * @see #FOREGROUND_SERVICE_DEFAULT
     * @see #FOREGROUND_SERVICE_IMMEDIATE
     */
    public static final int FOREGROUND_SERVICE_DEFERRED = 2;

    /**
     * If the Notification associated with starting a foreground service has been
     * built using setForegroundServiceBehavior() with this behavior, display of
     * the notification will be immediate even if the default behavior would be
     * to defer visibility for a short time.
     * @see Notification.Builder#setForegroundServiceBehavior(int)
     * @see #FOREGROUND_SERVICE_DEFAULT
     * @see #FOREGROUND_SERVICE_DEFERRED
     */
    public static final int FOREGROUND_SERVICE_IMMEDIATE = 1;

    /**
     * Constant for {@link Builder#setGroupAlertBehavior(int)}, meaning that all notifications in a
     * group with sound or vibration ought to make sound or vibrate (respectively), so this
     * notification will not be muted when it is in a group.
     */
    public static final int GROUP_ALERT_ALL = 0;

    /**
     * Constant for {@link Builder#setGroupAlertBehavior(int)}, meaning that the summary
     * notification in a group should be silenced (no sound or vibration) even if they are
     * posted to a {@link NotificationChannel} that has sound and/or vibration. Use this constant
     * to mute this notification if this notification is a group summary.
     *
     * <p>For example, you might want to use this constant if only the children notifications
     * in your group have content and the summary is only used to visually group notifications
     * rather than to alert the user that new information is available.
     */
    public static final int GROUP_ALERT_CHILDREN = 2;

    /**
     * Constant for {@link Builder#setGroupAlertBehavior(int)}, meaning that all children
     * notification in a group should be silenced (no sound or vibration) even if they are posted
     * to a {@link NotificationChannel} that has sound and/or vibration. Use this constant to
     * mute this notification if this notification is a group child. This must be applied to all
     * children notifications you want to mute.
     *
     * <p> For example, you might want to use this constant if you post a number of children
     * notifications at once (say, after a periodic sync), and only need to notify the user
     * audibly once.
     */
    public static final int GROUP_ALERT_SUMMARY = 1;

    /**
     * An activity that provides a user interface for adjusting notification preferences for its
     * containing application.
     */
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";

    /**
     * Default notification {@link #priority}. If your application does not prioritize its own
     * notifications, use this value for all notifications.
     *
     * @deprecated use {@link NotificationManager#IMPORTANCE_DEFAULT} instead.
     */
    @Deprecated
    public static final int PRIORITY_DEFAULT = 0;

    /**
     * Higher {@link #priority}, for more important notifications or alerts. The UI may choose to
     * show these items larger, or at a different position in notification lists, compared with
     * your app's {@link #PRIORITY_DEFAULT} items.
     *
     * @deprecated use {@link NotificationManager#IMPORTANCE_HIGH} instead.
     */
    @Deprecated
    public static final int PRIORITY_HIGH = 1;

    /**
     * Lower {@link #priority}, for items that are less important. The UI may choose to show these
     * items smaller, or at a different position in the list, compared with your app's
     * {@link #PRIORITY_DEFAULT} items.
     *
     * @deprecated use {@link NotificationManager#IMPORTANCE_LOW} instead.
     */
    @Deprecated
    public static final int PRIORITY_LOW = -1;

    /**
     * Highest {@link #priority}, for your application's most important items that require the
     * user's prompt attention or input.
     *
     * @deprecated use {@link NotificationManager#IMPORTANCE_HIGH} instead.
     */
    @Deprecated
    public static final int PRIORITY_MAX = 2;

    /**
     * Lowest {@link #priority}; these items might not be shown to the user except under special
     * circumstances, such as detailed notification logs.
     *
     * @deprecated use {@link NotificationManager#IMPORTANCE_MIN} instead.
     */
    @Deprecated
    public static final int PRIORITY_MIN = -2;

    /**
     * Use this constant as the value for audioStreamType to request that
     * the default stream type for notifications be used.  Currently the
     * default stream type is {@link AudioManager#STREAM_NOTIFICATION}.
     *
     * @deprecated Use {@link NotificationChannel#getAudioAttributes()} instead.
     */
    @Deprecated
    public static final int STREAM_DEFAULT = -1;

    /**
     * Notification visibility: Show this notification on all lockscreens, but conceal sensitive or
     * private information on secure lockscreens. Conceal sensitive or private information while
     * screen sharing.
     *
     * {@see #visibility}
     */
    public static final int VISIBILITY_PRIVATE = 0;

    /**
     * Notification visibility: Show this notification in its entirety on all lockscreens and while
     * screen sharing.
     *
     * {@see #visibility}
     */
    public static final int VISIBILITY_PUBLIC = 1;

    /**
     * Notification visibility: Do not reveal any part of this notification on a secure lockscreen
     * or while screen sharing.
     *
     * {@see #visibility}
     */
    public static final int VISIBILITY_SECRET = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Notification() {
    }

    /**
     * Structure to encapsulate a named action that can be shown as part of this notification.
     * It must include an icon, a label, and a {@link PendingIntent} to be fired when the action is
     * selected by the user.
     * <p>
     * Apps should use {@link Notification.Builder#addAction(int, CharSequence, PendingIntent)}
     * or {@link Notification.Builder#addAction(Notification.Action)}
     * to attach actions.
     * <p>
     * As of Android {@link android.os.Build.VERSION_CODES#S}, apps targeting API level {@link
     * android.os.Build.VERSION_CODES#S} or higher won't be able to start activities while
     * processing broadcast receivers or services in response to notification action clicks. To
     * launch an activity in those cases, provide a {@link PendingIntent} for the activity itself.
     */
    public static final class Action implements Parcelable {
        /**
         * {@code SemanticAction}: Archive the content associated with the notification. This
         * could mean archiving an email, message, etc.
         */
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;

        /**
         * {@code SemanticAction}: Call a contact, group, etc.
         */
        public static final int SEMANTIC_ACTION_CALL = 10;

        /**
         * {@code SemanticAction}: Delete the content associated with the notification. This
         * could mean deleting an email, message, etc.
         */
        public static final int SEMANTIC_ACTION_DELETE = 4;

        /**
         * {@code SemanticAction}: Mark content as read.
         */
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;

        /**
         * {@code SemanticAction}: Mark content as unread.
         */
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;

        /**
         * {@code SemanticAction}: Mute the content associated with the notification. This could
         * mean silencing a conversation or currently playing media.
         */
        public static final int SEMANTIC_ACTION_MUTE = 6;

        /**
         * No semantic action defined.
         */
        public static final int SEMANTIC_ACTION_NONE = 0;

        /**
         * {@code SemanticAction}: Reply to a conversation, chat, group, or wherever replies
         * may be appropriate.
         */
        public static final int SEMANTIC_ACTION_REPLY = 1;

        /**
         * {@code SemanticAction}: Mark content with a thumbs down.
         */
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;

        /**
         * {@code SemanticAction}: Mark content with a thumbs up.
         */
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;

        /**
         * {@code SemanticAction}: Unmute the content associated with the notification. This could
         * mean un-silencing a conversation or currently playing media.
         */
        public static final int SEMANTIC_ACTION_UNMUTE = 7;

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private Action() {
        }
    }

    /**
     * Helper class for generating large-format notifications that include a large image attachment.
     *
     * Here's how you'd set the <code>CallStyle</code> on a notification:
     * <pre class="prettyprint">
     * Notification notif = new Notification.Builder(mContext)
     *     .setSmallIcon(R.drawable.new_post)
     *     .setStyle(Notification.CallStyle.forIncomingCall(caller, declineIntent, answerIntent))
     *     .build();
     * </pre>
     */
    public static final class CallStyle {
        /**
         *  Call type for incoming calls.
         *
         *  See {@link #EXTRA_CALL_TYPE}.
         */
        public static final int CALL_TYPE_INCOMING = 1;

        /**
         * Call type for ongoing calls.
         *
         * See {@link #EXTRA_CALL_TYPE}.
         */
        public static final int CALL_TYPE_ONGOING = 2;

        /**
         * Call type for calls that are being screened.
         *
         * See {@link #EXTRA_CALL_TYPE}.
         */
        public static final int CALL_TYPE_SCREENING = 3;

        /**
         * Unknown call type.
         *
         * See {@link #EXTRA_CALL_TYPE}.
         */
        public static final int CALL_TYPE_UNKNOWN = 0;

        private CallStyle() {
        }
    }

    /**
     * Helper class for generating large-format notifications that include multiple back-and-forth
     * messages of varying types between any number of people.
     *
     * <p>
     * If the platform does not provide large-format notifications, this method has no effect. The
     * user will always see the normal notification view.
     *
     * <p>
     * If the app is targeting Android {@link android.os.Build.VERSION_CODES#P} and above, it is
     * required to use the {@link Person} class in order to get an optimal rendering of the
     * notification and its avatars. For conversations involving multiple people, the app should
     * also make sure that it marks the conversation as a group with
     * {@link #setGroupConversation(boolean)}.
     *
     * <p>
     * From Android {@link android.os.Build.VERSION_CODES#UPSIDE_DOWN_CAKE}, messaging style
     * notifications that are associated with a valid conversation shortcut
     * (via {@link Notification.Builder#setShortcutId(String)}) are displayed in a dedicated
     * conversation section in the shade above non-conversation alerting and silence notifications.
     * To be a valid conversation shortcut, the shortcut must be a
     * {@link ShortcutInfo.Builder#setLongLived(boolean)} dynamic or cached sharing shortcut.
     *
     * <p>
     * This class is a "rebuilder": It attaches to a Builder object and modifies its behavior.
     * Here's an example of how this may be used:
     * <pre class="prettyprint">
     *
     * Person user = new Person.Builder().setIcon(userIcon).setName(userName).build();
     * MessagingStyle style = new MessagingStyle(user)
     *      .addMessage(messages[1].getText(), messages[1].getTime(), messages[1].getPerson())
     *      .addMessage(messages[2].getText(), messages[2].getTime(), messages[2].getPerson())
     *      .setGroupConversation(hasMultiplePeople());
     *
     * Notification noti = new Notification.Builder()
     *     .setContentTitle(&quot;2 new messages with &quot; + sender.toString())
     *     .setContentText(subject)
     *     .setSmallIcon(R.drawable.new_message)
     *     .setLargeIcon(aBitmap)
     *     .setStyle(style)
     *     .build();
     * </pre>
     */
    public static final class MessagingStyle {
        /**
         * The maximum number of messages that will be retained in the Notification itself (the
         * number displayed is up to the platform).
         */
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;

        private MessagingStyle() {
        }
    }

    /**
     * Helper class to add wearable extensions to notifications.
     * <p class="note"> See
     * <a href="{@docRoot}wear/notifications/creating.html">Creating Notifications
     * for Android Wear</a> for more information on how to use this class.
     * <p>
     * To create a notification with wearable extensions:
     * <ol>
     *   <li>Create a {@link android.app.Notification.Builder}, setting any desired
     *   properties.
     *   <li>Create a {@link android.app.Notification.WearableExtender}.
     *   <li>Set wearable-specific properties using the
     *   {@code add} and {@code set} methods of {@link android.app.Notification.WearableExtender}.
     *   <li>Call {@link android.app.Notification.Builder#extend} to apply the extensions to a
     *   notification.
     *   <li>Post the notification to the notification system with the
     *   {@code NotificationManager.notify(...)} methods.
     * </ol>
     *
     * <pre class="prettyprint">
     * Notification notif = new Notification.Builder(mContext)
     *         .setContentTitle(&quot;New mail from &quot; + sender.toString())
     *         .setContentText(subject)
     *         .setSmallIcon(R.drawable.new_mail)
     *         .extend(new Notification.WearableExtender()
     *                 .setContentIcon(R.drawable.new_mail))
     *         .build();
     * NotificationManager notificationManger =
     *         (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
     * notificationManger.notify(0, notif);</pre>
     *
     * <p>Wearable extensions can be accessed on an existing notification by using the
     * {@code WearableExtender(Notification)} constructor,
     * and then using the {@code get} methods to access values.
     *
     * <pre class="prettyprint">
     * Notification.WearableExtender wearableExtender = new Notification.WearableExtender(
     *         notification);
     * List&lt;Notification&gt; pages = wearableExtender.getPages();</pre>
     */
    public static final class WearableExtender {
        /**
         * Sentinel value for use with {@link #setHintScreenTimeout} to keep the screen on
         * for a longer amount of time when this notification is displayed on the screen.
         *
         * @deprecated This feature is no longer supported.
         */
        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;

        /**
         * Sentinel value for use with {@link #setHintScreenTimeout} to keep the screen on for a
         * short amount of time when this notification is displayed on the screen. This
         * is the default value.
         *
         * @deprecated This feature is no longer supported.
         */
        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification with
         * default sizing.
         * <p>For custom display notifications created using {@link #setDisplayIntent},
         * the default is {@link #SIZE_MEDIUM}. All other notifications size automatically based
         * on their content.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_DEFAULT = 0;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification
         * full screen.
         * <p>This value is only applicable for custom display notifications created using
         * {@link #setDisplayIntent}.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification
         * with a large size.
         * <p>This value is only applicable for custom display notifications created using
         * {@link #setDisplayIntent}.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_LARGE = 4;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification
         * with a medium size.
         * <p>This value is only applicable for custom display notifications created using
         * {@link #setDisplayIntent}.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_MEDIUM = 3;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification
         * with a small size.
         * <p>This value is only applicable for custom display notifications created using
         * {@link #setDisplayIntent}.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_SMALL = 2;

        /**
         * Size value for use with {@link #setCustomSizePreset} to show this notification
         * with an extra small size.
         * <p>This value is only applicable for custom display notifications created using
         * {@link #setDisplayIntent}.
         *
         * @deprecated Display intents are no longer supported.
         */
        @Deprecated
        public static final int SIZE_XSMALL = 1;

        /**
         * Sentinel value for an action index that is unset.
         */
        public static final int UNSET_ACTION_INDEX = -1;

        private WearableExtender() {
        }
    }
}
