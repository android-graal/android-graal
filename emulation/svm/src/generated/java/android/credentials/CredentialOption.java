package android.credentials;

import android.os.Parcelable;

/**
 * Information about a specific type of credential to be requested during a {@link
 * CredentialManager#getCredential} operation.
 */
public final class CredentialOption implements Parcelable {
    /**
     * Bundle key to the list of elements keys supported/requested. Framework will use this key
     * to determine which types of Credentials will utilize Credential Registry when filtering
     * Credential Providers to ping.
     */
    public static final String SUPPORTED_ELEMENT_KEYS = "android.credentials.GetCredentialOption.SUPPORTED_ELEMENT_KEYS";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private CredentialOption() {
    }
}
