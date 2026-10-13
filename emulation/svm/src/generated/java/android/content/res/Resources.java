package android.content.res;

/**
 * Class for accessing an application's resources.  This sits on top of the
 * asset manager of the application (accessible through {@link #getAssets}) and
 * provides a high-level API for getting typed data from the assets.
 *
 * <p>The Android resource system keeps track of all non-code assets associated with an
 * application. You can use this class to access your application's resources. You can generally
 * acquire the {@link android.content.res.Resources} instance associated with your application
 * with {@link android.content.Context#getResources getResources()}.</p>
 *
 * <p>The Android SDK tools compile your application's resources into the application binary
 * at build time.  To use a resource, you must install it correctly in the source tree (inside
 * your project's {@code res/} directory) and build your application.  As part of the build
 * process, the SDK tools generate symbols for each resource, which you can use in your application
 * code to access the resources.</p>
 *
 * <p>Using application resources makes it easy to update various characteristics of your
 * application without modifying code, and&mdash;by providing sets of alternative
 * resources&mdash;enables you to optimize your application for a variety of device configurations
 * (such as for different languages and screen sizes). This is an important aspect of developing
 * Android applications that are compatible on different types of devices.</p>
 *
 * <p>After {@link Build.VERSION_CODES#R}, {@link Resources} must be obtained by
 * {@link android.app.Activity} or {@link android.content.Context} created with
 * {@link android.content.Context#createWindowContext(int, Bundle)}.
 * {@link Application#getResources()} may report wrong values in multi-window or on secondary
 * displays.
 *
 * <p>For more information about using resources, see the documentation about <a
 * href="{@docRoot}guide/topics/resources/index.html">Application Resources</a>.</p>
 */
public final class Resources {
    /**
     * The {@code null} resource ID. This denotes an invalid resource ID that is returned by the
     * system when a resource is not found or the value is set to {@code @null} in XML.
     */
    public static final int ID_NULL = 0;

    private Resources() {
    }
}
