package android.provider;

/**
 * The contract between the media provider and applications. Contains
 * definitions for the supported URIs and columns.
 * <p>
 * The media provider provides an indexed collection of common media types, such
 * as {@link Audio}, {@link Video}, and {@link Images}, from any attached
 * storage devices. Each collection is organized based on the primary MIME type
 * of the underlying content; for example, {@code image/*} content is indexed
 * under {@link Images}. The {@link Files} collection provides a broad view
 * across all collections, and does not filter by MIME type.
 */
public final class MediaStore {
    /**
     * Permission that grants access to {@link MediaColumns#OWNER_PACKAGE_NAME}
     * of every accessible media file.
     */
    public static final String ACCESS_MEDIA_OWNER_PACKAGE_NAME_PERMISSION = "com.android.providers.media.permission.ACCESS_MEDIA_OWNER_PACKAGE_NAME";

    /**
     * Permission that grants access to {@link MediaColumns#OEM_METADATA}
     * of every accessible media file.
     */
    public static final String ACCESS_OEM_METADATA_PERMISSION = "com.android.providers.media.permission.ACCESS_OEM_METADATA";

    /**
     * Standard Intent action that can be sent to have the camera application
     * capture an image and return it.
     * <p>
     * The caller may pass an extra EXTRA_OUTPUT to control where this image will be written.
     * If the EXTRA_OUTPUT is not present, then a small sized image is returned as a Bitmap
     * object in the extra field. This is useful for applications that only need a small image.
     * If the EXTRA_OUTPUT is present, then the full-sized image will be written to the Uri
     * value of EXTRA_OUTPUT.
     * As of {@link android.os.Build.VERSION_CODES#LOLLIPOP}, this uri can also be supplied through
     * {@link android.content.Intent#setClipData(ClipData)}. If using this approach, you still must
     * supply the uri through the EXTRA_OUTPUT field for compatibility with old applications.
     * If you don't set a ClipData, it will be copied there for you when calling
     * {@link Context#startActivity(Intent)}.
     * <p>
     * Regardless of whether or not EXTRA_OUTPUT is present, when an image is captured via this
     * intent, {@link android.hardware.Camera#ACTION_NEW_PICTURE} won't be broadcasted.
     * <p>
     * Note: if you app targets {@link android.os.Build.VERSION_CODES#M M} and above
     * and declares as using the {@link android.Manifest.permission#CAMERA} permission which
     * is not granted, then attempting to use this action will result in a {@link
     * java.lang.SecurityException}.
     *
     *  @see #EXTRA_OUTPUT
     *  @see android.hardware.Camera#ACTION_NEW_PICTURE
     */
    public static final String ACTION_IMAGE_CAPTURE = "android.media.action.IMAGE_CAPTURE";

    /**
     * Intent action that can be sent to have the camera application capture an image and return
     * it when the device is secured (e.g. with a pin, password, pattern, or face unlock).
     * Applications responding to this intent must not expose any personal content like existing
     * photos or videos on the device. The applications should be careful not to share any photo
     * or video with other applications or Internet. The activity should use {@link
     * Activity#setShowWhenLocked} to display on top of the
     * lock screen while secured. There is no activity stack when this flag is used, so
     * launching more than one activity is strongly discouraged.
     * <p>
     * The caller may pass an extra EXTRA_OUTPUT to control where this image will be written.
     * If the EXTRA_OUTPUT is not present, then a small sized image is returned as a Bitmap
     * object in the extra field. This is useful for applications that only need a small image.
     * If the EXTRA_OUTPUT is present, then the full-sized image will be written to the Uri
     * value of EXTRA_OUTPUT.
     * As of {@link android.os.Build.VERSION_CODES#LOLLIPOP}, this uri can also be supplied through
     * {@link android.content.Intent#setClipData(ClipData)}. If using this approach, you still must
     * supply the uri through the EXTRA_OUTPUT field for compatibility with old applications.
     * If you don't set a ClipData, it will be copied there for you when calling
     * {@link Context#startActivity(Intent)}.
     * <p>
     * Regardless of whether or not EXTRA_OUTPUT is present, when an image is captured via this
     * intent, {@link android.hardware.Camera#ACTION_NEW_PICTURE} won't be broadcasted.
     *
     * @see #ACTION_IMAGE_CAPTURE
     * @see #EXTRA_OUTPUT
     * @see android.hardware.Camera#ACTION_NEW_PICTURE
     */
    public static final String ACTION_IMAGE_CAPTURE_SECURE = "android.media.action.IMAGE_CAPTURE_SECURE";

    /**
     * Standard Intent action that can be sent to have the camera application
     * capture a
     * <a href="{@docRoot}media/platform/motion-photo-format">motion photo</a> and
     * return it.
     * <p>
     * The caller must either pass an extra EXTRA_OUTPUT to control where the image will be written,
     * or a uri through {@link android.content.Intent#setClipData(ClipData)}. If you don't set a
     * ClipData, it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * When an image is captured via this intent, {@link android.hardware.Camera#ACTION_NEW_PICTURE}
     * won't be broadcasted.
     * <p>
     * Note: If your app declares as using the {@link android.Manifest.permission#CAMERA} permission
     * which is not granted, then attempting to use this action will result in a {@link
     * java.lang.SecurityException}.
     *
     * @see #EXTRA_OUTPUT
     */
    public static final String ACTION_MOTION_PHOTO_CAPTURE = "android.provider.action.MOTION_PHOTO_CAPTURE";

    /**
     * Intent action that can be sent to have the camera application capture a
     * <a href="{@docRoot}media/platform/motion-photo-format">motion photo</a> and
     * return it when the device is secured (e.g. with a pin, password, pattern, or face unlock).
     * Applications responding to this intent must not expose any personal content like existing
     * photos or videos on the device. The applications should be careful not to share any photo
     * or video with other applications or Internet. The activity should use {@link
     * Activity#setShowWhenLocked} to display on top of the
     * lock screen while secured. There is no activity stack when this flag is used, so
     * launching more than one activity is strongly discouraged.
     * <p>
     * The caller must either pass an extra EXTRA_OUTPUT to control where the image will be written,
     * or a uri through {@link android.content.Intent#setClipData(ClipData)}. If you don't set a
     * ClipData, it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * When an image is captured via this intent, {@link android.hardware.Camera#ACTION_NEW_PICTURE}
     * won't be broadcasted.
     *
     * @see #ACTION_MOTION_PHOTO_CAPTURE
     * @see #EXTRA_OUTPUT
     */
    public static final String ACTION_MOTION_PHOTO_CAPTURE_SECURE = "android.provider.action.MOTION_PHOTO_CAPTURE_SECURE";

    /**
     * Activity Action: Allow the user to select images or videos provided by system and return it.
     * This is different than {@link Intent#ACTION_PICK} and {@link Intent#ACTION_GET_CONTENT} in
     * that
     *
     * <ul>
     *   <li>the data for this action is provided by the system
     *   <li>this action is only used for picking images and videos
     *   <li>caller gets read access to user picked items even without storage permissions
     * </ul>
     *
     * <p>Callers can optionally specify MIME type (such as {@code image/*} or {@code video/*}),
     * resulting in a range of content selection that the caller is interested in. The optional MIME
     * type can be requested with {@link Intent#setType(String)}.
     *
     * <p>If the caller needs multiple returned items (or caller wants to allow multiple selection),
     * then it can specify {@link MediaStore#EXTRA_PICK_IMAGES_MAX} to indicate this.
     *
     * <p>When the caller requests multiple selection, the value of {@link
     * MediaStore#EXTRA_PICK_IMAGES_MAX} must be a positive integer greater than 1 and less than or
     * equal to {@link MediaStore#getPickImagesMaxLimit}, otherwise {@link Activity#RESULT_CANCELED}
     * is returned. Use {@link MediaStore#EXTRA_PICK_IMAGES_IN_ORDER} in multiple selection mode to
     * allow the user to pick images in order.
     *
     * <p>If the caller needs to specify the {@link ApplicationMediaCapabilities} that should be
     * used while picking video files, use {@link MediaStore#EXTRA_MEDIA_CAPABILITIES} to indicate
     * this.
     *
     * <p>When the requested file format does not match the capabilities specified by caller and
     * the video duration is within the range that the system can handle, it will get transcoded to
     * a default supported format, otherwise, the caller will receive the original file.
     *
     * <p>Callers may use {@link Intent#EXTRA_LOCAL_ONLY} to limit content selection to local data.
     *
     * <p>For system stability, it is preferred to open the URIs obtained from using this action
     * by calling
     * {@link MediaStore#openFileDescriptor(ContentResolver, Uri, String, CancellationSignal)},
     * {@link MediaStore#openAssetFileDescriptor(ContentResolver, Uri, String, CancellationSignal)} or
     * {@link MediaStore#openTypedAssetFileDescriptor(ContentResolver, Uri, String, Bundle, CancellationSignal)}
     * instead of {@link ContentResolver} open APIs.
     *
     * <p>Output: MediaStore content URI(s) of the item(s) that was picked. Unlike other MediaStore
     * URIs, these are referred to as 'picker' URIs and expose a limited set of read-only
     * operations. Specifically, picker URIs can only be opened for read and queried for columns in
     * {@link PickerMediaColumns}.
     *
     * <p>Before this API, apps could use {@link Intent#ACTION_GET_CONTENT}. However, {@link
     * #ACTION_PICK_IMAGES} is now the recommended option for images and videos, since it offers a
     * better user experience.
     */
    public static final String ACTION_PICK_IMAGES = "android.provider.action.PICK_IMAGES";

