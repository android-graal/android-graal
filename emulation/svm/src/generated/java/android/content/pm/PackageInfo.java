package android.content.pm;

import android.os.Parcelable;

/**
 * Overall information about the contents of a package.  This corresponds
 * to all of the information collected from AndroidManifest.xml.
 */
public final class PackageInfo implements Parcelable {
    /**
     * Constant corresponding to <code>auto</code> in the
     * {@link android.R.attr#installLocation} attribute.
     */
    public static final int INSTALL_LOCATION_AUTO = 0;

    /**
     * Constant corresponding to <code>internalOnly</code> in the
     * {@link android.R.attr#installLocation} attribute.
     */
    public static final int INSTALL_LOCATION_INTERNAL_ONLY = 1;

    /**
     * Constant corresponding to <code>preferExternal</code> in the
     * {@link android.R.attr#installLocation} attribute.
     */
    public static final int INSTALL_LOCATION_PREFER_EXTERNAL = 2;

    /**
     * Flag for {@link #requestedPermissionsFlags}: the requested permission
     * is currently granted to the application.
     */
    public static final int REQUESTED_PERMISSION_GRANTED = 2;

    /**
     * Flag for {@link #requestedPermissionsFlags}: the requested permission was
     * not explicitly requested via uses-permission, but was instead implicitly
     * requested (e.g., for version compatibility reasons).
     */
    public static final int REQUESTED_PERMISSION_IMPLICIT = 4;

    /**
     * Flag for {@link #requestedPermissionsFlags}: the requested permission has
     * declared {@code neverForLocation} in their manifest as a strong assertion
     * by a developer that they will never use this permission to derive the
     * physical location of the device, regardless of
     * {@link android.Manifest.permission#ACCESS_FINE_LOCATION} and/or
     * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION} being granted.
     */
    public static final int REQUESTED_PERMISSION_NEVER_FOR_LOCATION = 65536;

    private PackageInfo() {
    }
}
