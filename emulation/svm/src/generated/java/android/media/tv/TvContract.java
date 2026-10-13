package android.media.tv;

import android.provider.BaseColumns;

/**
 * The contract between the TV provider and applications. Contains definitions for the supported
 * URIs and columns.
 * <h3>Overview</h3>
 *
 * <p>TvContract defines a basic database of TV content metadata such as channel and program
 * information. The information is stored in {@link Channels} and {@link Programs} tables.
 *
 * <ul>
 *     <li>A row in the {@link Channels} table represents information about a TV channel. The data
 *         format can vary greatly from standard to standard or according to service provider, thus
 *         the columns here are mostly comprised of basic entities that are usually seen to users
 *         regardless of standard such as channel number and name.</li>
 *     <li>A row in the {@link Programs} table represents a set of data describing a TV program such
 *         as program title and start time.</li>
 * </ul>
 */
public final class TvContract {
    /**
     * Broadcast Action: sent to the target TV input after it is first installed to notify the input
     * to initialize its channels and programs to the system content provider.
     *
     * <p>Note that this intent is sent only on devices with
     * {@link android.content.pm.PackageManager#FEATURE_LEANBACK} enabled. Besides that, in order
     * to receive this intent, the target TV input must:
     * <ul>
     *     <li>Declare a broadcast receiver for this intent in its
     *         <code>AndroidManifest.xml</code>.</li>
     *     <li>Declare appropriate permissions to write channel and program data in its
     *         <code>AndroidManifest.xml</code>.</li>
     * </ul>
     */
    public static final String ACTION_INITIALIZE_PROGRAMS = "android.media.tv.action.INITIALIZE_PROGRAMS";

    /**
     * Broadcast Action: sent by the system to tell the target TV input that one of its existing
     * preview programs is added to the watch next programs table by user.
     *
     * <p>The intent must contain the following bundle parameters:
     * <ul>
     *     <li>{@link #EXTRA_PREVIEW_PROGRAM_ID}: the ID of the existing preview program.</li>
     *     <li>{@link #EXTRA_WATCH_NEXT_PROGRAM_ID}: the ID of the new watch next program.</li>
     * </ul>
     */
    public static final String ACTION_PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT = "android.media.tv.action.PREVIEW_PROGRAM_ADDED_TO_WATCH_NEXT";

    /**
     * Broadcast Action: sent by the system to tell the target TV input that one of its preview
     * program's browsable state is disabled, i.e., it will no longer be shown to users, which, for
     * example, might be a result of users' interaction with UI. The input is expected to delete the
     * preview program from the content provider.
     *
     * <p>The intent must contain the following bundle parameter:
     * <ul>
     *     <li>{@link #EXTRA_PREVIEW_PROGRAM_ID}: the disabled preview program ID.</li>
     * </ul>
     */
    public static final String ACTION_PREVIEW_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.PREVIEW_PROGRAM_BROWSABLE_DISABLED";

    /**
     * Activity Action: sent by an application telling the system to make the given channel
     * browsable with user interaction. The system may show UI to ask user to approve the channel.
     * This is only relevant to channels with {@link Channels#TYPE_PREVIEW} type. Use
     * {@link Activity#startActivityForResult} to get the result of the request.
     *
     * <p>The intent must contain the following bundle parameters:
     * <ul>
     *     <li>{@link #EXTRA_CHANNEL_ID}: ID for the {@link Channels#TYPE_PREVIEW} channel as a long
     *     integer.</li>
     * </ul>
     */
    public static final String ACTION_REQUEST_CHANNEL_BROWSABLE = "android.media.tv.action.REQUEST_CHANNEL_BROWSABLE";

    /**
     * Broadcast Action: sent by the system to tell the target TV input that one of its "watch next"
     * program's browsable state is disabled, i.e., it will no longer be shown to users, which, for
     * example, might be a result of users' interaction with UI. The input is expected to delete the
     * "watch next" program from the content provider.
     *
     * <p>The intent must contain the following bundle parameter:
     * <ul>
     *     <li>{@link #EXTRA_WATCH_NEXT_PROGRAM_ID}: the disabled "watch next" program ID.</li>
     * </ul>
     */
    public static final String ACTION_WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED = "android.media.tv.action.WATCH_NEXT_PROGRAM_BROWSABLE_DISABLED";

    /**
     * The authority for the TV provider.
     */
    public static final String AUTHORITY = "android.media.tv";

    /**
     * The key for a bundle parameter containing a channel ID as a long integer
     */
    public static final String EXTRA_CHANNEL_ID = "android.media.tv.extra.CHANNEL_ID";

    /**
     * The key for a bundle parameter containing a program ID as a long integer.
     */
    public static final String EXTRA_PREVIEW_PROGRAM_ID = "android.media.tv.extra.PREVIEW_PROGRAM_ID";

    /**
     * The key for a bundle parameter containing a watch next program ID as a long integer.
     */
    public static final String EXTRA_WATCH_NEXT_PROGRAM_ID = "android.media.tv.extra.WATCH_NEXT_PROGRAM_ID";

    private TvContract() {
    }

    /**
     * Common base for the tables of TV channels/programs.
     */
    public interface BaseTvColumns extends BaseColumns {
        /**
         * The name of the package that owns the current row.
         *
         * <p>The TV provider fills in this column with the name of the package that provides the
         * initial data of the row. If the package is later uninstalled, the rows it owns are
         * automatically removed from the tables.
         *
         * <p>Type: TEXT
         */
        String COLUMN_PACKAGE_NAME = "package_name";
    }

    /**
     * Column definitions for the TV channels table.
     */
    public static final class Channels implements BaseTvColumns {
        /**
         * The broadcast visibility type for invisible services. Use this type when the service
         * is invisible from users and not able to be selected by users via any of the normal
         * service navigation mechanisms.
         *
         * @see #COLUMN_BROADCAST_VISIBILITY_TYPE
         */
        public static final int BROADCAST_VISIBILITY_TYPE_INVISIBLE = 2;

        /**
         * The broadcast visibility type for numeric selectable only services. Use this type when
         * the service is invisible from users but selectable by users only via direct entry of
         * the logical channel number.
         *
         * @see #COLUMN_BROADCAST_VISIBILITY_TYPE
         */
        public static final int BROADCAST_VISIBILITY_TYPE_NUMERIC_SELECTABLE_ONLY = 1;

        /**
         * The broadcast visibility type for visible services. Use this type when the service is
         * visible from users and selectable by users via normal service navigation mechanisms.
         *
         * @see #COLUMN_BROADCAST_VISIBILITY_TYPE
         */
        public static final int BROADCAST_VISIBILITY_TYPE_VISIBLE = 0;

        /**
         * The accent color of the app link template for this channel. This is primarily used for
         * the background color of the text box in the template.
         *
         * <p>The app-linking allows channel input sources to provide activity links from their live
         * channel programming to another activity. This enables content providers to increase user
         * engagement by offering the viewer other content or actions.
         *
         * <p>Type: INTEGER (color value)
         * @see #COLUMN_APP_LINK_ICON_URI
         * @see #COLUMN_APP_LINK_INTENT_URI
         * @see #COLUMN_APP_LINK_POSTER_ART_URI
         * @see #COLUMN_APP_LINK_TEXT
         */
        public static final String COLUMN_APP_LINK_COLOR = "app_link_color";

