package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Utsname {

    private Utsname() {
    }

    @CStruct(addStructKeyword = true)
    public interface utsname extends PointerBase {
        @CFieldAddress
        CCharPointer sysname();

        @CFieldAddress
        CCharPointer nodename();

        @CFieldAddress
        CCharPointer release();

        @CFieldAddress
        CCharPointer version();

        @CFieldAddress
        CCharPointer machine();
    }

    @CFunction
    public static native int uname(utsname buf);
}
