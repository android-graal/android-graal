package android.content;

import android.os.Parcelable;

/**
 * An intent is an abstract description of an operation to be performed.  It
 * can be used with {@link Context#startActivity(Intent) startActivity} to
 * launch an {@link android.app.Activity},
 * {@link android.content.Context#sendBroadcast(Intent) broadcastIntent} to
 * send it to any interested {@link BroadcastReceiver BroadcastReceiver} components,
 * and {@link android.content.Context#startService} or
 * {@link android.content.Context#bindService} to communicate with a
 * background {@link android.app.Service}.
 *
 * <p>An Intent provides a facility for performing late runtime binding between the code in
 * different applications. Its most significant use is in the launching of activities, where it
 * can be thought of as the glue between activities. It is basically a passive data structure
 * holding an abstract description of an action to be performed.</p>
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For information about how to create and resolve intents, read the
 * <a href="{@docRoot}guide/topics/intents/intents-filters.html">Intents and Intent Filters</a>
 * developer guide.</p>
 * </div>
 *
 * <a name="IntentStructure"></a>
 * <h3>Intent Structure</h3>
 * <p>The primary pieces of information in an intent are:</p>
 *
 * <ul>
 *   <li> <p><b>action</b> -- The general action to be performed, such as
 *     {@link #ACTION_VIEW}, {@link #ACTION_EDIT}, {@link #ACTION_MAIN},
 *     etc.</p>
 *   </li>
 *   <li> <p><b>data</b> -- The data to operate on, such as a person record
 *     in the contacts database, expressed as a {@link android.net.Uri}.</p>
 *   </li>
 * </ul>
 *
 *
 * <p>Some examples of action/data pairs are:</p>
 *
 * <ul>
 *   <li> <p><b>{@link #ACTION_VIEW} <i>content://contacts/people/1</i></b> -- Display
 *     information about the person whose identifier is "1".</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_DIAL} <i>content://contacts/people/1</i></b> -- Display
 *     the phone dialer with the person filled in.</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_VIEW} <i>tel:123</i></b> -- Display
 *     the phone dialer with the given number filled in.  Note how the
 *     VIEW action does what is considered the most reasonable thing for
 *     a particular URI.</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_DIAL} <i>tel:123</i></b> -- Display
 *     the phone dialer with the given number filled in.</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_EDIT} <i>content://contacts/people/1</i></b> -- Edit
 *     information about the person whose identifier is "1".</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_VIEW} <i>content://contacts/people/</i></b> -- Display
 *     a list of people, which the user can browse through.  This example is a
 *     typical top-level entry into the Contacts application, showing you the
 *     list of people. Selecting a particular person to view would result in a
 *     new intent { <b>{@link #ACTION_VIEW} <i>content://contacts/people/N</i></b> }
 *     being used to start an activity to display that person.</p>
 *   </li>
 * </ul>
 *
 * <p>In addition to these primary attributes, there are a number of secondary
 * attributes that you can also include with an intent:</p>
 *
 * <ul>
 *     <li> <p><b>category</b> -- Gives additional information about the action
 *         to execute.  For example, {@link #CATEGORY_LAUNCHER} means it should
 *         appear in the Launcher as a top-level application, while
 *         {@link #CATEGORY_ALTERNATIVE} means it should be included in a list
 *         of alternative actions the user can perform on a piece of data.</p>
 *     <li> <p><b>type</b> -- Specifies an explicit type (a MIME type) of the
 *         intent data.  Normally the type is inferred from the data itself.
 *         By setting this attribute, you disable that evaluation and force
 *         an explicit type.</p>
 *     <li> <p><b>component</b> -- Specifies an explicit name of a component
 *         class to use for the intent.  Normally this is determined by looking
 *         at the other information in the intent (the action, data/type, and
 *         categories) and matching that with a component that can handle it.
 *         If this attribute is set then none of the evaluation is performed,
 *         and this component is used exactly as is.  By specifying this attribute,
 *         all of the other Intent attributes become optional.</p>
 *     <li> <p><b>extras</b> -- This is a {@link Bundle} of any additional information.
 *         This can be used to provide extended information to the component.
 *         For example, if we have a action to send an e-mail message, we could
 *         also include extra pieces of data here to supply a subject, body,
 *         etc.</p>
 * </ul>
 *
 * <p>Here are some examples of other operations you can specify as intents
 * using these additional parameters:</p>
 *
 * <ul>
 *   <li> <p><b>{@link #ACTION_MAIN} with category {@link #CATEGORY_HOME}</b> --
 *     Launch the home screen.</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_GET_CONTENT} with MIME type
 *     <i>{@link android.provider.Contacts.Phones#CONTENT_URI
 *     vnd.android.cursor.item/phone}</i></b>
 *     -- Display the list of people's phone numbers, allowing the user to
 *     browse through them and pick one and return it to the parent activity.</p>
 *   </li>
 *   <li> <p><b>{@link #ACTION_GET_CONTENT} with MIME type
 *     <i>*{@literal /}*</i> and category {@link #CATEGORY_OPENABLE}</b>
 *     -- Display all pickers for data that can be opened with
 *     {@link ContentResolver#openInputStream(Uri) ContentResolver.openInputStream()},
 *     allowing the user to pick one of them and then some data inside of it
 *     and returning the resulting URI to the caller.  This can be used,
 *     for example, in an e-mail application to allow the user to pick some
 *     data to include as an attachment.</p>
 *   </li>
 * </ul>
 *
 * <p>There are a variety of standard Intent action and category constants
 * defined in the Intent class, but applications can also define their own.
 * These strings use Java-style scoping, to ensure they are unique -- for
 * example, the standard {@link #ACTION_VIEW} is called
 * "android.intent.action.VIEW".</p>
 *
 * <p>Put together, the set of actions, data types, categories, and extra data
 * defines a language for the system allowing for the expression of phrases
 * such as "call john smith's cell".  As applications are added to the system,
 * they can extend this language by adding new actions, types, and categories, or
 * they can modify the behavior of existing phrases by supplying their own
 * activities that handle them.</p>
 *
 * <a name="IntentResolution"></a>
 * <h3>Intent Resolution</h3>
 *
 * <p>There are two primary forms of intents you will use.
 *
 * <ul>
 *     <li> <p><b>Explicit Intents</b> have specified a component (via
 *     {@link #setComponent} or {@link #setClass}), which provides the exact
 *     class to be run.  Often these will not include any other information,
 *     simply being a way for an application to launch various internal
 *     activities it has as the user interacts with the application.
 *
 *     <li> <p><b>Implicit Intents</b> have not specified a component;
 *     instead, they must include enough information for the system to
 *     determine which of the available components is best to run for that
 *     intent.
 * </ul>
 *
 * <p>When using implicit intents, given such an arbitrary intent we need to
 * know what to do with it. This is handled by the process of <em>Intent
 * resolution</em>, which maps an Intent to an {@link android.app.Activity},
 * {@link BroadcastReceiver}, or {@link android.app.Service} (or sometimes two or
 * more activities/receivers) that can handle it.</p>
 *
 * <p>The intent resolution mechanism basically revolves around matching an
 * Intent against all of the &lt;intent-filter&gt; descriptions in the
 * installed application packages.  (Plus, in the case of broadcasts, any {@link BroadcastReceiver}
 * objects explicitly registered with {@link Context#registerReceiver}.)  More
 * details on this can be found in the documentation on the {@link
 * IntentFilter} class.</p>
 *
 * <p>There are three pieces of information in the Intent that are used for
 * resolution: the action, type, and category.  Using this information, a query
 * is done on the {@link PackageManager} for a component that can handle the
 * intent. The appropriate component is determined based on the intent
 * information supplied in the <code>AndroidManifest.xml</code> file as
 * follows:</p>
 *
 * <ul>
 *     <li> <p>The <b>action</b>, if given, must be listed by the component as
 *         one it handles.</p>
 *     <li> <p>The <b>type</b> is retrieved from the Intent's data, if not
 *         already supplied in the Intent.  Like the action, if a type is
 *         included in the intent (either explicitly or implicitly in its
 *         data), then this must be listed by the component as one it handles.</p>
 *     <li> For data that is not a <code>content:</code> URI and where no explicit
 *         type is included in the Intent, instead the <b>scheme</b> of the
 *         intent data (such as <code>http:</code> or <code>mailto:</code>) is
 *         considered. Again like the action, if we are matching a scheme it
 *         must be listed by the component as one it can handle.
 *     <li> <p>The <b>categories</b>, if supplied, must <em>all</em> be listed
 *         by the activity as categories it handles.  That is, if you include
 *         the categories {@link #CATEGORY_LAUNCHER} and
 *         {@link #CATEGORY_ALTERNATIVE}, then you will only resolve to components
 *         with an intent that lists <em>both</em> of those categories.
 *         Activities will very often need to support the
 *         {@link #CATEGORY_DEFAULT} so that they can be found by
 *         {@link Context#startActivity Context.startActivity()}.</p>
 * </ul>
 *
 * <p>For example, consider the Note Pad sample application that
 * allows a user to browse through a list of notes data and view details about
 * individual items.  Text in italics indicates places where you would replace a
 * name with one specific to your own package.</p>
 *
 * <pre> &lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"
 *       package="<i>com.android.notepad</i>"&gt;
 *     &lt;application android:icon="@drawable/app_notes"
 *             android:label="@string/app_name"&gt;
 *
 *         &lt;provider class=".NotePadProvider"
 *                 android:authorities="<i>com.google.provider.NotePad</i>" /&gt;
 *
 *         &lt;activity class=".NotesList" android:label="@string/title_notes_list"&gt;
 *             &lt;intent-filter&gt;
 *                 &lt;action android:name="android.intent.action.MAIN" /&gt;
 *                 &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
 *             &lt;/intent-filter&gt;
 *             &lt;intent-filter&gt;
 *                 &lt;action android:name="android.intent.action.VIEW" /&gt;
 *                 &lt;action android:name="android.intent.action.EDIT" /&gt;
 *                 &lt;action android:name="android.intent.action.PICK" /&gt;
 *                 &lt;category android:name="android.intent.category.DEFAULT" /&gt;
 *                 &lt;data android:mimeType="vnd.android.cursor.dir/<i>vnd.google.note</i>" /&gt;
 *             &lt;/intent-filter&gt;
 *             &lt;intent-filter&gt;
 *                 &lt;action android:name="android.intent.action.GET_CONTENT" /&gt;
 *                 &lt;category android:name="android.intent.category.DEFAULT" /&gt;
 *                 &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 *             &lt;/intent-filter&gt;
 *         &lt;/activity&gt;
 *
 *         &lt;activity class=".NoteEditor" android:label="@string/title_note"&gt;
 *             &lt;intent-filter android:label="@string/resolve_edit"&gt;
 *                 &lt;action android:name="android.intent.action.VIEW" /&gt;
 *                 &lt;action android:name="android.intent.action.EDIT" /&gt;
 *                 &lt;category android:name="android.intent.category.DEFAULT" /&gt;
 *                 &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 *             &lt;/intent-filter&gt;
 *
 *             &lt;intent-filter&gt;
 *                 &lt;action android:name="android.intent.action.INSERT" /&gt;
 *                 &lt;category android:name="android.intent.category.DEFAULT" /&gt;
 *                 &lt;data android:mimeType="vnd.android.cursor.dir/<i>vnd.google.note</i>" /&gt;
 *             &lt;/intent-filter&gt;
 *
 *         &lt;/activity&gt;
 *
 *         &lt;activity class=".TitleEditor" android:label="@string/title_edit_title"
 *                 android:theme="@android:style/Theme.Dialog"&gt;
 *             &lt;intent-filter android:label="@string/resolve_title"&gt;
 *                 &lt;action android:name="<i>com.android.notepad.action.EDIT_TITLE</i>" /&gt;
 *                 &lt;category android:name="android.intent.category.DEFAULT" /&gt;
 *                 &lt;category android:name="android.intent.category.ALTERNATIVE" /&gt;
 *                 &lt;category android:name="android.intent.category.SELECTED_ALTERNATIVE" /&gt;
 *                 &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 *             &lt;/intent-filter&gt;
 *         &lt;/activity&gt;
 *
 *     &lt;/application&gt;
 * &lt;/manifest&gt;</pre>
 *
 * <p>The first activity,
 * <code>com.android.notepad.NotesList</code>, serves as our main
 * entry into the app.  It can do three things as described by its three intent
 * templates:
 * <ol>
 * <li><pre>
 * &lt;intent-filter&gt;
 *     &lt;action android:name="{@link #ACTION_MAIN android.intent.action.MAIN}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_LAUNCHER android.intent.category.LAUNCHER}" /&gt;
 * &lt;/intent-filter&gt;</pre>
 * <p>This provides a top-level entry into the NotePad application: the standard
 * MAIN action is a main entry point (not requiring any other information in
 * the Intent), and the LAUNCHER category says that this entry point should be
 * listed in the application launcher.</p>
 * <li><pre>
 * &lt;intent-filter&gt;
 *     &lt;action android:name="{@link #ACTION_VIEW android.intent.action.VIEW}" /&gt;
 *     &lt;action android:name="{@link #ACTION_EDIT android.intent.action.EDIT}" /&gt;
 *     &lt;action android:name="{@link #ACTION_PICK android.intent.action.PICK}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_DEFAULT android.intent.category.DEFAULT}" /&gt;
 *     &lt;data android:mimeType="vnd.android.cursor.dir/<i>vnd.google.note</i>" /&gt;
 * &lt;/intent-filter&gt;</pre>
 * <p>This declares the things that the activity can do on a directory of
 * notes.  The type being supported is given with the &lt;type&gt; tag, where
 * <code>vnd.android.cursor.dir/vnd.google.note</code> is a URI from which
 * a Cursor of zero or more items (<code>vnd.android.cursor.dir</code>) can
 * be retrieved which holds our note pad data (<code>vnd.google.note</code>).
 * The activity allows the user to view or edit the directory of data (via
 * the VIEW and EDIT actions), or to pick a particular note and return it
 * to the caller (via the PICK action).  Note also the DEFAULT category
 * supplied here: this is <em>required</em> for the
 * {@link Context#startActivity Context.startActivity} method to resolve your
 * activity when its component name is not explicitly specified.</p>
 * <li><pre>
 * &lt;intent-filter&gt;
 *     &lt;action android:name="{@link #ACTION_GET_CONTENT android.intent.action.GET_CONTENT}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_DEFAULT android.intent.category.DEFAULT}" /&gt;
 *     &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 * &lt;/intent-filter&gt;</pre>
 * <p>This filter describes the ability to return to the caller a note selected by
 * the user without needing to know where it came from.  The data type
 * <code>vnd.android.cursor.item/vnd.google.note</code> is a URI from which
 * a Cursor of exactly one (<code>vnd.android.cursor.item</code>) item can
 * be retrieved which contains our note pad data (<code>vnd.google.note</code>).
 * The GET_CONTENT action is similar to the PICK action, where the activity
 * will return to its caller a piece of data selected by the user.  Here,
 * however, the caller specifies the type of data they desire instead of
 * the type of data the user will be picking from.</p>
 * </ol>
 *
 * <p>Given these capabilities, the following intents will resolve to the
 * NotesList activity:</p>
 *
 * <ul>
 *     <li> <p><b>{ action=android.app.action.MAIN }</b> matches all of the
 *         activities that can be used as top-level entry points into an
 *         application.</p>
 *     <li> <p><b>{ action=android.app.action.MAIN,
 *         category=android.app.category.LAUNCHER }</b> is the actual intent
 *         used by the Launcher to populate its top-level list.</p>
 *     <li> <p><b>{ action=android.intent.action.VIEW
 *          data=content://com.google.provider.NotePad/notes }</b>
 *         displays a list of all the notes under
 *         "content://com.google.provider.NotePad/notes", which
 *         the user can browse through and see the details on.</p>
 *     <li> <p><b>{ action=android.app.action.PICK
 *          data=content://com.google.provider.NotePad/notes }</b>
 *         provides a list of the notes under
 *         "content://com.google.provider.NotePad/notes", from which
 *         the user can pick a note whose data URL is returned back to the caller.</p>
 *     <li> <p><b>{ action=android.app.action.GET_CONTENT
 *          type=vnd.android.cursor.item/vnd.google.note }</b>
 *         is similar to the pick action, but allows the caller to specify the
 *         kind of data they want back so that the system can find the appropriate
 *         activity to pick something of that data type.</p>
 * </ul>
 *
 * <p>The second activity,
 * <code>com.android.notepad.NoteEditor</code>, shows the user a single
 * note entry and allows them to edit it.  It can do two things as described
 * by its two intent templates:
 * <ol>
 * <li><pre>
 * &lt;intent-filter android:label="@string/resolve_edit"&gt;
 *     &lt;action android:name="{@link #ACTION_VIEW android.intent.action.VIEW}" /&gt;
 *     &lt;action android:name="{@link #ACTION_EDIT android.intent.action.EDIT}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_DEFAULT android.intent.category.DEFAULT}" /&gt;
 *     &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 * &lt;/intent-filter&gt;</pre>
 * <p>The first, primary, purpose of this activity is to let the user interact
 * with a single note, as decribed by the MIME type
 * <code>vnd.android.cursor.item/vnd.google.note</code>.  The activity can
 * either VIEW a note or allow the user to EDIT it.  Again we support the
 * DEFAULT category to allow the activity to be launched without explicitly
 * specifying its component.</p>
 * <li><pre>
 * &lt;intent-filter&gt;
 *     &lt;action android:name="{@link #ACTION_INSERT android.intent.action.INSERT}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_DEFAULT android.intent.category.DEFAULT}" /&gt;
 *     &lt;data android:mimeType="vnd.android.cursor.dir/<i>vnd.google.note</i>" /&gt;
 * &lt;/intent-filter&gt;</pre>
 * <p>The secondary use of this activity is to insert a new note entry into
 * an existing directory of notes.  This is used when the user creates a new
 * note: the INSERT action is executed on the directory of notes, causing
 * this activity to run and have the user create the new note data which
 * it then adds to the content provider.</p>
 * </ol>
 *
 * <p>Given these capabilities, the following intents will resolve to the
 * NoteEditor activity:</p>
 *
 * <ul>
 *     <li> <p><b>{ action=android.intent.action.VIEW
 *          data=content://com.google.provider.NotePad/notes/<var>{ID}</var> }</b>
 *         shows the user the content of note <var>{ID}</var>.</p>
 *     <li> <p><b>{ action=android.app.action.EDIT
 *          data=content://com.google.provider.NotePad/notes/<var>{ID}</var> }</b>
 *         allows the user to edit the content of note <var>{ID}</var>.</p>
 *     <li> <p><b>{ action=android.app.action.INSERT
 *          data=content://com.google.provider.NotePad/notes }</b>
 *         creates a new, empty note in the notes list at
 *         "content://com.google.provider.NotePad/notes"
 *         and allows the user to edit it.  If they keep their changes, the URI
 *         of the newly created note is returned to the caller.</p>
 * </ul>
 *
 * <p>The last activity,
 * <code>com.android.notepad.TitleEditor</code>, allows the user to
 * edit the title of a note.  This could be implemented as a class that the
 * application directly invokes (by explicitly setting its component in
 * the Intent), but here we show a way you can publish alternative
 * operations on existing data:</p>
 *
 * <pre>
 * &lt;intent-filter android:label="@string/resolve_title"&gt;
 *     &lt;action android:name="<i>com.android.notepad.action.EDIT_TITLE</i>" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_DEFAULT android.intent.category.DEFAULT}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_ALTERNATIVE android.intent.category.ALTERNATIVE}" /&gt;
 *     &lt;category android:name="{@link #CATEGORY_SELECTED_ALTERNATIVE android.intent.category.SELECTED_ALTERNATIVE}" /&gt;
 *     &lt;data android:mimeType="vnd.android.cursor.item/<i>vnd.google.note</i>" /&gt;
 * &lt;/intent-filter&gt;</pre>
 *
 * <p>In the single intent template here, we
 * have created our own private action called
 * <code>com.android.notepad.action.EDIT_TITLE</code> which means to
 * edit the title of a note.  It must be invoked on a specific note
 * (data type <code>vnd.android.cursor.item/vnd.google.note</code>) like the previous
 * view and edit actions, but here displays and edits the title contained
 * in the note data.
 *
 * <p>In addition to supporting the default category as usual, our title editor
 * also supports two other standard categories: ALTERNATIVE and
 * SELECTED_ALTERNATIVE.  Implementing
 * these categories allows others to find the special action it provides
 * without directly knowing about it, through the
 * {@link android.content.pm.PackageManager#queryIntentActivityOptions} method, or
 * more often to build dynamic menu items with
 * {@link android.view.Menu#addIntentOptions}.  Note that in the intent
 * template here was also supply an explicit name for the template
 * (via <code>android:label="@string/resolve_title"</code>) to better control
 * what the user sees when presented with this activity as an alternative
 * action to the data they are viewing.
 *
 * <p>Given these capabilities, the following intent will resolve to the
 * TitleEditor activity:</p>
 *
 * <ul>
 *     <li> <p><b>{ action=com.android.notepad.action.EDIT_TITLE
 *          data=content://com.google.provider.NotePad/notes/<var>{ID}</var> }</b>
 *         displays and allows the user to edit the title associated
 *         with note <var>{ID}</var>.</p>
 * </ul>
 *
 * <h3>Standard Activity Actions</h3>
 *
 * <p>These are the current standard actions that Intent defines for launching
 * activities (usually through {@link Context#startActivity}.  The most
 * important, and by far most frequently used, are {@link #ACTION_MAIN} and
 * {@link #ACTION_EDIT}.
 *
 * <ul>
 *     <li> {@link #ACTION_MAIN}
 *     <li> {@link #ACTION_VIEW}
 *     <li> {@link #ACTION_ATTACH_DATA}
 *     <li> {@link #ACTION_EDIT}
 *     <li> {@link #ACTION_PICK}
 *     <li> {@link #ACTION_CHOOSER}
 *     <li> {@link #ACTION_GET_CONTENT}
 *     <li> {@link #ACTION_DIAL}
 *     <li> {@link #ACTION_CALL}
 *     <li> {@link #ACTION_SEND}
 *     <li> {@link #ACTION_SENDTO}
 *     <li> {@link #ACTION_ANSWER}
 *     <li> {@link #ACTION_INSERT}
 *     <li> {@link #ACTION_DELETE}
 *     <li> {@link #ACTION_RUN}
 *     <li> {@link #ACTION_SYNC}
 *     <li> {@link #ACTION_PICK_ACTIVITY}
 *     <li> {@link #ACTION_SEARCH}
 *     <li> {@link #ACTION_WEB_SEARCH}
 *     <li> {@link #ACTION_FACTORY_TEST}
 * </ul>
 *
 * <h3>Standard Broadcast Actions</h3>
 *
 * <p>These are the current standard actions that Intent defines for receiving
 * broadcasts (usually through {@link Context#registerReceiver} or a
 * &lt;receiver&gt; tag in a manifest).
 *
 * <ul>
 *     <li> {@link #ACTION_TIME_TICK}
 *     <li> {@link #ACTION_TIME_CHANGED}
 *     <li> {@link #ACTION_TIMEZONE_CHANGED}
 *     <li> {@link #ACTION_BOOT_COMPLETED}
 *     <li> {@link #ACTION_PACKAGE_ADDED}
 *     <li> {@link #ACTION_PACKAGE_CHANGED}
 *     <li> {@link #ACTION_PACKAGE_REMOVED}
 *     <li> {@link #ACTION_PACKAGE_RESTARTED}
 *     <li> {@link #ACTION_PACKAGE_DATA_CLEARED}
 *     <li> {@link #ACTION_PACKAGES_SUSPENDED}
 *     <li> {@link #ACTION_PACKAGES_UNSUSPENDED}
 *     <li> {@link #ACTION_UID_REMOVED}
 *     <li> {@link #ACTION_BATTERY_CHANGED}
 *     <li> {@link #ACTION_POWER_CONNECTED}
 *     <li> {@link #ACTION_POWER_DISCONNECTED}
 *     <li> {@link #ACTION_SHUTDOWN}
 * </ul>
 *
 * <p class="note"><strong>Note: </strong>If your app targets Android 11
 * (API level 30) or higher, registering broadcast such as
 * {@link #ACTION_PACKAGES_SUSPENDED} that includes package details in the
 * extras receives a filtered list of apps or nothing. Learn more about how to
 * <a href="/training/basics/intents/package-visibility">manage package visibility</a>.
 * </p>
 *
 * <h3>Standard Categories</h3>
 *
 * <p>These are the current standard categories that can be used to further
 * clarify an Intent via {@link #addCategory}.
 *
 * <ul>
 *     <li> {@link #CATEGORY_DEFAULT}
 *     <li> {@link #CATEGORY_BROWSABLE}
 *     <li> {@link #CATEGORY_TAB}
 *     <li> {@link #CATEGORY_ALTERNATIVE}
 *     <li> {@link #CATEGORY_SELECTED_ALTERNATIVE}
 *     <li> {@link #CATEGORY_LAUNCHER}
 *     <li> {@link #CATEGORY_INFO}
 *     <li> {@link #CATEGORY_HOME}
 *     <li> {@link #CATEGORY_PREFERENCE}
 *     <li> {@link #CATEGORY_TEST}
 *     <li> {@link #CATEGORY_CAR_DOCK}
 *     <li> {@link #CATEGORY_DESK_DOCK}
 *     <li> {@link #CATEGORY_LE_DESK_DOCK}
 *     <li> {@link #CATEGORY_HE_DESK_DOCK}
 *     <li> {@link #CATEGORY_CAR_MODE}
 *     <li> {@link #CATEGORY_APP_MARKET}
 *     <li> {@link #CATEGORY_VR_HOME}
 * </ul>
 *
 * <h3>Standard Extra Data</h3>
 *
 * <p>These are the current standard fields that can be used as extra data via
 * {@link #putExtra}.
 *
 * <ul>
 *     <li> {@link #EXTRA_ALARM_COUNT}
 *     <li> {@link #EXTRA_BCC}
 *     <li> {@link #EXTRA_CC}
 *     <li> {@link #EXTRA_CHANGED_COMPONENT_NAME}
 *     <li> {@link #EXTRA_DATA_REMOVED}
 *     <li> {@link #EXTRA_DOCK_STATE}
 *     <li> {@link #EXTRA_DOCK_STATE_HE_DESK}
 *     <li> {@link #EXTRA_DOCK_STATE_LE_DESK}
 *     <li> {@link #EXTRA_DOCK_STATE_CAR}
 *     <li> {@link #EXTRA_DOCK_STATE_DESK}
 *     <li> {@link #EXTRA_DOCK_STATE_UNDOCKED}
 *     <li> {@link #EXTRA_DONT_KILL_APP}
 *     <li> {@link #EXTRA_EMAIL}
 *     <li> {@link #EXTRA_INITIAL_INTENTS}
 *     <li> {@link #EXTRA_INTENT}
 *     <li> {@link #EXTRA_KEY_EVENT}
 *     <li> {@link #EXTRA_ORIGINATING_URI}
 *     <li> {@link #EXTRA_PHONE_NUMBER}
 *     <li> {@link #EXTRA_REFERRER}
 *     <li> {@link #EXTRA_REMOTE_INTENT_TOKEN}
 *     <li> {@link #EXTRA_REPLACING}
 *     <li> {@link #EXTRA_SHORTCUT_ICON}
 *     <li> {@link #EXTRA_SHORTCUT_ICON_RESOURCE}
 *     <li> {@link #EXTRA_SHORTCUT_INTENT}
 *     <li> {@link #EXTRA_STREAM}
 *     <li> {@link #EXTRA_SHORTCUT_NAME}
 *     <li> {@link #EXTRA_SUBJECT}
 *     <li> {@link #EXTRA_TEMPLATE}
 *     <li> {@link #EXTRA_TEXT}
 *     <li> {@link #EXTRA_TITLE}
 *     <li> {@link #EXTRA_UID}
 *     <li> {@link #EXTRA_USER_INITIATED}
 * </ul>
 *
 * <h3>Flags</h3>
 *
 * <p>These are the possible flags that can be used in the Intent via
 * {@link #setFlags} and {@link #addFlags}.  See {@link #setFlags} for a list
 * of all possible flags.
 */
