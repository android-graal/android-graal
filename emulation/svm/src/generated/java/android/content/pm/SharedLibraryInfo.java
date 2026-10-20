package android.content.pm;

import android.os.Parcelable;

/**
 * This class provides information for a shared library. There are
 * four types of shared libraries: builtin - non-updatable part of
 * the OS; dynamic - updatable backwards-compatible dynamically linked;
 * static - non backwards-compatible emulating static linking;
 * SDK - updatable backwards-incompatible dynamically loaded.
 */
public final class SharedLibraryInfo implements Parcelable {
    /**
     * Shared library type: this library is a part of the OS
     * and cannot be updated or uninstalled.
     */
    public static final int TYPE_BUILTIN = 0;

    /**
     * Shared library type: this library is backwards-compatible, can
     * be updated, and updates can be uninstalled. Clients link against
     * the latest version of the library.
     */
    public static final int TYPE_DYNAMIC = 1;

    /**
     * SDK package shared library type: this library is <strong>not</strong>
     * compatible between versions, can be updated and updates can be
     * uninstalled. Clients depend on a specific version of the library.
     *
     * SDK packages are not loaded automatically by the OS and rely
     * e.g. on 3P libraries to make them available for the clients.
     */
    public static final int TYPE_SDK_PACKAGE = 3;

    /**
     * Shared library type: this library is <strong>not</strong> backwards
     * -compatible, can be updated and updates can be uninstalled. Clients
     * link against a specific version of the library.
     *
     * Static shared libraries simulate static linking while allowing for
     * multiple clients to reuse the same instance of the library.
     */
    public static final int TYPE_STATIC = 2;

    /**
     * Constant for referring to an undefined version.
     */
    public static final int VERSION_UNDEFINED = -1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private SharedLibraryInfo() {
    }
}
