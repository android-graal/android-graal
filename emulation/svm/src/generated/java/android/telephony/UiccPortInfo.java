package android.telephony;

import android.os.Parcelable;

/**
 * UiccPortInfo class represents information about a single port contained on {@link UiccCardInfo}.
 * Per GSMA SGP.22 V3.0, a port is a logical entity to which an active UICC profile can be bound on
 * a UICC card. If UICC supports 2 ports, then the port index is numbered 0,1.
 * Each port index is unique within an UICC, but not necessarily unique across UICC’s.
 * For UICC's does not support MEP(Multi-enabled profile)
 * {@link android.content.pm.PackageManager#FEATURE_TELEPHONY_EUICC_MEP}, just return the default
 * port index 0.
 */
public final class UiccPortInfo implements Parcelable {
    /**
     * A redacted String if caller does not have permission to read ICCID.
     */
    public static final String ICCID_REDACTED = "FFFFFFFFFFFFFFFFFFFF";

    private UiccPortInfo() {
    }
}
