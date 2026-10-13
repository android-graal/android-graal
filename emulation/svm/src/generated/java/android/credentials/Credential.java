package android.credentials;

import android.os.Parcelable;

/**
 * Represents a user credential that can be used to authenticate to your app.
 */
public final class Credential implements Parcelable {
    /**
     * The type value for password credential related operations.
     */
    public static final String TYPE_PASSWORD_CREDENTIAL = "android.credentials.TYPE_PASSWORD_CREDENTIAL";

    private Credential() {
    }
}
