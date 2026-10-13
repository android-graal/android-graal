package android.content.pm;

import android.os.Parcelable;

/**
 * Information you can retrieve about a particular application.  This
 * corresponds to information collected from the AndroidManifest.xml's
 * &lt;application&gt; tag.
 */
public final class ApplicationInfo implements Parcelable {
    /**
     * Category for apps which are primarily accessibility apps, such as screen-readers.
     *
     * @see #category
     */
    public static final int CATEGORY_ACCESSIBILITY = 8;

    /**
     * Category for apps which primarily work with audio or music, such as music
     * players.
     *
     * @see #category
     */
    public static final int CATEGORY_AUDIO = 1;

    /**
     * Category for apps which are primarily games.
     *
     * @see #category
     */
    public static final int CATEGORY_GAME = 0;

    /**
     * Category for apps which primarily work with images or photos, such as
     * camera or gallery apps.
     *
     * @see #category
     */
    public static final int CATEGORY_IMAGE = 3;

    /**
     * Category for apps which are primarily maps apps, such as navigation apps.
     *
     * @see #category
     */
    public static final int CATEGORY_MAPS = 6;

    /**
     * Category for apps which are primarily news apps, such as newspapers,
     * magazines, or sports apps.
     *
     * @see #category
     */
    public static final int CATEGORY_NEWS = 5;

    /**
     * Category for apps which are primarily productivity apps, such as cloud
     * storage or workplace apps.
     *
     * @see #category
     */
    public static final int CATEGORY_PRODUCTIVITY = 7;

    /**
     * Category for apps which are primarily social apps, such as messaging,
     * communication, email, or social network apps.
     *
     * @see #category
     */
    public static final int CATEGORY_SOCIAL = 4;

    /**
     * Value when category is undefined.
     *
     * @see #category
     */
    public static final int CATEGORY_UNDEFINED = -1;

    /**
     * Category for apps which primarily work with video or movies, such as
     * streaming video apps.
     *
     * @see #category
     */
    public static final int CATEGORY_VIDEO = 2;

    /**
     * Value for {@link #flags}: set to <code>false</code> if the application does not wish
     * to permit any OS-driven backups of its data; <code>true</code> otherwise.
     *
     * <p>Comes from the
     * {@link android.R.styleable#AndroidManifestApplication_allowBackup android:allowBackup}
     * attribute of the &lt;application&gt; tag.
     */
    public static final int FLAG_ALLOW_BACKUP = 32768;

    /**
     * Value for {@link #flags}: default value for the corresponding ActivityInfo flag.
     * Comes from {@link android.R.styleable#AndroidManifestApplication_allowClearUserData
     * android:allowClearUserData} of the &lt;application&gt; tag.
     */
    public static final int FLAG_ALLOW_CLEAR_USER_DATA = 64;

    /**
     * Value for {@link #flags}: default value for the corresponding ActivityInfo flag.
     * Comes from {@link android.R.styleable#AndroidManifestApplication_allowTaskReparenting
     * android:allowTaskReparenting} of the &lt;application&gt; tag.
     */
    public static final int FLAG_ALLOW_TASK_REPARENTING = 32;

    /**
     * Value for {@link #flags}: set to true if this application would like to
     * allow debugging of its
     * code, even when installed on a non-development system.  Comes
     * from {@link android.R.styleable#AndroidManifestApplication_debuggable
     * android:debuggable} of the &lt;application&gt; tag.
     */
    public static final int FLAG_DEBUGGABLE = 2;

    /**
     * Value for {@link #flags}: Set to true if the application is
     * currently installed on external/removable/unprotected storage.  Such
     * applications may not be available if their storage is not currently
     * mounted.  When the storage it is on is not available, it will look like
     * the application has been uninstalled (its .apk is no longer available)
     * but its persistent data is not removed.
     */
    public static final int FLAG_EXTERNAL_STORAGE = 262144;

    /**
     * When set installer extracts native libs from .apk files.
     */
    public static final int FLAG_EXTRACT_NATIVE_LIBS = 268435456;

