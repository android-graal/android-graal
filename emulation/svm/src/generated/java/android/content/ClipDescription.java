package android.content;

import android.os.Parcelable;

/**
 * Meta-data describing the contents of a {@link ClipData}.  Provides enough
 * information to know if you can handle the ClipData, but not the data
 * itself.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using the clipboard framework, read the
 * <a href="{@docRoot}guide/topics/clipboard/copy-paste.html">Copy and Paste</a>
 * developer guide.</p>
 * </div>
 */
public final class ClipDescription implements Parcelable {
    /**
     * Value returned by {@link #getConfidenceScore(String)} if text classification has been
     * completed.
     */
    public static final int CLASSIFICATION_COMPLETE = 3;

    /**
     * Value returned by {@link #getConfidenceScore(String)} if text classification has not been
     * completed on the associated clip. This will be always be the case if the clip has not been
     * copied to clipboard, or if there is no associated clip.
     */
    public static final int CLASSIFICATION_NOT_COMPLETE = 1;

    /**
     * Value returned by {@link #getConfidenceScore(String)} if text classification was not and will
     * not be performed on the associated clip. This may be the case if the clip does not contain
     * text in its first item, or if the text is too long.
     */
    public static final int CLASSIFICATION_NOT_PERFORMED = 2;

    /**
     * Indicates that a ClipData's source is a remote device.
     *  <p>
     *      Type: boolean
     *  <p>
     *      This extra can be used to indicate that a ClipData comes from a separate device rather
     *      than being local. It is a rendering hint that can be used to take different behavior
     *      based on the source device of copied data.
     */
    public static final String EXTRA_IS_REMOTE_DEVICE = "android.content.extra.IS_REMOTE_DEVICE";

    /**
     * Indicates that a ClipData contains potentially sensitive information, such as a
     * password or credit card number.
     * <p>
     * Type: boolean
     * <p>
     * This extra can be used to indicate that a ClipData contains sensitive information that
     * should be redacted or hidden from view until a user takes explicit action to reveal it
     * (e.g., by pasting).
     * <p>
     * Adding this extra does not change clipboard behavior or add additional security to
     * the ClipData. Its purpose is essentially a rendering hint from the source application,
     * asking that the data within be obfuscated or redacted, unless the user has taken action
     * to make it visible.
     */
    public static final String EXTRA_IS_SENSITIVE = "android.content.extra.IS_SENSITIVE";

    /**
     * The MIME type for a clip holding HTML text.
     */
    public static final String MIMETYPE_TEXT_HTML = "text/html";

    /**
     * The MIME type for a clip holding an Intent.
     */
    public static final String MIMETYPE_TEXT_INTENT = "text/vnd.android.intent";

    /**
     * The MIME type for a clip holding plain text.
     */
    public static final String MIMETYPE_TEXT_PLAIN = "text/plain";

    /**
     * The MIME type for a clip holding one or more URIs.  This should be
     * used for URIs that are meaningful to a user (such as an http: URI).
     * It should <em>not</em> be used for a content: URI that references some
     * other piece of data; in that case the MIME type should be the type
     * of the referenced data.
     */
    public static final String MIMETYPE_TEXT_URILIST = "text/uri-list";

    /**
     * The MIME type for data whose type is otherwise unknown.
     * <p>
     * Per RFC 2046, the "application" media type is to be used for discrete
     * data which do not fit in any of the other categories, and the
     * "octet-stream" subtype is used to indicate that a body contains arbitrary
     * binary data.
     */
    public static final String MIMETYPE_UNKNOWN = "application/octet-stream";

    private ClipDescription() {
    }
}