        /**
         * The URI for the app badge icon of the app link template for this channel.
         *
         * <p>This small icon is overlaid at the bottom of the poster art specified by
         * {@link #COLUMN_APP_LINK_POSTER_ART_URI}. The data in the column must be a URI in one of
         * the following formats:
         *
         * <ul>
         * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
         * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})
         * </li>
         * <li>file ({@link android.content.ContentResolver#SCHEME_FILE})</li>
         * </ul>
         *
         * <p>The app-linking allows channel input sources to provide activity links from their live
         * channel programming to another activity. This enables content providers to increase user
         * engagement by offering the viewer other content or actions.
         *
         * <p>Type: TEXT
         * @see #COLUMN_APP_LINK_COLOR
         * @see #COLUMN_APP_LINK_INTENT_URI
         * @see #COLUMN_APP_LINK_POSTER_ART_URI
         * @see #COLUMN_APP_LINK_TEXT
         */
        public static final String COLUMN_APP_LINK_ICON_URI = "app_link_icon_uri";

        /**
         * The intent URI of the app link for this channel.
         *
         * <p>The URI is created using {@link Intent#toUri} with {@link Intent#URI_INTENT_SCHEME}
         * and converted back to the original intent with {@link Intent#parseUri}. The intent is
         * launched when the user clicks the corresponding app link for the current channel.
         *
         * <p>The app-linking allows channel input sources to provide activity links from their live
         * channel programming to another activity. This enables content providers to increase user
         * engagement by offering the viewer other content or actions.
         *
         * <p>Type: TEXT
         * @see #COLUMN_APP_LINK_COLOR
         * @see #COLUMN_APP_LINK_ICON_URI
         * @see #COLUMN_APP_LINK_POSTER_ART_URI
         * @see #COLUMN_APP_LINK_TEXT
         */
        public static final String COLUMN_APP_LINK_INTENT_URI = "app_link_intent_uri";

        /**
         * The URI for the poster art used as the background of the app link template for this
         * channel.
         *
         * <p>The data in the column must be a URL, or a URI in one of the following formats:
         *
         * <ul>
         * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
         * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})
         * </li>
         * <li>file ({@link android.content.ContentResolver#SCHEME_FILE})</li>
         * </ul>
         *
         * <p>The app-linking allows channel input sources to provide activity links from their live
         * channel programming to another activity. This enables content providers to increase user
         * engagement by offering the viewer other content or actions.
         *
         * <p>Type: TEXT
         * @see #COLUMN_APP_LINK_COLOR
         * @see #COLUMN_APP_LINK_ICON_URI
         * @see #COLUMN_APP_LINK_INTENT_URI
         * @see #COLUMN_APP_LINK_TEXT
         */
        public static final String COLUMN_APP_LINK_POSTER_ART_URI = "app_link_poster_art_uri";

        /**
         * The link text of the app link template for this channel.
         *
         * <p>This provides a short description of the action that happens when the corresponding
         * app link is clicked.
         *
         * <p>The app-linking allows channel input sources to provide activity links from their live
         * channel programming to another activity. This enables content providers to increase user
         * engagement by offering the viewer other content or actions.
         *
         * <p>Type: TEXT
         * @see #COLUMN_APP_LINK_COLOR
         * @see #COLUMN_APP_LINK_ICON_URI
         * @see #COLUMN_APP_LINK_INTENT_URI
         * @see #COLUMN_APP_LINK_POSTER_ART_URI
         */
        public static final String COLUMN_APP_LINK_TEXT = "app_link_text";

        /**
         * The comma-separated genre string of this TV channel.
         *
         * <p>Use the same language appeared in the underlying broadcast standard, if applicable.
         * Otherwise, leave empty. Use
         * {@link Genres#encode Genres.encode()} to create a text that can be stored in this column.
         * Use {@link Genres#decode Genres.decode()} to get the broadcast genre strings from the
         * text stored in the column.
         *
         * <p>Type: TEXT
         * @see Programs#COLUMN_BROADCAST_GENRE
         */
        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";

        /**
         * The broadcast visibility type of this TV channel.
         *
         * <p>This is used to indicate the broadcast visibility type defined in the underlying
         * broadcast standard or country/operator profile, if applicable. For example,
         * {@code visible_service_flag} and {@code numeric_selection_flag} of
         * {@code service_attribute_descriptor} in D-Book, the specification for UK-based TV
         * products, {@code visible_service_flag} and {@code selectable_service_flag} of
         * {@code ciplus_service_descriptor} in the CI Plus 1.3 specification.
         *
         * <p>The value should match one of the following:
         * {@link #BROADCAST_VISIBILITY_TYPE_VISIBLE},
         * {@link #BROADCAST_VISIBILITY_TYPE_NUMERIC_SELECTABLE_ONLY}, and
         * {@link #BROADCAST_VISIBILITY_TYPE_INVISIBLE}.
         *
         * <p>If not specified, this value is set to {@link #BROADCAST_VISIBILITY_TYPE_VISIBLE}
         * by default.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_BROADCAST_VISIBILITY_TYPE = "broadcast_visibility_type";

        /**
         * The flag indicating whether this TV channel is browsable or not.
         *
         * <p>This column can only be set by applications having proper system permission. For
         * other applications, this is a read-only column.
         *
         * <p>A value of 1 indicates the channel is included in the channel list that applications
         * use to browse channels, a value of 0 indicates the channel is not included in the list.
         * If not specified, this value is set to 0 (not browsable) by default.
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_BROWSABLE = "browsable";

        /**
         * The channel list ID of this TV channel.
         *
         * <p>It is used to identify the channel list constructed from broadcast SI based on the
         * underlying broadcast standard or country/operator profile, if applicable. Otherwise,
         * leave empty.
         *
         * <p>The ID can be defined by individual TV input services. For example, one may assign a
         * service operator name for the service operator channel list constructed from broadcast
         * SI or one may assign the {@code profile_name} of the operator_info() APDU defined in CI
         * Plus 1.3 for the dedicated CICAM operator profile channel list constructed
         * from CICAM NIT.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_CHANNEL_LIST_ID = "channel_list_id";

        /**
         * The description of this TV channel.
         *
         * <p>Can be empty initially.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_DESCRIPTION = "description";

        /**
         * The channel name that is displayed to the user.
         *
         * <p>A call sign is a good candidate to use for this purpose but any name that helps the
         * user recognize the current channel will be enough. Can also be empty depending on
         * broadcast standard.
         *
         * <p> Type: TEXT
         */
        public static final String COLUMN_DISPLAY_NAME = "display_name";

        /**
         * The channel number that is displayed to the user.
         *
         * <p>The format can vary depending on broadcast standard and product specification.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_DISPLAY_NUMBER = "display_number";

        /**
         * The global content ID of this TV channel, as a URI.
         *
         * <p>A globally unique URI that identifies this TV channel, if applicable. Suitable URIs
         * include
         * <ul>
         * <li>{@code globalServiceId} from ATSC A/331. ex {@code https://doi.org/10.5239/7E4E-B472}
         * <li>Other broadcast ID provider. ex {@code http://example.com/tv_channel/1234}
         * </ul>
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_GLOBAL_CONTENT_ID = "global_content_id";

        /**
         * The ID of the TV input service that provides this TV channel.
         *
         * <p>Use {@link #buildInputId} to build the ID.
         *
         * <p>This is a required field.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_INPUT_ID = "input_id";

        /**
         * Internal data used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Type: BLOB
         */
        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";