public final class Intent implements Parcelable {
    /**
     * <p>Broadcast Action: The user has switched the phone into or out of Airplane Mode. One or
     * more radios have been turned off or on. The intent will have the following extra value:</p>
     * <ul>
     *   <li><em>state</em> - A boolean value indicating whether Airplane Mode is on. If true,
     *   then cell radio and possibly other radios such as bluetooth or WiFi may have also been
     *   turned off</li>
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent by the system.</p>
     */
    public static final String ACTION_AIRPLANE_MODE_CHANGED = "android.intent.action.AIRPLANE_MODE";

    /**
     * Activity Action: List all available applications.
     * <p>Input: Nothing.
     * <p>Output: nothing.
     */
    public static final String ACTION_ALL_APPS = "android.intent.action.ALL_APPS";

    /**
     * Activity Action: Handle an incoming phone call.
     * <p>Input: nothing.
     * <p>Output: nothing.
     */
    public static final String ACTION_ANSWER = "android.intent.action.ANSWER";

    /**
     * Broadcast Action: Locale of a particular app has changed.
     *
     * <p class="note"> This broadcast is explicitly sent to the
     * {@link android.content.pm.InstallSourceInfo#getInstallingPackageName} installer
     *     of the app whose locale has changed.
     * <p class="note"> The broadcast could also be received by manifest-declared receivers with
     * {@code android.permission.READ_APP_SPECIFIC_LOCALES}
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * <p>Includes the following extras:
     * <ul>
     * <li>{@link #EXTRA_PACKAGE_NAME} is the name of the package for which locale changed.
     * <li>{@link #EXTRA_LOCALE_LIST} contains locales that are currently set for specified app
     * </ul>
     */
    public static final String ACTION_APPLICATION_LOCALE_CHANGED = "android.intent.action.APPLICATION_LOCALE_CHANGED";

    /**
     * An activity that provides a user interface for adjusting application preferences.
     * Optional but recommended settings for all applications which have settings.
     */
    public static final String ACTION_APPLICATION_PREFERENCES = "android.intent.action.APPLICATION_PREFERENCES";

    /**
     *  Broadcast Action: Sent after application restrictions are changed.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.</p>
     */
    public static final String ACTION_APPLICATION_RESTRICTIONS_CHANGED = "android.intent.action.APPLICATION_RESTRICTIONS_CHANGED";

    /**
     * Activity Action: The user pressed the "Report" button in the crash/ANR dialog.
     * This intent is delivered to the package which installed the application, usually
     * Google Play.
     * <p>Input: No data is specified. The bug report is passed in using
     * an {@link #EXTRA_BUG_REPORT} field.
     * <p>Output: Nothing.
     *
     * @see #EXTRA_BUG_REPORT
     */
    public static final String ACTION_APP_ERROR = "android.intent.action.APP_ERROR";

    /**
     * Activity Action: Perform assist action.
     * <p>
     * Input: {@link #EXTRA_ASSIST_PACKAGE}, {@link #EXTRA_ASSIST_CONTEXT}, can provide
     * additional optional contextual information about where the user was when they
     * requested the assist; {@link #EXTRA_REFERRER} may be set with additional referrer
     * information.
     * Output: nothing.
     */
    public static final String ACTION_ASSIST = "android.intent.action.ASSIST";

    /**
     * Used to indicate that some piece of data should be attached to some other
     * place.  For example, image data could be attached to a contact.  It is up
     * to the recipient to decide where the data should be attached; the intent
     * does not specify the ultimate destination.
     * <p>Input: {@link #getData} is URI of data to be attached.
     * <p>Output: nothing.
     */
    public static final String ACTION_ATTACH_DATA = "android.intent.action.ATTACH_DATA";

    /**
     * Activity action: Launch UI to manage auto-revoke state.
     *
     * This is equivalent to Intent#ACTION_APPLICATION_DETAILS_SETTINGS
     *
     * <p>
     * Input: {@link Intent#setData data} should be a {@code package}-scheme {@link Uri} with
     * a package name, whose auto-revoke state will be reviewed (mandatory).
     * E.g. {@code Uri.fromParts("package", packageName, null) }
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     */
    public static final String ACTION_AUTO_REVOKE_PERMISSIONS = "android.intent.action.AUTO_REVOKE_PERMISSIONS";

    /**
     * Broadcast Action:  This is a <em>sticky broadcast</em> containing the
     * charging state, level, and other information about the battery.
     * See {@link android.os.BatteryManager} for documentation on the
     * contents of the Intent.
     *
     * <p class="note">
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.  See {@link #ACTION_BATTERY_LOW},
     * {@link #ACTION_BATTERY_OKAY}, {@link #ACTION_POWER_CONNECTED},
     * and {@link #ACTION_POWER_DISCONNECTED} for distinct battery-related
     * broadcasts that are sent and can be received through manifest
     * receivers.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";

    /**
     * Broadcast Action:  Indicates low battery condition on the device.
     * This broadcast corresponds to the "Low battery warning" system dialog.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_BATTERY_LOW = "android.intent.action.BATTERY_LOW";

    /**
     * Broadcast Action:  Indicates the battery is now okay after being low.
     * This will be sent after {@link #ACTION_BATTERY_LOW} once the battery has
     * gone back up to an okay state.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";

    /**
     * Broadcast Action: This is broadcast once, after the user has finished
     * booting. It can be used to perform application-specific initialization,
     * such as installing alarms. You must hold the
     * {@link android.Manifest.permission#RECEIVE_BOOT_COMPLETED} permission in
     * order to receive this broadcast.
     * <p>
     * This broadcast is sent at boot by all devices (both with and without
     * direct boot support). Upon receipt of this broadcast, the user is
     * unlocked and both device-protected and credential-protected storage can
     * accessed safely.
     * <p>
     * If you need to run while the user is still locked (before they've entered
     * their lock pattern or PIN for the first time), you can listen for the
     * {@link #ACTION_LOCKED_BOOT_COMPLETED} broadcast.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     */
    public static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";

    /**
     * Activity Action: Show activity for reporting a bug.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_BUG_REPORT = "android.intent.action.BUG_REPORT";

    /**
     * Activity Action: Perform a call to someone specified by the data.
     * <p>Input: If nothing, an empty dialer is started; else {@link #getData}
     * is URI of a phone number to be dialed or a tel: URI of an explicit phone
     * number.
     * <p>Output: nothing.
     *
     * <p>Note: there will be restrictions on which applications can initiate a
     * call; most applications should use the {@link #ACTION_DIAL}.
     * <p>Note: this Intent <strong>cannot</strong> be used to call emergency
     * numbers.  Applications can <strong>dial</strong> emergency numbers using
     * {@link #ACTION_DIAL}, however.
     *
     * <p>Note: This Intent can only be used to dial call forwarding MMI codes if the application
     * using this intent is set as the default or system dialer. The system will treat any other
     * application using this Intent for the purpose of dialing call forwarding MMI codes as if the
     * {@link #ACTION_DIAL} Intent was used instead.
     *
     * <p>Note: An app filling the {@link android.app.role.RoleManager#ROLE_DIALER} role should use
     * {@link android.telecom.TelecomManager#placeCall(Uri, Bundle)} to place calls rather than
     * relying on this intent.
     *
     * <p>Note: If your app targets {@link android.os.Build.VERSION_CODES#M M}
     * or higher and declares as using the {@link android.Manifest.permission#CALL_PHONE}
     * permission which is not granted, then attempting to use this action will
     * result in a {@link java.lang.SecurityException}.
     */
    public static final String ACTION_CALL = "android.intent.action.CALL";

    /**
     * Activity Action: The user pressed the "call" button to go to the dialer
     * or other appropriate UI for placing a call.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_CALL_BUTTON = "android.intent.action.CALL_BUTTON";

    /**
     * Broadcast Action:  The "Camera Button" was pressed.  Includes a single
     * extra field, {@link #EXTRA_KEY_EVENT}, containing the key event that
     * caused the broadcast.
     */
    public static final String ACTION_CAMERA_BUTTON = "android.intent.action.CAMERA_BUTTON";

    /**
     * Activity Action: Main entry point for carrier setup apps.
     * <p>Carrier apps that provide an implementation for this action may be invoked to configure
     * carrier service and typically require
     * {@link android.telephony.TelephonyManager#hasCarrierPrivileges() carrier privileges} to
     * fulfill their duties.
     */
    public static final String ACTION_CARRIER_SETUP = "android.intent.action.CARRIER_SETUP";

    /**
     * Activity Action: Display an activity chooser, allowing the user to pick
     * what they want to before proceeding.  This can be used as an alternative
     * to the standard activity picker that is displayed by the system when
     * you try to start an activity with multiple possible matches, with these
     * differences in behavior:
     * <ul>
     * <li>You can specify the title that will appear in the activity chooser.
     * <li>The user does not have the option to make one of the matching
     * activities a preferred activity, and all possible activities will
     * always be shown even if one of them is currently marked as the
     * preferred activity.
     * </ul>
     * <p>
     * This action should be used when the user will naturally expect to
     * select an activity in order to proceed.  An example if when not to use
     * it is when the user clicks on a "mailto:" link.  They would naturally
     * expect to go directly to their mail app, so startActivity() should be
     * called directly: it will
     * either launch the current preferred app, or put up a dialog allowing the
     * user to pick an app to use and optionally marking that as preferred.
     * <p>
     * In contrast, if the user is selecting a menu item to send a picture
     * they are viewing to someone else, there are many different things they
     * may want to do at this point: send it through e-mail, upload it to a
     * web service, etc.  In this case the CHOOSER action should be used, to
     * always present to the user a list of the things they can do, with a
     * nice title given by the caller such as "Send this photo with:".
     * <p>
     * If you need to grant URI permissions through a chooser, you must specify
     * the permissions to be granted on the ACTION_CHOOSER Intent
     * <em>in addition</em> to the EXTRA_INTENT inside.  This means using
     * {@link #setClipData} to specify the URIs to be granted as well as
     * {@link #FLAG_GRANT_READ_URI_PERMISSION} and/or
     * {@link #FLAG_GRANT_WRITE_URI_PERMISSION} as appropriate.
     * <p>
     * As a convenience, an Intent of this form can be created with the
     * {@link #createChooser} function.
     * <p>
     * Input: No data should be specified.  get*Extra must have
     * a {@link #EXTRA_INTENT} field containing the Intent being executed,
     * and can optionally have a {@link #EXTRA_TITLE} field containing the
     * title text to display in the chooser.
     * <p>
     * Output: Depends on the protocol of {@link #EXTRA_INTENT}.
     */
    public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";

    /**
     * Broadcast Action: This is broadcast when a user action should request a
     * temporary system dialog to dismiss.  Some examples of temporary system
     * dialogs are the notification window-shade and the recent tasks dialog.
     *
     * @deprecated This intent is deprecated for third-party applications starting from Android
     *     {@link Build.VERSION_CODES#S} for security reasons. Unauthorized usage by applications
     *     will result in the broadcast intent being dropped for apps targeting API level less than
     *     {@link Build.VERSION_CODES#S} and in a {@link SecurityException} for apps targeting SDK
     *     level {@link Build.VERSION_CODES#S} or higher. Instrumentation initiated from the shell
     *     (eg. tests) is still able to use the intent. The platform will automatically collapse
     *     the proper system dialogs in the proper use-cases. For all others, the user is the one in
     *     control of closing dialogs.
     *
     * @see AccessibilityService#GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE
     */
    @Deprecated
    public static final String ACTION_CLOSE_SYSTEM_DIALOGS = "android.intent.action.CLOSE_SYSTEM_DIALOGS";

    /**
     * Broadcast Action: The current device {@link android.content.res.Configuration}
     * (orientation, locale, etc) has changed.  When such a change happens, the
     * UIs (view hierarchy) will need to be rebuilt based on this new
     * information; for the most part, applications don't need to worry about
     * this, because the system will take care of stopping and restarting the
     * application to make sure it sees the new changes.  Some system code that
     * can not be restarted will need to watch for this action and handle it
     * appropriately.
     *
     * <p class="note">
     * You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @see android.content.res.Configuration
     */
    public static final String ACTION_CONFIGURATION_CHANGED = "android.intent.action.CONFIGURATION_CHANGED";

    /**
     * Activity Action: Allow the user to create a new document. When invoked,
     * the system will display the various {@link DocumentsProvider} instances
     * installed on the device, letting the user navigate through them. The
     * returned document may be a newly created document with no content, or it
     * may be an existing document with the requested MIME type.
     * <p>
     * Each document is represented as a {@code content://} URI backed by a
     * {@link DocumentsProvider}, which can be opened as a stream with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}, or queried for
     * {@link android.provider.DocumentsContract.Document} metadata.
     * <p>
     * Callers must indicate the concrete MIME type of the document being
     * created by setting {@link #setType(String)}. This MIME type cannot be
     * changed after the document is created.
     * <p>
     * Callers can provide an initial display name through {@link #EXTRA_TITLE},
     * but the user may change this value before creating the file.
     * <p>
     * Callers must include {@link #CATEGORY_OPENABLE} in the Intent to obtain
     * URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}.
     * <p>
     * Callers can set a document URI through
     * {@link DocumentsContract#EXTRA_INITIAL_URI} to indicate the initial
     * location of documents navigator. System will do its best to launch the
     * navigator in the specified document if it's a folder, or the folder that
     * contains the specified document if not.
     * <p>
     * Output: The URI of the item that was created. This must be a
     * {@code content://} URI so that any receiver can access it.
     *
     * @see DocumentsContract
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     */
    public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";

    /**
     * Activity Action: Starts a note-taking activity that can be used to create a note. This action
     * can be used to start an activity on the lock screen. Activity should ensure to appropriately
     * handle privacy sensitive data and features when launched on the lock screen. See
     * {@link android.app.KeyguardManager} for lock screen checks.
     */
    public static final String ACTION_CREATE_NOTE = "android.intent.action.CREATE_NOTE";

    /**
     * Activity Action: Creates a reminder.
     * <p>Input: {@link #EXTRA_TITLE} The title of the reminder that will be shown to the user.
     * {@link #EXTRA_TEXT} The reminder text that will be shown to the user. The intent should at
     * least specify a title or a text. {@link #EXTRA_TIME} The time when the reminder will
     * be shown to the user. The time is specified in milliseconds since the Epoch (optional).
     * </p>
     * <p>Output: Nothing.</p>
     *
     * @see #EXTRA_TITLE
     * @see #EXTRA_TEXT
     * @see #EXTRA_TIME
     */
    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";

    /**
     * Activity Action: Creates a shortcut.
     * <p>Input: Nothing.</p>
     * <p>Output: An Intent representing the {@link android.content.pm.ShortcutInfo} result.</p>
     * <p>For compatibility with older versions of android the intent may also contain three
     * extras: SHORTCUT_INTENT (value: Intent), SHORTCUT_NAME (value: String),
     * and SHORTCUT_ICON (value: Bitmap) or SHORTCUT_ICON_RESOURCE
     * (value: ShortcutIconResource).</p>
     *
     * @see android.content.pm.ShortcutManager#createShortcutResultIntent
     * @see #EXTRA_SHORTCUT_INTENT
     * @see #EXTRA_SHORTCUT_NAME
     * @see #EXTRA_SHORTCUT_ICON
     * @see #EXTRA_SHORTCUT_ICON_RESOURCE
     * @see android.content.Intent.ShortcutIconResource
     */
    public static final String ACTION_CREATE_SHORTCUT = "android.intent.action.CREATE_SHORTCUT";

    /**
     * Broadcast Action: The date has changed.
     */
    public static final String ACTION_DATE_CHANGED = "android.intent.action.DATE_CHANGED";

    /**
     * A synonym for {@link #ACTION_VIEW}, the "standard" action that is
     * performed on a piece of data.
     */
    public static final String ACTION_DEFAULT = "android.intent.action.VIEW";

    /**
     * Activity Action: Define the meaning of the selected word(s).
     * <p>
     * Input: {@link #EXTRA_TEXT getCharSequence(EXTRA_TEXT)} is the text to define.
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_DEFINE = "android.intent.action.DEFINE";

    /**
     * Activity Action: Delete the given data from its container.
     * <p>Input: {@link #getData} is URI of data to be deleted.
     * <p>Output: nothing.
     */
    public static final String ACTION_DELETE = "android.intent.action.DELETE";

    /**
     * Broadcast Action: A sticky broadcast that indicates low storage space
     * condition on the device
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     *
     * @deprecated if your app targets {@link android.os.Build.VERSION_CODES#O}
     *             or above, this broadcast will no longer be delivered to any
     *             {@link BroadcastReceiver} defined in your manifest. Instead,
     *             apps are strongly encouraged to use the improved
     *             {@link Context#getCacheDir()} behavior so the system can
     *             automatically free up storage when needed.
     */
    @Deprecated
    public static final String ACTION_DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";

    /**
     * Broadcast Action: Indicates low storage space condition on the device no
     * longer exists
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     *
     * @deprecated if your app targets {@link android.os.Build.VERSION_CODES#O}
     *             or above, this broadcast will no longer be delivered to any
     *             {@link BroadcastReceiver} defined in your manifest. Instead,
     *             apps are strongly encouraged to use the improved
     *             {@link Context#getCacheDir()} behavior so the system can
     *             automatically free up storage when needed.
     */
    @Deprecated
    public static final String ACTION_DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";

    /**
     * Activity Action: Dial a number as specified by the data.  This shows a
     * UI with the number being dialed, allowing the user to explicitly
     * initiate the call.
     * <p>Input: If nothing, an empty dialer is started; else {@link #getData}
     * is URI of a phone number to be dialed or a tel: URI of an explicit phone
     * number.
     * <p>Output: nothing.
     */
    public static final String ACTION_DIAL = "android.intent.action.DIAL";

    /**
     * Broadcast Action:  A sticky broadcast for changes in the physical
     * docking state of the device.
     *
     * <p>The intent will have the following extra values:
     * <ul>
     *   <li><em>{@link #EXTRA_DOCK_STATE}</em> - the current dock
     *       state, indicating which dock the device is physically in.</li>
     * </ul>
     * <p>This is intended for monitoring the current physical dock state.
     * See {@link android.app.UiModeManager} for the normal API dealing with
     * dock mode changes.
     */
    public static final String ACTION_DOCK_EVENT = "android.intent.action.DOCK_EVENT";

    /**
     * Broadcast Action: Sent after the system starts dreaming.
     *
     * <p class="note">This is a protected intent that can only be sent by the system.
     * It is only sent to registered receivers.</p>
     */
    public static final String ACTION_DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";

    /**
     * Broadcast Action: Sent after the system stops dreaming.
     *
     * <p class="note">This is a protected intent that can only be sent by the system.
     * It is only sent to registered receivers.</p>
     */
    public static final String ACTION_DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";

    /**
     * Activity Action: Provide explicit editable access to the given data.
     * <p>Input: {@link #getData} is URI of data to be edited.
     * <p>Output: nothing.
     */
    public static final String ACTION_EDIT = "android.intent.action.EDIT";

