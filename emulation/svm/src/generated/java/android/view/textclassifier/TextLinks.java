package android.view.textclassifier;

import android.os.Parcelable;

/**
 * A collection of links, representing subsequences of text and the entity types (phone number,
 * address, url, etc) they may be.
 */
public final class TextLinks implements Parcelable {
    /**
     * Do not replace {@link ClickableSpan}s that exist where the {@link TextLinkSpan} needs to
     * be applied to. Do not apply the TextLinkSpan.
     */
    public static final int APPLY_STRATEGY_IGNORE = 0;

    /**
     * Replace any {@link ClickableSpan}s that exist where the {@link TextLinkSpan} needs to be
     * applied to.
     */
    public static final int APPLY_STRATEGY_REPLACE = 1;

    /**
     * The specified text does not match the text used to generate the links.
     */
    public static final int STATUS_DIFFERENT_TEXT = 3;

    /**
     * Links were successfully applied to the text.
     */
    public static final int STATUS_LINKS_APPLIED = 0;

    /**
     * No links applied to text. The links were filtered out.
     */
    public static final int STATUS_NO_LINKS_APPLIED = 2;

    /**
     * No links exist to apply to text. Links count is zero.
     */
    public static final int STATUS_NO_LINKS_FOUND = 1;

    /**
     * The specified text contains unsupported characters.
     */
    public static final int STATUS_UNSUPPORTED_CHARACTER = 4;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TextLinks() {
    }
}