        /**
         * Internal integer flag used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";

        /**
         * Internal integer flag used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";

        /**
         * Internal integer flag used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";

        /**
         * Internal integer flag used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";

        /**
         * The internal ID used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";

        /**
         * The flag indicating whether this TV channel is locked or not.
         *
         * <p>This is primarily used for alternative parental control to prevent unauthorized users
         * from watching the current channel regardless of the content rating. A value of 1
         * indicates the channel is locked and the user is required to enter passcode to unlock it
         * in order to watch the current program from the channel, a value of 0 indicates the
         * channel is not locked thus the user is not prompted to enter passcode If not specified,
         * this value is set to 0 (not locked) by default.
         *
         * <p>This column can only be set by applications having proper system permission to
         * modify parental control settings. For other applications, this is a read-only column.
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_LOCKED = "locked";

        /**
         * The network affiliation for this TV channel.
         *
         * <p>This is used to identify a channel that is commonly called by its network affiliation
         * instead of the display name. Examples include ABC for the channel KGO-HD, FOX for the
         * channel KTVU-HD and NBC for the channel KNTV-HD. Can be empty if not applicable.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_NETWORK_AFFILIATION = "network_affiliation";

        /**
         * The original network ID of this TV channel.
         *
         * <p>It is used to identify the originating delivery system, if applicable. Use the same
         * coding for {@code original_network_id} for ETSI EN 300 468/TR 101 211 and ARIB STD-B10.
         *
         * <p>This is a required field only if the underlying broadcast standard defines the same
         * name field. Otherwise, leave empty.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_ORIGINAL_NETWORK_ID = "original_network_id";

        /**
         * The remote control key preset number that is assigned to this channel.
         *
         * <p> This can be used for one-touch-tuning, tuning to the channel with
         * pressing the preset button.
         *
         * <p> Type: INTEGER (remote control key preset number)
         */
        public static final String COLUMN_REMOTE_CONTROL_KEY_PRESET_NUMBER = "remote_control_key_preset_number";

        /**
         * The flag indicating whether this TV channel is scrambled or not.
         *
         * <p>Use the same coding for scrambled in the underlying broadcast standard
         * if {@code free_ca_mode} in SDT is defined there (e.g. ETSI EN 300 468).
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_SCRAMBLED = "scrambled";

        /**
         * The flag indicating whether this TV channel is searchable or not.
         *
         * <p>The columns of searchable channels can be read by other applications that have proper
         * permission. Care must be taken not to open sensitive data.
         *
         * <p>A value of 1 indicates that the channel is searchable and its columns can be read by
         * other applications, a value of 0 indicates that the channel is hidden and its columns can
         * be read only by the package that owns the channel and the system. If not specified, this
         * value is set to 1 (searchable) by default.
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_SEARCHABLE = "searchable";

        /**
         * The service ID of this channel.
         *
         * <p>It is used to identify the current service, or channel from any other services within
         * a given Transport Stream, if applicable. Use the same coding for {@code service_id} in
         * ETSI EN 300 468 and ARIB STD-B10 or {@code program_number} in ISO/IEC 13818-1.
         *
         * <p>This is a required field only if the underlying broadcast standard defines the same
         * name field, or the current channel is transmitted via the MPEG Transport Stream. Leave
         * empty otherwise.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_SERVICE_ID = "service_id";

        /**
         * The predefined service type of this TV channel.
         *
         * <p>This is primarily used to indicate whether the current channel is a regular TV channel
         * or a radio-like channel. Use the same coding for {@code service_type} in the underlying
         * broadcast standard if it is defined there (e.g. ATSC A/53, ETSI EN 300 468 and ARIB
         * STD-B10). Otherwise use one of the followings: {@link #SERVICE_TYPE_OTHER},
         * {@link #SERVICE_TYPE_AUDIO_VIDEO}, {@link #SERVICE_TYPE_AUDIO}
         *
         * <p>This is a required field.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_SERVICE_TYPE = "service_type";

        /**
         * The flag indicating whether this TV channel is transient or not.
         *
         * <p>A value of 1 indicates that the channel will be automatically removed by the system on
         * reboot, and a value of 0 indicates that the channel is persistent across reboot. If not
         * specified, this value is set to 0 (not transient) by default.
         *
         * <p>Type: INTEGER (boolean)
         * @see PreviewPrograms#COLUMN_TRANSIENT
         * @see WatchNextPrograms#COLUMN_TRANSIENT
         */
        public static final String COLUMN_TRANSIENT = "transient";

        /**
         * The transport stream ID of this channel.
         *
         * <p>It is used to identify the Transport Stream that contains the current channel from any
         * other multiplex within a network, if applicable. Use the same coding for
         * {@code transport_stream_id} defined in ISO/IEC 13818-1 if the channel is transmitted via
         * the MPEG Transport Stream.
         *
         * <p>This is a required field only if the current channel is transmitted via the MPEG
         * Transport Stream. Leave empty otherwise.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_TRANSPORT_STREAM_ID = "transport_stream_id";

        /**
         * The broadcast system type of this TV channel.
         *
         * <p>This is used to indicate the broadcast standard (e.g. ATSC, DVB or ISDB) the current
         * channel conforms to. Use {@link #TYPE_OTHER} for streaming-based channels, which is the
         * default channel type. The value should match one of the followings:
         * {@link #TYPE_1SEG},
         * {@link #TYPE_ATSC_C},
         * {@link #TYPE_ATSC_M_H},
         * {@link #TYPE_ATSC_T},
         * {@link #TYPE_ATSC3_T},
         * {@link #TYPE_CMMB},
         * {@link #TYPE_DTMB},
         * {@link #TYPE_DVB_C},
         * {@link #TYPE_DVB_C2},
         * {@link #TYPE_DVB_H},
         * {@link #TYPE_DVB_S},
         * {@link #TYPE_DVB_S2},
         * {@link #TYPE_DVB_SH},
         * {@link #TYPE_DVB_T},
         * {@link #TYPE_DVB_T2},
         * {@link #TYPE_ISDB_C},
         * {@link #TYPE_ISDB_S},
         * {@link #TYPE_ISDB_S3},
         * {@link #TYPE_ISDB_T},
         * {@link #TYPE_ISDB_TB},
         * {@link #TYPE_NTSC},
         * {@link #TYPE_OTHER},
         * {@link #TYPE_PAL},
         * {@link #TYPE_SECAM},
         * {@link #TYPE_S_DMB},
         * {@link #TYPE_T_DMB}, and
         * {@link #TYPE_PREVIEW}.
         *
         * <p>This value cannot be changed once it's set. Trying to modify it will make the update
         * fail.
         *
         * <p>This is a required field.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_TYPE = "type";

        /**
         * The version number of this row entry used by TV input services.
         *
         * <p>This is best used by sync adapters to identify the rows to update. The number can be
         * defined by individual TV input services. One may assign the same value as
         * {@code version_number} that appears in ETSI EN 300 468 or ATSC A/65, if the data are
         * coming from a TV broadcast.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_VERSION_NUMBER = "version_number";

        /**
         * The typical video format for programs from this TV channel.
         *
         * <p>This is primarily used to filter out channels based on video format by applications.
         * The value should match one of the followings: {@link #VIDEO_FORMAT_240P},
         * {@link #VIDEO_FORMAT_360P}, {@link #VIDEO_FORMAT_480I}, {@link #VIDEO_FORMAT_480P},
         * {@link #VIDEO_FORMAT_576I}, {@link #VIDEO_FORMAT_576P}, {@link #VIDEO_FORMAT_720P},
         * {@link #VIDEO_FORMAT_1080I}, {@link #VIDEO_FORMAT_1080P}, {@link #VIDEO_FORMAT_2160P},
         * {@link #VIDEO_FORMAT_4320P}. Note that the actual video resolution of each program from a
         * given channel can vary thus one should use {@link Programs#COLUMN_VIDEO_WIDTH} and
         * {@link Programs#COLUMN_VIDEO_HEIGHT} to get more accurate video resolution.
         *
         * <p>Type: TEXT
         *
         * @see #getVideoResolution
         */
        public static final String COLUMN_VIDEO_FORMAT = "video_format";

