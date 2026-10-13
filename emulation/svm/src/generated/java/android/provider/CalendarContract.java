package android.provider;

/**
 * <p>
 * The contract between the calendar provider and applications. Contains
 * definitions for the supported URIs and data columns.
 * </p>
 * <h3>Overview</h3>
 * <p>
 * CalendarContract defines the data model of calendar and event related
 * information. This data is stored in a number of tables:
 * </p>
 * <ul>
 * <li>The {@link Calendars} table holds the calendar specific information. Each
 * row in this table contains the details for a single calendar, such as the
 * name, color, sync info, etc.</li>
 * <li>The {@link Events} table holds the event specific information. Each row
 * in this table has the info for a single event. It contains information such
 * as event title, location, start time, end time, etc. The event can occur
 * one-time or can recur multiple times. Attendees, reminders, and extended
 * properties are stored on separate tables and reference the {@link Events#_ID}
 * to link them with the event.</li>
 * <li>The {@link Instances} table holds the start and end time for occurrences
 * of an event. Each row in this table represents a single occurrence. For
 * one-time events there will be a 1:1 mapping of instances to events. For
 * recurring events, multiple rows will automatically be generated which
 * correspond to multiple occurrences of that event.</li>
 * <li>The {@link Attendees} table holds the event attendee or guest
 * information. Each row represents a single guest of an event. It specifies the
 * type of guest they are and their attendance response for the event.</li>
 * <li>The {@link Reminders} table holds the alert/notification data. Each row
 * represents a single alert for an event. An event can have multiple reminders.
 * The number of reminders per event is specified in
 * {@link Calendars#MAX_REMINDERS} which is set by the Sync Adapter that owns
 * the given calendar. Reminders are specified in minutes before the event and
 * have a type.</li>
 * <li>The {@link ExtendedProperties} table holds opaque data fields used by the
 * sync adapter. The provider takes no action with items in this table except to
 * delete them when their related events are deleted.</li>
 * </ul>
 * <p>
 * Other tables include:
 * </p>
 * <ul>
 * <li>
 * {@link SyncState}, which contains free-form data maintained by the sync
 * adapters</li>
 * </ul>
 */
public final class CalendarContract {
    /**
     * A special account type for calendars not associated with any account.
     * Normally calendars that do not match an account on the device will be
     * removed. Setting the account_type on a calendar to this will prevent it
     * from being wiped if it does not match an existing account.
     *
     * @see SyncColumns#ACCOUNT_TYPE
     */
    public static final String ACCOUNT_TYPE_LOCAL = "LOCAL";

    /**
     * Broadcast Action: This is the intent that gets fired when an alarm
     * notification needs to be posted for a reminder.
     */
    public static final String ACTION_EVENT_REMINDER = "android.intent.action.EVENT_REMINDER";

    /**
     * Activity Action: Display the event to the user in the custom app as
     * specified in {@link EventsColumns#CUSTOM_APP_PACKAGE}. The custom app
     * will be started via {@link Activity#startActivityForResult(Intent, int)}
     * and it should call {@link Activity#setResult(int)} with
     * {@link Activity#RESULT_OK} or {@link Activity#RESULT_CANCELED} to
     * acknowledge whether the action was handled or not.
     *
     * The custom app should have an intent filter like the following:
     * <pre>
     * &lt;intent-filter&gt;
     *    &lt;action android:name="android.provider.calendar.action.HANDLE_CUSTOM_EVENT" /&gt;
     *    &lt;category android:name="android.intent.category.DEFAULT" /&gt;
     *    &lt;data android:mimeType="vnd.android.cursor.item/event" /&gt;
     * &lt;/intent-filter&gt;</pre>
     * <p>
     * Input: {@link Intent#getData} has the event URI. The extra
     * {@link #EXTRA_EVENT_BEGIN_TIME} has the start time of the instance. The
     * extra {@link #EXTRA_CUSTOM_APP_URI} will have the
     * {@link EventsColumns#CUSTOM_APP_URI}.
     * <p>
     * Output: {@link Activity#RESULT_OK} if this was handled; otherwise
     * {@link Activity#RESULT_CANCELED}.
     */
    public static final String ACTION_HANDLE_CUSTOM_EVENT = "android.provider.calendar.action.HANDLE_CUSTOM_EVENT";

    /**
     * Action used to help apps show calendar events in the managed profile.
     */
    public static final String ACTION_VIEW_MANAGED_PROFILE_CALENDAR_EVENT = "android.provider.calendar.action.VIEW_MANAGED_PROFILE_CALENDAR_EVENT";

    /**
     * This authority is used for writing to or querying from the calendar
     * provider. Note: This is set at first run and cannot be changed without
     * breaking apps that access the provider.
     */
    public static final String AUTHORITY = "com.android.calendar";

    /**
     * An optional insert, update or delete URI parameter that allows the caller
     * to specify that it is a sync adapter. The default value is false. If set
     * to true, the modified row is not marked as "dirty" (needs to be synced)
     * and when the provider calls
     * {@link ContentResolver#notifyChange(android.net.Uri, android.database.ContentObserver, boolean)}
     * , the third parameter "syncToNetwork" is set to false. Furthermore, if
     * set to true, the caller must also include
     * {@link Calendars#ACCOUNT_NAME} and {@link Calendars#ACCOUNT_TYPE} as
     * query parameters.
     *
     * @see Uri.Builder#appendQueryParameter(java.lang.String, java.lang.String)
     */
    public static final String CALLER_IS_SYNCADAPTER = "caller_is_syncadapter";

