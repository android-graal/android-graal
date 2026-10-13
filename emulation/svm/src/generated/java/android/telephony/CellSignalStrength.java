package android.telephony;

/**
 * Abstract base class for cell phone signal strength related information.
 */
public final class CellSignalStrength {
    public static final int SIGNAL_STRENGTH_GOOD = 3;

    public static final int SIGNAL_STRENGTH_GREAT = 4;

    public static final int SIGNAL_STRENGTH_MODERATE = 2;

    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN = 0;

    public static final int SIGNAL_STRENGTH_POOR = 1;

    private CellSignalStrength() {
    }
}
