package android.print;

import android.os.Parcelable;

/**
 * This class represents the description of a print job. The print job
 * state includes properties such as its id, print attributes used for
 * generating the content, and so on. Note that the print jobs state may
 * change over time and this class represents a snapshot of this state.
 */
public final class PrintJobInfo implements Parcelable {
    /**
     * Print job state: The print job is blocked.
     * <p>
     * Next valid states: {@link #STATE_FAILED}, {@link #STATE_CANCELED},
     * {@link #STATE_STARTED}
     * </p>
     */
    public static final int STATE_BLOCKED = 4;

    /**
     * Print job state: The print job is canceled.
     * This is a terminal state.
     * <p>
     * Next valid states: None
     * </p>
     */
    public static final int STATE_CANCELED = 7;

    /**
     * Print job state: The print job is successfully printed.
     * This is a terminal state.
     * <p>
     * Next valid states: None
     * </p>
     */
    public static final int STATE_COMPLETED = 5;

    /**
     * Print job state: The print job is being created but not yet
     * ready to be printed.
     * <p>
     * Next valid states: {@link #STATE_QUEUED}
     * </p>
     */
    public static final int STATE_CREATED = 1;

    /**
     * Print job state: The print job was printing but printing failed.
     * <p>
     * Next valid states: {@link #STATE_CANCELED}, {@link #STATE_STARTED}
     * </p>
     */
    public static final int STATE_FAILED = 6;

    /**
     * Print job state: The print jobs is created, it is ready
     * to be printed and should be processed.
     * <p>
     * Next valid states: {@link #STATE_STARTED}, {@link #STATE_FAILED},
     * {@link #STATE_CANCELED}
     * </p>
     */
    public static final int STATE_QUEUED = 2;

    /**
     * Print job state: The print job is being printed.
     * <p>
     * Next valid states: {@link #STATE_COMPLETED}, {@link #STATE_FAILED},
     * {@link #STATE_CANCELED}, {@link #STATE_BLOCKED}
     * </p>
     */
    public static final int STATE_STARTED = 3;

    private PrintJobInfo() {
    }
}
