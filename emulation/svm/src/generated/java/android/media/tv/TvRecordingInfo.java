package android.media.tv;

import android.os.Parcelable;

/**
 * This class is used to describe the meta information for a TV recording. It can be retrieved by
 * the {@link android.media.tv.interactive.TvInteractiveAppService} by using getTvRecordingInfo
 * or getTvRecordingInfoList. It can then be updated to the TV app using setTvRecordingInfo.
 */
public final class TvRecordingInfo implements Parcelable {
    public static final int FRIDAY = 32;

    public static final int MONDAY = 2;

    public static final int RECORDING_ALL = 3;

    public static final int RECORDING_IN_PROGRESS = 2;

    public static final int RECORDING_SCHEDULED = 1;

    public static final int SATURDAY = 64;

    public static final int SUNDAY = 1;

    public static final int THURSDAY = 16;

    public static final int TUESDAY = 4;

    public static final int WEDNESDAY = 8;

    private TvRecordingInfo() {
    }
}
