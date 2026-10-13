package android.os.ext;

/**
 * Methods for interacting with the extension SDK.
 *
 * <p>This class provides information about the extension SDK versions present on this device. Use
 * the {@link #getExtensionVersion(int) getExtension} method to lookup the version of a given
 * extension.
 *
 * <p>The extension version advances as the platform evolves and new APIs are added, so is suitable
 * to use for determining API availability at runtime.
 */
public final class SdkExtensions {
    public static final int AD_SERVICES = 1000000;

    private SdkExtensions() {
    }
}