    /**
     * Value for {@link #flags}: set to true if this application holds the
     * {@link android.Manifest.permission#FACTORY_TEST} permission and the
     * device is running in factory test mode.
     */
    public static final int FLAG_FACTORY_TEST = 16;

    /**
     * Value for {@link #flags}: {@code true} if the application asks that only
     * full-data streaming backups of its data be performed even though it defines
     * a {@link android.app.backup.BackupAgent BackupAgent}, which normally
     * indicates that the app will manage its backed-up data via incremental
     * key/value updates.
     */
    public static final int FLAG_FULL_BACKUP_ONLY = 67108864;

    /**
     * Value for {@link #flags}: {@code true} when the application's rendering
     * should be hardware accelerated.
     */
    public static final int FLAG_HARDWARE_ACCELERATED = 536870912;

    /**
     * Value for {@link #flags}: set to true if this application has code
     * associated with it.  Comes
     * from {@link android.R.styleable#AndroidManifestApplication_hasCode
     * android:hasCode} of the &lt;application&gt; tag.
     */
    public static final int FLAG_HAS_CODE = 4;

    /**
     * Value for {@link #flags}: true if the application is currently
     * installed for the calling user.
     */
    public static final int FLAG_INSTALLED = 8388608;

    /**
     * Value for {@link #flags}: true if the application only has its
     * data installed; the application package itself does not currently
     * exist on the device.
     */
    public static final int FLAG_IS_DATA_ONLY = 16777216;

    /**
     * Value for {@link #flags}: true if the application was declared to be a
     * game, or false if it is a non-game application.
     *
     * @deprecated use {@link #CATEGORY_GAME} instead.
     */
    @Deprecated
    public static final int FLAG_IS_GAME = 33554432;

    /**
     * Value for {@link #flags}: set to <code>false</code> if the application must be kept
     * in memory following a full-system restore operation; <code>true</code> otherwise.
     * Ordinarily, during a full system restore operation each application is shut down
     * following execution of its agent's onRestore() method.  Setting this attribute to
     * <code>false</code> prevents this.  Most applications will not need to set this attribute.
     *
     * <p>If
     * {@link android.R.styleable#AndroidManifestApplication_allowBackup android:allowBackup}
     * is set to <code>false</code> or no
     * {@link android.R.styleable#AndroidManifestApplication_backupAgent android:backupAgent}
     * is specified, this flag will be ignored.
     *
     * <p>Comes from the
     * {@link android.R.styleable#AndroidManifestApplication_killAfterRestore android:killAfterRestore}
     * attribute of the &lt;application&gt; tag.
     */
    public static final int FLAG_KILL_AFTER_RESTORE = 65536;

    /**
     * Value for {@link #flags}: true when the application has requested a
     * large heap for its processes.  Corresponds to
     * {@link android.R.styleable#AndroidManifestApplication_largeHeap
     * android:largeHeap}.
     */
    public static final int FLAG_LARGE_HEAP = 1048576;

    /**
     * Value for {@link #flags}: true if code from this application will need to be
     * loaded into other applications' processes. On devices that support multiple
     * instruction sets, this implies the code might be loaded into a process that's
     * using any of the devices supported instruction sets.
     *
     * <p> The system might treat such applications specially, for eg., by
     * extracting the application's native libraries for all supported instruction
     * sets or by compiling the application's dex code for all supported instruction
     * sets.
     */
    public static final int FLAG_MULTIARCH = -2147483648;

    /**
     * Value for {@link #flags}: set to true if this application is persistent.
     * Comes from {@link android.R.styleable#AndroidManifestApplication_persistent
     * android:persistent} of the &lt;application&gt; tag.
     */
    public static final int FLAG_PERSISTENT = 8;

    /**
     * Value for {@link #flags}: true when the application knows how to adjust
     * its UI for different screen sizes.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_resizeable
     * android:resizeable}.
     */
    public static final int FLAG_RESIZEABLE_FOR_SCREENS = 4096;

