// Generated from android.jar and the NDK's GLES headers.
package org.androidgraal.gles;

import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.function.InvokeCFunctionPointer;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;
import org.graalvm.word.WordFactory;

@CLibrary("EGL")
public final class GLESv32 {
    private static GlBlendBarrier glBlendBarrier;

    private static GlCopyImageSubData glCopyImageSubData;

    private static GlDebugMessageControl glDebugMessageControl;

    private static GlDebugMessageInsert glDebugMessageInsert;

    private static GlDebugMessageCallback glDebugMessageCallback;

    private static GlGetDebugMessageLog glGetDebugMessageLog;

    private static GlPushDebugGroup glPushDebugGroup;

    private static GlPopDebugGroup glPopDebugGroup;

    private static GlObjectLabel glObjectLabel;

    private static GlGetObjectLabel glGetObjectLabel;

    private static GlObjectPtrLabel glObjectPtrLabel;

    private static GlGetObjectPtrLabel glGetObjectPtrLabel;

    private static GlGetPointerv glGetPointerv;

    private static GlEnablei glEnablei;

    private static GlDisablei glDisablei;

    private static GlBlendEquationi glBlendEquationi;

    private static GlBlendEquationSeparatei glBlendEquationSeparatei;

    private static GlBlendFunci glBlendFunci;

    private static GlBlendFuncSeparatei glBlendFuncSeparatei;

    private static GlColorMaski glColorMaski;

    private static GlIsEnabledi glIsEnabledi;

    private static GlDrawElementsBaseVertex glDrawElementsBaseVertex;

    private static GlDrawRangeElementsBaseVertex glDrawRangeElementsBaseVertex;

    private static GlDrawElementsInstancedBaseVertex glDrawElementsInstancedBaseVertex;

    private static GlFramebufferTexture glFramebufferTexture;

    private static GlPrimitiveBoundingBox glPrimitiveBoundingBox;

    private static GlGetGraphicsResetStatus glGetGraphicsResetStatus;

    private static GlReadnPixels glReadnPixels;

    private static GlGetnUniformfv glGetnUniformfv;

    private static GlGetnUniformiv glGetnUniformiv;

    private static GlGetnUniformuiv glGetnUniformuiv;

    private static GlMinSampleShading glMinSampleShading;

    private static GlPatchParameteri glPatchParameteri;

    private static GlTexParameterIiv glTexParameterIiv;

    private static GlTexParameterIuiv glTexParameterIuiv;

    private static GlGetTexParameterIiv glGetTexParameterIiv;

    private static GlGetTexParameterIuiv glGetTexParameterIuiv;

    private static GlSamplerParameterIiv glSamplerParameterIiv;

    private static GlSamplerParameterIuiv glSamplerParameterIuiv;

    private static GlGetSamplerParameterIiv glGetSamplerParameterIiv;

    private static GlGetSamplerParameterIuiv glGetSamplerParameterIuiv;

    private static GlTexBuffer glTexBuffer;

    private static GlTexBufferRange glTexBufferRange;

    private static GlTexStorage3DMultisample glTexStorage3DMultisample;

    private GLESv32() {
    }

    @CFunction("eglGetProcAddress")
    private static native PointerBase eglGetProcAddress(CCharPointer procname);

    private static <T extends PointerBase> T resolve(String name) {
        PointerBase function;
        try (CTypeConversion.CCharPointerHolder procname = CTypeConversion.toCString(name)) {
            function = eglGetProcAddress(procname.get());
        }
        if (function.isNull()) {
            throw new UnsatisfiedLinkError(name + " is not available on this device");
        }
        return WordFactory.pointer(function.rawValue());
    }

    public static GlBlendBarrier glBlendBarrier() {
        GlBlendBarrier pointer = glBlendBarrier;
        if (pointer.isNull()) {
            pointer = resolve("glBlendBarrier");
            glBlendBarrier = pointer;
        }
        return pointer;
    }

    public static GlCopyImageSubData glCopyImageSubData() {
        GlCopyImageSubData pointer = glCopyImageSubData;
        if (pointer.isNull()) {
            pointer = resolve("glCopyImageSubData");
            glCopyImageSubData = pointer;
        }
        return pointer;
    }

