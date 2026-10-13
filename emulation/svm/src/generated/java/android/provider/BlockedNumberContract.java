package android.provider;

/**
 * <p>
 * The contract between the blockednumber provider and applications. Contains definitions for
 * the supported URIs and columns.
 * </p>
 *
 * <h3> Overview </h3>
 * <p>
 * The content provider exposes a table containing blocked numbers. The columns and URIs for
 * accessing this table are defined by the {@link BlockedNumbers} class. Messages, and calls from
 * blocked numbers are discarded by the platform. Notifications upon provider changes can be
 * received using a {@link android.database.ContentObserver}.
 * </p>
 * <p>
 * The platform will not block messages, and calls from emergency numbers as defined by
 * {@link android.telephony.PhoneNumberUtils#isEmergencyNumber(String)}. If the user contacts
 * emergency services, number blocking is disabled by the platform for a duration defined by
 * {@link android.telephony.CarrierConfigManager#KEY_DURATION_BLOCKING_DISABLED_AFTER_EMERGENCY_INT}.
 * </p>
 *
 * <h3> Permissions </h3>
 * <p>
 * Only the system, the default SMS application, and the default phone app
 * (See {@link android.telecom.TelecomManager#getDefaultDialerPackage()}), and carrier apps
 * (See {@link android.service.carrier.CarrierService}) can read, and write to the blockednumber
 * provider. However, {@link #canCurrentUserBlockNumbers(Context)} can be accessed by any
 * application.
 * </p>
 *
 * <h3> Data </h3>
 * <p>
 * Other than regular phone numbers, the blocked number provider can also store addresses (such
 * as email) from which a user can receive messages, and calls. The blocked numbers are stored
 * in the {@link BlockedNumbers#COLUMN_ORIGINAL_NUMBER} column. A normalized version of phone
 * numbers (if normalization is possible) is stored in {@link BlockedNumbers#COLUMN_E164_NUMBER}
 * column. The platform blocks calls, and messages from an address if it is present in in the
 * {@link BlockedNumbers#COLUMN_ORIGINAL_NUMBER} column or if the E164 version of the address
 * matches the {@link BlockedNumbers#COLUMN_E164_NUMBER} column.
 * </p>
 *
 * <h3> Operations </h3>
 * <dl>
 * <dt><b>Insert</b></dt>
 * <dd>
 * <p>
 * {@link BlockedNumbers#COLUMN_ORIGINAL_NUMBER} is a required column that needs to be populated.
 * Apps can optionally provide the {@link BlockedNumbers#COLUMN_E164_NUMBER} which is the phone
 * number's E164 representation. The provider automatically populates this column if the app does
 * not provide it. Note that this column is not populated if normalization fails or if the address
 * is not a phone number (eg: email).
 * <p>
 * Attempting to insert an existing blocked number (same
 * {@link BlockedNumbers#COLUMN_ORIGINAL_NUMBER} column) will result in replacing the existing
 * blocked number.
 * <p>
 * Examples:
 * <pre>
 * ContentValues values = new ContentValues();
 * values.put(BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "1234567890");
 * Uri uri = getContentResolver().insert(BlockedNumbers.CONTENT_URI, values);
 * </pre>
 * <pre>
 * ContentValues values = new ContentValues();
 * values.put(BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "1234567890");
 * values.put(BlockedNumbers.COLUMN_E164_NUMBER, "+11234567890");
 * Uri uri = getContentResolver().insert(BlockedNumbers.CONTENT_URI, values);
 * </pre>
 * <pre>
 * ContentValues values = new ContentValues();
 * values.put(BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "12345@abdcde.com");
 * Uri uri = getContentResolver().insert(BlockedNumbers.CONTENT_URI, values);
 * </pre>
 * </p>
 * </dd>
 * <dt><b>Update</b></dt>
 * <dd>
 * <p>
 * Updates are not supported. Use Delete, and Insert instead.
 * </p>
 * </dd>
 * <dt><b>Delete</b></dt>
 * <dd>
 * <p>
 * Deletions can be performed as follows:
 * <pre>
 * ContentValues values = new ContentValues();
 * values.put(BlockedNumbers.COLUMN_ORIGINAL_NUMBER, "1234567890");
 * Uri uri = getContentResolver().insert(BlockedNumbers.CONTENT_URI, values);
 * getContentResolver().delete(uri, null, null);
 * </pre>
 * To check if a particular number is blocked, use the method
 * {@link #isBlocked(Context, String)}.
 * </p>
 * </dd>
 * <dt><b>Query</b></dt>
 * <dd>
 * <p>
 * All blocked numbers can be enumerated as follows:
 * <pre>
 * Cursor c = getContentResolver().query(BlockedNumbers.CONTENT_URI,
 *          new String[]{BlockedNumbers.COLUMN_ID, BlockedNumbers.COLUMN_ORIGINAL_NUMBER,
 *          BlockedNumbers.COLUMN_E164_NUMBER}, null, null, null);
 * </pre>
 * </p>
 * </dd>
 * <dt><b>Unblock</b></dt>
 * <dd>
 * <p>
 * Use the method {@link #unblock(Context, String)} to unblock numbers.
 * </p>
 * </dd>
 *
 * <h3> Multi-user </h3>
 * <p>
 * Apps must use the method {@link #canCurrentUserBlockNumbers(Context)} before performing any
 * operation on the blocked number provider. If {@link #canCurrentUserBlockNumbers(Context)} returns
 * {@code false}, all operations on the provider will fail with a {@link SecurityException}. The
 * platform will block calls, and messages from numbers in the provider independent of the current
 * user.
 * </p>
 */
public final class BlockedNumberContract {
    /**
     * The authority for the blocked number provider
     */
    public static final String AUTHORITY = "com.android.blockednumber";

    private BlockedNumberContract() {
    }

    /**
     * Constants to interact with the blocked numbers list.
     */
    public static final class BlockedNumbers {
        /**
         * Phone number to block.  The system generates it from {@link #COLUMN_ORIGINAL_NUMBER}
         * by removing all formatting characters.
         * <p>Optional in {@code insert}.  When not specified, the system tries to generate it
         * assuming the current country. (Which will still be null if the number is not valid.)
         * <p>TYPE: String</p>
         */
        public static final String COLUMN_E164_NUMBER = "e164_number";

        /**
         * Auto-generated ID field which monotonically increases.
         * <p>TYPE: long</p>
         */
        public static final String COLUMN_ID = "_id";

        /**
         * Phone number to block.
         * <p>Must be specified in {@code insert}.
         * <p>TYPE: String</p>
         */
        public static final String COLUMN_ORIGINAL_NUMBER = "original_number";

        /**
         * The MIME type of a blocked phone number under {@link #CONTENT_URI}.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/blocked_number";

        /**
         * The MIME type of {@link #CONTENT_URI} itself providing a directory of blocked phone
         * numbers.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/blocked_number";

        private BlockedNumbers() {
        }
    }
}
