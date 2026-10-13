package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about a particular security permission
 * group known to the system.  This corresponds to information collected from the
 * AndroidManifest.xml's &lt;permission-group&gt; tags.
 */
public final class PermissionGroupInfo implements Parcelable {
    /**
     * Flag for {@link #flags}, corresponding to <code>personalInfo</code>
     * value of {@link android.R.attr#permissionGroupFlags}.
     */
    public static final int FLAG_PERSONAL_INFO = 1;

    private PermissionGroupInfo() {
    }
}
