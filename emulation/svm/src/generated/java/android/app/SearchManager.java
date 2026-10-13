package android.app;

/**
 * This class provides access to the system search services.
 *
 * <p>In practice, you won't interact with this class directly, as search
 * services are provided through methods in {@link android.app.Activity Activity}
 * and the {@link android.content.Intent#ACTION_SEARCH ACTION_SEARCH}
 * {@link android.content.Intent Intent}.
 *
 * <p>
 * {@link Configuration#UI_MODE_TYPE_WATCH} does not support this system service.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using the search dialog and adding search
 * suggestions in your application, read the
 * <a href="{@docRoot}guide/topics/search/index.html">Search</a> developer guide.</p>
 * </div>
 */
public final class SearchManager {
    /**
     * Intent extra data key: Use this key with Intent.ACTION_SEARCH and
     * {@link android.content.Intent#getIntExtra content.Intent.getIntExtra()}
     * to obtain the keycode that the user used to trigger this query.  It will be zero if the
     * user simply pressed the "GO" button on the search UI.  This is primarily used in conjunction
     * with the keycode attribute in the actionkey element of your searchable.xml configuration
     * file.
     */
    public static final String ACTION_KEY = "action_key";

    /**
     * Intent extra data key: Use this key with Intent.ACTION_SEARCH and
     * {@link android.content.Intent#getStringExtra content.Intent.getStringExtra()}
     * to obtain the action message that was defined for a particular search action key and/or
     * suggestion.  It will be null if the search was launched by typing "enter", touching the
     * "GO" button, or other means not involving any action key.
     */
    public static final String ACTION_MSG = "action_msg";

    /**
     * Intent extra data key: Use this key with Intent.ACTION_SEARCH and
     * {@link android.content.Intent#getBundleExtra
     *  content.Intent.getBundleExtra()}
     * to obtain any additional app-specific data that was inserted by the
     * activity that launched the search.
     */
    public static final String APP_DATA = "app_data";

    /**
     * Boolean extra data key for a suggestion provider to return in {@link Cursor#getExtras} to
     * indicate that the search is not complete yet. This can be used by the search UI
     * to indicate that a search is in progress. The suggestion provider can return partial results
     * this way and send a change notification on the cursor when more results are available.
     */
    public static final String CURSOR_EXTRA_KEY_IN_PROGRESS = "in_progress";

    /**
     * Intent extra data key: This key will be used for the extra populated by the
     * {@link #SUGGEST_COLUMN_INTENT_EXTRA_DATA} column.
     */
    public static final String EXTRA_DATA_KEY = "intent_extra_data_key";

    /**
     * Boolean extra data key for {@link Intent#ACTION_WEB_SEARCH} intents.  If {@code true},
     * this search should open a new browser window, rather than using an existing one.
     */
    public static final String EXTRA_NEW_SEARCH = "new_search";

    /**
     * Boolean extra data key for {@link #INTENT_ACTION_GLOBAL_SEARCH} intents. If {@code true},
     * the initial query should be selected when the global search activity is started, so
     * that the user can easily replace it with another query.
     */
    public static final String EXTRA_SELECT_QUERY = "select_query";

    /**
     * Extra data key for {@link Intent#ACTION_WEB_SEARCH}. If set, the value must be a
     * {@link PendingIntent}. The search activity handling the {@link Intent#ACTION_WEB_SEARCH}
     * intent will fill in and launch the pending intent. The data URI will be filled in with an
     * http or https URI, and {@link android.provider.Browser#EXTRA_HEADERS} may be filled in.
     */
    public static final String EXTRA_WEB_SEARCH_PENDINGINTENT = "web_search_pendingintent";

    /**
     * Flag to specify that the entry can be used for query refinement, i.e., the query text
     * in the search field can be replaced with the text in this entry, when a query refinement
     * icon is clicked. The suggestion list should show such a clickable icon beside the entry.
     * <p>Use this flag as a bit-field for {@link #SUGGEST_COLUMN_FLAGS}.
     */
    public static final int FLAG_QUERY_REFINEMENT = 1;

    /**
     * Intent action for starting the global search activity.
     * The global search provider should handle this intent.
     *
     * Supported extra data keys: {@link #QUERY},
     * {@link #EXTRA_SELECT_QUERY},
     * {@link #APP_DATA}.
     */
    public static final String INTENT_ACTION_GLOBAL_SEARCH = "android.search.action.GLOBAL_SEARCH";

    /**
     * Intent action broadcasted to inform that the searchables list or default have changed.
     * Components should handle this intent if they cache any searchable data and wish to stay
     * up to date on changes.
     */
    public static final String INTENT_ACTION_SEARCHABLES_CHANGED = "android.search.action.SEARCHABLES_CHANGED";