    /**
     * Activity Action: Launch settings controlling images or videos selection with
     * {@link #ACTION_PICK_IMAGES}.
     *
     * The settings page allows a user to change the enabled {@link CloudMediaProvider} on the
     * device and other media selection configurations.
     *
     * @see #ACTION_PICK_IMAGES
     * @see #isCurrentCloudMediaProviderAuthority(ContentResolver, String)
     */
    public static final String ACTION_PICK_IMAGES_SETTINGS = "android.provider.action.PICK_IMAGES_SETTINGS";

    /**
     * Standard action that can be sent to review the given media file.
     * <p>
     * The launched application is expected to provide a large-scale view of the
     * given media file, while allowing the user to quickly access other
     * recently captured media files.
     * <p>
     * Input: {@link Intent#getData} is URI of the primary media item to
     * initially display.
     *
     * @see #ACTION_REVIEW_SECURE
     * @see #EXTRA_BRIGHTNESS
     */
    public static final String ACTION_REVIEW = "android.provider.action.REVIEW";

    /**
     * Standard action that can be sent to review the given media file when the
     * device is secured (e.g. with a pin, password, pattern, or face unlock).
     * The applications should be careful not to share any media with other
     * applications or Internet. The activity should use
     * {@link Activity#setShowWhenLocked} to display on top of the lock screen
     * while secured. There is no activity stack when this flag is used, so
     * launching more than one activity is strongly discouraged.
     * <p>
     * The launched application is expected to provide a large-scale view of the
     * given primary media file, while only allowing the user to quickly access
     * other media from an explicit secondary list.
     * <p>
     * Input: {@link Intent#getData} is URI of the primary media item to
     * initially display. {@link Intent#getClipData} is the limited list of
     * secondary media items that the user is allowed to review. If
     * {@link Intent#getClipData} is undefined, then no other media access
     * should be allowed.
     *
     * @see #EXTRA_BRIGHTNESS
     */
    public static final String ACTION_REVIEW_SECURE = "android.provider.action.REVIEW_SECURE";

    /**
     * Standard Intent action that can be sent to have the camera application
     * capture a video and return it.
     * <p>
     * The caller may pass in an extra EXTRA_VIDEO_QUALITY to control the video quality.
     * <p>
     * The caller may pass in an extra EXTRA_OUTPUT to control
     * where the video is written.
     * <ul>
     * <li>If EXTRA_OUTPUT is not present, the video will be written to the standard location
     * for videos, and the Uri of that location will be returned in the data field of the Uri.
     * {@link android.hardware.Camera#ACTION_NEW_VIDEO} will also be broadcasted when the video
     * is recorded.
     * <li>If EXTRA_OUTPUT is assigned a Uri value, no
     * {@link android.hardware.Camera#ACTION_NEW_VIDEO} will be broadcasted. As of
     * {@link android.os.Build.VERSION_CODES#LOLLIPOP}, this uri can also be
     * supplied through {@link android.content.Intent#setClipData(ClipData)}.  If using this
     * approach, you still must supply the uri through the EXTRA_OUTPUT field for compatibility
     * with old applications. If you don't set a ClipData, it will be copied there for you when
     * calling {@link Context#startActivity(Intent)}.
     * </ul>
     *
     * <p>Note: if you app targets {@link android.os.Build.VERSION_CODES#M M} and above
     * and declares as using the {@link android.Manifest.permission#CAMERA} permission which
     * is not granted, then atempting to use this action will result in a {@link
     * java.lang.SecurityException}.
     *
     * @see #EXTRA_OUTPUT
     * @see #EXTRA_VIDEO_QUALITY
     * @see #EXTRA_SIZE_LIMIT
     * @see #EXTRA_DURATION_LIMIT
     * @see android.hardware.Camera#ACTION_NEW_VIDEO
     */
    public static final String ACTION_VIDEO_CAPTURE = "android.media.action.VIDEO_CAPTURE";

    /**
     * The authority for the media provider
     */
    public static final String AUTHORITY = "media";

    /**
     * Specify that the caller wants to receive the original media format without transcoding.
     *
     * <b>Caution: using this flag can cause app
     * compatibility issues whenever Android adds support for new media formats.</b>
     * Clients should instead specify their supported media capabilities explicitly
     * in their manifest or with the {@link #EXTRA_MEDIA_CAPABILITIES} {@code open} flag.
     *
     * This option is useful for apps that don't attempt to parse the actual byte contents of media
     * files, such as playback using {@link MediaPlayer} or for off-device backup. Note that the
     * {@link android.Manifest.permission#ACCESS_MEDIA_LOCATION} permission will still be required
     * to avoid sensitive metadata redaction, similar to {@link #setRequireOriginal(Uri)}.
     * </ul>
     *
     * Note that this flag overrides any explicitly declared {@code media_capabilities.xml} or
     * {@link ApplicationMediaCapabilities} extras specified in the same {@code open} request.
     *
     * <p>This option can be added to the {@code opts} {@link Bundle} in various
     * {@link ContentResolver} {@code open} methods.
     *
     * @see ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)
     * @see ContentResolver#openTypedAssetFile(Uri, String, Bundle, CancellationSignal)
     * @see #setRequireOriginal(Uri)
     * @see MediaStore#getOriginalMediaFormatFileDescriptor(Context, ParcelFileDescriptor)
     */
    public static final String EXTRA_ACCEPT_ORIGINAL_MEDIA_FORMAT = "android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT";

    /**
     * When defined, the launched application is requested to set the given
     * brightness value via
     * {@link android.view.WindowManager.LayoutParams#screenBrightness} to help
     * ensure a smooth transition when launching {@link #ACTION_REVIEW} or
     * {@link #ACTION_REVIEW_SECURE} intents.
     */
    public static final String EXTRA_BRIGHTNESS = "android.provider.extra.BRIGHTNESS";

    /**
     * Specify the maximum allowed recording duration in seconds.
     */
    public static final String EXTRA_DURATION_LIMIT = "android.intent.extra.durationLimit";

    /**
     * The name of the Intent-extra used to control the onCompletion behavior of a MovieView. This
     * is a boolean property that specifies whether or not to finish the MovieView activity when the
     * movie completes playing. The default value is true, which means to automatically exit the
     * movie player activity when the movie completes playing.
     */
    public static final String EXTRA_FINISH_ON_COMPLETION = "android.intent.extra.finishOnCompletion";

    /**
     * The name of an Intent-extra used to control the UI of a ViewImage.
     * This is a boolean property that overrides the activity's default fullscreen state.
     */
    public static final String EXTRA_FULL_SCREEN = "android.intent.extra.fullScreen";

    /**
     * The name of the Intent-extra used to define the album
     */
    public static final String EXTRA_MEDIA_ALBUM = "android.intent.extra.album";

    /**
     * The name of the Intent-extra used to define the artist
     */
    public static final String EXTRA_MEDIA_ARTIST = "android.intent.extra.artist";

    /**
     * Specify the {@link ApplicationMediaCapabilities} that should be used while opening a media
     * or picking media files.
     *
     * <p>If the capabilities specified matches the format of the original file, the app will
     * receive the original file, otherwise, it will get transcoded to a default supported format.
     *
     * <p>When used while opening a media, add this option to the {@code opts} {@link Bundle} in
     * various {@link ContentResolver} {@code open} methods. This flag takes higher precedence over
     * the applications declared {@code media_capabilities.xml} and is useful for apps that want to
     * have more granular control over their supported media capabilities.
     *
     * <p>When used while picking media files, add this option to the intent-extra of
     * {@link MediaStore#ACTION_PICK_IMAGES}.
     *
     * @see ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)
     * @see ContentResolver#openTypedAssetFile(Uri, String, Bundle, CancellationSignal)
     * @see MediaStore#ACTION_PICK_IMAGES
     */
    public static final String EXTRA_MEDIA_CAPABILITIES = "android.provider.extra.MEDIA_CAPABILITIES";

    /**
     * Specify the UID of the app that should be used to determine supported media capabilities
     * while opening a media.
     *
     * If this specified UID is found to be capable of handling the original media file format, the
     * app will receive the original file, otherwise, the file will get transcoded to a default
     * format supported by the specified UID.
     */
    public static final String EXTRA_MEDIA_CAPABILITIES_UID = "android.provider.extra.MEDIA_CAPABILITIES_UID";

    /**
     * The name of the Intent-extra used to define the search focus. The search focus
     * indicates whether the search should be for things related to the artist, album
     * or song that is identified by the other extras.
     */
    public static final String EXTRA_MEDIA_FOCUS = "android.intent.extra.focus";

    /**
     * The name of the Intent-extra used to define the genre.
     */
    public static final String EXTRA_MEDIA_GENRE = "android.intent.extra.genre";

    /**
     * The name of the Intent-extra used to define the playlist.
     *
     * @deprecated Android playlists are now deprecated. We will keep the current
     *             functionality for compatibility resons, but we will no longer take feature
     *             request. We do not advise adding new usages of Android Playlists. M3U files can
     *             be used as an alternative.
     */
    @Deprecated
    public static final String EXTRA_MEDIA_PLAYLIST = "android.intent.extra.playlist";

    /**
     * The name of the Intent-extra used to define the radio channel.
     */
    public static final String EXTRA_MEDIA_RADIO_CHANNEL = "android.intent.extra.radio_channel";

    /**
     * The name of the Intent-extra used to define the song title
     */
    public static final String EXTRA_MEDIA_TITLE = "android.intent.extra.title";

    /**
     * The name of the Intent-extra used to indicate a content resolver Uri to be used to
     * store the requested image or video.
     */
    public static final String EXTRA_OUTPUT = "output";