    /**
     * Broadcast Action: Resources for a set of packages (which were
     * previously unavailable) are currently
     * available since the media on which they exist is available.
     * The extra data {@link #EXTRA_CHANGED_PACKAGE_LIST} contains a
     * list of packages whose availability changed.
     * The extra data {@link #EXTRA_CHANGED_UID_LIST} contains a
     * list of uids of packages whose availability changed.
     * Note that the
     * packages in this list do <em>not</em> receive this broadcast.
     * The specified set of packages are now available on the system.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages
     * whose resources(were previously unavailable) are currently available.
     * {@link #EXTRA_CHANGED_UID_LIST} is the set of uids of the
     * packages whose resources(were previously unavailable)
     * are  currently available.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_EXTERNAL_APPLICATIONS_AVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE";

    /**
     * Broadcast Action: Resources for a set of packages are currently
     * unavailable since the media on which they exist is unavailable.
     * The extra data {@link #EXTRA_CHANGED_PACKAGE_LIST} contains a
     * list of packages whose availability changed.
     * The extra data {@link #EXTRA_CHANGED_UID_LIST} contains a
     * list of uids of packages whose availability changed.
     * The specified set of packages can no longer be
     * launched and are practically unavailable on the system.
     * <p>Inclues the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages
     * whose resources are no longer available.
     * {@link #EXTRA_CHANGED_UID_LIST} is the set of packages
     * whose resources are no longer available.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE";

    /**
     *  Activity Action: Main entry point for factory tests.  Only used when
     *  the device is booting in factory test node.  The implementing package
     *  must be installed in the system image.
     *  <p>Input: nothing
     *  <p>Output: nothing
     */
    public static final String ACTION_FACTORY_TEST = "android.intent.action.FACTORY_TEST";

    /**
     * Activity Action: Allow the user to select a particular kind of data and
     * return it.  This is different than {@link #ACTION_PICK} in that here we
     * just say what kind of data is desired, not a URI of existing data from
     * which the user can pick.  An ACTION_GET_CONTENT could allow the user to
     * create the data as it runs (for example taking a picture or recording a
     * sound), let them browse over the web and download the desired data,
     * etc.
     * <p>
     * There are two main ways to use this action: if you want a specific kind
     * of data, such as a person contact, you set the MIME type to the kind of
     * data you want and launch it with {@link Context#startActivity(Intent)}.
     * The system will then launch the best application to select that kind
     * of data for you.
     * <p>
     * You may also be interested in any of a set of types of content the user
     * can pick.  For example, an e-mail application that wants to allow the
     * user to add an attachment to an e-mail message can use this action to
     * bring up a list of all of the types of content the user can attach.
     * <p>
     * In this case, you should wrap the GET_CONTENT intent with a chooser
     * (through {@link #createChooser}), which will give the proper interface
     * for the user to pick how to send your data and allow you to specify
     * a prompt indicating what they are doing.  You will usually specify a
     * broad MIME type (such as image/* or {@literal *}/*), resulting in a
     * broad range of content types the user can select from.
     * <p>
     * When using such a broad GET_CONTENT action, it is often desirable to
     * only pick from data that can be represented as a stream.  This is
     * accomplished by requiring the {@link #CATEGORY_OPENABLE} in the Intent.
     * <p>
     * Callers can optionally specify {@link #EXTRA_LOCAL_ONLY} to request that
     * the launched content chooser only returns results representing data that
     * is locally available on the device.  For example, if this extra is set
     * to true then an image picker should not show any pictures that are available
     * from a remote server but not already on the local device (thus requiring
     * they be downloaded when opened).
     * <p>
     * If the caller can handle multiple returned items (the user performing
     * multiple selection), then it can specify {@link #EXTRA_ALLOW_MULTIPLE}
     * to indicate this.
     * <p>
     * Input: {@link #getType} is the desired MIME type to retrieve.  Note
     * that no URI is supplied in the intent, as there are no constraints on
     * where the returned data originally comes from.  You may also include the
     * {@link #CATEGORY_OPENABLE} if you can only accept data that can be
     * opened as a stream.  You may use {@link #EXTRA_LOCAL_ONLY} to limit content
     * selection to local data.  You may use {@link #EXTRA_ALLOW_MULTIPLE} to
     * allow the user to select multiple items.
     * <p>
     * Output: The URI of the item that was picked.  This must be a content:
     * URI so that any receiver can access it.
     */
    public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";

    /**
     * Broadcast to a specific application to query any supported restrictions to impose
     * on restricted users. The broadcast intent contains an extra
     * {@link #EXTRA_RESTRICTIONS_BUNDLE} with the currently persisted
     * restrictions as a Bundle of key/value pairs. The value types can be Boolean, String or
     * String[] depending on the restriction type.<p/>
     * The response should contain an extra {@link #EXTRA_RESTRICTIONS_LIST},
     * which is of type <code>ArrayList&lt;RestrictionEntry&gt;</code>. It can also
     * contain an extra {@link #EXTRA_RESTRICTIONS_INTENT}, which is of type <code>Intent</code>.
     * The activity specified by that intent will be launched for a result which must contain
     * one of the extras {@link #EXTRA_RESTRICTIONS_LIST} or {@link #EXTRA_RESTRICTIONS_BUNDLE}.
     * The keys and values of the returned restrictions will be persisted.
     * @see RestrictionEntry
     */
    public static final String ACTION_GET_RESTRICTION_ENTRIES = "android.intent.action.GET_RESTRICTION_ENTRIES";

    /**
     * Broadcast Action: A GTalk connection has been established.
     */
    public static final String ACTION_GTALK_SERVICE_CONNECTED = "android.intent.action.GTALK_CONNECTED";

    /**
     * Broadcast Action: A GTalk connection has been disconnected.
     */
    public static final String ACTION_GTALK_SERVICE_DISCONNECTED = "android.intent.action.GTALK_DISCONNECTED";

    /**
     * Broadcast Action: Wired Headset plugged in or unplugged.
     *
     * Same as {@link android.media.AudioManager#ACTION_HEADSET_PLUG}, to be consulted for value
     *   and documentation.
     * <p>If the minimum SDK version of your application is
     * {@link android.os.Build.VERSION_CODES#LOLLIPOP}, it is recommended to refer
     * to the <code>AudioManager</code> constant in your receiver registration code instead.
     */
    public static final String ACTION_HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";

    /**
     * Broadcast Action: An input method has been changed.
     */
    public static final String ACTION_INPUT_METHOD_CHANGED = "android.intent.action.INPUT_METHOD_CHANGED";

    /**
     * Activity Action: Insert an empty item into the given container.
     * <p>Input: {@link #getData} is URI of the directory (vnd.android.cursor.dir/*)
     * in which to place the data.
     * <p>Output: URI of the new data that was created.
     */
    public static final String ACTION_INSERT = "android.intent.action.INSERT";

    /**
     * Activity Action: Pick an existing item, or insert a new item, and then edit it.
     * <p>Input: {@link #getType} is the desired MIME type of the item to create or edit.
     * The extras can contain type specific data to pass through to the editing/creating
     * activity.
     * <p>Output: The URI of the item that was picked.  This must be a content:
     * URI so that any receiver can access it.
     */
    public static final String ACTION_INSERT_OR_EDIT = "android.intent.action.INSERT_OR_EDIT";

    /**
     * Activity Action: Activity to handle split installation failures.
     * <p>Splits may be installed dynamically. This happens when an Activity is launched,
     * but the split that contains the application isn't installed. When a split is
     * installed in this manner, the containing package usually doesn't know this is
     * happening. However, if an error occurs during installation, the containing
     * package can define a single activity handling this action to deal with such
     * failures.
     * <p>The activity handling this action must be in the base package.
     * <p>
     * Input: {@link #EXTRA_INTENT} the original intent that started split installation.
     * {@link #EXTRA_SPLIT_NAME} the name of the split that failed to be installed.
     */
    public static final String ACTION_INSTALL_FAILURE = "android.intent.action.INSTALL_FAILURE";

    /**
     * Activity Action: Launch application installer.
     * <p>
     * Input: The data must be a content: URI at which the application
     * can be retrieved.  As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN_MR1},
     * you can also use "package:<package-name>" to install an application for the
     * current user that is already installed for another user. You can optionally supply
     * {@link #EXTRA_INSTALLER_PACKAGE_NAME}, {@link #EXTRA_NOT_UNKNOWN_SOURCE},
     * {@link #EXTRA_ALLOW_REPLACE}, and {@link #EXTRA_RETURN_RESULT}.
     * <p>
     * Output: If {@link #EXTRA_RETURN_RESULT}, returns whether the install
     * succeeded.
     * <p>
     * <strong>Note:</strong>If your app is targeting API level higher than 25 you
     * need to hold {@link android.Manifest.permission#REQUEST_INSTALL_PACKAGES}
     * in order to launch the application installer.
     * </p>
     *
     * @see #EXTRA_INSTALLER_PACKAGE_NAME
     * @see #EXTRA_NOT_UNKNOWN_SOURCE
     * @see #EXTRA_RETURN_RESULT
     *
     * @deprecated use {@link android.content.pm.PackageInstaller} instead
     */
    @Deprecated
    public static final String ACTION_INSTALL_PACKAGE = "android.intent.action.INSTALL_PACKAGE";

    /**
     * Activity Action: Use with startActivityForResult to start a system activity that captures
     * content on the screen to take a screenshot and present it to the user for editing. The
     * edited screenshot is saved on device and returned to the calling activity as a {@link Uri}
     * through {@link #getData()}. User interaction is required to return the edited screenshot to
     * the calling activity.
     *
     * <p>The response {@link Intent} may include additional data to "backlink" directly back to the
     * application for which the screenshot was captured. If present, the application "backlink" can
     * be retrieved via {@link #getClipData()}. The data is present only if the user accepted to
     * include the link information with the screenshot. The data can contain one of the following:
     * <ul>
     *     <li>A deeplinking {@link Uri} or an {@link Intent} if the captured app integrates with
     *         {@link android.app.assist.AssistContent}.</li>
     *     <li>Otherwise, a main launcher intent that launches the screenshotted application to
     *         its home screen.</li>
     * </ul>
     * The "backlink" to the screenshotted application will be set within {@link ClipData}, either
     * as a {@link Uri} or an {@link Intent} if present.
     *
     * <p>This intent action requires the permission
     * {@link android.Manifest.permission#LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE}.
     *
     * <p>Callers should query
     * {@link StatusBarManager#canLaunchCaptureContentActivityForNote(Activity)} before showing a UI
     * element that allows users to trigger this flow.
     *
     * <p>Callers should query for {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} in the
     * response {@link Intent} to check if the request was a success.
     */
    public static final String ACTION_LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE = "android.intent.action.LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE";

    /**
     * Broadcast Action: The receiver's effective locale has changed.
     *
     * This happens when the device locale, the receiving app's locale
     * (set via {@link android.app.LocaleManager#setApplicationLocales}) or language tags
     * of Regional preferences changed.
     *
     * Can be received by manifest-declared receivers.
     *
     * <p class="note"> If only the app locale changed, includes the following extras:
     * <ul>
     * <li>{@link #EXTRA_PACKAGE_NAME} is the name of the package for which locale changed.
     * <li>{@link #EXTRA_LOCALE_LIST} contains locales that are currently set for specified app
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_LOCALE_CHANGED = "android.intent.action.LOCALE_CHANGED";

    /**
     * Broadcast Action: This is broadcast once, after the user has finished
     * booting, but while still in the "locked" state. It can be used to perform
     * application-specific initialization, such as installing alarms. You must
     * hold the {@link android.Manifest.permission#RECEIVE_BOOT_COMPLETED}
     * permission in order to receive this broadcast.
     * <p>
     * This broadcast is sent immediately at boot by all devices (regardless of
     * direct boot support) running {@link android.os.Build.VERSION_CODES#N} or
     * higher. Upon receipt of this broadcast, the user is still locked and only
     * device-protected storage can be accessed safely. If you want to access
     * credential-protected storage, you need to wait for the user to be
     * unlocked (typically by entering their lock pattern or PIN for the first
     * time), after which the {@link #ACTION_USER_UNLOCKED} and
     * {@link #ACTION_BOOT_COMPLETED} broadcasts are sent.
     * <p>
     * To receive this broadcast, your receiver component must be marked as
     * being {@link ComponentInfo#directBootAware}.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     *
     * @see Context#createDeviceProtectedStorageContext()
     */
    public static final String ACTION_LOCKED_BOOT_COMPLETED = "android.intent.action.LOCKED_BOOT_COMPLETED";

    /**
     *  Activity Action: Start as a main entry point, does not expect to
     *  receive data.
     *  <p>Input: nothing
     *  <p>Output: nothing
     */
    public static final String ACTION_MAIN = "android.intent.action.MAIN";

    /**
     * Broadcast sent to the primary user when an associated managed profile is added (the profile
     * was created and is ready to be used). Carries an extra {@link #EXTRA_USER} that specifies
     * the {@link UserHandle} of the profile that was added. Only applications (for example
     * Launchers) that need to display merged content across both primary and managed profiles need
     * to worry about this broadcast. This is only sent to registered receivers,
     * not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_ADDED = "android.intent.action.MANAGED_PROFILE_ADDED";

    /**
     * Broadcast sent to the primary user when an associated managed profile has become available.
     * Currently this includes when the user disables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile. When quiet mode is
     * changed, this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the
     * new state of quiet mode. This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_AVAILABLE = "android.intent.action.MANAGED_PROFILE_AVAILABLE";

    /**
     * Broadcast sent to the primary user when an associated managed profile is removed.
     * Carries an extra {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile
     * that was removed.
     * Only applications (for example Launchers) that need to display merged content across both
     * primary and managed profiles need to worry about this broadcast. This is only sent to
     * registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_REMOVED = "android.intent.action.MANAGED_PROFILE_REMOVED";

    /**
     * Broadcast sent to the primary user when an associated managed profile has become unavailable.
     * Currently this includes when the user enables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile. When quiet mode is
     * changed, this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the
     * new state of quiet mode. This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_UNAVAILABLE = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE";

    /**
     * Broadcast sent to the primary user when the credential-encrypted private storage for
     * an associated managed profile is unlocked. Carries an extra {@link #EXTRA_USER} that
     * specifies the {@link UserHandle} of the profile that was unlocked. Only applications (for
     * example Launchers) that need to display merged content across both primary and managed
     * profiles need to worry about this broadcast. This is only sent to registered receivers,
     * not manifest receivers.
     */
    public static final String ACTION_MANAGED_PROFILE_UNLOCKED = "android.intent.action.MANAGED_PROFILE_UNLOCKED";

    /**
     * Activity Action: Show settings for managing network data usage of a
     * specific application. Applications should define an activity that offers
     * options to control data usage.
     */
    public static final String ACTION_MANAGE_NETWORK_USAGE = "android.intent.action.MANAGE_NETWORK_USAGE";

    /**
     * Broadcast Action:  Indicates low memory condition notification acknowledged by user
     * and package management should be started.
     * This is triggered by the user from the ACTION_DEVICE_STORAGE_LOW
     * notification.
     */
    public static final String ACTION_MANAGE_PACKAGE_STORAGE = "android.intent.action.MANAGE_PACKAGE_STORAGE";

    /**
     * Activity action: Launch UI to manage unused apps (hibernated apps).
     *
     * <p>
     * Input: Nothing.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     */
    public static final String ACTION_MANAGE_UNUSED_APPS = "android.intent.action.MANAGE_UNUSED_APPS";

    /**
     * Broadcast Action:  External media was removed from SD card slot, but mount point was not unmounted.
     * The path to the mount point for the removed media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_BAD_REMOVAL = "android.intent.action.MEDIA_BAD_REMOVAL";

    /**
     * Broadcast Action:  The "Media Button" was pressed.  Includes a single
     * extra field, {@link #EXTRA_KEY_EVENT}, containing the key event that
     * caused the broadcast.
     */
    public static final String ACTION_MEDIA_BUTTON = "android.intent.action.MEDIA_BUTTON";

    /**
     * Broadcast Action:  External media is present, and being disk-checked
     * The path to the mount point for the checking media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_CHECKING = "android.intent.action.MEDIA_CHECKING";

    /**
     * Broadcast Action:  User has expressed the desire to remove the external storage media.
     * Applications should close all files they have open within the mount point when they receive this intent.
     * The path to the mount point for the media to be ejected is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";

    /**
     * Broadcast Action:  External media is present and mounted at its mount point.
     * The path to the mount point for the mounted media is contained in the Intent.mData field.
     * The Intent contains an extra with name "read-only" and Boolean value to indicate if the
     * media was mounted read only.
     */
    public static final String ACTION_MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";

    /**
     * Broadcast Action:  External media is present, but is using an incompatible fs (or is blank)
     * The path to the mount point for the checking media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_NOFS = "android.intent.action.MEDIA_NOFS";

    /**
     * Broadcast Action:  External media has been removed.
     * The path to the mount point for the removed media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_REMOVED = "android.intent.action.MEDIA_REMOVED";

    /**
     * Broadcast Action:  The media scanner has finished scanning a directory.
     * The path to the scanned directory is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_SCANNER_FINISHED = "android.intent.action.MEDIA_SCANNER_FINISHED";

    /**
     * Broadcast Action: Request the media scanner to scan a file and add it to
     * the media database.
     * <p>
     * The path to the file is contained in {@link Intent#getData()}.
     *
     * @deprecated Callers should migrate to inserting items directly into
     *             {@link MediaStore}, where they will be automatically scanned
     *             after each mutation.
     */
    @Deprecated
    public static final String ACTION_MEDIA_SCANNER_SCAN_FILE = "android.intent.action.MEDIA_SCANNER_SCAN_FILE";

    /**
     * Broadcast Action:  The media scanner has started scanning a directory.
     * The path to the directory being scanned is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_SCANNER_STARTED = "android.intent.action.MEDIA_SCANNER_STARTED";

    /**
     * Broadcast Action:  External media is unmounted because it is being shared via USB mass storage.
     * The path to the mount point for the shared media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_SHARED = "android.intent.action.MEDIA_SHARED";

    /**
     * Broadcast Action:  External media is present but cannot be mounted.
     * The path to the mount point for the unmountable media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_UNMOUNTABLE = "android.intent.action.MEDIA_UNMOUNTABLE";

    /**
     * Broadcast Action:  External media is present, but not mounted at its mount point.
     * The path to the mount point for the unmounted media is contained in the Intent.mData field.
     */
    public static final String ACTION_MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";

    /**
     * Broadcast Action: A new version of your application has been installed
     * over an existing one.  This is only sent to the application that was
     * replaced.  It does not contain any additional data; to receive it, just
     * use an intent filter for this action.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED";

    /**
     * Broadcast Action: Sent to a package that has been suspended by the system. This is sent
     * whenever a package is put into a suspended state or any of its app extras change while in the
     * suspended state.
     * <p> Optionally includes the following extras:
     * <ul>
     *     <li> {@link #EXTRA_SUSPENDED_PACKAGE_EXTRAS} which is a {@link Bundle} which will contain
     *     useful information for the app being suspended.
     * </ul>
     * <p class="note">This is a protected intent that can only be sent
     * by the system. <em>This will be delivered to {@link BroadcastReceiver} components declared in
     * the manifest.</em>
     *
     * @see #ACTION_MY_PACKAGE_UNSUSPENDED
     * @see #EXTRA_SUSPENDED_PACKAGE_EXTRAS
     * @see PackageManager#isPackageSuspended()
     * @see PackageManager#getSuspendedPackageAppExtras()
     */
    public static final String ACTION_MY_PACKAGE_SUSPENDED = "android.intent.action.MY_PACKAGE_SUSPENDED";

    /**
     * Broadcast Action: Sent to a package that has been unsuspended.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system. <em>This will be delivered to {@link BroadcastReceiver} components declared in
     * the manifest.</em>
     *
     * @see #ACTION_MY_PACKAGE_SUSPENDED
     * @see #EXTRA_SUSPENDED_PACKAGE_EXTRAS
     * @see PackageManager#isPackageSuspended()
     * @see PackageManager#getSuspendedPackageAppExtras()
     */
    public static final String ACTION_MY_PACKAGE_UNSUSPENDED = "android.intent.action.MY_PACKAGE_UNSUSPENDED";

    /**
     * Broadcast Action: An outgoing call is about to be placed.
     *
     * <p>The Intent will have the following extra value:</p>
     * <ul>
     *   <li><em>{@link android.content.Intent#EXTRA_PHONE_NUMBER}</em> -
     *       the phone number dialed.</li>
     * </ul>
     * <p class="note">Starting in Android 15, this broadcast is no longer sent as an ordered
     * broadcast.  The <code>resultData</code> no longer has any effect and will not determine the
     * actual routing of the call.  Further, receivers of this broadcast do not get foreground
     * priority and cannot launch background activities.</p>
     * <p>Once the broadcast is finished, the resultData is used as the actual
     * number to call.  If  <code>null</code>, no call will be placed.</p>
     * <p>It is perfectly acceptable for multiple receivers to process the
     * outgoing call in turn: for example, a parental control application
     * might verify that the user is authorized to place the call at that
     * time, then a number-rewriting application might add an area code if
     * one was not specified.</p>
     * <p>For consistency, any receiver whose purpose is to prohibit phone
     * calls should have a priority of 0, to ensure it will see the final
     * phone number to be dialed.
     * Any receiver whose purpose is to rewrite phone numbers to be called
     * should have a positive priority.
     * Negative priorities are reserved for the system for this broadcast;
     * using them may cause problems.</p>
     * <p>Any BroadcastReceiver receiving this Intent <em>must not</em>
     * abort the broadcast.</p>
     * <p>Emergency calls cannot be intercepted using this mechanism, and
     * other calls cannot be modified to call emergency numbers using this
     * mechanism.
     * <p>Some apps (such as VoIP apps) may want to redirect the outgoing
     * call to use their own service instead. Those apps should first prevent
     * the call from being placed by setting resultData to <code>null</code>
     * and then start their own app to make the call.
     * <p>You must hold the
     * {@link android.Manifest.permission#PROCESS_OUTGOING_CALLS}
     * permission to receive this Intent.</p>
     *
     * <p class="note">Starting in {@link Build.VERSION_CODES#VANILLA_ICE_CREAM}, this broadcast is
     * no longer sent as an ordered broadcast, and does not allow activity launches.  This means
     * that receivers may no longer change the phone number for the outgoing call, or cancel the
     * outgoing call.  This functionality is only possible using the
     * {@link android.telecom.CallRedirectionService} API.  Although background receivers are
     * woken up to handle this intent, no guarantee is made as to the timeliness of the broadcast.
     * </p>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * <p class="note">If the user has chosen a {@link android.telecom.CallRedirectionService} to
     * handle redirection of outgoing calls, this intent will NOT be sent as an ordered broadcast.
     * This means that attempts to re-write the outgoing call by other apps using this intent will
     * be ignored.
     * </p>
     *
     * @deprecated Apps that redirect outgoing calls should use the
     * {@link android.telecom.CallRedirectionService} API.  Apps that perform call screening
     * should use the {@link android.telecom.CallScreeningService} API.  Apps which need to be
     * notified of basic call state should use
     * {@link android.telephony.TelephonyCallback.CallStateListener} to determine when a new
     * outgoing call is placed.
     */
    @Deprecated
    public static final String ACTION_NEW_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";

