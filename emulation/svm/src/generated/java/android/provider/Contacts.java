package android.provider;

/**
 * The Contacts provider stores all information about contacts.
 *
 * @deprecated The APIs have been superseded by {@link ContactsContract}. The newer APIs allow
 * access multiple accounts and support aggregation of similar contacts. These APIs continue to
 * work but will only return data for the first Google account created, which matches the original
 * behavior.
 */
@Deprecated
public final class Contacts {
    /**
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final String AUTHORITY = "contacts";

    /**
     * Signifies an email address row that is stored in the ContactMethods table
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final int KIND_EMAIL = 1;

    /**
     * Signifies an IM address row that is stored in the ContactMethods table
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final int KIND_IM = 3;

    /**
     * Signifies an Organization row that is stored in the Organizations table
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final int KIND_ORGANIZATION = 4;

    /**
     * Signifies a Phone row that is stored in the Phones table
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final int KIND_PHONE = 5;

    /**
     * Signifies a postal address row that is stored in the ContactMethods table
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final int KIND_POSTAL = 2;

    private Contacts() {
    }

    /**
     * This table stores all non-phone contact methods and a reference to the
     * person that the contact method belongs to.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class ContactMethods implements BaseColumns, ContactMethodsColumns, PeopleColumns {
        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * {@link Contacts#KIND_EMAIL} entry.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_EMAIL_ITEM_TYPE = "vnd.android.cursor.item/email";

        /**
         * The MIME type of a {@link #CONTENT_EMAIL_URI} sub-directory of
         * multiple {@link Contacts#KIND_EMAIL} entries.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_EMAIL_TYPE = "vnd.android.cursor.dir/email";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * {@link Contacts#KIND_IM} entry.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_IM_ITEM_TYPE = "vnd.android.cursor.item/jabber-im";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
         * {@link Contacts#KIND_POSTAL} entry.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_POSTAL_ITEM_TYPE = "vnd.android.cursor.item/postal-address";

        /**
         * The MIME type of a {@link #CONTENT_EMAIL_URI} sub-directory of
         * multiple {@link Contacts#KIND_POSTAL} entries.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_POSTAL_TYPE = "vnd.android.cursor.dir/postal-address";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of
         * @deprecated see {@link android.provider.ContactsContract}
         * phones.
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact-methods";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        /**
         * The ID of the person this contact method is assigned to.
         * <P>Type: INTEGER (long)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PERSON_ID = "person";

        /**
         * The column with latitude data for postal locations
         * <P>Type: REAL</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String POSTAL_LOCATION_LATITUDE = "data";

        /**
         * The column with longitude data for postal locations
         * <P>Type: REAL</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String POSTAL_LOCATION_LONGITUDE = "aux_data";

        /**
         * The predefined IM protocol types. The protocol can either be non-present, one
         * of these types, or a free-form string. These cases are encoded in the AUX_DATA
         * column as:
         *  - null
         *  - pre:<an integer, one of the protocols below>
         *  - custom:<a string>
         *  @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_AIM = 0;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_GOOGLE_TALK = 5;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_ICQ = 6;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_JABBER = 7;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_MSN = 1;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_QQ = 4;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_SKYPE = 3;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final int PROTOCOL_YAHOO = 2;

        private ContactMethods() {
        }
    }

    /**
     * Columns from the ContactMethods table that other tables join into
     * themseleves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface ContactMethodsColumns {
        /**
         * Auxiliary data for the contact method.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String AUX_DATA = "aux_data";

        /**
         * The data for the contact method.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String DATA = "data";

        /**
         * Whether this is the primary organization
         * <P>Type: INTEGER (if set, non-0 means true)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String ISPRIMARY = "isprimary";

        /**
         * The kind of the the contact method. For example, email address,
         * postal address, etc.
         * <P>Type: INTEGER (one of the values below)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String KIND = "kind";

        /**
         * The user defined label for the the contact method.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String LABEL = "label";

        /**
         * The type of the contact method, must be one of the types below.
         * <P>Type: INTEGER (one of the values below)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String TYPE = "type";

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_CUSTOM = 0;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_HOME = 1;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_OTHER = 3;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_WORK = 2;
    }

    /**
     * The extensions for a person
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Extensions implements BaseColumns, ExtensionsColumns {
        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single
         * phone.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contact_extensions";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of
         * phones.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contact_extensions";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "person, name ASC";

        /**
         * The ID of the person this phone number is assigned to.
         * <P>Type: INTEGER (long)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PERSON_ID = "person";

        private Extensions() {
        }
    }

    /**
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface ExtensionsColumns {
        /**
         * The name of this extension. May not be null. There may be at most one row for each name.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NAME = "name";

        /**
         * The value of this extension. May not be null.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String VALUE = "value";
    }

    /**
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class GroupMembership implements BaseColumns, GroupsColumns {
        /**
         * The directory twig for this sub-table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_DIRECTORY = "groupmembership";

        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single
         * person group.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contactsgroupmembership";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of all
         * person groups.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contactsgroupmembership";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "group_id ASC";

        /**
         * The row id of the accounts group.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_ID = "group_id";

        /**
         * The account of the group.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_SYNC_ACCOUNT = "group_sync_account";

        /**
         * The account type of the group.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_SYNC_ACCOUNT_TYPE = "group_sync_account_type";

        /**
         * The sync id of the group.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_SYNC_ID = "group_sync_id";

        /**
         * The row id of the person.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PERSON_ID = "person";

        private GroupMembership() {
        }
    }

    /**
     * This table contains the groups for an account.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Groups implements BaseColumns, GroupsColumns {
        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single
         * group.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/contactsgroup";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of
         * groups.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/contactsgroup";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_ANDROID_STARRED = "Starred in Android";

        /**
         * The "My Contacts" system group.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String GROUP_MY_CONTACTS = "Contacts";

        private Groups() {
        }
    }

    /**
     * Columns from the groups table.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface GroupsColumns {
        /**
         * The group name.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NAME = "name";

        /**
         * Notes about the group.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NOTES = "notes";

        /**
         * Whether this group should be synced if the SYNC_EVERYTHING settings is false
         * for this group's account.
         * <P>Type: INTEGER (boolean)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String SHOULD_SYNC = "should_sync";

        /**
         * The ID of this group if it is a System Group, null otherwise.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String SYSTEM_ID = "system_id";
    }

    /**
     * Contains helper classes used to create or manage {@link android.content.Intent Intents}
     * that involve contacts.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Intents {
        /**
         * Starts an Activity that lets the user pick a contact to attach an image to.
         * After picking the contact it launches the image cropper in face detection mode.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String ATTACH_IMAGE = "com.android.contacts.action.ATTACH_IMAGE";

        /**
         * Used with {@link #SHOW_OR_CREATE_CONTACT} to specify an exact
         * description to be shown when prompting user about creating a new
         * contact.
         * <p>
         * Type: STRING
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String EXTRA_CREATE_DESCRIPTION = "com.android.contacts.action.CREATE_DESCRIPTION";

        /**
         * Used with {@link #SHOW_OR_CREATE_CONTACT} to force creating a new
         * contact if no matching contact found. Otherwise, default behavior is
         * to prompt user with dialog before creating.
         * <p>
         * Type: BOOLEAN
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String EXTRA_FORCE_CREATE = "com.android.contacts.action.FORCE_CREATE";

        /**
         * This is the intent that is fired when a search suggestion is clicked on.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String SEARCH_SUGGESTION_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_CLICKED";

        /**
         * This is the intent that is fired when a search suggestion for creating a contact
         * is clicked on.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String SEARCH_SUGGESTION_CREATE_CONTACT_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_CREATE_CONTACT_CLICKED";

        /**
         * This is the intent that is fired when a search suggestion for dialing a number
         * is clicked on.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String SEARCH_SUGGESTION_DIAL_NUMBER_CLICKED = "android.provider.Contacts.SEARCH_SUGGESTION_DIAL_NUMBER_CLICKED";

        /**
         * Takes as input a data URI with a mailto: or tel: scheme. If a single
         * contact exists with the given data it will be shown. If no contact
         * exists, a dialog will ask the user if they want to create a new
         * contact with the provided details filled in. If multiple contacts
         * share the data the user will be prompted to pick which contact they
         * want to view.
         * <p>
         * For <code>mailto:</code> URIs, the scheme specific portion must be a
         * raw email address, such as one built using
         * {@link Uri#fromParts(String, String, String)}.
         * <p>
         * For <code>tel:</code> URIs, the scheme specific portion is compared
         * to existing numbers using the standard caller ID lookup algorithm.
         * The number must be properly encoded, for example using
         * {@link Uri#fromParts(String, String, String)}.
         * <p>
         * Any extras from the {@link Insert} class will be passed along to the
         * create activity if there are no contacts to show.
         * <p>
         * Passing true for the {@link #EXTRA_FORCE_CREATE} extra will skip
         * prompting the user when the contact doesn't exist.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String SHOW_OR_CREATE_CONTACT = "com.android.contacts.action.SHOW_OR_CREATE_CONTACT";

        private Intents() {
        }

        /**
         * Convenience class that contains string constants used
         * to create contact {@link android.content.Intent Intents}.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final class Insert {
            /**
             * The action code to use when adding a contact
             *  @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String ACTION = "android.intent.action.INSERT";

            /**
             * The extra field for the contact company.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String COMPANY = "company";

            /**
             * The extra field for the contact email address.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String EMAIL = "email";

            /**
             * The extra field for the email isprimary flag.
             * <P>Type: boolean</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String EMAIL_ISPRIMARY = "email_isprimary";

            /**
             * The extra field for the contact email type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.ContactMethodsColumns ContactMethodsColumns}
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String EMAIL_TYPE = "email_type";

            /**
             * If present, forces a bypass of quick insert mode.
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String FULL_MODE = "full_mode";

            /**
             * The extra field for an IM handle.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String IM_HANDLE = "im_handle";

            /**
             * The extra field for the IM isprimary flag.
             * <P>Type: boolean</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String IM_ISPRIMARY = "im_isprimary";

            /**
             * The extra field for the IM protocol
             * <P>Type: the result of {@link Contacts.ContactMethods#encodePredefinedImProtocol}
             * or {@link Contacts.ContactMethods#encodeCustomImProtocol}.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String IM_PROTOCOL = "im_protocol";

            /**
             * The extra field for the contact job title.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String JOB_TITLE = "job_title";

            /**
             * The extra field for the contact name.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String NAME = "name";

            /**
             * The extra field for the contact notes.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String NOTES = "notes";

            /**
             * The extra field for the contact phone number.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String PHONE = "phone";

            /**
             * The extra field for the contact phonetic name.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String PHONETIC_NAME = "phonetic_name";

            /**
             * The extra field for the phone isprimary flag.
             * <P>Type: boolean</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String PHONE_ISPRIMARY = "phone_isprimary";

            /**
             * The extra field for the contact phone number type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.PhonesColumns PhonesColumns},
             *  or a string specifying a custom label.</P>
             *  @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String PHONE_TYPE = "phone_type";

            /**
             * The extra field for the contact postal address.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String POSTAL = "postal";

            /**
             * The extra field for the postal isprimary flag.
             * <P>Type: boolean</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String POSTAL_ISPRIMARY = "postal_isprimary";

            /**
             * The extra field for the contact postal address type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.ContactMethodsColumns ContactMethodsColumns}
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String POSTAL_TYPE = "postal_type";

            /**
             * The extra field for an optional second contact email address.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String SECONDARY_EMAIL = "secondary_email";

            /**
             * The extra field for an optional second contact email type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.ContactMethodsColumns ContactMethodsColumns}
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String SECONDARY_EMAIL_TYPE = "secondary_email_type";

            /**
             * The extra field for an optional second contact phone number.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String SECONDARY_PHONE = "secondary_phone";

            /**
             * The extra field for an optional second contact phone number type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.PhonesColumns PhonesColumns},
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String SECONDARY_PHONE_TYPE = "secondary_phone_type";

            /**
             * The extra field for an optional third contact email address.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String TERTIARY_EMAIL = "tertiary_email";

            /**
             * The extra field for an optional third contact email type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.ContactMethodsColumns ContactMethodsColumns}
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String TERTIARY_EMAIL_TYPE = "tertiary_email_type";

            /**
             * The extra field for an optional third contact phone number.
             * <P>Type: String</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String TERTIARY_PHONE = "tertiary_phone";

            /**
             * The extra field for an optional third contact phone number type.
             * <P>Type: Either an integer value from {@link android.provider.Contacts.PhonesColumns PhonesColumns},
             *  or a string specifying a custom label.</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String TERTIARY_PHONE_TYPE = "tertiary_phone_type";

            private Insert() {
            }
        }

        /**
         * Intents related to the Contacts app UI.
         * @deprecated Do not use. This is not supported.
         */
        @Deprecated
        public static final class UI {
            /**
             * Activity Action: Display a filtered list of contacts
             * <p>
             * Input: Extra field {@link #FILTER_TEXT_EXTRA_KEY} is the text to use for
             * filtering
             * <p>
             * Output: Nothing.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String FILTER_CONTACTS_ACTION = "com.android.contacts.action.FILTER_CONTACTS";

            /**
             * Used as an int extra field in {@link #FILTER_CONTACTS_ACTION}
             * intents to supply the text on which to filter.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String FILTER_TEXT_EXTRA_KEY = "com.android.contacts.extra.FILTER_TEXT";

            /**
             * When in LIST_GROUP_ACTION mode, this is the group to display.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String GROUP_NAME_EXTRA_KEY = "com.android.contacts.extra.GROUP";

            /**
             * The action for the all contacts list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_ALL_CONTACTS_ACTION = "com.android.contacts.action.LIST_ALL_CONTACTS";

            /**
             * The action for the contacts with phone numbers list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_CONTACTS_WITH_PHONES_ACTION = "com.android.contacts.action.LIST_CONTACTS_WITH_PHONES";

            /**
             * The action for the default contacts list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_DEFAULT = "com.android.contacts.action.LIST_DEFAULT";

            /**
             * The action for the frequent contacts list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_FREQUENT_ACTION = "com.android.contacts.action.LIST_FREQUENT";

            /**
             * The action for the contacts list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_GROUP_ACTION = "com.android.contacts.action.LIST_GROUP";

            /**
             * The action for the starred contacts list tab.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_STARRED_ACTION = "com.android.contacts.action.LIST_STARRED";

            /**
             * The action for the "strequent" contacts list tab. It first lists the starred
             * contacts in alphabetical order and then the frequent contacts in descending
             * order of the number of times they have been contacted.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String LIST_STREQUENT_ACTION = "com.android.contacts.action.LIST_STREQUENT";

            /**
             * A key for to be used as an intent extra to set the activity
             * title to a custom String value.
             * @deprecated Do not use. This is not supported.
             */
            @Deprecated
            public static final String TITLE_EXTRA_KEY = "com.android.contacts.extra.TITLE_EXTRA";

