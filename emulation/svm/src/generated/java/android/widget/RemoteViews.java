package android.widget;

import android.os.Parcelable;

/**
 * A class that describes a view hierarchy that can be displayed in
 * another process. The hierarchy is inflated from a layout resource
 * file, and this class provides some basic operations for modifying
 * the content of the inflated hierarchy.
 *
 * <p>{@code RemoteViews} is limited to support for the following layouts:</p>
 * <ul>
 *   <li>{@link android.widget.AdapterViewFlipper}</li>
 *   <li>{@link android.widget.FrameLayout}</li>
 *   <li>{@link android.widget.GridLayout}</li>
 *   <li>{@link android.widget.GridView}</li>
 *   <li>{@link android.widget.LinearLayout}</li>
 *   <li>{@link android.widget.ListView}</li>
 *   <li>{@link android.widget.RelativeLayout}</li>
 *   <li>{@link android.widget.StackView}</li>
 *   <li>{@link android.widget.ViewFlipper}</li>
 * </ul>
 * <p>And the following widgets:</p>
 * <ul>
 *   <li>{@link android.widget.AnalogClock}</li>
 *   <li>{@link android.widget.Button}</li>
 *   <li>{@link android.widget.Chronometer}</li>
 *   <li>{@link android.widget.ImageButton}</li>
 *   <li>{@link android.widget.ImageView}</li>
 *   <li>{@link android.widget.ProgressBar}</li>
 *   <li>{@link android.widget.TextClock}</li>
 *   <li>{@link android.widget.TextView}</li>
 * </ul>
 * <p>As of API 31, the following widgets and layouts may also be used:</p>
 * <ul>
 *     <li>{@link android.widget.CheckBox}</li>
 *     <li>{@link android.widget.RadioButton}</li>
 *     <li>{@link android.widget.RadioGroup}</li>
 *     <li>{@link android.widget.Switch}</li>
 * </ul>
 * <p>Descendants of these classes are not supported.</p>
 */
public final class RemoteViews implements Parcelable {
    /**
     * The intent extra for whether the view whose checked state changed is currently checked.
     */
    public static final String EXTRA_CHECKED = "android.widget.extra.CHECKED";

    /**
     * The intent extra that contains the bounds for all shared elements.
     */
    public static final String EXTRA_SHARED_ELEMENT_BOUNDS = "android.widget.extra.SHARED_ELEMENT_BOUNDS";

    /**
     * The value will apply to the marginBottom.
     */
    public static final int MARGIN_BOTTOM = 3;

    /**
     * The value will apply to the marginEnd.
     */
    public static final int MARGIN_END = 5;

    /**
     * The value will apply to the marginLeft.
     */
    public static final int MARGIN_LEFT = 0;

    /**
     * The value will apply to the marginRight.
     */
    public static final int MARGIN_RIGHT = 2;

    /**
     * The value will apply to the marginStart.
     */
    public static final int MARGIN_START = 4;

    /**
     * The value will apply to the marginTop.
     */
    public static final int MARGIN_TOP = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private RemoteViews() {
    }
}