    /**
     * The name of an optional intent-extra used to specify URIs for pre-selection in photo picker
     * opened with {@link MediaStore#ACTION_PICK_IMAGES} in multi-select mode.
     *
     * <p>Only MediaStore content URI(s) of the item(s) received as a result of
     * {@link MediaStore#ACTION_PICK_IMAGES} action are accepted. The value of this intent-extra
     * should be an ArrayList of type URIs. Default value is null. Maximum number of URIs
     * that can be accepted is limited by the value passed in
     * {@link MediaStore#EXTRA_PICK_IMAGES_MAX} as part of the {@link MediaStore#ACTION_PICK_IMAGES}
     * intent. In case the count of input URIs is greater than the limit then
     * {@code IllegalArgumentException} is thrown.</p>
     *
     * <p>The provided list will be checked for permissions and authority. Any URI that is
     * inaccessible, doesn't match the current authorities(local or cloud) or is invalid will be
     * filtered out.</p>
     *
     * <p>The items corresponding to the URIs will appear selected when the photo picker is opened.
     * In the case of {@link MediaStore#EXTRA_PICK_IMAGES_IN_ORDER} the chronological order of the
     * input list will be used for ordered selection of the pre-selected items.</p>
     *
     * <p>This is not a mechanism to revoke permissions for items, i.e. de-selection of a
     * pre-selected item by the user will not result in revocation of the grant.</p>
     */
    public static final String EXTRA_PICKER_PRE_SELECTION_URIS = "android.provider.extra.PICKER_PRE_SELECTION_URIS";

    /**
     * The name of an optional intent-extra used to allow apps to specify the picker accent color.
     * The extra can only be specified in {@link MediaStore#ACTION_PICK_IMAGES}.
     * The accent color will be used for various primary elements in the PhotoPicker view.
     * All other colors will be set based on android material guidelines.
     * <p>
     * The value of this intent extra should be a long color value. The alpha component of the
     * given color is not taken into account while setting the accent color. We assume full color
     * opacity.
     * Only colors with luminance(can also be understood as brightness) greater than 0.05 and
     * less than 0.9 are permitted.
     * Luminance of a color is determined using:
     * luminance = Color.luminance(color)
     *       where color is the input accent color to be set.
     * Check {@link Color} docs for more details on color luminance and long color values.
     * In case the luminance of the input color is unacceptable, picker colors will be set
     * based on the colors of the device android theme.
     * In case of an invalid input color value i.e. the input color cannot be parsed,
     * {@code IllegalArgumentException} is thrown.
     */
    public static final String EXTRA_PICK_IMAGES_ACCENT_COLOR = "android.provider.extra.PICK_IMAGES_ACCENT_COLOR";

    /**
     * The name of an optional intent-extra used to allow ordered selection of items. Set this extra
     * to true to allow the user to see the order of their selected items. The result returned to
     * the caller will be the same as the user selected order. This extra is only allowed via the
     * {@link MediaStore#ACTION_PICK_IMAGES}.
     *
     * <p>The value of this intent-extra should be a boolean. Default value is false.
     *
     * @see #ACTION_PICK_IMAGES
     */
    public static final String EXTRA_PICK_IMAGES_IN_ORDER = "android.provider.extra.PICK_IMAGES_IN_ORDER";

    /**
     * The name of an optional intent-extra used to allow apps to specify the tab the picker should
     * open with. The extra can only be specified in {@link MediaStore#ACTION_PICK_IMAGES}.
     * <p>
     * The value of this intent-extra must be one of: {@link MediaStore#PICK_IMAGES_TAB_ALBUMS}
     * for the albums tab and {@link MediaStore#PICK_IMAGES_TAB_IMAGES} for the photos tab.
     * The system will decide which tab to open by default and in most cases,
     * it is {@link MediaStore#PICK_IMAGES_TAB_IMAGES} i.e. the photos tab.
     */
    public static final String EXTRA_PICK_IMAGES_LAUNCH_TAB = "android.provider.extra.PICK_IMAGES_LAUNCH_TAB";

    /**
     * The name of an optional intent-extra used to allow multiple selection of
     * items and constrain maximum number of items that can be returned by
     * {@link MediaStore#ACTION_PICK_IMAGES}, action may still return nothing
     * (0 items) if the user chooses to cancel.
     * <p>
     * The value of this intent-extra should be a positive integer greater
     * than 1 and less than or equal to
     * {@link MediaStore#getPickImagesMaxLimit}, otherwise
     * {@link Activity#RESULT_CANCELED} is returned.
     */
    public static final String EXTRA_PICK_IMAGES_MAX = "android.provider.extra.PICK_IMAGES_MAX";

    /**
     * The name of the Intent-extra used to control the orientation of a ViewImage or a MovieView.
     * This is an int property that overrides the activity's requestedOrientation.
     * @see android.content.pm.ActivityInfo#SCREEN_ORIENTATION_UNSPECIFIED
     */
    public static final String EXTRA_SCREEN_ORIENTATION = "android.intent.extra.screenOrientation";

    /**
     * The name of an Intent-extra used to control the UI of a ViewImage.
     * This is a boolean property that specifies whether or not to show action icons.
     */
    public static final String EXTRA_SHOW_ACTION_ICONS = "android.intent.extra.showActionIcons";

    /**
     * Specify the maximum allowed size.
     */
    public static final String EXTRA_SIZE_LIMIT = "android.intent.extra.sizeLimit";

    /**
     * The name of the Intent-extra used to control the quality of a recorded video. This is an
     * integer property. Currently value 0 means low quality, suitable for MMS messages, and
     * value 1 means high quality. In the future other quality levels may be added.
     */
    public static final String EXTRA_VIDEO_QUALITY = "android.intent.extra.videoQuality";

    /**
     * An intent to perform a search for music media and automatically play content from the
     * result when possible. This can be fired, for example, by the result of a voice recognition
     * command to listen to music.
     * <p>This intent always includes the {@link android.provider.MediaStore#EXTRA_MEDIA_FOCUS}
     * and {@link android.app.SearchManager#QUERY} extras. The
     * {@link android.provider.MediaStore#EXTRA_MEDIA_FOCUS} extra determines the search mode, and
     * the value of the {@link android.app.SearchManager#QUERY} extra depends on the search mode.
     * For more information about the search modes for this intent, see
     * <a href="{@docRoot}guide/components/intents-common.html#PlaySearch">Play music based
     * on a search query</a> in <a href="{@docRoot}guide/components/intents-common.html">Common
     * Intents</a>.</p>
     *
     * <p>This intent makes the most sense for apps that can support large-scale search of music,
     * such as services connected to an online database of music which can be streamed and played
     * on the device.</p>
     */
    public static final String INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH = "android.media.action.MEDIA_PLAY_FROM_SEARCH";

    /**
     * Activity Action: Perform a search for media.
     * Contains at least the {@link android.app.SearchManager#QUERY} extra.
     * May also contain any combination of the following extras:
     * EXTRA_MEDIA_ARTIST, EXTRA_MEDIA_ALBUM, EXTRA_MEDIA_TITLE, EXTRA_MEDIA_FOCUS
     *
     * @see android.provider.MediaStore#EXTRA_MEDIA_ARTIST
     * @see android.provider.MediaStore#EXTRA_MEDIA_ALBUM
     * @see android.provider.MediaStore#EXTRA_MEDIA_TITLE
     * @see android.provider.MediaStore#EXTRA_MEDIA_FOCUS
     */
    public static final String INTENT_ACTION_MEDIA_SEARCH = "android.intent.action.MEDIA_SEARCH";

    /**
     * Activity Action: Launch a music player.
     * The activity should be able to play, browse, or manipulate music files stored on the device.
     *
     * @deprecated Use {@link android.content.Intent#CATEGORY_APP_MUSIC} instead.
     */
    @Deprecated
    public static final String INTENT_ACTION_MUSIC_PLAYER = "android.intent.action.MUSIC_PLAYER";

    /**
     * The name of the Intent action used to launch a camera in still image mode.
     */
    public static final String INTENT_ACTION_STILL_IMAGE_CAMERA = "android.media.action.STILL_IMAGE_CAMERA";

    /**
     * The name of the Intent action used to launch a camera in still image mode
     * for use when the device is secured (e.g. with a pin, password, pattern,
     * or face unlock). Applications responding to this intent must not expose
     * any personal content like existing photos or videos on the device. The
     * applications should be careful not to share any photo or video with other
     * applications or internet. The activity should use {@link
     * Activity#setShowWhenLocked} to display
     * on top of the lock screen while secured. There is no activity stack when
     * this flag is used, so launching more than one activity is strongly
     * discouraged.
     */
    public static final String INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE = "android.media.action.STILL_IMAGE_CAMERA_SECURE";

    /**
     * An intent to perform a search for readable media and automatically play content from the
     * result when possible. This can be fired, for example, by the result of a voice recognition
     * command to read a book or magazine.
     * <p>
     * Contains the {@link android.app.SearchManager#QUERY} extra, which is a string that can
     * contain any type of unstructured text search, like the name of a book or magazine, an author
     * a genre, a publisher, or any combination of these.
     * <p>
     * Because this intent includes an open-ended unstructured search string, it makes the most
     * sense for apps that can support large-scale search of text media, such as services connected
     * to an online database of books and/or magazines which can be read on the device.
     */
    public static final String INTENT_ACTION_TEXT_OPEN_FROM_SEARCH = "android.media.action.TEXT_OPEN_FROM_SEARCH";

    /**
     * The name of the Intent action used to launch a camera in video mode.
     */
    public static final String INTENT_ACTION_VIDEO_CAMERA = "android.media.action.VIDEO_CAMERA";

    /**
     * An intent to perform a search for video media and automatically play content from the
     * result when possible. This can be fired, for example, by the result of a voice recognition
     * command to play movies.
     * <p>
     * Contains the {@link android.app.SearchManager#QUERY} extra, which is a string that can
     * contain any type of unstructured video search, like the name of a movie, one or more actors,
     * a genre, or any combination of these.
     * <p>
     * Because this intent includes an open-ended unstructured search string, it makes the most
     * sense for apps that can support large-scale search of video, such as services connected to an
     * online database of videos which can be streamed and played on the device.
     */
    public static final String INTENT_ACTION_VIDEO_PLAY_FROM_SEARCH = "android.media.action.VIDEO_PLAY_FROM_SEARCH";

