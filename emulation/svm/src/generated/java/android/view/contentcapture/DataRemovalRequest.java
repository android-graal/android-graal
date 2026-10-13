package android.view.contentcapture;

import android.os.Parcelable;

/**
 * Class used by apps to request the content capture service to remove data associated with
 * {@link LocusId LocusIds}.
 *
 * <p>An app which has tagged data with a LocusId can therefore delete them later. This is intended
 * to let apps propagate deletions of user data into the operating system.
 */
public final class DataRemovalRequest implements Parcelable {
    /**
     * When set, service should use the {@link LocusId#getId()} as prefix for the data to be
     * removed.
     */
    public static final int FLAG_IS_PREFIX = 1;

    private DataRemovalRequest() {
    }
}
