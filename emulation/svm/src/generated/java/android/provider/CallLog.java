package android.provider;

/**
 * The CallLog provider contains information about placed and received calls.
 */
public final class CallLog {
    public static final String AUTHORITY = "call_log";

    private CallLog() {
    }

    /**
     * Contains the recent calls.
     * <p>
     * Note: If you want to query the call log and limit the results to a single value, you should
     * append the {@link #LIMIT_PARAM_KEY} parameter to the content URI.  For example:
     * <pre>
     * {@code
     * getContentResolver().query(
     *                 Calls.CONTENT_URI.buildUpon().appendQueryParameter(LIMIT_PARAM_KEY, "1")
     *                 .build(),
     *                 null, null, null, null);
     * }
     * </pre>
     * <p>
     * The call log provider enforces strict SQL grammar, so you CANNOT append "LIMIT" to the SQL
     * query as below:
     * <pre>
     * {@code
     * getContentResolver().query(Calls.CONTENT_URI, null, "LIMIT 1", null, null);
     * }
     * </pre>
     */
    public static final class Calls implements BaseColumns {
        /**
         * Call log type for a call which was answered on another device.  Used in situations where
         * a call rings on multiple devices simultaneously and it ended up being answered on a
         * device other than the current one.
         */
        public static final int ANSWERED_EXTERNALLY_TYPE = 7;

        /**
         * String that stores the asserted display name associated with business call.
         */
        public static final String ASSERTED_DISPLAY_NAME = "asserted_display_name";

        /**
         * Value for {@link CallLog.Calls#MISSED_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#MISSED_TYPE} to indicate that a call was automatically rejected by
         * system because an ongoing emergency call.
         */
        public static final long AUTO_MISSED_EMERGENCY_CALL = 1L;

        /**
         * Value for {@link CallLog.Calls#MISSED_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#MISSED_TYPE} to indicate that a call was automatically rejected by
         * system because the system cannot support any more dialing calls.
         */
        public static final long AUTO_MISSED_MAXIMUM_DIALING = 4L;

        /**
         * Value for {@link CallLog.Calls#MISSED_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#MISSED_TYPE} to indicate that a call was automatically rejected by
         * system because the system cannot support any more ringing calls.
         */
        public static final long AUTO_MISSED_MAXIMUM_RINGING = 2L;

        /**
         * Call log type for calls blocked automatically.
         */
        public static final int BLOCKED_TYPE = 6;