    /**
     * Intent Extras key: {@link EventsColumns#CUSTOM_APP_URI} for the event in
     * the {@link #ACTION_HANDLE_CUSTOM_EVENT} intent
     */
    public static final String EXTRA_CUSTOM_APP_URI = "customAppUri";

    /**
     * Intent Extras key: When creating an event, set this to true to create an
     * all-day event by default
     */
    public static final String EXTRA_EVENT_ALL_DAY = "allDay";

    /**
     * Intent Extras key: The start time of an event or an instance of a
     * recurring event. (milliseconds since epoch)
     */
    public static final String EXTRA_EVENT_BEGIN_TIME = "beginTime";

    /**
     * Intent Extras key: The end time of an event or an instance of a recurring
     * event. (milliseconds since epoch)
     */
    public static final String EXTRA_EVENT_END_TIME = "endTime";

    /**
     * Intent Extras key: An extra of type {@code long} holding the id of an event.
     */
    public static final String EXTRA_EVENT_ID = "id";

    private CalendarContract() {
    }

    /**
     * Columns from the Attendees table that other tables join into themselves.
     */
    protected interface AttendeesColumns {
        /**
         * The email address of the attendee. Column name.
         * <P>Type: STRING</P>
         */
        String ATTENDEE_EMAIL = "attendeeEmail";

        /**
         * The identity of the attendee as referenced in
         * {@link ContactsContract.CommonDataKinds.Identity#IDENTITY}.
         * This is required only if {@link #ATTENDEE_ID_NAMESPACE} is present. Column name.
         * <P>Type: STRING</P>
         */
        String ATTENDEE_IDENTITY = "attendeeIdentity";

        /**
         * The identity name space of the attendee as referenced in
         * {@link ContactsContract.CommonDataKinds.Identity#NAMESPACE}.
         * This is required only if {@link #ATTENDEE_IDENTITY} is present. Column name.
         * <P>Type: STRING</P>
         */
        String ATTENDEE_ID_NAMESPACE = "attendeeIdNamespace";

        /**
         * The name of the attendee. Column name.
         * <P>Type: STRING</P>
         */
        String ATTENDEE_NAME = "attendeeName";

        /**
         * The relationship of the attendee to the user. Column name.
         * <P>Type: INTEGER (one of {@link #RELATIONSHIP_ATTENDEE}, ...}.</P>
         */
        String ATTENDEE_RELATIONSHIP = "attendeeRelationship";

        /**
         * The attendance status of the attendee. Column name.
         * <P>Type: Integer (one of {@link #ATTENDEE_STATUS_ACCEPTED}, ...).</P>
         */
        String ATTENDEE_STATUS = "attendeeStatus";

        int ATTENDEE_STATUS_ACCEPTED = 1;

        int ATTENDEE_STATUS_DECLINED = 2;

        int ATTENDEE_STATUS_INVITED = 3;

        int ATTENDEE_STATUS_NONE = 0;

        int ATTENDEE_STATUS_TENTATIVE = 4;

        /**
         * The type of attendee. Column name.
         * <P>
         * Type: Integer (one of {@link #TYPE_NONE}, {@link #TYPE_REQUIRED},
         * {@link #TYPE_OPTIONAL}, {@link #TYPE_RESOURCE})
         * </P>
         */
        String ATTENDEE_TYPE = "attendeeType";

        /**
         * The id of the event. Column name.
         * <P>Type: INTEGER</P>
         */
        String EVENT_ID = "event_id";

        int RELATIONSHIP_ATTENDEE = 1;

        int RELATIONSHIP_NONE = 0;

        int RELATIONSHIP_ORGANIZER = 2;

        int RELATIONSHIP_PERFORMER = 3;

        int RELATIONSHIP_SPEAKER = 4;

        int TYPE_NONE = 0;

        int TYPE_OPTIONAL = 2;

        int TYPE_REQUIRED = 1;

        /**
         * This specifies that an attendee is a resource, like a room, a
         * cabbage, or something and not an actual person.
         */
        int TYPE_RESOURCE = 3;
    }

    protected interface CalendarAlertsColumns {
        /**
         * The alarm time of the event, in UTC. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String ALARM_TIME = "alarmTime";

        /**
         * The start time of the event, in UTC. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String BEGIN = "begin";

        /**
         * The creation time of this database entry, in UTC.
         * Useful for debugging missed reminders. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String CREATION_TIME = "creationTime";

        /**
         * The default sort order for this alerts queries
         */
        String DEFAULT_SORT_ORDER = "begin ASC,title ASC";

        /**
         * The end time of the event, in UTC. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String END = "end";

        /**
         * The event that the alert belongs to. Column name.
         * <P>Type: INTEGER (foreign key to the Events table)</P>
         */
        String EVENT_ID = "event_id";

        /**
         * The number of minutes that this alarm precedes the start time. Column
         * name.
         * <P>Type: INTEGER</P>
         */
        String MINUTES = "minutes";

