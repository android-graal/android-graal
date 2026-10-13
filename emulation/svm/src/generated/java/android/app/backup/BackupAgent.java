package android.app.backup;

/**
 * Provides the central interface between an
 * application and Android's data backup infrastructure.  An application that wishes
 * to participate in the backup and restore mechanism will declare a subclass of
 * {@link android.app.backup.BackupAgent}, implement the
 * {@link #onBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor) onBackup()}
 * and {@link #onRestore(BackupDataInput, int, ParcelFileDescriptor) onRestore()} methods,
 * and provide the name of its backup agent class in its {@code AndroidManifest.xml} file via
 * the <code>
 * <a href="{@docRoot}guide/topics/manifest/application-element.html">&lt;application&gt;</a></code>
 * tag's {@code android:backupAgent} attribute.
 *
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>For more information about using BackupAgent, read the
 * <a href="{@docRoot}guide/topics/data/backup.html">Data Backup</a> developer guide.</p></div>
 *
 * <h3>Basic Operation</h3>
 * <p>
 * When the application makes changes to data that it wishes to keep backed up,
 * it should call the
 * {@link android.app.backup.BackupManager#dataChanged() BackupManager.dataChanged()} method.
 * This notifies the Android Backup Manager that the application needs an opportunity
 * to update its backup image.  The Backup Manager, in turn, schedules a
 * backup pass to be performed at an opportune time.
 * <p>
 * Restore operations are typically performed only when applications are first
 * installed on a device.  At that time, the operating system checks to see whether
 * there is a previously-saved data set available for the application being installed, and if so,
 * begins an immediate restore pass to deliver the backup data as part of the installation
 * process.
 * <p>
 * When a backup or restore pass is run, the application's process is launched
 * (if not already running), the manifest-declared backup agent class (in the {@code
 * android:backupAgent} attribute) is instantiated within
 * that process, and the agent's {@link #onCreate()} method is invoked.  This prepares the
 * agent instance to run the actual backup or restore logic.  At this point the
 * agent's
 * {@link #onBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor) onBackup()} or
 * {@link #onRestore(BackupDataInput, int, ParcelFileDescriptor) onRestore()} method will be
 * invoked as appropriate for the operation being performed.
 * <p>
 * A backup data set consists of one or more "entities," flattened binary data
 * records that are each identified with a key string unique within the data set.  Adding a
 * record to the active data set or updating an existing record is done by simply
 * writing new entity data under the desired key.  Deleting an entity from the data set
 * is done by writing an entity under that key with header specifying a negative data
 * size, and no actual entity data.
 * <p>
 * <b>Helper Classes</b>
 * <p>
 * An extensible agent based on convenient helper classes is available in
 * {@link android.app.backup.BackupAgentHelper}.  That class is particularly
 * suited to handling of simple file or {@link android.content.SharedPreferences}
 * backup and restore.
 * <p>
 * <b>Threading</b>
 * <p>
 * The constructor, as well as {@link #onCreate()} and {@link #onDestroy()} lifecycle callbacks run
 * on the main thread (UI thread) of the application that implements the BackupAgent.
 * The data-handling callbacks:
 * {@link #onBackup(ParcelFileDescriptor, BackupDataOutput, ParcelFileDescriptor) onBackup()},
 * {@link #onFullBackup(FullBackupDataOutput)},
 * {@link #onRestore(BackupDataInput, int, ParcelFileDescriptor) onRestore()},
 * {@link #onRestoreFile(ParcelFileDescriptor, long, File, int, long, long) onRestoreFile()},
 * {@link #onRestoreFinished()}, and {@link #onQuotaExceeded(long, long) onQuotaExceeded()}
 * run on binder pool threads.
 *
 * @see android.app.backup.BackupManager
 * @see android.app.backup.BackupAgentHelper
 * @see android.app.backup.BackupDataInput
 * @see android.app.backup.BackupDataOutput
 */
public final class BackupAgent {
    /**
     * Flag for {@link BackupDataOutput#getTransportFlags()} and
     * {@link FullBackupDataOutput#getTransportFlags()} only.
     *
     * <p>The transport has client-side encryption enabled. i.e., the user's backup has been
     * encrypted with a key known only to the device, and not to the remote storage solution. Even
     * if an attacker had root access to the remote storage provider they should not be able to
     * decrypt the user's backup data.
     */
    public static final int FLAG_CLIENT_SIDE_ENCRYPTION_ENABLED = 1;

    /**
     * Flag for {@link BackupDataOutput#getTransportFlags()} and
     * {@link FullBackupDataOutput#getTransportFlags()} only.
     *
     * <p>The transport is for a device-to-device transfer. There is no third party or intermediate
     * storage. The user's backup data is sent directly to another device over e.g., USB or WiFi.
     */
    public static final int FLAG_DEVICE_TO_DEVICE_TRANSFER = 2;

    /**
     * During a full restore, indicates that the file system object being restored
     * is a directory.
     */
    public static final int TYPE_DIRECTORY = 2;

    /**
     * During a full restore, indicates that the file system object being restored
     * is an ordinary file.
     */
    public static final int TYPE_FILE = 1;

    private BackupAgent() {
    }
}
