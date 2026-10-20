package android.content.pm.verify.domain;

import android.os.Parcelable;

/**
 * Contains the user selection state for a package. This means all web HTTP(S) domains declared by a
 * package in its manifest, whether or not they were marked for auto verification.
 * <p>
 * Applications should use {@link #getHostToStateMap()} if necessary to
 * check if/how they are verified for a domain, which is required starting from platform
 * {@link android.os.Build.VERSION_CODES#S} in order to open {@link Intent}s which declare
 * {@link Intent#CATEGORY_BROWSABLE} or no category and also match against
 * {@link Intent#CATEGORY_DEFAULT} {@link android.content.IntentFilter}s, either through an
 * explicit declaration of {@link Intent#CATEGORY_DEFAULT} or through the use of
 * {@link android.content.pm.PackageManager#MATCH_DEFAULT_ONLY}, which is usually added for the
 * caller when using {@link Context#startActivity(Intent)} and similar.
 * <p>
 * By default, all apps are allowed to automatically open links for the above case for domains that
 * they've successfully verified against. This is reflected by {@link #isLinkHandlingAllowed()}.
 * The user can decide to disable this, disallowing the application from opening all links. Note
 * that the toggle affects <b>all</b> links and is not based on the verification state of the
 * domains.
 * <p>
 * Assuming the toggle is enabled, the user can also select additional unverified domains to grant
 * to the application to open, which is reflected in {@link #getHostToStateMap()}. But only a single
 * application can be approved for a domain unless the applications are both approved. If another
 * application is approved, the user will not be allowed to enable the domain.
 */
public final class DomainVerificationUserState implements Parcelable {
    /**
     * The domain is unverified and unselected, and the application is unable to open web links
     * that resolve to the domain.
     */
    public static final int DOMAIN_STATE_NONE = 0;

    /**
     * The domain has been selected by the user. This may be reset to {@link #DOMAIN_STATE_NONE} if
     * another application is selected or verified for the same domain.
     */
    public static final int DOMAIN_STATE_SELECTED = 1;

    /**
     * The domain has been previously verified by the domain verification agent.
     */
    public static final int DOMAIN_STATE_VERIFIED = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private DomainVerificationUserState() {
    }
}