    /**
     * Value indicating that the default matching behavior should be used, as
     * defined by the key documentation.
     */
    public static final int MATCH_DEFAULT = 0;

    /**
     * Value indicating that operations should exclude items matching the
     * criteria defined by this key.
     */
    public static final int MATCH_EXCLUDE = 2;

    /**
     * Value indicating that operations should include items matching the
     * criteria defined by this key.
     * <p>
     * Note that items <em>not</em> matching the criteria <em>may</em> also be
     * included depending on the default behavior documented by the key. If you
     * want to operate exclusively on matching items, use {@link #MATCH_ONLY}.
     */
    public static final int MATCH_INCLUDE = 1;

    /**
     * Value indicating that operations should only operate on items explicitly
     * matching the criteria defined by this key.
     */
    public static final int MATCH_ONLY = 3;

    /**
     * Name of the file signaling the media scanner to ignore media in the containing directory
     * and its subdirectories. Developers should use this to avoid application graphics showing
     * up in the Gallery and likewise prevent application sounds and music from showing up in
     * the Music app.
     */
    public static final String MEDIA_IGNORE_FILENAME = ".nomedia";

    /**
     * Name of current volume being scanned by the media scanner.
     */
    public static final String MEDIA_SCANNER_VOLUME = "volume";

    /**
     * Name under which an activity handling {@link #ACTION_REVIEW} or
     * {@link #ACTION_REVIEW_SECURE} publishes the service name for its prewarm
     * service.
     * <p>
     * This meta-data should reference the fully qualified class name of the prewarm service
     * <p>
     * The prewarm service can be bound before starting {@link #ACTION_REVIEW} or
     * {@link #ACTION_REVIEW_SECURE}.
     * An application implementing this prewarm service should do the absolute minimum amount of
     * work to initialize its resources to efficiently handle an {@link #ACTION_REVIEW} or
     * {@link #ACTION_REVIEW_SECURE} in the near future.
     */
    public static final String META_DATA_REVIEW_GALLERY_PREWARM_SERVICE = "android.media.review_gallery_prewarm_service";

    /**
     * Name under which an activity handling {@link #INTENT_ACTION_STILL_IMAGE_CAMERA} or
     * {@link #INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE} publishes the service name for its prewarm
     * service.
     * <p>
     * This meta-data should reference the fully qualified class name of the prewarm service
     * extending {@code CameraPrewarmService}.
     * <p>
     * The prewarm service will get bound and receive a prewarm signal
     * {@code CameraPrewarmService#onPrewarm()} when a camera launch intent fire might be imminent.
     * An application implementing a prewarm service should do the absolute minimum amount of work
     * to initialize the camera in order to reduce startup time in likely case that shortly after a
     * camera launch intent would be sent.
     */
    public static final String META_DATA_STILL_IMAGE_CAMERA_PREWARM_SERVICE = "android.media.still_image_camera_preview_service";

    /**
     * One of the permitted values for {@link MediaStore#EXTRA_PICK_IMAGES_LAUNCH_TAB} to open the
     * picker with albums tab.
     */
    public static final int PICK_IMAGES_TAB_ALBUMS = 0;

    /**
     * One of the permitted values for {@link MediaStore#EXTRA_PICK_IMAGES_LAUNCH_TAB} to open the
     * picker with photos tab.
     */
    public static final int PICK_IMAGES_TAB_IMAGES = 1;

    /**
     * Flag that requests {@link ContentResolver#query} to include content from
     * recently unmounted volumes.
     * <p>
     * When the flag is set, {@link ContentResolver#query} will return content
     * from all volumes(i.e., both mounted and recently unmounted volume whose
     * content is still held by MediaProvider).
     * <p>
     * Note that the query result doesn't provide any hint for content from
     * unmounted volume. It's strongly recommended to use default query to
     * avoid accessing/operating on the content that are not available on the
     * device.
     * <p>
     * The flag is useful for apps which manage their own database and
     * query MediaStore in order to synchronize between MediaStore database
     * and their own database.
     */
    public static final String QUERY_ARG_INCLUDE_RECENTLY_UNMOUNTED_VOLUMES = "android:query-arg-recently-unmounted-volumes";

    /**
     * Flag that indicates if only the latest selection in the photoPicker for
     * the calling app should be returned. If set to true, all items that were
     * granted to the calling app in the last selection are returned.
     *
     * <p>Selection in this scenario refers to when the user selects items in
     * <b> the permission prompt photo picker</b>. The access for these items
     * is granted to the calling app and these grants are persisted unless the
     * user deselects a granted item explicitly.</p>
     *
     * <p>The result excludes items owned by the calling app unless they are
     * explicitly selected by the user.</p>
     *
     * <p>Note: If there has been no user selections after the introduction of
     * this feature then all the granted items will be returned.</p>
     *
     * <p>This key can be placed in a {@link Bundle} of extras and passed to
     * {@link ContentResolver#query}.</p>
     *
     * @see android.Manifest.permission#READ_MEDIA_VISUAL_USER_SELECTED
     */
    public static final String QUERY_ARG_LATEST_SELECTION_ONLY = "android:query-arg-latest-selection-only";

    /**
     * Specify how {@link MediaColumns#IS_FAVORITE} items should be filtered
     * when performing a {@link MediaStore} operation.
     * <p>
     * This key can be placed in a {@link Bundle} of extras and passed to
     * {@link ContentResolver#query}, {@link ContentResolver#update}, or
     * {@link ContentResolver#delete}.
     * <p>
     * By default, favorite items are <em>not</em> filtered away from
     * operations.
     *
     * @see MediaColumns#IS_FAVORITE
     * @see MediaStore#QUERY_ARG_MATCH_FAVORITE
     * @see MediaStore#createFavoriteRequest
     */
    public static final String QUERY_ARG_MATCH_FAVORITE = "android:query-arg-match-favorite";

    /**
     * Specify how {@link MediaColumns#IS_PENDING} items should be filtered when
     * performing a {@link MediaStore} operation.
     * <p>
     * This key can be placed in a {@link Bundle} of extras and passed to
     * {@link ContentResolver#query}, {@link ContentResolver#update}, or
     * {@link ContentResolver#delete}.
     * <p>
     * By default, pending items are filtered away from operations.
     */
    public static final String QUERY_ARG_MATCH_PENDING = "android:query-arg-match-pending";

    /**
     * Specify how {@link MediaColumns#IS_TRASHED} items should be filtered when
     * performing a {@link MediaStore} operation.
     * <p>
     * This key can be placed in a {@link Bundle} of extras and passed to
     * {@link ContentResolver#query}, {@link ContentResolver#update}, or
     * {@link ContentResolver#delete}.
     * <p>
     * By default, trashed items are filtered away from operations.
     *
     * @see MediaColumns#IS_TRASHED
     * @see MediaStore#QUERY_ARG_MATCH_TRASHED
     * @see MediaStore#createTrashRequest
     */
    public static final String QUERY_ARG_MATCH_TRASHED = "android:query-arg-match-trashed";

    /**
     * Flag that requests {@link ContentResolver#query} to sort the result in descending order
     * based on {@link MediaColumns#INFERRED_DATE}.
     * <p>
     * When this flag is used as an extra in a {@link Bundle} passed to
     * {@link ContentResolver#query}, all other sorting options such as
     * {@link android.content.ContentResolver#QUERY_ARG_SORT_COLUMNS} or
     * {@link android.content.ContentResolver#QUERY_ARG_SQL_SORT_ORDER} are disregarded.
     */
    public static final String QUERY_ARG_MEDIA_STANDARD_SORT_ORDER = "android:query-arg-media-standard-sort-order";

    /**
     * Specify a {@link Uri} that is "related" to the current operation being
     * performed.
     * <p>
     * This is typically used to allow an operation that may normally be
     * rejected, such as making a copy of a pre-existing image located under a
     * {@link MediaColumns#RELATIVE_PATH} where new images are not allowed.
     * <p>
     * It's strongly recommended that when making a copy of pre-existing content
     * that you define the "original document ID" GUID as defined by the <em>XMP
     * Media Management</em> standard.
     * <p>
     * This key can be placed in a {@link Bundle} of extras and passed to
     * {@link ContentResolver#insert}.
     */
    public static final String QUERY_ARG_RELATED_URI = "android:query-arg-related-uri";

    /**
     * The string that is used when a media attribute is not known. For example,
     * if an audio file does not have any meta data, the artist and album columns
     * will be set to this value.
     */
    public static final String UNKNOWN_STRING = "<unknown>";

    /**
     * Synthetic volume name that provides a view of all content across the
     * "external" storage of the device.
     * <p>
     * This synthetic volume provides a merged view of all media across all
     * currently attached external storage devices.
     * <p>
     * Because this is a synthetic volume, you can't insert new content into
     * this volume. Instead, you can insert content into a specific storage
     * volume obtained from {@link #getExternalVolumeNames(Context)}.
     */
    public static final String VOLUME_EXTERNAL = "external";

    /**
     * Specific volume name that represents the primary external storage device
     * at {@link Environment#getExternalStorageDirectory()}.
     * <p>
     * This volume may not always be available, such as when the user has
     * ejected the device. You can find a list of all specific volume names
     * using {@link #getExternalVolumeNames(Context)}.
     */
    public static final String VOLUME_EXTERNAL_PRIMARY = "external_primary";

    /**
     * Synthetic volume name that provides a view of all content across the
     * "internal" storage of the device.
     * <p>
     * This synthetic volume provides a merged view of all media distributed
     * with the device, such as built-in ringtones and wallpapers.
     * <p>
     * Because this is a synthetic volume, you can't insert new content into
     * this volume.
     */
    public static final String VOLUME_INTERNAL = "internal";

