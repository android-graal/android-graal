package android.view;

import android.os.Parcelable;

/**
 * The interface that apps use to talk to the window manager.
 * <p>
 * Each window manager instance is bound to a {@link Display}. To obtain the
 * <code>WindowManager</code> associated with a display,
 * call {@link Context#createWindowContext(Display, int, Bundle)} to get the display's UI context,
 * then call {@link Context#getSystemService(String)} or {@link Context#getSystemService(Class)} on
 * the UI context.
 * <p>
 * The simplest way to show a window on a particular display is to create a {@link Presentation},
 * which automatically obtains a <code>WindowManager</code> and context for the display.
 */
public interface WindowManager {
    /**
     * Value applicable for the {@link #PROPERTY_COMPAT_ALLOW_SMALL_COVER_SCREEN} property to
     * provide a signal to the system that an application or its specific activities explicitly
     * opt into being displayed on small cover screens on flippable style foldable devices that
     * measure at least 1.5 inches up to 2.2 inches for the shorter dimension and at least 2.4
     * inches up to 3.4 inches for the longer dimension
     */
    int COMPAT_SMALL_COVER_SCREEN_OPT_IN = 1;

    /**
     * Application-level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * tag that specifies whether OEMs are permitted to provide activity embedding split-rule
     * configurations on behalf of the app.
     *
     * <p>If {@code true}, the system is permitted to override the app's windowing behavior and
     * implement activity embedding split rules, such as displaying activities side by side. A
     * system override informs the app that the activity embedding APIs are disabled so the app
     * doesn't provide its own activity embedding rules, which would conflict with the system's
     * rules.
     *
     * <p>If {@code false}, the system is not permitted to override the windowing behavior of the
     * app. Set the property to {@code false} if the app provides its own activity embedding split
     * rules, or if you want to prevent the system override for any other reason.
     *
     * <p>The default value is {@code false}.
     *
     * <p class="note"><b>Note:</b> Refusal to permit the system override is not enforceable. OEMs
     * can override the app's activity embedding implementation whether or not this property is
     * specified and set to {@code false}. The property is, in effect, a hint to OEMs.
     *
     * <p>OEMs can implement activity embedding on any API level. The best practice for apps is to
     * always explicitly set this property in the app manifest file regardless of targeted API level
     * rather than rely on the default value.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_ACTIVITY_EMBEDDING_ALLOW_SYSTEM_OVERRIDE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_ACTIVITY_EMBEDDING_ALLOW_SYSTEM_OVERRIDE = "android.window.PROPERTY_ACTIVITY_EMBEDDING_ALLOW_SYSTEM_OVERRIDE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * that an app can specify to inform the system that the app is activity embedding split feature
     * enabled.
     *
     * <p>With this property, the system could provide custom behaviors for the apps that are
     * activity embedding split feature enabled. For example, the fixed-portrait orientation
     * requests of the activities could be ignored by the system in order to provide seamless
     * activity embedding split experiences while holding large screen devices in landscape
     * orientation.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED = "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be excluded from the camera compatibility
     * force rotation treatment.
     *
     * <p>The camera compatibility treatment aligns portrait app windows with the natural
     * orientation of the device and landscape app windows opposite the device natural orientation.
     * Mismatch between the orientations can lead to camera issues like a sideways or stretched
     * viewfinder since this is one of the strongest assumptions that apps make when they implement
     * camera previews. Since app and device natural orientations aren't guaranteed to match, the
     * rotation can cause letterboxing. The forced rotation is triggered as soon as an app opens the
     * camera and is removed once camera is closed.
     *
     * <p>Camera compatibility can be enabled by device manufacturers on displays that have the
     * ignore requested orientation display setting enabled, which enables compatibility mode for
     * fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>With this property set to {@code true} or unset, the system may apply the force rotation
     * treatment to fixed-orientation activities. Device manufacturers can exclude packages from the
     * treatment using their discretion to improve display compatibility.
     *
     * <p>With this property set to {@code false}, the system will not apply the force rotation
     * treatment.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_CAMERA_COMPAT_ALLOW_FORCE_ROTATION"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_CAMERA_COMPAT_ALLOW_FORCE_ROTATION = "android.window.PROPERTY_CAMERA_COMPAT_ALLOW_FORCE_ROTATION";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be excluded from the activity "refresh"
     * after the camera compatibility force rotation treatment.
     *
     * <p>The camera compatibility treatment aligns portrait app windows with the natural
     * orientation of the device and landscape app windows opposite the device natural orientation.
     * Mismatch between the orientations can lead to camera issues like a sideways or stretched
     * viewfinder since this is one of the strongest assumptions that apps make when they implement
     * camera previews. Since app and device natural orientations aren't guaranteed to match, the
     * rotation can cause letterboxing. The forced rotation is triggered as soon as an app opens the
     * camera and is removed once camera is closed.
     *
     * <p>Force rotation is followed by the "Refresh" of the activity by going through "resumed ->
     * ... -> stopped -> ... -> resumed" cycle (by default) or "resumed -> paused -> resumed" cycle
     * (if overridden, see {@link #PROPERTY_CAMERA_COMPAT_ENABLE_REFRESH_VIA_PAUSE} for context).
     * This allows to clear cached values in apps (e.g. display or camera rotation) that influence
     * camera preview and can lead to sideways or stretching issues persisting even after force
     * rotation.
     *
     * <p>The camera compatibility can be enabled by device manufacturers on displays that have the
     * ignore requested orientation display setting enabled, which enables compatibility mode for
     * fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>With this property set to {@code true} or unset, the system may "refresh" activity after
     * the force rotation treatment. Device manufacturers can exclude packages from the "refresh"
     * using their discretion to improve display compatibility.
     *
     * <p>With this property set to {@code false}, the system will not "refresh" activity after the
     * force rotation treatment.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_CAMERA_COMPAT_ALLOW_REFRESH"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_CAMERA_COMPAT_ALLOW_REFRESH = "android.window.PROPERTY_CAMERA_COMPAT_ALLOW_REFRESH";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the activity should be or shouldn't be "refreshed" after
     * the camera compatibility force rotation treatment using "paused -> resumed" cycle rather than
     * "stopped -> resumed".
     *
     * <p>The camera compatibility treatment aligns orientations of portrait app window and natural
     * orientation of the device. Mismatch between the orientations can lead to camera issues like a
     * sideways or stretched viewfinder since this is one of the strongest assumptions that apps
     * make when they implement camera previews. Since app and natural display orientations aren't
     * guaranteed to match, the rotation can cause letterboxing. The forced rotation is triggered as
     * soon as app opens the camera and is removed once camera is closed.
     *
     * <p>Force rotation is followed by the "Refresh" of the activity by going through "resumed ->
     * ... -> stopped -> ... -> resumed" cycle (by default) or "resumed -> paused -> resumed" cycle
     * (if overridden by device manufacturers or using this property). This allows to clear cached
     * values in apps (e.g., display or camera rotation) that influence camera preview and can lead
     * to sideways or stretching issues persisting even after force rotation.
     *
     * <p>The camera compatibility can be enabled by device manufacturers on displays that have the
     * ignore requested orientation display setting enabled, which enables compatibility mode for
     * fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>Device manufacturers can override packages to "refresh" via "resumed -> paused -> resumed"
     * cycle using their discretion to improve display compatibility.
     *
     * <p>With this property set to {@code true}, the system will "refresh" activity after the
     * force rotation treatment using "resumed -> paused -> resumed" cycle.
     *
     * <p>With this property set to {@code false}, the system will not "refresh" activity after the
     * force rotation treatment using "resumed -> paused -> resumed" cycle even if the device
     * manufacturer adds the corresponding override.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_CAMERA_COMPAT_ENABLE_REFRESH_VIA_PAUSE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_CAMERA_COMPAT_ENABLE_REFRESH_VIA_PAUSE = "android.window.PROPERTY_CAMERA_COMPAT_ENABLE_REFRESH_VIA_PAUSE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be opted-out from the compatibility
     * override that fixes display orientation to landscape natural orientation when an activity is
     * fullscreen.
     *
     * <p>When this compat override is enabled and while display is fixed to the landscape natural
     * orientation, the orientation requested by the activity will be still respected by bounds
     * resolution logic. For instance, if an activity requests portrait orientation, then activity
     * appears in letterbox mode for fixed-orientation apps with the display rotated to the lanscape
     * natural orientation.
     *
     * <p>The treatment is disabled by default but device manufacturers can enable the treatment
     * using their discretion to improve display compatibility on displays that have the ignore
     * orientation request display setting enabled by OEMs on the device, which enables
     * compatibility mode for fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>With this property set to {@code true} or unset, the system wiil use landscape display
     * orientation when the following conditions are met:
     * <ul>
     *     <li>Natural orientation of the display is landscape
     *     <li>ignore requested orientation display setting is enabled
     *     <li>Activity is fullscreen.
     *     <li>Device manufacturer enabled the treatment.
     * </ul>
     *
     * <p>With this property set to {@code false}, device manufacturer per-app override for
     * display orientation won't be applied.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_DISPLAY_ORIENTATION_OVERRIDE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_DISPLAY_ORIENTATION_OVERRIDE = "android.window.PROPERTY_COMPAT_ALLOW_DISPLAY_ORIENTATION_OVERRIDE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app can be opted-out from the compatibility
     * treatment that avoids {@link android.app.Activity#setRequestedOrientation
     * Activity#setRequestedOrientation()} loops. Loops can be triggered by the OEM-configured
     * ignore requested orientation display setting (on Android 12 (API level 31) and higher) or by
     * the landscape natural orientation of the device.
     *
     * <p>The system could ignore {@link android.app.Activity#setRequestedOrientation
     * Activity#setRequestedOrientation()} call from an app if both of the following conditions are
     * true:
     * <ul>
     *     <li>Activity has requested orientation more than two times within one-second timer
     *     <li>Activity is not letterboxed for fixed-orientation apps
     * </ul>
     *
     * <p>Setting this property to {@code false} informs the system that the app must be
     * opted-out from the compatibility treatment even if the device manufacturer has opted the app
     * into the treatment.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name=
     *       "android.window.PROPERTY_COMPAT_ALLOW_IGNORING_ORIENTATION_REQUEST_WHEN_LOOP_DETECTED"
     *     android:value="false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_IGNORING_ORIENTATION_REQUEST_WHEN_LOOP_DETECTED = "android.window.PROPERTY_COMPAT_ALLOW_IGNORING_ORIENTATION_REQUEST_WHEN_LOOP_DETECTED";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be opted-out from the compatibility
     * override that changes the min aspect ratio.
     *
     * <p>When this compat override is enabled the min aspect ratio given in the app's manifest can
     * be overridden by the device manufacturer using their discretion to improve display
     * compatibility unless the app's manifest value is higher. This treatment will also apply if
     * no min aspect ratio value is provided in the manifest. These treatments can apply either in
     * specific cases (e.g. device is in portrait) or each time the app is displayed on screen.
     *
     * <p>Setting this property to {@code false} informs the system that the app must be
     * opted-out from the compatibility treatment even if the device manufacturer has opted the app
     * into the treatment.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_MIN_ASPECT_RATIO_OVERRIDE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_MIN_ASPECT_RATIO_OVERRIDE = "android.window.PROPERTY_COMPAT_ALLOW_MIN_ASPECT_RATIO_OVERRIDE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be excluded from the compatibility
     * override for orientation set by the device manufacturer. When the orientation override is
     * applied it can:
     * <ul>
     *   <li>Replace the specific orientation requested by the app with another selected by the
     *             device manufacturer; for example, replace undefined requested by the app with portrait.
     *   <li>Always use an orientation selected by the device manufacturer.
     *   <li>Do one of the above but only when camera connection is open.
     * </ul>
     *
     * <p>This property is different from {@link #PROPERTY_COMPAT_IGNORE_REQUESTED_ORIENTATION}
     * (which is used to avoid orientation loops caused by the incorrect use of {@link
     * android.app.Activity#setRequestedOrientation Activity#setRequestedOrientation()}) because
     * this property overrides the app to an orientation selected by the device manufacturer rather
     * than ignoring one of orientation requests coming from the app while respecting the previous
     * one.
     *
     * <p>With this property set to {@code true} or unset, device manufacturers can override
     * orientation for the app using their discretion to improve display compatibility.
     *
     * <p>With this property set to {@code false}, device manufacturer per-app override for
     * orientation won't be applied.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_ORIENTATION_OVERRIDE"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_ORIENTATION_OVERRIDE = "android.window.PROPERTY_COMPAT_ALLOW_ORIENTATION_OVERRIDE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app should be opted-out from the compatibility
     * overrides that change the resizability of the app.
     *
     * <p>When these compat overrides are enabled they force the packages they are applied to to be
     * resizable/unresizable. If the app is forced to be resizable this won't change whether the app
     * can be put into multi-windowing mode, but allow the app to resize without going into size
     * compatibility mode when the window container resizes, such as display size change or screen
     * rotation.
     *
     * <p>Setting this property to {@code false} informs the system that the app must be
     * opted-out from the compatibility treatment even if the device manufacturer has opted the app
     * into the treatment.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_RESIZEABLE_ACTIVITY_OVERRIDES"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_RESIZEABLE_ACTIVITY_OVERRIDES = "android.window.PROPERTY_COMPAT_ALLOW_RESIZEABLE_ACTIVITY_OVERRIDES";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that it needs to be opted-out from the compatibility
     * treatment that sandboxes the {@link android.view.View View} API.
     *
     * <p>The treatment can be enabled by device manufacturers for applications which misuse
     * {@link android.view.View View} APIs by expecting that
     * {@link android.view.View#getLocationOnScreen View#getLocationOnScreen()} and
     * {@link android.view.View#getWindowVisibleDisplayFrame View#getWindowVisibleDisplayFrame()}
     * return coordinates as if an activity is positioned in the top-left corner of the screen, with
     * left coordinate equal to 0. This may not be the case for applications in multi-window and
     * letterbox modes.
     *
     * <p>Setting this property to {@code false} informs the system that the application must be
     * opted-out from the "Sandbox View API to Activity bounds" treatment even if the device
     * manufacturer has opted the app into the treatment.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_SANDBOXING_VIEW_BOUNDS_APIS"
     *     android:value="false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_SANDBOXING_VIEW_BOUNDS_APIS = "android.window.PROPERTY_COMPAT_ALLOW_SANDBOXING_VIEW_BOUNDS_APIS";

    /**
     * Application or Activity level
     * {@link android.content.pm.PackageManager.Property PackageManager.Property} to provide any
     * preferences for showing all or specific Activities on small cover displays of foldable
     * style devices.
     *
     * <p>The only supported value for the property is {@link #COMPAT_SMALL_COVER_SCREEN_OPT_IN}.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_SMALL_COVER_SCREEN"
     *     android:value=1 <!-- COMPAT_COVER_SCREEN_OPT_IN -->/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_SMALL_COVER_SCREEN = "android.window.PROPERTY_COMPAT_ALLOW_SMALL_COVER_SCREEN";

    /**
     * Application level
     * {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * tag that (when set to false) informs the system the app has opted out of the
     * full-screen option of the user aspect ratio compatibility override settings. (For
     * background information about the user aspect ratio compatibility override, see
     * {@link #PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_OVERRIDE}.)
     *
     * <p>When users apply the full-screen compatibility override, the orientation
     * of the activity is forced to {@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_USER}.
     *
     * <p>The user override is intended to improve the app experience on devices that have the
     * ignore orientation request display setting enabled by OEMs, which enables compatibility mode
     * for fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>To opt out of the full-screen option of the user aspect ratio compatibility
     * override, add this property to your app manifest and set the value to {@code false}.
     * Your app will have full-screen option removed from the list of user aspect ratio
     * override options in device settings, and users will not be able to apply
     * full-screen override to your app.
     *
     * <p><b>Note:</b> If {@link #PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_OVERRIDE} is
     * {@code false}, this property has no effect.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_FULLSCREEN_OVERRIDE"
     *     android:value="false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_FULLSCREEN_OVERRIDE = "android.window.PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_FULLSCREEN_OVERRIDE";

    /**
     * Application level
     * {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * tag that (when set to false) informs the system the app has opted out of the
     * user-facing aspect ratio compatibility override.
     *
     * <p>The compatibility override enables device users to set the app's aspect
     * ratio or force the app to fill the display regardless of the aspect
     * ratio or orientation specified in the app manifest.
     *
     * <p>The aspect ratio compatibility override is exposed to users in device
     * settings. A menu in device settings lists all apps that have not opted out of
     * the compatibility override. Users select apps from the menu and set the
     * app aspect ratio on a per-app basis. Typically, the menu is available
     * only on large screen devices.
     *
     * <p>When users apply the aspect ratio override, the minimum aspect ratio
     * specified in the app manifest is overridden. If users choose a
     * full-screen aspect ratio, the orientation of the activity is forced to
     * {@link android.content.pm.ActivityInfo#SCREEN_ORIENTATION_USER};
     * see {@link #PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_FULLSCREEN_OVERRIDE} to
     * disable the full-screen option only.
     *
     * <p>The user override is intended to improve the app experience on devices that have the
     * ignore orientation request display setting enabled by OEMs, which enables compatibility mode
     * for fixed-orientation apps on Android 12 (API level 31) or higher. See
     * <a href="{@docRoot}guide/practices/device-compatibility-mode">Device compatibility mode</a>
     * for more details.
     *
     * <p>To opt out of the user aspect ratio compatibility override, add this property
     * to your app manifest and set the value to {@code false}. Your app will be excluded
     * from the list of apps in device settings, and users will not be able to override
     * the app's aspect ratio.
     *
     * <p>Not setting this property at all, or setting this property to {@code true} has no effect.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_OVERRIDE"
     *     android:value="false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_OVERRIDE = "android.window.PROPERTY_COMPAT_ALLOW_USER_ASPECT_RATIO_OVERRIDE";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the application can be opted-in or opted-out from the
     * compatibility treatment that enables sending a fake focus event for unfocused resumed
     * split-screen activities. This is needed because some game engines wait to get focus before
     * drawing the content of the app which isn't guaranteed by default in multi-window mode.
     *
     * <p>Device manufacturers can enable this treatment using their discretion on a per-device
     * basis to improve display compatibility. The treatment also needs to be specifically enabled
     * on a per-app basis afterwards. This can either be done by device manufacturers or developers.
     *
     * <p>With this property set to {@code true}, the system will apply the treatment only if the
     * device manufacturer had previously enabled it on the device. A fake focus event will be sent
     * to the app after it is resumed only if the app is in split-screen.
     *
     * <p>Setting this property to {@code false} informs the system that the activity must be
     * opted-out from the compatibility treatment even if the device manufacturer has opted the app
     * into the treatment.
     *
     * <p>If the property remains unset the system will apply the treatment only if it had
     * previously been enabled both at the device and app level by the device manufacturer.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_ENABLE_FAKE_FOCUS"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_ENABLE_FAKE_FOCUS = "android.window.PROPERTY_COMPAT_ENABLE_FAKE_FOCUS";

    /**
     * Application level {@link android.content.pm.PackageManager.Property PackageManager.Property}
     * for an app to inform the system that the app can be opted-in or opted-out from the
     * compatibility treatment that avoids {@link android.app.Activity#setRequestedOrientation
     * Activity#setRequestedOrientation()} loops. Loops can be triggered by the OEM-configured
     * ignore requested orientation display setting (on Android 12 (API level 31) and higher) or by
     * the landscape natural orientation of the device.
     *
     * <p>The treatment is disabled by default but device manufacturers can enable the treatment
     * using their discretion to improve display compatibility.
     *
     * <p>With this property set to {@code true}, the system could ignore
     * {@link android.app.Activity#setRequestedOrientation Activity#setRequestedOrientation()} call
     * from an app if one of the following conditions are true:
     * <ul>
     *     <li>Activity is relaunching due to the previous
     *     {@link android.app.Activity#setRequestedOrientation Activity#setRequestedOrientation()}
     *     call.
     *     <li>Camera compatibility force rotation treatment is active for the package.
     * </ul>
     *
     * <p>Setting this property to {@code false} informs the system that the app must be
     * opted-out from the compatibility treatment even if the device manufacturer has opted the app
     * into the treatment.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;application&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_COMPAT_IGNORE_REQUESTED_ORIENTATION"
     *     android:value="true|false"/&gt;
     * &lt;/application&gt;
     * </pre>
     */
    String PROPERTY_COMPAT_IGNORE_REQUESTED_ORIENTATION = "android.window.PROPERTY_COMPAT_IGNORE_REQUESTED_ORIENTATION";

