package android.provider;

/**
 * The contract between the provider of contact records on the device's SIM cards and applications.
 * Contains definitions of the supported URIs and columns.
 *
 * <h3>Permissions</h3>
 * <p>
 * Querying this provider requires {@link android.Manifest.permission#READ_CONTACTS} and writing
 * to this provider requires {@link android.Manifest.permission#WRITE_CONTACTS}
 * </p>
 */
public final class SimPhonebookContract {
    /**
     * The authority for the SIM phonebook provider.
     */
    public static final String AUTHORITY = "com.android.simphonebook";

    private SimPhonebookContract() {
    }

    /**
     * Constants for metadata about the elementary files of the SIM cards in the phone.
     *
     * <h3>Operations </h3>
     * <dl>
     * <dd><b>Insert</b></dd>
     * <p>Insert is not supported for the Uris defined in this class.</p>
     * <dd><b>Update</b></dd>
     * <p>Update is not supported for the Uris defined in this class.</p>
     * <dd><b>Delete</b></dd>
     * <p>Delete is not supported for the Uris defined in this class.</p>
     * <dd><b>Query</b></dd>
     * <p>
     * The elementary files for all the inserted SIMs can be read via
     * {@link ElementaryFiles#CONTENT_URI}. Unsupported elementary files are omitted from the
     * results. This Uri always returns all supported elementary files for all available SIMs; it
     * does not support filtering via a selection. A specific elementary file can be queried
     * via a Uri returned by {@link ElementaryFiles#getItemUri(int, int)}. If the elementary file
     * referenced by this Uri is unsupported by the SIM then the query will return an empty cursor.
     * </p>
     * </dl>
     */
    public static final class ElementaryFiles {
        /**
         * The MIME type of a CONTENT_URI subdirectory of a single ADN-like elementary file.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/sim-elementary-file";

        /**
         * The MIME type of CONTENT_URI providing a directory of ADN-like elementary files.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/sim-elementary-file";

        /**
         * Type for accessing records in the "abbreviated dialing number" (ADN) elementary file on
         * the SIM.
         *
         * <p>ADN records are typically user created.
         */
        public static final int EF_ADN = 1;

        /**
         * Type for accessing records in the "fixed dialing number" (FDN) elementary file on the
         * SIM.
         *
         * <p>FDN numbers are the numbers that are allowed to dialed for outbound calls when FDN is
         * enabled.
         *
         * <p>FDN records cannot be modified by applications. Hence, insert, update and
         * delete methods operating on this Uri will throw UnsupportedOperationException
         */
        public static final int EF_FDN = 2;

        /**
         * Type for accessing records in the "service dialing number" (SDN) elementary file on the
         * SIM.
         *
         * <p>Typically SDNs are preset numbers provided by the carrier for common operations (e.g.
         * voicemail, check balance, etc).
         *
         * <p>SDN records cannot be modified by applications. Hence, insert, update and delete
         * methods operating on this Uri will throw UnsupportedOperationException
         */
        public static final int EF_SDN = 3;

        /**
         * The elementary file type for this row.
         *
         * @see ElementaryFiles#EF_ADN
         * @see ElementaryFiles#EF_FDN
         * @see ElementaryFiles#EF_SDN
         */
        public static final String EF_TYPE = "ef_type";

        /**
         * A value for an elementary file that is not recognized.
         *
         * <p>Generally this should be ignored. If new values are added then this will be used
         * for apps that target SDKs where they aren't defined.
         */
        public static final int EF_UNKNOWN = 0;

        /**
         * The maximum number of records supported by the elementary file.
         */
        public static final String MAX_RECORDS = "max_records";

        /**
         * The maximum length supported for the name of a record in the elementary file.
         */
        public static final String NAME_MAX_LENGTH = "name_max_length";

        /**
         * The maximum length supported for the phone number of a record in the elementary file.
         */
        public static final String PHONE_NUMBER_MAX_LENGTH = "phone_number_max_length";

        /**
         * Count of the number of records that are currently stored in the elementary file.
         */
        public static final String RECORD_COUNT = "record_count";

        /**
         * {@link SubscriptionInfo#getSimSlotIndex()} of the SIM for this row.
         */
        public static final String SLOT_INDEX = "slot_index";

        /**
         * {@link SubscriptionInfo#getSubscriptionId()} of the SIM for this row.
         */
        public static final String SUBSCRIPTION_ID = "subscription_id";

        private ElementaryFiles() {
        }
    }