    private MediaStore() {
    }

    /**
     * Collection of all media with MIME type of {@code audio/*}.
     */
    public static final class Audio {
        private Audio() {
        }

        /**
         * Audio album metadata columns.
         */
        public interface AlbumColumns {
            /**
             * The album on which the audio file appears, if any
             */
            String ALBUM = "album";

            /**
             * Cached album art.
             *
             * @deprecated Apps may not have filesystem permissions to directly
             *             access this path. Instead of trying to open this path
             *             directly, apps should use
             *             {@link ContentResolver#loadThumbnail}
             *             to gain access.
             */
            @Deprecated
            String ALBUM_ART = "album_art";

            /**
             * The id for the album
             */
            String ALBUM_ID = "album_id";

            /**
             * A non human readable key calculated from the ALBUM, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String ALBUM_KEY = "album_key";

            /**
             * The name of the artist whose songs appear on this album.
             */
            String ARTIST = "artist";

            /**
             * The ID of the artist whose songs appear on this album.
             */
            String ARTIST_ID = "artist_id";

            /**
             * A non human readable key calculated from the ARTIST, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String ARTIST_KEY = "artist_key";

            /**
             * The year in which the earliest songs
             * on this album were released. This will often
             * be the same as {@link #LAST_YEAR}, but for compilation albums
             * they might differ.
             */
            String FIRST_YEAR = "minyear";

            /**
             * The year in which the latest songs
             * on this album were released. This will often
             * be the same as {@link #FIRST_YEAR}, but for compilation albums
             * they might differ.
             */
            String LAST_YEAR = "maxyear";

            /**
             * The number of songs on this album
             */
            String NUMBER_OF_SONGS = "numsongs";

            /**
             * This column is available when getting album info via artist,
             * and indicates the number of songs on the album by the given
             * artist.
             */
            String NUMBER_OF_SONGS_FOR_ARTIST = "numsongs_by_artist";
        }

        /**
         * Contains artists for audio files
         */
        public static final class Albums implements BaseColumns, AlbumColumns {
            /**
             * The MIME type for this table.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/albums";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "album_key";

            /**
             * The MIME type for entries in this table.
             */
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/album";

            private Albums() {
            }
        }

        /**
         * Audio artist metadata columns.
         */
        public interface ArtistColumns {
            /**
             * The artist who created the audio file, if any
             */
            String ARTIST = "artist";

            /**
             * A non human readable key calculated from the ARTIST, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String ARTIST_KEY = "artist_key";

            /**
             * The number of albums in the database for this artist
             */
            String NUMBER_OF_ALBUMS = "number_of_albums";

            /**
             * The number of albums in the database for this artist
             */
            String NUMBER_OF_TRACKS = "number_of_tracks";
        }

        /**
         * Contains artists for audio files
         */
        public static final class Artists implements BaseColumns, ArtistColumns {
            /**
             * The MIME type for this table.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/artists";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "artist_key";

            /**
             * The MIME type for entries in this table.
             */
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/artist";

            private Artists() {
            }
        }

        /**
         * Audio metadata columns.
         */
        public interface AudioColumns extends MediaColumns {
            /**
             * The id of the album the audio file is from, if any
             */
            String ALBUM_ID = "album_id";

            /**
             * A non human readable key calculated from the ALBUM, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String ALBUM_KEY = "album_key";

            /**
             * The id of the artist who created the audio file, if any
             */
            String ARTIST_ID = "artist_id";

            /**
             * A non human readable key calculated from the ARTIST, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String ARTIST_KEY = "artist_key";

            /**
             * The number of bits used to represent each audio sample, if available.
             */
            String BITS_PER_SAMPLE = "bits_per_sample";

            /**
             * The position within the audio item at which playback should be
             * resumed.
             */
            String BOOKMARK = "bookmark";

            /**
             * The genre of the audio file, if any.
             */
            String GENRE = "genre";

            /**
             * The id of the genre the audio file is from, if any
             */
            String GENRE_ID = "genre_id";

            /**
             * A non human readable key calculated from the GENRE, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String GENRE_KEY = "genre_key";

            /**
             * Non-zero if the audio file may be an alarm
             */
            String IS_ALARM = "is_alarm";

            /**
             * Non-zero if the audio file is an audiobook
             */
            String IS_AUDIOBOOK = "is_audiobook";

            /**
             * Non-zero if the audio file is music
             */
            String IS_MUSIC = "is_music";

            /**
             * Non-zero if the audio file may be a notification sound
             */
            String IS_NOTIFICATION = "is_notification";

            /**
             * Non-zero if the audio file is a podcast
             */
            String IS_PODCAST = "is_podcast";

            /**
             * Non-zero if the audio file is a voice recording recorded
             * by voice recorder apps
             */
            String IS_RECORDING = "is_recording";

            /**
             * Non-zero if the audio file may be a ringtone
             */
            String IS_RINGTONE = "is_ringtone";

            /**
             * The sample rate in Hz, if available.
             */
            String SAMPLERATE = "samplerate";

            /**
             * A non human readable key calculated from the TITLE, used for
             * searching, sorting and grouping
             *
             * @see Audio#keyFor(String)
             * @deprecated These keys are generated using
             *             {@link java.util.Locale#ROOT}, which means they don't
             *             reflect locale-specific sorting preferences. To apply
             *             locale-specific sorting preferences, use
             *             {@link ContentResolver#QUERY_ARG_SQL_SORT_ORDER} with
             *             {@code COLLATE LOCALIZED}, or
             *             {@link ContentResolver#QUERY_ARG_SORT_LOCALE}.
             */
            @Deprecated
            String TITLE_KEY = "title_key";

            /**
             * The resource URI of a localized title, if any.
             * <p>
             * Conforms to this pattern:
             * <ul>
             * <li>Scheme: {@link ContentResolver#SCHEME_ANDROID_RESOURCE}
             * <li>Authority: Package Name of ringtone title provider
             * <li>First Path Segment: Type of resource (must be "string")
             * <li>Second Path Segment: Resource ID of title
             * </ul>
             */
            String TITLE_RESOURCE_URI = "title_resource_uri";

            /**
             * The track number of this song on the album, if any.
             * This number encodes both the track number and the
             * disc number. For multi-disc sets, this number will
             * be 1xxx for tracks on the first disc, 2xxx for tracks
             * on the second disc, etc.
             */
            String TRACK = "track";

            /**
             * The year the audio file was recorded, if any
             */
            String YEAR = "year";
        }

        /**
         * Contains all genres for audio files
         */
        public static final class Genres implements BaseColumns, GenresColumns {
            /**
             * The MIME type for this table.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/genre";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "name";

            /**
             * The MIME type for entries in this table.
             */
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/genre";

            private Genres() {
            }

            /**
             * Sub-directory of each genre containing all members.
             */
            public static final class Members implements AudioColumns {
                /**
                 * The ID of the audio file
                 */
                public static final String AUDIO_ID = "audio_id";

                /**
                 * A subdirectory of each genre containing all member audio files.
                 */
                public static final String CONTENT_DIRECTORY = "members";

                /**
                 * The default sort order for this table
                 */
                public static final String DEFAULT_SORT_ORDER = "title_key";

                /**
                 * The ID of the genre
                 */
                public static final String GENRE_ID = "genre_id";

                private Members() {
                }
            }
        }

        /**
         * Audio genre metadata columns.
         */
        public interface GenresColumns {
            /**
             * The name of the genre
             */
            String NAME = "name";
        }

        public static final class Media implements AudioColumns {
            /**
             * The MIME type for this table.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/audio";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "title_key";

            /**
             * The MIME type for an audio track.
             */
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/audio";

            /**
             * The name of the Intent-extra used to define a maximum file size for
             * a recording made by the SoundRecorder application.
             *
             * @see #RECORD_SOUND_ACTION
             */
            public static final String EXTRA_MAX_BYTES = "android.provider.MediaStore.extra.MAX_BYTES";

            /**
             * Activity Action: Start SoundRecorder application.
             * <p>Input: nothing.
             * <p>Output: An uri to the recorded sound stored in the Media Library
             * if the recording was successful.
             * May also contain the extra EXTRA_MAX_BYTES.
             * @see #EXTRA_MAX_BYTES
             */
            public static final String RECORD_SOUND_ACTION = "android.provider.MediaStore.RECORD_SOUND";

            private Media() {
            }
        }

        /**
         * Contains playlists for audio files
         *
         * @deprecated Android playlists are now deprecated. We will keep the current
         *             functionality for compatibility resons, but we will no longer take
         *             feature request. We do not advise adding new usages of Android Playlists.
         *             M3U files can be used as an alternative.
         */
        @Deprecated
        public static final class Playlists implements BaseColumns, PlaylistsColumns {
            /**
             * The MIME type for this table.
             */
            @Deprecated
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/playlist";

            /**
             * The default sort order for this table
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "name";

            /**
             * The MIME type for entries in this table.
             */
            @Deprecated
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/playlist";

            private Playlists() {
            }

            /**
             * Sub-directory of each playlist containing all members.
             */
            @Deprecated
            public static final class Members implements AudioColumns {
                /**
                 * The ID of the audio file
                 */
                @Deprecated
                public static final String AUDIO_ID = "audio_id";

                /**
                 * A subdirectory of each playlist containing all member audio
                 * files.
                 */
                @Deprecated
                public static final String CONTENT_DIRECTORY = "members";

                /**
                 * The default sort order for this table
                 */
                @Deprecated
                public static final String DEFAULT_SORT_ORDER = "play_order";

                /**
                 * The ID of the playlist
                 */
                @Deprecated
                public static final String PLAYLIST_ID = "playlist_id";

                /**
                 * The order of the songs in the playlist
                 */
                @Deprecated
                public static final String PLAY_ORDER = "play_order";