        /**
         * The typical video resolution.
         *
         * <p>This is primarily used to filter out channels based on video resolution
         * by applications. The value is from SDT if defined there. (e.g. ETSI EN 300 468)
         * The value should match one of the followings: {@link #VIDEO_RESOLUTION_SD},
         * {@link #VIDEO_RESOLUTION_HD}, {@link #VIDEO_RESOLUTION_UHD}.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_VIDEO_RESOLUTION = "video_resolution";

        /**
         * The MIME type of a single TV channel.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/channel";

        /**
         * The MIME type of a directory of TV channels.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/channel";

        /**
         * The service type for radio channels that have audio only.
         */
        public static final String SERVICE_TYPE_AUDIO = "SERVICE_TYPE_AUDIO";

        /**
         * The service type for regular TV channels that have both audio and video.
         */
        public static final String SERVICE_TYPE_AUDIO_VIDEO = "SERVICE_TYPE_AUDIO_VIDEO";

        /**
         * A generic service type.
         */
        public static final String SERVICE_TYPE_OTHER = "SERVICE_TYPE_OTHER";

        /**
         * The channel type for 1seg (handheld).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_1SEG = "TYPE_1SEG";

        /**
         * The channel type for ATSC3.0 (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ATSC3_T = "TYPE_ATSC3_T";

        /**
         * The channel type for ATSC (cable).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ATSC_C = "TYPE_ATSC_C";

        /**
         * The channel type for ATSC-M/H (mobile/handheld).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ATSC_M_H = "TYPE_ATSC_M_H";

        /**
         * The channel type for ATSC (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ATSC_T = "TYPE_ATSC_T";

        /**
         * The channel type for CMMB (handheld).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_CMMB = "TYPE_CMMB";

        /**
         * The channel type for DTMB (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DTMB = "TYPE_DTMB";

        /**
         * The channel type for DVB-C (cable).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_C = "TYPE_DVB_C";

        /**
         * The channel type for DVB-C2 (cable).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_C2 = "TYPE_DVB_C2";

        /**
         * The channel type for DVB-H (handheld).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_H = "TYPE_DVB_H";

        /**
         * The channel type for DVB-S (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_S = "TYPE_DVB_S";

        /**
         * The channel type for DVB-S2 (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_S2 = "TYPE_DVB_S2";

        /**
         * The channel type for DVB-SH (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_SH = "TYPE_DVB_SH";

        /**
         * The channel type for DVB-T (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_T = "TYPE_DVB_T";

        /**
         * The channel type for DVB-T2 (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_DVB_T2 = "TYPE_DVB_T2";

        /**
         * The channel type for ISDB-C (cable).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ISDB_C = "TYPE_ISDB_C";

        /**
         * The channel type for ISDB-S (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ISDB_S = "TYPE_ISDB_S";

        /**
         * The channel type for ISDB-S3 (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ISDB_S3 = "TYPE_ISDB_S3";

        /**
         * The channel type for ISDB-T (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ISDB_T = "TYPE_ISDB_T";

        /**
         * The channel type for ISDB-Tb (Brazil).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_ISDB_TB = "TYPE_ISDB_TB";

        /**
         * The channel type for NTSC.
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_NTSC = "TYPE_NTSC";

        /**
         * A generic channel type.
         *
         * Use this if the current channel is streaming-based or its broadcast system type does not
         * fit under any other types. This is the default channel type.
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_OTHER = "TYPE_OTHER";

        /**
         * The channel type for PAL.
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_PAL = "TYPE_PAL";

        /**
         * The channel type for preview videos.
         *
         * <P>Unlike other broadcast TV channel types, the programs in the preview channel usually
         * are promotional videos. The UI may treat the preview channels differently from the other
         * broadcast channels.
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_PREVIEW = "TYPE_PREVIEW";

        /**
         * The channel type for SECAM.
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_SECAM = "TYPE_SECAM";

        /**
         * The channel type for S-DMB (satellite).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_S_DMB = "TYPE_S_DMB";

        /**
         * The channel type for T-DMB (terrestrial).
         *
         * @see #COLUMN_TYPE
         */
        public static final String TYPE_T_DMB = "TYPE_T_DMB";

        /**
         * The video format for 1080i.
         */
        public static final String VIDEO_FORMAT_1080I = "VIDEO_FORMAT_1080I";

        /**
         * The video format for 1080p.
         */
        public static final String VIDEO_FORMAT_1080P = "VIDEO_FORMAT_1080P";

        /**
         * The video format for 2160p.
         */
        public static final String VIDEO_FORMAT_2160P = "VIDEO_FORMAT_2160P";

        /**
         * The video format for 240p.
         */
        public static final String VIDEO_FORMAT_240P = "VIDEO_FORMAT_240P";

        /**
         * The video format for 360p.
         */
        public static final String VIDEO_FORMAT_360P = "VIDEO_FORMAT_360P";

        /**
         * The video format for 4320p.
         */
        public static final String VIDEO_FORMAT_4320P = "VIDEO_FORMAT_4320P";

        /**
         * The video format for 480i.
         */
        public static final String VIDEO_FORMAT_480I = "VIDEO_FORMAT_480I";

        /**
         * The video format for 480p.
         */
        public static final String VIDEO_FORMAT_480P = "VIDEO_FORMAT_480P";

        /**
         * The video format for 576i.
         */
        public static final String VIDEO_FORMAT_576I = "VIDEO_FORMAT_576I";

        /**
         * The video format for 576p.
         */
        public static final String VIDEO_FORMAT_576P = "VIDEO_FORMAT_576P";

        /**
         * The video format for 720p.
         */
        public static final String VIDEO_FORMAT_720P = "VIDEO_FORMAT_720P";

        /**
         * The video resolution for enhanced-definition.
         */
        public static final String VIDEO_RESOLUTION_ED = "VIDEO_RESOLUTION_ED";

