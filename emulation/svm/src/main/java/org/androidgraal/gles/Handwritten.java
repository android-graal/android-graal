package org.androidgraal.gles;

import static android.opengl.GLES20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH;
import static android.opengl.GLES20.GL_ACTIVE_UNIFORM_MAX_LENGTH;
import static android.opengl.GLES20.GL_INFO_LOG_LENGTH;
import static android.opengl.GLES20.GL_SHADER_SOURCE_LENGTH;
import static android.opengl.GLES30.GL_BUFFER_MAP_LENGTH;
import static android.opengl.GLES30.GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH;
import static android.opengl.GLES30.GL_UNIFORM_BLOCK_NAME_LENGTH;

import android.opengl.GLES32;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.androidgraal.android.CString;
import org.androidgraal.android.Pointer;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;
import org.graalvm.nativeimage.c.type.CLongPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.nativeimage.c.type.CTypeConversion.CCharPointerHolder;
import org.graalvm.nativeimage.c.type.CTypeConversion.CCharPointerPointerHolder;
import org.graalvm.nativeimage.c.type.WordPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

public final class Handwritten {

    private Handwritten() {
    }

    public static String glGetProgramInfoLog(int program) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_INFO_LOG_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetProgramInfoLog(program, chars.length, length, Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetShaderInfoLog(int shader) {
        byte[] chars = new byte[Queries.queryShader(shader, GL_INFO_LOG_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetShaderInfoLog(shader, chars.length, length, Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetShaderSource(int shader) {
        byte[] chars = new byte[Queries.queryShader(shader, GL_SHADER_SOURCE_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetShaderSource(shader, chars.length, length, Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static void glShaderSource(int shader, String string) {
        try (CCharPointerHolder source = CString.of(string)) {
            CCharPointerPointer strings = StackValue.get(CCharPointerPointer.class);
            strings.write(source.get());
            GLESv2.glShaderSource(shader, 1, strings, WordFactory.nullPointer());
        }
    }

    public static String glGetActiveAttrib(int program, int index, IntBuffer size, IntBuffer type) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, 4);
                PinnedObject typePin = Pointer.pin(type, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetActiveAttrib(program, index, chars.length, length, Pointer.address(size, sizePin),
                    Pointer.address(type, typePin), Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetActiveAttrib(int program, int index, int[] size, int sizeOffset, int[] type,
            int typeOffset) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_ACTIVE_ATTRIBUTE_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, sizeOffset, 4);
                PinnedObject typePin = Pointer.pin(type, typeOffset, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetActiveAttrib(program, index, chars.length, length, Pointer.address(sizePin, sizeOffset),
                    Pointer.address(typePin, typeOffset), Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetActiveUniform(int program, int index, IntBuffer size, IntBuffer type) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_ACTIVE_UNIFORM_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, 4);
                PinnedObject typePin = Pointer.pin(type, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetActiveUniform(program, index, chars.length, length, Pointer.address(size, sizePin),
                    Pointer.address(type, typePin), Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetActiveUniform(int program, int index, int[] size, int sizeOffset, int[] type,
            int typeOffset) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_ACTIVE_UNIFORM_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, sizeOffset, 4);
                PinnedObject typePin = Pointer.pin(type, typeOffset, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv2.glGetActiveUniform(program, index, chars.length, length, Pointer.address(sizePin, sizeOffset),
                    Pointer.address(typePin, typeOffset), Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetActiveUniformBlockName(int program, int uniformBlockIndex) {
        byte[] chars = new byte[Queries.queryUniformBlock(program, uniformBlockIndex, GL_UNIFORM_BLOCK_NAME_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject pin = PinnedObject.create(chars)) {
            GLESv3.glGetActiveUniformBlockName(program, uniformBlockIndex, chars.length, length,
                    Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static void glGetActiveUniformBlockName(int program, int uniformBlockIndex, Buffer length,
            Buffer uniformBlockName) {
        try (PinnedObject lengthPin = Pointer.pin(length, 4);
                PinnedObject uniformBlockNamePin = Pointer.pin(uniformBlockName)) {
            int bufSize = uniformBlockName.remaining() << Pointer.elementShift(uniformBlockName);
            GLESv3.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize, Pointer.address(length, lengthPin),
                    Pointer.address(uniformBlockName, uniformBlockNamePin));
        }
    }

    public static Buffer glGetBufferPointerv(int target, int pname) {
        CLongPointer mapLength = StackValue.get(CLongPointer.class);
        mapLength.write(0);
        GLESv3.glGetBufferParameteri64v(target, GL_BUFFER_MAP_LENGTH, mapLength);
        WordPointer pointer = StackValue.get(WordPointer.class);
        pointer.write(WordFactory.nullPointer());
        GLESv3.glGetBufferPointerv(target, pname, pointer);
        return mappedBuffer(pointer.read(), mapLength.read());
    }

    public static Buffer glMapBufferRange(int target, int offset, int length, int access) {
        return mappedBuffer(
                GLESv3.glMapBufferRange(target, WordFactory.signed(offset), WordFactory.signed(length), access),
                length);
    }

    public static void glGetTransformFeedbackVarying(int program, int index, int bufSize, IntBuffer length,
            IntBuffer size, IntBuffer type, byte name) {
        throw new UnsupportedOperationException(
                "glGetTransformFeedbackVarying with a byte name is deprecated, use the ByteBuffer or String form");
    }

    public static String glGetTransformFeedbackVarying(int program, int index, int[] size, int sizeOffset, int[] type,
            int typeOffset) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, sizeOffset, 4);
                PinnedObject typePin = Pointer.pin(type, typeOffset, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv3.glGetTransformFeedbackVarying(program, index, chars.length, length,
                    Pointer.address(sizePin, sizeOffset), Pointer.address(typePin, typeOffset),
                    Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetTransformFeedbackVarying(int program, int index, IntBuffer size, IntBuffer type) {
        byte[] chars = new byte[Queries.queryProgram(program, GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject sizePin = Pointer.pin(size, 4);
                PinnedObject typePin = Pointer.pin(type, 4);
                PinnedObject pin = PinnedObject.create(chars)) {
            GLESv3.glGetTransformFeedbackVarying(program, index, chars.length, length, Pointer.address(size, sizePin),
                    Pointer.address(type, typePin), Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static void glGetUniformIndices(int program, String[] uniformNames, int[] uniformIndices,
            int uniformIndicesOffset) {
        try (CCharPointerPointerHolder names = CString.of(uniformNames);
                PinnedObject uniformIndicesPin = Pointer.pin(uniformIndices, uniformIndicesOffset,
                        uniformNames.length * 4L)) {
            GLESv3.glGetUniformIndices(program, uniformNames.length, names.get(),
                    Pointer.address(uniformIndicesPin, uniformIndicesOffset));
        }
    }

    public static void glGetUniformIndices(int program, String[] uniformNames, IntBuffer uniformIndices) {
        try (CCharPointerPointerHolder names = CString.of(uniformNames);
                PinnedObject uniformIndicesPin = Pointer.pin(uniformIndices, uniformNames.length * 4L)) {
            GLESv3.glGetUniformIndices(program, uniformNames.length, names.get(),
                    Pointer.address(uniformIndices, uniformIndicesPin));
        }
    }

    public static void glTransformFeedbackVaryings(int program, String[] varyings, int bufferMode) {
        try (CCharPointerPointerHolder names = CString.of(varyings)) {
            GLESv3.glTransformFeedbackVaryings(program, varyings.length, names.get(), bufferMode);
        }
    }

    public static int glCreateShaderProgramv(int type, String[] strings) {
        try (CCharPointerPointerHolder sources = CString.of(strings)) {
            return GLESv3.glCreateShaderProgramv(type, strings.length, sources.get());
        }
    }

    public static String glGetProgramPipelineInfoLog(int pipeline) {
        byte[] chars = new byte[Queries.queryProgramPipeline(pipeline, GL_INFO_LOG_LENGTH)];
        CIntPointer length = StackValue.get(CIntPointer.class);
        length.write(0);
        try (PinnedObject pin = PinnedObject.create(chars)) {
            GLESv3.glGetProgramPipelineInfoLog(pipeline, chars.length, length, Pointer.address(pin, 0));
        }
        return CString.toJava(chars, length.read());
    }

    public static String glGetProgramResourceName(int program, int programInterface, int index) {
        throw notOnAndroid("glGetProgramResourceName");
    }

    public static int glGetDebugMessageLog(int count, int bufSize, int[] sources, int sourcesOffset, int[] types,
            int typesOffset, int[] ids, int idsOffset, int[] severities, int severitiesOffset, int[] lengths,
            int lengthsOffset, byte[] messageLog, int messageLogOffset) {
        throw notOnAndroid("glGetDebugMessageLog");
    }

    public static int glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids,
            IntBuffer severities, IntBuffer lengths, ByteBuffer messageLog) {
        throw notOnAndroid("glGetDebugMessageLog");
    }

    public static String[] glGetDebugMessageLog(int count, int[] sources, int sourcesOffset, int[] types,
            int typesOffset, int[] ids, int idsOffset, int[] severities, int severitiesOffset) {
        throw notOnAndroid("glGetDebugMessageLog");
    }

    public static String[] glGetDebugMessageLog(int count, IntBuffer sources, IntBuffer types, IntBuffer ids,
            IntBuffer severities) {
        throw notOnAndroid("glGetDebugMessageLog");
    }

    public static String glGetObjectLabel(int identifier, int name) {
        throw notOnAndroid("glGetObjectLabel");
    }

    public static void glObjectPtrLabel(long ptr, String label) {
        throw notOnAndroid("glObjectPtrLabel");
    }

    public static String glGetObjectPtrLabel(long ptr) {
        throw notOnAndroid("glGetObjectPtrLabel");
    }

    public static long glGetPointerv(int pname) {
        throw notOnAndroid("glGetPointerv");
    }

    public static void glDebugMessageCallback(GLES32.DebugProc callback) {
        throw notOnAndroid("glDebugMessageCallback");
    }

    private static UnsupportedOperationException notOnAndroid(String function) {
        return new UnsupportedOperationException(function + " is not implemented on Android");
    }

    private static Buffer mappedBuffer(PointerBase address, long size) {
        if (address.isNull()) {
            return null;
        }
        if (size > Integer.MAX_VALUE) {
            throw new IllegalStateException("mapping of " + size + " bytes exceeds a ByteBuffer");
        }
        return CTypeConversion.asByteBuffer(address, (int) size).order(ByteOrder.BIG_ENDIAN);
    }
}
