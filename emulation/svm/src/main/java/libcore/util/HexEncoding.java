/*
 * Copyright (C) 2014 The Android Open Source Project
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

package libcore.util;

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.annotation.SystemApi;

/**
 * Hexadecimal encoding where each byte is represented by two hexadecimal digits.
 * @hide
 */
@SystemApi(client = MODULE_LIBRARIES)
public class HexEncoding {

    private static final char[] LOWER_CASE_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private static final char[] UPPER_CASE_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    /** Hidden constructor to prevent instantiation. */
    private HexEncoding() {}

    /**
     * Encodes the provided data as a sequence of hexadecimal characters.
     *
     * @param  data byte array to encode
     * @param  upperCase {@code true} to use uppercase letters, {@code false}
     *         for lowercase
     * @return the encoded data
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static char[] encode(byte[] data, boolean upperCase) {
        return encode(data, 0, data.length, upperCase);
    }

    /**
     * Encodes the provided data as a sequence of hexadecimal characters.
     */
    private static char[] encode(byte[] data, int offset, int len, boolean upperCase) {
        char[] digits = upperCase ? UPPER_CASE_DIGITS : LOWER_CASE_DIGITS;
        char[] result = new char[len * 2];
        for (int i = 0; i < len; i++) {
            byte b = data[offset + i];
            int resultIndex = 2 * i;
            result[resultIndex] = (digits[(b >> 4) & 0x0f]);
            result[resultIndex + 1] = (digits[b & 0x0f]);
        }

        return result;
    }

    /**
     * Encodes the provided data as a sequence of hexadecimal characters.
     *
     * @param  data byte array to encode
     * @return the encoded data, using uppercase letters
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static String encodeToString(byte[] data) {
        return encodeToString(data, true /* upperCase */);
    }

    /**
     * Encodes the provided data as a sequence of hexadecimal characters.
     *
     * @param  data byte array to encode.
     * @param  upperCase {@code true} to use uppercase letters, {@code false}
     *         for lowercase
     * @return the encoded data
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static String encodeToString(byte[] data, boolean upperCase) {
        return new String(encode(data, upperCase));
    }
}
