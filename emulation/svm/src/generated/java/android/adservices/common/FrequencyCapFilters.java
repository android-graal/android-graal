package android.adservices.common;

import android.os.Parcelable;

/**
 * A container for the ad filters that are based on frequency caps.
 *
 * <p>No more than 20 frequency cap filters may be associated with a single ad.
 *
 * <p>Frequency caps filters combine an event type with a list of {@link KeyedFrequencyCap} objects
 * to define a collection of ad filters. If any of these frequency caps are exceeded for a given ad,
 * the ad will be removed from the group of ads submitted to a buyer adtech's bidding function.
 */
public final class FrequencyCapFilters implements Parcelable {
    public static final int AD_EVENT_TYPE_CLICK = 3;

    public static final int AD_EVENT_TYPE_IMPRESSION = 1;

    public static final int AD_EVENT_TYPE_VIEW = 2;

    /**
     * The WIN ad event type is automatically populated within the Protected Audience service for
     * any winning ad which is returned from Protected Audience ad selection.
     *
     * <p>It should not be used to manually update an ad counter histogram.
     */
    public static final int AD_EVENT_TYPE_WIN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private FrequencyCapFilters() {
    }
}
