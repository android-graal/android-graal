package android.app.assist;

import android.os.Parcelable;

/**
 * <p>This API automatically creates assist data from the platform's
 * implementation of assist and autofill.
 *
 * <p>The structure is used for assist purposes when created by
 * {@link android.app.Activity#onProvideAssistData}, {@link View#onProvideStructure(ViewStructure)},
 * or {@link View#onProvideVirtualStructure(ViewStructure)}.
 *
 * <p>The structure is also used for autofill purposes when created by
 * {@link View#onProvideAutofillStructure(ViewStructure, int)},
 * or {@link View#onProvideAutofillVirtualStructure(ViewStructure, int)}.
 *
 * <p>For performance reasons, some properties of the assist data might only be available for
 * assist or autofill purposes. In those cases, a property's availability will be documented
 * in its javadoc.
 *
 * <p>To learn about using Autofill in your app, read the
 * <a href="/guide/topics/text/autofill">Autofill Framework</a> guides.
 */
public final class AssistStructure implements Parcelable {
    private AssistStructure() {
    }

    /**
     * Describes a single view in the assist data.
     */
    public static final class ViewNode {
        /**
         * Magic value for text color that has not been defined, which is very unlikely
         * to be confused with a real text color.
         */
        public static final int TEXT_COLOR_UNDEFINED = 1;

        public static final int TEXT_STYLE_BOLD = 1;

        public static final int TEXT_STYLE_ITALIC = 2;

        public static final int TEXT_STYLE_STRIKE_THRU = 8;

        public static final int TEXT_STYLE_UNDERLINE = 4;

        private ViewNode() {
        }
    }
}
