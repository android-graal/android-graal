package android.nfc;

import android.os.Parcelable;

/**
 * Represents an immutable NDEF Record.
 * <p>
 * NDEF (NFC Data Exchange Format) is a light-weight binary format,
 * used to encapsulate typed data. It is specified by the NFC Forum,
 * for transmission and storage with NFC, however it is transport agnostic.
 * <p>
 * NDEF defines messages and records. An NDEF Record contains
 * typed data, such as MIME-type media, a URI, or a custom
 * application payload. An NDEF Message is a container for
 * one or more NDEF Records.
 * <p>
 * This class represents logical (complete) NDEF Records, and can not be
 * used to represent chunked (partial) NDEF Records. However
 * {@link NdefMessage#NdefMessage(byte[])} can be used to parse a message
 * containing chunked records, and will return a message with unchunked
 * (complete) records.
 * <p>
 * A logical NDEF Record always contains a 3-bit TNF (Type Name Field)
 * that provides high level typing for the rest of the record. The
 * remaining fields are variable length and not always present:
 * <ul>
 * <li><em>type</em>: detailed typing for the payload</li>
 * <li><em>id</em>: identifier meta-data, not commonly used</li>
 * <li><em>payload</em>: the actual payload</li>
 * </ul>
 * <p>
 * Helpers such as {@link NdefRecord#createUri}, {@link NdefRecord#createMime}
 * and {@link NdefRecord#createExternal} are included to create well-formatted
 * NDEF Records with correctly set tnf, type, id and payload fields, please
 * use these helpers whenever possible.
 * <p>
 * Use the constructor {@link #NdefRecord(short, byte[], byte[], byte[])}
 * if you know what you are doing and what to set the fields individually.
 * Only basic validation is performed with this constructor, so it is possible
 * to create records that do not confirm to the strict NFC Forum
 * specifications.
 * <p>
 * The binary representation of an NDEF Record includes additional flags to
 * indicate location with an NDEF message, provide support for chunking of
 * NDEF records, and to pack optional fields. This class does not expose
 * those details. To write an NDEF Record as binary you must first put it
 * into an {@link NdefMessage}, then call {@link NdefMessage#toByteArray()}.
 * <p class="note">
 * {@link NdefMessage} and {@link NdefRecord} implementations are
 * always available, even on Android devices that do not have NFC hardware.
 * <p class="note">
 * {@link NdefRecord}s are intended to be immutable (and thread-safe),
 * however they may contain mutable fields. So take care not to modify
 * mutable fields passed into constructors, or modify mutable fields
 * obtained by getter methods, unless such modification is explicitly
 * marked as safe.
 *
 * @see NfcAdapter#ACTION_NDEF_DISCOVERED
 * @see NdefMessage
 */
public final class NdefRecord implements Parcelable {
    /**
     * Indicates the type field contains an absolute-URI
     * BNF construct defined by RFC 3986.<p>
     * When creating new records prefer {@link #createUri},
     * since it offers more compact URI encoding
     * ({@literal #RTD_URI} allows compression of common URI prefixes).
     *
     * @see #createUri
     */
    public static final short TNF_ABSOLUTE_URI = 3;

    /**
     * Indicates the record is empty.<p>
     * Type, id and payload fields are empty in a {@literal TNF_EMPTY} record.
     */
    public static final short TNF_EMPTY = 0;

    /**
     * Indicates the type field contains an external type name.<p>
     * Used to encode custom payloads. When creating new records
     * use the helper {@link #createExternal}.<p>
     * The external-type RTD format is specified in NFCForum-TS-RTD_1.0.<p>
     * <p>
     * Note this TNF should not be used with RTD_TEXT or RTD_URI constants.
     * Those are well known RTD constants, not external RTD constants.
     *
     * @see #createExternal
     */
    public static final short TNF_EXTERNAL_TYPE = 4;

    /**
     * Indicates the type field contains a media-type BNF
     * construct, defined by RFC 2046.<p>
     * Use this with MIME type names such as {@literal "image/jpeg"}, or
     * using the helper {@link #createMime}.
     *
     * @see #createMime
     */
    public static final short TNF_MIME_MEDIA = 2;

    /**
     * Indicates the payload is an intermediate or final chunk of a chunked
     * NDEF Record.<p>
     * {@literal TNF_UNCHANGED} can not be used with this class
     * since all {@link NdefRecord}s are already unchunked, however they
     * may appear in the binary format.
     */
    public static final short TNF_UNCHANGED = 6;

    /**
     * Indicates the payload type is unknown.<p>
     * NFC Forum explains this should be treated similarly to the
     * "application/octet-stream" MIME type. The payload
     * type is not explicitly encoded within the record.
     * <p>
     * The type field is empty in an {@literal TNF_UNKNOWN} record.
     */
    public static final short TNF_UNKNOWN = 5;

    /**
     * Indicates the type field contains a well-known RTD type name.<p>
     * Use this tnf with RTD types such as {@link #RTD_TEXT}, {@link #RTD_URI}.
     * <p>
     * The RTD type name format is specified in NFCForum-TS-RTD_1.0.
     *
     * @see #RTD_URI
     * @see #RTD_TEXT
     * @see #RTD_SMART_POSTER
     * @see #createUri
     */
    public static final short TNF_WELL_KNOWN = 1;

    private NdefRecord() {
    }
}