        /**
         * The video resolution for full high-definition.
         */
        public static final String VIDEO_RESOLUTION_FHD = "VIDEO_RESOLUTION_FHD";

        /**
         * The video resolution for high-definition.
         */
        public static final String VIDEO_RESOLUTION_HD = "VIDEO_RESOLUTION_HD";

        /**
         * The video resolution for standard-definition.
         */
        public static final String VIDEO_RESOLUTION_SD = "VIDEO_RESOLUTION_SD";

        /**
         * The video resolution for ultra high-definition.
         */
        public static final String VIDEO_RESOLUTION_UHD = "VIDEO_RESOLUTION_UHD";

        private Channels() {
        }

        /**
         * A sub-directory of a single TV channel that represents its primary logo.
         *
         * <p>To access this directory, append {@link Channels.Logo#CONTENT_DIRECTORY} to the raw
         * channel URI.  The resulting URI represents an image file, and should be interacted
         * using ContentResolver.openAssetFileDescriptor.
         *
         * <p>Note that this sub-directory also supports opening the logo as an asset file in write
         * mode.  Callers can create or replace the primary logo associated with this channel by
         * opening the asset file and writing the full-size photo contents into it. (Make sure there
         * is no padding around the logo image.) When the file is closed, the image will be parsed,
         * sized down if necessary, and stored.
         *
         * <p>Usage example:
         * <pre>
         * public void writeChannelLogo(long channelId, byte[] logo) {
         *     Uri channelLogoUri = TvContract.buildChannelLogoUri(channelId);
         *     try {
         *         AssetFileDescriptor fd =
         *             getContentResolver().openAssetFileDescriptor(channelLogoUri, "rw");
         *         OutputStream os = fd.createOutputStream();
         *         os.write(logo);
         *         os.close();
         *         fd.close();
         *     } catch (IOException e) {
         *         // Handle error cases.
         *     }
         * }
         * </pre>
         */
        public static final class Logo {
            /**
             * The directory twig for this sub-table.
             */
            public static final String CONTENT_DIRECTORY = "logo";

            private Logo() {
            }
        }
    }

    /**
     * Column definitions for the preview TV programs table.
     */
    public static final class PreviewPrograms implements BaseTvColumns {
        public static final int ASPECT_RATIO_16_9 = 0;

        public static final int ASPECT_RATIO_1_1 = 3;

        public static final int ASPECT_RATIO_2_3 = 4;

        public static final int ASPECT_RATIO_3_2 = 1;

        public static final int ASPECT_RATIO_4_3 = 2;

        public static final int AVAILABILITY_AVAILABLE = 0;

        public static final int AVAILABILITY_FREE_WITH_SUBSCRIPTION = 1;

        public static final int AVAILABILITY_PAID_CONTENT = 2;

        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";

        public static final String COLUMN_AUTHOR = "author";

        public static final String COLUMN_AVAILABILITY = "availability";

        public static final String COLUMN_BROWSABLE = "browsable";

        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";

        /**
         * The ID of the TV channel that provides this TV program.
         *
         * <p>This value cannot be changed once it's set. Trying to modify it will make the update
         * fail.
         *
         * <p>This is a part of the channel URI and matches to {@link BaseColumns#_ID}.
         *
         * <p>This is a required field.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_CHANNEL_ID = "channel_id";

        public static final String COLUMN_CONTENT_ID = "content_id";

        public static final String COLUMN_CONTENT_RATING = "content_rating";

        public static final String COLUMN_DURATION_MILLIS = "duration_millis";

        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";

        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";

        public static final String COLUMN_EPISODE_TITLE = "episode_title";

        public static final String COLUMN_INTENT_URI = "intent_uri";

        public static final String COLUMN_INTERACTION_COUNT = "interaction_count";

        public static final String COLUMN_INTERACTION_TYPE = "interaction_type";

        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";

        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";

        public static final String COLUMN_ITEM_COUNT = "item_count";

        public static final String COLUMN_LAST_PLAYBACK_POSITION_MILLIS = "last_playback_position_millis";

        public static final String COLUMN_LIVE = "live";

        public static final String COLUMN_LOGO_URI = "logo_uri";

        public static final String COLUMN_LONG_DESCRIPTION = "long_description";

        public static final String COLUMN_OFFER_PRICE = "offer_price";

        public static final String COLUMN_POSTER_ART_ASPECT_RATIO = "poster_art_aspect_ratio";

        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";

        public static final String COLUMN_PREVIEW_VIDEO_URI = "preview_video_uri";

        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static final String COLUMN_REVIEW_RATING = "review_rating";

        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";

        public static final String COLUMN_SEARCHABLE = "searchable";

        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";

        public static final String COLUMN_SEASON_TITLE = "season_title";

        public static final String COLUMN_SERIES_ID = "series_id";

        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";

        public static final String COLUMN_SPLIT_ID = "split_id";

        public static final String COLUMN_STARTING_PRICE = "starting_price";

        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";

        public static final String COLUMN_THUMBNAIL_ASPECT_RATIO = "poster_thumbnail_aspect_ratio";

        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_TRANSIENT = "transient";

        public static final String COLUMN_TYPE = "type";

        public static final String COLUMN_VERSION_NUMBER = "version_number";

        public static final String COLUMN_VIDEO_HEIGHT = "video_height";

        public static final String COLUMN_VIDEO_WIDTH = "video_width";

        /**
         * The weight of the preview program within the channel.
         *
         * <p>The UI may choose to show this item in a different position in the channel row.
         * A larger weight value means the program is more important than other programs having
         * smaller weight values. The value is relevant for the preview programs in the same
         * channel. This is only relevant to {@link Channels#TYPE_PREVIEW}.
         *
         * <p>Can be empty.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_WEIGHT = "weight";

        /**
         * The MIME type of a single preview TV program.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/preview_program";

        /**
         * The MIME type of a directory of preview TV programs.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/preview_program";

        public static final int INTERACTION_TYPE_FANS = 3;

        public static final int INTERACTION_TYPE_FOLLOWERS = 2;

        public static final int INTERACTION_TYPE_LIKES = 4;

        public static final int INTERACTION_TYPE_LISTENS = 1;

        public static final int INTERACTION_TYPE_THUMBS = 5;

        public static final int INTERACTION_TYPE_VIEWERS = 6;

        public static final int INTERACTION_TYPE_VIEWS = 0;

        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;

        public static final int REVIEW_RATING_STYLE_STARS = 0;

        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;

        public static final int TYPE_ALBUM = 8;

        public static final int TYPE_ARTIST = 9;

        public static final int TYPE_CHANNEL = 6;

        public static final int TYPE_CLIP = 4;

        public static final int TYPE_EVENT = 5;

        public static final int TYPE_MOVIE = 0;

        public static final int TYPE_PLAYLIST = 10;

        public static final int TYPE_STATION = 11;

        public static final int TYPE_TRACK = 7;

        public static final int TYPE_TV_EPISODE = 3;

        public static final int TYPE_TV_SEASON = 2;

        public static final int TYPE_TV_SERIES = 1;

        private PreviewPrograms() {
        }
    }

    /**
     * Column definitions for the TV programs table.
     *
     * <p>By default, the query results will be sorted by
     * {@link Programs#COLUMN_START_TIME_UTC_MILLIS} in ascending order.
     */
    public static final class Programs implements BaseTvColumns {
        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";