    public static GlDebugMessageControl glDebugMessageControl() {
        GlDebugMessageControl pointer = glDebugMessageControl;
        if (pointer.isNull()) {
            pointer = resolve("glDebugMessageControl");
            glDebugMessageControl = pointer;
        }
        return pointer;
    }

    public static GlDebugMessageInsert glDebugMessageInsert() {
        GlDebugMessageInsert pointer = glDebugMessageInsert;
        if (pointer.isNull()) {
            pointer = resolve("glDebugMessageInsert");
            glDebugMessageInsert = pointer;
        }
        return pointer;
    }

    public static GlDebugMessageCallback glDebugMessageCallback() {
        GlDebugMessageCallback pointer = glDebugMessageCallback;
        if (pointer.isNull()) {
            pointer = resolve("glDebugMessageCallback");
            glDebugMessageCallback = pointer;
        }
        return pointer;
    }

    public static GlGetDebugMessageLog glGetDebugMessageLog() {
        GlGetDebugMessageLog pointer = glGetDebugMessageLog;
        if (pointer.isNull()) {
            pointer = resolve("glGetDebugMessageLog");
            glGetDebugMessageLog = pointer;
        }
        return pointer;
    }

    public static GlPushDebugGroup glPushDebugGroup() {
        GlPushDebugGroup pointer = glPushDebugGroup;
        if (pointer.isNull()) {
            pointer = resolve("glPushDebugGroup");
            glPushDebugGroup = pointer;
        }
        return pointer;
    }

    public static GlPopDebugGroup glPopDebugGroup() {
        GlPopDebugGroup pointer = glPopDebugGroup;
        if (pointer.isNull()) {
            pointer = resolve("glPopDebugGroup");
            glPopDebugGroup = pointer;
        }
        return pointer;
    }

    public static GlObjectLabel glObjectLabel() {
        GlObjectLabel pointer = glObjectLabel;
        if (pointer.isNull()) {
            pointer = resolve("glObjectLabel");
            glObjectLabel = pointer;
        }
        return pointer;
    }

    public static GlGetObjectLabel glGetObjectLabel() {
        GlGetObjectLabel pointer = glGetObjectLabel;
        if (pointer.isNull()) {
            pointer = resolve("glGetObjectLabel");
            glGetObjectLabel = pointer;
        }
        return pointer;
    }

    public static GlObjectPtrLabel glObjectPtrLabel() {
        GlObjectPtrLabel pointer = glObjectPtrLabel;
        if (pointer.isNull()) {
            pointer = resolve("glObjectPtrLabel");
            glObjectPtrLabel = pointer;
        }
        return pointer;
    }

    public static GlGetObjectPtrLabel glGetObjectPtrLabel() {
        GlGetObjectPtrLabel pointer = glGetObjectPtrLabel;
        if (pointer.isNull()) {
            pointer = resolve("glGetObjectPtrLabel");
            glGetObjectPtrLabel = pointer;
        }
        return pointer;
    }

    public static GlGetPointerv glGetPointerv() {
        GlGetPointerv pointer = glGetPointerv;
        if (pointer.isNull()) {
            pointer = resolve("glGetPointerv");
            glGetPointerv = pointer;
        }
        return pointer;
    }

    public static GlEnablei glEnablei() {
        GlEnablei pointer = glEnablei;
        if (pointer.isNull()) {
            pointer = resolve("glEnablei");
            glEnablei = pointer;
        }
        return pointer;
    }

    public static GlDisablei glDisablei() {
        GlDisablei pointer = glDisablei;
        if (pointer.isNull()) {
            pointer = resolve("glDisablei");
            glDisablei = pointer;
        }
        return pointer;
    }

    public static GlBlendEquationi glBlendEquationi() {
        GlBlendEquationi pointer = glBlendEquationi;
        if (pointer.isNull()) {
            pointer = resolve("glBlendEquationi");
            glBlendEquationi = pointer;
        }
        return pointer;
    }

    public static GlBlendEquationSeparatei glBlendEquationSeparatei() {
        GlBlendEquationSeparatei pointer = glBlendEquationSeparatei;
        if (pointer.isNull()) {
            pointer = resolve("glBlendEquationSeparatei");
            glBlendEquationSeparatei = pointer;
        }
        return pointer;
    }