    /**
     * Activity Action: Allow the user to select and return one or more existing
     * documents. When invoked, the system will display the various
     * {@link DocumentsProvider} instances installed on the device, letting the
     * user interactively navigate through them. These documents include local
     * media, such as photos and video, and documents provided by installed
     * cloud storage providers.
     * <p>
     * Each document is represented as a {@code content://} URI backed by a
     * {@link DocumentsProvider}, which can be opened as a stream with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}, or queried for
     * {@link android.provider.DocumentsContract.Document} metadata.
     * <p>
     * All selected documents are returned to the calling application with
     * persistable read and write permission grants. If you want to maintain
     * access to the documents across device reboots, you need to explicitly
     * take the persistable permissions using
     * {@link ContentResolver#takePersistableUriPermission(Uri, int)}.
     * <p>
     * Callers must indicate the acceptable document MIME types through
     * {@link #setType(String)}. For example, to select photos, use
     * {@code image/*}. If multiple disjoint MIME types are acceptable, define
     * them in {@link #EXTRA_MIME_TYPES} and {@link #setType(String)} to
     * {@literal *}/*.
     * <p>
     * If the caller can handle multiple returned items (the user performing
     * multiple selection), then you can specify {@link #EXTRA_ALLOW_MULTIPLE}
     * to indicate this.
     * <p>
     * Callers must include {@link #CATEGORY_OPENABLE} in the Intent to obtain
     * URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}.
     * <p>
     * Callers can set a document URI through
     * {@link DocumentsContract#EXTRA_INITIAL_URI} to indicate the initial
     * location of documents navigator. System will do its best to launch the
     * navigator in the specified document if it's a folder, or the folder that
     * contains the specified document if not.
     * <p>
     * Output: The URI of the item that was picked, returned in
     * {@link #getData()}. This must be a {@code content://} URI so that any
     * receiver can access it. If multiple documents were selected, they are
     * returned in {@link #getClipData()}.
     *
     * @see DocumentsContract
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #ACTION_CREATE_DOCUMENT
     * @see #FLAG_GRANT_PERSISTABLE_URI_PERMISSION
     */
    public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";

    /**
     * Activity Action: Allow the user to pick a directory subtree. When
     * invoked, the system will display the various {@link DocumentsProvider}
     * instances installed on the device, letting the user navigate through
     * them. Apps can fully manage documents within the returned directory.
     * <p>
     * To gain access to descendant (child, grandchild, etc) documents, use
     * {@link DocumentsContract#buildDocumentUriUsingTree(Uri, String)} and
     * {@link DocumentsContract#buildChildDocumentsUriUsingTree(Uri, String)}
     * with the returned URI.
     * <p>
     * Callers can set a document URI through
     * {@link DocumentsContract#EXTRA_INITIAL_URI} to indicate the initial
     * location of documents navigator. System will do its best to launch the
     * navigator in the specified document if it's a folder, or the folder that
     * contains the specified document if not.
     * <p>
     * Output: The URI representing the selected directory tree.
     *
     * @see DocumentsContract
     */
    public static final String ACTION_OPEN_DOCUMENT_TREE = "android.intent.action.OPEN_DOCUMENT_TREE";

    /**
     * Broadcast Action: Packages have been suspended.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages which have been suspended
     * <li> {@link #EXTRA_CHANGED_UID_LIST} is the set of uids which have been suspended
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system. It is only sent to registered receivers.
     */
    public static final String ACTION_PACKAGES_SUSPENDED = "android.intent.action.PACKAGES_SUSPENDED";

    /**
     * Broadcast Action: Packages have been unsuspended.
     * <p>Includes the following extras:
     * <ul>
     * <li> {@link #EXTRA_CHANGED_PACKAGE_LIST} is the set of packages which have been unsuspended
     * <li> {@link #EXTRA_CHANGED_UID_LIST} is the set of uids which have been unsuspended
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system. It is only sent to registered receivers.
     */
    public static final String ACTION_PACKAGES_UNSUSPENDED = "android.intent.action.PACKAGES_UNSUSPENDED";

    /**
     * Broadcast Action: A new application package has been installed on the
     * device. The data contains the name of the package.  Note that the
     * newly installed package does <em>not</em> receive this broadcast.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the new package.
     * <li> {@link #EXTRA_REPLACING} is set to true if this is following
     * an {@link #ACTION_PACKAGE_REMOVED} broadcast for the same package.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";

    /**
     * Broadcast Action: An existing application package has been changed (for
     * example, a component has been enabled or disabled).  The data contains
     * the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * <li> {@link #EXTRA_CHANGED_COMPONENT_NAME_LIST} containing the class name
     * of the changed components (or the package name itself).
     * <li> {@link #EXTRA_DONT_KILL_APP} containing boolean field to override the
     * default action of restarting the application.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";

    /**
     * Broadcast Action: The user has cleared the data of a package.  This should
     * be preceded by {@link #ACTION_PACKAGE_RESTARTED}, after which all of
     * its persistent data is erased and this broadcast sent.
     * Note that the cleared package does <em>not</em>
     * receive this broadcast. The data contains the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package. If the
     *      package whose data was cleared is an uninstalled instant app, then the UID
     *      will be -1. The platform keeps some meta-data associated with instant apps
     *      after they are uninstalled.
     * <li> {@link #EXTRA_PACKAGE_NAME} containing the package name only if the cleared
     *      data was for an instant app.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_DATA_CLEARED = "android.intent.action.PACKAGE_DATA_CLEARED";

    /**
     * Broadcast Action: Sent to the installer package of an application when
     * that application is first launched (that is the first time it is moved
     * out of the stopped state).  The data contains the name of the package.
     *
     * <p>When the application is first launched, the application itself doesn't receive this
     * broadcast.</p>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_FIRST_LAUNCH = "android.intent.action.PACKAGE_FIRST_LAUNCH";

    /**
     * Broadcast Action: An existing application package has been completely
     * removed from the device.  The data contains the name of the package.
     * This is like {@link #ACTION_PACKAGE_REMOVED}, but only set when
     * {@link #EXTRA_DATA_REMOVED} is true and
     * {@link #EXTRA_REPLACING} is false of that broadcast.
     *
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid previously assigned
     * to the package.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_FULLY_REMOVED = "android.intent.action.PACKAGE_FULLY_REMOVED";

    /**
     * Broadcast Action: Trigger the download and eventual installation
     * of a package.
     * <p>Input: {@link #getData} is the URI of the package file to download.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     *
     * @deprecated This constant has never been used.
     */
    @Deprecated
    public static final String ACTION_PACKAGE_INSTALL = "android.intent.action.PACKAGE_INSTALL";

    /**
     * Broadcast Action: Sent to the system package verifier when a package
     * needs to be verified. The data contains the package URI.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     * </p>
     */
    public static final String ACTION_PACKAGE_NEEDS_VERIFICATION = "android.intent.action.PACKAGE_NEEDS_VERIFICATION";

    /**
     * Broadcast Action: An existing application package has been removed from
     * the device.  The data contains the name of the package.  The package
     * that is being removed does <em>not</em> receive this Intent.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid previously assigned
     * to the package.
     * <li> {@link #EXTRA_DATA_REMOVED} is set to true if the entire
     * application -- data and code -- is being removed.
     * <li> {@link #EXTRA_REPLACING} is set to true if this will be followed
     * by an {@link #ACTION_PACKAGE_ADDED} broadcast for the same package.
     * <li> {@link #EXTRA_USER_INITIATED} containing boolean field to signal that the application
     * was removed with the user-initiated action.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";

    /**
     * Broadcast Action: A new version of an application package has been
     * installed, replacing an existing version that was previously installed.
     * The data contains the name of the package.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the new package.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_PACKAGE_REPLACED = "android.intent.action.PACKAGE_REPLACED";

    /**
     * Broadcast Action: The user has restarted a package, and all of its
     * processes have been killed.  All runtime state
     * associated with it (processes, alarms, notifications, etc) should
     * be removed.  Note that the restarted package does <em>not</em>
     * receive this broadcast.
     * The data contains the name of the package.
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>
     * Starting in Android V, an extra timestamp
     * {@link #EXTRA_TIME} is included with this broadcast to indicate the exact time the package
     * was restarted, in {@link SystemClock#elapsedRealtime() elapsed realtime}.
     * </p>
     */
    public static final String ACTION_PACKAGE_RESTARTED = "android.intent.action.PACKAGE_RESTARTED";

    /**
     * Broadcast Action: An application package that was previously in the stopped state has been
     * started and is no longer considered stopped.
     * <p>When a package is force-stopped, the {@link #ACTION_PACKAGE_RESTARTED} broadcast is sent
     * and the package in the stopped state cannot self-start for any reason unless there's an
     * explicit request to start a component in the package. The {@link #ACTION_PACKAGE_UNSTOPPED}
     * broadcast is sent when such an explicit process start occurs and the package is taken
     * out of the stopped state. The data contains the name of the package.
     * </p>
     * <ul>
     * <li> {@link #EXTRA_UID} containing the integer uid assigned to the package.
     * <li> {@link #EXTRA_TIME} containing the {@link SystemClock#elapsedRealtime()
     *          elapsed realtime} of when the package was unstopped.
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent by the system.
     *
     * @see ApplicationInfo#FLAG_STOPPED
     * @see #ACTION_PACKAGE_RESTARTED
     */
    public static final String ACTION_PACKAGE_UNSTOPPED = "android.intent.action.PACKAGE_UNSTOPPED";

    /**
     * Broadcast Action: Sent to the system package verifier when a package is
     * verified. The data contains the package URI.
     * <p class="note">
     * This is a protected intent that can only be sent by the system.
     */
    public static final String ACTION_PACKAGE_VERIFIED = "android.intent.action.PACKAGE_VERIFIED";

    /**
     * Activity Action: Create a new item in the given container, initializing it
     * from the current contents of the clipboard.
     * <p>Input: {@link #getData} is URI of the directory (vnd.android.cursor.dir/*)
     * in which to place the data.
     * <p>Output: URI of the new data that was created.
     */
    public static final String ACTION_PASTE = "android.intent.action.PASTE";

    /**
     * Activity Action: Pick an item from the data, returning what was selected.
     * <p>Input: {@link #getData} is URI containing a directory of data
     * (vnd.android.cursor.dir/*) from which to pick an item.
     * <p>Output: The URI of the item that was picked.
     */
    public static final String ACTION_PICK = "android.intent.action.PICK";

    /**
     * Activity Action: Pick an activity given an intent, returning the class
     * selected.
     * <p>Input: get*Extra field {@link #EXTRA_INTENT} is an Intent
     * used with {@link PackageManager#queryIntentActivities} to determine the
     * set of activities from which to pick.
     * <p>Output: Class name of the activity that was selected.
     */
    public static final String ACTION_PICK_ACTIVITY = "android.intent.action.PICK_ACTIVITY";

    /**
     * Broadcast Action:  External power has been connected to the device.
     * This is intended for applications that wish to register specifically to this notification.
     * Unlike ACTION_BATTERY_CHANGED, applications will be woken for this and so do not have to
     * stay active to receive this notification.  This action can be used to implement actions
     * that wait until power is available to trigger.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";

    /**
     * Broadcast Action:  External power has been removed from the device.
     * This is intended for applications that wish to register specifically to this notification.
     * Unlike ACTION_BATTERY_CHANGED, applications will be woken for this and so do not have to
     * stay active to receive this notification.  This action can be used to implement actions
     * that wait until power is available to trigger.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";

    /**
     * Activity Action: Show power usage information to the user.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_POWER_USAGE_SUMMARY = "android.intent.action.POWER_USAGE_SUMMARY";

    /**
     * Activity Action: Process a piece of text.
     * <p>Input: {@link #EXTRA_PROCESS_TEXT} contains the text to be processed.
     * {@link #EXTRA_PROCESS_TEXT_READONLY} states if the resulting text will be read-only.</p>
     * <p>Output: {@link #EXTRA_PROCESS_TEXT} contains the processed text.</p>
     */
    public static final String ACTION_PROCESS_TEXT = "android.intent.action.PROCESS_TEXT";

    /**
     * Broadcast sent to the parent user when an associated profile has been started and unlocked.
     * Carries an extra {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile.
     * This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_PROFILE_ACCESSIBLE = "android.intent.action.PROFILE_ACCESSIBLE";

    /**
     * Broadcast sent to the parent user when an associated profile is added (the profile was
     * created and is ready to be used).
     * Carries an extra {@link #EXTRA_USER} that specifies the  {@link UserHandle} of the profile
     * that was added.
     *
     * <p>This broadcast is similar to {@link #ACTION_MANAGED_PROFILE_ADDED} but functions as a
     * generic broadcast for all profile users.
     * It is sent in addition to the {@link #ACTION_MANAGED_PROFILE_ADDED} broadcast when a
     * managed user is added.
     *
     * <p>Only applications (for example Launchers) that need to display merged content across both
     * the parent user and its associated profiles need to worry about this broadcast.
     * This is only sent to registered receivers created with {@link Context#registerReceiver}.
     * It is not sent to manifest receivers.
     */
    public static final String ACTION_PROFILE_ADDED = "android.intent.action.PROFILE_ADDED";

    /**
     * Broadcast sent to the primary user when an associated profile has become available.
     * This is sent when a user disables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile. When quiet mode is
     * changed, this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the
     * new state of quiet mode. This is only sent to registered receivers, not manifest receivers.
     *
     * <p>This broadcast is similar to {@link #ACTION_MANAGED_PROFILE_AVAILABLE} but functions as a
     * generic broadcast for all users of type {@link android.os.UserManager#isProfile()}}. In
     * case of a managed profile, both {@link #ACTION_MANAGED_PROFILE_AVAILABLE} and
     * {@link #ACTION_PROFILE_AVAILABLE} broadcasts are sent.
     */
    public static final String ACTION_PROFILE_AVAILABLE = "android.intent.action.PROFILE_AVAILABLE";

    /**
     * Broadcast sent to the parent user when an associated profile has stopped.
     * Carries an extra {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile.
     * This is only sent to registered receivers, not manifest receivers.
     */
    public static final String ACTION_PROFILE_INACCESSIBLE = "android.intent.action.PROFILE_INACCESSIBLE";

    /**
     * Broadcast sent to the parent user when an associated profile is removed.
     * Carries an extra {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile
     * that was removed.
     *
     * <p>This broadcast is similar to {@link #ACTION_MANAGED_PROFILE_REMOVED} but functions as a
     * generic broadcast for all profile users.
     * It is sent in addition to the {@link #ACTION_MANAGED_PROFILE_REMOVED} broadcast when a
     * managed user is removed.
     *
     * <p>Only applications (for example Launchers) that need to display merged content across both
     * the parent user and its associated profiles need to worry about this broadcast.
     * This is only sent to registered receivers created with {@link Context#registerReceiver}.
     * It is not sent to manifest receivers.
     */
    public static final String ACTION_PROFILE_REMOVED = "android.intent.action.PROFILE_REMOVED";

    /**
     * Broadcast sent to the primary user when an associated profile has become unavailable.
     * This is sent when a user enables quiet mode for the profile. Carries an extra
     * {@link #EXTRA_USER} that specifies the {@link UserHandle} of the profile. When quiet mode is
     * changed, this broadcast will carry a boolean extra {@link #EXTRA_QUIET_MODE} indicating the
     * new state of quiet mode. This is only sent to registered receivers, not manifest receivers.
     *
     * <p>This broadcast is similar to {@link #ACTION_MANAGED_PROFILE_UNAVAILABLE} but functions as
     * a generic broadcast for all users of type {@link android.os.UserManager#isProfile()}}. In
     * case of a managed profile, both {@link #ACTION_MANAGED_PROFILE_UNAVAILABLE} and
     * {@link #ACTION_PROFILE_UNAVAILABLE} broadcasts are sent.
     */
    public static final String ACTION_PROFILE_UNAVAILABLE = "android.intent.action.PROFILE_UNAVAILABLE";

    /**
     * Broadcast Action: Some content providers have parts of their namespace
     * where they publish new events or items that the user may be especially
     * interested in. For these things, they may broadcast this action when the
     * set of interesting items change.
     *
     * For example, GmailProvider sends this notification when the set of unread
     * mail in the inbox changes.
     *
     * <p>The data of the intent identifies which part of which provider
     * changed. When queried through the content resolver, the data URI will
     * return the data set in question.
     *
     * <p>The intent will have the following extra values:
     * <ul>
     *   <li><em>count</em> - The number of items in the data set. This is the
     *       same as the number of items in the cursor returned by querying the
     *       data URI. </li>
     * </ul>
     *
     * This intent will be sent at boot (if the count is non-zero) and when the
     * data set changes. It is possible for the data set to change without the
     * count changing (for example, if a new unread message arrives in the same
     * sync operation in which a message is archived). The phone should still
     * ring/vibrate/etc as normal in this case.
     */
    public static final String ACTION_PROVIDER_CHANGED = "android.intent.action.PROVIDER_CHANGED";

    /**
     * Sent when the user taps on the clock widget in the system's "quick settings" area.
     */
    public static final String ACTION_QUICK_CLOCK = "android.intent.action.QUICK_CLOCK";

    /**
     * Activity Action: Quick view the data. Launches a quick viewer for
     * a URI or a list of URIs.
     * <p>Activities handling this intent action should handle the vast majority of
     * MIME types rather than only specific ones.
     * <p>Quick viewers must render the quick view image locally, and must not send
     * file content outside current device.
     * <p>Input: {@link #getData} is a mandatory content URI of the item to
     * preview. {@link #getClipData} contains an optional list of content URIs
     * if there is more than one item to preview. {@link #EXTRA_INDEX} is an
     * optional index of the URI in the clip data to show first.
     * {@link #EXTRA_QUICK_VIEW_FEATURES} is an optional extra indicating the features
     * that can be shown in the quick view UI.
     * <p>Output: nothing.
     * @see #EXTRA_INDEX
     * @see #EXTRA_QUICK_VIEW_FEATURES
     */
    public static final String ACTION_QUICK_VIEW = "android.intent.action.QUICK_VIEW";

    /**
     * Broadcast Action: Have the device reboot.  This is only for use by
     * system code.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_REBOOT = "android.intent.action.REBOOT";

    /**
     * Activity Action: Run the data, whatever that means.
     * <p>Input: ?  (Note: this is currently specific to the test harness.)
     * <p>Output: nothing.
     */
    public static final String ACTION_RUN = "android.intent.action.RUN";

    /**
     * Activity action: Launch UI to open the Safety Center, which highlights the user's security
     * and privacy status.
     */
    public static final String ACTION_SAFETY_CENTER = "android.intent.action.SAFETY_CENTER";

    /**
     * Broadcast Action: Sent when the device goes to sleep and becomes non-interactive.
     * <p>
     * For historical reasons, the name of this broadcast action refers to the power
     * state of the screen but it is actually sent in response to changes in the
     * overall interactive state of the device.
     * </p><p>
     * This broadcast is sent when the device becomes non-interactive which may have
     * nothing to do with the screen turning off.  To determine the
     * actual state of the screen, use {@link android.view.Display#getState}.
     * </p><p>
     * See {@link android.os.PowerManager#isInteractive} for details.
     * </p>
     * You <em>cannot</em> receive this through components declared in
     * manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";

    /**
     * Broadcast Action: Sent when the device wakes up and becomes interactive.
     * <p>
     * For historical reasons, the name of this broadcast action refers to the power
     * state of the screen but it is actually sent in response to changes in the
     * overall interactive state of the device.
     * </p><p>
     * This broadcast is sent when the device becomes interactive which may have
     * nothing to do with the screen turning on.  To determine the
     * actual state of the screen, use {@link android.view.Display#getState}.
     * </p><p>
     * See {@link android.os.PowerManager#isInteractive} for details.
     * </p>
     * You <em>cannot</em> receive this through components declared in
     * manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";

    /**
     * Activity Action: Perform a search.
     * <p>Input: {@link android.app.SearchManager#QUERY getStringExtra(SearchManager.QUERY)}
     * is the text to search for.  If empty, simply
     * enter your search results Activity with the search UI activated.
     * <p>Output: nothing.
     */
    public static final String ACTION_SEARCH = "android.intent.action.SEARCH";

    /**
     * Activity Action: Start action associated with long pressing on the
     * search key.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_SEARCH_LONG_PRESS = "android.intent.action.SEARCH_LONG_PRESS";

    /**
     * Activity Action: Deliver some data to someone else.  Who the data is
     * being delivered to is not specified; it is up to the receiver of this
     * action to ask the user where the data should be sent.
     * <p>
     * When launching a SEND intent, you should usually wrap it in a chooser
     * (through {@link #createChooser}), which will give the proper interface
     * for the user to pick how to send your data and allow you to specify
     * a prompt indicating what they are doing.
     * <p>
     * Input: {@link #getType} is the MIME type of the data being sent.
     * get*Extra can have either a {@link #EXTRA_TEXT}
     * or {@link #EXTRA_STREAM} field, containing the data to be sent.  If
     * using EXTRA_TEXT, the MIME type should be "text/plain"; otherwise it
     * should be the MIME type of the data in EXTRA_STREAM.  Use {@literal *}/*
     * if the MIME type is unknown (this will only allow senders that can
     * handle generic data streams).  If using {@link #EXTRA_TEXT}, you can
     * also optionally supply {@link #EXTRA_HTML_TEXT} for clients to retrieve
     * your text with HTML formatting.
     * <p>
     * As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, the data
     * being sent can be supplied through {@link #setClipData(ClipData)}.  This
     * allows you to use {@link #FLAG_GRANT_READ_URI_PERMISSION} when sharing
     * content: URIs and other advanced features of {@link ClipData}.  If
     * using this approach, you still must supply the same data through the
     * {@link #EXTRA_TEXT} or {@link #EXTRA_STREAM} fields described below
     * for compatibility with old applications.  If you don't set a ClipData,
     * it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#O}, if
     * {@link #CATEGORY_TYPED_OPENABLE} is passed, then the Uris passed in
     * either {@link #EXTRA_STREAM} or via {@link #setClipData(ClipData)} may
     * be openable only as asset typed files using
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}.
     * <p>
     * Optional standard extras, which may be interpreted by some recipients as
     * appropriate, are: {@link #EXTRA_EMAIL}, {@link #EXTRA_CC},
     * {@link #EXTRA_BCC}, {@link #EXTRA_SUBJECT}.
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_SEND = "android.intent.action.SEND";

    /**
     * Activity Action: Send a message to someone specified by the data.
     * <p>Input: {@link #getData} is URI describing the target.
     * <p>Output: nothing.
     */
    public static final String ACTION_SENDTO = "android.intent.action.SENDTO";

