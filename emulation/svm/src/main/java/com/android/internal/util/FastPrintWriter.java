/*
 * Copyright (C) 2013 The Android Open Source Project
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

package com.android.internal.util;

import android.compat.annotation.UnsupportedAppUsage;
import android.util.Log;
import android.util.Printer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

@android.ravenwood.annotation.RavenwoodKeepWholeClass
public class FastPrintWriter extends PrintWriter {
    private static class DummyWriter extends Writer {
        @Override
        public void close() throws IOException {
            UnsupportedOperationException ex
                    = new UnsupportedOperationException("Shouldn't be here");
            throw ex;
        }

        @Override
        public void flush() throws IOException {
            close();
        }

        @Override
        public void write(char[] buf, int offset, int count) throws IOException {
            close();
        }
    };

    private final int mBufferLen;
    private final char[] mText;
    private int mPos;

    final private OutputStream mOutputStream;
    final private boolean mAutoFlush;
    final private String mSeparator;

    final private Writer mWriter;
    final private Printer mPrinter;

    private CharsetEncoder mCharset;
    final private ByteBuffer mBytes;

    private boolean mIoError;

    /**
     * Constructs a new {@code PrintWriter} with {@code out} as its target
     * stream. By default, the new print writer does not automatically flush its
     * contents to the target stream when a newline is encountered.
     *
     * @param out
     *            the target output stream.
     * @throws NullPointerException
     *             if {@code out} is {@code null}.
     */
    @UnsupportedAppUsage
    public FastPrintWriter(OutputStream out) {
        this(out, false, 8192);
    }

    /**
     * Constructs a new {@code PrintWriter} with {@code out} as its target
     * stream and a custom buffer size. The parameter {@code autoFlush} determines
     * if the print writer automatically flushes its contents to the target stream
     * when a newline is encountered.
     *
     * @param out
     *            the target output stream.
     * @param autoFlush
     *            indicates whether contents are flushed upon encountering a
     *            newline sequence.
     * @param bufferLen
     *            specifies the size of the FastPrintWriter's internal buffer; the
     *            default is 8192.
     * @throws NullPointerException
     *             if {@code out} is {@code null}.
     */
    public FastPrintWriter(OutputStream out, boolean autoFlush, int bufferLen) {
        super(new DummyWriter(), autoFlush);
        if (out == null) {
            throw new NullPointerException("out is null");
        }
        mBufferLen = bufferLen;
        mText = new char[bufferLen];
        mBytes = ByteBuffer.allocate(mBufferLen);
        mOutputStream = out;
        mWriter = null;
        mPrinter = null;
        mAutoFlush = autoFlush;
        mSeparator = System.lineSeparator();
        initDefaultEncoder();
    }

    /**
     * Sets the error flag of this writer to true.
     */
    protected void setError() {
        synchronized (lock) {
            mIoError = true;
        }
    }

    private final void initDefaultEncoder() {
        mCharset = Charset.defaultCharset().newEncoder();
        mCharset.onMalformedInput(CodingErrorAction.REPLACE);
        mCharset.onUnmappableCharacter(CodingErrorAction.REPLACE);
    }

    private void appendLocked(String str, int i, final int length) throws IOException {
        final int BUFFER_LEN = mBufferLen;
        if (length > BUFFER_LEN) {
            final int end = i + length;
            while (i < end) {
                int next = i + BUFFER_LEN;
                appendLocked(str, i, next < end ? BUFFER_LEN : (end - i));
                i = next;
            }
            return;
        }
        int pos = mPos;
        if ((pos+length) > BUFFER_LEN) {
            flushLocked();
            pos = mPos;
        }
        str.getChars(i, i + length, mText, pos);
        mPos = pos + length;
    }

    private void flushBytesLocked() throws IOException {
        if (!mIoError) {
            int position;
            if ((position = mBytes.position()) > 0) {
                mBytes.flip();
                mOutputStream.write(mBytes.array(), 0, position);
                mBytes.clear();
            }
        }
    }

    private void flushLocked() throws IOException {
        //Log.i("PackageManager", "flush mPos=" + mPos);
        if (mPos > 0) {
            if (mOutputStream != null) {
                CharBuffer charBuffer = CharBuffer.wrap(mText, 0, mPos);
                CoderResult result = mCharset.encode(charBuffer, mBytes, true);
                while (!mIoError) {
                    if (result.isError()) {
                        throw new IOException(result.toString());
                    } else if (result.isOverflow()) {
                        flushBytesLocked();
                        result = mCharset.encode(charBuffer, mBytes, true);
                        continue;
                    }
                    break;
                }
                if (!mIoError) {
                    flushBytesLocked();
                    mOutputStream.flush();
                }
            } else if (mWriter != null) {
                if (!mIoError) {
                    mWriter.write(mText, 0, mPos);
                    mWriter.flush();
                }
            } else {
                int nonEolOff = 0;
                final int sepLen = mSeparator.length();
                final int len = sepLen < mPos ? sepLen : mPos;
                while (nonEolOff < len && mText[mPos-1-nonEolOff]
                        == mSeparator.charAt(mSeparator.length()-1-nonEolOff)) {
                    nonEolOff++;
                }
                if (nonEolOff >= mPos) {
                    mPrinter.println("");
                } else {
                    mPrinter.println(new String(mText, 0, mPos-nonEolOff));
                }
            }
            mPos = 0;
        }
    }

    @Override
    public void close() {
        synchronized (lock) {
            try {
                flushLocked();
                if (mOutputStream != null) {
                    mOutputStream.close();
                } else if (mWriter != null) {
                    mWriter.close();
                }
            } catch (IOException e) {
                Log.w("FastPrintWriter", "Write failure", e);
                setError();
            }
        }
    }

    /**
     * Prints a string to the target. The string is converted to an array of
     * bytes using the encoding chosen during the construction of this writer.
     * The bytes are then written to the target with {@code write(int)}.
     * <p>
     * If an I/O error occurs, this writer's error flag is set to {@code true}.
     *
     * @param str
     *            the string to print to the target.
     * @see #write(int)
     */
    public void print(String str) {
        if (str == null) {
            str = String.valueOf((Object) null);
        }
        synchronized (lock) {
            try {
                appendLocked(str, 0, str.length());
            } catch (IOException e) {
                Log.w("FastPrintWriter", "Write failure", e);
                setError();
            }
        }
    }


    /**
     * Prints a newline. Flushes this writer if the autoFlush flag is set to {@code true}.
     */
    public void println() {
        synchronized (lock) {
            try {
                appendLocked(mSeparator, 0, mSeparator.length());
                if (mAutoFlush) {
                    flushLocked();
                }
            } catch (IOException e) {
                Log.w("FastPrintWriter", "Write failure", e);
                setError();
            }
        }
    }
}