                /**
                 * The ID within the playlist.
                 */
                @Deprecated
                public static final String _ID = "_id";

                private Members() {
                }
            }
        }

        /**
         * Audio playlist metadata columns.
         *
         * @deprecated Android playlists are now deprecated. We will keep the current
         *             functionality for compatibility reasons, but we will no longer take
         *             feature request. We do not advise adding new usages of Android Playlists.
         *             M3U files can be used as an alternative.
         */
        @Deprecated
        public interface PlaylistsColumns extends MediaColumns {
            /**
             * Path to the playlist file on disk.
             */
            @Deprecated
            String DATA = "_data";

            /**
             * The time the media item was first added.
             */
            @Deprecated
            String DATE_ADDED = "date_added";

            /**
             * The time the media item was last modified.
             */
            @Deprecated
            String DATE_MODIFIED = "date_modified";

            /**
             * The name of the playlist
             */
            @Deprecated
            String NAME = "name";
        }

        public static final class Radio {
            /**
             * The MIME type for entries in this table.
             */
            public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/radio";

            private Radio() {
            }
        }
    }

    /**
     * Download metadata columns.
     */
    public interface DownloadColumns extends MediaColumns {
        /**
         * Uri indicating where the item has been downloaded from.
         */
        String DOWNLOAD_URI = "download_uri";

        /**
         * Uri indicating HTTP referer of {@link #DOWNLOAD_URI}.
         */
        String REFERER_URI = "referer_uri";
    }

    /**
     * Collection of downloaded items.
     */
    public static final class Downloads implements DownloadColumns {
        /**
         * The MIME type for this table.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/download";

        private Downloads() {
        }
    }

    /**
     * Media provider table containing an index of all files in the media storage,
     * including non-media files.  This should be used by applications that work with
     * non-media file types (text, HTML, PDF, etc) as well as applications that need to
     * work with multiple media file types in a single query.
     */
    public static final class Files {
        private Files() {
        }

        /**
         * File metadata columns.
         */
        public interface FileColumns extends MediaColumns {
            /**
             * The media type (audio, video, image, document, playlist or subtitle)
             * of the file, or 0 for not a media file
             */
            String MEDIA_TYPE = "media_type";

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is an audio file.
             */
            int MEDIA_TYPE_AUDIO = 2;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file is a document file.
             */
            int MEDIA_TYPE_DOCUMENT = 6;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is an image file.
             */
            int MEDIA_TYPE_IMAGE = 1;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is not an audio, image, video, document, playlist, or subtitles file.
             */
            int MEDIA_TYPE_NONE = 0;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is a playlist file.
             *
             * @deprecated Android playlists are now deprecated. We will keep the current
             *             functionality for compatibility reasons, but we will no longer take
             *             feature request. We do not advise adding new usages of Android Playlists.
             *             M3U files can be used as an alternative.
             */
            @Deprecated
            int MEDIA_TYPE_PLAYLIST = 4;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is a subtitles or lyrics file.
             */
            int MEDIA_TYPE_SUBTITLE = 5;

            /**
             * Constant for the {@link #MEDIA_TYPE} column indicating that file
             * is a video file.
             */
            int MEDIA_TYPE_VIDEO = 3;

            /**
             * The MIME type of the media item.
             * <p>
             * This is typically defined based on the file extension of the media
             * item. However, it may be the value of the {@code format} attribute
             * defined by the <em>Dublin Core Media Initiative</em> standard,
             * extracted from any XMP metadata contained within this media item.
             * <p class="note">
             * Note: the {@code format} attribute may be ignored if the top-level
             * MIME type disagrees with the file extension. For example, it's
             * reasonable for an {@code image/jpeg} file to declare a {@code format}
             * of {@code image/vnd.google.panorama360+jpg}, but declaring a
             * {@code format} of {@code audio/ogg} would be ignored.
             * <p>
             * This is a read-only column that is automatically computed.
             */
            String MIME_TYPE = "mime_type";

            /**
             * The index of the parent directory of the file
             */
            String PARENT = "parent";
        }
    }

    /**
     * Collection of all media with MIME type of {@code image/*}.
     */
    public static final class Images {
        private Images() {
        }

        /**
         * Image metadata columns.
         */
        public interface ImageColumns extends MediaColumns {
            /**
             * Indexed value of {@link ExifInterface#TAG_IMAGE_DESCRIPTION}
             * extracted from this media item.
             */
            String DESCRIPTION = "description";

            /**
             * Indexed value of {@link ExifInterface#TAG_EXPOSURE_TIME}
             * extracted from this media item.
             */
            String EXPOSURE_TIME = "exposure_time";

            /**
             * Indexed value of {@link ExifInterface#TAG_F_NUMBER}
             * extracted from this media item.
             */
            String F_NUMBER = "f_number";

            /**
             * Indexed value of {@link ExifInterface#TAG_ISO_SPEED_RATINGS}
             * extracted from this media item.
             */
            String ISO = "iso";

            /**
             * Whether the image should be published as public or private
             */
            String IS_PRIVATE = "isprivate";

            /**
             * The latitude where the image was captured.
             *
             * @deprecated location details are no longer indexed for privacy
             *             reasons, and this value is now always {@code null}.
             *             You can still manually obtain location metadata using
             *             {@link ExifInterface#getLatLong(float[])}.
             */
            @Deprecated
            String LATITUDE = "latitude";

            /**
             * The longitude where the image was captured.
             *
             * @deprecated location details are no longer indexed for privacy
             *             reasons, and this value is now always {@code null}.
             *             You can still manually obtain location metadata using
             *             {@link ExifInterface#getLatLong(float[])}.
             */
            @Deprecated
            String LONGITUDE = "longitude";

            /**
             * The mini thumb id.
             *
             * @deprecated all thumbnails should be obtained via
             *             {@link MediaStore.Images.Thumbnails#getThumbnail}, as this
             *             value is no longer supported.
             */
            @Deprecated
            String MINI_THUMB_MAGIC = "mini_thumb_magic";

            /**
             * The picasa id of the image
             *
             * @deprecated this value was only relevant for images hosted on
             *             Picasa, which are no longer supported.
             */
            @Deprecated
            String PICASA_ID = "picasa_id";

            /**
             * Indexed value of {@link ExifInterface#TAG_SCENE_CAPTURE_TYPE}
             * extracted from this media item.
             */
            String SCENE_CAPTURE_TYPE = "scene_capture_type";
        }

        public static final class Media implements ImageColumns {
            /**
             * The MIME type of this directory of
             * images.  Note that each entry in this directory will have a standard
             * image MIME type as appropriate -- for example, image/jpeg.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/image";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "bucket_display_name";

            private Media() {
            }
        }

        /**
         * This class provides utility methods to obtain thumbnails for various
         * {@link Images} items.
         *
         * @deprecated Callers should migrate to using
         *             {@link ContentResolver#loadThumbnail}, since it offers
         *             richer control over requested thumbnail sizes and
         *             cancellation behavior.
         */
        @Deprecated
        public static final class Thumbnails implements BaseColumns {
            /**
             * Path to the thumbnail file on disk.
             *
             * As of {@link android.os.Build.VERSION_CODES#Q}, this thumbnail
             * has correct rotation, don't need to rotate it again.
             */
            @Deprecated
            public static final String DATA = "_data";

            /**
             * The default sort order for this table
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "image_id ASC";

            @Deprecated
            public static final int FULL_SCREEN_KIND = 2;

            /**
             * The height of the thumbnail
             */
            @Deprecated
            public static final String HEIGHT = "height";

            /**
             * The original image for the thumbnal
             */
            @Deprecated
            public static final String IMAGE_ID = "image_id";

            /**
             * The kind of the thumbnail
             */
            @Deprecated
            public static final String KIND = "kind";

            @Deprecated
            public static final int MICRO_KIND = 3;

            @Deprecated
            public static final int MINI_KIND = 1;

            /**
             * The blob raw data of thumbnail
             *
             * @deprecated this column never existed internally, and could never
             *             have returned valid data.
             */
            @Deprecated
            public static final String THUMB_DATA = "thumb_data";

            /**
             * The width of the thumbnal
             */
            @Deprecated
            public static final String WIDTH = "width";

            private Thumbnails() {
            }
        }
    }

