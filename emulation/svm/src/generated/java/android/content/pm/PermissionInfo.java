package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about a particular security permission
 * known to the system.  This corresponds to information collected from the
 * AndroidManifest.xml's &lt;permission&gt; tags.
 */
public final class PermissionInfo implements Parcelable {
    /**
     * Flag for {@link #flags}, corresponding to <code>costsMoney</code>
     * value of {@link android.R.attr#permissionFlags}.
     */
    public static final int FLAG_COSTS_MONEY = 1;

    /**
     * Flag for {@link #flags}, corresponding to <code>hardRestricted</code>
     * value of {@link android.R.attr#permissionFlags}.
     *
     * <p> This permission is restricted by the platform and it would be
     * grantable only to apps that meet special criteria per platform
     * policy.
     */
    public static final int FLAG_HARD_RESTRICTED = 4;

    /**
     * Flag for {@link #flags}, corresponding to <code>immutablyRestricted</code>
     * value of {@link android.R.attr#permissionFlags}.
     *
     * <p>This permission is restricted immutably which means that its
     * restriction state may be specified only on the first install of
     * the app and will stay in this initial allowlist state until
     * the app is uninstalled.
     */
    public static final int FLAG_IMMUTABLY_RESTRICTED = 16;

    /**
     * Flag for {@link #flags}, indicating that this permission has been
     * installed into the system's globally defined permissions.
     */
    public static final int FLAG_INSTALLED = 1073741824;

    /**
     * Flag for {@link #flags}, corresponding to <code>softRestricted</code>
     * value of {@link android.R.attr#permissionFlags}.
     *
     * <p>This permission is restricted by the platform and it would be
     * grantable in its full form to apps that meet special criteria
     * per platform policy. Otherwise, a weaker form of the permission
     * would be granted. The weak grant depends on the permission.
     */
    public static final int FLAG_SOFT_RESTRICTED = 8;

    /**
     * Dangerous value for {@link #protectionLevel}, corresponding
     * to the <code>dangerous</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_DANGEROUS = 1;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>appop</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_APPOP = 64;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>development</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_DEVELOPMENT = 32;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>installer</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_INSTALLER = 256;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>instant</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_INSTANT = 4096;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>pre23</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_PRE23 = 128;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>preinstalled</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_PREINSTALLED = 1024;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>privileged</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_PRIVILEGED = 16;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>runtime</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_RUNTIME_ONLY = 8192;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>setup</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_SETUP = 2048;

    /**
     * @deprecated Old name for {@link #PROTECTION_FLAG_PRIVILEGED}, which
     * is now very confusing because it only applies to privileged apps, not all
     * apps on the system image.
     */
    @Deprecated
    public static final int PROTECTION_FLAG_SYSTEM = 16;

    /**
     * Additional flag for {@link #protectionLevel}, corresponding
     * to the <code>verifier</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_FLAG_VERIFIER = 512;

    /**
     * System-level value for {@link #protectionLevel}, corresponding
     * to the <code>internal</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_INTERNAL = 4;

    /**
     * Mask for {@link #protectionLevel}: the basic protection type.
     *
     * @deprecated Use #getProtection() instead.
     */
    @Deprecated
    public static final int PROTECTION_MASK_BASE = 15;

    /**
     * Mask for {@link #protectionLevel}: additional flag bits.
     *
     * @deprecated Use #getProtectionFlags() instead.
     */
    @Deprecated
    public static final int PROTECTION_MASK_FLAGS = 65520;

    /**
     * A normal application value for {@link #protectionLevel}, corresponding
     * to the <code>normal</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_NORMAL = 0;

    /**
     * System-level value for {@link #protectionLevel}, corresponding
     * to the <code>signature</code> value of
     * {@link android.R.attr#protectionLevel}.
     */
    public static final int PROTECTION_SIGNATURE = 2;

    /**
     * @deprecated Use {@link #PROTECTION_SIGNATURE}|{@link #PROTECTION_FLAG_PRIVILEGED}
     * instead.
     */
    @Deprecated
    public static final int PROTECTION_SIGNATURE_OR_SYSTEM = 3;

    private PermissionInfo() {
    }
}