        /**
         * The time that the notification was created by the Calendar app,
         * in UTC. Useful for debugging missed reminders. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String NOTIFY_TIME = "notifyTime";

        /**
         * The time that the alarm broadcast was received by the Calendar app,
         * in UTC. Useful for debugging missed reminders. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String RECEIVED_TIME = "receivedTime";

        /**
         * The state of this alert. It starts out as {@link #STATE_SCHEDULED}, then
         * when the alarm goes off, it changes to {@link #STATE_FIRED}, and then when
         * the user dismisses the alarm it changes to {@link #STATE_DISMISSED}. Column
         * name.
         * <P>Type: INTEGER</P>
         */
        String STATE = "state";

        /**
         * Once the user has dismissed the notification the alert's state should
         * be set to dismissed so it is not fired again.
         */
        int STATE_DISMISSED = 2;

        /**
         * After a notification for an alert has been created it should be
         * updated to fired.
         */
        int STATE_FIRED = 1;

        /**
         * An alert begins in this state when it is first created.
         */
        int STATE_SCHEDULED = 0;
    }

    /**
     * CalendarCache stores some settings for calendar including the current
     * time zone for the instances. These settings are stored using a key/value
     * scheme. A {@link #KEY} must be specified when updating these values.
     */
    public static final class CalendarCache implements CalendarCacheColumns {
        /**
         * The key for updating the time zone used by the provider when it
         * generates the instances table. This should only be written if the
         * type is set to {@link #TIMEZONE_TYPE_HOME}. A valid time zone id
         * should be written to this field.
         */
        public static final String KEY_TIMEZONE_INSTANCES = "timezoneInstances";

        /**
         * The key for reading the last time zone set by the user. This should
         * only be read by apps and it will be automatically updated whenever
         * {@link #KEY_TIMEZONE_INSTANCES} is updated with
         * {@link #TIMEZONE_TYPE_HOME} set.
         */
        public static final String KEY_TIMEZONE_INSTANCES_PREVIOUS = "timezoneInstancesPrevious";

        /**
         * They key for updating the use of auto/home time zones in Calendar.
         * Valid values are {@link #TIMEZONE_TYPE_AUTO} or
         * {@link #TIMEZONE_TYPE_HOME}.
         */
        public static final String KEY_TIMEZONE_TYPE = "timezoneType";

        /**
         * The value to write to {@link #KEY_TIMEZONE_TYPE} if the provider
         * should stay in sync with the device's time zone.
         */
        public static final String TIMEZONE_TYPE_AUTO = "auto";

        /**
         * The value to write to {@link #KEY_TIMEZONE_TYPE} if the provider
         * should use a fixed time zone set by the user.
         */
        public static final String TIMEZONE_TYPE_HOME = "home";

        private CalendarCache() {
        }
    }

    protected interface CalendarCacheColumns {
        /**
         * The key for the setting. Keys are defined in {@link CalendarCache}.
         */
        String KEY = "key";

        /**
         * The value of the given setting.
         */
        String VALUE = "value";
    }

    /**
     * Columns specific to the Calendars Uri that other Uris can query.
     */
    protected interface CalendarColumns {
        /**
         * A comma separated list of attendee types supported for this calendar
         * in the format "#,#,#". Valid types are {@link Attendees#TYPE_NONE},
         * {@link Attendees#TYPE_OPTIONAL}, {@link Attendees#TYPE_REQUIRED},
         * {@link Attendees#TYPE_RESOURCE}. Setting this field to only
         * {@link Attendees#TYPE_NONE} should be used to indicate that changing
         * the attendee type is not supported.
         */
        String ALLOWED_ATTENDEE_TYPES = "allowedAttendeeTypes";

        /**
         * A comma separated list of availability types supported for this
         * calendar in the format "#,#,#". Valid types are
         * {@link Events#AVAILABILITY_BUSY}, {@link Events#AVAILABILITY_FREE},
         * {@link Events#AVAILABILITY_TENTATIVE}. Setting this field to only
         * {@link Events#AVAILABILITY_BUSY} should be used to indicate that
         * changing the availability is not supported.
         */
        String ALLOWED_AVAILABILITY = "allowedAvailability";

        /**
         * A comma separated list of reminder methods supported for this
         * calendar in the format "#,#,#". Valid types are
         * {@link Reminders#METHOD_DEFAULT}, {@link Reminders#METHOD_ALERT},
         * {@link Reminders#METHOD_EMAIL}, {@link Reminders#METHOD_SMS},
         * {@link Reminders#METHOD_ALARM}. Column name.
         * <P>Type: TEXT</P>
         */
        String ALLOWED_REMINDERS = "allowedReminders";

        /**
         * The level of access that the user has for the calendar
         * <P>Type: INTEGER (one of the values below)</P>
         */
        String CALENDAR_ACCESS_LEVEL = "calendar_access_level";

        /**
         * The color of the calendar. This should only be updated by the sync
         * adapter, not other apps, as changing a calendar's color can adversely
         * affect its display.
         * <P>Type: INTEGER (color value)</P>
         */
        String CALENDAR_COLOR = "calendar_color";

