package android.telecom;

import android.os.Parcelable;

public final class PhoneAccountSuggestion implements Parcelable {
    /**
     * Indicates that the {@link PhoneAccountHandle} is suggested because the user uses it
     * frequently for the number that we are calling.
     */
    public static final int REASON_FREQUENT = 2;

    /**
     * Indicates that the {@link PhoneAccountHandle} is suggested because the number we're calling
     * is on the same carrier, and therefore may have lower rates.
     */
    public static final int REASON_INTRA_CARRIER = 1;

    /**
     * Indicates that this account is not suggested for use, but is still available.
     */
    public static final int REASON_NONE = 0;

    /**
     * Indicates that the {@link PhoneAccountHandle} is suggested for a reason not otherwise
     * enumerated here.
     */
    public static final int REASON_OTHER = 4;

    /**
     * Indicates that the {@link PhoneAccountHandle} is suggested because the user explicitly
     * specified that it be used for the number we are calling.
     */
    public static final int REASON_USER_SET = 3;

    private PhoneAccountSuggestion() {
    }
}
