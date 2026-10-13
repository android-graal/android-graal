package android.service.dreams;

/**
 * Extend this class to implement a custom dream (available to the user as a "Daydream").
 *
 * <p>Dreams are interactive screensavers launched when a charging device is idle, or docked in a
 * desk dock. Dreams provide another modality for apps to express themselves, tailored for
 * an exhibition/lean-back experience.</p>
 *
 * <p>The {@code DreamService} lifecycle is as follows:</p>
 * <ol>
 *   <li>{@link #onAttachedToWindow}
 *     <p>Use this for initial setup, such as calling {@link #setContentView setContentView()}.</li>
 *   <li>{@link #onDreamingStarted}
 *     <p>Your dream has started, so you should begin animations or other behaviors here.</li>
 *   <li>{@link #onDreamingStopped}
 *     <p>Use this to stop the things you started in {@link #onDreamingStarted}.</li>
 *   <li>{@link #onDetachedFromWindow}
 *     <p>Use this to dismantle resources (for example, detach from handlers
 *        and listeners).</li>
 * </ol>
 *
 * <p>In addition, onCreate and onDestroy (from the Service interface) will also be called, but
 * initialization and teardown should be done by overriding the hooks above.</p>
 *
 * <p>To be available to the system, your {@code DreamService} should be declared in the
 * manifest as follows:</p>
 * <pre>
 * &lt;service
 *     android:name=".MyDream"
 *     android:exported="true"
 *     android:icon="@drawable/my_icon"
 *     android:label="@string/my_dream_label" >
 *
 *     &lt;intent-filter>
 *         &lt;action android:name="android.service.dreams.DreamService" />
 *         &lt;category android:name="android.intent.category.DEFAULT" />
 *     &lt;/intent-filter>
 *
 *     &lt;!-- Point to additional information for this dream (optional) -->
 *     &lt;meta-data
 *         android:name="android.service.dream"
 *         android:resource="@xml/my_dream" />
 * &lt;/service>
 * </pre>
 *
 * <p>If specified with the {@code <meta-data>} element,
 * additional information for the dream is defined using the
 * {@link android.R.styleable#Dream &lt;dream&gt;} element in a separate XML file.
 * Currently, the only additional
 * information you can provide is for a settings activity that allows the user to configure
 * the dream behavior. For example:</p>
 * <p class="code-caption">res/xml/my_dream.xml</p>
 * <pre>
 * &lt;dream xmlns:android="http://schemas.android.com/apk/res/android"
 *     android:settingsActivity="com.example.app/.MyDreamSettingsActivity" />
 * </pre>
 * <p>This makes a Settings button available alongside your dream's listing in the
 * system settings, which when pressed opens the specified activity.</p>
 *
 *
 * <p>To specify your dream layout, call {@link #setContentView}, typically during the
 * {@link #onAttachedToWindow} callback. For example:</p>
 * <pre>
 * public class MyDream extends DreamService {
 *
 *     &#64;Override
 *     public void onAttachedToWindow() {
 *         super.onAttachedToWindow();
 *
 *         // Exit dream upon user touch
 *         setInteractive(false);
 *         // Hide system UI
 *         setFullscreen(true);
 *         // Set the dream layout
 *         setContentView(R.layout.dream);
 *     }
 * }
 * </pre>
 *
 * <p>When targeting api level 21 and above, you must declare the service in your manifest file
 * with the {@link android.Manifest.permission#BIND_DREAM_SERVICE} permission. For example:</p>
 * <pre>
 * &lt;service
 *     android:name=".MyDream"
 *     android:exported="true"
 *     android:icon="@drawable/my_icon"
 *     android:label="@string/my_dream_label"
 *     android:permission="android.permission.BIND_DREAM_SERVICE">
 *   &lt;intent-filter>
 *     &lt;action android:name=”android.service.dreams.DreamService” />
 *     &lt;category android:name=”android.intent.category.DEFAULT” />
 *   &lt;/intent-filter>
 * &lt;/service>
 * </pre>
 */
public final class DreamService {
    /**
     * Name under which a Dream publishes information about itself.
     * This meta-data must reference an XML resource containing
     * a <code>&lt;{@link android.R.styleable#Dream dream}&gt;</code>
     * tag.
     */
    public static final String DREAM_META_DATA = "android.service.dream";

    /**
     * The {@link Intent} that must be declared as handled by the service.
     */
    public static final String SERVICE_INTERFACE = "android.service.dreams.DreamService";

    private DreamService() {
    }
}
