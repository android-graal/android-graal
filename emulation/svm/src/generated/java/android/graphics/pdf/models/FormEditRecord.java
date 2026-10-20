package android.graphics.pdf.models;

import android.os.Parcelable;

/**
 * Record of a form filling operation that has been executed on a single form field in a PDF.
 * Contains the minimum amount of data required to replicate the action on the form.
 *
 * @see <a
 * href="https://opensource.adobe.com/dc-acrobat-sdk-docs/pdfstandards/PDF32000_2008.pdf">PDF
 * 32000-1:2008</a>
 */
public final class FormEditRecord implements Parcelable {
    /**
     * Indicates a click on a clickable form widget
     */
    public static final int EDIT_TYPE_CLICK = 0;

    /**
     * Represents setting indices on a combobox or listbox form widget
     */
    public static final int EDIT_TYPE_SET_INDICES = 1;

    /**
     * Represents setting text on a text field or editable combobox form widget
     */
    public static final int EDIT_TYPE_SET_TEXT = 2;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private FormEditRecord() {
    }
}
