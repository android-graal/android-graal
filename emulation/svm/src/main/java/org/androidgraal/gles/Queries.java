package org.androidgraal.gles;

import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.CIntPointer;

final class Queries {

    private Queries() {
    }

    public static int queryInteger(int pname) {
        CIntPointer value = StackValue.get(CIntPointer.class);
        value.write(0);
        GLESv2.glGetIntegerv(pname, value);
        return value.read();
    }

    public static int queryProgram(int program, int pname) {
        CIntPointer value = StackValue.get(CIntPointer.class);
        value.write(0);
        GLESv2.glGetProgramiv(program, pname, value);
        return value.read();
    }

    public static int queryShader(int shader, int pname) {
        CIntPointer value = StackValue.get(CIntPointer.class);
        value.write(0);
        GLESv2.glGetShaderiv(shader, pname, value);
        return value.read();
    }

    public static int queryProgramPipeline(int pipeline, int pname) {
        CIntPointer value = StackValue.get(CIntPointer.class);
        value.write(0);
        GLESv3.glGetProgramPipelineiv(pipeline, pname, value);
        return value.read();
    }

    public static int queryUniformBlock(int program, int uniformBlockIndex, int pname) {
        CIntPointer value = StackValue.get(CIntPointer.class);
        value.write(0);
        GLESv3.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, value);
        return value.read();
    }
}
