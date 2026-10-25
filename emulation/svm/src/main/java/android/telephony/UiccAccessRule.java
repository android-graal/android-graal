/*
 * Copyright (C) 2017 The Android Open Source Project
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
package android.telephony;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.SystemApi;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.androidgraal.hosted.annotate.Emulated;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Describes a single UICC access rule according to the GlobalPlatform Secure Element Access Control
 * specification.
 *
 * @hide
 */
@SystemApi
@Emulated
public final class UiccAccessRule implements Parcelable {
    private static final String TAG = "UiccAccessRule";

    private static final int ENCODING_VERSION = 1;

    /**
     * Delimiter used to decode {@link CarrierConfigManager#KEY_CARRIER_CERTIFICATE_STRING_ARRAY}.
     */
    private static final String DELIMITER_CERTIFICATE_HASH_PACKAGE_NAMES = ":";

    /**
     * Delimiter used to decode {@link CarrierConfigManager#KEY_CARRIER_CERTIFICATE_STRING_ARRAY}.
     */
    private static final String DELIMITER_INDIVIDUAL_PACKAGE_NAMES = ",";

    public static final @android.annotation.NonNull Creator<UiccAccessRule> CREATOR = new Creator<UiccAccessRule>() {
        @Override
        public UiccAccessRule createFromParcel(Parcel in) {
            return new UiccAccessRule(in);
        }

        @Override
        public UiccAccessRule[] newArray(int size) {
            return new UiccAccessRule[size];
        }
    };

    /**
     * Encode these access rules as a byte array which can be parsed with {@link #decodeRules}.
     * @hide
     */
    @Nullable
    public static byte[] encodeRules(@Nullable UiccAccessRule[] accessRules) {
        if (accessRules == null) {
            return null;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream output = new DataOutputStream(baos);
            output.writeInt(ENCODING_VERSION);
            output.writeInt(accessRules.length);
            for (UiccAccessRule accessRule : accessRules) {
                output.writeInt(accessRule.mCertificateHash.length);
                output.write(accessRule.mCertificateHash);
                if (accessRule.mPackageName != null) {
                    output.writeBoolean(true);
                    output.writeUTF(accessRule.mPackageName);
                } else {
                    output.writeBoolean(false);
                }
                output.writeLong(accessRule.mAccessType);
            }
            output.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(
                    "ByteArrayOutputStream should never lead to an IOException", e);
        }
    }

    /**
     * Decodes a byte array generated with {@link #encodeRules}.
     * @hide
     */
    @Nullable
    public static UiccAccessRule[] decodeRules(@Nullable byte[] encodedRules) {
        if (encodedRules == null) {
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(encodedRules);
        try (DataInputStream input = new DataInputStream(bais)) {
            input.readInt(); // version; currently ignored
            int count = input.readInt();
            UiccAccessRule[] accessRules = new UiccAccessRule[count];
            for (int i = 0; i < count; i++) {
                int certificateHashLength = input.readInt();
                byte[] certificateHash = new byte[certificateHashLength];
                input.readFully(certificateHash);
                String packageName = input.readBoolean() ? input.readUTF() : null;
                long accessType = input.readLong();
                accessRules[i] = new UiccAccessRule(certificateHash, packageName, accessType);
            }
            input.close();
            return accessRules;
        } catch (IOException e) {
            throw new IllegalStateException(
                    "ByteArrayInputStream should never lead to an IOException", e);
        }
    }

    private final byte[] mCertificateHash;
    private final int mCertificateHashHashCode;
    private final @Nullable String mPackageName;
    // This bit is not currently used, but reserved for future use.
    private final long mAccessType;

    public UiccAccessRule(byte[] certificateHash, @Nullable String packageName, long accessType) {
        this.mCertificateHash = certificateHash;
        this.mCertificateHashHashCode = getCertificateHashHashCode(this.mCertificateHash);
        this.mPackageName = packageName;
        this.mAccessType = accessType;
    }

    UiccAccessRule(Parcel in) {
        mCertificateHash = in.createByteArray();
        mCertificateHashHashCode = getCertificateHashHashCode(mCertificateHash);
        mPackageName = in.readString();
        mAccessType = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(mCertificateHash);
        dest.writeString(mPackageName);
        dest.writeLong(mAccessType);
    }

    /**
     * Return the package name this rule applies to.
     *
     * @return the package name, or null if this rule applies to any package signed with the given
     *     certificate.
     */
    public @Nullable String getPackageName() {
        return mPackageName;
    }

    /**
     * Returns true if the given certificate and package name match this rule's values.
     * @hide
     */
    public boolean hasMatchingCertificateHashAndPackageName(
            @Nullable byte[] certHash, @Nullable String packageName) {
        return certHash != null && Arrays.equals(this.mCertificateHash, certHash) &&
                (TextUtils.isEmpty(this.mPackageName) || this.mPackageName.equals(packageName));
    }

    /**
     * Returns true if the given certificate hash hash
     * and package name both match this rules' values.
     *
     * @hide
     */
    public boolean hasMatchingCertificateHashHashAndPackageName(
            int certHashHashCode, String packageName) {
        return certHashHashCode == this.mCertificateHashHashCode
                && (TextUtils.isEmpty(this.mPackageName) || this.mPackageName.equals(packageName));
    }

    /**
     * @hide
     */
    public static int getCertificateHashHashCode(byte[] certHash) {
        return Arrays.hashCode(certHash);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UiccAccessRule that = (UiccAccessRule) obj;
        return Arrays.equals(mCertificateHash, that.mCertificateHash)
                && Objects.equals(mPackageName, that.mPackageName)
                && mAccessType == that.mAccessType;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Arrays.hashCode(mCertificateHash);
        result = 31 * result + Objects.hashCode(mPackageName);
        result = 31 * result + Objects.hashCode(mAccessType);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
