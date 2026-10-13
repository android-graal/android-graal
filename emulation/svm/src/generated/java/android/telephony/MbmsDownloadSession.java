package android.telephony;

/**
 * This class provides functionality for file download over MBMS.
 */
public final class MbmsDownloadSession {
    /**
     * The default directory name for all MBMS temp files. If you call
     * {@link #download(DownloadRequest)} without first calling
     * {@link #setTempFileRootDirectory(File)}, this directory will be created for you under the
     * path returned by {@link Context#getFilesDir()}.
     */
    public static final String DEFAULT_TOP_LEVEL_TEMP_DIRECTORY = "androidMbmsTempFileRoot";

    /**
     * {@link Uri} extra that Android will attach to the intent supplied via
     * {@link android.telephony.mbms.DownloadRequest.Builder#setAppIntent(Intent)}
     * Indicates the location of the successfully downloaded file within the directory that the
     * app provided via the builder.
     *
     * Will always be set to a non-null value if
     * {@link #EXTRA_MBMS_DOWNLOAD_RESULT} is set to {@link #RESULT_SUCCESSFUL}.
     */
    public static final String EXTRA_MBMS_COMPLETED_FILE_URI = "android.telephony.extra.MBMS_COMPLETED_FILE_URI";

    /**
     * Extra containing the {@link DownloadRequest} for which the download result or file
     * descriptor request is for. Must not be null.
     */
    public static final String EXTRA_MBMS_DOWNLOAD_REQUEST = "android.telephony.extra.MBMS_DOWNLOAD_REQUEST";

    /**
     * Integer extra that Android will attach to the intent supplied via
     * {@link android.telephony.mbms.DownloadRequest.Builder#setAppIntent(Intent)}
     * Indicates the result code of the download. One of
     * {@link #RESULT_SUCCESSFUL}, {@link #RESULT_EXPIRED}, {@link #RESULT_CANCELLED},
     * {@link #RESULT_IO_ERROR}, {@link #RESULT_DOWNLOAD_FAILURE}, {@link #RESULT_OUT_OF_STORAGE},
     * {@link #RESULT_SERVICE_ID_NOT_DEFINED}, or {@link #RESULT_FILE_ROOT_UNREACHABLE}.
     *
     * This extra may also be used by the middleware when it is sending intents to the app.
     */
    public static final String EXTRA_MBMS_DOWNLOAD_RESULT = "android.telephony.extra.MBMS_DOWNLOAD_RESULT";

    /**
     * {@link FileInfo} extra that Android will attach to the intent supplied via
     * {@link android.telephony.mbms.DownloadRequest.Builder#setAppIntent(Intent)}
     * Indicates the file for which the download result is for. Never null.
     *
     * This extra may also be used by the middleware when it is sending intents to the app.
     */
    public static final String EXTRA_MBMS_FILE_INFO = "android.telephony.extra.MBMS_FILE_INFO";

    /**
     * Indicates that the download was cancelled via {@link #cancelDownload(DownloadRequest)}.
     */
    public static final int RESULT_CANCELLED = 2;

    /**
     * Indicates that there was an error while processing downloaded files, such as a file repair or
     * file decoding error and is not due to a file I/O error.
     *
     * This is likely a transient error and another {@link DownloadRequest} should be sent to try
     * the download again.
     */
    public static final int RESULT_DOWNLOAD_FAILURE = 6;

    /**
     * Indicates that the download will not be completed due to the expiration of its download
     * window on the carrier's network.
     */
    public static final int RESULT_EXPIRED = 3;

    /**
     * Indicates that the file root that was set is currently unreachable. This can happen if the
     * temp files are set to be stored on external storage and the SD card was removed, for example.
     * The temp file root should be changed before sending another DownloadRequest.
     */
    public static final int RESULT_FILE_ROOT_UNREACHABLE = 8;

    /**
     * Indicates that the download will not be completed due to an I/O error incurred while
     * writing to temp files.
     *
     * This is likely a transient error and another {@link DownloadRequest} should be sent to try
     * the download again.
     */
    public static final int RESULT_IO_ERROR = 4;

    /**
     * Indicates that the file system is full and the {@link DownloadRequest} can not complete.
     * Either space must be made on the current file system or the temp file root location must be
     * changed to a location that is not full to download the temp files.
     */
    public static final int RESULT_OUT_OF_STORAGE = 7;

    /**
     * Indicates that the Service ID specified in the {@link DownloadRequest} is incorrect due to
     * the Id being incorrect, stale, expired, or similar.
     */
    public static final int RESULT_SERVICE_ID_NOT_DEFINED = 5;

    /**
     * Indicates that the download was successful.
     */
    public static final int RESULT_SUCCESSFUL = 1;

    /**
     * Indicates that the file is actively being downloaded.
     */
    public static final int STATUS_ACTIVELY_DOWNLOADING = 1;

    /**
     * Indicates that the file is awaiting the next download or repair operations. When a more
     * precise status is known, the status will change to either {@link #STATUS_PENDING_REPAIR} or
     * {@link #STATUS_PENDING_DOWNLOAD_WINDOW}.
     */
    public static final int STATUS_PENDING_DOWNLOAD = 2;

    /**
     * Indicates that the file is waiting to download because its download window has not yet
     * started and is scheduled for a future time.
     */
    public static final int STATUS_PENDING_DOWNLOAD_WINDOW = 4;

    /**
     * Indicates that the file is awaiting file repair after the download has ended.
     */
    public static final int STATUS_PENDING_REPAIR = 3;

    /**
     * Indicates that the middleware has no information on the file.
     */
    public static final int STATUS_UNKNOWN = 0;

    private MbmsDownloadSession() {
    }
}
