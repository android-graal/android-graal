package android.app.slice;

import android.os.Parcelable;

/**
 * A SliceItem is a single unit in the tree structure of a {@link Slice}.
 *
 * A SliceItem a piece of content and some hints about what that content
 * means or how it should be displayed. The types of content can be:
 * <li>{@link #FORMAT_SLICE}</li>
 * <li>{@link #FORMAT_TEXT}</li>
 * <li>{@link #FORMAT_IMAGE}</li>
 * <li>{@link #FORMAT_ACTION}</li>
 * <li>{@link #FORMAT_INT}</li>
 * <li>{@link #FORMAT_LONG}</li>
 * <li>{@link #FORMAT_REMOTE_INPUT}</li>
 * <li>{@link #FORMAT_BUNDLE}</li>
 *
 * The hints that a {@link SliceItem} are a set of strings which annotate
 * the content. The hints that are guaranteed to be understood by the system
 * are defined on {@link Slice}.
 * @deprecated Slice framework has been deprecated, it will not receive any updates from
 *          {@link android.os.Build.VANILLA_ICE_CREAM} and forward. If you are looking for a
 *          framework that sends displayable data from one app to another, consider using
 *          {@link android.app.appsearch.AppSearchManager}.
 */
@Deprecated
public final class SliceItem implements Parcelable {
    /**
     * A {@link SliceItem} that contains a {@link PendingIntent}
     *
     * Note: Actions contain 2 pieces of data, In addition to the pending intent, the
     * item contains a {@link Slice} that the action applies to.
     */
    @Deprecated
    public static final String FORMAT_ACTION = "action";

    /**
     * A {@link SliceItem} that contains a {@link Bundle}.
     */
    @Deprecated
    public static final String FORMAT_BUNDLE = "bundle";

    /**
     * A {@link SliceItem} that contains an {@link Icon}
     */
    @Deprecated
    public static final String FORMAT_IMAGE = "image";

    /**
     * A {@link SliceItem} that contains an int.
     */
    @Deprecated
    public static final String FORMAT_INT = "int";

    /**
     * A {@link SliceItem} that contains a long.
     */
    @Deprecated
    public static final String FORMAT_LONG = "long";

    /**
     * A {@link SliceItem} that contains a {@link RemoteInput}.
     */
    @Deprecated
    public static final String FORMAT_REMOTE_INPUT = "input";

    /**
     * A {@link SliceItem} that contains a {@link Slice}
     */
    @Deprecated
    public static final String FORMAT_SLICE = "slice";

    /**
     * A {@link SliceItem} that contains a {@link CharSequence}
     */
    @Deprecated
    public static final String FORMAT_TEXT = "text";

    private SliceItem() {
    }
}