        /**
         * A key for looking up a color from the {@link Colors} table. NULL or
         * an empty string are reserved for indicating that the calendar does
         * not use a key for looking up the color. The provider will update
         * {@link #CALENDAR_COLOR} automatically when a valid key is written to
         * this column. The key must reference an existing row of the
         * {@link Colors} table. @see Colors
         * <P>
         * Type: TEXT
         * </P>
         */
        String CALENDAR_COLOR_KEY = "calendar_color_index";

        /**
         * The display name of the calendar. Column name.
         * <P>
         * Type: TEXT
         * </P>
         */
        String CALENDAR_DISPLAY_NAME = "calendar_displayName";

        /**
         * The time zone the calendar is associated with.
         * <P>Type: TEXT</P>
         */
        String CALENDAR_TIME_ZONE = "calendar_timezone";

        /**
         * Full access to modify the calendar, but not the access control
         *  settings
         */
        int CAL_ACCESS_CONTRIBUTOR = 500;

        /**
         * Full access to modify the calendar, but not the access control
         *  settings
         */
        int CAL_ACCESS_EDITOR = 600;

        /**
         * Can only see free/busy information about the calendar
         */
        int CAL_ACCESS_FREEBUSY = 100;

        /**
         * Cannot access the calendar
         */
        int CAL_ACCESS_NONE = 0;

        /**
         * not used
         */
        int CAL_ACCESS_OVERRIDE = 400;

        /**
         * Full access to the calendar
         */
        int CAL_ACCESS_OWNER = 700;

        /**
         * Can read all event details
         */
        int CAL_ACCESS_READ = 200;

        /**
         * Can reply yes/no/maybe to an event
         */
        int CAL_ACCESS_RESPOND = 300;

        /**
         * Domain admin
         */
        int CAL_ACCESS_ROOT = 800;

        /**
         * Can the organizer modify the time zone of the event? Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String CAN_MODIFY_TIME_ZONE = "canModifyTimeZone";

        /**
         * Can the organizer respond to the event?  If no, the status of the
         * organizer should not be shown by the UI.  Defaults to 1. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String CAN_ORGANIZER_RESPOND = "canOrganizerRespond";

        /**
         * Is this the primary calendar for this account. If this column is not explicitly set, the
         * provider will return 1 if {@link Calendars#ACCOUNT_NAME} is equal to
         * {@link Calendars#OWNER_ACCOUNT}.
         */
        String IS_PRIMARY = "isPrimary";

        /**
         * The maximum number of reminders allowed for an event. Column name.
         * <P>Type: INTEGER</P>
         */
        String MAX_REMINDERS = "maxReminders";

        /**
         * The owner account for this calendar, based on the calendar feed.
         * This will be different from the _SYNC_ACCOUNT for delegated calendars.
         * Column name.
         * <P>Type: String</P>
         */
        String OWNER_ACCOUNT = "ownerAccount";

        /**
         * Is this calendar synced and are its events stored on the device?
         * 0 - Do not sync this calendar or store events for this calendar.
         * 1 - Sync down events for this calendar.
         * <p>Type: INTEGER (boolean)</p>
         */
        String SYNC_EVENTS = "sync_events";

        /**
         * Is the calendar selected to be displayed?
         * 0 - do not show events associated with this calendar.
         * 1 - show events associated with this calendar
         * <P>Type: INTEGER (boolean)</P>
         */
        String VISIBLE = "visible";
    }

    /**
     * Generic columns for use by sync adapters. The specific functions of these
     * columns are private to the sync adapter. Other clients of the API should
     * not attempt to either read or write this column. These columns are
     * editable as part of the Calendars Uri, but can only be read if accessed
     * through any other Uri.
     */
    protected interface CalendarSyncColumns {
        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC1 = "cal_sync1";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC10 = "cal_sync10";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC2 = "cal_sync2";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC3 = "cal_sync3";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC4 = "cal_sync4";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC5 = "cal_sync5";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC6 = "cal_sync6";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC7 = "cal_sync7";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC8 = "cal_sync8";