        /**
         * The comma-separated genre string of this TV program.
         *
         * <p>Use the same language appeared in the underlying broadcast standard, if applicable.
         * (For example, one can refer to the genre strings used in Genre Descriptor of ATSC A/65 or
         * Content Descriptor of ETSI EN 300 468, if appropriate.) Otherwise, leave empty. Use
         * {@link Genres#encode} to create a text that can be stored in this column. Use
         * {@link Genres#decode} to get the broadcast genre strings from the text stored in the
         * column.
         *
         * <p>Type: TEXT
         * @see Genres#encode
         * @see Genres#decode
         */
        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";

        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";

        /**
         * The ID of the TV channel that provides this TV program.
         *
         * <p>This is a part of the channel URI and matches to {@link BaseColumns#_ID}.
         *
         * <p>This is a required field.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_CHANNEL_ID = "channel_id";

        public static final String COLUMN_CONTENT_RATING = "content_rating";

        /**
         * The end time of this TV program, in milliseconds since the epoch.
         *
         * <p>The value should be equal to or less than {@link #COLUMN_START_TIME_UTC_MILLIS} of the
         * next program in the same channel. In practice, end time will usually be the start time of
         * the next program.
         *
         * <p>Can be empty if this program belongs to a {@link Channels#TYPE_PREVIEW} channel.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";

        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";

        /**
         * The episode number of this TV program for episodic TV shows.
         *
         * <p>Can be empty.
         *
         * <p>Type: INTEGER
         *
         * @deprecated Use {@link #COLUMN_EPISODE_DISPLAY_NUMBER} instead.
         */
        @Deprecated
        public static final String COLUMN_EPISODE_NUMBER = "episode_number";

        public static final String COLUMN_EPISODE_TITLE = "episode_title";

        /**
         * The event ID of this TV program.
         *
         * <p>It is used to identify the current TV program in the same channel, if applicable.
         * Use the same coding for {@code event_id} in the underlying broadcast standard if it
         * is defined there (e.g. ATSC A/65, ETSI EN 300 468 and ARIB STD-B10).
         *
         * <p>This is a required field only if the underlying broadcast standard defines the same
         * name field. Otherwise, leave empty.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_EVENT_ID = "event_id";

        /**
         * The global content ID of this TV program, as a URI.
         *
         * <p>A globally unique ID that identifies this TV program, if applicable. Suitable URIs
         * include
         * <ul>
         * <li>{@code crid://<CRIDauthority>/<data>} from ETSI TS 102 323
         * <li>{@code globalContentId} from ATSC A/332
         * <li>Other broadcast ID provider. ex {@code http://example.com/tv_program/1234}
         * </ul>
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_GLOBAL_CONTENT_ID = "global_content_id";

        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";

        /**
         * The internal ID used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";

        public static final String COLUMN_LONG_DESCRIPTION = "long_description";

        /**
         * The comma-separated series IDs of this TV program for episodic TV shows.
         *
         * <p>This is used to indicate the series IDs.
         * Programs in the same series share a series ID.
         * Use this instead of {@link #COLUMN_SERIES_ID} if more than one series IDs
         * are assigned to the TV program.
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_MULTI_SERIES_ID = "multi_series_id";

        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";

        /**
         * The flag indicating whether recording of this program is prohibited.
         *
         * <p>A value of 1 indicates that recording of this program is prohibited and application
         * will not schedule any recording for this program. A value of 0 indicates that the
         * recording is not prohibited. If not specified, this value is set to 0 (not prohibited) by
         * default.
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_RECORDING_PROHIBITED = "recording_prohibited";

        public static final String COLUMN_REVIEW_RATING = "review_rating";

        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";

        /**
         * The flag indicating whether this TV program is scrambled or not.
         *
         * <p>Use the same coding for scrambled in the underlying broadcast standard
         * if {@code free_ca_mode} in EIT is defined there (e.g. ETSI EN 300 468).
         *
         * <p>Type: INTEGER (boolean)
         */
        public static final String COLUMN_SCRAMBLED = "scrambled";

        public static final String COLUMN_SEARCHABLE = "searchable";

        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";

        /**
         * The season number of this TV program for episodic TV shows.
         *
         * <p>Can be empty.
         *
         * <p>Type: INTEGER
         *
         * @deprecated Use {@link #COLUMN_SEASON_DISPLAY_NUMBER} instead.
         */
        @Deprecated
        public static final String COLUMN_SEASON_NUMBER = "season_number";

        public static final String COLUMN_SEASON_TITLE = "season_title";

        public static final String COLUMN_SERIES_ID = "series_id";

        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";

        public static final String COLUMN_SPLIT_ID = "split_id";

        /**
         * The start time of this TV program, in milliseconds since the epoch.
         *
         * <p>The value should be equal to or larger than {@link #COLUMN_END_TIME_UTC_MILLIS} of the
         * previous program in the same channel. In practice, start time will usually be the end
         * time of the previous program.
         *
         * <p>Can be empty if this program belongs to a {@link Channels#TYPE_PREVIEW} channel.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";

        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_VERSION_NUMBER = "version_number";

        public static final String COLUMN_VIDEO_HEIGHT = "video_height";

        public static final String COLUMN_VIDEO_WIDTH = "video_width";

        /**
         * The MIME type of a single TV program.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/program";

        /**
         * The MIME type of a directory of TV programs.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/program";

        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;

        public static final int REVIEW_RATING_STYLE_STARS = 0;

        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;

        private Programs() {
        }

        /**
         * Canonical genres for TV programs.
         */
        public static final class Genres {
            /**
             * The genre for Animal/Wildlife.
             */
            public static final String ANIMAL_WILDLIFE = "ANIMAL_WILDLIFE";

            /**
             * The genre for Arts.
             */
            public static final String ARTS = "ARTS";

            /**
             * The genre for Comedy.
             */
            public static final String COMEDY = "COMEDY";

            /**
             * The genre for Drama.
             */
            public static final String DRAMA = "DRAMA";

            /**
             * The genre for Education.
             */
            public static final String EDUCATION = "EDUCATION";

            /**
             * The genre for Entertainment.
             */
            public static final String ENTERTAINMENT = "ENTERTAINMENT";

            /**
             * The genre for Family/Kids.
             */
            public static final String FAMILY_KIDS = "FAMILY_KIDS";

            /**
             * The genre for Gaming.
             */
            public static final String GAMING = "GAMING";

            /**
             * The genre for Life Style.
             */
            public static final String LIFE_STYLE = "LIFE_STYLE";

            /**
             * The genre for Movies.
             */
            public static final String MOVIES = "MOVIES";

            /**
             * The genre for Music.
             */
            public static final String MUSIC = "MUSIC";

            /**
             * The genre for News.
             */
            public static final String NEWS = "NEWS";

            /**
             * The genre for Premier.
             */
            public static final String PREMIER = "PREMIER";

            /**
             * The genre for Shopping.
             */
            public static final String SHOPPING = "SHOPPING";

