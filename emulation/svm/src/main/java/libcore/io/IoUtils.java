/*
 * Copyright (C) 2010 The Android Open Source Project
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

package libcore.io;

import static android.annotation.SystemApi.Client.MODULE_LIBRARIES;

import android.annotation.SystemApi;
import android.compat.annotation.UnsupportedAppUsage;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;

import org.androidgraal.android.FileDescriptors;
import org.androidgraal.hosted.annotate.Adapted;
import org.androidgraal.hosted.annotate.Premise;
import org.androidgraal.hosted.annotate.Transcribed;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import libcore.util.NonNull;
import libcore.util.Nullable;

import static android.system.OsConstants.F_GETFL;
import static android.system.OsConstants.F_SETFL;
import static android.system.OsConstants.O_NONBLOCK;
import static android.system.OsConstants.O_RDONLY;

/** @hide */
@SystemApi(client = MODULE_LIBRARIES)
public final class IoUtils {
    private IoUtils() {
    }

    /**
     * Acquires ownership of an integer file descriptor from a {@link FileDescriptor}.
     *
     * This method invalidates the {@link FileDescriptor} passed in.
     *
     * The important part of this function is that you are taking ownership of a resource that you
     * must either clean up yourself, or hand off to some other object that does that for you.
     *
     * See bionic/include/android/fdsan.h for more details.
     *
     * @param fd {@link FileDescriptor} to take ownership from, must be non-{@code null}.
     * @return raw file descriptor
     * @throws NullPointerException if fd is null
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    @Adapted(reason = "releases the int through FileDescriptors without fdsan",
            premise = Premise.NO_FDSAN)
    public static int acquireRawFd(@NonNull FileDescriptor fd) {
        Objects.requireNonNull(fd);

        // Get the numeric Unix file descriptor. -1 means it is invalid; for example if
        // {@link FileDescriptor#release$()} has already been called on the FileDescriptor.
        int rawFd = FileDescriptors.release(fd);
        return rawFd;
    }

    /**
     * Closes a file descriptor, so that it no longer refers to any file and may
     * be reused. Also resets the internal int to -1.
     *
     * @param fd is {@link FileDescriptor} instance, invalid value is ignored.
     * @throws IOException if an I/O error occurred.
     *
     * @hide
     */
    @SystemApi(client = MODULE_LIBRARIES)
    @Adapted(reason = "closes through FileDescriptors instead of IoBridge.closeAndSignalBlockedThreads",
            premise = Premise.NO_LIBCORE_JDK)
    public static void close(@Nullable FileDescriptor fd) throws IOException {
        FileDescriptors.close(fd);
    }

    /**
     * Calls {@link #close(FileDescriptor)}, ignoring any exceptions.
     *
     * @param fd is {@link FileDescriptor} instance, invalid value is ignored.
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    public static void closeQuietly(@Nullable FileDescriptor fd) {
        try {
            IoUtils.close(fd);
        } catch (IOException ignored) {
        }
    }

    /**
     * Sets file descriptor to be blocking or non-blocking.
     *
     * @param fd is {@link FileDescriptor} instance
     * @param blocking is a boolean that defines whether fd should be blocking or non-blocking
     * @throws IOException if system API call fails
     *
     * @hide
     */
    @UnsupportedAppUsage
    @SystemApi(client = MODULE_LIBRARIES)
    @Transcribed
    public static void setBlocking(@NonNull FileDescriptor fd, boolean blocking) throws IOException {
        try {
            int flags = Os.fcntlVoid(fd, F_GETFL);
            if (!blocking) {
                flags |= O_NONBLOCK;
            } else {
                flags &= ~O_NONBLOCK;
            }
            Os.fcntlInt(fd, F_SETFL, flags);
        } catch (ErrnoException errnoException) {
            throw errnoException.rethrowAsIOException();
        }
    }
}
