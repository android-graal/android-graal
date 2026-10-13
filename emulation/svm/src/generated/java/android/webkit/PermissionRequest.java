package android.webkit;

/**
 * This class defines a permission request and is used when web content
 * requests access to protected resources. The permission request related events
 * are delivered via {@link WebChromeClient#onPermissionRequest} and
 * {@link WebChromeClient#onPermissionRequestCanceled}.
 *
 * Either {@link #grant(String[]) grant()} or {@link #deny()} must be called in UI
 * thread to respond to the request.
 *
 * New protected resources whose names are not defined here may be requested in
 * future versions of WebView, even when running on an older Android release. To
 * avoid unintentionally granting requests for new permissions, you should pass the
 * specific permissions you intend to grant to {@link #grant(String[]) grant()},
 * and avoid writing code like this example:
 * <pre class="prettyprint">
 * permissionRequest.grant(permissionRequest.getResources())  // This is wrong!!!
 * </pre>
 * See the WebView's release notes for information about new protected resources.
 */
public final class PermissionRequest {
    /**
     * Resource belongs to audio capture device, like microphone.
     */
    public static final String RESOURCE_AUDIO_CAPTURE = "android.webkit.resource.AUDIO_CAPTURE";

    /**
     * Resource will allow sysex messages to be sent to or received from MIDI devices. These
     * messages are privileged operations, e.g. modifying sound libraries and sampling data, or
     * even updating the MIDI device's firmware.
     *
     * Permission may be requested for this resource in API levels 21 and above, if the Android
     * device has been updated to WebView 45 or above.
     */
    public static final String RESOURCE_MIDI_SYSEX = "android.webkit.resource.MIDI_SYSEX";

    /**
     * Resource belongs to protected media identifier.
     * After the user grants this resource, the origin can use EME APIs to generate the license
     * requests.
     */
    public static final String RESOURCE_PROTECTED_MEDIA_ID = "android.webkit.resource.PROTECTED_MEDIA_ID";

    /**
     * Resource belongs to video capture device, like camera.
     */
    public static final String RESOURCE_VIDEO_CAPTURE = "android.webkit.resource.VIDEO_CAPTURE";

    private PermissionRequest() {
    }
}