        /**
         * Generic column for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String CAL_SYNC9 = "cal_sync9";
    }

    /**
     * Constants and helpers for the Calendars table, which contains details for
     * individual calendars. <h3>Operations</h3> All operations can be done
     * either as an app or as a sync adapter. To perform an operation as a sync
     * adapter {@link #CALLER_IS_SYNCADAPTER} should be set to true and
     * {@link #ACCOUNT_NAME} and {@link #ACCOUNT_TYPE} must be set in the Uri
     * parameters. See
     * {@link Uri.Builder#appendQueryParameter(java.lang.String, java.lang.String)}
     * for details on adding parameters. Sync adapters have write access to more
     * columns but are restricted to a single account at a time. Calendars are
     * designed to be primarily managed by a sync adapter and inserting new
     * calendars should be done as a sync adapter. For the most part, apps
     * should only update calendars (such as changing the color or display
     * name). If a local calendar is required an app can do so by inserting as a
     * sync adapter and using an {@link #ACCOUNT_TYPE} of
     * {@link #ACCOUNT_TYPE_LOCAL} .
     * <dl>
     * <dt><b>Insert</b></dt>
     * <dd>When inserting a new calendar the following fields must be included:
     * <ul>
     * <li>{@link #ACCOUNT_NAME}</li>
     * <li>{@link #ACCOUNT_TYPE}</li>
     * <li>{@link #NAME}</li>
     * <li>{@link #CALENDAR_DISPLAY_NAME}</li>
     * <li>{@link #CALENDAR_COLOR}</li>
     * <li>{@link #CALENDAR_ACCESS_LEVEL}</li>
     * <li>{@link #OWNER_ACCOUNT}</li>
     * </ul>
     * The following fields are not required when inserting a Calendar but are
     * generally a good idea to include:
     * <ul>
     * <li>{@link #SYNC_EVENTS} set to 1</li>
     * <li>{@link #CALENDAR_TIME_ZONE}</li>
     * <li>{@link #ALLOWED_REMINDERS}</li>
     * <li>{@link #ALLOWED_AVAILABILITY}</li>
     * <li>{@link #ALLOWED_ATTENDEE_TYPES}</li>
     * </ul>
     * <dt><b>Update</b></dt>
     * <dd>To perform an update on a calendar the {@link #_ID} of the calendar
     * should be provided either as an appended id to the Uri (
     * {@link ContentUris#withAppendedId}) or as the first selection item--the
     * selection should start with "_id=?" and the first selectionArg should be
     * the _id of the calendar. Calendars may also be updated using a selection
     * without the id. In general, the {@link #ACCOUNT_NAME} and
     * {@link #ACCOUNT_TYPE} should not be changed after a calendar is created
     * as this can cause issues for sync adapters.
     * <dt><b>Delete</b></dt>
     * <dd>Calendars can be deleted either by the {@link #_ID} as an appended id
     * on the Uri or using any standard selection. Deleting a calendar should
     * generally be handled by a sync adapter as it will remove the calendar
     * from the database and all associated data (aka events).</dd>
     * <dt><b>Query</b></dt>
     * <dd>Querying the Calendars table will get you all information about a set
     * of calendars. There will be one row returned for each calendar that
     * matches the query selection, or at most a single row if the {@link #_ID}
     * is appended to the Uri.</dd>
     * </dl>
     * <h3>Calendar Columns</h3> The following Calendar columns are writable by
     * both an app and a sync adapter.
     * <ul>
     * <li>{@link #NAME}</li>
     * <li>{@link #CALENDAR_DISPLAY_NAME}</li>
     * <li>{@link #VISIBLE}</li>
     * <li>{@link #SYNC_EVENTS}</li>
     * </ul>
     * The following Calendars columns are writable only by a sync adapter
     * <ul>
     * <li>{@link #ACCOUNT_NAME}</li>
     * <li>{@link #ACCOUNT_TYPE}</li>
     * <li>{@link #CALENDAR_COLOR}</li>
     * <li>{@link #_SYNC_ID}</li>
     * <li>{@link #DIRTY}</li>
     * <li>{@link #MUTATORS}</li>
     * <li>{@link #OWNER_ACCOUNT}</li>
     * <li>{@link #MAX_REMINDERS}</li>
     * <li>{@link #ALLOWED_REMINDERS}</li>
     * <li>{@link #ALLOWED_AVAILABILITY}</li>
     * <li>{@link #ALLOWED_ATTENDEE_TYPES}</li>
     * <li>{@link #CAN_MODIFY_TIME_ZONE}</li>
     * <li>{@link #CAN_ORGANIZER_RESPOND}</li>
     * <li>{@link #CAN_PARTIALLY_UPDATE}</li>
     * <li>{@link #CALENDAR_LOCATION}</li>
     * <li>{@link #CALENDAR_TIME_ZONE}</li>
     * <li>{@link #CALENDAR_ACCESS_LEVEL}</li>
     * <li>{@link #DELETED}</li>
     * <li>{@link #CAL_SYNC1}</li>
     * <li>{@link #CAL_SYNC2}</li>
     * <li>{@link #CAL_SYNC3}</li>
     * <li>{@link #CAL_SYNC4}</li>
     * <li>{@link #CAL_SYNC5}</li>
     * <li>{@link #CAL_SYNC6}</li>
     * <li>{@link #CAL_SYNC7}</li>
     * <li>{@link #CAL_SYNC8}</li>
     * <li>{@link #CAL_SYNC9}</li>
     * <li>{@link #CAL_SYNC10}</li>
     * </ul>
     */
    public static final class Calendars implements BaseColumns, CalendarColumns, SyncColumns {
        /**
         * The default location for the calendar. Column name.
         * <P>Type: TEXT</P>
         */
        public static final String CALENDAR_LOCATION = "calendar_location";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "calendar_displayName";

        /**
         * The name of the calendar. Column name.
         * <P>Type: TEXT</P>
         */
        public static final String NAME = "name";

        private Calendars() {
        }
    }

    protected interface ColorsColumns extends SyncStateContract.Columns {
        /**
         * The color as an 8-bit ARGB integer value. Colors should specify alpha
         * as fully opaque (eg 0xFF993322) as the alpha may be ignored or
         * modified for display. It is reccomended that colors be usable with
         * light (near white) text. Apps should not depend on that assumption,
         * however. Column name.
         * <P>
         * Type: INTEGER (NOT NULL)
         * </P>
         */
        String COLOR = "color";

