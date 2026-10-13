package android.content;

import android.os.Parcelable;

/**
 * Applications can expose restrictions for a restricted user on a
 * multiuser device. The administrator can configure these restrictions that will then be
 * applied to the restricted user. Each RestrictionsEntry is one configurable restriction.
 * <p/>
 * Any application that chooses to expose such restrictions does so by implementing a
 * receiver that handles the {@link Intent#ACTION_GET_RESTRICTION_ENTRIES} action.
 * The receiver then returns a result bundle that contains an entry called "restrictions", whose
 * value is an ArrayList<RestrictionsEntry>.
 */
public final class RestrictionEntry implements Parcelable {
    /**
     * Restriction of type "bool". Use this for storing a boolean value, typically presented as
     * a checkbox in the UI.
     */
    public static final int TYPE_BOOLEAN = 1;

    /**
     * Restriction of type "bundle". Use this for storing {@link android.os.Bundle bundles} of
     * restrictions
     */
    public static final int TYPE_BUNDLE = 7;

    /**
     * Restriction of type "bundle_array". Use this for storing arrays of
     * {@link android.os.Bundle bundles} of restrictions
     */
    public static final int TYPE_BUNDLE_ARRAY = 8;

    /**
     * Restriction of type "choice". Use this for storing a string value, typically presented as
     * a single-select list. Call {@link #setChoiceEntries(String[])} and
     * {@link #setChoiceValues(String[])} to set the localized list entries to present to the user
     * and the corresponding values, respectively.
     */
    public static final int TYPE_CHOICE = 2;

    /**
     * Restriction of type "integer". Use this for storing an integer value. The range of values
     * is from {@link Integer#MIN_VALUE} to {@link Integer#MAX_VALUE}.
     */
    public static final int TYPE_INTEGER = 5;

    /**
     * Restriction of type "multi-select". Use this for presenting a multi-select list where more
     * than one entry can be selected, such as for choosing specific titles to allowlist.
     * Call {@link #setChoiceEntries(String[])} and
     * {@link #setChoiceValues(String[])} to set the localized list entries to present to the user
     * and the corresponding values, respectively.
     * Use {@link #getAllSelectedStrings()} and {@link #setAllSelectedStrings(String[])} to
     * manipulate the selections.
     */
    public static final int TYPE_MULTI_SELECT = 4;

    /**
     * Hidden restriction type. Use this type for information that needs to be transferred
     * across but shouldn't be presented to the user in the UI. Stores a single String value.
     */
    public static final int TYPE_NULL = 0;

    /**
     * Restriction of type "string". Use this for storing a string value.
     * @see #setSelectedString
     * @see #getSelectedString
     */
    public static final int TYPE_STRING = 6;

    private RestrictionEntry() {
    }
}
