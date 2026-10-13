package android.os;

/**
 * Monitors files (using <a href="http://en.wikipedia.org/wiki/Inotify">inotify</a>)
 * to fire an event after files are accessed or changed by any process on
 * the device (including this one).  FileObserver is an abstract class;
 * subclasses must implement the event handler {@link #onEvent(int, String)}.
 *
 * <p>Each FileObserver instance can monitor multiple files or directories.
 * If a directory is monitored, events will be triggered for all files and
 * subdirectories inside the monitored directory.</p>
 *
 * <p>An event mask is used to specify which changes or actions to report.
 * Event type constants are used to describe the possible changes in the
 * event mask as well as what actually happened in event callbacks.</p>
 *
 * <p class="caution"><b>Warning</b>: If a FileObserver is garbage collected, it
 * will stop sending events.  To ensure you keep receiving events, you must
 * keep a reference to the FileObserver instance from some other live object.</p>
 */
public final class FileObserver {
    /**
     * Event type: Data was read from a file
     */
    public static final int ACCESS = 1;

    /**
     * Event mask: All valid event types, combined
     */
    public static final int ALL_EVENTS = 4095;

    /**
     * Event type: Metadata (permissions, owner, timestamp) was changed explicitly
     */
    public static final int ATTRIB = 4;

    /**
     * Event type: Someone had a file or directory open read-only, and closed it
     */
    public static final int CLOSE_NOWRITE = 16;

    /**
     * Event type: Someone had a file or directory open for writing, and closed it
     */
    public static final int CLOSE_WRITE = 8;

    /**
     * Event type: A new file or subdirectory was created under the monitored directory
     */
    public static final int CREATE = 256;

    /**
     * Event type: A file was deleted from the monitored directory
     */
    public static final int DELETE = 512;

    /**
     * Event type: The monitored file or directory was deleted; monitoring effectively stops
     */
    public static final int DELETE_SELF = 1024;

    /**
     * Event type: Data was written to a file
     */
    public static final int MODIFY = 2;

    /**
     * Event type: A file or subdirectory was moved from the monitored directory
     */
    public static final int MOVED_FROM = 64;

    /**
     * Event type: A file or subdirectory was moved to the monitored directory
     */
    public static final int MOVED_TO = 128;

    /**
     * Event type: The monitored file or directory was moved; monitoring continues
     */
    public static final int MOVE_SELF = 2048;

    /**
     * Event type: A file or directory was opened
     */
    public static final int OPEN = 32;

    private FileObserver() {
    }
}
