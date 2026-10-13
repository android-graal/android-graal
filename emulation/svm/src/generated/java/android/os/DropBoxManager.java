package android.os;

/**
 * Enqueues chunks of data (from various sources -- application crashes, kernel
 * log records, etc.).  The queue is size bounded and will drop old data if the
 * enqueued data exceeds the maximum size.  You can think of this as a
 * persistent, system-wide, blob-oriented "logcat".
 *
 * <p>DropBoxManager entries are not sent anywhere directly, but other system
 * services and debugging tools may scan and upload entries for processing.
 */
public final class DropBoxManager {
    /**
     * Broadcast Action: This is broadcast when a new entry is added in the dropbox.
     * For apps targeting 35 and later, For apps targeting Android versions lower
     * than 35, you must hold
     * {@link android.Manifest.permission#READ_LOGS}.
     * This broadcast can be rate limited for low priority entries
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_DROPBOX_ENTRY_ADDED = "android.intent.action.DROPBOX_ENTRY_ADDED";

    /**
     * Extra for {@link android.os.DropBoxManager#ACTION_DROPBOX_ENTRY_ADDED}:
     * integer value containing number of broadcasts dropped due to rate limiting on
     * this {@link android.os.DropBoxManager#EXTRA_TAG}
     */
    public static final String EXTRA_DROPPED_COUNT = "android.os.extra.DROPPED_COUNT";

    /**
     * Extra for {@link android.os.DropBoxManager#ACTION_DROPBOX_ENTRY_ADDED}:
     * string containing the dropbox tag.
     */
    public static final String EXTRA_TAG = "tag";

    /**
     * Extra for {@link android.os.DropBoxManager#ACTION_DROPBOX_ENTRY_ADDED}:
     * long integer value containing time (in milliseconds since January 1, 1970 00:00:00 UTC)
     * when the entry was created.
     */
    public static final String EXTRA_TIME = "time";

    /**
     * Flag value: Entry's content was deleted to save space.
     */
    public static final int IS_EMPTY = 1;

    /**
     * Flag value: Content can be decompressed with java.util.zip.GZIPOutputStream.
     */
    public static final int IS_GZIPPED = 4;

    /**
     * Flag value: Content is human-readable UTF-8 text (can be combined with IS_GZIPPED).
     */
    public static final int IS_TEXT = 2;

    private DropBoxManager() {
    }
}