    /**
     * Activity or Application level {@link android.content.pm.PackageManager.Property
     * PackageManager.Property} for an app to declare that System UI should be shown for this
     * app/component to allow it to be launched as multiple instances.  This property only affects
     * SystemUI behavior and does _not_ affect whether a component can actually be launched into
     * multiple instances, which is determined by the Activity's {@code launchMode} or the launching
     * Intent's flags.  If the property is set on the Application, then all components within that
     * application will use that value unless specified per component.
     *
     * The value must be a boolean string.
     *
     * <p><b>Syntax:</b>
     * <pre>
     * &lt;activity&gt;
     *   &lt;property
     *     android:name="android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI"
     *     android:value="true|false"/&gt;
     * &lt;/activity&gt;
     * </pre>
     */
    String PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI = "android.window.PROPERTY_SUPPORTS_MULTI_INSTANCE_SYSTEM_UI";

    /**
     * Indicates the app that registered the callback is not visible in screen recording.
     */
    int SCREEN_RECORDING_STATE_NOT_VISIBLE = 0;

    /**
     * Indicates the app that registered the callback is visible in screen recording.
     */
    int SCREEN_RECORDING_STATE_VISIBLE = 1;

    final class LayoutParams implements Parcelable {
        public static final int ALPHA_CHANGED = 128;

