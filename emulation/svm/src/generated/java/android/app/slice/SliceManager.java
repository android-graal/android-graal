package android.app.slice;

/**
 * Class to handle interactions with {@link Slice}s.
 * <p>
 * The SliceManager manages permissions and pinned state for slices.
 * @deprecated Slice framework has been deprecated, it will not receive any updates from
 *          {@link android.os.Build.VANILLA_ICE_CREAM} and forward. If you are looking for a
 *          framework that sends displayable data from one app to another, consider using
 *          {@link android.app.appsearch.AppSearchManager}.
 */
@Deprecated
public final class SliceManager {
    /**
     * Category used to resolve intents that can be rendered as slices.
     * <p>
     * This category should be included on intent filters on providers that extend
     * {@link SliceProvider}.
     * @see SliceProvider
     * @see SliceProvider#onMapIntentToUri(Intent)
     * @see #mapIntentToUri(Intent)
     */
    @Deprecated
    public static final String CATEGORY_SLICE = "android.app.slice.category.SLICE";

    /**
     * The meta-data key that allows an activity to easily be linked directly to a slice.
     * <p>
     * An activity can be statically linked to a slice uri by including a meta-data item
     * for this key that contains a valid slice uri for the same application declaring
     * the activity.
     *
     * <pre class="prettyprint">
     * {@literal
     * <activity android:name="com.example.mypkg.MyActivity">
     *     <meta-data android:name="android.metadata.SLICE_URI"
     *                android:value="content://com.example.mypkg/main_slice" />
     *  </activity>}
     * </pre>
     *
     * @see #mapIntentToUri(Intent)
     * @see SliceProvider#onMapIntentToUri(Intent)
     */
    @Deprecated
    public static final String SLICE_METADATA_KEY = "android.metadata.SLICE_URI";

    private SliceManager() {
    }
}