            private UI() {
            }
        }
    }

    /**
     * Columns from the Organizations table that other columns join into themselves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface OrganizationColumns {
        /**
         * The name of the company for this organization.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String COMPANY = "company";

        /**
         * Whether this is the primary organization
         * <P>Type: INTEGER (if set, non-0 means true)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String ISPRIMARY = "isprimary";

        /**
         * The user provided label, only used if TYPE is TYPE_CUSTOM.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String LABEL = "label";

        /**
         * The person this organization is tied to.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String PERSON_ID = "person";

        /**
         * The title within this organization.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String TITLE = "title";

        /**
         * The type of the organizations.
         * <P>Type: INTEGER (one of the constants below)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String TYPE = "type";

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_CUSTOM = 0;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_OTHER = 2;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_WORK = 1;
    }

    /**
     * A sub directory of a single person that contains all of their Phones.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Organizations implements BaseColumns, OrganizationColumns {
        /**
         * The directory twig for this sub-table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_DIRECTORY = "organizations";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "company, title, isprimary ASC";

        private Organizations() {
        }
    }

    /**
     * This table contains people.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class People implements BaseColumns,
            PeopleColumns,
            PhonesColumns,
            PresenceColumns {
        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single
         * person.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/person";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of
         * people.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/person";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        /**
         * The ID of the persons preferred email.
         * <P>Type: INTEGER (foreign key to contact_methods table on the
         * _ID field)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PRIMARY_EMAIL_ID = "primary_email";

        /**
         * The ID of the persons preferred organization.
         * <P>Type: INTEGER (foreign key to organizations table on the
         * _ID field)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PRIMARY_ORGANIZATION_ID = "primary_organization";

        /**
         * The ID of the persons preferred phone number.
         * <P>Type: INTEGER (foreign key to phones table on the _ID field)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PRIMARY_PHONE_ID = "primary_phone";

        private People() {
        }

        /**
         * A subdirectory of a single person that contains all of their
         * ContactMethods.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final class ContactMethods implements BaseColumns, ContactMethodsColumns, PeopleColumns {
            /**
             * The directory twig for this sub-table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String CONTENT_DIRECTORY = "contact_methods";

            /**
             * The default sort order for this table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "data ASC";

            private ContactMethods() {
            }
        }

        /**
         * The extensions for a person
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final class Extensions implements BaseColumns, ExtensionsColumns {
            /**
             * The directory twig for this sub-table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String CONTENT_DIRECTORY = "extensions";

            /**
             * The default sort order for this table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "name ASC";

            /**
             * The ID of the person this phone number is assigned to.
             * <P>Type: INTEGER (long)</P>
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String PERSON_ID = "person";

            private Extensions() {
            }
        }

        /**
         * A sub directory of a single person that contains all of their Phones.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final class Phones implements BaseColumns, PeopleColumns, PhonesColumns {
            /**
             * The directory twig for this sub-table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String CONTENT_DIRECTORY = "phones";

            /**
             * The default sort order for this table
             * @deprecated see {@link android.provider.ContactsContract}
             */
            @Deprecated
            public static final String DEFAULT_SORT_ORDER = "number ASC";

            private Phones() {
            }
        }
    }

    /**
     * Columns from the People table that other tables join into themselves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface PeopleColumns {
        /**
         * A custom ringtone associated with a person. Not always present.
         * <P>Type: TEXT (URI to the ringtone)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String CUSTOM_RINGTONE = "custom_ringtone";

        /**
         * The display name. If name is not null name, else if number is not null number,
         * else if email is not null email.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String DISPLAY_NAME = "display_name";

        /**
         * The last time a person was contacted.
         * <P>Type: INTEGER</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String LAST_TIME_CONTACTED = "last_time_contacted";

        /**
         * The person's name.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NAME = "name";

        /**
         * Notes about the person.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NOTES = "notes";

        /**
         * Phonetic equivalent of the person's name, in a locale-dependent
         * character set (e.g. hiragana for Japanese).
         * Used for pronunciation and/or collation in some languages.
         * <p>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String PHONETIC_NAME = "phonetic_name";

        /**
         * The server version of the photo
         * <P>Type: TEXT (the version number portion of the photo URI)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String PHOTO_VERSION = "photo_version";

        /**
         * Whether the person should always be sent to voicemail. Not always
         * present.
         * <P>Type: INTEGER (0 for false, 1 for true)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String SEND_TO_VOICEMAIL = "send_to_voicemail";

        /**
         * Is the contact starred?
         * <P>Type: INTEGER (boolean)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String STARRED = "starred";

        /**
         * The number of times a person has been contacted
         * <P>Type: INTEGER</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String TIMES_CONTACTED = "times_contacted";
    }

    /**
     * This table stores phone numbers and a reference to the person that the
     * contact method belongs to. Phone numbers are stored separately from
     * other contact methods to make caller ID lookup more efficient.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Phones implements BaseColumns, PeopleColumns, PhonesColumns {
        /**
         * The MIME type of a {@link #CONTENT_URI} subdirectory of a single
         * phone.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/phone";

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of
         * phones.
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/phone";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        /**
         * The ID of the person this phone number is assigned to.
         * <P>Type: INTEGER (long)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String PERSON_ID = "person";

        private Phones() {
        }
    }

    /**
     * Columns from the Phones table that other columns join into themselves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface PhonesColumns {
        /**
         * Whether this is the primary phone number
         * <P>Type: INTEGER (if set, non-0 means true)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String ISPRIMARY = "isprimary";

        /**
         * The user provided label for the phone number, only used if TYPE is TYPE_CUSTOM.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String LABEL = "label";

        /**
         * The phone number as the user entered it.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NUMBER = "number";

        /**
         * The normalized phone number
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String NUMBER_KEY = "number_key";

        /**
         * The type of the the phone number.
         * <P>Type: INTEGER (one of the constants below)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String TYPE = "type";

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_CUSTOM = 0;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_FAX_HOME = 5;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_FAX_WORK = 4;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_HOME = 1;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_MOBILE = 2;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_OTHER = 7;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_PAGER = 6;

        /**
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        int TYPE_WORK = 3;
    }

    /**
     * The photos over all of the people
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Photos implements BaseColumns, PhotosColumns {
        /**
         * The directory twig for this sub-table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_DIRECTORY = "photo";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "person ASC";

        private Photos() {
        }
    }

    /**
     * Columns from the Photos table that other columns join into themselves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface PhotosColumns {
        /**
         * The image data, or null if there is no image.
         * <P>Type: BLOB</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String DATA = "data";

        /**
         * non-zero if a download is required and the photo isn't marked as a bad resource.
         * You must specify this in the columns in order to use it in the where clause.
         * <P>Type: INTEGER(boolean)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String DOWNLOAD_REQUIRED = "download_required";

        /**
         * non-zero if this photo is known to exist on the server
         * <P>Type: INTEGER(boolean)</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String EXISTS_ON_SERVER = "exists_on_server";

        /**
         * The _SYNC_VERSION of the photo that was last downloaded
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String LOCAL_VERSION = "local_version";

        /**
         * The person this photo is associated with.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String PERSON_ID = "person";

        /**
         * Contains the description of the upload or download error from
         * the previous attempt. If null then the previous attempt succeeded.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String SYNC_ERROR = "sync_error";
    }

    /**
     * The IM presence columns with some contacts specific columns mixed in.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface PresenceColumns {
        @Deprecated
        int AVAILABLE = 5;

        @Deprecated
        int AWAY = 2;

        @Deprecated
        int DO_NOT_DISTURB = 4;

        @Deprecated
        int IDLE = 3;

        /**
         * The IM account for the local user that the presence data came from.
         * <P>Type: STRING</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String IM_ACCOUNT = "im_account";

        /**
         * The IM handle the presence item is for. The handle is scoped to
         * the {@link #IM_PROTOCOL}.
         * <P>Type: STRING</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String IM_HANDLE = "im_handle";

        /**
         * The IM service the presence is coming from. Formatted using either
         * {@link Contacts.ContactMethods#encodePredefinedImProtocol} or
         * {@link Contacts.ContactMethods#encodeCustomImProtocol}.
         * <P>Type: STRING</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String IM_PROTOCOL = "im_protocol";

        @Deprecated
        int INVISIBLE = 1;

        /**
         * Presence Status definition
         */
        @Deprecated
        int OFFLINE = 0;

        /**
         * The user defined status line.
         * <P>Type: TEXT</P>
         */
        @Deprecated
        String PRESENCE_CUSTOM_STATUS = "status";

        /**
         * The server defined status.
         * <P>Type: INTEGER (one of the values below)</P>
         */
        @Deprecated
        String PRESENCE_STATUS = "mode";

        /**
         * The priority, an integer, used by XMPP presence
         * <P>Type: INTEGER</P>
         */
        @Deprecated
        String PRIORITY = "priority";
    }

    /**
     * The settings over all of the people
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public static final class Settings implements BaseColumns, SettingsColumns {
        /**
         * The directory twig for this sub-table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String CONTENT_DIRECTORY = "settings";

        /**
         * The default sort order for this table
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String DEFAULT_SORT_ORDER = "key ASC";

        /**
         * A setting that is used to indicate if we should sync down all groups for the
         * specified account. For this setting the _SYNC_ACCOUNT column must be set.
         * If this isn't set then we will only sync the groups whose SHOULD_SYNC column
         * is set to true.
         * <p>
         * This is a boolean setting. It is true if it is set and it is anything other than the
         * emptry string or "0".
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        public static final String SYNC_EVERYTHING = "syncEverything";

        private Settings() {
        }
    }

    /**
     * Columns from the Settings table that other columns join into themselves.
     * @deprecated see {@link android.provider.ContactsContract}
     */
    @Deprecated
    public interface SettingsColumns {
        /**
         * The key of this setting.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String KEY = "key";

        /**
         * The value of this setting.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String VALUE = "value";

        /**
         * The _SYNC_ACCOUNT to which this setting corresponds. This may be null.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String _SYNC_ACCOUNT = "_sync_account";

        /**
         * The _SYNC_ACCOUNT_TYPE to which this setting corresponds. This may be null.
         * <P>Type: TEXT</P>
         * @deprecated see {@link android.provider.ContactsContract}
         */
        @Deprecated
        String _SYNC_ACCOUNT_TYPE = "_sync_account_type";
    }
}