            /**
             * The genre for Sports.
             */
            public static final String SPORTS = "SPORTS";

            /**
             * The genre for Tech/Science.
             */
            public static final String TECH_SCIENCE = "TECH_SCIENCE";

            /**
             * The genre for Travel.
             */
            public static final String TRAVEL = "TRAVEL";

            private Genres() {
            }
        }
    }

    /**
     * Column definitions for the recorded TV programs table.
     *
     * <p>By default, the query results will be sorted by {@link #COLUMN_START_TIME_UTC_MILLIS} in
     * ascending order.
     */
    public static final class RecordedPrograms implements BaseTvColumns {
        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";

        /**
         * The comma-separated genre string of this recorded TV program.
         *
         * <p>Use the same language appeared in the underlying broadcast standard, if applicable.
         * (For example, one can refer to the genre strings used in Genre Descriptor of ATSC A/65 or
         * Content Descriptor of ETSI EN 300 468, if appropriate.) Otherwise, leave empty. Use
         * {@link Genres#encode Genres.encode()} to create a text that can be stored in this column.
         * Use {@link Genres#decode Genres.decode()} to get the broadcast genre strings from the
         * text stored in the column.
         *
         * <p>Type: TEXT
         * @see Programs#COLUMN_BROADCAST_GENRE
         */
        public static final String COLUMN_BROADCAST_GENRE = "broadcast_genre";

        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";

        /**
         * The ID of the TV channel that provides this recorded program.
         *
         * <p>This is a part of the channel URI and matches to {@link BaseColumns#_ID}.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_CHANNEL_ID = "channel_id";

        public static final String COLUMN_CONTENT_RATING = "content_rating";

        /**
         * The end time of the original TV program, in milliseconds since the epoch.
         *
         * <p>Type: INTEGER (long)
         * @see Programs#COLUMN_END_TIME_UTC_MILLIS
         */
        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";

        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";

        public static final String COLUMN_EPISODE_TITLE = "episode_title";

        /**
         * The ID of the TV input service that is associated with this recorded program.
         *
         * <p>Use {@link #buildInputId} to build the ID.
         *
         * <p>This is a required field.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_INPUT_ID = "input_id";

        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";

        /**
         * The internal ID used by individual TV input services.
         *
         * <p>This is internal to the provider that inserted it, and should not be decoded by other
         * apps.
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";

        public static final String COLUMN_LONG_DESCRIPTION = "long_description";

        /**
         * The comma-separated series IDs of this TV program for episodic TV shows.
         *
         * <p>This is used to indicate the series IDs.
         * Programs in the same series share a series ID.
         * Use this instead of {@link #COLUMN_SERIES_ID} if more than one series IDs
         * are assigned to the TV program.
         *
         * <p>Can be empty.
         *
         * <p>Type: TEXT
         */
        public static final String COLUMN_MULTI_SERIES_ID = "multi_series_id";

        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";

        /**
         * The data size (in bytes) for this recorded program.
         *
         * <p>Together with {@link #COLUMN_RECORDING_DATA_URI}, applications can use this
         * information to manage recording storage.
         *
         * <p>Type: INTEGER (long)
         * @see #COLUMN_RECORDING_DATA_URI
         */
        public static final String COLUMN_RECORDING_DATA_BYTES = "recording_data_bytes";

        /**
         * The URI of the recording data for this recorded program.
         *
         * <p>Together with {@link #COLUMN_RECORDING_DATA_BYTES}, applications can use this
         * information to manage recording storage. The URI should indicate a file or directory with
         * the scheme {@link android.content.ContentResolver#SCHEME_FILE}.
         *
         * <p>Type: TEXT
         * @see #COLUMN_RECORDING_DATA_BYTES
         */
        public static final String COLUMN_RECORDING_DATA_URI = "recording_data_uri";

        /**
         * The duration (in milliseconds) of this recorded program.
         *
         * <p>The actual duration of the recorded program can differ from the one calculated by
         * {@link #COLUMN_END_TIME_UTC_MILLIS} - {@link #COLUMN_START_TIME_UTC_MILLIS} as program
         * recording can be interrupted in the middle for some reason, resulting in a partially
         * recorded program, which is still playable.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_RECORDING_DURATION_MILLIS = "recording_duration_millis";

        /**
         * The expiration time for this recorded program, in milliseconds since the epoch.
         *
         * <p>Recorded TV programs do not expire by default unless explicitly requested by the user
         * or the user allows applications to delete them in order to free up disk space for future
         * recording. However, some TV content can have expiration date set by the content provider
         * when recorded. This field is used to indicate such a restriction.
         *
         * <p>Can be empty.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_RECORDING_EXPIRE_TIME_UTC_MILLIS = "recording_expire_time_utc_millis";

        public static final String COLUMN_REVIEW_RATING = "review_rating";

        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";

        public static final String COLUMN_SEARCHABLE = "searchable";

        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";

        public static final String COLUMN_SEASON_TITLE = "season_title";

        public static final String COLUMN_SERIES_ID = "series_id";

        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";

        public static final String COLUMN_SPLIT_ID = "split_id";

        /**
         * The start time of the original TV program, in milliseconds since the epoch.
         *
         * <p>Type: INTEGER (long)
         * @see Programs#COLUMN_START_TIME_UTC_MILLIS
         */
        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";

        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_VERSION_NUMBER = "version_number";

        public static final String COLUMN_VIDEO_HEIGHT = "video_height";

        public static final String COLUMN_VIDEO_WIDTH = "video_width";

        /**
         * The MIME type of a single recorded TV program.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/recorded_program";

        /**
         * The MIME type of a directory of recorded TV programs.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/recorded_program";

        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;

        public static final int REVIEW_RATING_STYLE_STARS = 0;

        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;

        private RecordedPrograms() {
        }
    }

    /**
     * Column definitions for the "watch next" TV programs table.
     */
    public static final class WatchNextPrograms implements BaseTvColumns {
        public static final int ASPECT_RATIO_16_9 = 0;

        public static final int ASPECT_RATIO_1_1 = 3;

        public static final int ASPECT_RATIO_2_3 = 4;

        public static final int ASPECT_RATIO_3_2 = 1;

        public static final int ASPECT_RATIO_4_3 = 2;

        public static final int AVAILABILITY_AVAILABLE = 0;

        public static final int AVAILABILITY_FREE_WITH_SUBSCRIPTION = 1;

        public static final int AVAILABILITY_PAID_CONTENT = 2;

        public static final String COLUMN_AUDIO_LANGUAGE = "audio_language";

        public static final String COLUMN_AUTHOR = "author";

        public static final String COLUMN_AVAILABILITY = "availability";

        public static final String COLUMN_BROWSABLE = "browsable";

        public static final String COLUMN_CANONICAL_GENRE = "canonical_genre";

        public static final String COLUMN_CONTENT_ID = "content_id";

        public static final String COLUMN_CONTENT_RATING = "content_rating";

        public static final String COLUMN_DURATION_MILLIS = "duration_millis";

        public static final String COLUMN_END_TIME_UTC_MILLIS = "end_time_utc_millis";

        public static final String COLUMN_EPISODE_DISPLAY_NUMBER = "episode_display_number";