    /**
     * Activity Action: Deliver multiple data to someone else.
     * <p>
     * Like {@link #ACTION_SEND}, except the data is multiple.
     * <p>
     * Input: {@link #getType} is the MIME type of the data being sent.
     * get*ArrayListExtra can have either a {@link #EXTRA_TEXT} or {@link
     * #EXTRA_STREAM} field, containing the data to be sent.  If using
     * {@link #EXTRA_TEXT}, you can also optionally supply {@link #EXTRA_HTML_TEXT}
     * for clients to retrieve your text with HTML formatting.
     * <p>
     * Multiple types are supported, and receivers should handle mixed types
     * whenever possible. The right way for the receiver to check them is to
     * use the content resolver on each URI. The intent sender should try to
     * put the most concrete mime type in the intent type, but it can fall
     * back to {@literal <type>/*} or {@literal *}/* as needed.
     * <p>
     * e.g. if you are sending image/jpg and image/jpg, the intent's type can
     * be image/jpg, but if you are sending image/jpg and image/png, then the
     * intent's type should be image/*.
     * <p>
     * As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, the data
     * being sent can be supplied through {@link #setClipData(ClipData)}.  This
     * allows you to use {@link #FLAG_GRANT_READ_URI_PERMISSION} when sharing
     * content: URIs and other advanced features of {@link ClipData}.  If
     * using this approach, you still must supply the same data through the
     * {@link #EXTRA_TEXT} or {@link #EXTRA_STREAM} fields described below
     * for compatibility with old applications.  If you don't set a ClipData,
     * it will be copied there for you when calling {@link Context#startActivity(Intent)}.
     * <p>
     * Starting from {@link android.os.Build.VERSION_CODES#O}, if
     * {@link #CATEGORY_TYPED_OPENABLE} is passed, then the Uris passed in
     * either {@link #EXTRA_STREAM} or via {@link #setClipData(ClipData)} may
     * be openable only as asset typed files using
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}.
     * <p>
     * Optional standard extras, which may be interpreted by some recipients as
     * appropriate, are: {@link #EXTRA_EMAIL}, {@link #EXTRA_CC},
     * {@link #EXTRA_BCC}, {@link #EXTRA_SUBJECT}.
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";

    /**
     * Activity Action: Show settings for choosing wallpaper.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     */
    public static final String ACTION_SET_WALLPAPER = "android.intent.action.SET_WALLPAPER";

    /**
     * Activity Action: Launch an activity showing the app information.
     * For applications which install other applications (such as app stores), it is recommended
     * to handle this action for providing the app information to the user.
     *
     * <p>Input: {@link #EXTRA_PACKAGE_NAME} specifies the package whose information needs
     * to be displayed.
     * <p>Output: Nothing.
     */
    public static final String ACTION_SHOW_APP_INFO = "android.intent.action.SHOW_APP_INFO";

    /**
     * Activity Action: Action to show the list of all work apps in the launcher. For example,
     * shows the work apps folder or tab.
     *
     * <p>Input: Nothing.
     * <p>Output: nothing.
     */
    public static final String ACTION_SHOW_WORK_APPS = "android.intent.action.SHOW_WORK_APPS";

    /**
     * Broadcast Action:  Device is shutting down.
     * This is broadcast when the device is being shut down (completely turned
     * off, not sleeping).  Once the broadcast is complete, the final shutdown
     * will proceed and all unsaved data lost.  Apps will not normally need
     * to handle this, since the foreground activity will be paused as well.
     * <p>As of {@link Build.VERSION_CODES#P} this broadcast is only sent to receivers registered
     * through {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver}.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     * <p>May include the following extras:
     * <ul>
     * <li> {@link #EXTRA_SHUTDOWN_USERSPACE_ONLY} a boolean that is set to true if this
     * shutdown is only for userspace processes.  If not set, assumed to be false.
     * </ul>
     */
    public static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";

    /**
     * Activity Action: Perform a data synchronization.
     * <p>Input: ?
     * <p>Output: ?
     */
    public static final String ACTION_SYNC = "android.intent.action.SYNC";

    /**
     * Activity Action: Start the platform-defined tutorial
     * <p>Input: {@link android.app.SearchManager#QUERY getStringExtra(SearchManager.QUERY)}
     * is the text to search for.  If empty, simply
     * enter your search results Activity with the search UI activated.
     * <p>Output: nothing.
     */
    public static final String ACTION_SYSTEM_TUTORIAL = "android.intent.action.SYSTEM_TUTORIAL";

    /**
     * Broadcast Action: The timezone has changed. The intent will have the following extra values:</p>
     * <ul>
     *   <li>{@link #EXTRA_TIMEZONE} - The java.util.TimeZone.getID() value identifying the new
     *   time zone.</li>
     * </ul>
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_TIMEZONE_CHANGED = "android.intent.action.TIMEZONE_CHANGED";

    /**
     * Broadcast Action: The time was set.
     */
    public static final String ACTION_TIME_CHANGED = "android.intent.action.TIME_SET";

    /**
     * Broadcast Action: The current time has changed.  Sent every
     * minute.  You <em>cannot</em> receive this through components declared
     * in manifests, only by explicitly registering for it with
     * {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver()}.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";

    /**
     * Activity Action: Perform text translation.
     * <p>
     * Input: {@link #EXTRA_TEXT getCharSequence(EXTRA_TEXT)} is the text to translate.
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_TRANSLATE = "android.intent.action.TRANSLATE";

    /**
     * Broadcast Action: A uid has been removed from the system.  The uid
     * number is stored in the extra data under {@link #EXTRA_UID}.
     *
     * In certain instances, {@link #EXTRA_REPLACING} is set to true if the UID is not being
     * fully removed.
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_UID_REMOVED = "android.intent.action.UID_REMOVED";

    /**
     * Broadcast Action:  The device has entered USB Mass Storage mode.
     * This is used mainly for the USB Settings panel.
     * Apps should listen for ACTION_MEDIA_MOUNTED and ACTION_MEDIA_UNMOUNTED broadcasts to be notified
     * when the SD card file system is mounted or unmounted
     * @deprecated replaced by android.os.storage.StorageEventListener
     */
    @Deprecated
    public static final String ACTION_UMS_CONNECTED = "android.intent.action.UMS_CONNECTED";

    /**
     * Broadcast Action:  The device has exited USB Mass Storage mode.
     * This is used mainly for the USB Settings panel.
     * Apps should listen for ACTION_MEDIA_MOUNTED and ACTION_MEDIA_UNMOUNTED broadcasts to be notified
     * when the SD card file system is mounted or unmounted
     * @deprecated replaced by android.os.storage.StorageEventListener
     */
    @Deprecated
    public static final String ACTION_UMS_DISCONNECTED = "android.intent.action.UMS_DISCONNECTED";

    /**
     * Broadcast Action: Sent to the responsible installer of an archived package when unarchival
     * is requested.
     *
     * @see android.content.pm.PackageInstaller#requestUnarchive
     */
    public static final String ACTION_UNARCHIVE_PACKAGE = "android.intent.action.UNARCHIVE_PACKAGE";

    /**
     * Activity Action: Launch application uninstaller.
     * <p>
     * Input: The data must be a package: URI whose scheme specific part is
     * the package name of the current installed package to be uninstalled.
     * You can optionally supply {@link #EXTRA_RETURN_RESULT}.
     * <p>
     * Output: If {@link #EXTRA_RETURN_RESULT}, returns whether the uninstall
     * succeeded.
     * <p>
     * Requires {@link android.Manifest.permission#REQUEST_DELETE_PACKAGES}
     * since {@link Build.VERSION_CODES#P}.
     *
     * @deprecated Use {@link android.content.pm.PackageInstaller#uninstall(String, IntentSender)}
     *             instead
     */
    @Deprecated
    public static final String ACTION_UNINSTALL_PACKAGE = "android.intent.action.UNINSTALL_PACKAGE";

    /**
     * Sent after a user switch is complete, if the switch caused the process's user to be
     * sent to the background.  This is only sent to receivers registered
     * through {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver}.  It is sent to the user that is going to the
     * background.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     */
    public static final String ACTION_USER_BACKGROUND = "android.intent.action.USER_BACKGROUND";

    /**
     * Sent after a user switch is complete, if the switch caused the process's user to be
     * brought to the foreground.  This is only sent to receivers registered
     * through {@link Context#registerReceiver(BroadcastReceiver, IntentFilter)
     * Context.registerReceiver}.  It is sent to the user that is going to the
     * foreground.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     */
    public static final String ACTION_USER_FOREGROUND = "android.intent.action.USER_FOREGROUND";

    /**
     * Sent the first time a user is starting, to allow system apps to
     * perform one time initialization.  (This will not be seen by third
     * party applications because a newly initialized user does not have any
     * third party applications installed for it.)  This is sent early in
     * starting the user, around the time the home app is started, before
     * {@link #ACTION_BOOT_COMPLETED} is sent.  This is sent as a foreground
     * broadcast, since it is part of a visible user interaction; be as quick
     * as possible when handling it.
     *
     * <p><b>Note:</b> This broadcast is not sent to the system user.
     */
    public static final String ACTION_USER_INITIALIZE = "android.intent.action.USER_INITIALIZE";

    /**
     * Broadcast Action: Sent when the user is present after device wakes up (e.g when the
     * keyguard is gone).
     *
     * <p class="note">This is a protected intent that can only be sent
     * by the system.
     */
    public static final String ACTION_USER_PRESENT = "android.intent.action.USER_PRESENT";

    /**
     * Broadcast Action: Sent when the credential-encrypted private storage has
     * become unlocked for the target user. This is only sent to registered
     * receivers, not manifest receivers.
     *
     * <p>
     * <b>Note:</b> The user's actual state might have changed by the time the broadcast is
     * received. For example, the user could have been removed, started or stopped already,
     * regardless of which broadcast you receive. Because of that, receivers should always check
     * the current state of the user.
     */
    public static final String ACTION_USER_UNLOCKED = "android.intent.action.USER_UNLOCKED";

    /**
     * Activity Action: Display the data to the user.  This is the most common
     * action performed on data -- it is the generic action you can use on
     * a piece of data to get the most reasonable thing to occur.  For example,
     * when used on a contacts entry it will view the entry; when used on a
     * mailto: URI it will bring up a compose window filled with the information
     * supplied by the URI; when used with a tel: URI it will invoke the
     * dialer.
     * <p>Input: {@link #getData} is URI from which to retrieve data.
     * <p>Output: nothing.
     */
    public static final String ACTION_VIEW = "android.intent.action.VIEW";

    /**
     * Activity Action: Display an activity state associated with an unique {@link LocusId}.
     *
     * <p>For example, a chat app could use the context to resume a conversation between 2 users.
     *
     * <p>Input: {@link #EXTRA_LOCUS_ID} specifies the unique identifier of the locus in the
     * app domain. Should be stable across reboots and backup / restore.
     * <p>Output: nothing.
     */
    public static final String ACTION_VIEW_LOCUS = "android.intent.action.VIEW_LOCUS";

    /**
     * Activity action: Launch UI to show information about the usage
     * of a given permission group. This action would be handled by apps that
     * want to show details about how and why given permission group is being
     * used.
     * <p>
     * <strong>Important:</strong>You must protect the activity that handles
     * this action with the {@link android.Manifest.permission#START_VIEW_PERMISSION_USAGE
     *  START_VIEW_PERMISSION_USAGE} permission to ensure that only the
     * system can launch this activity. The system will not launch
     * activities that are not properly protected.
     *
     * <p>
     * Input: {@link #EXTRA_PERMISSION_GROUP_NAME} specifies the permission group
     * for which the launched UI would be targeted.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     */
    public static final String ACTION_VIEW_PERMISSION_USAGE = "android.intent.action.VIEW_PERMISSION_USAGE";

    /**
     * Activity action: Launch UI to show information about the usage of a given permission group in
     * a given period. This action would be handled by apps that want to show details about how and
     * why given permission group is being used.
     * <p>
     * <strong>Important:</strong>You must protect the activity that handles this action with the
     * {@link android.Manifest.permission#START_VIEW_PERMISSION_USAGE} permission to ensure that
     * only the system can launch this activity. The system will not launch activities that are not
     * properly protected.
     *
     * <p>
     * Input: {@link #EXTRA_PERMISSION_GROUP_NAME} specifies the permission group for which the
     * launched UI would be targeted.
     * </p>
     * <p>
     * Input: {@link #EXTRA_ATTRIBUTION_TAGS} specifies the attribution tags for the usage entry.
     * </p>
     * <p>
     * Input: {@link #EXTRA_START_TIME} specifies the start time of the period (epoch time in
     * millis). Both start time and end time are needed and start time must be <= end time.
     * </p>
     * <p>
     * Input: {@link #EXTRA_END_TIME} specifies the end time of the period (epoch time in
     * millis). Both start time and end time are needed and start time must be <= end time.
     * </p>
     * <p>
     * Output: Nothing.
     * </p>
     */
    public static final String ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD = "android.intent.action.VIEW_PERMISSION_USAGE_FOR_PERIOD";

    /**
     * Activity Action: Start Voice Command.
     * <p>Input: Nothing.
     * <p>Output: Nothing.
     * <p class="note">
     * In some cases, a matching Activity may not exist, so ensure you
     * safeguard against this.
     */
    public static final String ACTION_VOICE_COMMAND = "android.intent.action.VOICE_COMMAND";

    /**
     * Broadcast Action:  The current system wallpaper has changed.  See
     * {@link android.app.WallpaperManager} for retrieving the new wallpaper.
     * This should <em>only</em> be used to determine when the wallpaper
     * has changed to show the new wallpaper to the user.  You should certainly
     * never, in response to this, change the wallpaper or other attributes of
     * it such as the suggested size.  That would be unexpected, right?  You'd cause
     * all kinds of loops, especially if other apps are doing similar things,
     * right?  Of course.  So please don't do this.
     *
     * @deprecated Modern applications should use
     * {@link android.view.WindowManager.LayoutParams#FLAG_SHOW_WALLPAPER
     * WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER} to have the wallpaper
     * shown behind their UI, rather than watching for this broadcast and
     * rendering the wallpaper on their own.
     */
    @Deprecated
    public static final String ACTION_WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";

    /**
     * Activity Action: Perform a web search.
     * <p>
     * Input: {@link android.app.SearchManager#QUERY
     * getStringExtra(SearchManager.QUERY)} is the text to search for. If it is
     * a url starts with http or https, the site will be opened. If it is plain
     * text, Google search will be applied.
     * <p>
     * Output: nothing.
     */
    public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";

    /**
     * A response code used with {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} to indicate
     * that screenshot is blocked by IT admin.
     */
    public static final int CAPTURE_CONTENT_FOR_NOTE_BLOCKED_BY_ADMIN = 4;

    /**
     * A response code used with {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} to indicate
     * that something went wrong.
     */
    public static final int CAPTURE_CONTENT_FOR_NOTE_FAILED = 1;

    /**
     * A response code used with {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} to indicate
     * that the request was a success.
     *
     * <p>This code will only be returned after the user has interacted with the system screenshot
     * activity to consent to sharing the data with the note.
     *
     * <p>The captured screenshot is returned as a {@link Uri} through {@link #getData()}.
     */
    public static final int CAPTURE_CONTENT_FOR_NOTE_SUCCESS = 0;

    /**
     * A response code used with {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} to indicate
     * that user canceled the content capture flow.
     */
    public static final int CAPTURE_CONTENT_FOR_NOTE_USER_CANCELED = 2;

    /**
     * A response code used with {@link #EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE} to indicate
     * that the intent action {@link #ACTION_LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE} was started
     * by an activity that is running in a non-supported window mode.
     */
    public static final int CAPTURE_CONTENT_FOR_NOTE_WINDOW_MODE_UNSUPPORTED = 3;

    /**
     * The accessibility shortcut is a global gesture for users with disabilities to trigger an
     * important for them accessibility feature to help developers determine whether they want to
     * make their activity a shortcut target.
     * <p>
     * An activity of interest to users with accessibility needs may request to be the target of
     * the accessibility shortcut. It handles intent {@link #ACTION_MAIN} with this category,
     * which will be dispatched by the system when the user activates the shortcut when it is
     * configured to point at this target.
     * </p>
     * <p>
     * An activity declared itself to be a target of the shortcut in AndroidManifest.xml. It must
     * also do two things:
     * <ul>
     *     <ol>
     *         Specify that it handles the <code>android.intent.action.MAIN</code>
     *         {@link android.content.Intent}
     *         with category <code>android.intent.category.ACCESSIBILITY_SHORTCUT_TARGET</code>.
     *     </ol>
     *     <ol>
     *         Provide a meta-data entry <code>android.accessibilityshortcut.target</code> in the
     *         manifest when declaring the activity.
     *     </ol>
     * </ul>
     * If either of these items is missing, the system will ignore the accessibility shortcut
     * target. Following is an example declaration:
     * </p>
     * <pre>
     * &lt;activity android:name=".MainActivity"
     * . . .
     *   &lt;intent-filter&gt;
     *       &lt;action android:name="android.intent.action.MAIN" /&gt;
     *       &lt;category android:name="android.intent.category.ACCESSIBILITY_SHORTCUT_TARGET" /&gt;
     *   &lt;/intent-filter&gt;
     *   &lt;meta-data android:name="android.accessibilityshortcut.target"
     *                   android:resource="@xml/accessibilityshortcut" /&gt;
     * &lt;/activity&gt;
     * </pre>
     * <p> This is a sample XML file configuring a accessibility shortcut target: </p>
     * <pre>
     * &lt;accessibility-shortcut-target
     *     android:description="@string/shortcut_target_description"
     *     android:summary="@string/shortcut_target_summary"
     *     android:animatedImageDrawable="@drawable/shortcut_target_animated_image"
     *     android:htmlDescription="@string/shortcut_target_html_description"
     *     android:settingsActivity="com.example.android.shortcut.target.SettingsActivity" /&gt;
     * </pre>
     * <p>
     * Both description and summary are necessary. The system will ignore the accessibility
     * shortcut target if they are missing. The animated image and html description are supported
     * to help users understand how to use the shortcut target. The settings activity is a
     * component name that allows the user to modify the settings for this accessibility shortcut
     * target.
     * </p>
     */
    public static final String CATEGORY_ACCESSIBILITY_SHORTCUT_TARGET = "android.intent.category.ACCESSIBILITY_SHORTCUT_TARGET";

    /**
     * Set if the activity should be considered as an alternative action to
     * the data the user is currently viewing.  See also
     * {@link #CATEGORY_SELECTED_ALTERNATIVE} for an alternative action that
     * applies to the selection in a list of items.
     *
     * <p>Supporting this category means that you would like your activity to be
     * displayed in the set of alternative things the user can do, usually as
     * part of the current activity's options menu.  You will usually want to
     * include a specific label in the &lt;intent-filter&gt; of this action
     * describing to the user what it does.
     *
     * <p>The action of IntentFilter with this category is important in that it
     * describes the specific action the target will perform.  This generally
     * should not be a generic action (such as {@link #ACTION_VIEW}, but rather
     * a specific name such as "com.android.camera.action.CROP.  Only one
     * alternative of any particular action will be shown to the user, so using
     * a specific action like this makes sure that your alternative will be
     * displayed while also allowing other applications to provide their own
     * overrides of that particular action.
     */
    public static final String CATEGORY_ALTERNATIVE = "android.intent.category.ALTERNATIVE";

    /**
     * Used with {@link #ACTION_MAIN} to launch the browser application.
     * The activity should be able to browse the Internet.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_BROWSER = "android.intent.category.APP_BROWSER";

    /**
     * Used with {@link #ACTION_MAIN} to launch the calculator application.
     * The activity should be able to perform standard arithmetic operations.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_CALCULATOR = "android.intent.category.APP_CALCULATOR";

    /**
     * Used with {@link #ACTION_MAIN} to launch the calendar application.
     * The activity should be able to view and manipulate calendar entries.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_CALENDAR = "android.intent.category.APP_CALENDAR";

    /**
     * Used with {@link #ACTION_MAIN} to launch the contacts application.
     * The activity should be able to view and manipulate address book entries.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_CONTACTS = "android.intent.category.APP_CONTACTS";

    /**
     * Used with {@link #ACTION_MAIN} to launch the email application.
     * The activity should be able to send and receive email.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_EMAIL = "android.intent.category.APP_EMAIL";

    /**
     * Used with {@link #ACTION_MAIN} to launch the files application.
     * The activity should be able to browse and manage files stored on the device.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_FILES = "android.intent.category.APP_FILES";

    /**
     * Used with {@link #ACTION_MAIN} to launch the fitness application.
     * The activity should be able to give the user fitness information and manage workouts
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_FITNESS = "android.intent.category.APP_FITNESS";

    /**
     * Used with {@link #ACTION_MAIN} to launch the gallery application.
     * The activity should be able to view and manipulate image and video files
     * stored on the device.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_GALLERY = "android.intent.category.APP_GALLERY";

    /**
     * Used with {@link #ACTION_MAIN} to launch the maps application.
     * The activity should be able to show the user's current location and surroundings.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_MAPS = "android.intent.category.APP_MAPS";

    /**
     * This activity allows the user to browse and download new applications.
     */
    public static final String CATEGORY_APP_MARKET = "android.intent.category.APP_MARKET";

    /**
     * Used with {@link #ACTION_MAIN} to launch the messaging application.
     * The activity should be able to send and receive text messages.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_MESSAGING = "android.intent.category.APP_MESSAGING";

    /**
     * Used with {@link #ACTION_MAIN} to launch the music application.
     * The activity should be able to play, browse, or manipulate music files
     * stored on the device.
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_MUSIC = "android.intent.category.APP_MUSIC";

    /**
     * Used with {@link #ACTION_MAIN} to launch the weather application.
     * The activity should be able to give the user information about the weather
     * <p>NOTE: This should not be used as the primary key of an Intent,
     * since it will not result in the app launching with the correct
     * action and category.  Instead, use this with
     * {@link #makeMainSelectorActivity(String, String)} to generate a main
     * Intent with this category in the selector.</p>
     */
    public static final String CATEGORY_APP_WEATHER = "android.intent.category.APP_WEATHER";

    /**
     * Activities that can be safely invoked from a browser must support this
     * category.  For example, if the user is viewing a web page or an e-mail
     * and clicks on a link in the text, the Intent generated to execute that
     * link will require the BROWSABLE category, so that only activities
     * supporting this category will be considered as possible actions.  By
     * supporting this category, you are promising that there is nothing
     * damaging (without user intervention) that can happen by invoking any
     * matching Intent.
     */
    public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";

    /**
     * An activity to run when device is inserted into a car dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    public static final String CATEGORY_CAR_DOCK = "android.intent.category.CAR_DOCK";

    /**
     * Used to indicate that the activity can be used in a car environment.
     */
    public static final String CATEGORY_CAR_MODE = "android.intent.category.CAR_MODE";

    /**
     * Set if the activity should be an option for the default action
     * (center press) to perform on a piece of data.  Setting this will
     * hide from the user any activities without it set when performing an
     * action on some data.  Note that this is normally -not- set in the
     * Intent when initiating an action -- it is for use in intent filters
     * specified in packages.
     */
    public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";

    /**
     * An activity to run when device is inserted into a desk dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    public static final String CATEGORY_DESK_DOCK = "android.intent.category.DESK_DOCK";

    /**
     * This activity is a development preference panel.
     */
    public static final String CATEGORY_DEVELOPMENT_PREFERENCE = "android.intent.category.DEVELOPMENT_PREFERENCE";

    /**
     * Capable of running inside a parent activity container.
     */
    public static final String CATEGORY_EMBED = "android.intent.category.EMBED";

    /**
     * To be used as code under test for framework instrumentation tests.
     */
    public static final String CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST = "android.intent.category.FRAMEWORK_INSTRUMENTATION_TEST";

    /**
     * An activity to run when device is inserted into a digital (high end) dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    public static final String CATEGORY_HE_DESK_DOCK = "android.intent.category.HE_DESK_DOCK";

    /**
     * This is the home activity, that is the first activity that is displayed
     * when the device boots.
     */
    public static final String CATEGORY_HOME = "android.intent.category.HOME";

    /**
     * Provides information about the package it is in; typically used if
     * a package does not contain a {@link #CATEGORY_LAUNCHER} to provide
     * a front-door to the user without having to be shown in the all apps list.
     */
    public static final String CATEGORY_INFO = "android.intent.category.INFO";

    /**
     * Should be displayed in the top-level launcher.
     */
    public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";

    /**
     * Indicates an activity optimized for Leanback mode, and that should
     * be displayed in the Leanback launcher.
     */
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";