        /**
         * The key used to reference this color. This can be any non-empty
         * string, but must be unique for a given {@link #ACCOUNT_TYPE} and
         * {@link #ACCOUNT_NAME}. Column name.
         * <P>
         * Type: TEXT
         * </P>
         */
        String COLOR_KEY = "color_index";

        /**
         * The type of color, which describes how it should be used. Valid types
         * are {@link #TYPE_CALENDAR} and {@link #TYPE_EVENT}. Column name.
         * <P>
         * Type: INTEGER (NOT NULL)
         * </P>
         */
        String COLOR_TYPE = "color_type";

        /**
         * This indicateds a color that can be used for calendars.
         */
        int TYPE_CALENDAR = 0;

        /**
         * This indicates a color that can be used for events.
         */
        int TYPE_EVENT = 1;
    }

    protected interface EventDaysColumns {
        /**
         * The Julian ending day number. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        String ENDDAY = "endDay";

        /**
         * The Julian starting day number. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        String STARTDAY = "startDay";
    }

    /**
     * Columns from the Events table that other tables join into themselves.
     */
    protected interface EventsColumns {
        /**
         * Confidential is not used by the app.
         */
        int ACCESS_CONFIDENTIAL = 1;

        /**
         * Default access is controlled by the server and will be treated as
         * public on the device.
         */
        int ACCESS_DEFAULT = 0;

        /**
         * Defines how the event shows up for others when the calendar is
         * shared. Column name.
         * <P>Type: INTEGER (One of {@link #ACCESS_DEFAULT}, ...)</P>
         */
        String ACCESS_LEVEL = "accessLevel";

        /**
         * Private shares the event as a free/busy slot with no details.
         */
        int ACCESS_PRIVATE = 2;

        /**
         * Public makes the contents visible to anyone with access to the
         * calendar.
         */
        int ACCESS_PUBLIC = 3;

        /**
         * Is the event all day (time zone independent). Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String ALL_DAY = "allDay";

        /**
         * If this event counts as busy time or is still free time that can be
         * scheduled over. Column name.
         * <P>
         * Type: INTEGER (One of {@link #AVAILABILITY_BUSY},
         * {@link #AVAILABILITY_FREE}, {@link #AVAILABILITY_TENTATIVE})
         * </P>
         */
        String AVAILABILITY = "availability";

        /**
         * Indicates that this event takes up time and will conflict with other
         * events.
         */
        int AVAILABILITY_BUSY = 0;

        /**
         * Indicates that this event is free time and will not conflict with
         * other events.
         */
        int AVAILABILITY_FREE = 1;

        /**
         * Indicates that the owner's availability may change, but should be
         * considered busy time that will conflict.
         */
        int AVAILABILITY_TENTATIVE = 2;

        /**
         * The {@link Calendars#_ID} of the calendar the event belongs to.
         * Column name.
         * <P>Type: INTEGER</P>
         */
        String CALENDAR_ID = "calendar_id";

        /**
         * Whether the user can invite others to the event. The
         * GUESTS_CAN_INVITE_OTHERS is a setting that applies to an arbitrary
         * guest, while CAN_INVITE_OTHERS indicates if the user can invite
         * others (either through GUESTS_CAN_INVITE_OTHERS or because the user
         * has modify access to the event). Column name.
         * <P>Type: INTEGER (boolean, readonly)</P>
         */
        String CAN_INVITE_OTHERS = "canInviteOthers";

        /**
         * The package name of the custom app that can provide a richer
         * experience for the event. See the ACTION TYPE
         * {@link CalendarContract#ACTION_HANDLE_CUSTOM_EVENT} for details.
         * Column name.
         * <P> Type: TEXT </P>
         */
        String CUSTOM_APP_PACKAGE = "customAppPackage";

        /**
         * The URI used by the custom app for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String CUSTOM_APP_URI = "customAppUri";

        /**
         * The description of the event. Column name.
         * <P>Type: TEXT</P>
         */
        String DESCRIPTION = "description";

        /**
         *  This will be {@link #EVENT_COLOR} if it is not null; otherwise, this will be
         *  {@link Calendars#CALENDAR_COLOR}.
         *  Read-only value. To modify, write to {@link #EVENT_COLOR} or
         *  {@link Calendars#CALENDAR_COLOR} directly.
         * <P>
         *      Type: INTEGER
         * </P>
         */
        String DISPLAY_COLOR = "displayColor";

        /**
         * The time the event ends in UTC millis since epoch. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String DTEND = "dtend";

        /**
         * The time the event starts in UTC millis since epoch. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String DTSTART = "dtstart";

        /**
         * The duration of the event in RFC2445 format. Column name.
         * <P>Type: TEXT (duration in RFC2445 format)</P>
         */
        String DURATION = "duration";

        /**
         * A secondary color for the individual event. This should only be
         * updated by the sync adapter for a given account.
         * <P>Type: INTEGER</P>
         */
        String EVENT_COLOR = "eventColor";

        /**
         * A secondary color key for the individual event. NULL or an empty
         * string are reserved for indicating that the event does not use a key
         * for looking up the color. The provider will update
         * {@link #EVENT_COLOR} automatically when a valid key is written to
         * this column. The key must reference an existing row of the
         * {@link Colors} table. @see Colors
         * <P>
         * Type: TEXT
         * </P>
         */
        String EVENT_COLOR_KEY = "eventColor_index";