        public static final int ANIMATION_CHANGED = 16;

        /**
         * Value for {@link #screenBrightness} and {@link #buttonBrightness}
         * indicating that the screen or button backlight brightness should be set
         * to the hightest value when this window is in front.
         */
        public static final float BRIGHTNESS_OVERRIDE_FULL = 1.0f;

        /**
         * Default value for {@link #screenBrightness} and {@link #buttonBrightness}
         * indicating that the brightness value is not overridden for this window
         * and normal brightness policy should be used.
         */
        public static final float BRIGHTNESS_OVERRIDE_NONE = -1.0f;

        /**
         * Value for {@link #screenBrightness} and {@link #buttonBrightness}
         * indicating that the screen or button backlight brightness should be set
         * to the lowest value when this window is in front.
         */
        public static final float BRIGHTNESS_OVERRIDE_OFF = 0.0f;

        public static final int DIM_AMOUNT_CHANGED = 32;

        /**
         * Indicates whether this window wants the HDR conversion is disabled.
         */
        public static final int DISPLAY_FLAG_DISABLE_HDR_CONVERSION = 1;

        /**
         * Start of window types that represent normal application windows.
         */
        public static final int FIRST_APPLICATION_WINDOW = 1;

        /**
         * Start of types of sub-windows.  The {@link #token} of these windows
         * must be set to the window they are attached to.  These types of
         * windows are kept next to their attached window in Z-order, and their
         * coordinate space is relative to their attached window.
         */
        public static final int FIRST_SUB_WINDOW = 1000;

        /**
         * Start of system-specific window types.  These are not normally
         * created by applications.
         */
        public static final int FIRST_SYSTEM_WINDOW = 2000;

        public static final int FLAGS_CHANGED = 4;

        /**
         * Window flag: as long as this window is visible to the user, allow
         *   the lock screen to activate while the screen is on.
         *   This can be used independently, or in combination with
         *   {@link #FLAG_KEEP_SCREEN_ON} and/or {@link #FLAG_SHOW_WHEN_LOCKED}
         */
        public static final int FLAG_ALLOW_LOCK_WHILE_SCREEN_ON = 1;

