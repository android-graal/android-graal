package org.androidgraal.bionic;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CConstant;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.word.PointerBase;

@CContext(BionicDirectives.class)
public final class Vm {

    private Vm() {
    }

    @CConstant
    public static native int VMADDR_CID_ANY();

    @CConstant
    public static native int VMADDR_CID_HOST();

    @CConstant
    public static native int VMADDR_CID_LOCAL();

    @CConstant
    public static native int VMADDR_PORT_ANY();

    @CStruct(addStructKeyword = true)
    public interface sockaddr_vm extends PointerBase {
        @CField
        short svm_family();

        @CField
        void svm_family(short value);

        @CField
        int svm_port();

        @CField
        void svm_port(int value);

        @CField
        int svm_cid();

        @CField
        void svm_cid(int value);
    }
}
