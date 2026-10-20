package android.content;

import android.os.Parcelable;

/**
 * Structured description of Intent values to be matched.  An IntentFilter can
 * match against actions, categories, and data (either via its type, scheme,
 * and/or path) in an Intent.  It also includes a "priority" value which is
 * used to order multiple matching filters.
 *
 * <p>IntentFilter objects are often created in XML as part of a package's
 * {@link android.R.styleable#AndroidManifest AndroidManifest.xml} file,
 * using {@link android.R.styleable#AndroidManifestIntentFilter intent-filter}
 * tags.
 *
 * <p>There are three Intent characteristics you can filter on: the
 * <em>action</em>, <em>data</em>, and <em>categories</em>.  For each of these
 * characteristics you can provide
 * multiple possible matching values (via {@link #addAction},
 * {@link #addDataType}, {@link #addDataScheme}, {@link #addDataSchemeSpecificPart},
 * {@link #addDataAuthority}, {@link #addDataPath}, and {@link #addCategory}, respectively).
 * For actions, if no data characteristics are specified, then the filter will
 * only match intents that contain no data.
 *
 * <p>The data characteristic is
 * itself divided into three attributes: type, scheme, authority, and path.
 * Any that are
 * specified must match the contents of the Intent.  If you specify a scheme
 * but no type, only Intent that does not have a type (such as mailto:) will
 * match; a content: URI will never match because they always have a MIME type
 * that is supplied by their content provider.  Specifying a type with no scheme
 * has somewhat special meaning: it will match either an Intent with no URI
 * field, or an Intent with a content: or file: URI.  If you specify neither,
 * then only an Intent with no data or type will match.  To specify an authority,
 * you must also specify one or more schemes that it is associated with.
 * To specify a path, you also must specify both one or more authorities and
 * one or more schemes it is associated with.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about how to create and resolve intents, read the
 * <a href="{@docRoot}guide/topics/intents/intents-filters.html">Intents and Intent Filters</a>
 * developer guide.</p>
 * </div>
 *
 * <h3>Filter Rules</h3>
 * <p>A match is based on the following rules.  Note that
 * for an IntentFilter to match an Intent, three conditions must hold:
 * the <strong>action</strong> and <strong>category</strong> must match, and
 * the data (both the <strong>data type</strong> and
 * <strong>data scheme+authority+path</strong> if specified) must match
 * (see {@link #match(ContentResolver, Intent, boolean, String)} for more details
 * on how the data fields match).
 *
 * <p><strong>Action</strong> matches if any of the given values match the
 * Intent action; if the filter specifies no actions, then it will only match
 * Intents that do not contain an action.
 *
 * <p><strong>Data Type</strong> matches if any of the given values match the
 * Intent type.  The Intent
 * type is determined by calling {@link Intent#resolveType}.  A wildcard can be
 * used for the MIME sub-type, in both the Intent and IntentFilter, so that the
 * type "audio/*" will match "audio/mpeg", "audio/aiff", "audio/*", etc.
 * <em>Note that MIME type matching here is <b>case sensitive</b>, unlike
 * formal RFC MIME types!</em>  You should thus always use lower case letters
 * for your MIME types.
 *
 * <p><strong>Data Scheme</strong> matches if any of the given values match the
 * Intent data's scheme.
 * The Intent scheme is determined by calling {@link Intent#getData}
 * and {@link android.net.Uri#getScheme} on that URI.
 * <em>Note that scheme matching here is <b>case sensitive</b>, unlike
 * formal RFC schemes!</em>  You should thus always use lower case letters
 * for your schemes.
 *
 * <p><strong>Data Scheme Specific Part</strong> matches if any of the given values match
 * the Intent's data scheme specific part <em>and</em> one of the data schemes in the filter
 * has matched the Intent, <em>or</em> no scheme specific parts were supplied in the filter.
 * The Intent scheme specific part is determined by calling
 * {@link Intent#getData} and {@link android.net.Uri#getSchemeSpecificPart} on that URI.
 * <em>Note that scheme specific part matching is <b>case sensitive</b>.</em>
 *
 * <p><strong>Data Authority</strong> matches if any of the given values match
 * the Intent's data authority <em>and</em> one of the data schemes in the filter
 * has matched the Intent, <em>or</em> no authorities were supplied in the filter.
 * The Intent authority is determined by calling
 * {@link Intent#getData} and {@link android.net.Uri#getAuthority} on that URI.
 * <em>Note that authority matching here is <b>case sensitive</b>, unlike
 * formal RFC host names!</em>  You should thus always use lower case letters
 * for your authority.
 *
 * <p><strong>Data Path</strong> matches if any of the given values match the
 * Intent's data path <em>and</em> both a scheme and authority in the filter
 * has matched against the Intent, <em>or</em> no paths were supplied in the
 * filter.  The Intent authority is determined by calling
 * {@link Intent#getData} and {@link android.net.Uri#getPath} on that URI.
 *
 * <p><strong>Categories</strong> match if <em>all</em> of the categories in
 * the Intent match categories given in the filter.  Extra categories in the
 * filter that are not in the Intent will not cause the match to fail.  Note
 * that unlike the action, an IntentFilter with no categories
 * will only match an Intent that does not have any categories.
 */