        /**
         * The timezone for the end time of the event. Column name.
         * <P>Type: TEXT</P>
         */
        String EVENT_END_TIMEZONE = "eventEndTimezone";

        /**
         * Where the event takes place. Column name.
         * <P>Type: TEXT</P>
         */
        String EVENT_LOCATION = "eventLocation";

        /**
         * The timezone for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String EVENT_TIMEZONE = "eventTimezone";

        /**
         * The recurrence exception dates for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String EXDATE = "exdate";

        /**
         * The recurrence exception rule for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String EXRULE = "exrule";

        /**
         * Whether guests can invite other guests. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String GUESTS_CAN_INVITE_OTHERS = "guestsCanInviteOthers";

        /**
         * Whether guests can modify the event. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String GUESTS_CAN_MODIFY = "guestsCanModify";

        /**
         * Whether guests can see the list of attendees. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String GUESTS_CAN_SEE_GUESTS = "guestsCanSeeGuests";

        /**
         * Whether the event has an alarm or not. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String HAS_ALARM = "hasAlarm";

        /**
         * Whether the event has attendee information.  True if the event
         * has full attendee data, false if the event has information about
         * self only. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String HAS_ATTENDEE_DATA = "hasAttendeeData";

        /**
         * Whether the event has extended properties or not. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String HAS_EXTENDED_PROPERTIES = "hasExtendedProperties";

        /**
         * Are we the organizer of this event. If this column is not explicitly set, the provider
         * will return 1 if {@link #ORGANIZER} is equal to {@link Calendars#OWNER_ACCOUNT}.
         * Column name.
         * <P>Type: STRING</P>
         */
        String IS_ORGANIZER = "isOrganizer";

        /**
         * The last date this event repeats on, or NULL if it never ends. Column
         * name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String LAST_DATE = "lastDate";

        /**
         * Used to indicate that a row is not a real event but an original copy of a locally
         * modified event. A copy is made when an event changes from non-dirty to dirty and the
         * event is on a calendar with {@link Calendars#CAN_PARTIALLY_UPDATE} set to 1. This copy
         * does not get expanded in the instances table and is only visible in queries made by a
         * sync adapter. The copy gets removed when the event is changed back to non-dirty by a
         * sync adapter.
         * <P>Type: INTEGER (boolean)</P>
         */
        String LAST_SYNCED = "lastSynced";

        /**
         * Email of the organizer (owner) of the event. Column name.
         * <P>Type: STRING</P>
         */
        String ORGANIZER = "organizer";

        /**
         * The allDay status (true or false) of the original recurring event
         * for which this event is an exception. Column name.
         * <P>Type: INTEGER (boolean)</P>
         */
        String ORIGINAL_ALL_DAY = "originalAllDay";

        /**
         * The {@link Events#_ID} of the original recurring event for which this
         * event is an exception. Column name.
         * <P>Type: TEXT</P>
         */
        String ORIGINAL_ID = "original_id";

        /**
         * The original instance time of the recurring event for which this
         * event is an exception. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        String ORIGINAL_INSTANCE_TIME = "originalInstanceTime";

        /**
         * The _sync_id of the original recurring event for which this event is
         * an exception. The provider should keep the original_id in sync when
         * this is updated. Column name.
         * <P>Type: TEXT</P>
         */
        String ORIGINAL_SYNC_ID = "original_sync_id";

        /**
         * The recurrence dates for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String RDATE = "rdate";

        /**
         * The recurrence rule for the event. Column name.
         * <P>Type: TEXT</P>
         */
        String RRULE = "rrule";

        /**
         * This is a copy of the attendee status for the owner of this event.
         * This field is copied here so that we can efficiently filter out
         * events that are declined without having to look in the Attendees
         * table. Column name.
         *
         * <P>Type: INTEGER (int)</P>
         */
        String SELF_ATTENDEE_STATUS = "selfAttendeeStatus";

        /**
         * The event status. Column name.
         * <P>Type: INTEGER (one of {@link #STATUS_TENTATIVE}...)</P>
         */
        String STATUS = "eventStatus";

        int STATUS_CANCELED = 2;

        int STATUS_CONFIRMED = 1;

        int STATUS_TENTATIVE = 0;

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA1 = "sync_data1";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA10 = "sync_data10";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA2 = "sync_data2";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA3 = "sync_data3";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA4 = "sync_data4";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA5 = "sync_data5";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA6 = "sync_data6";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA7 = "sync_data7";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA8 = "sync_data8";

        /**
         * This column is available for use by sync adapters. Column name.
         * <P>Type: TEXT</P>
         */
        String SYNC_DATA9 = "sync_data9";

        /**
         * The title of the event. Column name.
         * <P>Type: TEXT</P>
         */
        String TITLE = "title";

        /**
         * The UID for events added from the RFC 2445 iCalendar format.
         * Column name.
         * <P>Type: TEXT</P>
         */
        String UID_2445 = "uid2445";
    }

    protected interface ExtendedPropertiesColumns {
        /**
         * The event the extended property belongs to. Column name.
         * <P>Type: INTEGER (foreign key to the Events table)</P>
         */
        String EVENT_ID = "event_id";