    public static GlBlendFunci glBlendFunci() {
        GlBlendFunci pointer = glBlendFunci;
        if (pointer.isNull()) {
            pointer = resolve("glBlendFunci");
            glBlendFunci = pointer;
        }
        return pointer;
    }

    public static GlBlendFuncSeparatei glBlendFuncSeparatei() {
        GlBlendFuncSeparatei pointer = glBlendFuncSeparatei;
        if (pointer.isNull()) {
            pointer = resolve("glBlendFuncSeparatei");
            glBlendFuncSeparatei = pointer;
        }
        return pointer;
    }

    public static GlColorMaski glColorMaski() {
        GlColorMaski pointer = glColorMaski;
        if (pointer.isNull()) {
            pointer = resolve("glColorMaski");
            glColorMaski = pointer;
        }
        return pointer;
    }

    public static GlIsEnabledi glIsEnabledi() {
        GlIsEnabledi pointer = glIsEnabledi;
        if (pointer.isNull()) {
            pointer = resolve("glIsEnabledi");
            glIsEnabledi = pointer;
        }
        return pointer;
    }

    public static GlDrawElementsBaseVertex glDrawElementsBaseVertex() {
        GlDrawElementsBaseVertex pointer = glDrawElementsBaseVertex;
        if (pointer.isNull()) {
            pointer = resolve("glDrawElementsBaseVertex");
            glDrawElementsBaseVertex = pointer;
        }
        return pointer;
    }

    public static GlDrawRangeElementsBaseVertex glDrawRangeElementsBaseVertex() {
        GlDrawRangeElementsBaseVertex pointer = glDrawRangeElementsBaseVertex;
        if (pointer.isNull()) {
            pointer = resolve("glDrawRangeElementsBaseVertex");
            glDrawRangeElementsBaseVertex = pointer;
        }
        return pointer;
    }

    public static GlDrawElementsInstancedBaseVertex glDrawElementsInstancedBaseVertex() {
        GlDrawElementsInstancedBaseVertex pointer = glDrawElementsInstancedBaseVertex;
        if (pointer.isNull()) {
            pointer = resolve("glDrawElementsInstancedBaseVertex");
            glDrawElementsInstancedBaseVertex = pointer;
        }
        return pointer;
    }

    public static GlFramebufferTexture glFramebufferTexture() {
        GlFramebufferTexture pointer = glFramebufferTexture;
        if (pointer.isNull()) {
            pointer = resolve("glFramebufferTexture");
            glFramebufferTexture = pointer;
        }
        return pointer;
    }

    public static GlPrimitiveBoundingBox glPrimitiveBoundingBox() {
        GlPrimitiveBoundingBox pointer = glPrimitiveBoundingBox;
        if (pointer.isNull()) {
            pointer = resolve("glPrimitiveBoundingBox");
            glPrimitiveBoundingBox = pointer;
        }
        return pointer;
    }

    public static GlGetGraphicsResetStatus glGetGraphicsResetStatus() {
        GlGetGraphicsResetStatus pointer = glGetGraphicsResetStatus;
        if (pointer.isNull()) {
            pointer = resolve("glGetGraphicsResetStatus");
            glGetGraphicsResetStatus = pointer;
        }
        return pointer;
    }

    public static GlReadnPixels glReadnPixels() {
        GlReadnPixels pointer = glReadnPixels;
        if (pointer.isNull()) {
            pointer = resolve("glReadnPixels");
            glReadnPixels = pointer;
        }
        return pointer;
    }

    public static GlGetnUniformfv glGetnUniformfv() {
        GlGetnUniformfv pointer = glGetnUniformfv;
        if (pointer.isNull()) {
            pointer = resolve("glGetnUniformfv");
            glGetnUniformfv = pointer;
        }
        return pointer;
    }