        /**
         * Window flag: when set, inverts the input method focusability of the window.
         *
         *  The effect of setting this flag depends on whether {@link #FLAG_NOT_FOCUSABLE} is set:
         *  <p>
         *  If {@link #FLAG_NOT_FOCUSABLE} is <em>not</em> set, i.e. when the window is focusable,
         *  setting this flag prevents this window from becoming the target of the input method.
         *  Consequently, it will <em>not</em> be able to interact with the input method,
         *  and will be layered above the input method (unless there is another input method
         *  target above it).
         *
         *  <p>
         *  If {@link #FLAG_NOT_FOCUSABLE} <em>is</em> set, setting this flag requests for the window
         *  to be the input method target even though  the window is <em>not</em> focusable.
         *  Consequently, it will be layered below the input method.
         *  Note: Windows that set {@link #FLAG_NOT_FOCUSABLE} cannot interact with the input method,
         *  regardless of this flag.
         */
        public static final int FLAG_ALT_FOCUSABLE_IM = 131072;

        /**
         * Window flag: enable blur behind for this window.
         */
        public static final int FLAG_BLUR_BEHIND = 4;

        /**
         * Window flag: everything behind this window will be dimmed.
         *   Use {@link #dimAmount} to control the amount of dim.
         */
        public static final int FLAG_DIM_BEHIND = 2;

        /**
         * Window flag: when set the window will cause the keyguard to be
         * dismissed, only if it is not a secure lock keyguard. Because such a
         * keyguard is not needed for security, it will never re-appear if the
         * user navigates to another window (in contrast to
         * {@link #FLAG_SHOW_WHEN_LOCKED}, which will only temporarily hide both
         * secure and non-secure keyguards but ensure they reappear when the
         * user moves to another UI that doesn't hide them). If the keyguard is
         * currently active and is secure (requires an unlock credential) than
         * the user will still need to confirm it before seeing this window,
         * unless {@link #FLAG_SHOW_WHEN_LOCKED} has also been set.
         *
         * @deprecated Use {@link #FLAG_SHOW_WHEN_LOCKED} or
         *             {@link KeyguardManager#requestDismissKeyguard} instead.
         *             Since keyguard was dismissed all the time as long as an
         *             activity with this flag on its window was focused,
         *             keyguard couldn't guard against unintentional touches on
         *             the screen, which isn't desired.
         */
        @Deprecated
        public static final int FLAG_DISMISS_KEYGUARD = 4194304;

        /**
         * Window flag: turn on dithering when compositing this window to
         *   the screen.
         *  @deprecated This flag is no longer used.
         */
        @Deprecated
        public static final int FLAG_DITHER = 4096;

        /**
         * Flag indicating that this Window is responsible for drawing the background for the
         * system bars. If set, the system bars are drawn with a transparent background and the
         * corresponding areas in this window are filled with the colors specified in
         * {@link Window#getStatusBarColor()} and {@link Window#getNavigationBarColor()}.
         */
        public static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = -2147483648;

        /**
         * Window flag: override {@link #FLAG_FULLSCREEN} and force the
         * screen decorations (such as the status bar) to be shown.
         *
         * @deprecated This value became API "by accident", and shouldn't be used by 3rd party
         * applications.
         */
        @Deprecated
        public static final int FLAG_FORCE_NOT_FULLSCREEN = 2048;

        /**
         * Window flag: hide all screen decorations (such as the status bar) while
         * this window is displayed.  This allows the window to use the entire
         * display space for itself -- the status bar will be hidden when
         * an app window with this flag set is on the top layer. A fullscreen window
         * will ignore a value of {@link #SOFT_INPUT_ADJUST_RESIZE} for the window's
         * {@link #softInputMode} field; the window will stay fullscreen
         * and will not resize.
         *
         * <p>This flag can be controlled in your theme through the
         * {@link android.R.attr#windowFullscreen} attribute; this attribute
         * is automatically set for you in the standard fullscreen themes
         * such as {@link android.R.style#Theme_NoTitleBar_Fullscreen},
         * {@link android.R.style#Theme_Black_NoTitleBar_Fullscreen},
         * {@link android.R.style#Theme_Light_NoTitleBar_Fullscreen},
         * {@link android.R.style#Theme_Holo_NoActionBar_Fullscreen},
         * {@link android.R.style#Theme_Holo_Light_NoActionBar_Fullscreen},
         * {@link android.R.style#Theme_DeviceDefault_NoActionBar_Fullscreen}, and
         * {@link android.R.style#Theme_DeviceDefault_Light_NoActionBar_Fullscreen}.</p>
         *
         * @deprecated Use {@link WindowInsetsController#hide(int)} with {@link Type#statusBars()}
         * instead.
         */
        @Deprecated
        public static final int FLAG_FULLSCREEN = 1024;

        /**
         * <p>Indicates whether this window should be hardware accelerated.
         * Requesting hardware acceleration does not guarantee it will happen.</p>
         *
         * <p>This flag can be controlled programmatically <em>only</em> to enable
         * hardware acceleration. To enable hardware acceleration for a given
         * window programmatically, do the following:</p>
         *
         * <pre>
         * Window w = activity.getWindow(); // in Activity's onCreate() for instance
         * w.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
         *         WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
         * </pre>
         *
         * <p>It is important to remember that this flag <strong>must</strong>
         * be set before setting the content view of your activity or dialog.</p>
         *
         * <p>This flag cannot be used to disable hardware acceleration after it
         * was enabled in your manifest using
         * {@link android.R.attr#hardwareAccelerated}. If you need to selectively
         * and programmatically disable hardware acceleration (for automated testing
         * for instance), make sure it is turned off in your manifest and enable it
         * on your activity or dialog when you need it instead, using the method
         * described above.</p>
         *
         * <p>This flag is automatically set by the system if the
         * {@link android.R.attr#hardwareAccelerated android:hardwareAccelerated}
         * XML attribute is set to true on an activity or on the application.</p>
         */
        public static final int FLAG_HARDWARE_ACCELERATED = 16777216;

        /**
         * Window flag: intended for windows that will often be used when the user is
         *  holding the screen against their face, it will aggressively filter the event
         *  stream to prevent unintended presses in this situation that may not be
         *  desired for a particular window, when such an event stream is detected, the
         *  application will receive a CANCEL motion event to indicate this so applications
         *  can handle this accordingly by taking no action on the event
         *  until the finger is released.
         */
        public static final int FLAG_IGNORE_CHEEK_PRESSES = 32768;

        /**
         * Window flag: as long as this window is visible to the user, keep
         *   the device's screen turned on and bright.
         */
        public static final int FLAG_KEEP_SCREEN_ON = 128;

        /**
         * Window flag: When requesting layout with an attached window, the attached window may
         * overlap with the screen decorations of the parent window such as the navigation bar. By
         * including this flag, the window manager will layout the attached window within the decor
         * frame of the parent window such that it doesn't overlap with screen decorations.
         *
         * @deprecated Use {@link #setFitInsetsTypes(int)} to determine whether the attached
         * window will overlap with system bars.
         */
        @Deprecated
        public static final int FLAG_LAYOUT_ATTACHED_IN_DECOR = 1073741824;

        /**
         * Window flag: a special option only for use in combination with
         * {@link #FLAG_LAYOUT_IN_SCREEN}.  When requesting layout in the
         * screen your window may appear on top of or behind screen decorations
         * such as the status bar.  By also including this flag, the window
         * manager will report the inset rectangle needed to ensure your
         * content is not covered by screen decorations.  This flag is normally
         * set for you by Window as described in {@link Window#setFlags}
         *
         * @deprecated Insets will always be delivered to your application.
         */
        @Deprecated
        public static final int FLAG_LAYOUT_INSET_DECOR = 65536;

