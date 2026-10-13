package android.provider;

/**
 * The ContentProvider contract for associating data with ana data array account.
 * This may be used by providers that want to store this data in a standard way.
 */
public final class SyncStateContract {
    private SyncStateContract() {
    }

    public interface Columns extends BaseColumns {
        /**
         * A reference to the name of the account to which this data belongs
         * <P>Type: STRING</P>
         */
        String ACCOUNT_NAME = "account_name";

        /**
         * A reference to the type of the account to which this data belongs
         * <P>Type: STRING</P>
         */
        String ACCOUNT_TYPE = "account_type";

        /**
         * The sync data associated with this account.
         * <P>Type: NONE</P>
         */
        String DATA = "data";
    }

    public static final class Constants implements Columns {
        public static final String CONTENT_DIRECTORY = "syncstate";

        private Constants() {
        }
    }
}