public class IntentFilter implements Parcelable {
    /**
     * The part of a match constant that applies a quality adjustment to the
     * basic category of match.  The value {@link #MATCH_ADJUSTMENT_NORMAL}
     * is no adjustment; higher numbers than that improve the quality, while
     * lower numbers reduce it.
     */
    public static final int MATCH_ADJUSTMENT_MASK = 65535;

    /**
     * Quality adjustment applied to the category of match that signifies
     * the default, base value; higher numbers improve the quality while
     * lower numbers reduce it.
     */
    public static final int MATCH_ADJUSTMENT_NORMAL = 32768;

    /**
     * The filter matched an intent that had no data specified.
     */
    public static final int MATCH_CATEGORY_EMPTY = 1048576;

    /**
     * The filter matched an intent with the same data URI scheme and
     * authority host.
     */
    public static final int MATCH_CATEGORY_HOST = 3145728;

    /**
     * The part of a match constant that describes the category of match
     * that occurred.  May be either {@link #MATCH_CATEGORY_EMPTY},
     * {@link #MATCH_CATEGORY_SCHEME}, {@link #MATCH_CATEGORY_SCHEME_SPECIFIC_PART},
     * {@link #MATCH_CATEGORY_HOST}, {@link #MATCH_CATEGORY_PORT},
     * {@link #MATCH_CATEGORY_PATH}, or {@link #MATCH_CATEGORY_TYPE}.  Higher
     * values indicate a better match.
     */
    public static final int MATCH_CATEGORY_MASK = 268369920;

    /**
     * The filter matched an intent with the same data URI scheme,
     * authority, and path.
     */
    public static final int MATCH_CATEGORY_PATH = 5242880;

    /**
     * The filter matched an intent with the same data URI scheme and
     * authority host and port.
     */
    public static final int MATCH_CATEGORY_PORT = 4194304;

    /**
     * The filter matched an intent with the same data URI scheme.
     */
    public static final int MATCH_CATEGORY_SCHEME = 2097152;

    /**
     * The filter matched an intent with the same data URI scheme and
     * scheme specific part.
     */
    public static final int MATCH_CATEGORY_SCHEME_SPECIFIC_PART = 5767168;

    /**
     * The filter matched an intent with the same data MIME type.
     */
    public static final int MATCH_CATEGORY_TYPE = 6291456;

    /**
     * The filter didn't match due to different actions.
     */
    public static final int NO_MATCH_ACTION = -3;

    /**
     * The filter didn't match because it required one or more categories
     * that were not in the Intent.
     */
    public static final int NO_MATCH_CATEGORY = -4;

    /**
     * The filter didn't match due to different data URIs.
     */
    public static final int NO_MATCH_DATA = -2;

    /**
     * The filter didn't match due to different MIME types.
     */
    public static final int NO_MATCH_TYPE = -1;

    /**
     * The filter {@link #setPriority} value at which system high-priority
     * receivers are placed; that is, receivers that should execute before
     * application code. Applications should never use filters with this or
     * higher priorities.
     *
     * @see #setPriority
     */
    public static final int SYSTEM_HIGH_PRIORITY = 1000;

    /**
     * The filter {@link #setPriority} value at which system low-priority
     * receivers are placed; that is, receivers that should execute after
     * application code. Applications should never use filters with this or
     * lower priorities.
     *
     * @see #setPriority
     */
    public static final int SYSTEM_LOW_PRIORITY = -1000;

    @Override
    public final int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private IntentFilter() {
    }
}