        /**
         * Window flag: allow window contents to extend in to the screen's
         * overscan area, if there is one.  The window should still correctly
         * position its contents to take the overscan area into account.
         *
         * <p>This flag can be controlled in your theme through the
         * {@link android.R.attr#windowOverscan} attribute; this attribute
         * is automatically set for you in the standard overscan themes
         * such as
         * {@link android.R.style#Theme_Holo_NoActionBar_Overscan},
         * {@link android.R.style#Theme_Holo_Light_NoActionBar_Overscan},
         * {@link android.R.style#Theme_DeviceDefault_NoActionBar_Overscan}, and
         * {@link android.R.style#Theme_DeviceDefault_Light_NoActionBar_Overscan}.</p>
         *
         * <p>When this flag is enabled for a window, its normal content may be obscured
         * to some degree by the overscan region of the display.  To ensure key parts of
         * that content are visible to the user, you can use
         * {@link View#setFitsSystemWindows(boolean) View.setFitsSystemWindows(boolean)}
         * to set the point in the view hierarchy where the appropriate offsets should
         * be applied.  (This can be done either by directly calling this function, using
         * the {@link android.R.attr#fitsSystemWindows} attribute in your view hierarchy,
         * or implementing you own {@link View#fitSystemWindows(android.graphics.Rect)
         * View.fitSystemWindows(Rect)} method).</p>
         *
         * <p>This mechanism for positioning content elements is identical to its equivalent
         * use with layout and {@link View#setSystemUiVisibility(int)
         * View.setSystemUiVisibility(int)}; here is an example layout that will correctly
         * position its UI elements with this overscan flag is set:</p>
         *
         * {@sample development/samples/ApiDemos/res/layout/overscan_activity.xml complete}
         *
         * @deprecated Overscan areas aren't set by any Android product anymore as of Android 11.
         */
        @Deprecated
        public static final int FLAG_LAYOUT_IN_OVERSCAN = 33554432;

        /**
         * Window flag for attached windows: Place the window within the entire screen, ignoring
         * any constraints from the parent window.
         *
         *  <p>Note: on displays that have a {@link DisplayCutout}, the window may be placed
         *  such that it avoids the {@link DisplayCutout} area if necessary according to the
         *  {@link #layoutInDisplayCutoutMode}.
         */
        public static final int FLAG_LAYOUT_IN_SCREEN = 256;

        /**
         * Window flag: allow window to extend outside of the screen.
         */
        public static final int FLAG_LAYOUT_NO_LIMITS = 512;

        /**
         * Flag for a window in local focus mode.
         * Window in local focus mode can control focus independent of window manager using
         * {@link Window#setLocalFocus(boolean, boolean)}.
         * Usually window in this mode will not get touch/key events from window manager, but will
         * get events only via local injection using {@link Window#injectInputEvent(InputEvent)}.
         */
        public static final int FLAG_LOCAL_FOCUS_MODE = 268435456;

        /**
         * Window flag: this window won't ever get key input focus, so the
         *  user can not send key or other button events to it.  Those will
         *  instead go to whatever focusable window is behind it.  This flag
         *  will also enable {@link #FLAG_NOT_TOUCH_MODAL} whether or not that
         *  is explicitly set.
         *
         *  <p>Setting this flag also implies that the window will not need to
         *  interact with
         *  a soft input method, so it will be Z-ordered and positioned
         *  independently of any active input method (typically this means it
         *  gets Z-ordered on top of the input method, so it can use the full
         *  screen for its content and cover the input method if needed.  You
         *  can use {@link #FLAG_ALT_FOCUSABLE_IM} to modify this behavior.
         */
        public static final int FLAG_NOT_FOCUSABLE = 8;

        /**
         * Window flag: this window can never receive touch events.
         *
         * <p>The intention of this flag is to leave the touch to be handled by some window below
         * this window (in Z order).
         *
         * <p>Starting from Android {@link Build.VERSION_CODES#S}, for security reasons, touch
         * events that pass through windows containing this flag (ie. are within the bounds of the
         * window) will only be delivered to the touch-consuming window if one (or more) of the
         * items below are true:
         * <ol>
         *   <li><b>Same UID</b>: This window belongs to the same UID that owns the touch-consuming
         *   window.
         *   <li><b>Trusted windows</b>: This window is trusted. Trusted windows include (but are
         *   not limited to) accessibility windows ({@link #TYPE_ACCESSIBILITY_OVERLAY}), the IME
         *   ({@link #TYPE_INPUT_METHOD}) and assistant windows (TYPE_VOICE_INTERACTION). Windows of
         *   type {@link #TYPE_APPLICATION_OVERLAY} are <b>not</b> trusted, see below.
         *   <li><b>Invisible windows</b>: This window is {@link View#GONE} or
         *   {@link View#INVISIBLE}.
         *   <li><b>Fully transparent windows</b>: This window has {@link LayoutParams#alpha} equal
         *   to 0.
         *   <li><b>One SAW window with enough transparency</b>: This window is of type {@link
         *   #TYPE_APPLICATION_OVERLAY}, has {@link LayoutParams#alpha} below or equal to the
         *   <a href="#MaximumOpacity">maximum obscuring opacity</a> (see below) and it's the
         *   <b>only</b> window of type {@link #TYPE_APPLICATION_OVERLAY} from this UID in the touch
         *   path.
         *   <li><b>Multiple SAW windows with enough transparency</b>: The multiple overlapping
         *   {@link #TYPE_APPLICATION_OVERLAY} windows in the
         *   touch path from this UID have a <b>combined obscuring opacity</b> below or equal to
         *   the <a href="#MaximumOpacity">maximum obscuring opacity</a>. See section
         *   <a href="#ObscuringOpacity">Combined obscuring opacity</a> below on how to compute this
         *   value.
         * </ol>
         * <p>If none of these cases hold, the touch will not be delivered and a message will be
         * logged to logcat.</p>
         *
         * <a name="MaximumOpacity"></a>
         * <h3>Maximum obscuring opacity</h3>
         * <p>This value is <b>0.8</b>. Apps that want to gather this value from the system rather
         * than hard-coding it might want to use {@link
         * android.hardware.input.InputManager#getMaximumObscuringOpacityForTouch()}.</p>
         *
         * <a name="ObscuringOpacity"></a>
         * <h3>Combined obscuring opacity</h3>
         *
         * <p>The <b>combined obscuring opacity</b> of a set of windows is obtained by combining the
         * opacity values of all windows in the set using the associative and commutative operation
         * defined as:
         * <pre>
         * opacity({A,B}) = 1 - (1 - opacity(A))*(1 - opacity(B))
         * </pre>
         * <p>where {@code opacity(X)} is the {@link LayoutParams#alpha} of window X. So, for a set
         * of windows {@code {W1, .., Wn}}, the combined obscuring opacity will be:
         * <pre>
         * opacity({W1, .., Wn}) = 1 - (1 - opacity(W1)) * ... * (1 - opacity(Wn))
         * </pre>
         */
        public static final int FLAG_NOT_TOUCHABLE = 16;

        /**
         * Window flag: even when this window is focusable (its
         *  {@link #FLAG_NOT_FOCUSABLE} is not set), allow any pointer events
         *  outside of the window to be sent to the windows behind it.  Otherwise
         *  it will consume all pointer events itself, regardless of whether they
         *  are inside of the window.
         */
        public static final int FLAG_NOT_TOUCH_MODAL = 32;

        /**
         * Window flag: a special mode where the layout parameters are used
         *  to perform scaling of the surface when it is composited to the
         *  screen.
         */
        public static final int FLAG_SCALED = 16384;

        /**
         * Window flag: treat the content of the window as secure, preventing
         *  it from appearing in screenshots or from being viewed on non-secure
         *  displays.
         *
         *  <p>See {@link android.view.View#setContentSensitivity(int)}, a window hosting
         *  a sensitive view will be marked as secure during media projection, preventing
         *  it from being viewed on non-secure displays and during screen share.
         *
         *  <p>See {@link android.view.Display#FLAG_SECURE} for more details about
         *  secure surfaces and secure displays.
         */
        public static final int FLAG_SECURE = 8192;

        /**
         * Window flag: ask that the system wallpaper be shown behind
         *  your window.  The window surface must be translucent to be able
         *  to actually see the wallpaper behind it; this flag just ensures
         *  that the wallpaper surface will be there if this window actually
         *  has translucent regions.
         *
         *  <p>This flag can be controlled in your theme through the
         *  {@link android.R.attr#windowShowWallpaper} attribute; this attribute
         *  is automatically set for you in the standard wallpaper themes
         *  such as {@link android.R.style#Theme_Wallpaper},
         *  {@link android.R.style#Theme_Wallpaper_NoTitleBar},
         *  {@link android.R.style#Theme_Wallpaper_NoTitleBar_Fullscreen},
         *  {@link android.R.style#Theme_Holo_Wallpaper},
         *  {@link android.R.style#Theme_Holo_Wallpaper_NoTitleBar},
         *  {@link android.R.style#Theme_DeviceDefault_Wallpaper}, and
         *  {@link android.R.style#Theme_DeviceDefault_Wallpaper_NoTitleBar}.</p>
         *
         *  <p> When this flag is set, all touch events sent to this window is also sent to the
         *  wallpaper, which is used to interact with live wallpapers. Check
         *  {@link LayoutParams#areWallpaperTouchEventsEnabled()}, which is set to {@code true}
         *  by default. When showing sensitive information on the window, if you want to disable
         *  sending the touch events to the wallpaper, use
         *  {@link LayoutParams#setWallpaperTouchEventsEnabled(boolean)}.</p>
         */
        public static final int FLAG_SHOW_WALLPAPER = 1048576;