        /**
         * The name of the extended property.  This is a uri of the form
         * {scheme}#{local-name} convention. Column name.
         * <P>Type: TEXT</P>
         */
        String NAME = "name";

        /**
         * The value of the extended property. Column name.
         * <P>Type: TEXT</P>
         */
        String VALUE = "value";
    }

    /**
     * Fields and helpers for interacting with Instances. An instance is a
     * single occurrence of an event including time zone specific start and end
     * days and minutes. The instances table is not writable and only provides a
     * way to query event occurrences.
     */
    public static final class Instances implements BaseColumns, CalendarColumns, EventsColumns {
        /**
         * The beginning time of the instance, in UTC milliseconds. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        public static final String BEGIN = "begin";

        /**
         * The ending time of the instance, in UTC milliseconds. Column name.
         * <P>Type: INTEGER (long; millis since epoch)</P>
         */
        public static final String END = "end";

        /**
         * The Julian end day of the instance, relative to the local time
         * zone. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        public static final String END_DAY = "endDay";

        /**
         * The end minute of the instance measured from midnight in the
         * local time zone. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        public static final String END_MINUTE = "endMinute";

        /**
         * The _id of the event for this instance. Column name.
         * <P>Type: INTEGER (long, foreign key to the Events table)</P>
         */
        public static final String EVENT_ID = "event_id";

        /**
         * The Julian start day of the instance, relative to the local time
         * zone. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        public static final String START_DAY = "startDay";

        /**
         * The start minute of the instance measured from midnight in the
         * local time zone. Column name.
         * <P>Type: INTEGER (int)</P>
         */
        public static final String START_MINUTE = "startMinute";

        private Instances() {
        }
    }

    protected interface RemindersColumns {
        /**
         * The event the reminder belongs to. Column name.
         * <P>Type: INTEGER (foreign key to the Events table)</P>
         */
        String EVENT_ID = "event_id";

        /**
         * The alarm method, as set on the server. {@link #METHOD_DEFAULT},
         * {@link #METHOD_ALERT}, {@link #METHOD_EMAIL}, {@link #METHOD_SMS} and
         * {@link #METHOD_ALARM} are possible values; the device will only
         * process {@link #METHOD_DEFAULT} and {@link #METHOD_ALERT} reminders
         * (the other types are simply stored so we can send the same reminder
         * info back to the server when we make changes).
         */
        String METHOD = "method";

        int METHOD_ALARM = 4;

        int METHOD_ALERT = 1;

        int METHOD_DEFAULT = 0;

        int METHOD_EMAIL = 2;

        int METHOD_SMS = 3;

        /**
         * The minutes prior to the event that the alarm should ring.  -1
         * specifies that we should use the default value for the system.
         * Column name.
         * <P>Type: INTEGER</P>
         */
        String MINUTES = "minutes";

        /**
         * Passing this as a minutes value will use the default reminder
         * minutes.
         */
        int MINUTES_DEFAULT = -1;
    }

    /**
     * Columns for Sync information used by Calendars and Events tables. These
     * have specific uses which are expected to be consistent by the app and
     * sync adapter.
     */
    protected interface SyncColumns extends CalendarSyncColumns {
        /**
         * The account that was used to sync the entry to the device. If the
         * account_type is not {@link #ACCOUNT_TYPE_LOCAL} then the name and
         * type must match an account on the device or the calendar will be
         * deleted.
         * <P>Type: TEXT</P>
         */
        String ACCOUNT_NAME = "account_name";

        /**
         * The type of the account that was used to sync the entry to the
         * device. A type of {@link #ACCOUNT_TYPE_LOCAL} will keep this event
         * form being deleted if there are no matching accounts on the device.
         * <P>Type: TEXT</P>
         */
        String ACCOUNT_TYPE = "account_type";

        /**
         * If set to 1 this causes events on this calendar to be duplicated with
         * {@link Events#LAST_SYNCED} set to 1 whenever the event
         * transitions from non-dirty to dirty. The duplicated event will not be
         * expanded in the instances table and will only show up in sync adapter
         * queries of the events table. It will also be deleted when the
         * originating event has its dirty flag cleared by the sync adapter.
         * <P>Type: INTEGER (boolean)</P>
         */
        String CAN_PARTIALLY_UPDATE = "canPartiallyUpdate";

        /**
         * Whether the row has been deleted but not synced to the server. A
         * deleted row should be ignored.
         * <P>
         * Type: INTEGER (boolean)
         * </P>
         */
        String DELETED = "deleted";

        /**
         * Used to indicate that local, unsynced, changes are present.
         * <P>Type: INTEGER (long)</P>
         */
        String DIRTY = "dirty";

        /**
         * Used in conjunction with {@link #DIRTY} to indicate what packages wrote local changes.
         * <P>Type: TEXT</P>
         */
        String MUTATORS = "mutators";

        /**
         * The unique ID for a row assigned by the sync source. NULL if the row
         * has never been synced. This is used as a reference id for exceptions
         * along with {@link BaseColumns#_ID}.
         * <P>Type: TEXT</P>
         */
        String _SYNC_ID = "_sync_id";
    }
}
