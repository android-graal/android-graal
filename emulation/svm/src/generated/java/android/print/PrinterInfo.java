package android.print;

import android.os.Parcelable;

/**
 * This class represents the description of a printer. Instances of
 * this class are created by print services to report to the system
 * the printers they manage. The information of this class has two
 * major components, printer properties such as name, id, status,
 * description and printer capabilities which describe the various
 * print modes a printer supports such as media sizes, margins, etc.
 * <p>
 * Once {@link PrinterInfo.Builder#build() built} the objects are immutable.
 * </p>
 */
public final class PrinterInfo implements Parcelable {
    /**
     * Printer status: the printer is busy printing.
     */
    public static final int STATUS_BUSY = 2;

    /**
     * Printer status: the printer is idle and ready to print.
     */
    public static final int STATUS_IDLE = 1;

    /**
     * Printer status: the printer is not available.
     */
    public static final int STATUS_UNAVAILABLE = 3;

    private PrinterInfo() {
    }
}