    /**
     * Value for {@link #flags}: Set to <code>true</code> if the application's backup
     * agent claims to be able to handle restore data even "from the future,"
     * i.e. from versions of the application with a versionCode greater than
     * the one currently installed on the device.  <i>Use with caution!</i>  By default
     * this attribute is <code>false</code> and the Backup Manager will ensure that data
     * from "future" versions of the application are never supplied during a restore operation.
     *
     * <p>If
     * {@link android.R.styleable#AndroidManifestApplication_allowBackup android:allowBackup}
     * is set to <code>false</code> or no
     * {@link android.R.styleable#AndroidManifestApplication_backupAgent android:backupAgent}
     * is specified, this flag will be ignored.
     *
     * <p>Comes from the
     * {@link android.R.styleable#AndroidManifestApplication_restoreAnyVersion android:restoreAnyVersion}
     * attribute of the &lt;application&gt; tag.
     */
    public static final int FLAG_RESTORE_ANY_VERSION = 131072;

    /**
     * Value for {@link #flags}: true if this application's package is in
     * the stopped state.
     *
     * <p>Stopped is the initial state after an app is installed, before it is launched
     * or otherwise directly interacted with by the user. The system tries not to
     * start it unless initiated by a user interaction (typically launching its icon
     * from the launcher, could also include user actions like adding it as an app widget,
     * selecting it as a live wallpaper, selecting it as a keyboard, etc). Stopped
     * applications will not receive implicit broadcasts unless the sender specifies
     * {@link android.content.Intent#FLAG_INCLUDE_STOPPED_PACKAGES}.
     *
     * <p>Applications should avoid launching activities, binding to or starting services, or
     * otherwise causing a stopped application to run unless initiated by the user.
     *
     * <p>An app can also return to the stopped state by a "force stop".
     */
    public static final int FLAG_STOPPED = 2097152;

    /**
     * Value for {@link #flags}: true when the application's window can be
     * increased in size for larger screens.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_largeScreens
     * android:largeScreens}.
     */
    public static final int FLAG_SUPPORTS_LARGE_SCREENS = 2048;

    /**
     * Value for {@link #flags}: true when the application's window can be
     * displayed on normal screens.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_normalScreens
     * android:normalScreens}.
     */
    public static final int FLAG_SUPPORTS_NORMAL_SCREENS = 1024;

    /**
     * Value for {@link #flags}: true  when the application is willing to support
     * RTL (right to left). All activities will inherit this value.
     *
     * Set from the {@link android.R.attr#supportsRtl} attribute in the
     * activity's manifest.
     *
     * Default value is false (no support for RTL).
     */
    public static final int FLAG_SUPPORTS_RTL = 4194304;

    /**
     * Value for {@link #flags}: true when the application knows how to
     * accommodate different screen densities.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_anyDensity
     * android:anyDensity}.
     *
     * @deprecated Set by default when targeting API 4 or higher and apps
     *             should not set this to false.
     */
    @Deprecated
    public static final int FLAG_SUPPORTS_SCREEN_DENSITIES = 8192;

    /**
     * Value for {@link #flags}: true when the application's window can be
     * reduced in size for smaller screens.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_smallScreens
     * android:smallScreens}.
     */
    public static final int FLAG_SUPPORTS_SMALL_SCREENS = 512;

    /**
     * Value for {@link #flags}: true when the application's window can be
     * increased in size for extra large screens.  Corresponds to
     * {@link android.R.styleable#AndroidManifestSupportsScreens_xlargeScreens
     * android:xlargeScreens}.
     */
    public static final int FLAG_SUPPORTS_XLARGE_SCREENS = 524288;

    /**
     * Value for {@link #flags}: true if this application's package is in
     * the suspended state.
     */
    public static final int FLAG_SUSPENDED = 1073741824;

    /**
     * Value for {@link #flags}: if set, this application is installed in the device's system image.
     * This should not be used to make security decisions. Instead, rely on
     * {@linkplain android.content.pm.PackageManager#checkSignatures(java.lang.String,java.lang.String)
     * signature checks} or
     * <a href="https://developer.android.com/training/articles/security-tips#Permissions">permissions</a>.
     *
     * <p><b>Warning:</b> Note that this flag does not behave the same as
     * {@link android.R.attr#protectionLevel android:protectionLevel} {@code system} or
     * {@code signatureOrSystem}.
     */
    public static final int FLAG_SYSTEM = 1;

