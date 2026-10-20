/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.app.admin;

import android.annotation.IntDef;
import android.annotation.NonNull;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.IndentingPrintWriter;
import android.util.Log;

import org.androidgraal.hosted.annotate.Emulated;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Network configuration to be set for the user profile
 * {@see DevicePolicyManager#setPreferentialNetworkServiceConfigs}.
 */
@Emulated
public final class PreferentialNetworkServiceConfig implements Parcelable {
    final boolean mIsEnabled;
    final int mNetworkId;
    final boolean mAllowFallbackToDefaultConnection;
    final boolean mShouldBlockNonMatchingNetworks;
    final int[] mIncludedUids;
    final int[] mExcludedUids;

    private static final String LOG_TAG = "PreferentialNetworkServiceConfig";
    private static final String TAG_PREFERENTIAL_NETWORK_SERVICE_CONFIG =
            "preferential_network_service_config";
    private static final String TAG_CONFIG_ENABLED =
            "preferential_network_service_config_enabled";
    private static final String TAG_UID = "uid";
    private static final String TAG_NETWORK_ID =
            "preferential_network_service_network_id";
    private static final String TAG_ALLOW_FALLBACK_TO_DEFAULT_CONNECTION =
            "allow_fallback_to_default_connection";
    private static final String TAG_BLOCK_NON_MATCHING_NETWORKS =
            "block_non_matching_networks";
    private static final String TAG_INCLUDED_UIDS = "included_uids";
    private static final String TAG_EXCLUDED_UIDS = "excluded_uids";
    private static final String ATTR_VALUE = "value";

    /** @hide */
    public static final PreferentialNetworkServiceConfig DEFAULT =
            (new PreferentialNetworkServiceConfig.Builder()).build();

    /**
     * Preferential network identifier 1.
     */
    public static final int PREFERENTIAL_NETWORK_ID_1 = 1;

    /**
     * Preferential network identifier 2.
     */
    public static final int PREFERENTIAL_NETWORK_ID_2 = 2;

    /**
     * Preferential network identifier 3.
     */
    public static final int PREFERENTIAL_NETWORK_ID_3 = 3;

    /**
     * Preferential network identifier 4.
     */
    public static final int PREFERENTIAL_NETWORK_ID_4 = 4;

    /**
     * Preferential network identifier 5.
     */
    public static final int PREFERENTIAL_NETWORK_ID_5 = 5;

    /** @hide */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(prefix = { "PREFERENTIAL_NETWORK_ID_" }, value = {
            PREFERENTIAL_NETWORK_ID_1,
            PREFERENTIAL_NETWORK_ID_2,
            PREFERENTIAL_NETWORK_ID_3,
            PREFERENTIAL_NETWORK_ID_4,
            PREFERENTIAL_NETWORK_ID_5,
    })

    public @interface PreferentialNetworkPreferenceId {
    }

    private PreferentialNetworkServiceConfig(boolean isEnabled,
            boolean allowFallbackToDefaultConnection, boolean shouldBlockNonMatchingNetworks,
            int[] includedUids,
            int[] excludedUids, @PreferentialNetworkPreferenceId int networkId) {
        mIsEnabled = isEnabled;
        mAllowFallbackToDefaultConnection = allowFallbackToDefaultConnection;
        mShouldBlockNonMatchingNetworks = shouldBlockNonMatchingNetworks;
        mIncludedUids = includedUids;
        mExcludedUids = excludedUids;
        mNetworkId = networkId;
    }

    private PreferentialNetworkServiceConfig(Parcel in) {
        mIsEnabled = in.readBoolean();
        mAllowFallbackToDefaultConnection = in.readBoolean();
        mShouldBlockNonMatchingNetworks = in.readBoolean();
        mNetworkId = in.readInt();
        mIncludedUids = in.createIntArray();
        mExcludedUids = in.createIntArray();
    }

    /**
     * Is the preferential network enabled.
     * @return true if enabled else false
     */
    public boolean isEnabled() {
        return mIsEnabled;
    }

    /**
     * Whether fallback to the device-wide default network is allowed.
     *
     * This boolean configures whether the default connection (e.g. general cell network or wifi)
     * should be used if no preferential network service connection is available. If true, the
     * default connection will be used when no preferential service is available. If false, the
     * UIDs subject to this configuration will have no default network.
     * Note that while this boolean determines whether the UIDs subject to this configuration have
     * a default network in the absence of a preferential service, apps can still explicitly decide
     * to use another network than their default network by requesting them from the system. This
     * boolean does not determine whether the UIDs are blocked from using such other networks.
     * See {@link #shouldBlockNonMatchingNetworks()} for that configuration.
     *
     * @return true if fallback is allowed, else false.
     */
    public boolean isFallbackToDefaultConnectionAllowed() {
        return mAllowFallbackToDefaultConnection;
    }

    /**
     * Whether to block UIDs from using other networks than the preferential service.
     *
     * Apps can inspect the list of available networks on the device and choose to use multiple
     * of them concurrently for performance, privacy or other reasons.
     * This boolean configures whether the concerned UIDs should be blocked from using
     * networks that do not match the configured preferential network service even if these
     * networks are otherwise open to all apps.
     *
     * @return true if UIDs should be blocked from using the other networks, else false.
     */
    public boolean shouldBlockNonMatchingNetworks() {
        return mShouldBlockNonMatchingNetworks;
    }

    /**
     * Get the array of uids that are applicable for the profile preference.
     *
     * {@see #getExcludedUids()}
     * Included UIDs and Excluded UIDs can't both be non-empty.
     * if both are empty, it means this request applies to all uids in the user profile.
     * if included is not empty, then only included UIDs are applied.
     * if excluded is not empty, then it is all uids in the user profile except these UIDs.
     * @return Array of uids applicable for the profile preference.
     *      Empty array would mean that this request applies to all uids in the profile.
     */
    public @NonNull int[] getIncludedUids() {
        return mIncludedUids;
    }

    /**
     * Get the array of uids that are excluded for the profile preference.
     *
     * {@see #getIncludedUids()}
     * Included UIDs and Excluded UIDs can't both be non-empty.
     * if both are empty, it means this request applies to all uids in the user profile.
     * if included is not empty, then only included UIDs are applied.
     * if excluded is not empty, then it is all uids in the user profile except these UIDs.
     * @return Array of uids that are excluded for the profile preference.
     *      Empty array would mean that this request applies to all uids in the profile.
     */
    public @NonNull int[] getExcludedUids() {
        return mExcludedUids;
    }

    /**
     * @return preference enterprise identifier.
     * preference identifier is applicable only if preference network service is enabled
     *
     */
    public @PreferentialNetworkPreferenceId int getNetworkId() {
        return mNetworkId;
    }

    @Override
    public String toString() {
        return "PreferentialNetworkServiceConfig{"
                + "mIsEnabled=" + isEnabled()
                + "mAllowFallbackToDefaultConnection=" + isFallbackToDefaultConnectionAllowed()
                + "mBlockNonMatchingNetworks=" + shouldBlockNonMatchingNetworks()
                + "mIncludedUids=" + Arrays.toString(mIncludedUids)
                + "mExcludedUids=" + Arrays.toString(mExcludedUids)
                + "mNetworkId=" + mNetworkId
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PreferentialNetworkServiceConfig that = (PreferentialNetworkServiceConfig) o;
        return mIsEnabled == that.mIsEnabled
                && mAllowFallbackToDefaultConnection == that.mAllowFallbackToDefaultConnection
                && mShouldBlockNonMatchingNetworks == that.mShouldBlockNonMatchingNetworks
                && mNetworkId == that.mNetworkId
                && Arrays.equals(mIncludedUids, that.mIncludedUids)
                && Arrays.equals(mExcludedUids, that.mExcludedUids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mIsEnabled, mAllowFallbackToDefaultConnection,
                mShouldBlockNonMatchingNetworks, Arrays.hashCode(mIncludedUids),
                Arrays.hashCode(mExcludedUids), mNetworkId);
    }

    /**
     * Builder used to create {@link PreferentialNetworkServiceConfig} objects.
     * Specify the preferred Network preference
     */
    public static final class Builder {
        boolean mIsEnabled = false;
        int mNetworkId = 0;
        boolean mAllowFallbackToDefaultConnection = true;
        boolean mShouldBlockNonMatchingNetworks = false;
        int[] mIncludedUids = new int[0];
        int[] mExcludedUids = new int[0];

        /**
         * Constructs an empty Builder with preferential network disabled by default.
         */
        public Builder() {}

        /**
         * Set the preferential network service enabled state.
         * Default value is false.
         * @param isEnabled  the desired network preference to use, true to enable else false
         * @return The builder to facilitate chaining.
         */
        @NonNull
        public PreferentialNetworkServiceConfig.Builder setEnabled(boolean isEnabled) {
            mIsEnabled = isEnabled;
            return this;
        }

        /**
         * Set whether fallback to the device-wide default network is allowed.
         *
         * This boolean configures whether the default connection (e.g. general cell network or
         * wifi) should be used if no preferential network service connection is available. If true,
         * the default connection will be used when no preferential service is available. If false,
         * the UIDs subject to this configuration will have no default network.
         * Note that while this boolean determines whether the UIDs subject to this configuration
         * have a default network in the absence of a preferential service, apps can still
         * explicitly decide to use another network than their default network by requesting them
         * from the system. This boolean does not determine whether the UIDs are blocked from using
         * such other networks.
         * Use {@link #setShouldBlockNonMatchingNetworks(boolean)} to specify this.
         *
         * The default value is true.
         *
         * @param allowFallbackToDefaultConnection  true if fallback is allowed else false
         * @return The builder to facilitate chaining.
         */
        @NonNull
        @SuppressLint("MissingGetterMatchingBuilder")
        public PreferentialNetworkServiceConfig.Builder setFallbackToDefaultConnectionAllowed(
                boolean allowFallbackToDefaultConnection) {
            mAllowFallbackToDefaultConnection = allowFallbackToDefaultConnection;
            return this;
        }

        /**
         * Set whether to block UIDs from using other networks than the preferential service.
         *
         * Apps can inspect the list of available networks on the device and choose to use multiple
         * of them concurrently for performance, privacy or other reasons.
         * This boolean configures whether the concerned UIDs should be blocked from using
         * networks that do not match the configured preferential network service even if these
         * networks are otherwise open to all apps.
         *
         * The default value is false. This value can only be set to {@code true} if
         * {@link #setFallbackToDefaultConnectionAllowed(boolean)} is set to {@code false}, because
         * allowing fallback but blocking it does not make sense. Failure to comply with this
         * constraint will throw when building the object.
         *
         * @param blockNonMatchingNetworks true if UIDs should be blocked from using non-matching
         *                                 networks.
         * @return The builder to facilitate chaining.
         */
        @NonNull
        public PreferentialNetworkServiceConfig.Builder setShouldBlockNonMatchingNetworks(
                boolean blockNonMatchingNetworks) {
            mShouldBlockNonMatchingNetworks = blockNonMatchingNetworks;
            return this;
        }

        /**
         * Set the array of uids whose network access will go through this preferential
         * network service.
         * {@see #setExcludedUids(int[])}
         * Included UIDs and Excluded UIDs can't both be non-empty.
         * if both are empty, it means this request applies to all uids in the user profile.
         * if included is not empty, then only included UIDs are applied.
         * if excluded is not empty, then it is all uids in the user profile except these UIDs.
         * @param uids  array of included uids
         * @return The builder to facilitate chaining.
         */
        @NonNull
        public PreferentialNetworkServiceConfig.Builder setIncludedUids(
                @NonNull int[] uids) {
            Objects.requireNonNull(uids);
            mIncludedUids = uids;
            return this;
        }

        /**
         * Set the array of uids who are not allowed through this preferential
         * network service.
         * {@see #setIncludedUids(int[])}
         * Included UIDs and Excluded UIDs can't both be non-empty.
         * if both are empty, it means this request applies to all uids in the user profile.
         * if included is not empty, then only included UIDs are applied.
         * if excluded is not empty, then it is all uids in the user profile except these UIDs.
         * @param uids  array of excluded uids
         * @return The builder to facilitate chaining.
         */
        @NonNull
        public PreferentialNetworkServiceConfig.Builder setExcludedUids(
                @NonNull int[] uids) {
            Objects.requireNonNull(uids);
            mExcludedUids = uids;
            return this;
        }

        /**
         * Returns an instance of {@link PreferentialNetworkServiceConfig} created from the
         * fields set on this builder.
         */
        @NonNull
        public PreferentialNetworkServiceConfig build() {
            if (mIncludedUids.length > 0 && mExcludedUids.length > 0) {
                throw new IllegalStateException("Both includedUids and excludedUids "
                        + "cannot be nonempty");
            }
            if (mShouldBlockNonMatchingNetworks && mAllowFallbackToDefaultConnection) {
                throw new IllegalStateException("A config cannot both allow fallback and "
                        + "block non-matching networks");
            }
            return new PreferentialNetworkServiceConfig(mIsEnabled,
                    mAllowFallbackToDefaultConnection, mShouldBlockNonMatchingNetworks,
                    mIncludedUids, mExcludedUids, mNetworkId);
        }

        /**
         * Set the preferential network identifier.
         * preference identifier is applicable only if preferential network service is enabled.
         * @param preferenceId  preference Id
         * @return The builder to facilitate chaining.
         */
        @NonNull
        public PreferentialNetworkServiceConfig.Builder setNetworkId(
                @PreferentialNetworkPreferenceId int preferenceId) {
            if ((preferenceId < PREFERENTIAL_NETWORK_ID_1)
                    || (preferenceId > PREFERENTIAL_NETWORK_ID_5)) {
                throw new IllegalArgumentException("Invalid preference identifier");
            }
            mNetworkId = preferenceId;
            return this;
        }
    }

    @Override
    public void writeToParcel(@NonNull android.os.Parcel dest, int flags) {
        dest.writeBoolean(mIsEnabled);
        dest.writeBoolean(mAllowFallbackToDefaultConnection);
        dest.writeBoolean(mShouldBlockNonMatchingNetworks);
        dest.writeInt(mNetworkId);
        dest.writeIntArray(mIncludedUids);
        dest.writeIntArray(mExcludedUids);
    }

    private List<String> intArrayToStringList(int[] array) {
        return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.toList());
    }

    /**
     * @hide
     */
    public void dump(IndentingPrintWriter pw) {
        pw.print("networkId=");
        pw.println(mNetworkId);
        pw.print("isEnabled=");
        pw.println(mIsEnabled);
        pw.print("allowFallbackToDefaultConnection=");
        pw.println(mAllowFallbackToDefaultConnection);
        pw.print("blockNonMatchingNetworks=");
        pw.println(mShouldBlockNonMatchingNetworks);
        pw.print("includedUids=");
        pw.println(Arrays.toString(mIncludedUids));
        pw.print("excludedUids=");
        pw.println(Arrays.toString(mExcludedUids));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    public static final Creator<PreferentialNetworkServiceConfig> CREATOR =
            new Creator<PreferentialNetworkServiceConfig>() {
                @Override
                public PreferentialNetworkServiceConfig[] newArray(int size) {
                    return new PreferentialNetworkServiceConfig[size];
                }

                @Override
                public PreferentialNetworkServiceConfig createFromParcel(
                        @NonNull android.os.Parcel in) {
                    return new PreferentialNetworkServiceConfig(in);
                }
            };
}
