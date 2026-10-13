package android.app.slice;

/**
 * A SliceProvider allows an app to provide content to be displayed in system spaces. This content
 * is templated and can contain actions, and the behavior of how it is surfaced is specific to the
 * system surface.
 * <p>
 * Slices are not currently live content. They are bound once and shown to the user. If the content
 * changes due to a callback from user interaction, then
 * {@link ContentResolver#notifyChange(Uri, ContentObserver)} should be used to notify the system.
 * </p>
 * <p>
 * The provider needs to be declared in the manifest to provide the authority for the app. The
 * authority for most slices is expected to match the package of the application.
 * </p>
 *
 * <pre class="prettyprint">
 * {@literal
 * <provider
 *     android:name="com.example.mypkg.MySliceProvider"
 *     android:authorities="com.example.mypkg" />}
 * </pre>
 * <p>
 * Slices can be identified by a Uri or by an Intent. To link an Intent with a slice, the provider
 * must have an {@link IntentFilter} matching the slice intent. When a slice is being requested via
 * an intent, {@link #onMapIntentToUri(Intent)} can be called and is expected to return an
 * appropriate Uri representing the slice.
 *
 * <pre class="prettyprint">
 * {@literal
 * <provider
 *     android:name="com.example.mypkg.MySliceProvider"
 *     android:authorities="com.example.mypkg">
 *     <intent-filter>
 *         <action android:name="com.example.mypkg.intent.action.MY_SLICE_INTENT" />
 *         <category android:name="android.app.slice.category.SLICE" />
 *     </intent-filter>
 * </provider>}
 * </pre>
 *
 * @see Slice
 * @deprecated Slice framework has been deprecated, it will not receive any updates from
 *          {@link android.os.Build.VANILLA_ICE_CREAM} and forward. If you are looking for a
 *          framework that sends displayable data from one app to another, consider using
 *          {@link android.app.appsearch.AppSearchManager}.
 */
@Deprecated
public final class SliceProvider {
    /**
     * This is the Android platform's MIME type for a URI
     * containing a slice implemented through {@link SliceProvider}.
     */
    @Deprecated
    public static final String SLICE_TYPE = "vnd.android.slice";

    private SliceProvider() {
    }
}