        public static final String COLUMN_EPISODE_TITLE = "episode_title";

        public static final String COLUMN_INTENT_URI = "intent_uri";

        public static final String COLUMN_INTERACTION_COUNT = "interaction_count";

        public static final String COLUMN_INTERACTION_TYPE = "interaction_type";

        public static final String COLUMN_INTERNAL_PROVIDER_DATA = "internal_provider_data";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG1 = "internal_provider_flag1";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG2 = "internal_provider_flag2";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG3 = "internal_provider_flag3";

        public static final String COLUMN_INTERNAL_PROVIDER_FLAG4 = "internal_provider_flag4";

        public static final String COLUMN_INTERNAL_PROVIDER_ID = "internal_provider_id";

        public static final String COLUMN_ITEM_COUNT = "item_count";

        /**
         * The last UTC time that the user engaged in this TV program, in milliseconds since the
         * epoch. This is a hint for the application that is used for ordering of "watch next"
         * programs.
         *
         * <p>The meaning of the value varies depending on the {@link #COLUMN_WATCH_NEXT_TYPE}:
         * <ul>
         *     <li>{@link #WATCH_NEXT_TYPE_CONTINUE}: the date that the user was last watching the
         *     content.</li>
         *     <li>{@link #WATCH_NEXT_TYPE_NEXT}: the date of the last episode watched.</li>
         *     <li>{@link #WATCH_NEXT_TYPE_NEW}: the release date of the new episode.</li>
         *     <li>{@link #WATCH_NEXT_TYPE_WATCHLIST}: the date the item was added to the Watchlist.
         *     </li>
         * </ul>
         *
         * <p>This is a required field.
         *
         * <p>Type: INTEGER (long)
         */
        public static final String COLUMN_LAST_ENGAGEMENT_TIME_UTC_MILLIS = "last_engagement_time_utc_millis";

        public static final String COLUMN_LAST_PLAYBACK_POSITION_MILLIS = "last_playback_position_millis";

        public static final String COLUMN_LIVE = "live";

        public static final String COLUMN_LOGO_URI = "logo_uri";

        public static final String COLUMN_LONG_DESCRIPTION = "long_description";

        public static final String COLUMN_OFFER_PRICE = "offer_price";

        public static final String COLUMN_POSTER_ART_ASPECT_RATIO = "poster_art_aspect_ratio";

        public static final String COLUMN_POSTER_ART_URI = "poster_art_uri";

        public static final String COLUMN_PREVIEW_VIDEO_URI = "preview_video_uri";

        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static final String COLUMN_REVIEW_RATING = "review_rating";

        public static final String COLUMN_REVIEW_RATING_STYLE = "review_rating_style";

        public static final String COLUMN_SEARCHABLE = "searchable";

        public static final String COLUMN_SEASON_DISPLAY_NUMBER = "season_display_number";

        public static final String COLUMN_SEASON_TITLE = "season_title";

        public static final String COLUMN_SERIES_ID = "series_id";

        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";

        public static final String COLUMN_SPLIT_ID = "split_id";

        public static final String COLUMN_STARTING_PRICE = "starting_price";

        public static final String COLUMN_START_TIME_UTC_MILLIS = "start_time_utc_millis";

        public static final String COLUMN_THUMBNAIL_ASPECT_RATIO = "poster_thumbnail_aspect_ratio";

        public static final String COLUMN_THUMBNAIL_URI = "thumbnail_uri";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_TRANSIENT = "transient";

        public static final String COLUMN_TYPE = "type";

        public static final String COLUMN_VERSION_NUMBER = "version_number";

        public static final String COLUMN_VIDEO_HEIGHT = "video_height";

        public static final String COLUMN_VIDEO_WIDTH = "video_width";

        /**
         * The "watch next" type of this program content.
         *
         * <p>The value should match one of the followings:
         * {@link #WATCH_NEXT_TYPE_CONTINUE},
         * {@link #WATCH_NEXT_TYPE_NEXT},
         * {@link #WATCH_NEXT_TYPE_NEW}, and
         * {@link #WATCH_NEXT_TYPE_WATCHLIST}.
         *
         * <p>This is a required field.
         *
         * <p>Type: INTEGER
         */
        public static final String COLUMN_WATCH_NEXT_TYPE = "watch_next_type";

        /**
         * The MIME type of a single preview TV program.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/watch_next_program";

        /**
         * The MIME type of a directory of "watch next" TV programs.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/watch_next_program";

        public static final int INTERACTION_TYPE_FANS = 3;

        public static final int INTERACTION_TYPE_FOLLOWERS = 2;

        public static final int INTERACTION_TYPE_LIKES = 4;

        public static final int INTERACTION_TYPE_LISTENS = 1;

        public static final int INTERACTION_TYPE_THUMBS = 5;

        public static final int INTERACTION_TYPE_VIEWERS = 6;

        public static final int INTERACTION_TYPE_VIEWS = 0;

        public static final int REVIEW_RATING_STYLE_PERCENTAGE = 2;

        public static final int REVIEW_RATING_STYLE_STARS = 0;

        public static final int REVIEW_RATING_STYLE_THUMBS_UP_DOWN = 1;

        public static final int TYPE_ALBUM = 8;

        public static final int TYPE_ARTIST = 9;

        public static final int TYPE_CHANNEL = 6;

        public static final int TYPE_CLIP = 4;

        public static final int TYPE_EVENT = 5;

        public static final int TYPE_MOVIE = 0;

        public static final int TYPE_PLAYLIST = 10;

        public static final int TYPE_STATION = 11;

        public static final int TYPE_TRACK = 7;

        public static final int TYPE_TV_EPISODE = 3;

        public static final int TYPE_TV_SEASON = 2;

        public static final int TYPE_TV_SERIES = 1;

        /**
         * The watch next type for CONTINUE. Use this type when the user has already watched more
         * than 1 minute of this content.
         *
         * @see #COLUMN_WATCH_NEXT_TYPE
         */
        public static final int WATCH_NEXT_TYPE_CONTINUE = 0;

        /**
         * The watch next type for NEW. Use this type when the user had watched all of the available
         * episodes from some episodic content, but a new episode became available since the user
         * started watching the first episode and now there is exactly one unwatched episode. This
         * could also work for recorded events in a series e.g. soccer matches or football games.
         *
         * @see #COLUMN_WATCH_NEXT_TYPE
         */
        public static final int WATCH_NEXT_TYPE_NEW = 2;

        /**
         * The watch next type for NEXT. Use this type when the user has watched one or more
         * complete episodes from some episodic content, but there remains more than one episode
         * remaining or there is one last episode remaining, but it is not “new” in that it was
         * released before the user started watching the show.
         *
         * @see #COLUMN_WATCH_NEXT_TYPE
         */
        public static final int WATCH_NEXT_TYPE_NEXT = 1;

        /**
         * The watch next type for WATCHLIST. Use this type when the user has elected to explicitly
         * add a movie, event or series to a “watchlist” as a manual way of curating what they
         * want to watch next.
         *
         * @see #COLUMN_WATCH_NEXT_TYPE
         */
        public static final int WATCH_NEXT_TYPE_WATCHLIST = 3;

        private WatchNextPrograms() {
        }
    }
}