        /**
         * Window flag: special flag to let windows be shown when the screen
         *  is locked. This will let application windows take precedence over
         *  key guard or any other lock screens. Can be used with
         *  {@link #FLAG_KEEP_SCREEN_ON} to turn screen on and display windows
         *  directly before showing the key guard window.  Can be used with
         *  {@link #FLAG_DISMISS_KEYGUARD} to automatically fully dismisss
         *  non-secure keyguards.  This flag only applies to the top-most
         *  full-screen window.
         *  @deprecated Use {@link android.R.attr#showWhenLocked} or
         *  {@link android.app.Activity#setShowWhenLocked(boolean)} instead to prevent an
         *  unintentional double life-cycle event.
         */
        @Deprecated
        public static final int FLAG_SHOW_WHEN_LOCKED = 524288;

        /**
         * Window flag: when set the window will accept for touch events
         *  outside of its bounds to be sent to other windows that also
         *  support split touch.  When this flag is not set, the first pointer
         *  that goes down determines the window to which all subsequent touches
         *  go until all pointers go up.  When this flag is set, each pointer
         *  (not necessarily the first) that goes down determines the window
         *  to which all subsequent touches of that pointer will go until that
         *  pointer goes up thereby enabling touches with multiple pointers
         *  to be split across multiple windows.
         */
        public static final int FLAG_SPLIT_TOUCH = 8388608;

        /**
         * Window flag: when set, if the device is asleep when the touch
         *  screen is pressed, you will receive this first touch event.  Usually
         *  the first touch event is consumed by the system since the user can
         *  not see what they are pressing on.
         *
         *  @deprecated This flag has no effect.
         */
        @Deprecated
        public static final int FLAG_TOUCHABLE_WHEN_WAKING = 64;

        /**
         * Window flag: request a translucent navigation bar with minimal system-provided
         * background protection.
         *
         * <p>This flag can be controlled in your theme through the
         * {@link android.R.attr#windowTranslucentNavigation} attribute; this attribute
         * is automatically set for you in the standard translucent decor themes
         * such as
         * {@link android.R.style#Theme_Holo_NoActionBar_TranslucentDecor},
         * {@link android.R.style#Theme_Holo_Light_NoActionBar_TranslucentDecor},
         * {@link android.R.style#Theme_DeviceDefault_NoActionBar_TranslucentDecor}, and
         * {@link android.R.style#Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor}.</p>
         *
         * <p>When this flag is enabled for a window, it automatically sets
         * the system UI visibility flags {@link View#SYSTEM_UI_FLAG_LAYOUT_STABLE} and
         * {@link View#SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION}.</p>
         *
         * <p>Note: For devices that support
         * {@link android.content.pm.PackageManager#FEATURE_AUTOMOTIVE} this flag can be disabled
         * by the car manufacturers.
         *
         * @deprecated Use {@link Window#setNavigationBarColor(int)} with a half-translucent color
         * instead.
         */
        @Deprecated
        public static final int FLAG_TRANSLUCENT_NAVIGATION = 134217728;

        /**
         * Window flag: request a translucent status bar with minimal system-provided
         * background protection.
         *
         * <p>This flag can be controlled in your theme through the
         * {@link android.R.attr#windowTranslucentStatus} attribute; this attribute
         * is automatically set for you in the standard translucent decor themes
         * such as
         * {@link android.R.style#Theme_Holo_NoActionBar_TranslucentDecor},
         * {@link android.R.style#Theme_Holo_Light_NoActionBar_TranslucentDecor},
         * {@link android.R.style#Theme_DeviceDefault_NoActionBar_TranslucentDecor}, and
         * {@link android.R.style#Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor}.</p>
         *
         * <p>When this flag is enabled for a window, it automatically sets
         * the system UI visibility flags {@link View#SYSTEM_UI_FLAG_LAYOUT_STABLE} and
         * {@link View#SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN}.</p>
         *
         * <p>Note: For devices that support
         * {@link android.content.pm.PackageManager#FEATURE_AUTOMOTIVE} this flag may be ignored.
         *
         * @deprecated Use {@link Window#setStatusBarColor(int)} with a half-translucent color
         * instead.
         */
        @Deprecated
        public static final int FLAG_TRANSLUCENT_STATUS = 67108864;

        /**
         * Window flag: when set as a window is being added or made
         *  visible, once the window has been shown then the system will
         *  poke the power manager's user activity (as if the user had woken
         *  up the device) to turn the screen on.
         *  @deprecated Use {@link android.R.attr#turnScreenOn} or
         *  {@link android.app.Activity#setTurnScreenOn(boolean)} instead to prevent an
         *  unintentional double life-cycle event.
         */
        @Deprecated
        public static final int FLAG_TURN_SCREEN_ON = 2097152;

        /**
         * Window flag: if you have set {@link #FLAG_NOT_TOUCH_MODAL}, you
         *  can set this flag to receive a single special MotionEvent with
         *  the action
         *  {@link MotionEvent#ACTION_OUTSIDE MotionEvent.ACTION_OUTSIDE} for
         *  touches that occur outside of your window.  Note that you will not
         *  receive the full down/move/up gesture, only the location of the
         *  first down as an ACTION_OUTSIDE.
         */
        public static final int FLAG_WATCH_OUTSIDE_TOUCH = 262144;

        public static final int FORMAT_CHANGED = 8;

        /**
         * End of types of application windows.
         */
        public static final int LAST_APPLICATION_WINDOW = 99;

        /**
         * End of types of sub-windows.
         */
        public static final int LAST_SUB_WINDOW = 1999;

        /**
         * End of types of system windows.
         */
        public static final int LAST_SYSTEM_WINDOW = 2999;

        public static final int LAYOUT_CHANGED = 1;

        /**
         * The window is always allowed to extend into the {@link DisplayCutout} areas on the all
         * edges of the screen.
         *
         * <p>
         * The window must make sure that no important content overlaps with the
         * {@link DisplayCutout}.
         *
         * <p>
         * In this mode, the window extends under cutouts on the all edges of the display in both
         * portrait and landscape, regardless of whether the window is hiding the system bars.
         *
         * <p>
         * Note: Android might not allow the content view to overlap the system bars in view level.
         * To override this behavior and allow content to be able to extend into the cutout area,
         * call {@link Window#setDecorFitsSystemWindows(boolean)} with {@code false}.
         *
         * @see DisplayCutout
         * @see WindowInsets#getDisplayCutout()
         * @see #layoutInDisplayCutoutMode
         * @see android.R.attr#windowLayoutInDisplayCutoutMode
         *         android:windowLayoutInDisplayCutoutMode
         */
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS = 3;

        /**
         * The window is allowed to extend into the {@link DisplayCutout} area, only if the
         * {@link DisplayCutout} is fully contained within a system bar or the {@link DisplayCutout}
         * is not deeper than 16 dp, but this depends on the OEM choice. Otherwise, the window is
         * laid out such that it does not overlap with the {@link DisplayCutout} area.
         *
         * <p>
         * In practice, this means that if the window did not set {@link #FLAG_FULLSCREEN} or
         * {@link View#SYSTEM_UI_FLAG_FULLSCREEN}, it can extend into the cutout area in portrait
         * if the cutout is at the top edge. Similarly for
         * {@link View#SYSTEM_UI_FLAG_HIDE_NAVIGATION} and a cutout at the bottom of the screen.
         * Otherwise (i.e. fullscreen or landscape) it is laid out such that it does not overlap the
         * cutout area.
         *
         * <p>
         * The usual precautions for not overlapping with the status and navigation bar are
         * sufficient for ensuring that no important content overlaps with the DisplayCutout.
         *
         * <p>
         * Note: OEMs can have an option to allow the window to always extend into the
         * {@link DisplayCutout} area, no matter the cutout flag set, when the {@link DisplayCutout}
         * is on the different side from system bars, only if the {@link DisplayCutout} overlaps at
         * most 16dp with the windows.
         * In such case, OEMs must provide an opt-in/out affordance for users.
         *
         * @see DisplayCutout
         * @see WindowInsets
         * @see #layoutInDisplayCutoutMode
         * @see android.R.attr#windowLayoutInDisplayCutoutMode
         *         android:windowLayoutInDisplayCutoutMode
         */
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT = 0;