    /**
     * An activity to run when device is inserted into a analog (low end) dock.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    public static final String CATEGORY_LE_DESK_DOCK = "android.intent.category.LE_DESK_DOCK";

    /**
     * This activity may be exercised by the monkey or other automated test tools.
     */
    public static final String CATEGORY_MONKEY = "android.intent.category.MONKEY";

    /**
     * Used to indicate that an intent only wants URIs that can be opened with
     * {@link ContentResolver#openFileDescriptor(Uri, String)}. Openable URIs
     * must support at least the columns defined in {@link OpenableColumns} when
     * queried.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_CREATE_DOCUMENT
     */
    public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";

    /**
     * This activity is a preference panel.
     */
    public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";

    /**
     * To be used as a sample code example (not part of the normal user
     * experience).
     */
    public static final String CATEGORY_SAMPLE_CODE = "android.intent.category.SAMPLE_CODE";

    /**
     * The home activity shown on secondary displays that support showing home activities.
     */
    public static final String CATEGORY_SECONDARY_HOME = "android.intent.category.SECONDARY_HOME";

    /**
     * Set if the activity should be considered as an alternative selection
     * action to the data the user has currently selected.  This is like
     * {@link #CATEGORY_ALTERNATIVE}, but is used in activities showing a list
     * of items from which the user can select, giving them alternatives to the
     * default action that will be performed on it.
     */
    public static final String CATEGORY_SELECTED_ALTERNATIVE = "android.intent.category.SELECTED_ALTERNATIVE";

    /**
     * Intended to be used as a tab inside of a containing TabActivity.
     */
    public static final String CATEGORY_TAB = "android.intent.category.TAB";

    /**
     * To be used as a test (not part of the normal user experience).
     */
    public static final String CATEGORY_TEST = "android.intent.category.TEST";

    /**
     * Used to indicate that an intent filter can accept files which are not necessarily
     * openable by {@link ContentResolver#openFileDescriptor(Uri, String)}, but
     * at least streamable via
     * {@link ContentResolver#openTypedAssetFileDescriptor(Uri, String, Bundle)}
     * using one of the stream types exposed via
     * {@link ContentResolver#getStreamTypes(Uri, String)}.
     *
     * @see #ACTION_SEND
     * @see #ACTION_SEND_MULTIPLE
     */
    public static final String CATEGORY_TYPED_OPENABLE = "android.intent.category.TYPED_OPENABLE";

    /**
     * To be used as a unit test (run through the Test Harness).
     */
    public static final String CATEGORY_UNIT_TEST = "android.intent.category.UNIT_TEST";

    /**
     * Categories for activities that can participate in voice interaction.
     * An activity that supports this category must be prepared to run with
     * no UI shown at all (though in some case it may have a UI shown), and
     * rely on {@link android.app.VoiceInteractor} to interact with the user.
     */
    public static final String CATEGORY_VOICE = "android.intent.category.VOICE";

    /**
     * An activity to use for the launcher when the device is placed in a VR Headset viewer.
     * Used with {@link #ACTION_MAIN} to launch an activity.  For more
     * information, see {@link android.app.UiModeManager}.
     */
    public static final String CATEGORY_VR_HOME = "android.intent.category.VR_HOME";

    /**
     * Indicates that the content being shared with {@link #ACTION_SEND} represents an album
     * (e.g. containing photos).
     *
     * @see #EXTRA_CHOOSER_CONTENT_TYPE_HINT
     */
    public static final int CHOOSER_CONTENT_TYPE_ALBUM = 1;

    /**
     * Used as an int extra field in {@link android.app.AlarmManager} pending intents
     * to tell the application being invoked how many pending alarms are being
     * delivered with the intent.  For one-shot alarms this will always be 1.
     * For recurring alarms, this might be greater than 1 if the device was
     * asleep or powered off at the time an earlier alarm would have been
     * delivered.
     *
     * <p>Note: You must supply a <b>mutable</b> {@link android.app.PendingIntent} to
     * {@code AlarmManager} while setting your alarms to be able to read this value on receiving
     * them. <em>Mutability of pending intents must be explicitly specified by apps targeting
     * {@link Build.VERSION_CODES#S} or higher</em>.
     *
     * @see android.app.PendingIntent#FLAG_MUTABLE
     */
    public static final String EXTRA_ALARM_COUNT = "android.intent.extra.ALARM_COUNT";

    /**
     * Extra used to indicate that an intent can allow the user to select and
     * return multiple items. This is a boolean extra; the default is false. If
     * true, an implementation is allowed to present the user with a UI where
     * they can pick multiple items that are all returned to the caller. When
     * this happens, they should be returned as the {@link #getClipData()} part
     * of the result Intent.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     */
    public static final String EXTRA_ALLOW_MULTIPLE = "android.intent.extra.ALLOW_MULTIPLE";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Tells the installer UI to skip the confirmation with the user
     * if the .apk is replacing an existing one.
     * @deprecated As of {@link android.os.Build.VERSION_CODES#JELLY_BEAN}, Android
     * will no longer show an interstitial message about updating existing
     * applications so this is no longer needed.
     */
    @Deprecated
    public static final String EXTRA_ALLOW_REPLACE = "android.intent.extra.ALLOW_REPLACE";

    /**
     * An Intent[] describing additional, alternate choices you would like shown with
     * {@link #ACTION_CHOOSER}.
     *
     * <p>An app may be capable of providing several different payload types to complete a
     * user's intended action. For example, an app invoking {@link #ACTION_SEND} to share photos
     * with another app may use EXTRA_ALTERNATE_INTENTS to have the chooser transparently offer
     * several different supported sending mechanisms for sharing, such as the actual "image/*"
     * photo data or a hosted link where the photos can be viewed.</p>
     *
     * <p>The intent present in {@link #EXTRA_INTENT} will be treated as the
     * first/primary/preferred intent in the set. Additional intents specified in
     * this extra are ordered; by default intents that appear earlier in the array will be
     * preferred over intents that appear later in the array as matches for the same
     * target component. To alter this preference, a calling app may also supply
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER}.</p>
     */
    public static final String EXTRA_ALTERNATE_INTENTS = "android.intent.extra.ALTERNATE_INTENTS";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_ADDED} and
     * {@link android.content.Intent#ACTION_PACKAGE_REMOVED} intents to indicate that
     * the package is being archived. Either by removing the existing APK, or by installing
     * a package without an APK.
     */
    public static final String EXTRA_ARCHIVAL = "android.intent.extra.ARCHIVAL";

    /**
     * An optional field on {@link #ACTION_ASSIST} and containing additional contextual
     * information supplied by the current foreground app at the time of the assist request.
     * This is a {@link Bundle} of additional data.
     */
    public static final String EXTRA_ASSIST_CONTEXT = "android.intent.extra.ASSIST_CONTEXT";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the InputDevice id
     * that was used to invoke the assist.
     */
    public static final String EXTRA_ASSIST_INPUT_DEVICE_ID = "android.intent.extra.ASSIST_INPUT_DEVICE_ID";

    /**
     * An optional field on {@link #ACTION_ASSIST} suggesting that the user will likely use a
     * keyboard as the primary input device for assistance.
     */
    public static final String EXTRA_ASSIST_INPUT_HINT_KEYBOARD = "android.intent.extra.ASSIST_INPUT_HINT_KEYBOARD";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the name of the current foreground
     * application package at the time the assist was invoked.
     */
    public static final String EXTRA_ASSIST_PACKAGE = "android.intent.extra.ASSIST_PACKAGE";

    /**
     * An optional field on {@link #ACTION_ASSIST} containing the uid of the current foreground
     * application package at the time the assist was invoked.
     */
    public static final String EXTRA_ASSIST_UID = "android.intent.extra.ASSIST_UID";

    /**
     * A String[] holding attribution tags when used with
     * {@link #ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD}
     * and ACTION_MANAGE_PERMISSION_USAGE
     *
     * E.g. an attribution tag could be location_provider, com.google.android.gms.*, etc.
     */
    public static final String EXTRA_ATTRIBUTION_TAGS = "android.intent.extra.ATTRIBUTION_TAGS";

    /**
     * Used as a boolean extra field in {@link #ACTION_CHOOSER} intents to specify
     * whether to show the chooser or not when there is only one application available
     * to choose from.
     */
    public static final String EXTRA_AUTO_LAUNCH_SINGLE_CHOICE = "android.intent.extra.AUTO_LAUNCH_SINGLE_CHOICE";

    /**
     * A String[] holding e-mail addresses that should be blind carbon copied.
     */
    public static final String EXTRA_BCC = "android.intent.extra.BCC";

    /**
     * Used as a parcelable extra field in {@link #ACTION_APP_ERROR}, containing
     * the bug report.
     */
    public static final String EXTRA_BUG_REPORT = "android.intent.extra.BUG_REPORT";

    /**
     * An int extra used by activity started with
     * {@link #ACTION_LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE} to indicate status of the response.
     * This extra is used along with result code set to {@link android.app.Activity#RESULT_OK}.
     *
     * <p>The value for this extra can be one of the following:
     * <ul>
     *     <li>{@link #CAPTURE_CONTENT_FOR_NOTE_SUCCESS}</li>
     *     <li>{@link #CAPTURE_CONTENT_FOR_NOTE_FAILED}</li>
     *     <li>{@link #CAPTURE_CONTENT_FOR_NOTE_USER_CANCELED}</li>
     *     <li>{@link #CAPTURE_CONTENT_FOR_NOTE_WINDOW_MODE_UNSUPPORTED}</li>
     *     <li>{@link #CAPTURE_CONTENT_FOR_NOTE_BLOCKED_BY_ADMIN}</li>
     * </ul>
     */
    public static final String EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE = "android.intent.extra.CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE";

    /**
     * A String[] holding e-mail addresses that should be carbon copied.
     */
    public static final String EXTRA_CC = "android.intent.extra.CC";

    /**
     * @deprecated See {@link #EXTRA_CHANGED_COMPONENT_NAME_LIST}; this field
     * will contain only the first name in the list.
     */
    @Deprecated
    public static final String EXTRA_CHANGED_COMPONENT_NAME = "android.intent.extra.changed_component_name";

    /**
     * This field is part of {@link android.content.Intent#ACTION_PACKAGE_CHANGED},
     * and contains a string array of all of the components that have changed.  If
     * the state of the overall package has changed, then it will contain an entry
     * with the package name itself.
     */
    public static final String EXTRA_CHANGED_COMPONENT_NAME_LIST = "android.intent.extra.changed_component_name_list";

    /**
     * This field is part of
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_AVAILABLE},
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE},
     * {@link android.content.Intent#ACTION_PACKAGES_SUSPENDED},
     * {@link android.content.Intent#ACTION_PACKAGES_UNSUSPENDED}
     * and contains a string array of all of the components that have changed.
     */
    public static final String EXTRA_CHANGED_PACKAGE_LIST = "android.intent.extra.changed_package_list";

    /**
     * This field is part of
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_AVAILABLE},
     * {@link android.content.Intent#ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE}
     * and contains an integer array of uids of all of the components
     * that have changed.
     */
    public static final String EXTRA_CHANGED_UID_LIST = "android.intent.extra.changed_uid_list";

    /**
     * Optional argument used to provide a {@link ContentProvider} {@link Uri} to an
     * {@link #ACTION_CHOOSER} Intent which allows additional toggleable items to be included
     * in the sharing UI.
     * <p>
     * For example, this could be  used to show photos being shared in the context of the user's
     * entire photo roll, with the option to change the set of photos being shared.
     * <p>
     * When this is provided in an {@link #ACTION_CHOOSER} Intent with an {@link #ACTION_SEND} or
     * {@link #ACTION_SEND_MULTIPLE} target Intent, the sharesheet will query (see
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}) this URI to
     * retrieve a set of additional items available for selection. The set of items returned by the
     * content provider is expected to contain all the items from the {@link #EXTRA_STREAM}
     * argument, in their relative order, which will be marked as selected. The URI's authority
     * must be different from any shared items URI provided in {@link #EXTRA_STREAM} or returned by
     * the provider.
     *
     * <p>The {@link Bundle} argument of the
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)}
     * method will contains the original intent Chooser has been launched with under the
     * {@link #EXTRA_INTENT} key as a context for the current sharing session. The returned
     * {@link android.database.Cursor} should contain:
     * <ul>
     * <li>{@link android.service.chooser.AdditionalContentContract.Columns#URI} column for the item
     * URI.</li>
     * <li>Optional columns {@link MediaStore.MediaColumns#WIDTH} and
     * {@link MediaStore.MediaColumns#HEIGHT} for the dimensions of the preview image.
     * These columns can also be returned for each {@link #EXTRA_STREAM} item metadata
     * {@link ContentProvider#query(Uri, String[], Bundle, CancellationSignal)} call.</li>
     * <li>Optional {@link AdditionalContentContract.CursorExtraKeys#POSITION} extra that
     * specifies the cursor starting position; the item at this position is expected to match the
     * item specified by {@link #EXTRA_CHOOSER_FOCUSED_ITEM_POSITION}.</li></ul></p>
     *
     * <p>When the user makes a selection change,
     * {@link ContentProvider#call(String, String, Bundle)} method will be invoked with the "method"
     * argument set to
     * {@link android.service.chooser.AdditionalContentContract.MethodNames#ON_SELECTION_CHANGED},
     * the "arg" argument set to this argument's value, and the "extras" {@link Bundle} argument
     * containing {@link #EXTRA_INTENT} key containing the original intent Chooser has been launched
     * with but with the modified target intent --Chooser will modify the target intent according to
     * the selection changes made by the user.
     * Applications may implement this method to change any of the following Chooser arguments by
     * returning new values in the result bundle:
     * {@link #EXTRA_CHOOSER_TARGETS},
     * {@link #EXTRA_ALTERNATE_INTENTS},
     * {@link #EXTRA_CHOOSER_CUSTOM_ACTIONS},
     * {@link #EXTRA_CHOOSER_MODIFY_SHARE_ACTION},
     * {@link #EXTRA_METADATA_TEXT},
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER},
     * {@link #EXTRA_CHOOSER_RESULT_INTENT_SENDER},
     * {@link #EXTRA_EXCLUDE_COMPONENTS}.
     * </p>
     */
    public static final String EXTRA_CHOOSER_ADDITIONAL_CONTENT_URI = "android.intent.extra.CHOOSER_ADDITIONAL_CONTENT_URI";

    /**
     * Optional integer extra to be used with {@link #ACTION_CHOOSER} to describe conteng being
     * shared.
     * <p>
     * If provided, sharesheets may customize their UI presentation to include a more precise
     * description of the content being shared.
     *
     * @see #CHOOSER_CONTENT_TYPE_ALBUM
     * @see #createChooser(Intent, CharSequence)
     */
    public static final String EXTRA_CHOOSER_CONTENT_TYPE_HINT = "android.intent.extra.CHOOSER_CONTENT_TYPE_HINT";

    /**
     * A Parcelable[] of {@link ChooserAction} objects to provide the Android Sharesheet with
     * app-specific actions to be presented to the user when invoking {@link #ACTION_CHOOSER}.
     * You can provide as many as five custom actions.
     */
    public static final String EXTRA_CHOOSER_CUSTOM_ACTIONS = "android.intent.extra.CHOOSER_CUSTOM_ACTIONS";

    /**
     * Optional argument to be used with {@link #EXTRA_CHOOSER_ADDITIONAL_CONTENT_URI}, used in
     * combination with {@link #EXTRA_CHOOSER_ADDITIONAL_CONTENT_URI}.
     * An integer, zero-based index into {@link #EXTRA_STREAM} argument indicating the item that
     * should be focused by the Chooser in preview.
     */
    public static final String EXTRA_CHOOSER_FOCUSED_ITEM_POSITION = "android.intent.extra.CHOOSER_FOCUSED_ITEM_POSITION";

    /**
     * Optional argument to be used with {@link #ACTION_CHOOSER}.
     * A {@link ChooserAction} to allow the user to modify what is being shared in some way. This
     * may be integrated into the content preview on sharesheets that have a preview UI.
     */
    public static final String EXTRA_CHOOSER_MODIFY_SHARE_ACTION = "android.intent.extra.CHOOSER_MODIFY_SHARE_ACTION";

    /**
     * An {@link IntentSender} for an Activity that will be invoked when the user makes a selection
     * from the chooser activity presented by {@link #ACTION_CHOOSER}.
     *
     * <p>An app preparing an action for another app to complete may wish to allow the user to
     * disambiguate between several options for completing the action based on the chosen target
     * or otherwise refine the action before it is invoked.
     * </p>
     *
     * <p>When sent, this IntentSender may be filled in with the following extras:</p>
     * <ul>
     *     <li>{@link #EXTRA_INTENT} The first intent that matched the user's chosen target</li>
     *     <li>{@link #EXTRA_ALTERNATE_INTENTS} Any additional intents that also matched the user's
     *     chosen target beyond the first</li>
     *     <li>{@link #EXTRA_RESULT_RECEIVER} A {@link ResultReceiver} that the refinement activity
     *     should fill in and send once the disambiguation is complete</li>
     * </ul>
     */
    public static final String EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER = "android.intent.extra.CHOOSER_REFINEMENT_INTENT_SENDER";

    /**
     * A {@link ChooserResult} which describes how the sharing session completed.
     * <p>
     * An instance is supplied to the optional IntentSender provided to
     * {@link #createChooser(Intent, CharSequence, IntentSender)} when the session completes.
     */
    public static final String EXTRA_CHOOSER_RESULT = "android.intent.extra.CHOOSER_RESULT";

    /**
     * An {@link IntentSender} that will be notified when a user successfully chooses a target
     * component or initiates an action such as copy or edit within an {@link #ACTION_CHOOSER}
     * activity. The IntentSender will have the extra {@link #EXTRA_CHOOSER_RESULT} describing
     * the result.
     */
    public static final String EXTRA_CHOOSER_RESULT_INTENT_SENDER = "android.intent.extra.CHOOSER_RESULT_INTENT_SENDER";

    /**
     * A {@link android.service.chooser.ChooserTarget ChooserTarget[]} for {@link #ACTION_CHOOSER}
     * describing additional high-priority deep-link targets for the chooser to present to the user.
     *
     * <p>Targets provided in this way will be presented inline with all other targets provided
     * by services from other apps. They will be prioritized before other service targets, but
     * after those targets provided by sources that the user has manually pinned to the front.
     * You can provide up to two targets on this extra (the limit of two targets
     * starts in Android 10).</p>
     *
     * @see #ACTION_CHOOSER
     */
    public static final String EXTRA_CHOOSER_TARGETS = "android.intent.extra.CHOOSER_TARGETS";

    /**
     * The {@link ComponentName} chosen by the user to complete an action.
     *
     * @see #EXTRA_CHOSEN_COMPONENT_INTENT_SENDER
     */
    public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";

    /**
     * An {@link IntentSender} that will be notified if a user successfully chooses a target
     * component to handle an action in an {@link #ACTION_CHOOSER} activity. The IntentSender
     * will have the extra {@link #EXTRA_CHOSEN_COMPONENT} appended to it containing the
     * {@link ComponentName} of the chosen component.
     *
     * <p>In some situations this callback may never come, for example if the user abandons
     * the chooser, switches to another task or any number of other reasons. Apps should not
     * be written assuming that this callback will always occur.</p>
     */
    public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";

    /**
     * Intent extra: A {@link ComponentName} value.
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_COMPONENT_NAME = "android.intent.extra.COMPONENT_NAME";

    /**
     * An {@code ArrayList} of {@code String} annotations describing content for
     * {@link #ACTION_CHOOSER}.
     *
     * <p>If {@link #EXTRA_CONTENT_ANNOTATIONS} is present in an intent used to start a
     * {@link #ACTION_CHOOSER} activity, the first three annotations will be used to rank apps.</p>
     *
     * <p>Annotations should describe the major components or topics of the content. It is up to
     * apps initiating {@link #ACTION_CHOOSER} to learn and add annotations. Annotations should be
     * learned in advance, e.g., when creating or saving content, to avoid increasing latency to
     * start {@link #ACTION_CHOOSER}. Names of customized annotations should not contain the colon
     * character. Performance on customized annotations can suffer, if they are rarely used for
     * {@link #ACTION_CHOOSER} in the past 14 days. Therefore, it is recommended to use the
     * following annotations when applicable.</p>
     * <ul>
     *     <li>"product" represents that the topic of the content is mainly about products, e.g.,
     *     health & beauty, and office supplies.</li>
     *     <li>"emotion" represents that the topic of the content is mainly about emotions, e.g.,
     *     happy, and sad.</li>
     *     <li>"person" represents that the topic of the content is mainly about persons, e.g.,
     *     face, finger, standing, and walking.</li>
     *     <li>"child" represents that the topic of the content is mainly about children, e.g.,
     *     child, and baby.</li>
     *     <li>"selfie" represents that the topic of the content is mainly about selfies.</li>
     *     <li>"crowd" represents that the topic of the content is mainly about crowds.</li>
     *     <li>"party" represents that the topic of the content is mainly about parties.</li>
     *     <li>"animal" represent that the topic of the content is mainly about animals.</li>
     *     <li>"plant" represents that the topic of the content is mainly about plants, e.g.,
     *     flowers.</li>
     *     <li>"vacation" represents that the topic of the content is mainly about vacations.</li>
     *     <li>"fashion" represents that the topic of the content is mainly about fashion, e.g.
     *     sunglasses, jewelry, handbags and clothing.</li>
     *     <li>"material" represents that the topic of the content is mainly about materials, e.g.,
     *     paper, and silk.</li>
     *     <li>"vehicle" represents that the topic of the content is mainly about vehicles, like
     *     cars, and boats.</li>
     *     <li>"document" represents that the topic of the content is mainly about documents, e.g.
     *     posters.</li>
     *     <li>"design" represents that the topic of the content is mainly about design, e.g. arts
     *     and designs of houses.</li>
     *     <li>"holiday" represents that the topic of the content is mainly about holidays, e.g.,
     *     Christmas and Thanksgiving.</li>
     * </ul>
     */
    public static final String EXTRA_CONTENT_ANNOTATIONS = "android.intent.extra.CONTENT_ANNOTATIONS";

    /**
     * Optional CharSequence extra to provide a search query.
     * The format of this query is dependent on the receiving application.
     *
     * <p>Applicable to {@link Intent} with actions:
     * <ul>
     *      <li>{@link Intent#ACTION_GET_CONTENT}</li>
     *      <li>{@link Intent#ACTION_OPEN_DOCUMENT}</li>
     * </ul>
     */
    public static final String EXTRA_CONTENT_QUERY = "android.intent.extra.CONTENT_QUERY";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to indicate whether this represents a full uninstall (removing
     * both the code and its data) or a partial uninstall (leaving its data,
     * implying that this is an update).
     */
    public static final String EXTRA_DATA_REMOVED = "android.intent.extra.DATA_REMOVED";

    /**
     * Used as an int extra field in {@link android.content.Intent#ACTION_DOCK_EVENT}
     * intents to request the dock state.  Possible values are
     * {@link android.content.Intent#EXTRA_DOCK_STATE_UNDOCKED},
     * {@link android.content.Intent#EXTRA_DOCK_STATE_DESK}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_CAR}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_LE_DESK}, or
     * {@link android.content.Intent#EXTRA_DOCK_STATE_HE_DESK}.
     */
    public static final String EXTRA_DOCK_STATE = "android.intent.extra.DOCK_STATE";

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a car dock.
     */
    public static final int EXTRA_DOCK_STATE_CAR = 2;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a desk dock.
     */
    public static final int EXTRA_DOCK_STATE_DESK = 1;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a digital (high end) dock.
     */
    public static final int EXTRA_DOCK_STATE_HE_DESK = 4;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is in a analog (low end) dock.
     */
    public static final int EXTRA_DOCK_STATE_LE_DESK = 3;

    /**
     * Used as an int value for {@link android.content.Intent#EXTRA_DOCK_STATE}
     * to represent that the phone is not in any dock.
     */
    public static final int EXTRA_DOCK_STATE_UNDOCKED = 0;

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED} or
     * {@link android.content.Intent#ACTION_PACKAGE_CHANGED} intents to override the default action
     * of restarting the application.
     */
    public static final String EXTRA_DONT_KILL_APP = "android.intent.extra.DONT_KILL_APP";

    /**
     * Intent extra: The number of milliseconds.
     * <p>
     * Type: long
     * </p>
     */
    public static final String EXTRA_DURATION_MILLIS = "android.intent.extra.DURATION_MILLIS";

    /**
     * A String[] holding e-mail addresses that should be delivered to.
     */
    public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";

    /**
     * A long representing the end timestamp (epoch time in millis) of the permission usage when
     * used with {@link #ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD}
     * and ACTION_MANAGE_PERMISSION_USAGE
     */
    public static final String EXTRA_END_TIME = "android.intent.extra.END_TIME";

    /**
     * A {@link ComponentName ComponentName[]} describing components that should be filtered out
     * and omitted from a list of components presented to the user.
     *
     * <p>When used with {@link #ACTION_CHOOSER}, the chooser will omit any of the components
     * in this array if it otherwise would have shown them. Useful for omitting specific targets
     * from your own package or other apps from your organization if the idea of sending to those
     * targets would be redundant with other app functionality. Filtered components will not
     * be able to present targets from an associated <code>ChooserTargetService</code>.</p>
     */
    public static final String EXTRA_EXCLUDE_COMPONENTS = "android.intent.extra.EXCLUDE_COMPONENTS";

    /**
     * Extra that can be included on activity intents coming from the storage UI
     * when it launches sub-activities to manage various types of storage.  For example,
     * it may use {@link #ACTION_VIEW} with a "image/*" MIME type to have an app show
     * the images on the device, and in that case also include this extra to tell the
     * app it is coming from the storage UI so should help the user manage storage of
     * this type.
     */
    public static final String EXTRA_FROM_STORAGE = "android.intent.extra.FROM_STORAGE";

    /**
     * A constant String that is associated with the Intent, used with
     * {@link #ACTION_SEND} to supply an alternative to {@link #EXTRA_TEXT}
     * as HTML formatted text.  Note that you <em>must</em> also supply
     * {@link #EXTRA_TEXT}.
     */
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";

    /**
     * Optional index with semantics depending on the intent action.
     *
     * <p>The value must be an integer greater or equal to 0.
     * @see #ACTION_QUICK_VIEW
     */
    public static final String EXTRA_INDEX = "android.intent.extra.INDEX";

    /**
     * A Parcelable[] of {@link Intent} or
     * {@link android.content.pm.LabeledIntent} objects as set with
     * {@link #putExtra(String, Parcelable[])} to place
     * at the front of the list of choices, when shown to the user with an
     * {@link #ACTION_CHOOSER}. You can choose up to two additional activities
     * to show before the app suggestions (the limit of two additional activities starts in
     * Android 10).
     */
    public static final String EXTRA_INITIAL_INTENTS = "android.intent.extra.INITIAL_INTENTS";

    /**
     * Used as a string extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Specifies the installer package name; this package will receive the
     * {@link #ACTION_APP_ERROR} intent.
     */
    public static final String EXTRA_INSTALLER_PACKAGE_NAME = "android.intent.extra.INSTALLER_PACKAGE_NAME";

    /**
     * An Intent describing the choices you would like shown with
     * {@link #ACTION_PICK_ACTIVITY} or {@link #ACTION_CHOOSER}.
     */
    public static final String EXTRA_INTENT = "android.intent.extra.INTENT";

    /**
     * A {@link android.view.KeyEvent} object containing the event that
     * triggered the creation of the Intent it is in.
     */
    public static final String EXTRA_KEY_EVENT = "android.intent.extra.KEY_EVENT";

    /**
     * Intent extra: A {@link android.os.LocaleList}
     * <p>
     * Type: LocaleList
     * </p>
     */
    public static final String EXTRA_LOCALE_LIST = "android.intent.extra.LOCALE_LIST";

    /**
     * Extra used to indicate that an intent should only return data that is on
     * the local device. This is a boolean extra; the default is false. If true,
     * an implementation should only allow the user to select data that is
     * already on the device, not requiring it be downloaded from a remote
     * service when opened.
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     * @see #ACTION_OPEN_DOCUMENT_TREE
     * @see #ACTION_CREATE_DOCUMENT
     */
    public static final String EXTRA_LOCAL_ONLY = "android.intent.extra.LOCAL_ONLY";

    /**
     * Intent extra: ID of the context used on {@link #ACTION_VIEW_LOCUS}.
     *
     * <p>
     * Type: {@link LocusId}
     * </p>
     */
    public static final String EXTRA_LOCUS_ID = "android.intent.extra.LOCUS_ID";

    /**
     * A CharSequence of additional text describing the content being shared. This text will be
     * displayed to the user as a part of the sharesheet when included in an
     * {@link #ACTION_CHOOSER} {@link Intent}.
     *
     * <p>e.g. When sharing a photo, metadata could inform the user that location data is included
     * in the photo they are sharing.</p>
     */
    public static final String EXTRA_METADATA_TEXT = "android.intent.extra.METADATA_TEXT";

    /**
     * Extra used to communicate a set of acceptable MIME types. The type of the
     * extra is {@code String[]}. Values may be a combination of concrete MIME
     * types (such as "image/png") and/or partial MIME types (such as
     * "audio/*").
     *
     * @see #ACTION_GET_CONTENT
     * @see #ACTION_OPEN_DOCUMENT
     */
    public static final String EXTRA_MIME_TYPES = "android.intent.extra.MIME_TYPES";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} to install a
     * package.  Specifies that the application being installed should not be
     * treated as coming from an unknown source, but as coming from the app
     * invoking the Intent.  For this to work you must start the installer with
     * startActivityForResult().
     */
    public static final String EXTRA_NOT_UNKNOWN_SOURCE = "android.intent.extra.NOT_UNKNOWN_SOURCE";