    /**
     * Value for {@link #flags}: this is set if the application has specified
     * {@link android.R.styleable#AndroidManifestApplication_testOnly
     * android:testOnly} to be true.
     */
    public static final int FLAG_TEST_ONLY = 256;

    /**
     * Value for {@link #flags}: this is set if this application has been
     * installed as an update to a built-in system application.
     */
    public static final int FLAG_UPDATED_SYSTEM_APP = 128;

    /**
     * Value for {@link #flags}: {@code true} if the application may use cleartext network traffic
     * (e.g., HTTP rather than HTTPS; WebSockets rather than WebSockets Secure; XMPP, IMAP, SMTP
     * without STARTTLS or TLS). If {@code false}, the app declares that it does not intend to use
     * cleartext network traffic, in which case platform components (e.g., HTTP stacks,
     * {@code DownloadManager}, {@code MediaPlayer}) will refuse app's requests to use cleartext
     * traffic. Third-party libraries are encouraged to honor this flag as well.
     *
     * <p>NOTE: {@code WebView} honors this flag for applications targeting API level 26 and up.
     *
     * <p>This flag is ignored on Android N and above if an Android Network Security Config is
     * present.
     *
     * <p>This flag comes from
     * {@link android.R.styleable#AndroidManifestApplication_usesCleartextTraffic
     * android:usesCleartextTraffic} of the &lt;application&gt; tag.
     */
    public static final int FLAG_USES_CLEARTEXT_TRAFFIC = 134217728;

    /**
     * Value for {@link #flags}: set to true if this application would like to
     * request the VM to operate under the safe mode. Comes from
     * {@link android.R.styleable#AndroidManifestApplication_vmSafeMode
     * android:vmSafeMode} of the &lt;application&gt; tag.
     */
    public static final int FLAG_VM_SAFE_MODE = 16384;

    /**
     * Always enable GWP-ASan in this application or process.
     */
    public static final int GWP_ASAN_ALWAYS = 1;

    /**
     * Default (unspecified) setting of GWP-ASan.
     */
    public static final int GWP_ASAN_DEFAULT = -1;

    /**
     * Never enable GWP-ASan in this application or process.
     */
    public static final int GWP_ASAN_NEVER = 0;

    /**
     * Enable Memtag in Async mode in this application or process.
     */
    public static final int MEMTAG_ASYNC = 1;

    /**
     * Default (unspecified) setting of Memtag.
     */
    public static final int MEMTAG_DEFAULT = -1;

    /**
     * Do not enable Memtag in this application or process.
     */
    public static final int MEMTAG_OFF = 0;

    /**
     * Enable Memtag in Sync mode in this application or process.
     */
    public static final int MEMTAG_SYNC = 2;

    /**
     * Use default value for
     * {@link android.R.styleable#AndroidManifestApplication_requestRawExternalStorageAccess}.
     */
    public static final int RAW_EXTERNAL_STORAGE_ACCESS_DEFAULT = 0;

    /**
     * Raw external storage was not requested by this app.
     */
    public static final int RAW_EXTERNAL_STORAGE_ACCESS_NOT_REQUESTED = 2;

    /**
     * Raw external storage was requested by this app.
     */
    public static final int RAW_EXTERNAL_STORAGE_ACCESS_REQUESTED = 1;

    /**
     * Default (unspecified) setting of nativeHeapZeroInitialized.
     */
    public static final int ZEROINIT_DEFAULT = -1;

    /**
     * Disable zero-initialization of the native heap in this application or process.
     */
    public static final int ZEROINIT_DISABLED = 0;

    /**
     * Enable zero-initialization of the native heap in this application or process.
     */
    public static final int ZEROINIT_ENABLED = 1;

    private ApplicationInfo() {
    }
}