    /**
     * Common media metadata columns.
     */
    public interface MediaColumns extends BaseColumns {
        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_ALBUM}
         * extracted from this media item.
         */
        String ALBUM = "album";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_ALBUMARTIST}
         * extracted from this media item.
         */
        String ALBUM_ARTIST = "album_artist";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_ARTIST}
         * or {@link ExifInterface#TAG_ARTIST} extracted from this media item.
         */
        String ARTIST = "artist";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_AUTHOR}
         * extracted from this media item.
         */
        String AUTHOR = "author";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_BITRATE}
         * extracted from this media item.
         */
        String BITRATE = "bitrate";

        /**
         * The primary bucket display name of this media item. This can be
         * useful to present the user a first-level clustering of related
         * media items. This is a read-only column that is automatically
         * computed.
         */
        String BUCKET_DISPLAY_NAME = "bucket_display_name";

        /**
         * The primary bucket ID of this media item. This can be useful to
         * present the user a first-level clustering of related media items.
         * This is a read-only column that is automatically computed.
         */
        String BUCKET_ID = "bucket_id";

        /**
         * Indexed value of
         * {@link MediaMetadataRetriever#METADATA_KEY_CAPTURE_FRAMERATE}
         * extracted from this media item.
         */
        String CAPTURE_FRAMERATE = "capture_framerate";

        /**
         * Indexed value of
         * {@link MediaMetadataRetriever#METADATA_KEY_CD_TRACK_NUMBER} extracted
         * from this media item.
         */
        String CD_TRACK_NUMBER = "cd_track_number";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_COMPILATION}
         * extracted from this media item.
         */
        String COMPILATION = "compilation";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_COMPOSER}
         * extracted from this media item.
         */
        String COMPOSER = "composer";

        /**
         * Absolute filesystem path to the media item on disk.
         * <p>
         * Apps may use this path to do file operations. However, they should not assume that the
         * file is always available. Apps must be prepared to handle any file-based I/O errors that
         * could occur.
         * <p>
         * From Android 11 onwards, this column is read-only for apps that target
         * {@link android.os.Build.VERSION_CODES#R R} and higher. On those devices, when creating or
         * updating a uri, this column's value is not accepted. Instead, to update the
         * filesystem location of a file, use the values of the {@link #DISPLAY_NAME} and
         * {@link #RELATIVE_PATH} columns.
         * <p>
         * Though direct file operations are supported,
         * {@link ContentResolver#openFileDescriptor(Uri, String)} API is recommended for better
         * performance.
         */
        String DATA = "_data";

        /**
         * The time the media item was first added.
         */
        String DATE_ADDED = "date_added";

        /**
         * The time the media item should be considered expired. Typically only
         * meaningful in the context of {@link #IS_PENDING} or
         * {@link #IS_TRASHED}.
         * <p>
         * The value stored in this column is automatically calculated when
         * {@link #IS_PENDING} or {@link #IS_TRASHED} is changed. The default
         * pending expiration is typically 7 days, and the default trashed
         * expiration is typically 30 days.
         * <p>
         * Expired media items are automatically deleted once their expiration
         * time has passed, typically during the next device idle period.
         */
        String DATE_EXPIRES = "date_expires";

        /**
         * Indexed value of {@link File#lastModified()} extracted from this
         * media item.
         */
        String DATE_MODIFIED = "date_modified";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_DATE} or
         * {@link ExifInterface#TAG_DATETIME_ORIGINAL} extracted from this media
         * item.
         * <p>
         * Note that images must define both
         * {@link ExifInterface#TAG_DATETIME_ORIGINAL} and
         * {@code ExifInterface#TAG_OFFSET_TIME_ORIGINAL} to reliably determine
         * this value in relation to the epoch.
         */
        String DATE_TAKEN = "datetaken";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_DISC_NUMBER}
         * extracted from this media item.
         */
        String DISC_NUMBER = "disc_number";

        /**
         * The display name of the media item.
         * <p>
         * For example, an item stored at
         * {@code /storage/0000-0000/DCIM/Vacation/IMG1024.JPG} would have a
         * display name of {@code IMG1024.JPG}.
         */
        String DISPLAY_NAME = "_display_name";

        /**
         * The "document ID" GUID as defined by the <em>XMP Media
         * Management</em> standard, extracted from any XMP metadata contained
         * within this media item. The value is {@code null} when no metadata
         * was found.
         * <p>
         * Each "document ID" is created once for each new resource. Different
         * renditions of that resource are expected to have different IDs.
         */
        String DOCUMENT_ID = "document_id";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_DURATION}
         * extracted from this media item.
         */
        String DURATION = "duration";

        /**
         * Generation number at which metadata for this media item was first
         * inserted. This is useful for apps that are attempting to quickly
         * identify exactly which media items have been added since a previous
         * point in time. Generation numbers are monotonically increasing over
         * time, and can be safely arithmetically compared.
         * <p>
         * Detecting media additions using generation numbers is more robust
         * than using {@link #DATE_ADDED}, since those values may change in
         * unexpected ways when apps use {@link File#setLastModified(long)} or
         * when the system clock is set incorrectly.
         * <p>
         * Note that before comparing these detailed generation values, you
         * should first confirm that the overall version hasn't changed by
         * checking {@link MediaStore#getVersion(Context, String)}, since that
         * indicates when a more radical change has occurred. If the overall
         * version changes, you should assume that generation numbers have been
         * reset and perform a full synchronization pass.
         *
         * @see MediaStore#getGeneration(Context, String)
         */
        String GENERATION_ADDED = "generation_added";

        /**
         * Generation number at which metadata for this media item was last
         * changed. This is useful for apps that are attempting to quickly
         * identify exactly which media items have changed since a previous
         * point in time. Generation numbers are monotonically increasing over
         * time, and can be safely arithmetically compared.
         * <p>
         * Detecting media changes using generation numbers is more robust than
         * using {@link #DATE_MODIFIED}, since those values may change in
         * unexpected ways when apps use {@link File#setLastModified(long)} or
         * when the system clock is set incorrectly.
         * <p>
         * Note that before comparing these detailed generation values, you
         * should first confirm that the overall version hasn't changed by
         * checking {@link MediaStore#getVersion(Context, String)}, since that
         * indicates when a more radical change has occurred. If the overall
         * version changes, you should assume that generation numbers have been
         * reset and perform a full synchronization pass.
         *
         * @see MediaStore#getGeneration(Context, String)
         */
        String GENERATION_MODIFIED = "generation_modified";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_GENRE}
         * extracted from this media item.
         */
        String GENRE = "genre";

        /**
         * Indexed value of
         * {@link MediaMetadataRetriever#METADATA_KEY_VIDEO_HEIGHT},
         * {@link MediaMetadataRetriever#METADATA_KEY_IMAGE_HEIGHT} or
         * {@link ExifInterface#TAG_IMAGE_LENGTH} extracted from this media
         * item.
         * <p>
         * Type: INTEGER
         */
        String HEIGHT = "height";

        /**
         * File's approximate creation date.
         * <p>
         * Following is the derivation logic:
         * 1. If {@link MediaColumns#DATE_TAKEN} is present, use it.
         * 2. If {@link MediaColumns#DATE_TAKEN} is absent, use {@link MediaColumns#DATE_MODIFIED}.
         * Note: When {@link QUERY_ARG_MEDIA_STANDARD_SORT_ORDER} query argument
         * is used, the sorting is based on this column in descending order.
         */
        String INFERRED_DATE = "inferred_date";

        /**
         * The "instance ID" GUID as defined by the <em>XMP Media
         * Management</em> standard, extracted from any XMP metadata contained
         * within this media item. The value is {@code null} when no metadata
         * was found.
         * <p>
         * This "instance ID" changes with each save operation of a specific
         * "document ID".
         */
        String INSTANCE_ID = "instance_id";

        /**
         * Flag indicating if the media item has been marked as being part of
         * the {@link Downloads} collection.
         */
        String IS_DOWNLOAD = "is_download";

        /**
         * Flag indicating if a media item is DRM protected.
         */
        String IS_DRM = "is_drm";

        /**
         * Flag indicating if the media item has been marked as being a
         * "favorite" by the user.
         *
         * @see MediaColumns#IS_FAVORITE
         * @see MediaStore#QUERY_ARG_MATCH_FAVORITE
         * @see MediaStore#createFavoriteRequest
         */
        String IS_FAVORITE = "is_favorite";

        /**
         * Flag indicating if a media item is pending, and still being inserted
         * by its owner. While this flag is set, only the owner of the item can
         * open the underlying file; requests from other apps will be rejected.
         * <p>
         * Pending items are retained either until they are published by setting
         * the field to {@code 0}, or until they expire as defined by
         * {@link #DATE_EXPIRES}.
         *
         * @see MediaStore#QUERY_ARG_MATCH_PENDING
         */
        String IS_PENDING = "is_pending";

        /**
         * Flag indicating if a media item is trashed.
         * <p>
         * Trashed items are retained until they expire as defined by
         * {@link #DATE_EXPIRES}.
         *
         * @see MediaColumns#IS_TRASHED
         * @see MediaStore#QUERY_ARG_MATCH_TRASHED
         * @see MediaStore#createTrashRequest
         */
        String IS_TRASHED = "is_trashed";

        /**
         * The MIME type of the media item.
         * <p>
         * This is typically defined based on the file extension of the media
         * item. However, it may be the value of the {@code format} attribute
         * defined by the <em>Dublin Core Media Initiative</em> standard,
         * extracted from any XMP metadata contained within this media item.
         * <p class="note">
         * Note: the {@code format} attribute may be ignored if the top-level
         * MIME type disagrees with the file extension. For example, it's
         * reasonable for an {@code image/jpeg} file to declare a {@code format}
         * of {@code image/vnd.google.panorama360+jpg}, but declaring a
         * {@code format} of {@code audio/ogg} would be ignored.
         * <p>
         * This is a read-only column that is automatically computed.
         */
        String MIME_TYPE = "mime_type";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_NUM_TRACKS}
         * extracted from this media item.
         */
        String NUM_TRACKS = "num_tracks";

        /**
         * Column which allows OEMs to store custom metadata for a media file.
         */
        String OEM_METADATA = "oem_metadata";

        /**
         * Indexed value of
         * {@link MediaMetadataRetriever#METADATA_KEY_VIDEO_ROTATION},
         * {@link MediaMetadataRetriever#METADATA_KEY_IMAGE_ROTATION}, or
         * {@link ExifInterface#TAG_ORIENTATION} extracted from this media item.
         * <p>
         * For consistency the indexed value is expressed in degrees, such as 0,
         * 90, 180, or 270.
         * <p>
         * Type: INTEGER
         */
        String ORIENTATION = "orientation";

        /**
         * The "original document ID" GUID as defined by the <em>XMP Media
         * Management</em> standard, extracted from any XMP metadata contained
         * within this media item.
         * <p>
         * This "original document ID" links a resource to its original source.
         * For example, when you save a PSD document as a JPEG, then convert the
         * JPEG to GIF format, the "original document ID" of both the JPEG and
         * GIF files is the "document ID" of the original PSD file.
         */
        String ORIGINAL_DOCUMENT_ID = "original_document_id";

        /**
         * Package name that contributed this media. The value may be
         * {@code NULL} if ownership cannot be reliably determined.
         * <p>
         * From Android {@link Build.VERSION_CODES#UPSIDE_DOWN_CAKE} onwards,
         * visibility and query of this field will depend on
         * <a href="/training/basics/intents/package-visibility">package visibility</a>.
         * For {@link ContentResolver#query} operation, result set will
         * be restricted to visible packages only.
         */
        String OWNER_PACKAGE_NAME = "owner_package_name";

        /**
         * Relative path of this media item within the storage device where it
         * is persisted. For example, an item stored at
         * {@code /storage/0000-0000/DCIM/Vacation/IMG1024.JPG} would have a
         * path of {@code DCIM/Vacation/}.
         * <p>
         * This value should only be used for organizational purposes, and you
         * should not attempt to construct or access a raw filesystem path using
         * this value. If you need to open a media item, use an API like
         * {@link ContentResolver#openFileDescriptor(Uri, String)}.
         * <p>
         * When this value is set to {@code NULL} during an
         * {@link ContentResolver#insert} operation, the newly created item will
         * be placed in a relevant default location based on the type of media
         * being inserted. For example, a {@code image/jpeg} item will be placed
         * under {@link Environment#DIRECTORY_PICTURES}.
         * <p>
         * You can modify this column during an {@link ContentResolver#update}
         * call, which will move the underlying file on disk.
         * <p>
         * In both cases above, content must be placed under a top-level
         * directory that is relevant to the media type. For example, attempting
         * to place a {@code audio/mpeg} file under
         * {@link Environment#DIRECTORY_PICTURES} will be rejected.
         */
        String RELATIVE_PATH = "relative_path";

        /**
         * Calculated value that combines {@link #WIDTH} and {@link #HEIGHT}
         * into a user-presentable string.
         */
        String RESOLUTION = "resolution";

        /**
         * Indexed value of {@link File#length()} extracted from this media
         * item.
         */
        String SIZE = "_size";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_TITLE}
         * extracted from this media item.
         */
        String TITLE = "title";

        /**
         * Volume name of the specific storage device where this media item is
         * persisted. The value is typically one of the volume names returned
         * from {@link MediaStore#getExternalVolumeNames(Context)}.
         * <p>
         * This is a read-only column that is automatically computed.
         */
        String VOLUME_NAME = "volume_name";

        /**
         * Indexed value of
         * {@link MediaMetadataRetriever#METADATA_KEY_VIDEO_WIDTH},
         * {@link MediaMetadataRetriever#METADATA_KEY_IMAGE_WIDTH} or
         * {@link ExifInterface#TAG_IMAGE_WIDTH} extracted from this media item.
         * <p>
         * Type: INTEGER
         */
        String WIDTH = "width";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_WRITER}
         * extracted from this media item.
         */
        String WRITER = "writer";

        /**
         * Indexed XMP metadata extracted from this media item.
         * <p>
         * The structure of this metadata is defined by the <a href=
         * "https://en.wikipedia.org/wiki/Extensible_Metadata_Platform"><em>XMP
         * Media Management</em> standard</a>, published as ISO 16684-1:2012.
         * <p>
         * This metadata is typically extracted from a
         * {@link ExifInterface#TAG_XMP} contained inside an image file or from
         * a {@code XMP_} box contained inside an ISO/IEC base media file format
         * (MPEG-4 Part 12).
         * <p>
         * Note that any location details are redacted from this metadata for
         * privacy reasons.
         */
        String XMP = "xmp";

        /**
         * Indexed value of {@link MediaMetadataRetriever#METADATA_KEY_YEAR}
         * extracted from this media item.
         */
        String YEAR = "year";
    }

    /**
     * Photo picker metadata columns.
     *
     * @see #ACTION_PICK_IMAGES
     */
    public static final class PickerMediaColumns {
        /**
         * This is identical to {@link MediaColumns#DATA}, however, apps should not assume that the
         * file is always available because the file may be backed by a {@link CloudMediaProvider}
         * fetching content over a network. Therefore, apps must be prepared to handle any
         * additional file-based I/O errors that could occur as a result of network errors.
         *
         * @see MediaColumns#DATA
         */
        public static final String DATA = "_data";

        /**
         * This is identical to {@link MediaColumns#DATE_TAKEN}.
         *
         * @see MediaColumns#DATE_TAKEN
         */
        public static final String DATE_TAKEN = "datetaken";

        /**
         * This is identical to {@link MediaColumns#DISPLAY_NAME}.
         *
         * @see MediaColumns#DISPLAY_NAME
         */
        public static final String DISPLAY_NAME = "_display_name";

        /**
         * This is identical to {@link MediaColumns#DURATION}.
         *
         * @see MediaColumns#DURATION
         */
        public static final String DURATION_MILLIS = "duration";

        /**
         * This is identical to {@link MediaColumns#HEIGHT}.
         *
         * @see MediaColumns#HEIGHT
         */
        public static final String HEIGHT = "height";

        /**
         * This is identical to {@link MediaColumns#MIME_TYPE}.
         *
         * @see MediaColumns#MIME_TYPE
         */
        public static final String MIME_TYPE = "mime_type";

        /**
         * This is identical to {@link MediaColumns#ORIENTATION}.
         *
         * @see MediaColumns#ORIENTATION
         */
        public static final String ORIENTATION = "orientation";

        /**
         * This is identical to {@link MediaColumns#SIZE}.
         *
         * @see MediaColumns#SIZE
         */
        public static final String SIZE = "_size";

        /**
         * This is identical to {@link MediaColumns#WIDTH}.
         *
         * @see MediaColumns#WIDTH
         */
        public static final String WIDTH = "width";

        private PickerMediaColumns() {
        }
    }

    /**
     * Collection of all media with MIME type of {@code video/*}.
     */
    public static final class Video {
        /**
         * The default sort order for this table.
         */
        public static final String DEFAULT_SORT_ORDER = "_display_name";

        private Video() {
        }

        public static final class Media implements VideoColumns {
            /**
             * The MIME type for this table.
             */
            public static final String CONTENT_TYPE = "vnd.android.cursor.dir/video";

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "title";

            private Media() {
            }
        }

        /**
         * This class provides utility methods to obtain thumbnails for various
         * {@link Video} items.
         *
         * @deprecated Callers should migrate to using
         *             {@link ContentResolver#loadThumbnail}, since it offers
         *             richer control over requested thumbnail sizes and
         *             cancellation behavior.
         */
        @Deprecated
        public static final class Thumbnails implements BaseColumns {
            /**
             * Path to the thumbnail file on disk.
             */
            @Deprecated
            public static final String DATA = "_data";

            /**
             * The default sort order for this table
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "video_id ASC";

            @Deprecated
            public static final int FULL_SCREEN_KIND = 2;

            /**
             * The height of the thumbnail
             */
            @Deprecated
            public static final String HEIGHT = "height";

            /**
             * The kind of the thumbnail
             */
            @Deprecated
            public static final String KIND = "kind";

            @Deprecated
            public static final int MICRO_KIND = 3;

            @Deprecated
            public static final int MINI_KIND = 1;

            /**
             * The original image for the thumbnal
             */
            @Deprecated
            public static final String VIDEO_ID = "video_id";

            /**
             * The width of the thumbnal
             */
            @Deprecated
            public static final String WIDTH = "width";

            private Thumbnails() {
            }
        }

        /**
         * Video metadata columns.
         */
        public interface VideoColumns extends MediaColumns {
            /**
             * The position within the video item at which playback should be
             * resumed.
             */
            String BOOKMARK = "bookmark";

            /**
             * The YouTube category of the video
             */
            String CATEGORY = "category";

            /**
             * The color range of this media file, if available.
             *
             * @see MediaFormat#COLOR_RANGE_LIMITED
             * @see MediaFormat#COLOR_RANGE_FULL
             */
            String COLOR_RANGE = "color_range";

            /**
             * The color standard of this media file, if available.
             *
             * @see MediaFormat#COLOR_STANDARD_BT709
             * @see MediaFormat#COLOR_STANDARD_BT601_PAL
             * @see MediaFormat#COLOR_STANDARD_BT601_NTSC
             * @see MediaFormat#COLOR_STANDARD_BT2020
             */
            String COLOR_STANDARD = "color_standard";

            /**
             * The color transfer of this media file, if available.
             *
             * @see MediaFormat#COLOR_TRANSFER_LINEAR
             * @see MediaFormat#COLOR_TRANSFER_SDR_VIDEO
             * @see MediaFormat#COLOR_TRANSFER_ST2084
             * @see MediaFormat#COLOR_TRANSFER_HLG
             */
            String COLOR_TRANSFER = "color_transfer";

            /**
             * The description of the video recording
             */
            String DESCRIPTION = "description";

            /**
             * Whether the video should be published as public or private
             */
            String IS_PRIVATE = "isprivate";

            /**
             * The language of the video
             */
            String LANGUAGE = "language";

            /**
             * The latitude where the video was captured.
             *
             * @deprecated location details are no longer indexed for privacy
             *             reasons, and this value is now always {@code null}.
             *             You can still manually obtain location metadata using
             *             {@link MediaMetadataRetriever#METADATA_KEY_LOCATION}.
             */
            @Deprecated
            String LATITUDE = "latitude";

            /**
             * The longitude where the video was captured.
             *
             * @deprecated location details are no longer indexed for privacy
             *             reasons, and this value is now always {@code null}.
             *             You can still manually obtain location metadata using
             *             {@link MediaMetadataRetriever#METADATA_KEY_LOCATION}.
             */
            @Deprecated
            String LONGITUDE = "longitude";

            /**
             * The mini thumb id.
             *
             * @deprecated all thumbnails should be obtained via
             *             {@link MediaStore.Images.Thumbnails#getThumbnail}, as this
             *             value is no longer supported.
             */
            @Deprecated
            String MINI_THUMB_MAGIC = "mini_thumb_magic";

            /**
             * The user-added tags associated with a video
             */
            String TAGS = "tags";
        }
    }
}
