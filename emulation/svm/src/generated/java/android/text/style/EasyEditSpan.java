package android.text.style;

import android.text.ParcelableSpan;

/**
 * Provides an easy way to edit a portion of text.
 * <p>
 * The {@link TextView} uses this span to allow the user to delete a chuck of text in one click.
 * <p>
 * {@link TextView} removes the span when the user deletes the whole text or modifies it.
 * <p>
 * This span can be also used to receive notification when the user deletes or modifies the text;
 */
public abstract class EasyEditSpan implements ParcelableSpan {
    /**
     * The extra key field in the pending intent that describes how the text changed.
     *
     * @see #TEXT_DELETED
     * @see #TEXT_MODIFIED
     */
    public static final String EXTRA_TEXT_CHANGED_TYPE = "android.text.style.EXTRA_TEXT_CHANGED_TYPE";

    /**
     * The value of {@link #EXTRA_TEXT_CHANGED_TYPE} when the text wrapped by this span is deleted.
     */
    public static final int TEXT_DELETED = 1;

    /**
     * The value of {@link #EXTRA_TEXT_CHANGED_TYPE} when the text wrapped by this span is modified.
     */
    public static final int TEXT_MODIFIED = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpanTypeId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private EasyEditSpan() {
    }
}