    /**
     * Intent action for starting the global search settings activity.
     * The global search provider should handle this intent.
     */
    public static final String INTENT_ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";

    /**
     * Intent action broadcasted to inform that the search settings have changed in some way.
     * Either searchables have been enabled or disabled, or a different web search provider
     * has been chosen.
     */
    public static final String INTENT_ACTION_SEARCH_SETTINGS_CHANGED = "android.search.action.SETTINGS_CHANGED";

    /**
     * Intent action for starting a web search provider's settings activity.
     * Web search providers should handle this intent if they have provider-specific
     * settings to implement.
     */
    public static final String INTENT_ACTION_WEB_SEARCH_SETTINGS = "android.search.action.WEB_SEARCH_SETTINGS";

    /**
     * Intent action to be broadcast to inform that the global search provider
     * has changed.
     */
    public static final String INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED = "android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED";

    /**
     * This is a shortcut definition for the default menu key to use for invoking search.
     *
     * See Menu.Item.setAlphabeticShortcut() for more information.
     */
    public static final char MENU_KEY = 's';

    /**
     * This is a shortcut definition for the default menu key to use for invoking search.
     *
     * See Menu.Item.setAlphabeticShortcut() for more information.
     */
    public static final int MENU_KEYCODE = 47;

    /**
     * Intent extra data key: Use this key with
     * {@link android.content.Intent#getStringExtra
     *  content.Intent.getStringExtra()}
     * to obtain the query string from Intent.ACTION_SEARCH.
     */
    public static final String QUERY = "query";

    /**
     * MIME type for shortcut validation.  You'll use this in your suggestions content provider
     * in the getType() function.
     */
    public static final String SHORTCUT_MIME_TYPE = "vnd.android.cursor.item/vnd.android.search.suggest";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content contains audio, you
     * should provide this column to specify the audio channel configuration. The data in the
     * column is string with format like "channels.subchannels" such as "1.0" or "5.1".
     */
    public static final String SUGGEST_COLUMN_AUDIO_CHANNEL_CONFIG = "suggest_audio_channel_config";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is media type, you
     * should provide this column so search app could understand more about your content. The data
     * in the column must specify the MIME type of the content.
     */
    public static final String SUGGEST_COLUMN_CONTENT_TYPE = "suggest_content_type";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is video or audio, you
     * should provide this column to specify the duration of your content in milliseconds. The data
     * in the column is of long type.
     */
    public static final String SUGGEST_COLUMN_DURATION = "suggest_duration";

    /**
     * Column name for suggestions cursor. <i>Optional.</i> This column is used to specify
     * additional flags per item. Multiple flags can be specified.
     * <p>
     * Must be one of {@link #FLAG_QUERY_REFINEMENT} or 0 to indicate no flags.
     * </p>
     */
    public static final String SUGGEST_COLUMN_FLAGS = "suggest_flags";

