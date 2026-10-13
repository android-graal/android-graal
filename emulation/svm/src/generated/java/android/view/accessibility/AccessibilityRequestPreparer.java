package android.view.accessibility;

/**
 * Object responsible to ensuring that a {@link View} is prepared to meet a synchronous request for
 * accessibility data.
 * <p>
 * Because accessibility requests arrive to {@link View}s synchronously on the UI thread, a View
 * that requires information from other processes can struggle to meet those requests. Registering
 * an instance of this class with {@link AccessibilityManager} allows a View to be notified when
 * a request is about to be made, and to asynchronously inform the accessibility system when it is
 * ready to meet the request.
 * <p>
 * <strong>Note:</strong> This class should only be needed in exceptional situations where a
 * {@link View} cannot otherwise synchronously meet the request for accessibility data.
 */
public final class AccessibilityRequestPreparer {
    public static final int REQUEST_TYPE_EXTRA_DATA = 1;

    private AccessibilityRequestPreparer() {
    }
}
