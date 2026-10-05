package org.androidgraal.gles;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.CTypeConversion.CCharPointerHolder;
import org.graalvm.nativeimage.c.type.CTypeConversion.CCharPointerPointerHolder;

public final class CString {

    private CString() {
    }

    public static CCharPointerHolder of(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        return CTypeConversion.toCString(value);
    }

    public static CCharPointerPointerHolder of(String[] values) {
        if (values == null) {
            throw new IllegalArgumentException("null array");
        }
        for (String value : values) {
            if (value == null)
                throw new IllegalArgumentException();
        }
        return CTypeConversion.toCStrings(values);
    }

    public static String toJava(CCharPointer value) {
        return value.isNull() ? null : CTypeConversion.utf8ToJavaString(value);
    }

    public static String toJava(byte[] chars, int length) {
        return new String(chars, 0, Math.clamp(length, 0, chars.length), UTF_8);
    }
}
