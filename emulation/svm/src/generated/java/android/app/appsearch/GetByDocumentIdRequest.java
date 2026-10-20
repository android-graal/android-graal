package android.app.appsearch;

import android.os.Parcelable;

/**
 * Encapsulates a request to retrieve documents by namespace and IDs from the {@link
 * AppSearchSession} database.
 *
 * @see AppSearchSession#getByDocumentId
 */
public final class GetByDocumentIdRequest implements Parcelable {
    /**
     * Schema type to be used in {@link GetByDocumentIdRequest.Builder#addProjection} to apply
     * property paths to all results, excepting any types that have had their own, specific property
     * paths set.
     */
    public static final String PROJECTION_SCHEMA_TYPE_WILDCARD = "*";

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private GetByDocumentIdRequest() {
    }
}
