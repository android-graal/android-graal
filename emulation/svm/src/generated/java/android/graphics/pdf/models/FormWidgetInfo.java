package android.graphics.pdf.models;

import android.os.Parcelable;

/**
 * Information about a form widget of a PDF document.
 *
 * @see <a
 * href="https://opensource.adobe.com/dc-acrobat-sdk-docs/pdfstandards/PDF32000_2008.pdf">PDF
 * 32000-1:2008</a>
 */
public final class FormWidgetInfo implements Parcelable {
    /**
     * Represents a checkbox type form widget
     */
    public static final int WIDGET_TYPE_CHECKBOX = 2;

    /**
     * Represents a combobox type form widget
     */
    public static final int WIDGET_TYPE_COMBOBOX = 4;

    /**
     * Represents a listbox type form widget
     */
    public static final int WIDGET_TYPE_LISTBOX = 5;

    /**
     * Represents a push button type form widget
     */
    public static final int WIDGET_TYPE_PUSHBUTTON = 1;

    /**
     * Represents a radio button type form widget
     */
    public static final int WIDGET_TYPE_RADIOBUTTON = 3;

    /**
     * Represents a signature type form widget
     */
    public static final int WIDGET_TYPE_SIGNATURE = 7;

    /**
     * Represents a text field type form widget
     */
    public static final int WIDGET_TYPE_TEXTFIELD = 6;

    /**
     * Represents a form widget type that is unknown
     */
    public static final int WIDGET_TYPE_UNKNOWN = 0;

    private FormWidgetInfo() {
    }
}