    /**
     * Column name for suggestions cursor.  <i>Unused - can be null or column can be omitted.</i>
     */
    public static final String SUGGEST_COLUMN_FORMAT = "suggest_format";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If your cursor includes this column,
     *  then all suggestions will be provided in a format that includes space for two small icons,
     *  one at the left and one at the right of each suggestion.  The data in the column must
     *  be a resource ID of a drawable, or a URI in one of the following formats:
     *
     * <ul>
     * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
     * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})</li>
     * <li>file ({@link android.content.ContentResolver#SCHEME_FILE})</li>
     * </ul>
     *
     * See {@link android.content.ContentResolver#openAssetFileDescriptor(Uri, String)}
     * for more information on these schemes.
     */
    public static final String SUGGEST_COLUMN_ICON_1 = "suggest_icon_1";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If your cursor includes this column,
     *  then all suggestions will be provided in a format that includes space for two small icons,
     *  one at the left and one at the right of each suggestion.  The data in the column must
     *  be a resource ID of a drawable, or a URI in one of the following formats:
     *
     * <ul>
     * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
     * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})</li>
     * <li>file ({@link android.content.ContentResolver#SCHEME_FILE})</li>
     * </ul>
     *
     * See {@link android.content.ContentResolver#openAssetFileDescriptor(Uri, String)}
     * for more information on these schemes.
     */
    public static final String SUGGEST_COLUMN_ICON_2 = "suggest_icon_2";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If this column exists <i>and</i>
     * this element exists at the given row, this is the action that will be used when
     * forming the suggestion's intent.  If the element is not provided, the action will be taken
     * from the android:searchSuggestIntentAction field in your XML metadata.  <i>At least one of
     * these must be present for the suggestion to generate an intent.</i>  Note:  If your action is
     * the same for all suggestions, it is more efficient to specify it using XML metadata and omit
     * it from the cursor.
     */
    public static final String SUGGEST_COLUMN_INTENT_ACTION = "suggest_intent_action";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If this column exists <i>and</i>
     * this element exists at the given row, this is the data that will be used when
     * forming the suggestion's intent.  If the element is not provided, the data will be taken
     * from the android:searchSuggestIntentData field in your XML metadata.  If neither source
     * is provided, the Intent's data field will be null.  Note:  If your data is
     * the same for all suggestions, or can be described using a constant part and a specific ID,
     * it is more efficient to specify it using XML metadata and omit it from the cursor.
     */
    public static final String SUGGEST_COLUMN_INTENT_DATA = "suggest_intent_data";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If this column exists <i>and</i>
     * this element exists at the given row, then "/" and this value will be appended to the data
     * field in the Intent.  This should only be used if the data field has already been set to an
     * appropriate base string.
     */
    public static final String SUGGEST_COLUMN_INTENT_DATA_ID = "suggest_intent_data_id";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If this column exists <i>and</i>
     * this element exists at the given row, this is the data that will be used when
     * forming the suggestion's intent. If not provided, the Intent's extra data field will be null.
     * This column allows suggestions to provide additional arbitrary data which will be included as
     * an extra under the key {@link #EXTRA_DATA_KEY}.
     */
    public static final String SUGGEST_COLUMN_INTENT_EXTRA_DATA = "suggest_intent_extra_data";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is media type, you
     * should provide this column to specify whether your content is live media such as live video
     * or live audio. The value in the column is of integer type with value of either 0 indicating
     * non-live content or 1 indicating live content.
     */
    public static final String SUGGEST_COLUMN_IS_LIVE = "suggest_is_live";

    /**
     * Column name for suggestions cursor. <i>Optional.</i> This column may be
     * used to specify the time in {@link System#currentTimeMillis
     * System.currentTImeMillis()} (wall time in UTC) when an item was last
     * accessed within the results-providing application. If set, this may be
     * used to show more-recently-used items first.
     */
    public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is video or audio and
     * has a known production year, you should provide this column to specify the production year
     * of your content. The data in the column is of integer type.
     */
    public static final String SUGGEST_COLUMN_PRODUCTION_YEAR = "suggest_production_year";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is purchasable, you
     * should provide this column to specify the displayable string representation of the purchase
     * price of your content including the currency and the amount. If it's free, you should
     * provide localized string to specify that it's free. This column can be omitted if the content
     * is not applicable to purchase.
     */
    public static final String SUGGEST_COLUMN_PURCHASE_PRICE = "suggest_purchase_price";

    /**
     * Column name for suggestions cursor.  <i>Required if action is
     * {@link android.content.Intent#ACTION_SEARCH ACTION_SEARCH}, optional otherwise.</i>  If this
     * column exists <i>and</i> this element exists at the given row, this is the data that will be
     * used when forming the suggestion's query.
     */
    public static final String SUGGEST_COLUMN_QUERY = "suggest_intent_query";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content has a rating, you
     * should provide this column to specify the rating score of your content. The data in the
     * column is of float type. See {@link android.media.Rating} about valid rating scores for each
     * rating style.
     */
    public static final String SUGGEST_COLUMN_RATING_SCORE = "suggest_rating_score";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content has a rating, you
     * should provide this column to specify the rating style of your content. The data in the
     * column must be one of the constant values specified in {@link android.media.Rating}
     */
    public static final String SUGGEST_COLUMN_RATING_STYLE = "suggest_rating_style";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is rentable, you
     * should provide this column to specify the displayable string representation of the rental
     * price of your content including the currency and the amount. If it's free, you should
     * provide localized string to specify that it's free. This column can be omitted if the
     * content is not applicable to rent.
     */
    public static final String SUGGEST_COLUMN_RENTAL_PRICE = "suggest_rental_price";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If your cursor includes this column,
     * then the image will be displayed when forming the suggestion. The suggested dimension for
     * the image is 270x400 px for portrait mode and 400x225 px for landscape mode. The data in the
     * column must be a resource ID of a drawable, or a URI in one of the following formats:
     *
     * <ul>
     * <li>content ({@link android.content.ContentResolver#SCHEME_CONTENT})</li>
     * <li>android.resource ({@link android.content.ContentResolver#SCHEME_ANDROID_RESOURCE})</li>
     * <li>file ({@link android.content.ContentResolver#SCHEME_FILE})</li>
     * </ul>
     *
     * See {@link android.content.ContentResolver#openAssetFileDescriptor(Uri, String)}
     * for more information on these schemes.
     */
    public static final String SUGGEST_COLUMN_RESULT_CARD_IMAGE = "suggest_result_card_image";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  This column is used to indicate whether
     * a search suggestion should be stored as a shortcut, and whether it should be refreshed.  If
     * missing, the result will be stored as a shortcut and never validated.  If set to
     * {@link #SUGGEST_NEVER_MAKE_SHORTCUT}, the result will not be stored as a shortcut.
     * Otherwise, the shortcut id will be used to check back for an up to date suggestion using
     * {@link #SUGGEST_URI_PATH_SHORTCUT}.
     */
    public static final String SUGGEST_COLUMN_SHORTCUT_ID = "suggest_shortcut_id";

    /**
     * Column name for suggestions cursor. <i>Optional.</i> This column is used to specify
     * that a spinner should be shown in lieu of an icon2 while the shortcut of this suggestion
     * is being refreshed.
     */
    public static final String SUGGEST_COLUMN_SPINNER_WHILE_REFRESHING = "suggest_spinner_while_refreshing";

    /**
     * Column name for suggestions cursor.  <i>Required.</i>  This is the primary line of text that
     * will be presented to the user as the suggestion.
     */
    public static final String SUGGEST_COLUMN_TEXT_1 = "suggest_text_1";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i>  If your cursor includes this column,
     *  then all suggestions will be provided in a two-line format.  The second line of text is in
     *  a much smaller appearance.
     */
    public static final String SUGGEST_COLUMN_TEXT_2 = "suggest_text_2";

    /**
     * Column name for suggestions cursor.  <i>Optional.</i> This is a URL that will be shown
     * as the second line of text instead of {@link #SUGGEST_COLUMN_TEXT_2}. This is a separate
     * column so that the search UI knows to display the text as a URL, e.g. by using a different
     * color. If this column is absent, or has the value {@code null},
     * {@link #SUGGEST_COLUMN_TEXT_2} will be used instead.
     */
    public static final String SUGGEST_COLUMN_TEXT_2_URL = "suggest_text_2_url";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is video, you should
     * provide this column to specify the number of horizontal lines. The data in the column is of
     * integer type.
     */
    public static final String SUGGEST_COLUMN_VIDEO_HEIGHT = "suggest_video_height";

    /**
     * Column name for suggestions cursor. <i>Optional.</i>  If your content is video, you should
     * provide this column to specify the number of vertical lines. The data in the column is of
     * integer type.
     */
    public static final String SUGGEST_COLUMN_VIDEO_WIDTH = "suggest_video_width";

    /**
     * MIME type for suggestions data.  You'll use this in your suggestions content provider
     * in the getType() function.
     */
    public static final String SUGGEST_MIME_TYPE = "vnd.android.cursor.dir/vnd.android.search.suggest";

    /**
     * Column value for suggestion column {@link #SUGGEST_COLUMN_SHORTCUT_ID} when a suggestion
     * should not be stored as a shortcut in global search.
     */
    public static final String SUGGEST_NEVER_MAKE_SHORTCUT = "_-1";

    /**
     * Query parameter added to suggestion queries to limit the number of suggestions returned.
     * This limit is only advisory and suggestion providers may chose to ignore it.
     */
    public static final String SUGGEST_PARAMETER_LIMIT = "limit";

    /**
     * Uri path for queried suggestions data.  This is the path that the search manager
     * will use when querying your content provider for suggestions data based on user input
     * (e.g. looking for partial matches).
     * Typically you'll use this with a URI matcher.
     */
    public static final String SUGGEST_URI_PATH_QUERY = "search_suggest_query";

    /**
     * Uri path for shortcut validation.  This is the path that the search manager will use when
     * querying your content provider to refresh a shortcutted suggestion result and to check if it
     * is still valid.  When asked, a source may return an up to date result, or no result.  No
     * result indicates the shortcut refers to a no longer valid sugggestion.
     *
     * @see #SUGGEST_COLUMN_SHORTCUT_ID
     */
    public static final String SUGGEST_URI_PATH_SHORTCUT = "search_suggest_shortcut";

    /**
     * Intent extra data key: Use this key with
     * {@link android.content.Intent#getStringExtra
     *  content.Intent.getStringExtra()}
     * to obtain the query string typed in by the user.
     * This may be different from the value of {@link #QUERY}
     * if the intent is the result of selecting a suggestion.
     * In that case, {@link #QUERY} will contain the value of
     * {@link #SUGGEST_COLUMN_QUERY} for the suggestion, and
     * {@link #USER_QUERY} will contain the string typed by the
     * user.
     */
    public static final String USER_QUERY = "user_query";

    private SearchManager() {
    }
}
