package org.androidgraal.shared;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/**
 * A throwable and its cause chain serialized, one record per throwable, outermost first: class name, message, stack
 * trace. Suppressed throwables are not written. Every throwable reads back as a {@link MarshalledException}.
 */
public final class ThrowableMarshaller {

    private ThrowableMarshaller() {
    }

    /**
     * Serializes {@code throwable} and its causes, a cause chain stops where it repeats.
     */
    public static byte[] write(Throwable throwable) {
        List<Throwable> chain = new ArrayList<>();
        Set<Throwable> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        Throwable t = throwable;
        while (t != null && seen.add(t)) {
            chain.add(t);
            t = t.getCause();
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (DataOutputStream out = new DataOutputStream(bytes)) {
            out.writeInt(chain.size());
            for (Throwable link : chain) {
                writeThrowable(out, link);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return bytes.toByteArray();
    }

    /**
     * The throwable {@code wire} holds, its outermost trace continued by the calling thread's without the leading
     * frames of this class and of {@code skipped}.
     */
    public static MarshalledException read(byte[] wire, Class<?>... skipped) {
        String[] thrown;
        String[] messages;
        StackTraceElement[][] frames;
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(wire))) {
            int links = in.readInt();
            thrown = new String[links];
            messages = new String[links];
            frames = new StackTraceElement[links][];
            for (int i = 0; i < links; i++) {
                thrown[i] = readString(in);
                messages[i] = readString(in);
                StackTraceElement[] stack = new StackTraceElement[in.readInt()];
                for (int j = 0; j < stack.length; j++) {
                    stack[j] = new StackTraceElement(readString(in), readString(in), readString(in), in.readInt());
                }
                frames[i] = stack;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        MarshalledException cause = null;
        for (int i = thrown.length - 1; i >= 0; i--) {
            StackTraceElement[] stack = i == 0 ? merge(frames[i], new Throwable().getStackTrace(), skipped) : frames[i];
            cause = new MarshalledException(thrown[i], messages[i], stack, cause);
        }
        return cause;
    }

    private static void writeThrowable(DataOutputStream out, Throwable throwable) throws IOException {
        String thrown = throwable instanceof MarshalledException marshalled
                ? marshalled.getForeignExceptionClassName()
                : throwable.getClass().getName();
        writeString(out, thrown);
        writeString(out, throwable.getMessage());
        StackTraceElement[] stack = throwable.getStackTrace();
        out.writeInt(stack.length);
        for (StackTraceElement e : stack) {
            writeString(out, e.getClassName());
            writeString(out, e.getMethodName());
            writeString(out, e.getFileName());
            out.writeInt(e.getLineNumber());
        }
    }

    private static StackTraceElement[] merge(StackTraceElement[] foreign, StackTraceElement[] local,
            Class<?>[] skipped) {
        int skip = 0;
        while (skip < local.length && skipped(local[skip].getClassName(), skipped)) {
            skip++;
        }
        StackTraceElement[] merged = new StackTraceElement[foreign.length + local.length - skip];
        System.arraycopy(foreign, 0, merged, 0, foreign.length);
        System.arraycopy(local, skip, merged, foreign.length, local.length - skip);
        return merged;
    }

    private static boolean skipped(String className, Class<?>[] skipped) {
        if (className.equals(ThrowableMarshaller.class.getName())) {
            return true;
        }
        for (Class<?> c : skipped) {
            if (className.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    private static void writeString(DataOutputStream out, String s) throws IOException {
        if (s == null) {
            out.writeInt(-1);
            return;
        }
        out.writeInt(s.length());
        out.writeChars(s);
    }

    private static String readString(DataInputStream in) throws IOException {
        int length = in.readInt();
        if (length < 0) {
            return null;
        }
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) {
            chars[i] = in.readChar();
        }
        return new String(chars);
    }
}
