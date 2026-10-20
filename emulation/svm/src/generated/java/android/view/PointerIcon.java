package android.view;

import android.os.Parcelable;

/**
 * Represents an icon that can be used as a mouse pointer.
 * <p>
 * Pointer icons can be provided either by the system using system types,
 * or by applications using bitmaps or application resources.
 * </p>
 */
public final class PointerIcon implements Parcelable {
    /**
     * Type constant: alias (indicating an alias of/shortcut to something is
     *  to be created.
     */
    public static final int TYPE_ALIAS = 1010;

    /**
     * Type constant: all-scroll.
     */
    public static final int TYPE_ALL_SCROLL = 1013;

    /**
     * Type constant: Arrow icon.  (Default mouse pointer)
     */
    public static final int TYPE_ARROW = 1000;

    /**
     * Type constant: cell.
     */
    public static final int TYPE_CELL = 1006;

    /**
     * Type constant: context-menu.
     */
    public static final int TYPE_CONTEXT_MENU = 1001;

    /**
     * Type constant: copy.
     */
    public static final int TYPE_COPY = 1011;

    /**
     * Type constant: crosshair.
     */
    public static final int TYPE_CROSSHAIR = 1007;

    /**
     * The default pointer icon.
     * @deprecated This is the same as using {@link #TYPE_ARROW}. Use {@link #TYPE_ARROW} to
     *     explicitly show an arrow, or use a {@code null} {@link PointerIcon} with
     *     {@link View#setPointerIcon(PointerIcon)} or
     *     {@link View#onResolvePointerIcon(MotionEvent, int)} instead to show
     *     the default pointer icon.
     */
    @Deprecated
    public static final int TYPE_DEFAULT = 1000;

    /**
     * Type constant: grab.
     */
    public static final int TYPE_GRAB = 1020;

    /**
     * Type constant: grabbing.
     */
    public static final int TYPE_GRABBING = 1021;

    /**
     * Type constant: hand.
     */
    public static final int TYPE_HAND = 1002;

    /**
     * Type constant: handwriting.
     */
    public static final int TYPE_HANDWRITING = 1022;

    /**
     * Type constant: help.
     */
    public static final int TYPE_HELP = 1003;

    /**
     * Type constant: horizontal double arrow mainly for resizing.
     */
    public static final int TYPE_HORIZONTAL_DOUBLE_ARROW = 1014;

    /**
     * Type constant: no-drop.
     */
    public static final int TYPE_NO_DROP = 1012;

    /**
     * Type constant: Null icon.  It has no bitmap.
     */
    public static final int TYPE_NULL = 0;

    /**
     * Type constant: text.
     */
    public static final int TYPE_TEXT = 1008;

    /**
     * Type constant: diagonal double arrow -- top-left to bottom-right.
     */
    public static final int TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW = 1017;

    /**
     * Type constant: diagonal double arrow -- top-right to bottom-left.
     */
    public static final int TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW = 1016;

    /**
     * Type constant: vertical double arrow mainly for resizing.
     */
    public static final int TYPE_VERTICAL_DOUBLE_ARROW = 1015;

    /**
     * Type constant: vertical-text.
     */
    public static final int TYPE_VERTICAL_TEXT = 1009;

    /**
     * Type constant: wait.
     */
    public static final int TYPE_WAIT = 1004;

    /**
     * Type constant: zoom-in.
     */
    public static final int TYPE_ZOOM_IN = 1018;

    /**
     * Type constant: zoom-out.
     */
    public static final int TYPE_ZOOM_OUT = 1019;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PointerIcon() {
    }
}
