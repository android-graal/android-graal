package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about a particular application
 * activity or receiver. This corresponds to information collected
 * from the AndroidManifest.xml's &lt;activity&gt; and
 * &lt;receiver&gt; tags.
 */
public final class ActivityInfo implements Parcelable {
    /**
     * Value for {@link #colorMode} indicating that the activity should use the
     * default color mode (sRGB, low dynamic range).
     *
     * @see android.R.attr#colorMode
     */
    public static final int COLOR_MODE_DEFAULT = 0;

    /**
     * Value of {@link #colorMode} indicating that the activity should use a
     * high dynamic range if the presentation display supports it.
     *
     * <p>Note: This does not impact SurfaceViews or SurfaceControls, as those have their own
     * independent HDR support.</p>
     *
     * <p><b>Important:</b> Although this value was added in API 26, it is strongly recommended
     * to avoid using it until API 34 which is when HDR support for the UI toolkit was officially
     * added.</p>
     *
     * @see android.R.attr#colorMode
     */
    public static final int COLOR_MODE_HDR = 2;

    /**
     * Value of {@link #colorMode} indicating that the activity should use a
     * wide color gamut if the presentation display supports it.
     *
     * @see android.R.attr#colorMode
     */
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT = 1;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle asset path changes.  Set from the {@link android.R.attr#configChanges}
     * attribute. This is not a core resource configuration, but a higher-level value, so its
     * constant starts at the high bits.
     */
    public static final int CONFIG_ASSETS_PATHS = -2147483648;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the change to the display color gamut or dynamic
     * range. Set from the {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_COLOR_MODE = 16384;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle density changes. Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_DENSITY = 4096;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the font scaling factor.  Set from the
     * {@link android.R.attr#configChanges} attribute.  This is
     * not a core resource configuration, but a higher-level value, so its
     * constant starts at the high bits.
     */
    public static final int CONFIG_FONT_SCALE = 1073741824;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to font weight.  Set from the
     * {@link android.R.attr#configChanges} attribute.  This is
     * not a core resource configuration, but a higher-level value, so its
     * constant starts at the high bits.
     */
    public static final int CONFIG_FONT_WEIGHT_ADJUSTMENT = 268435456;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the change to gender. Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_GRAMMATICAL_GENDER = 32768;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the keyboard type.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_KEYBOARD = 16;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the keyboard or navigation being hidden/exposed.
     * Note that inspite of the name, this applies to the changes to any
     * hidden states: keyboard or navigation.
     * Set from the {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_KEYBOARD_HIDDEN = 32;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the change to layout direction. Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_LAYOUT_DIRECTION = 8192;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the locale.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_LOCALE = 4;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the IMSI MCC.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_MCC = 1;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the IMSI MNC.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_MNC = 2;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the navigation type.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_NAVIGATION = 64;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the screen orientation.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_ORIENTATION = 128;

    /**
     * <p>This is probably not the constant you want, the resources compiler supports a less
     * dangerous version of it, 'allKnown', that only suppresses all currently existing
     * configuration change restarts depending on your target SDK rather than whatever the latest
     * SDK supports, allowing the application to work with resources on future Platform versions.
     *
     * <p>Bit in {@link #configChanges} that indicates that the activity doesn't use Android
     * Resources at all and doesn't need to be restarted on any configuration changes. This bit
     * disables all restarts for configuration dimensions available in the current target SDK as
     * well as dimensions introduced in future SDKs. Use it only if the activity doesn't need
     * anything from its resources, and doesn't depend on any libraries that may provide resources
     * and need to respond to configuration changes. When set,
     * {@link Activity#onConfigurationChanged(Configuration)} will be called instead of a restart,
     * and it’s up to the implementation to ensure that no stale resource values remain loaded
     * anywhere in the code.
     *
     * <p>This overrides all other bits, and this is recommended to be used individually.
     *
     * <p>This is not a core resource configuration, but a higher-level value, so its constant
     * starts at the high bits.
     */
    public static final int CONFIG_RESOURCES_UNUSED = 134217728;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the screen layout.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_SCREEN_LAYOUT = 256;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the screen size. Set from the
     * {@link android.R.attr#configChanges} attribute.  This will be
     * set by default for applications that target an earlier version
     * than {@link android.os.Build.VERSION_CODES#HONEYCOMB_MR2}...
     * <b>however</b>, you will not see the bit set here becomes some
     * applications incorrectly compare {@link #configChanges} against
     * an absolute value rather than correctly masking out the bits
     * they are interested in.  Please don't do that, thanks.
     */
    public static final int CONFIG_SCREEN_SIZE = 1024;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the smallest screen size. Set from the
     * {@link android.R.attr#configChanges} attribute.  This will be
     * set by default for applications that target an earlier version
     * than {@link android.os.Build.VERSION_CODES#HONEYCOMB_MR2}...
     * <b>however</b>, you will not see the bit set here becomes some
     * applications incorrectly compare {@link #configChanges} against
     * an absolute value rather than correctly masking out the bits
     * they are interested in.  Please don't do that, thanks.
     */
    public static final int CONFIG_SMALLEST_SCREEN_SIZE = 2048;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle changes to the touchscreen type.  Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_TOUCHSCREEN = 8;

    /**
     * Bit in {@link #configChanges} that indicates that the activity
     * can itself handle the ui mode. Set from the
     * {@link android.R.attr#configChanges} attribute.
     */
    public static final int CONFIG_UI_MODE = 512;

    /**
     * Constant corresponding to <code>always</code> in
     * the {@link android.R.attr#documentLaunchMode} attribute.
     */
    public static final int DOCUMENT_LAUNCH_ALWAYS = 2;

    /**
     * Constant corresponding to <code>intoExisting</code> in
     * the {@link android.R.attr#documentLaunchMode} attribute.
     */
    public static final int DOCUMENT_LAUNCH_INTO_EXISTING = 1;

    /**
     * Constant corresponding to <code>never</code> in
     * the {@link android.R.attr#documentLaunchMode} attribute.
     */
    public static final int DOCUMENT_LAUNCH_NEVER = 3;

    /**
     * Constant corresponding to <code>none</code> in
     * the {@link android.R.attr#documentLaunchMode} attribute.
     */
    public static final int DOCUMENT_LAUNCH_NONE = 0;

    /**
     * Bit in {@link #flags} that indicates that the activity can be moved
     * between tasks based on its task affinity.  Set from the
     * {@link android.R.attr#allowTaskReparenting} attribute.
     */
    public static final int FLAG_ALLOW_TASK_REPARENTING = 64;

    /**
     * Bit in {@link #flags}: If set, indicates that the activity can be embedded by untrusted
     * hosts. In this case the interactions with and visibility of the embedded activity may be
     * limited. Set from the {@link android.R.attr#allowUntrustedActivityEmbedding} attribute.
     */
    public static final int FLAG_ALLOW_UNTRUSTED_ACTIVITY_EMBEDDING = 268435456;

    /**
     * Bit in {@link #flags} indicating that, when the activity is the root
     * of a task, that task's stack should never be cleared when it is
     * relaunched from home.  Set from the
     * {@link android.R.attr#alwaysRetainTaskState} attribute.
     */
    public static final int FLAG_ALWAYS_RETAIN_TASK_STATE = 8;

    /**
     * Bit in {@link #flags} indicating that tasks started with this activity are to be
     * removed from the recent list of tasks when the last activity in the task is finished.
     * Corresponds to {@link android.R.attr#autoRemoveFromRecents}
     */
    public static final int FLAG_AUTO_REMOVE_FROM_RECENTS = 8192;

    /**
     * Bit in {@link #flags} indicating that, when the activity is the root
     * of a task, that task's stack should be cleared each time the user
     * re-launches it from home.  As a result, the user will always
     * return to the original activity at the top of the task.
     * This flag only applies to activities that
     * are used to start the root of a new task.  Set from the
     * {@link android.R.attr#clearTaskOnLaunch} attribute.
     */
    public static final int FLAG_CLEAR_TASK_ON_LAUNCH = 4;

    /**
     * Bit in {@link #flags} indicating that this activity should be run with VR mode enabled.
     *
     * @see android.app.Activity#setVrModeEnabled(boolean, ComponentName)
     */
    public static final int FLAG_ENABLE_VR_MODE = 32768;

    /**
     * Bit in {@link #flags} that indicates that the activity should not
     * appear in the list of recently launched activities.  Set from the
     * {@link android.R.attr#excludeFromRecents} attribute.
     */
    public static final int FLAG_EXCLUDE_FROM_RECENTS = 32;

    /**
     * Bit in {@link #flags} indicating that, when a request to close system
     * windows happens, this activity is finished.
     * Set from the
     * {@link android.R.attr#finishOnCloseSystemDialogs} attribute.
     */
    public static final int FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS = 256;

    /**
     * Bit in {@link #flags} indicating that, when the activity's task is
     * relaunched from home, this activity should be finished.
     * Set from the
     * {@link android.R.attr#finishOnTaskLaunch} attribute.
     */
    public static final int FLAG_FINISH_ON_TASK_LAUNCH = 2;

    /**
     * Value for {@link #flags}: true when the application's rendering should
     * be hardware accelerated.
     */
    public static final int FLAG_HARDWARE_ACCELERATED = 512;

    /**
     * Bit in {@link #flags} corresponding to an immersive activity
     * that wishes not to be interrupted by notifications.
     * Applications that hide the system notification bar with
     * {@link android.view.WindowManager.LayoutParams#FLAG_FULLSCREEN}
     * may still be interrupted by high-priority notifications; for example, an
     * incoming phone call may use
     * {@link android.app.Notification#fullScreenIntent fullScreenIntent}
     * to present a full-screen in-call activity to the user, pausing the
     * current activity as a side-effect. An activity with
     * {@link #FLAG_IMMERSIVE} set, however, will not be interrupted; the
     * notification may be shown in some other way (such as a small floating
     * "toast" window).
     *
     * Note that this flag will always reflect the Activity's
     * <code>android:immersive</code> manifest definition, even if the Activity's
     * immersive state is changed at runtime via
     * {@link android.app.Activity#setImmersive(boolean)}.
     *
     * @see android.app.Notification#FLAG_HIGH_PRIORITY
     * @see android.app.Activity#setImmersive(boolean)
     */
    public static final int FLAG_IMMERSIVE = 2048;

    /**
     * Bit in {@link #flags} indicating whether this activity is able to
     * run in multiple processes.  If
     * true, the system may instantiate it in the some process as the
     * process starting it in order to conserve resources.  If false, the
     * default, it always runs in {@link #processName}.  Set from the
     * {@link android.R.attr#multiprocess} attribute.
     */
    public static final int FLAG_MULTIPROCESS = 1;

    /**
     * Bit in {@link #flags} indicating that, when the user navigates away
     * from an activity, it should be finished.
     * Set from the
     * {@link android.R.attr#noHistory} attribute.
     */
    public static final int FLAG_NO_HISTORY = 128;

    /**
     * Bit in {@link #flags} indicating whether the display should preferably be switched to a
     * minimal post processing mode.
     * See {@link android.R.attr#preferMinimalPostProcessing}
     */
    public static final int FLAG_PREFER_MINIMAL_POST_PROCESSING = 33554432;

    /**
     * Bit in {@link #flags}: If set, a task rooted at this activity will have its
     * baseIntent replaced by the activity immediately above this. Each activity may further
     * relinquish its identity to the activity above it using this flag. Set from the
     * {@link android.R.attr#relinquishTaskIdentity} attribute.
     */
    public static final int FLAG_RELINQUISH_TASK_IDENTITY = 4096;

    /**
     * Bit in {@link #flags} indicating that this activity can start is creation/resume
     * while the previous activity is still pausing.  Corresponds to
     * {@link android.R.attr#resumeWhilePausing}
     */
    public static final int FLAG_RESUME_WHILE_PAUSING = 16384;

    /**
     * Bit in {@link #flags}: If set, a single instance of the receiver will
     * run for all users on the device.  Set from the
     * {@link android.R.attr#singleUser} attribute.  Note that this flag is
     * only relevant for ActivityInfo structures that are describing receiver
     * components; it is not applied to activities.
     */
    public static final int FLAG_SINGLE_USER = 1073741824;

    /**
     * Bit in {@link #flags} indicating that the activity's state
     * is not required to be saved, so that if there is a failure the
     * activity will not be removed from the activity stack.  Set from the
     * {@link android.R.attr#stateNotNeeded} attribute.
     */
    public static final int FLAG_STATE_NOT_NEEDED = 16;

    /**
     * Constant corresponding to <code>standard</code> in
     * the {@link android.R.attr#launchMode} attribute.
     */
    public static final int LAUNCH_MULTIPLE = 0;

    /**
     * Constant corresponding to <code>singleInstance</code> in
     * the {@link android.R.attr#launchMode} attribute.
     */
    public static final int LAUNCH_SINGLE_INSTANCE = 3;

    /**
     * Constant corresponding to <code>singleInstancePerTask</code> in
     * the {@link android.R.attr#launchMode} attribute.
     */
    public static final int LAUNCH_SINGLE_INSTANCE_PER_TASK = 4;

    /**
     * Constant corresponding to <code>singleTask</code> in
     * the {@link android.R.attr#launchMode} attribute.
     */
    public static final int LAUNCH_SINGLE_TASK = 2;

    /**
     * Constant corresponding to <code>singleTop</code> in
     * the {@link android.R.attr#launchMode} attribute.
     */
    public static final int LAUNCH_SINGLE_TOP = 1;

    /**
     * Constant corresponding to <code>persistAcrossReboots</code> in
     * the {@link android.R.attr#persistableMode} attribute.
     */
    public static final int PERSIST_ACROSS_REBOOTS = 2;

    /**
     * Constant corresponding to <code>doNotPersist</code> in
     * the {@link android.R.attr#persistableMode} attribute.
     */
    public static final int PERSIST_NEVER = 1;

    /**
     * Constant corresponding to <code>persistRootOnly</code> in
     * the {@link android.R.attr#persistableMode} attribute.
     */
    public static final int PERSIST_ROOT_ONLY = 0;

    /**
     * Constant corresponding to <code>behind</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_BEHIND = 3;

    /**
     * Constant corresponding to <code>fullSensor</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_FULL_SENSOR = 10;

    /**
     * Constant corresponding to <code>fullUser</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_FULL_USER = 13;

    /**
     * Constant corresponding to <code>landscape</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;

    /**
     * Constant corresponding to <code>locked</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_LOCKED = 14;

    /**
     * Constant corresponding to <code>nosensor</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_NOSENSOR = 5;

    /**
     * Constant corresponding to <code>portrait</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_PORTRAIT = 1;

    /**
     * Constant corresponding to <code>reverseLandscape</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;

    /**
     * Constant corresponding to <code>reversePortrait</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;

    /**
     * Constant corresponding to <code>sensor</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_SENSOR = 4;

    /**
     * Constant corresponding to <code>sensorLandscape</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;

    /**
     * Constant corresponding to <code>sensorPortrait</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7;

    /**
     * Constant corresponding to <code>unspecified</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1;

    /**
     * Constant corresponding to <code>user</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_USER = 2;

    /**
     * Constant corresponding to <code>userLandscape</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;

    /**
     * Constant corresponding to <code>userPortrait</code> in
     * the {@link android.R.attr#screenOrientation} attribute.
     */
    public static final int SCREEN_ORIENTATION_USER_PORTRAIT = 12;

    /**
     * Flag for use with {@link #uiOptions}.
     * Indicates that the action bar should put all action items in a separate bar when
     * the screen is narrow.
     * <p>This value corresponds to "splitActionBarWhenNarrow" for the {@link #uiOptions} XML
     * attribute.
     */
    public static final int UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW = 1;

    private ActivityInfo() {
    }
}
