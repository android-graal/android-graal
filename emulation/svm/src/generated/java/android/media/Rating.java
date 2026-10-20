package android.media;

import android.os.Parcelable;

/**
 * A class to encapsulate rating information used as content metadata.
 * A rating is defined by its rating style (see {@link #RATING_HEART},
 * {@link #RATING_THUMB_UP_DOWN}, {@link #RATING_3_STARS}, {@link #RATING_4_STARS},
 * {@link #RATING_5_STARS} or {@link #RATING_PERCENTAGE}) and the actual rating value (which may
 * be defined as "unrated"), both of which are defined when the rating instance is constructed
 * through one of the factory methods.
 */
public final class Rating implements Parcelable {
    /**
     * A rating style with 0 to 3 stars.
     */
    public static final int RATING_3_STARS = 3;

    /**
     * A rating style with 0 to 4 stars.
     */
    public static final int RATING_4_STARS = 4;

    /**
     * A rating style with 0 to 5 stars.
     */
    public static final int RATING_5_STARS = 5;

    /**
     * A rating style with a single degree of rating, "heart" vs "no heart". Can be used to
     * indicate the content referred to is a favorite (or not).
     */
    public static final int RATING_HEART = 1;

    /**
     * Indicates a rating style is not supported. A Rating will never have this
     * type, but can be used by other classes to indicate they do not support
     * Rating.
     */
    public static final int RATING_NONE = 0;

    /**
     * A rating style expressed as a percentage.
     */
    public static final int RATING_PERCENTAGE = 6;

    /**
     * A rating style for "thumb up" vs "thumb down".
     */
    public static final int RATING_THUMB_UP_DOWN = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Rating() {
    }
}
