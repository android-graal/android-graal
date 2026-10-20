/*
 * Copyright (C) 2006 The Android Open Source Project
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

package android.os;

import static android.os.ParcelFileDescriptor.MODE_APPEND;
import static android.os.ParcelFileDescriptor.MODE_CREATE;
import static android.os.ParcelFileDescriptor.MODE_READ_ONLY;
import static android.os.ParcelFileDescriptor.MODE_READ_WRITE;
import static android.os.ParcelFileDescriptor.MODE_TRUNCATE;
import static android.os.ParcelFileDescriptor.MODE_WRITE_ONLY;
import static android.system.OsConstants.EINVAL;
import static android.system.OsConstants.ENOSYS;
import static android.system.OsConstants.F_OK;
import static android.system.OsConstants.EIO;
import static android.system.OsConstants.O_ACCMODE;
import static android.system.OsConstants.O_APPEND;
import static android.system.OsConstants.O_CREAT;
import static android.system.OsConstants.O_RDONLY;
import static android.system.OsConstants.O_RDWR;
import static android.system.OsConstants.O_TRUNC;
import static android.system.OsConstants.O_WRONLY;
import static android.system.OsConstants.R_OK;
import static android.system.OsConstants.S_ISFIFO;
import static android.system.OsConstants.S_ISREG;
import static android.system.OsConstants.S_ISSOCK;
import static android.system.OsConstants.W_OK;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.TestApi;
import android.compat.annotation.UnsupportedAppUsage;
import android.system.ErrnoException;
import android.system.Os;
import android.system.StructStat;
import android.text.TextUtils;
import android.util.EmptyArray;
import android.util.Log;
import android.util.Slog;

import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ArrayUtils;

import libcore.io.IoUtils;

import org.androidgraal.hosted.annotate.Emulated;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * Utility methods useful for working with files.
 */
@android.ravenwood.annotation.RavenwoodKeepWholeClass
@Emulated
public final class FileUtils {
    @UnsupportedAppUsage
    private FileUtils() {
    }

    /** {@hide} */
    public static int translateModeStringToPosix(String mode) {
        for (int i = 0; i < mode.length(); i++) {
            switch (mode.charAt(i)) {
                case 'r':
                case 'w':
                case 't':
                case 'a':
                    break;
                default:
                    throw new IllegalArgumentException("Bad mode: " + mode);
            }
        }

        int res = 0;
        if (mode.startsWith("rw")) {
            res = O_RDWR | O_CREAT;
        } else if (mode.startsWith("w")) {
            res = O_WRONLY | O_CREAT;
        } else if (mode.startsWith("r")) {
            res = O_RDONLY;
        } else {
            throw new IllegalArgumentException("Bad mode: " + mode);
        }
        if (mode.indexOf('t') != -1) {
            res |= O_TRUNC;
        }
        if (mode.indexOf('a') != -1) {
            res |= O_APPEND;
        }
        return res;
    }

    /** {@hide} */
    public static int translateModePosixToPfd(int mode) {
        int res = 0;
        if ((mode & O_ACCMODE) == O_RDWR) {
            res = MODE_READ_WRITE;
        } else if ((mode & O_ACCMODE) == O_WRONLY) {
            res = MODE_WRITE_ONLY;
        } else if ((mode & O_ACCMODE) == O_RDONLY) {
            res = MODE_READ_ONLY;
        } else {
            throw new IllegalArgumentException("Bad mode: " + mode);
        }
        if ((mode & O_CREAT) == O_CREAT) {
            res |= MODE_CREATE;
        }
        if ((mode & O_TRUNC) == O_TRUNC) {
            res |= MODE_TRUNCATE;
        }
        if ((mode & O_APPEND) == O_APPEND) {
            res |= MODE_APPEND;
        }
        return res;
    }

    /** {@hide} */
    public static int translateModePfdToPosix(int mode) {
        int res = 0;
        if ((mode & MODE_READ_WRITE) == MODE_READ_WRITE) {
            res = O_RDWR;
        } else if ((mode & MODE_WRITE_ONLY) == MODE_WRITE_ONLY) {
            res = O_WRONLY;
        } else if ((mode & MODE_READ_ONLY) == MODE_READ_ONLY) {
            res = O_RDONLY;
        } else {
            throw new IllegalArgumentException("Bad mode: " + mode);
        }
        if ((mode & MODE_CREATE) == MODE_CREATE) {
            res |= O_CREAT;
        }
        if ((mode & MODE_TRUNCATE) == MODE_TRUNCATE) {
            res |= O_TRUNC;
        }
        if ((mode & MODE_APPEND) == MODE_APPEND) {
            res |= O_APPEND;
        }
        return res;
    }
}