    /**
     * Used as a URI extra field with {@link #ACTION_INSTALL_PACKAGE} and
     * {@link #ACTION_VIEW} to indicate the URI from which the local APK in the Intent
     * data field originated from.
     */
    public static final String EXTRA_ORIGINATING_URI = "android.intent.extra.ORIGINATING_URI";

    /**
     * String array of package names.
     */
    public static final String EXTRA_PACKAGES = "android.intent.extra.PACKAGES";

    /**
     * Intent extra: An app package name.
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_PACKAGE_NAME = "android.intent.extra.PACKAGE_NAME";

    /**
     * Intent extra: The name of a permission group.
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_PERMISSION_GROUP_NAME = "android.intent.extra.PERMISSION_GROUP_NAME";

    /**
     * A String holding the phone number originally entered in
     * {@link android.content.Intent#ACTION_NEW_OUTGOING_CALL}, or the actual
     * number to call in a {@link android.content.Intent#ACTION_CALL}.
     */
    public static final String EXTRA_PHONE_NUMBER = "android.intent.extra.PHONE_NUMBER";

    /**
     * The name of the extra used to define the text to be processed, as a
     * CharSequence. Note that this may be a styled CharSequence, so you must use
     * {@link Bundle#getCharSequence(String) Bundle.getCharSequence()} to retrieve it.
     */
    public static final String EXTRA_PROCESS_TEXT = "android.intent.extra.PROCESS_TEXT";

    /**
     * The name of the boolean extra used to define if the processed text will be used as read-only.
     */
    public static final String EXTRA_PROCESS_TEXT_READONLY = "android.intent.extra.PROCESS_TEXT_READONLY";

    /**
     * An optional extra of {@code String[]} indicating which quick view features should be made
     * available to the user in the quick view UI while handing a
     * {@link Intent#ACTION_QUICK_VIEW} intent.
     * <li>Enumeration of features here is not meant to restrict capabilities of the quick viewer.
     * Quick viewer can implement features not listed below.
     * <li>Features included at this time are: {@link QuickViewConstants#FEATURE_VIEW},
     * {@link QuickViewConstants#FEATURE_EDIT}, {@link QuickViewConstants#FEATURE_DELETE},
     * {@link QuickViewConstants#FEATURE_DOWNLOAD}, {@link QuickViewConstants#FEATURE_SEND},
     * {@link QuickViewConstants#FEATURE_PRINT}.
     * <p>
     * Requirements:
     * <li>Quick viewer shouldn't show a feature if the feature is absent in
     * {@link #EXTRA_QUICK_VIEW_FEATURES}.
     * <li>When {@link #EXTRA_QUICK_VIEW_FEATURES} is not present, quick viewer should follow
     * internal policies.
     * <li>Presence of an feature in {@link #EXTRA_QUICK_VIEW_FEATURES}, does not constitute a
     * requirement that the feature be shown. Quick viewer may, according to its own policies,
     * disable or hide features.
     *
     * @see #ACTION_QUICK_VIEW
     */
    public static final String EXTRA_QUICK_VIEW_FEATURES = "android.intent.extra.QUICK_VIEW_FEATURES";

    /**
     * Optional boolean extra indicating whether quiet mode has been switched on or off.
     * When a profile goes into quiet mode, all apps in the profile are killed and the
     * profile user is stopped. Widgets originating from the profile are masked, and app
     * launcher icons are grayed out.
     */
    public static final String EXTRA_QUIET_MODE = "android.intent.extra.QUIET_MODE";

    /**
     * This extra can be used with any Intent used to launch an activity, supplying information
     * about who is launching that activity.  This field contains a {@link android.net.Uri}
     * object, typically an http: or https: URI of the web site that the referral came from;
     * it can also use the {@link #URI_ANDROID_APP_SCHEME android-app:} scheme to identify
     * a native application that it came from.
     *
     * <p>To retrieve this value in a client, use {@link android.app.Activity#getReferrer}
     * instead of directly retrieving the extra.  It is also valid for applications to
     * instead supply {@link #EXTRA_REFERRER_NAME} for cases where they can only create
     * a string, not a Uri; the field here, if supplied, will always take precedence,
     * however.</p>
     *
     * @see #EXTRA_REFERRER_NAME
     */
    public static final String EXTRA_REFERRER = "android.intent.extra.REFERRER";

    /**
     * Alternate version of {@link #EXTRA_REFERRER} that supplies the URI as a String rather
     * than a {@link android.net.Uri} object.  Only for use in cases where Uri objects can
     * not be created, in particular when Intent extras are supplied through the
     * {@link #URI_INTENT_SCHEME intent:} or {@link #URI_ANDROID_APP_SCHEME android-app:}
     * schemes.
     *
     * @see #EXTRA_REFERRER
     */
    public static final String EXTRA_REFERRER_NAME = "android.intent.extra.REFERRER_NAME";

    /**
     * Used in the extra field in the remote intent. It's a string token passed with the
     * remote intent.
     */
    public static final String EXTRA_REMOTE_INTENT_TOKEN = "android.intent.extra.remote_intent_token";

    /**
     * A Bundle forming a mapping of potential target package names to different extras Bundles
     * to add to the default intent extras in {@link #EXTRA_INTENT} when used with
     * {@link #ACTION_CHOOSER}. Each key should be a package name. The package need not
     * be currently installed on the device.
     *
     * <p>An application may choose to provide alternate extras for the case where a user
     * selects an activity from a predetermined set of target packages. If the activity
     * the user selects from the chooser belongs to a package with its package name as
     * a key in this bundle, the corresponding extras for that package will be merged with
     * the extras already present in the intent at {@link #EXTRA_INTENT}. If a replacement
     * extra has the same key as an extra already present in the intent it will overwrite
     * the extra from the intent.</p>
     *
     * <p><em>Examples:</em>
     * <ul>
     *     <li>An application may offer different {@link #EXTRA_TEXT} to an application
     *     when sharing with it via {@link #ACTION_SEND}, augmenting a link with additional query
     *     parameters for that target.</li>
     *     <li>An application may offer additional metadata for known targets of a given intent
     *     to pass along information only relevant to that target such as account or content
     *     identifiers already known to that application.</li>
     * </ul></p>
     */
    public static final String EXTRA_REPLACEMENT_EXTRAS = "android.intent.extra.REPLACEMENT_EXTRAS";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to indicate that this is a replacement of the package, so this
     * broadcast will immediately be followed by an add broadcast for a
     * different version of the same package.
     */
    public static final String EXTRA_REPLACING = "android.intent.extra.REPLACING";

    /**
     * Extra sent in the intent to the BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}. The type of the extra is a Bundle containing
     * the restrictions as key/value pairs.
     */
    public static final String EXTRA_RESTRICTIONS_BUNDLE = "android.intent.extra.restrictions_bundle";

    /**
     * Extra used in the response from a BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}.
     */
    public static final String EXTRA_RESTRICTIONS_INTENT = "android.intent.extra.restrictions_intent";

    /**
     * Extra used in the response from a BroadcastReceiver that handles
     * {@link #ACTION_GET_RESTRICTION_ENTRIES}. The type of the extra is
     * <code>ArrayList&lt;RestrictionEntry&gt;</code>.
     */
    public static final String EXTRA_RESTRICTIONS_LIST = "android.intent.extra.restrictions_list";

    /**
     * A {@link ResultReceiver} used to return data back to the sender.
     *
     * <p>Used to complete an app-specific
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER refinement} for {@link #ACTION_CHOOSER}.</p>
     *
     * <p>If {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER} is present in the intent
     * used to start a {@link #ACTION_CHOOSER} activity this extra will be
     * {@link #fillIn(Intent, int) filled in} to that {@link IntentSender} and sent
     * when the user selects a target component from the chooser. It is up to the recipient
     * to send a result to this ResultReceiver to signal that disambiguation is complete
     * and that the chooser should invoke the user's choice.</p>
     *
     * <p>The disambiguator should provide a Bundle to the ResultReceiver with an intent
     * assigned to the key {@link #EXTRA_INTENT}. This supplied intent will be used by the chooser
     * to match and fill in the final Intent or ChooserTarget before starting it.
     * The supplied intent must {@link #filterEquals(Intent) match} one of the intents from
     * {@link #EXTRA_INTENT} or {@link #EXTRA_ALTERNATE_INTENTS} passed to
     * {@link #EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER} to be accepted.</p>
     *
     * <p>The result code passed to the ResultReceiver should be
     * {@link android.app.Activity#RESULT_OK} if the refinement succeeded and the supplied intent's
     * target in the chooser should be started, or {@link android.app.Activity#RESULT_CANCELED} if
     * the chooser should finish without starting a target.</p>
     */
    public static final String EXTRA_RESULT_RECEIVER = "android.intent.extra.RESULT_RECEIVER";

    /**
     * Used as a boolean extra field with {@link #ACTION_INSTALL_PACKAGE} or
     * {@link #ACTION_UNINSTALL_PACKAGE}.  Specifies that the installer UI should
     * return to the application the result code of the install/uninstall.  The returned result
     * code will be {@link android.app.Activity#RESULT_OK} on success or
     * {@link android.app.Activity#RESULT_FIRST_USER} on failure.
     */
    public static final String EXTRA_RETURN_RESULT = "android.intent.extra.RETURN_RESULT";

    /**
     * The name of the extra used to define the icon, as a Bitmap, of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     * @deprecated Replaced with {@link android.content.pm.ShortcutManager#createShortcutResultIntent}
     */
    @Deprecated
    public static final String EXTRA_SHORTCUT_ICON = "android.intent.extra.shortcut.ICON";

    /**
     * The name of the extra used to define the icon, as a ShortcutIconResource, of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     * @see android.content.Intent.ShortcutIconResource
     * @deprecated Replaced with {@link android.content.pm.ShortcutManager#createShortcutResultIntent}
     */
    @Deprecated
    public static final String EXTRA_SHORTCUT_ICON_RESOURCE = "android.intent.extra.shortcut.ICON_RESOURCE";

    /**
     * Intent extra: ID of the shortcut used to send the share intent. Will be sent with
     * {@link #ACTION_SEND}.
     *
     * @see ShortcutInfo#getId()
     *
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";

    /**
     * The name of the extra used to define the Intent of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     * @deprecated Replaced with {@link android.content.pm.ShortcutManager#createShortcutResultIntent}
     */
    @Deprecated
    public static final String EXTRA_SHORTCUT_INTENT = "android.intent.extra.shortcut.INTENT";

    /**
     * The name of the extra used to define the name of a shortcut.
     *
     * @see #ACTION_CREATE_SHORTCUT
     * @deprecated Replaced with {@link android.content.pm.ShortcutManager#createShortcutResultIntent}
     */
    @Deprecated
    public static final String EXTRA_SHORTCUT_NAME = "android.intent.extra.shortcut.NAME";

    /**
     * Optional extra for {@link #ACTION_SHUTDOWN} that allows the sender to qualify that
     * this shutdown is only for the user space of the system, not a complete shutdown.
     * When this is true, hardware devices can use this information to determine that
     * they shouldn't do a complete shutdown of their device since this is not a
     * complete shutdown down to the kernel, but only user space restarting.
     * The default if not supplied is false.
     */
    public static final String EXTRA_SHUTDOWN_USERSPACE_ONLY = "android.intent.extra.SHUTDOWN_USERSPACE_ONLY";

    /**
     * Intent extra: An app split name.
     * <p>
     * Type: String
     * </p>
     */
    public static final String EXTRA_SPLIT_NAME = "android.intent.extra.SPLIT_NAME";

    /**
     * A long representing the start timestamp (epoch time in millis) of the permission usage
     * when used with {@link #ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD}
     * and ACTION_MANAGE_PERMISSION_USAGE
     */
    public static final String EXTRA_START_TIME = "android.intent.extra.START_TIME";

    /**
     * A content: URI holding a stream of data associated with the Intent,
     * used with {@link #ACTION_SEND} to supply the data being sent.
     */
    public static final String EXTRA_STREAM = "android.intent.extra.STREAM";

    /**
     * A constant string holding the desired subject line of a message.
     */
    public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";

    /**
     * Intent extra: A {@link Bundle} of extras for a package being suspended. Will be sent as an
     * extra with {@link #ACTION_MY_PACKAGE_SUSPENDED}.
     *
     * <p>The contents of this {@link Bundle} are a contract between the suspended app and the
     * suspending app, i.e. any app with the permission {@code android.permission.SUSPEND_APPS}.
     * This is meant to enable the suspended app to better handle the state of being suspended.
     *
     * @see #ACTION_MY_PACKAGE_SUSPENDED
     * @see #ACTION_MY_PACKAGE_UNSUSPENDED
     * @see PackageManager#isPackageSuspended()
     * @see PackageManager#getSuspendedPackageAppExtras()
     */
    public static final String EXTRA_SUSPENDED_PACKAGE_EXTRAS = "android.intent.extra.SUSPENDED_PACKAGE_EXTRAS";

    /**
     * The initial data to place in a newly created record.  Use with
     * {@link #ACTION_INSERT}.  The data here is a Map containing the same
     * fields as would be given to the underlying ContentProvider.insert()
     * call.
     */
    public static final String EXTRA_TEMPLATE = "android.intent.extra.TEMPLATE";

    /**
     * A constant CharSequence that is associated with the Intent, used with
     * {@link #ACTION_SEND} to supply the literal data to be sent.  Note that
     * this may be a styled CharSequence, so you must use
     * {@link Bundle#getCharSequence(String) Bundle.getCharSequence()} to
     * retrieve it.
     */
    public static final String EXTRA_TEXT = "android.intent.extra.TEXT";

    /**
     * Optional extra specifying a time in milliseconds. The timebase depends on the Intent
     * including this extra. The value must be non-negative.
     * <p>
     * Type: long
     * </p>
     */
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    /**
     * Extra sent with {@link #ACTION_TIMEZONE_CHANGED} specifying the new time zone of the device.
     *
     * <p>Type: String, the same as returned by {@link TimeZone#getID()} to identify time zones.
     */
    public static final String EXTRA_TIMEZONE = "time-zone";

    /**
     * A CharSequence dialog title to provide to the user when used with a
     * {@link #ACTION_CHOOSER}.
     */
    public static final String EXTRA_TITLE = "android.intent.extra.TITLE";

    /**
     * Used as an int extra field in {@link android.content.Intent#ACTION_UID_REMOVED}
     * intents to supply the uid the package had been assigned.  Also an optional
     * extra in {@link android.content.Intent#ACTION_PACKAGE_REMOVED} or
     * {@link android.content.Intent#ACTION_PACKAGE_CHANGED} for the same
     * purpose.
     */
    public static final String EXTRA_UID = "android.intent.extra.UID";

    /**
     * The {@link UserHandle} carried with intents.
     */
    public static final String EXTRA_USER = "android.intent.extra.USER";

    /**
     * Used as a boolean extra field in {@link android.content.Intent#ACTION_PACKAGE_REMOVED}
     * intents to signal that the application was removed with the user-initiated action.
     */
    public static final String EXTRA_USER_INITIATED = "android.intent.extra.USER_INITIATED";

    /**
     * A boolean extra used with {@link #ACTION_CREATE_NOTE} indicating whether the launched
     * note-taking activity should show a UI that is suitable to use with stylus input.
     */
    public static final String EXTRA_USE_STYLUS_MODE = "android.intent.extra.USE_STYLUS_MODE";

    /**
     * Use with {@link #fillIn} to allow the current action value to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_ACTION = 1;

    /**
     * Use with {@link #fillIn} to allow the current categories to be
     * overwritten, even if they are already set.
     */
    public static final int FILL_IN_CATEGORIES = 4;

    /**
     * Use with {@link #fillIn} to allow the current ClipData to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_CLIP_DATA = 128;

    /**
     * Use with {@link #fillIn} to allow the current component value to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_COMPONENT = 8;

    /**
     * Use with {@link #fillIn} to allow the current data or type value
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_DATA = 2;

    /**
     * Use with {@link #fillIn} to allow the current identifier value to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_IDENTIFIER = 256;

    /**
     * Use with {@link #fillIn} to allow the current package value to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_PACKAGE = 16;

    /**
     * Use with {@link #fillIn} to allow the current selector to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_SELECTOR = 64;

    /**
     * Use with {@link #fillIn} to allow the current bounds rectangle to be
     * overwritten, even if it is already set.
     */
    public static final int FILL_IN_SOURCE_BOUNDS = 32;

    /**
     * This flag is not normally set by application code, but set for you by
     * the system as described in the
     * {@link android.R.styleable#AndroidManifestActivity_launchMode
     * launchMode} documentation for the singleTask mode.
     */
    public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 4194304;

    /**
     * If set in an Intent passed to {@link Context#startActivity Context.startActivity()},
     * this flag will cause any existing task that would be associated with the
     * activity to be cleared before the activity is started.  That is, the activity
     * becomes the new root of an otherwise empty task, and any old activities
     * are finished.  This can only be used in conjunction with {@link #FLAG_ACTIVITY_NEW_TASK}.
     */
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 32768;

