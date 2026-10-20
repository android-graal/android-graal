package android.nfc.cardemulation;

import android.os.Parcelable;

/**
 * Polling Frames represent data about individual frames of an NFC polling loop. These frames will
 * be delivered to subclasses of {@link HostApduService} that have registered filters with
 * {@link CardEmulation#registerPollingLoopFilterForService(ComponentName, String, boolean)} that
 * match a given frame in a loop and will be delivered through calls to
 * {@link HostApduService#processPollingFrames(List)}.
 */
public final class PollingFrame implements Parcelable {
    /**
     * POLLING_LOOP_TYPE_A is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop is for NFC-A.
     */
    public static final int POLLING_LOOP_TYPE_A = 65;

    /**
     * POLLING_LOOP_TYPE_B is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop is for NFC-B.
     */
    public static final int POLLING_LOOP_TYPE_B = 66;

    /**
     * POLLING_LOOP_TYPE_F is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop is for NFC-F.
     */
    public static final int POLLING_LOOP_TYPE_F = 70;

    /**
     * POLLING_LOOP_TYPE_OFF is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop turns off.
     */
    public static final int POLLING_LOOP_TYPE_OFF = 88;

    /**
     * POLLING_LOOP_TYPE_ON is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop turns on.
     */
    public static final int POLLING_LOOP_TYPE_ON = 79;

    /**
     * POLLING_LOOP_TYPE_UNKNOWN is the value associated with the key
     * POLLING_LOOP_TYPE  in the Bundle passed to {@link HostApduService#processPollingFrames(List)}
     * when the polling loop frame isn't recognized.
     */
    public static final int POLLING_LOOP_TYPE_UNKNOWN = 85;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PollingFrame() {
    }
}
