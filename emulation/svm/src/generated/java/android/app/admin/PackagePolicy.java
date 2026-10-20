package android.app.admin;

import android.os.Parcelable;

/**
 * A generic class that defines which APK packages are in scope for some device policy.
 * <p>
 * The packages can be defined using either an allowlist or a blocklist.
 * In allowlist mode, it could optionally include all system packages
 * that meet the specific criteria of the device policy in question.
 */
public final class PackagePolicy implements Parcelable {
    /**
     * PackagePolicy type indicator for {@link PackagePolicy}
     * <p>
     * This constant indicates that all packages are denied except for the packages returned by
     * {@link PackagePolicy#getPackageNames()}, which acts as an allowlist.
     *
     * @see #PACKAGE_POLICY_BLOCKLIST
     * @see #PACKAGE_POLICY_ALLOWLIST_AND_SYSTEM
     */
    public static final int PACKAGE_POLICY_ALLOWLIST = 3;

    /**
     * PackagePolicy type indicator for {@link PackagePolicy}
     * <p>
     * This constant indicates system packages are allowed in addition to the packages returned by
     * {@link PackagePolicy#getPackageNames()}, which acts as an allowlist.
     *
     * <p>Functions that accept {@link PackagePolicy} will further clarify
     * how this policy is interpreted.
     *
     * @see #PACKAGE_POLICY_BLOCKLIST
     * @see #PACKAGE_POLICY_ALLOWLIST
     */
    public static final int PACKAGE_POLICY_ALLOWLIST_AND_SYSTEM = 2;

    /**
     * PackagePolicy type indicator for {@link PackagePolicy}
     * <p>
     * This constant indicates that all packages are allowed except for the packages returned by
     * {@link PackagePolicy#getPackageNames()}, which acts as a denylist.
     * @see #PACKAGE_POLICY_ALLOWLIST_AND_SYSTEM
     * @see #PACKAGE_POLICY_ALLOWLIST
     */
    public static final int PACKAGE_POLICY_BLOCKLIST = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private PackagePolicy() {
    }
}