    /**
     * If set, and the activity being launched is already running in the
     * current task, then instead of launching a new instance of that activity,
     * all of the other activities on top of it will be closed and this Intent
     * will be delivered to the (now on top) old activity as a new Intent.
     *
     * <p>For example, consider a task consisting of the activities: A, B, C, D.
     * If D calls startActivity() with an Intent that resolves to the component
     * of activity B, then C and D will be finished and B receive the given
     * Intent, resulting in the stack now being: A, B.
     *
     * <p>The currently running instance of activity B in the above example will
     * either receive the new intent you are starting here in its
     * onNewIntent() method, or be itself finished and restarted with the
     * new intent.  If it has declared its launch mode to be "multiple" (the
     * default) and you have not set {@link #FLAG_ACTIVITY_SINGLE_TOP} in
     * the same intent, then it will be finished and re-created; for all other
     * launch modes or if {@link #FLAG_ACTIVITY_SINGLE_TOP} is set then this
     * Intent will be delivered to the current instance's onNewIntent().
     *
     * <p>This launch mode can also be used to good effect in conjunction with
     * {@link #FLAG_ACTIVITY_NEW_TASK}: if used to start the root activity
     * of a task, it will bring any currently running instance of that task
     * to the foreground, and then clear it to its root state.  This is
     * especially useful, for example, when launching an activity from the
     * notification manager.
     *
     * <p>See
     * <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html">Tasks and Back
     * Stack</a> for more information about tasks.
     */
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 67108864;

    /**
     * @deprecated As of API 21 this performs identically to
     * {@link #FLAG_ACTIVITY_NEW_DOCUMENT} which should be used instead of this.
     */
    @Deprecated
    public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 524288;

    /**
     * If set, the new activity is not kept in the list of recently launched
     * activities.
     */
    public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 8388608;

    /**
     * If set and this intent is being used to launch a new activity from an
     * existing one, then the reply target of the existing activity will be
     * transferred to the new activity.  This way, the new activity can call
     * {@link android.app.Activity#setResult} and have that result sent back to
     * the reply target of the original activity.
     */
    public static final int FLAG_ACTIVITY_FORWARD_RESULT = 33554432;

    /**
     * This flag is not normally set by application code, but set for you by
     * the system if this activity is being launched from history.
     */
    public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 1048576;

    /**
     * This flag is only used for split-screen multi-window mode. The new activity will be displayed
     * adjacent to the one launching it if possible. This can only be used in conjunction with
     * {@link #FLAG_ACTIVITY_NEW_TASK}. Also, setting {@link #FLAG_ACTIVITY_MULTIPLE_TASK} is
     * required if you want a new instance of an existing activity to be created.
     */
    public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 4096;

    /**
     * If set in an Intent passed to {@link Context#startActivity Context.startActivity()},
     * this flag will attempt to launch an instant app if no full app on the device can already
     * handle the intent.
     * <p>
     * When attempting to resolve instant apps externally, the following {@link Intent} properties
     * are supported:
     * <ul>
     *     <li>{@link Intent#setAction(String)}</li>
     *     <li>{@link Intent#addCategory(String)}</li>
     *     <li>{@link Intent#setData(Uri)}</li>
     *     <li>{@link Intent#setType(String)}</li>
     *     <li>{@link Intent#setPackage(String)}</li>
     *     <li>{@link Intent#addFlags(int)}</li>
     * </ul>
     * <p>
     * In the case that no instant app can be found, the installer will be launched to notify the
     * user that the intent could not be resolved. On devices that do not support instant apps,
     * the flag will be ignored.
     */
    public static final int FLAG_ACTIVITY_MATCH_EXTERNAL = 2048;

    /**
     * This flag is used to create a new task and launch an activity into it.
     * This flag is always paired with either {@link #FLAG_ACTIVITY_NEW_DOCUMENT}
     * or {@link #FLAG_ACTIVITY_NEW_TASK}. In both cases these flags alone would
     * search through existing tasks for ones matching this Intent. Only if no such
     * task is found would a new task be created. When paired with
     * FLAG_ACTIVITY_MULTIPLE_TASK both of these behaviors are modified to skip
     * the search for a matching task and unconditionally start a new task.
     *
     * <strong>When used with {@link #FLAG_ACTIVITY_NEW_TASK} do not use this
     * flag unless you are implementing your own
     * top-level application launcher.</strong>  Used in conjunction with
     * {@link #FLAG_ACTIVITY_NEW_TASK} to disable the
     * behavior of bringing an existing task to the foreground.  When set,
     * a new task is <em>always</em> started to host the Activity for the
     * Intent, regardless of whether there is already an existing task running
     * the same thing.
     *
     * <p><strong>Because the default system does not include graphical task management,
     * you should not use this flag unless you provide some way for a user to
     * return back to the tasks you have launched.</strong>
     *
     * See {@link #FLAG_ACTIVITY_NEW_DOCUMENT} for details of this flag's use for
     * creating new document tasks.
     *
     * <p>This flag is ignored if one of {@link #FLAG_ACTIVITY_NEW_TASK} or
     * {@link #FLAG_ACTIVITY_NEW_DOCUMENT} is not also set.
     *
     * <p>See
     * <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html">Tasks and Back
     * Stack</a> for more information about tasks.
     *
     * @see #FLAG_ACTIVITY_NEW_DOCUMENT
     * @see #FLAG_ACTIVITY_NEW_TASK
     */
    public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 134217728;

    /**
     * This flag is used to open a document into a new task rooted at the activity launched
     * by this Intent. Through the use of this flag, or its equivalent attribute,
     * {@link android.R.attr#documentLaunchMode} multiple instances of the same activity
     * containing different documents will appear in the recent tasks list.
     *
     * <p>The use of the activity attribute form of this,
     * {@link android.R.attr#documentLaunchMode}, is
     * preferred over the Intent flag described here. The attribute form allows the
     * Activity to specify multiple document behavior for all launchers of the Activity
     * whereas using this flag requires each Intent that launches the Activity to specify it.
     *
     * <p>Note that the default semantics of this flag w.r.t. whether the recents entry for
     * it is kept after the activity is finished is different than the use of
     * {@link #FLAG_ACTIVITY_NEW_TASK} and {@link android.R.attr#documentLaunchMode} -- if
     * this flag is being used to create a new recents entry, then by default that entry
     * will be removed once the activity is finished.  You can modify this behavior with
     * {@link #FLAG_ACTIVITY_RETAIN_IN_RECENTS}.
     *
     * <p>FLAG_ACTIVITY_NEW_DOCUMENT may be used in conjunction with {@link
     * #FLAG_ACTIVITY_MULTIPLE_TASK}. When used alone it is the
     * equivalent of the Activity manifest specifying {@link
     * android.R.attr#documentLaunchMode}="intoExisting". When used with
     * FLAG_ACTIVITY_MULTIPLE_TASK it is the equivalent of the Activity manifest specifying
     * {@link android.R.attr#documentLaunchMode}="always". The flag is ignored even in
     * conjunction with {@link #FLAG_ACTIVITY_MULTIPLE_TASK} when the Activity manifest specifies
     * {@link android.R.attr#documentLaunchMode}="never".
     *
     * Refer to {@link android.R.attr#documentLaunchMode} for more information.
     *
     * @see android.R.attr#documentLaunchMode
     * @see #FLAG_ACTIVITY_MULTIPLE_TASK
     */
    public static final int FLAG_ACTIVITY_NEW_DOCUMENT = 524288;

    /**
     * If set, this activity will become the start of a new task on this
     * history stack.  A task (from the activity that started it to the
     * next task activity) defines an atomic group of activities that the
     * user can move to.  Tasks can be moved to the foreground and background;
     * all of the activities inside of a particular task always remain in
     * the same order.  See
     * <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html">Tasks and Back
     * Stack</a> for more information about tasks.
     *
     * <p>This flag is generally used by activities that want
     * to present a "launcher" style behavior: they give the user a list of
     * separate things that can be done, which otherwise run completely
     * independently of the activity launching them.
     *
     * <p>When using this flag, if a task is already running for the activity
     * you are now starting, then a new activity will not be started; instead,
     * the current task will simply be brought to the front of the screen with
     * the state it was last in.  See {@link #FLAG_ACTIVITY_MULTIPLE_TASK} for a flag
     * to disable this behavior.
     *
     * <p>This flag can not be used when the caller is requesting a result from
     * the activity being launched.
     */
    public static final int FLAG_ACTIVITY_NEW_TASK = 268435456;

    /**
     * If set in an Intent passed to {@link Context#startActivity Context.startActivity()},
     * this flag will prevent the system from applying an activity transition
     * animation to go to the next activity state.  This doesn't mean an
     * animation will never run -- if another activity change happens that doesn't
     * specify this flag before the activity started here is displayed, then
     * that transition will be used.  This flag can be put to good use
     * when you are going to do a series of activity operations but the
     * animation seen by the user shouldn't be driven by the first activity
     * change but rather a later one.
     */
    public static final int FLAG_ACTIVITY_NO_ANIMATION = 65536;

    /**
     * If set, the new activity is not kept in the history stack.  As soon as
     * the user navigates away from it, the activity is finished.  This may also
     * be set with the {@link android.R.styleable#AndroidManifestActivity_noHistory
     * noHistory} attribute.
     *
     * <p>If set, {@link android.app.Activity#onActivityResult onActivityResult()}
     * is never invoked when the current activity starts a new activity which
     * sets a result and finishes.
     */
    public static final int FLAG_ACTIVITY_NO_HISTORY = 1073741824;

    /**
     * If set, this flag will prevent the normal {@link android.app.Activity#onUserLeaveHint}
     * callback from occurring on the current frontmost activity before it is
     * paused as the newly-started activity is brought to the front.
     *
     * <p>Typically, an activity can rely on that callback to indicate that an
     * explicit user action has caused their activity to be moved out of the
     * foreground. The callback marks an appropriate point in the activity's
     * lifecycle for it to dismiss any notifications that it intends to display
     * "until the user has seen them," such as a blinking LED.
     *
     * <p>If an activity is ever started via any non-user-driven events such as
     * phone-call receipt or an alarm handler, this flag should be passed to {@link
     * Context#startActivity Context.startActivity}, ensuring that the pausing
     * activity does not think the user has acknowledged its notification.
     */
    public static final int FLAG_ACTIVITY_NO_USER_ACTION = 262144;

    /**
     * If set and this intent is being used to launch a new activity from an
     * existing one, the current activity will not be counted as the top
     * activity for deciding whether the new intent should be delivered to
     * the top instead of starting a new one.  The previous activity will
     * be used as the top, with the assumption being that the current activity
     * will finish itself immediately.
     */
    public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 16777216;

    /**
     * If set in an Intent passed to {@link Context#startActivity Context.startActivity()},
     * this flag will cause the launched activity to be brought to the front of its
     * task's history stack if it is already running.
     *
     * <p>For example, consider a task consisting of four activities: A, B, C, D.
     * If D calls startActivity() with an Intent that resolves to the component
     * of activity B, then B will be brought to the front of the history stack,
     * with this resulting order:  A, C, D, B.
     *
     * This flag will be ignored if {@link #FLAG_ACTIVITY_CLEAR_TOP} is also
     * specified.
     */
    public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 131072;

    /**
     * If set in an intent passed to {@link Context#startActivity Context.startActivity()}, this
     * flag will only launch the intent if it resolves to a single result. If no such result exists
     * or if the system chooser would otherwise be displayed, an {@link ActivityNotFoundException}
     * will be thrown.
     */
    public static final int FLAG_ACTIVITY_REQUIRE_DEFAULT = 512;

    /**
     * If set in an intent passed to {@link Context#startActivity Context.startActivity()}, this
     * flag will only launch the intent if it resolves to a result that is not a browser. If no such
     * result exists, an {@link ActivityNotFoundException} will be thrown.
     */
    public static final int FLAG_ACTIVITY_REQUIRE_NON_BROWSER = 1024;

    /**
     * If set, and this activity is either being started in a new task or
     * bringing to the top an existing task, then it will be launched as
     * the front door of the task.  This will result in the application of
     * any affinities needed to have that task in the proper state (either
     * moving activities to or from it), or simply resetting that task to
     * its initial state if needed.
     *
     * @see android.R.attr#allowTaskReparenting
     * @see android.R.attr#clearTaskOnLaunch
     * @see android.R.attr#finishOnTaskLaunch
     */
    public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 2097152;

    /**
     * By default a document created by {@link #FLAG_ACTIVITY_NEW_DOCUMENT} will
     * have its entry in recent tasks removed when the user closes it (with back
     * or however else it may finish()). If you would like to instead allow the
     * document to be kept in recents so that it can be re-launched, you can use
     * this flag. When set and the task's activity is finished, the recents
     * entry will remain in the interface for the user to re-launch it, like a
     * recents entry for a top-level application.
     * <p>
     * The receiving activity can override this request with
     * {@link android.R.attr#autoRemoveFromRecents} or by explcitly calling
     * {@link android.app.Activity#finishAndRemoveTask()
     * Activity.finishAndRemoveTask()}.
     */
    public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 8192;

    /**
     * If set, the activity will not be launched if it is already running
     * at the top of the history stack.  See
     * <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html#TaskLaunchModes">
     * Tasks and Back Stack</a> for more information.
     */
    public static final int FLAG_ACTIVITY_SINGLE_TOP = 536870912;

    /**
     * If set in an Intent passed to {@link Context#startActivity Context.startActivity()},
     * this flag will cause a newly launching task to be placed on top of the current
     * home activity task (if there is one).  That is, pressing back from the task
     * will always return the user to home even if that was not the last activity they
     * saw.   This can only be used in conjunction with {@link #FLAG_ACTIVITY_NEW_TASK}.
     */
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 16384;

    /**
     * A flag you can enable for debugging: when set, log messages will be
     * printed during the resolution of this intent to show you what has
     * been found to create the final resolved list.
     */
    public static final int FLAG_DEBUG_LOG_RESOLUTION = 8;

    /**
     * Flag used to automatically match intents based on their Direct Boot
     * awareness and the current user state.
     * <p>
     * Since the default behavior is to automatically apply the current user
     * state, this is effectively a sentinel value that doesn't change the
     * output of any queries based on its presence or absence.
     * <p>
     * Instead, this value can be useful in conjunction with
     * {@link android.os.StrictMode.VmPolicy.Builder#detectImplicitDirectBoot()}
     * to detect when a caller is relying on implicit automatic matching,
     * instead of confirming the explicit behavior they want.
     */
    public static final int FLAG_DIRECT_BOOT_AUTO = 256;

    /**
     * If set, this intent will not match any components in packages that
     * are currently
     * {@linkplain android.content.pm.ApplicationInfo#FLAG_STOPPED stopped}.
     * If this is not set, then the default behavior is to include such
     * applications in the result.
     */
    public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 16;

    /**
     * Can be set by the caller to indicate that this Intent is coming from
     * a background operation, not from direct user interaction.
     */
    public static final int FLAG_FROM_BACKGROUND = 4;

    /**
     * When combined with {@link #FLAG_GRANT_READ_URI_PERMISSION} and/or
     * {@link #FLAG_GRANT_WRITE_URI_PERMISSION}, the URI permission grant can be
     * persisted across device reboots until explicitly revoked with
     * {@link Context#revokeUriPermission(Uri, int)}. This flag only offers the
     * grant for possible persisting; the receiving application must call
     * {@link ContentResolver#takePersistableUriPermission(Uri, int)} to
     * actually persist.
     *
     * @see ContentResolver#takePersistableUriPermission(Uri, int)
     * @see ContentResolver#releasePersistableUriPermission(Uri, int)
     * @see ContentResolver#getPersistedUriPermissions()
     * @see ContentResolver#getOutgoingPersistedUriPermissions()
     */
    public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 64;

    /**
     * When combined with {@link #FLAG_GRANT_READ_URI_PERMISSION} and/or
     * {@link #FLAG_GRANT_WRITE_URI_PERMISSION}, the URI permission grant
     * applies to any URI that is a prefix match against the original granted
     * URI. (Without this flag, the URI must match exactly for access to be
     * granted.) Another URI is considered a prefix match only when scheme,
     * authority, and all path segments defined by the prefix are an exact
     * match.
     */
    public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 128;

    /**
     * If set, the recipient of this Intent will be granted permission to
     * perform read operations on the URI in the Intent's data and any URIs
     * specified in its ClipData.  When applying to an Intent's ClipData,
     * all URIs as well as recursive traversals through data or other ClipData
     * in Intent items will be granted; only the grant flags of the top-level
     * Intent are used.
     */
    public static final int FLAG_GRANT_READ_URI_PERMISSION = 1;

    /**
     * If set, the recipient of this Intent will be granted permission to
     * perform write operations on the URI in the Intent's data and any URIs
     * specified in its ClipData.  When applying to an Intent's ClipData,
     * all URIs as well as recursive traversals through data or other ClipData
     * in Intent items will be granted; only the grant flags of the top-level
     * Intent are used.
     */
    public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 2;

    /**
     * If set, this intent will always match any components in packages that
     * are currently
     * {@linkplain android.content.pm.ApplicationInfo#FLAG_STOPPED stopped}.
     * This is the default behavior when
     * {@link #FLAG_EXCLUDE_STOPPED_PACKAGES} is not set.  If both of these
     * flags are set, this one wins (it allows overriding of exclude for
     * places where the framework may automatically set the exclude flag,
     * such as broadcasts).
     */
    public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;

    /**
     * If set, when sending a broadcast the recipient is allowed to run at
     * foreground priority, with a shorter timeout interval.  During normal
     * broadcasts the receivers are not automatically hoisted out of the
     * background priority class.
     */
    public static final int FLAG_RECEIVER_FOREGROUND = 268435456;

    /**
     * If this is an ordered broadcast, don't allow receivers to abort the broadcast.
     * They can still propagate results through to later receivers, but they can not prevent
     * later receivers from seeing the broadcast.
     */
    public static final int FLAG_RECEIVER_NO_ABORT = 134217728;

    /**
     * If set, when sending a broadcast only registered receivers will be
     * called -- no BroadcastReceiver components will be launched.
     */
    public static final int FLAG_RECEIVER_REGISTERED_ONLY = 1073741824;

    /**
     * If set, when sending a broadcast the new broadcast will replace
     * any existing pending broadcast that matches it.  Matching is defined
     * by {@link Intent#filterEquals(Intent) Intent.filterEquals} returning
     * true for the intents of the two broadcasts.  When a match is found,
     * the new broadcast (and receivers associated with it) will replace the
     * existing one in the pending broadcast list, remaining at the same
     * position in the list.
     *
     * <p>This flag is most typically used with sticky broadcasts, which
     * only care about delivering the most recent values of the broadcast
     * to their receivers.
     */
    public static final int FLAG_RECEIVER_REPLACE_PENDING = 536870912;

    /**
     * If set, the broadcast will be visible to receivers in Instant Apps. By default Instant Apps
     * will not receive broadcasts.
     *
     * <em>This flag has no effect when used by an Instant App.</em>
     */
    public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 2097152;

    /**
     * Boolean that can be supplied as meta-data with a dock activity, to
     * indicate that the dock should take over the home key when it is active.
     */
    public static final String METADATA_DOCK_HOME = "android.dock_home";

    /**
     * Flag for use with {@link #toUri} and {@link #parseUri}: allow parsing
     * of unsafe information.  In particular, the flags {@link #FLAG_GRANT_READ_URI_PERMISSION},
     * {@link #FLAG_GRANT_WRITE_URI_PERMISSION}, {@link #FLAG_GRANT_PERSISTABLE_URI_PERMISSION},
     * and {@link #FLAG_GRANT_PREFIX_URI_PERMISSION} flags can not be set, so that the
     * generated Intent can not cause unexpected data access to happen.
     *
     * <p>If you do not trust the source of the URI being parsed, you should still do further
     * processing to protect yourself from it.  In particular, when using it to start an
     * activity you should usually add in {@link #CATEGORY_BROWSABLE} to limit the activities
     * that can handle it.</p>
     */
    public static final int URI_ALLOW_UNSAFE = 4;

    /**
     * Flag for use with {@link #toUri} and {@link #parseUri}: the URI string
     * always has the "android-app:" scheme.  This is a variation of
     * {@link #URI_INTENT_SCHEME} whose format is simpler for the case of an
     * http/https URI being delivered to a specific package name.  The format
     * is:
     *
     * <pre class="prettyprint">
     * android-app://{package_id}[/{scheme}[/{host}[/{path}]]][#Intent;{...}]</pre>
     *
     * <p>In this scheme, only the <code>package_id</code> is required.  If you include a host,
     * you must also include a scheme; including a path also requires both a host and a scheme.
     * The final #Intent; fragment can be used without a scheme, host, or path.
     * Note that this can not be
     * used with intents that have a {@link #setSelector}, since the base intent
     * will always have an explicit package name.</p>
     *
     * <p>Some examples of how this scheme maps to Intent objects:</p>
     * <table border="2" width="85%" align="center" frame="hsides" rules="rows">
     *     <colgroup align="left" />
     *     <colgroup align="left" />
     *     <thead>
     *     <tr><th>URI</th> <th>Intent</th></tr>
     *     </thead>
     *
     *     <tbody>
     *     <tr><td><code>android-app://com.example.app</code></td>
     *         <td><table style="margin:0;border:0;cellpadding:0;cellspacing:0">
     *             <tr><td>Action: </td><td>{@link #ACTION_MAIN}</td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *         </table></td>
     *     </tr>
     *     <tr><td><code>android-app://com.example.app/http/example.com</code></td>
     *         <td><table style="margin:0;border:0;cellpadding:0;cellspacing:0">
     *             <tr><td>Action: </td><td>{@link #ACTION_VIEW}</td></tr>
     *             <tr><td>Data: </td><td><code>http://example.com/</code></td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *         </table></td>
     *     </tr>
     *     <tr><td><code>android-app://com.example.app/http/example.com/foo?1234</code></td>
     *         <td><table style="margin:0;border:0;cellpadding:0;cellspacing:0">
     *             <tr><td>Action: </td><td>{@link #ACTION_VIEW}</td></tr>
     *             <tr><td>Data: </td><td><code>http://example.com/foo?1234</code></td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *         </table></td>
     *     </tr>
     *     <tr><td><code>android-app://com.example.app/<br />#Intent;action=com.example.MY_ACTION;end</code></td>
     *         <td><table style="margin:0;border:0;cellpadding:0;cellspacing:0">
     *             <tr><td>Action: </td><td><code>com.example.MY_ACTION</code></td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *         </table></td>
     *     </tr>
     *     <tr><td><code>android-app://com.example.app/http/example.com/foo?1234<br />#Intent;action=com.example.MY_ACTION;end</code></td>
     *         <td><table style="margin:0;border:0;cellpadding:0;cellspacing:0">
     *             <tr><td>Action: </td><td><code>com.example.MY_ACTION</code></td></tr>
     *             <tr><td>Data: </td><td><code>http://example.com/foo?1234</code></td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *         </table></td>
     *     </tr>
     *     <tr><td><code>android-app://com.example.app/<br />#Intent;action=com.example.MY_ACTION;<br />i.some_int=100;S.some_str=hello;end</code></td>
     *         <td><table border="" style="margin:0" >
     *             <tr><td>Action: </td><td><code>com.example.MY_ACTION</code></td></tr>
     *             <tr><td>Package: </td><td><code>com.example.app</code></td></tr>
     *             <tr><td>Extras: </td><td><code>some_int=(int)100<br />some_str=(String)hello</code></td></tr>
     *         </table></td>
     *     </tr>
     *     </tbody>
     * </table>
     */
    public static final int URI_ANDROID_APP_SCHEME = 2;

    /**
     * Flag for use with {@link #toUri} and {@link #parseUri}: the URI string
     * always has the "intent:" scheme.  This syntax can be used when you want
     * to later disambiguate between URIs that are intended to describe an
     * Intent vs. all others that should be treated as raw URIs.  When used
     * with {@link #parseUri}, any other scheme will result in a generic
     * VIEW action for that raw URI.
     */
    public static final int URI_INTENT_SCHEME = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private Intent() {
    }
}
