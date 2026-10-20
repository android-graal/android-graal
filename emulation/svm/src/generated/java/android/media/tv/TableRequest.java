package android.media.tv;

import android.os.Parcelable;

/**
 * A request for Table from broadcast signal.
 */
public final class TableRequest implements Parcelable {
    /**
     * Bouquet Association Table
     */
    public static final int TABLE_NAME_BAT = 4;

    /**
     * Conditional Access Table
     */
    public static final int TABLE_NAME_CAT = 2;

    /**
     * Event Information Table
     */
    public static final int TABLE_NAME_EIT = 6;

    /**
     * Network Information Table
     */
    public static final int TABLE_NAME_NIT = 3;

    /**
     * Program Association Table
     */
    public static final int TABLE_NAME_PAT = 0;

    /**
     * Program Mapping Table
     */
    public static final int TABLE_NAME_PMT = 1;

    /**
     * Service Description Table
     */
    public static final int TABLE_NAME_SDT = 5;

    /**
     * Selection Information Table
     */
    public static final int TABLE_NAME_SIT = 9;

    /**
     * Time and Date Table
     */
    public static final int TABLE_NAME_TDT = 7;

    /**
     * Time Offset Table
     */
    public static final int TABLE_NAME_TOT = 8;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private TableRequest() {
    }
}