    public static GlGetnUniformiv glGetnUniformiv() {
        GlGetnUniformiv pointer = glGetnUniformiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetnUniformiv");
            glGetnUniformiv = pointer;
        }
        return pointer;
    }

    public static GlGetnUniformuiv glGetnUniformuiv() {
        GlGetnUniformuiv pointer = glGetnUniformuiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetnUniformuiv");
            glGetnUniformuiv = pointer;
        }
        return pointer;
    }

    public static GlMinSampleShading glMinSampleShading() {
        GlMinSampleShading pointer = glMinSampleShading;
        if (pointer.isNull()) {
            pointer = resolve("glMinSampleShading");
            glMinSampleShading = pointer;
        }
        return pointer;
    }

    public static GlPatchParameteri glPatchParameteri() {
        GlPatchParameteri pointer = glPatchParameteri;
        if (pointer.isNull()) {
            pointer = resolve("glPatchParameteri");
            glPatchParameteri = pointer;
        }
        return pointer;
    }

    public static GlTexParameterIiv glTexParameterIiv() {
        GlTexParameterIiv pointer = glTexParameterIiv;
        if (pointer.isNull()) {
            pointer = resolve("glTexParameterIiv");
            glTexParameterIiv = pointer;
        }
        return pointer;
    }

    public static GlTexParameterIuiv glTexParameterIuiv() {
        GlTexParameterIuiv pointer = glTexParameterIuiv;
        if (pointer.isNull()) {
            pointer = resolve("glTexParameterIuiv");
            glTexParameterIuiv = pointer;
        }
        return pointer;
    }

    public static GlGetTexParameterIiv glGetTexParameterIiv() {
        GlGetTexParameterIiv pointer = glGetTexParameterIiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetTexParameterIiv");
            glGetTexParameterIiv = pointer;
        }
        return pointer;
    }

    public static GlGetTexParameterIuiv glGetTexParameterIuiv() {
        GlGetTexParameterIuiv pointer = glGetTexParameterIuiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetTexParameterIuiv");
            glGetTexParameterIuiv = pointer;
        }
        return pointer;
    }

    public static GlSamplerParameterIiv glSamplerParameterIiv() {
        GlSamplerParameterIiv pointer = glSamplerParameterIiv;
        if (pointer.isNull()) {
            pointer = resolve("glSamplerParameterIiv");
            glSamplerParameterIiv = pointer;
        }
        return pointer;
    }

    public static GlSamplerParameterIuiv glSamplerParameterIuiv() {
        GlSamplerParameterIuiv pointer = glSamplerParameterIuiv;
        if (pointer.isNull()) {
            pointer = resolve("glSamplerParameterIuiv");
            glSamplerParameterIuiv = pointer;
        }
        return pointer;
    }

    public static GlGetSamplerParameterIiv glGetSamplerParameterIiv() {
        GlGetSamplerParameterIiv pointer = glGetSamplerParameterIiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetSamplerParameterIiv");
            glGetSamplerParameterIiv = pointer;
        }
        return pointer;
    }

    public static GlGetSamplerParameterIuiv glGetSamplerParameterIuiv() {
        GlGetSamplerParameterIuiv pointer = glGetSamplerParameterIuiv;
        if (pointer.isNull()) {
            pointer = resolve("glGetSamplerParameterIuiv");
            glGetSamplerParameterIuiv = pointer;
        }
        return pointer;
    }

    public static GlTexBuffer glTexBuffer() {
        GlTexBuffer pointer = glTexBuffer;
        if (pointer.isNull()) {
            pointer = resolve("glTexBuffer");
            glTexBuffer = pointer;
        }
        return pointer;
    }

    public static GlTexBufferRange glTexBufferRange() {
        GlTexBufferRange pointer = glTexBufferRange;
        if (pointer.isNull()) {
            pointer = resolve("glTexBufferRange");
            glTexBufferRange = pointer;
        }
        return pointer;
    }

    public static GlTexStorage3DMultisample glTexStorage3DMultisample() {
        GlTexStorage3DMultisample pointer = glTexStorage3DMultisample;
        if (pointer.isNull()) {
            pointer = resolve("glTexStorage3DMultisample");
            glTexStorage3DMultisample = pointer;
        }
        return pointer;
    }

    public interface GlBlendBarrier extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call();
    }

    public interface GlCopyImageSubData extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int srcName, int srcTarget, int srcLevel, int srcX, int srcY, int srcZ, int dstName, int dstTarget,
                int dstLevel, int dstX, int dstY, int dstZ, int srcWidth, int srcHeight, int srcDepth);
    }

    public interface GlDebugMessageControl extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int source, int type, int severity, int count, PointerBase ids, boolean enabled);
    }

    public interface GlDebugMessageInsert extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int source, int type, int id, int severity, int length, CCharPointer buf);
    }

    public interface GlDebugMessageCallback extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(PointerBase callback, PointerBase userParam);
    }

    public interface GlGetDebugMessageLog extends CFunctionPointer {
        @InvokeCFunctionPointer
        int call(int count, int bufSize, PointerBase sources, PointerBase types, PointerBase ids,
                PointerBase severities, PointerBase lengths, PointerBase messageLog);
    }

    public interface GlPushDebugGroup extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int source, int id, int length, CCharPointer message);
    }

    public interface GlPopDebugGroup extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call();
    }

    public interface GlObjectLabel extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int identifier, int name, int length, CCharPointer label);
    }

    public interface GlGetObjectLabel extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int identifier, int name, int bufSize, PointerBase length, PointerBase label);
    }

    public interface GlObjectPtrLabel extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(PointerBase ptr, int length, CCharPointer label);
    }

    public interface GlGetObjectPtrLabel extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(PointerBase ptr, int bufSize, PointerBase length, PointerBase label);
    }

    public interface GlGetPointerv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int pname, PointerBase params);
    }

    public interface GlEnablei extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int index);
    }

    public interface GlDisablei extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int index);
    }

    public interface GlBlendEquationi extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int buf, int mode);
    }

    public interface GlBlendEquationSeparatei extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int buf, int modeRGB, int modeAlpha);
    }

    public interface GlBlendFunci extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int buf, int src, int dst);
    }

    public interface GlBlendFuncSeparatei extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int buf, int srcRGB, int dstRGB, int srcAlpha, int dstAlpha);
    }

    public interface GlColorMaski extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int index, boolean r, boolean g, boolean b, boolean a);
    }

    public interface GlIsEnabledi extends CFunctionPointer {
        @InvokeCFunctionPointer
        boolean call(int target, int index);
    }

    public interface GlDrawElementsBaseVertex extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int mode, int count, int type, PointerBase indices, int basevertex);
    }

    public interface GlDrawRangeElementsBaseVertex extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int mode, int start, int end, int count, int type, PointerBase indices, int basevertex);
    }

    public interface GlDrawElementsInstancedBaseVertex extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int mode, int count, int type, PointerBase indices, int instancecount, int basevertex);
    }

    public interface GlFramebufferTexture extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int attachment, int texture, int level);
    }

    public interface GlPrimitiveBoundingBox extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW);
    }

    public interface GlGetGraphicsResetStatus extends CFunctionPointer {
        @InvokeCFunctionPointer
        int call();
    }

    public interface GlReadnPixels extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int x, int y, int width, int height, int format, int type, int bufSize, PointerBase data);
    }

    public interface GlGetnUniformfv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int program, int location, int bufSize, PointerBase params);
    }

    public interface GlGetnUniformiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int program, int location, int bufSize, PointerBase params);
    }

    public interface GlGetnUniformuiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int program, int location, int bufSize, PointerBase params);
    }

    public interface GlMinSampleShading extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(float value);
    }

    public interface GlPatchParameteri extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int pname, int value);
    }

    public interface GlTexParameterIiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int pname, PointerBase params);
    }

    public interface GlTexParameterIuiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int pname, PointerBase params);
    }

    public interface GlGetTexParameterIiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int pname, PointerBase params);
    }

    public interface GlGetTexParameterIuiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int pname, PointerBase params);
    }

    public interface GlSamplerParameterIiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int sampler, int pname, PointerBase param);
    }

    public interface GlSamplerParameterIuiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int sampler, int pname, PointerBase param);
    }

    public interface GlGetSamplerParameterIiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int sampler, int pname, PointerBase params);
    }

    public interface GlGetSamplerParameterIuiv extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int sampler, int pname, PointerBase params);
    }

    public interface GlTexBuffer extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int internalformat, int buffer);
    }

    public interface GlTexBufferRange extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int internalformat, int buffer, SignedWord offset, SignedWord size);
    }

    public interface GlTexStorage3DMultisample extends CFunctionPointer {
        @InvokeCFunctionPointer
        void call(int target, int samples, int internalformat, int width, int height, int depth,
                boolean fixedsamplelocations);
    }
}
