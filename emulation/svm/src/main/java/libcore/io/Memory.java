/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.io;

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.compat.annotation.UnsupportedAppUsage;
import android.annotation.SystemApi;

import java.nio.ByteOrder;

import dalvik.annotation.optimization.FastNative;

import libcore.util.NonNull;

import org.androidgraal.hosted.annotate.Emulated;

/**
 * Unsafe access to memory.
 *
 * @hide
 */
@SystemApi(client = MODULE_LIBRARIES)
@Emulated
public final class Memory {
    private Memory() { }

    /**
     * Gets int value from a byte buffer {@code src} at offset {@code offset} using
     * {@code order} byte order.
     *
     * @param src    source byte buffer
     * @param offset offset in {@code src} to get bytes from
     * @param order  byte order
     * @return int value
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static int peekInt(@NonNull byte[] src, int offset, @NonNull ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            return (((src[offset++] & 0xff) << 24) |
                    ((src[offset++] & 0xff) << 16) |
                    ((src[offset++] & 0xff) <<  8) |
                    ((src[offset  ] & 0xff) <<  0));
        } else {
            return (((src[offset++] & 0xff) <<  0) |
                    ((src[offset++] & 0xff) <<  8) |
                    ((src[offset++] & 0xff) << 16) |
                    ((src[offset  ] & 0xff) << 24));
        }
    }

    /**
     * Writes given int value {@code value} to a byte buffer {@code dst} using
     * {@code order} byte order.
     *
     * @param dst    byte buffer where to write {@code value}
     * @param offset offset in {@code dst} to put value to
     * @param value  int value to write
     * @param order  byte order
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    public static void pokeInt(@NonNull byte[] dst, int offset, int value, @NonNull ByteOrder order) {
        if (order == ByteOrder.BIG_ENDIAN) {
            dst[offset++] = (byte) ((value >> 24) & 0xff);
            dst[offset++] = (byte) ((value >> 16) & 0xff);
            dst[offset++] = (byte) ((value >>  8) & 0xff);
            dst[offset  ] = (byte) ((value >>  0) & 0xff);
        } else {
            dst[offset++] = (byte) ((value >>  0) & 0xff);
            dst[offset++] = (byte) ((value >>  8) & 0xff);
            dst[offset++] = (byte) ((value >> 16) & 0xff);
            dst[offset  ] = (byte) ((value >> 24) & 0xff);
        }
    }
}