        /**
         * The window is never allowed to overlap with the DisplayCutout area.
         *
         * <p>
         * This should be used with windows that transiently set
         * {@link View#SYSTEM_UI_FLAG_FULLSCREEN} or {@link View#SYSTEM_UI_FLAG_HIDE_NAVIGATION}
         * to avoid a relayout of the window when the respective flag is set or cleared.
         *
         * @see DisplayCutout
         * @see #layoutInDisplayCutoutMode
         * @see android.R.attr#windowLayoutInDisplayCutoutMode
         *         android:windowLayoutInDisplayCutoutMode
         */
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER = 2;

        /**
         * The window is always allowed to extend into the {@link DisplayCutout} areas on the short
         * edges of the screen.
         *
         * <p>
         * The window will never extend into a {@link DisplayCutout} area on the long edges of the
         * screen, unless the {@link DisplayCutout} is not deeper than 16 dp, but this depends on
         * the OEM choice.
         *
         * <p>
         * Note: OEMs can have an option to allow the window to extend into the
         * {@link DisplayCutout} area on the long edge side, only if the cutout overlaps at most
         * 16dp with the windows. In such case, OEMs must provide an opt-in/out affordance for
         * users.
         *
         * <p>
         * The window must make sure that no important content overlaps with the
         * {@link DisplayCutout}.
         *
         * <p>
         * In this mode, the window extends under cutouts on the short edge of the display in both
         * portrait and landscape, regardless of whether the window is hiding the system bars:<br/>
         * <img src="{@docRoot}reference/android/images/display_cutout/short_edge/fullscreen_top_no_letterbox.png"
         * height="720"
         * alt="Screenshot of a fullscreen activity on a display with a cutout at the top edge in
         *         portrait, no letterbox is applied."/>
         *
         * <img src="{@docRoot}reference/android/images/display_cutout/short_edge/landscape_top_no_letterbox.png"
         * width="720"
         * alt="Screenshot of an activity on a display with a cutout at the top edge in landscape,
         *         no letterbox is applied."/>
         *
         * <p>
         * A cutout in the corner can be considered to be on different edge in different device
         * rotations. This behavior may vary from device to device. Use this flag is possible to
         * letterbox your app if the display cutout is at corner.
         *
         * <p>
         * On the other hand, should the cutout be on the long edge of the display, a letterbox will
         * be applied such that the window does not extend into the cutout on either long edge:
         * <br/>
         * <img src="{@docRoot}reference/android/images/display_cutout/short_edge/portrait_side_letterbox.png"
         * height="720"
         * alt="Screenshot of an activity on a display with a cutout on the long edge in portrait,
         *         letterbox is applied."/>
         *
         * <p>
         * Note: Android might not allow the content view to overlap the system bars in view level.
         * To override this behavior and allow content to be able to extend into the cutout area,
         * call {@link Window#setDecorFitsSystemWindows(boolean)} with {@code false}.
         *
         * @see DisplayCutout
         * @see WindowInsets#getDisplayCutout()
         * @see #layoutInDisplayCutoutMode
         * @see android.R.attr#windowLayoutInDisplayCutoutMode
         *         android:windowLayoutInDisplayCutoutMode
         */
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1;

        public static final int MEMORY_TYPE_CHANGED = 256;

        /**
         * @deprecated this is ignored, this value is set automatically when needed.
         */
        @Deprecated
        public static final int MEMORY_TYPE_GPU = 2;

        /**
         * @deprecated this is ignored, this value is set automatically when needed.
         */
        @Deprecated
        public static final int MEMORY_TYPE_HARDWARE = 1;

        /**
         * @deprecated this is ignored, this value is set automatically when needed.
         */
        @Deprecated
        public static final int MEMORY_TYPE_NORMAL = 0;

        /**
         * @deprecated this is ignored, this value is set automatically when needed.
         */
        @Deprecated
        public static final int MEMORY_TYPE_PUSH_BUFFERS = 3;

        public static final int ROTATION_ANIMATION_CHANGED = 4096;

        /**
         * Value for {@link #rotationAnimation} which specifies that this
         * window will fade in or out following a rotation.
         */
        public static final int ROTATION_ANIMATION_CROSSFADE = 1;

        /**
         * Value for {@link #rotationAnimation} which specifies that this window
         * will immediately disappear or appear following a rotation.
         */
        public static final int ROTATION_ANIMATION_JUMPCUT = 2;

        /**
         * Value for {@link #rotationAnimation} which specifies that this
         * window will visually rotate in or out following a rotation.
         */
        public static final int ROTATION_ANIMATION_ROTATE = 0;

        /**
         * Value for {@link #rotationAnimation} to specify seamless rotation mode.
         * This works like JUMPCUT but will fall back to CROSSFADE if rotation
         * can't be applied without pausing the screen. For example, this is ideal
         * for Camera apps which don't want the viewfinder contents to ever rotate
         * or fade (and rather to be seamless) but also don't want ROTATION_ANIMATION_JUMPCUT
         * during app transition scenarios where seamless rotation can't be applied.
         */
        public static final int ROTATION_ANIMATION_SEAMLESS = 3;

        public static final int SCREEN_BRIGHTNESS_CHANGED = 2048;

        public static final int SCREEN_ORIENTATION_CHANGED = 1024;

        /**
         * Adjustment option for {@link #softInputMode}: set to have a window
         *  not adjust for a shown input method.  The window will not be resized,
         *  and it will not be panned to make its focus visible.
         */
        public static final int SOFT_INPUT_ADJUST_NOTHING = 48;

        /**
         * Adjustment option for {@link #softInputMode}: set to have a window
         *  pan when an input method is
         *  shown, so it doesn't need to deal with resizing but just panned
         *  by the framework to ensure the current input focus is visible.  This
         *  can <em>not</em> be combined with {@link #SOFT_INPUT_ADJUST_RESIZE}; if
         *  neither of these are set, then the system will try to pick one or
         *  the other depending on the contents of the window.
         */
        public static final int SOFT_INPUT_ADJUST_PAN = 32;

        /**
         * Adjustment option for {@link #softInputMode}: set to allow the
         *  window to be resized when an input
         *  method is shown, so that its contents are not covered by the input
         *  method.  This can <em>not</em> be combined with
         *  {@link #SOFT_INPUT_ADJUST_PAN}; if
         *  neither of these are set, then the system will try to pick one or
         *  the other depending on the contents of the window. If the window's
         *  layout parameter flags include {@link #FLAG_FULLSCREEN}, this
         *  value for {@link #softInputMode} will be ignored; the window will
         *  not resize, but will stay fullscreen.
         *
         *  @deprecated Call {@link Window#setDecorFitsSystemWindows(boolean)} with {@code false} and
         *  install an {@link OnApplyWindowInsetsListener} on your root content view that fits insets
         *  of type {@link Type#ime()}.
         */
        @Deprecated
        public static final int SOFT_INPUT_ADJUST_RESIZE = 16;

        /**
         * Adjustment option for {@link #softInputMode}: nothing specified.
         *  The system will try to pick one or
         *  the other depending on the contents of the window.
         */
        public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0;

        /**
         * Bit for {@link #softInputMode}: set when the user has navigated
         * forward to the window.  This is normally set automatically for
         * you by the system, though you may want to set it in certain cases
         * when you are displaying a window yourself.  This flag will always
         * be cleared automatically after the window is displayed.
         */
        public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 256;