        /**
         * Where the {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#BLOCKED_TYPE},
         * indicates the reason why a call is blocked.
         * <P>Type: INTEGER</P>
         *
         * <p>
         * Allowed values:
         * <ul>
         * <li>{@link CallLog.Calls#BLOCK_REASON_NOT_BLOCKED}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_CALL_SCREENING_SERVICE}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_DIRECT_TO_VOICEMAIL}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_BLOCKED_NUMBER}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_UNKNOWN_NUMBER}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_RESTRICTED_NUMBER}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_PAY_PHONE}</li>
         * <li>{@link CallLog.Calls#BLOCK_REASON_NOT_IN_CONTACTS}</li>
         * </ul>
         * </p>
         */
        public static final String BLOCK_REASON = "block_reason";

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because it is
         * in the BlockedNumbers provider.
         */
        public static final int BLOCK_REASON_BLOCKED_NUMBER = 3;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked by a
         * CallScreeningService. The {@link CallLog.Calls#CALL_SCREENING_COMPONENT_NAME} and
         * {@link CallLog.Calls#CALL_SCREENING_APP_NAME} columns will indicate which call screening
         * service was responsible for blocking the call.
         */
        public static final int BLOCK_REASON_CALL_SCREENING_SERVICE = 1;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because the user
         * configured a contact to be sent directly to voicemail.
         */
        public static final int BLOCK_REASON_DIRECT_TO_VOICEMAIL = 2;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set as the default value when a call was
         * not blocked by a CallScreeningService or any other system call blocking method.
         */
        public static final int BLOCK_REASON_NOT_BLOCKED = 0;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because the user
         * has chosen to block all calls from numbers not in their contacts.
         */
        public static final int BLOCK_REASON_NOT_IN_CONTACTS = 7;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because the user
         * has chosen to block all calls from pay phones.
         */
        public static final int BLOCK_REASON_PAY_PHONE = 6;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because the user
         * has chosen to block all calls from restricted numbers.
         */
        public static final int BLOCK_REASON_RESTRICTED_NUMBER = 5;

        /**
         * Value for {@link CallLog.Calls#BLOCK_REASON}, set when {@link CallLog.Calls#TYPE} is
         * {@link CallLog.Calls#BLOCKED_TYPE} to indicate that a call was blocked because the user
         * has chosen to block all calls from unknown numbers.
         */
        public static final int BLOCK_REASON_UNKNOWN_NUMBER = 4;

        /**
         * The cached phone number, formatted with formatting rules based on the country the
         * user was in when the call was made or received.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_FORMATTED_NUMBER = "formatted_number";

        /**
         * The cached URI to look up the contact associated with the phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_LOOKUP_URI = "lookup_uri";

        /**
         * The cached phone number of the contact which matches this entry, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_MATCHED_NUMBER = "matched_number";

        /**
         * The cached name associated with the phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_NAME = "name";

        /**
         * The cached normalized(E164) version of the phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_NORMALIZED_NUMBER = "normalized_number";

        /**
         * The cached number label, for a custom number type, associated with the
         * phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT</P>
         */
        public static final String CACHED_NUMBER_LABEL = "numberlabel";

        /**
         * The cached number type (Home, Work, etc) associated with the
         * phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: INTEGER</P>
         */
        public static final String CACHED_NUMBER_TYPE = "numbertype";

        /**
         * The cached photo id of the picture associated with the phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String CACHED_PHOTO_ID = "photo_id";

        /**
         * The cached photo URI of the picture associated with the phone number, if it exists.
         *
         * <p>This value is typically filled in by the dialer app for the caching purpose,
         * so it's not guaranteed to be present, and may not be current if the contact
         * information associated with this number has changed.
         * <P>Type: TEXT (URI)</P>
         */
        public static final String CACHED_PHOTO_URI = "photo_uri";

        /**
         * The name of the app which blocked a call. Will be populated when the
         * {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#BLOCKED_TYPE}. Provided as a
         * convenience so that the call log can still indicate which app blocked a call, even if
         * that app is no longer installed.
         * <P>Type: TEXT</P>
         */
        public static final String CALL_SCREENING_APP_NAME = "call_screening_app_name";

        /**
         * The ComponentName of the CallScreeningService which blocked this call. Will be
         * populated when the {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#BLOCKED_TYPE}.
         * <P>Type: TEXT</P>
         */
        public static final String CALL_SCREENING_COMPONENT_NAME = "call_screening_component_name";

        /**
         * A reference to the picture that was sent via call composer.
         *
         * The string contained in this field should be converted to an {@link Uri} via
         * {@link Uri#parse(String)}, then passed to {@link ContentResolver#openFileDescriptor}
         * in order to obtain a file descriptor to access the picture data.
         *
         * The user may choose to delete the picture associated with a call independently of the
         * call log entry, in which case {@link ContentResolver#openFileDescriptor} may throw a
         * {@link FileNotFoundException}.
         *
         * Note that pictures sent or received via call composer will not be included in any
         * backups of the call log.
         *
         * <p>Type: TEXT</p>
         */
        public static final String COMPOSER_PHOTO_URI = "composer_photo_uri";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * call.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/calls";

        /**
         * The MIME type of {@link #CONTENT_URI} and {@link #CONTENT_FILTER_URI}
         * providing a directory of calls.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/calls";

        /**
         * The ISO 3166-1 two letters country code of the country where the
         * user received or made the call.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String COUNTRY_ISO = "countryiso";

        /**
         * The data usage of the call in bytes.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATA_USAGE = "data_usage";

        /**
         * The date the call occured, in milliseconds since the epoch
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE = "date";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "date DESC";

        /**
         * The duration of the call in seconds
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DURATION = "duration";

        /**
         * An optional extra used with {@link #CONTENT_TYPE Calls.CONTENT_TYPE} and
         * {@link Intent#ACTION_VIEW} to specify that the presented list of calls should be
         * filtered for a particular call type.
         *
         * Applications implementing a call log UI should check for this extra, and display a
         * filtered list of calls based on the specified call type. If not applicable within the
         * application's UI, it should be silently ignored.
         *
         * <p>
         * The following example brings up the call log, showing only missed calls.
         * <pre>
         * Intent intent = new Intent(Intent.ACTION_VIEW);
         * intent.setType(CallLog.Calls.CONTENT_TYPE);
         * intent.putExtra(CallLog.Calls.EXTRA_CALL_TYPE_FILTER, CallLog.Calls.MISSED_TYPE);
         * startActivity(intent);
         * </pre>
         * </p>
         */
        public static final String EXTRA_CALL_TYPE_FILTER = "android.provider.extra.CALL_TYPE_FILTER";

        /**
         * Bit-mask describing features of the call (e.g. video).
         *
         * <P>Type: INTEGER (int)</P>
         */
        public static final String FEATURES = "features";

        /**
         * Indicates the call underwent Assisted Dialing.
         * @see TelecomManager#EXTRA_USE_ASSISTED_DIALING
         */
        public static final int FEATURES_ASSISTED_DIALING_USED = 16;

        /**
         * Call was HD.
         */
        public static final int FEATURES_HD_CALL = 4;

        /**
         * Call was pulled externally.
         */
        public static final int FEATURES_PULLED_EXTERNALLY = 2;

        /**
         * Call was on RTT at some point
         */
        public static final int FEATURES_RTT = 32;

        /**
         * Call had video.
         */
        public static final int FEATURES_VIDEO = 1;

        /**
         * Call was VoLTE
         */
        public static final int FEATURES_VOLTE = 64;

        /**
         * Call was WIFI call.
         */
        public static final int FEATURES_WIFI = 8;

        /**
         * A geocoded location for the number associated with this call.
         * <p>
         * The string represents a city, state, or country associated with the number.
         * <P>Type: TEXT</P>
         */
        public static final String GEOCODED_LOCATION = "geocoded_location";

        /**
         * Call log type for incoming calls.
         */
        public static final int INCOMING_TYPE = 1;

        /**
         * Boolean indicating whether the call is a business call.
         */
        public static final String IS_BUSINESS_CALL = "is_business_call";

        /**
         * Whether this item has been read or otherwise consumed by the user.
         * <p>
         * Unlike the {@link #NEW} field, which requires the user to have acknowledged the
         * existence of the entry, this implies the user has interacted with the entry.
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String IS_READ = "is_read";

        /**
         * The date the row is last inserted, updated, or marked as deleted, in milliseconds
         * since the epoch. Read only.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String LAST_MODIFIED = "last_modified";

        /**
         * Query parameter used to limit the number of call logs returned.
         * <p>
         * TYPE: integer
         */
        public static final String LIMIT_PARAM_KEY = "limit";

        /**
         * A reference to the location that was sent via call composer.
         *
         * This column contains the content URI of the corresponding entry in {@link Locations}
         * table, which contains the actual location data. The
         * {@link Manifest.permission#ACCESS_FINE_LOCATION} permission is required to access that
         * table.
         *
         * If your app has the appropriate permissions, the location data may be obtained by
         * converting the value of this column to an {@link Uri} via {@link Uri#parse}, then passing
         * the result to {@link ContentResolver#query}.
         *
         * The user may choose to delete the location associated with a call independently of the
         * call log entry, in which case the {@link Cursor} returned from
         * {@link ContentResolver#query} will either be {@code null} or empty, with
         * {@link Cursor#getCount()} returning {@code 0}.
         *
         * This column will not be populated when a call is received while the device is locked, and
         * it will not be part of any backups.
         *
         * <p>Type: TEXT</p>
         */
        public static final String LOCATION = "location";

        /**
         * Where the {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE},
         * indicates factors which may have lead the user to miss the call.
         * <P>Type: INTEGER</P>
         *
         * <p>
         * There are two main cases. Auto missed cases and user missed cases. Default value is:
         * <ul>
         * <li>{@link CallLog.Calls#MISSED_REASON_NOT_MISSED}</li>
         * </ul>
         * </p>
         * <P>
         * Auto missed cases are those where a call was missed because it was not possible for the
         * incoming call to be presented to the user at all. Possible values are:
         * <ul>
         * <li>{@link CallLog.Calls#AUTO_MISSED_EMERGENCY_CALL}</li>
         * <li>{@link CallLog.Calls#AUTO_MISSED_MAXIMUM_RINGING}</li>
         * <li>{@link CallLog.Calls#AUTO_MISSED_MAXIMUM_DIALING}</li>
         * </ul>
         * </P>
         * <P>
         * User missed cases are those where the incoming call was presented to the user, but
         * factors such as a low ringing volume may have contributed to the call being missed.
         * Following bits can be set to indicate possible reasons for this:
         * <ul>
         * <li>{@link CallLog.Calls#USER_MISSED_SHORT_RING}</li>
         * <li>{@link CallLog.Calls#USER_MISSED_DND_MODE}</li>
         * <li>{@link CallLog.Calls#USER_MISSED_LOW_RING_VOLUME}</li>
         * <li>{@link CallLog.Calls#USER_MISSED_NO_VIBRATE}</li>
         * <li>{@link CallLog.Calls#USER_MISSED_CALL_SCREENING_SERVICE_SILENCED}</li>
         * <li>{@link CallLog.Calls#USER_MISSED_CALL_FILTERS_TIMEOUT}</li>
         * </ul>
         * </P>
         */
        public static final String MISSED_REASON = "missed_reason";

        /**
         * Value for {@link CallLog.Calls#MISSED_REASON}, set as the default value when a call was
         * not missed.
         */
        public static final long MISSED_REASON_NOT_MISSED = 0L;

        /**
         * Call log type for missed calls.
         */
        public static final int MISSED_TYPE = 3;

        /**
         * Whether or not the call has been acknowledged
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String NEW = "new";

        /**
         * The phone number as the user entered it.
         * <P>Type: TEXT</P>
         */
        public static final String NUMBER = "number";

        /**
         * The number presenting rules set by the network.
         *
         * <p>
         * Allowed values:
         * <ul>
         * <li>{@link #PRESENTATION_ALLOWED}</li>
         * <li>{@link #PRESENTATION_RESTRICTED}</li>
         * <li>{@link #PRESENTATION_UNKNOWN}</li>
         * <li>{@link #PRESENTATION_PAYPHONE}</li>
         * <li>{@link #PRESENTATION_UNAVAILABLE}</li>
         * </ul>
         * </p>
         *
         * <P>Type: INTEGER</P>
         */
        public static final String NUMBER_PRESENTATION = "presentation";

        /**
         * Query parameter used to specify the starting record to return.
         * <p>
         * TYPE: integer
         */
        public static final String OFFSET_PARAM_KEY = "offset";

        /**
         * Call log type for outgoing calls.
         */
        public static final int OUTGOING_TYPE = 2;

        /**
         * The component name of the account used to place or receive the call; in string form.
         * <P>Type: TEXT</P>
         */
        public static final String PHONE_ACCOUNT_COMPONENT_NAME = "subscription_component_name";

        /**
         * The identifier for the account used to place or receive the call.
         * <P>Type: TEXT</P>
         */
        public static final String PHONE_ACCOUNT_ID = "subscription_id";

        /**
         * The post-dial portion of a dialed number, including any digits dialed after a
         * {@link TelecomManager#DTMF_CHARACTER_PAUSE} or a {@link
         * TelecomManager#DTMF_CHARACTER_WAIT} and these characters themselves.
         * <P>Type: TEXT</P>
         */
        public static final String POST_DIAL_DIGITS = "post_dial_digits";

        /**
         * Number is allowed to display for caller id.
         */
        public static final int PRESENTATION_ALLOWED = 1;

        /**
         * Number is a pay phone.
         */
        public static final int PRESENTATION_PAYPHONE = 4;

        /**
         * Number is blocked by user.
         */
        public static final int PRESENTATION_RESTRICTED = 2;

        /**
         * Number is unavailable.
         */
        public static final int PRESENTATION_UNAVAILABLE = 5;

        /**
         * Number is not specified or unknown by network.
         */
        public static final int PRESENTATION_UNKNOWN = 3;

        /**
         * The priority of the call, as delivered via call composer.
         *
         * For outgoing calls, contains the priority set by the local user. For incoming calls,
         * contains the priority set by the remote caller. If no priority was set or the call
         * did not include call composer elements, defaults to {@link #PRIORITY_NORMAL}.
         * Valid values are {@link #PRIORITY_NORMAL} and {@link #PRIORITY_URGENT}.
         * <p>Type: INTEGER</p>
         */
        public static final String PRIORITY = "priority";

        /**
         * Used as a value in the {@link #PRIORITY} column.
         *
         * Indicates that the call is of normal priority. This is also the default value for calls
         * that did not include call composer elements.
         */
        public static final int PRIORITY_NORMAL = 0;

        /**
         * Used as a value in the {@link #PRIORITY} column.
         *
         * Indicates that the call is of urgent priority.
         */
        public static final int PRIORITY_URGENT = 1;

        /**
         * Call log type for calls rejected by direct user action.
         */
        public static final int REJECTED_TYPE = 5;

        /**
         * The subject of the call, as delivered via call composer.
         *
         * For outgoing calls, contains the subject set by the local user. For incoming calls,
         * contains the subject set by the remote caller. May be null if no subject was set.
         * <p>Type: TEXT</p>
         */
        public static final String SUBJECT = "subject";

        /**
         * Transcription of the call or voicemail entry. This will only be populated for call log
         * entries of type {@link #VOICEMAIL_TYPE} that have valid transcriptions.
         */
        public static final String TRANSCRIPTION = "transcription";

        /**
         * The type of the call (incoming, outgoing or missed).
         * <P>Type: INTEGER (int)</P>
         *
         * <p>
         * Allowed values:
         * <ul>
         * <li>{@link #INCOMING_TYPE}</li>
         * <li>{@link #OUTGOING_TYPE}</li>
         * <li>{@link #MISSED_TYPE}</li>
         * <li>{@link #VOICEMAIL_TYPE}</li>
         * <li>{@link #REJECTED_TYPE}</li>
         * <li>{@link #BLOCKED_TYPE}</li>
         * <li>{@link #ANSWERED_EXTERNALLY_TYPE}</li>
         * </ul>
         * </p>
         */
        public static final String TYPE = "type";

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * the call filters timed out.
         */
        public static final long USER_MISSED_CALL_FILTERS_TIMEOUT = 4194304L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * this call is silenced by the call screening service.
         */
        public static final long USER_MISSED_CALL_SCREENING_SERVICE_SILENCED = 2097152L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * this call is silenced because the phone is in 'do not disturb mode'.
         */
        public static final long USER_MISSED_DND_MODE = 262144L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * this call rings with a low ring volume.
         */
        public static final long USER_MISSED_LOW_RING_VOLUME = 524288L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * the call was missed just because user didn't answer it.
         */
        public static final long USER_MISSED_NO_ANSWER = 65536L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE} set this bit when
         * this call rings without vibration.
         */
        public static final long USER_MISSED_NO_VIBRATE = 1048576L;

        /**
         * When {@link CallLog.Calls#TYPE} is {@link CallLog.Calls#MISSED_TYPE}, set this bit when
         * this call rang for a short period of time.
         */
        public static final long USER_MISSED_SHORT_RING = 131072L;

        /**
         * For an incoming call, the secondary line number the call was received via.
         * When a SIM card has multiple phone numbers associated with it, the via number indicates
         * which of the numbers associated with the SIM was called.
         */
        public static final String VIA_NUMBER = "via_number";

        /**
         * Call log type for voicemails.
         */
        public static final int VOICEMAIL_TYPE = 4;

        /**
         * URI of the voicemail entry. Populated only for {@link #VOICEMAIL_TYPE}.
         * <P>Type: TEXT</P>
         */
        public static final String VOICEMAIL_URI = "voicemail_uri";

        private Calls() {
        }
    }

    /**
     * Table that contains information on location data sent via call composer.
     *
     * All fields in this table require the {@link Manifest.permission#ACCESS_FINE_LOCATION}
     * permission for access.
     */
    public static final class Locations implements BaseColumns {
        /**
         * Authority for the locations content provider.
         */
        public static final String AUTHORITY = "call_composer_locations";

        /**
         * Content type for the location entries.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/call_composer_location";

        /**
         * Content type for the location table.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/call_composer_location";

        /**
         * Latitude in degrees. See {@link android.location.Location#setLatitude(double)}.
         * <p>Type: REAL</p>
         */
        public static final String LATITUDE = "latitude";

        /**
         * Longitude in degrees. See {@link android.location.Location#setLongitude(double)}.
         * <p>Type: REAL</p>
         */
        public static final String LONGITUDE = "longitude";

        private Locations() {
        }
    }
}
