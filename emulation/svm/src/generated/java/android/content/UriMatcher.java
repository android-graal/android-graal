package android.content;

/**
 * Utility class to aid in matching URIs in content providers.
 *
 * <p>To use this class, build up a tree of <code>UriMatcher</code> objects.
 * For example:
 * <pre>
 *     private static final int PEOPLE = 1;
 *     private static final int PEOPLE_ID = 2;
 *     private static final int PEOPLE_PHONES = 3;
 *     private static final int PEOPLE_PHONES_ID = 4;
 *     private static final int PEOPLE_CONTACTMETHODS = 7;
 *     private static final int PEOPLE_CONTACTMETHODS_ID = 8;
 *
 *     private static final int DELETED_PEOPLE = 20;
 *
 *     private static final int PHONES = 9;
 *     private static final int PHONES_ID = 10;
 *     private static final int PHONES_FILTER = 14;
 *
 *     private static final int CONTACTMETHODS = 18;
 *     private static final int CONTACTMETHODS_ID = 19;
 *
 *     private static final int CALLS = 11;
 *     private static final int CALLS_ID = 12;
 *     private static final int CALLS_FILTER = 15;
 *
 *     private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
 *
 *     static
 *     {
 *         sURIMatcher.addURI("contacts", "people", PEOPLE);
 *         sURIMatcher.addURI("contacts", "people/#", PEOPLE_ID);
 *         sURIMatcher.addURI("contacts", "people/#/phones", PEOPLE_PHONES);
 *         sURIMatcher.addURI("contacts", "people/#/phones/#", PEOPLE_PHONES_ID);
 *         sURIMatcher.addURI("contacts", "people/#/contact_methods", PEOPLE_CONTACTMETHODS);
 *         sURIMatcher.addURI("contacts", "people/#/contact_methods/#", PEOPLE_CONTACTMETHODS_ID);
 *         sURIMatcher.addURI("contacts", "deleted_people", DELETED_PEOPLE);
 *         sURIMatcher.addURI("contacts", "phones", PHONES);
 *         sURIMatcher.addURI("contacts", "phones/filter/*", PHONES_FILTER);
 *         sURIMatcher.addURI("contacts", "phones/#", PHONES_ID);
 *         sURIMatcher.addURI("contacts", "contact_methods", CONTACTMETHODS);
 *         sURIMatcher.addURI("contacts", "contact_methods/#", CONTACTMETHODS_ID);
 *         sURIMatcher.addURI("call_log", "calls", CALLS);
 *         sURIMatcher.addURI("call_log", "calls/filter/*", CALLS_FILTER);
 *         sURIMatcher.addURI("call_log", "calls/#", CALLS_ID);
 *     }
 * </pre>
 * <p>Starting from API level {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR2}, paths can start
 *  with a leading slash.  For example:
 * <pre>
 *         sURIMatcher.addURI("contacts", "/people", PEOPLE);
 * </pre>
 * <p>Then when you need to match against a URI, call {@link #match}, providing
 * the URL that you have been given.  You can use the result to build a query,
 * return a type, insert or delete a row, or whatever you need, without duplicating
 * all of the if-else logic that you would otherwise need.  For example:
 * <pre>
 *     public String getType(Uri url)
 *     {
 *         int match = sURIMatcher.match(url);
 *         switch (match)
 *         {
 *             case PEOPLE:
 *                 return "vnd.android.cursor.dir/person";
 *             case PEOPLE_ID:
 *                 return "vnd.android.cursor.item/person";
 * ... snip ...
 *                 return "vnd.android.cursor.dir/snail-mail";
 *             case PEOPLE_ADDRESS_ID:
 *                 return "vnd.android.cursor.item/snail-mail";
 *             default:
 *                 return null;
 *         }
 *     }
 * </pre>
 * instead of:
 * <pre>
 *     public String getType(Uri url)
 *     {
 *         List<String> pathSegments = url.getPathSegments();
 *         if (pathSegments.size() >= 2) {
 *             if ("people".equals(pathSegments.get(1))) {
 *                 if (pathSegments.size() == 2) {
 *                     return "vnd.android.cursor.dir/person";
 *                 } else if (pathSegments.size() == 3) {
 *                     return "vnd.android.cursor.item/person";
 * ... snip ...
 *                     return "vnd.android.cursor.dir/snail-mail";
 *                 } else if (pathSegments.size() == 3) {
 *                     return "vnd.android.cursor.item/snail-mail";
 *                 }
 *             }
 *         }
 *         return null;
 *     }
 * </pre>
 */
public final class UriMatcher {
    public static final int NO_MATCH = -1;

    private UriMatcher() {
    }
}