    /**
     * Constants for the contact records on a SIM card.
     *
     * <h3 id="simrecords-data">Data</h3>
     * <p>
     * Data is stored in a specific elementary file on a specific SIM card and these are isolated
     * from each other. SIM cards are identified by their subscription ID. SIM cards may not support
     * all or even any of the elementary file types. A SIM will have constraints on
     * the values of the data that can be stored in each elementary file. The available SIMs,
     * their supported elementary file types and the constraints on the data can be discovered by
     * querying {@link ElementaryFiles#CONTENT_URI}. Each elementary file has a fixed capacity
     * for the number of records that may be stored. This can be determined from the value
     * of the {@link ElementaryFiles#MAX_RECORDS} column.
     * </p>
     * <p>
     * The {@link SimRecords#PHONE_NUMBER} column can only contain dialable characters and this
     * applies regardless of the SIM that is being used. See
     * {@link android.telephony.PhoneNumberUtils#isDialable(char)} for more details. Additionally
     * the phone number can contain at most {@link ElementaryFiles#PHONE_NUMBER_MAX_LENGTH}
     * characters. The {@link SimRecords#NAME} column can contain at most
     * {@link ElementaryFiles#NAME_MAX_LENGTH} bytes when it is encoded for storage on the SIM.
     * Encoding is done internally and so the name should be provided to these provider APIs as a
     * Java String but the number of bytes required to encode it for storage will vary depending on
     * the characters it contains. This length can be determined by calling
     * {@link SimRecords#getEncodedNameLength(ContentResolver, String)}.
     * </p>
     * <h3>Operations </h3>
     * <dl>
     * <dd><b>Insert</b></dd>
     * <p>
     * Only {@link ElementaryFiles#EF_ADN} supports inserts. {@link SimRecords#PHONE_NUMBER}
     * is a required column. If the value provided for this column is missing, null, empty
     * or violates the requirements discussed in the <a href="#simrecords-data">Data</a>
     * section above an {@link IllegalArgumentException} will be thrown. The
     * {@link SimRecords#NAME} column may be omitted but if provided and it violates any of
     * the requirements discussed in the <a href="#simrecords-data">Data</a> section above
     * an {@link IllegalArgumentException} will be thrown.
     * </p>
     * <p>
     * If an insert is not possible because the elementary file is full then an
     * {@link IllegalStateException} will be thrown.
     * </p>
     * <dd><b>Update</b></dd>
     * <p>
     * Updates can only be performed for individual records on {@link ElementaryFiles#EF_ADN}.
     * A specific record is referenced via the Uri returned by
     * {@link SimRecords#getItemUri(int, int, int)}. Updates have the same constraints and
     * behavior for the {@link SimRecords#PHONE_NUMBER} and {@link SimRecords#NAME} as insert.
     * However, in the case of update the {@link SimRecords#PHONE_NUMBER} may be omitted as
     * the existing record will already have a valid value.
     * </p>
     * <dd><b>Delete</b></dd>
     * <p>
     * Delete may only be performed for individual records on {@link ElementaryFiles#EF_ADN}.
     * Deleting records will free up space for use by future inserts.
     * </p>
     * <dd><b>Query</b></dd>
     * <p>
     * All the records stored on a specific elementary file can be read via a Uri returned by
     * {@link SimRecords#getContentUri(int, int)}. This query always returns all records; there
     * is no support for filtering via a selection. An individual record can be queried via a Uri
     * returned by {@link SimRecords#getItemUri(int, int, int)}. Queries will throw an
     * {@link IllegalArgumentException} when the SIM with the subscription ID or the elementary file
     * type are invalid or unavailable.
     * </p>
     * </dl>
     */
    public static final class SimRecords {
        /**
         * The MIME type of a CONTENT_URI subdirectory of a single SIM record.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/sim-contact_v2";

        /**
         * The MIME type of CONTENT_URI providing a directory of SIM records.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/sim-contact_v2";

        /**
         * The type of the elementary file the record is from.
         *
         * @see ElementaryFiles#EF_ADN
         * @see ElementaryFiles#EF_FDN
         * @see ElementaryFiles#EF_SDN
         */
        public static final String ELEMENTARY_FILE_TYPE = "elementary_file_type";

        /**
         * Value returned from {@link #getEncodedNameLength(ContentResolver, String)} when the name
         * length could not be determined because the name could not be encoded.
         */
        public static final int ERROR_NAME_UNSUPPORTED = -1;

        /**
         * The name for this record.
         *
         * <p>An {@link IllegalArgumentException} will be thrown by insert and update if this
         * exceeds the maximum supported length. Use
         * {@link #getEncodedNameLength(ContentResolver, String)} to check how long the name
         * will be after encoding.
         *
         * @see ElementaryFiles#NAME_MAX_LENGTH
         * @see #getEncodedNameLength(ContentResolver, String)
         */
        public static final String NAME = "name";

        /**
         * The phone number for this record.
         *
         * <p>Only dialable characters are supported.
         *
         * <p>An {@link IllegalArgumentException} will be thrown by insert and update if this
         * exceeds the maximum supported length or contains unsupported characters.
         *
         * @see ElementaryFiles#PHONE_NUMBER_MAX_LENGTH
         * @see android.telephony.PhoneNumberUtils#isDialable(char)
         */
        public static final String PHONE_NUMBER = "phone_number";

        /**
         * The 1-based offset of the record in the elementary file that contains it.
         *
         * <p>This can be used to access individual SIM records by appending it to the
         * elementary file URIs but it is not like a normal database ID because it is not
         * auto-incrementing and it is not unique across SIM cards or elementary files. Hence, care
         * should be taken when using it to ensure that it is applied to the correct SIM and EF.
         *
         * @see #getItemUri(int, int, int)
         */
        public static final String RECORD_NUMBER = "record_number";

        /**
         * The subscription ID of the SIM the record is from.
         *
         * @see SubscriptionInfo#getSubscriptionId()
         */
        public static final String SUBSCRIPTION_ID = "subscription_id";

        private SimRecords() {
        }
    }
}