        /**
         * Mask for {@link #softInputMode} of the bits that determine the
         * way that the window should be adjusted to accommodate the soft
         * input window.
         */
        public static final int SOFT_INPUT_MASK_ADJUST = 240;

        /**
         * Mask for {@link #softInputMode} of the bits that determine the
         * desired visibility state of the soft input area for this window.
         */
        public static final int SOFT_INPUT_MASK_STATE = 15;

        public static final int SOFT_INPUT_MODE_CHANGED = 512;

        /**
         * Visibility state for {@link #softInputMode}: please always hide any
         * soft input area when this window receives focus.
         */
        public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;

        /**
         * Visibility state for {@link #softInputMode}: please always make the
         * soft input area visible when this window receives input focus.
         *
         * <p>Applications that target {@link android.os.Build.VERSION_CODES#P} and later, this flag
         * is ignored unless there is a focused view that returns {@code true} from
         * {@link View#onCheckIsTextEditor()} when the window is focused.</p>
         */
        public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;

        /**
         * Visibility state for {@link #softInputMode}: please hide any soft input
         * area when normally appropriate (when the user is navigating
         * forward to your window).
         */
        public static final int SOFT_INPUT_STATE_HIDDEN = 2;

        /**
         * Visibility state for {@link #softInputMode}: please don't change the state of
         * the soft input area.
         */
        public static final int SOFT_INPUT_STATE_UNCHANGED = 1;

        /**
         * Visibility state for {@link #softInputMode}: no state has been specified. The system may
         * show or hide the software keyboard for better user experience when the window gains
         * focus.
         */
        public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;

        /**
         * Visibility state for {@link #softInputMode}: please show the soft
         * input area when normally appropriate (when the user is navigating
         * forward to your window).
         *
         * <p>Applications that target {@link android.os.Build.VERSION_CODES#P} and later, this flag
         * is ignored unless there is a focused view that returns {@code true} from
         * {@link View#onCheckIsTextEditor()} when the window is focused.</p>
         */
        public static final int SOFT_INPUT_STATE_VISIBLE = 4;

        public static final int TITLE_CHANGED = 64;

        /**
         * Window type: Windows that are overlaid <em>only</em> by a connected {@link
         * android.accessibilityservice.AccessibilityService} for interception of
         * user interactions without changing the windows an accessibility service
         * can introspect. In particular, an accessibility service can introspect
         * only windows that a sighted user can interact with which is they can touch
         * these windows or can type into these windows. For example, if there
         * is a full screen accessibility overlay that is touchable, the windows
         * below it will be introspectable by an accessibility service even though
         * they are covered by a touchable window.
         */
        public static final int TYPE_ACCESSIBILITY_OVERLAY = 2032;

        /**
         * Window type: a normal application window.  The {@link #token} must be
         * an Activity token identifying who the window belongs to.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_APPLICATION = 2;

        /**
         * Window type: like {@link #TYPE_APPLICATION_PANEL}, but layout
         *  of the window happens as that of a top-level window, <em>not</em>
         *  as a child of its container.
         */
        public static final int TYPE_APPLICATION_ATTACHED_DIALOG = 1003;

        /**
         * Window type: window for showing media (such as video).  These windows
         * are displayed behind their attached window.
         */
        public static final int TYPE_APPLICATION_MEDIA = 1001;

        /**
         * Window type: Application overlay windows are displayed above all activity windows
         * (types between {@link #FIRST_APPLICATION_WINDOW} and {@link #LAST_APPLICATION_WINDOW})
         * but below critical system windows like the status bar or IME.
         * <p>
         * The system may change the position, size, or visibility of these windows at anytime
         * to reduce visual clutter to the user and also manage resources.
         * <p>
         * Requires {@link android.Manifest.permission#SYSTEM_ALERT_WINDOW} permission.
         * <p>
         * The system will adjust the importance of processes with this window type to reduce the
         * chance of the low-memory-killer killing them.
         * <p>
         * In multi-user systems shows only on the owning user's screen.
         */
        public static final int TYPE_APPLICATION_OVERLAY = 2038;

        /**
         * Window type: a panel on top of an application window.  These windows
         * appear on top of their attached window.
         */
        public static final int TYPE_APPLICATION_PANEL = 1000;

        /**
         * Window type: special application window that is displayed while the
         * application is starting.  Not for use by applications themselves;
         * this is used by the system to display something until the
         * application can show its own windows.
         * In multiuser systems shows on all users' windows.
         */
        public static final int TYPE_APPLICATION_STARTING = 3;

        /**
         * Window type: a sub-panel on top of an application window.  These
         * windows are displayed on top their attached window and any
         * {@link #TYPE_APPLICATION_PANEL} panels.
         */
        public static final int TYPE_APPLICATION_SUB_PANEL = 1002;

        /**
         * Window type: an application window that serves as the "base" window
         * of the overall application; all other application windows will
         * appear on top of it.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_BASE_APPLICATION = 1;

        public static final int TYPE_CHANGED = 2;

        /**
         * Window type: a variation on TYPE_APPLICATION that ensures the window
         * manager will wait for this window to be drawn before the app is shown.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_DRAWN_APPLICATION = 4;

        /**
         * Window type: internal input methods windows, which appear above
         * the normal UI.  Application windows may be resized or panned to keep
         * the input focus visible while this window is displayed.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_INPUT_METHOD = 2011;

        /**
         * Window type: internal input methods dialog windows, which appear above
         * the current input method window.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_INPUT_METHOD_DIALOG = 2012;

        /**
         * Window type: dialogs that the keyguard shows
         * In multiuser systems shows on all users' windows.
         */
        public static final int TYPE_KEYGUARD_DIALOG = 2009;

        /**
         * Window type: phone.  These are non-application windows providing
         * user interaction with the phone (in particular incoming calls).
         * These windows are normally placed above all applications, but behind
         * the status bar.
         * In multiuser systems shows on all users' windows.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_PHONE = 2002;

        /**
         * Window type: priority phone UI, which needs to be displayed even if
         * the keyguard is active.  These windows must not take input
         * focus, or they will interfere with the keyguard.
         * In multiuser systems shows on all users' windows.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_PRIORITY_PHONE = 2007;

        /**
         * Window type: Window for Presentation on top of private
         * virtual display.
         */
        public static final int TYPE_PRIVATE_PRESENTATION = 2030;

        /**
         * Window type: the search bar.  There can be only one search bar
         * window; it is placed at the top of the screen.
         * In multiuser systems shows on all users' windows.
         */
        public static final int TYPE_SEARCH_BAR = 2001;

        /**
         * Window type: the status bar.  There can be only one status bar
         * window; it is placed at the top of the screen, and all other
         * windows are shifted down so they are below it.
         * In multiuser systems shows on all users' windows.
         */
        public static final int TYPE_STATUS_BAR = 2000;

        /**
         * Window type: system window, such as low power alert. These windows
         * are always on top of application windows.
         * In multiuser systems shows only on the owning user's window.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_SYSTEM_ALERT = 2003;

        /**
         * Window type: panel that slides out from the status bar
         * In multiuser systems shows on all users' windows.
         */
        public static final int TYPE_SYSTEM_DIALOG = 2008;

        /**
         * Window type: internal system error windows, appear on top of
         * everything they can.
         * In multiuser systems shows only on the owning user's window.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_SYSTEM_ERROR = 2010;

        /**
         * Window type: system overlay windows, which need to be displayed
         * on top of everything else.  These windows must not take input
         * focus, or they will interfere with the keyguard.
         * In multiuser systems shows only on the owning user's window.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_SYSTEM_OVERLAY = 2006;

        /**
         * Window type: transient notifications.
         * In multiuser systems shows only on the owning user's window.
         * @deprecated for non-system apps. Use {@link #TYPE_APPLICATION_OVERLAY} instead.
         */
        @Deprecated
        public static final int TYPE_TOAST = 2005;

        /**
         * Window type: wallpaper window, placed behind any window that wants
         * to sit on top of the wallpaper.
         * In multiuser systems shows only on the owning user's window.
         */
        public static final int TYPE_WALLPAPER = 2013;

        @Override
        public int describeContents() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeToParcel(android.os.Parcel dest, int flags) {
            throw new UnsupportedOperationException();
        }

        private LayoutParams() {
        }
    }
}
