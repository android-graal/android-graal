// Generated from android.jar and the NDK's GLES headers.
package org.androidgraal.gles;

import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;

@CLibrary("GLESv3")
public final class GLESv3 {
    private GLESv3() {
    }

    @CFunction("glReadBuffer")
    public static native void glReadBuffer(int src);

    @CFunction("glDrawRangeElements")
    public static native void glDrawRangeElements(int mode, int start, int end, int count, int type,
            PointerBase indices);

    @CFunction("glTexImage3D")
    public static native void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth,
            int border, int format, int type, PointerBase pixels);

    @CFunction("glTexSubImage3D")
    public static native void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width,
            int height, int depth, int format, int type, PointerBase pixels);

    @CFunction("glCopyTexSubImage3D")
    public static native void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x,
            int y, int width, int height);

    @CFunction("glCompressedTexImage3D")
    public static native void glCompressedTexImage3D(int target, int level, int internalformat, int width, int height,
            int depth, int border, int imageSize, PointerBase data);

    @CFunction("glCompressedTexSubImage3D")
    public static native void glCompressedTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset,
            int width, int height, int depth, int format, int imageSize, PointerBase data);

    @CFunction("glGenQueries")
    public static native void glGenQueries(int n, PointerBase ids);

    @CFunction("glDeleteQueries")
    public static native void glDeleteQueries(int n, PointerBase ids);

    @CFunction("glIsQuery")
    public static native boolean glIsQuery(int id);

    @CFunction("glBeginQuery")
    public static native void glBeginQuery(int target, int id);

    @CFunction("glEndQuery")
    public static native void glEndQuery(int target);

    @CFunction("glGetQueryiv")
    public static native void glGetQueryiv(int target, int pname, PointerBase params);

    @CFunction("glGetQueryObjectuiv")
    public static native void glGetQueryObjectuiv(int id, int pname, PointerBase params);

    @CFunction("glUnmapBuffer")
    public static native boolean glUnmapBuffer(int target);

    @CFunction("glGetBufferPointerv")
    public static native void glGetBufferPointerv(int target, int pname, PointerBase params);

    @CFunction("glDrawBuffers")
    public static native void glDrawBuffers(int n, PointerBase bufs);

    @CFunction("glUniformMatrix2x3fv")
    public static native void glUniformMatrix2x3fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix3x2fv")
    public static native void glUniformMatrix3x2fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix2x4fv")
    public static native void glUniformMatrix2x4fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix4x2fv")
    public static native void glUniformMatrix4x2fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix3x4fv")
    public static native void glUniformMatrix3x4fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix4x3fv")
    public static native void glUniformMatrix4x3fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glBlitFramebuffer")
    public static native void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0,
            int dstX1, int dstY1, int mask, int filter);

    @CFunction("glRenderbufferStorageMultisample")
    public static native void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width,
            int height);

    @CFunction("glFramebufferTextureLayer")
    public static native void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer);

    @CFunction("glMapBufferRange")
    public static native PointerBase glMapBufferRange(int target, SignedWord offset, SignedWord length, int access);

    @CFunction("glFlushMappedBufferRange")
    public static native void glFlushMappedBufferRange(int target, SignedWord offset, SignedWord length);

    @CFunction("glBindVertexArray")
    public static native void glBindVertexArray(int array);

    @CFunction("glDeleteVertexArrays")
    public static native void glDeleteVertexArrays(int n, PointerBase arrays);

    @CFunction("glGenVertexArrays")
    public static native void glGenVertexArrays(int n, PointerBase arrays);

    @CFunction("glIsVertexArray")
    public static native boolean glIsVertexArray(int array);

    @CFunction("glGetIntegeri_v")
    public static native void glGetIntegeri_v(int target, int index, PointerBase data);

    @CFunction("glBeginTransformFeedback")
    public static native void glBeginTransformFeedback(int primitiveMode);

    @CFunction("glEndTransformFeedback")
    public static native void glEndTransformFeedback();

    @CFunction("glBindBufferRange")
    public static native void glBindBufferRange(int target, int index, int buffer, SignedWord offset, SignedWord size);

    @CFunction("glBindBufferBase")
    public static native void glBindBufferBase(int target, int index, int buffer);

    @CFunction("glTransformFeedbackVaryings")
    public static native void glTransformFeedbackVaryings(int program, int count, CCharPointerPointer varyings,
            int bufferMode);

    @CFunction("glGetTransformFeedbackVarying")
    public static native void glGetTransformFeedbackVarying(int program, int index, int bufSize, PointerBase length,
            PointerBase size, PointerBase type, PointerBase name);

    @CFunction("glVertexAttribIPointer")
    public static native void glVertexAttribIPointer(int index, int size, int type, int stride, PointerBase pointer);

    @CFunction("glGetVertexAttribIiv")
    public static native void glGetVertexAttribIiv(int index, int pname, PointerBase params);

    @CFunction("glGetVertexAttribIuiv")
    public static native void glGetVertexAttribIuiv(int index, int pname, PointerBase params);

    @CFunction("glVertexAttribI4i")
    public static native void glVertexAttribI4i(int index, int x, int y, int z, int w);

    @CFunction("glVertexAttribI4ui")
    public static native void glVertexAttribI4ui(int index, int x, int y, int z, int w);

    @CFunction("glVertexAttribI4iv")
    public static native void glVertexAttribI4iv(int index, PointerBase v);

    @CFunction("glVertexAttribI4uiv")
    public static native void glVertexAttribI4uiv(int index, PointerBase v);

    @CFunction("glGetUniformuiv")
    public static native void glGetUniformuiv(int program, int location, PointerBase params);

    @CFunction("glGetFragDataLocation")
    public static native int glGetFragDataLocation(int program, CCharPointer name);

    @CFunction("glUniform1ui")
    public static native void glUniform1ui(int location, int v0);

    @CFunction("glUniform2ui")
    public static native void glUniform2ui(int location, int v0, int v1);

    @CFunction("glUniform3ui")
    public static native void glUniform3ui(int location, int v0, int v1, int v2);

    @CFunction("glUniform4ui")
    public static native void glUniform4ui(int location, int v0, int v1, int v2, int v3);

    @CFunction("glUniform1uiv")
    public static native void glUniform1uiv(int location, int count, PointerBase value);

    @CFunction("glUniform2uiv")
    public static native void glUniform2uiv(int location, int count, PointerBase value);

    @CFunction("glUniform3uiv")
    public static native void glUniform3uiv(int location, int count, PointerBase value);

    @CFunction("glUniform4uiv")
    public static native void glUniform4uiv(int location, int count, PointerBase value);

    @CFunction("glClearBufferiv")
    public static native void glClearBufferiv(int buffer, int drawbuffer, PointerBase value);

    @CFunction("glClearBufferuiv")
    public static native void glClearBufferuiv(int buffer, int drawbuffer, PointerBase value);

    @CFunction("glClearBufferfv")
    public static native void glClearBufferfv(int buffer, int drawbuffer, PointerBase value);

    @CFunction("glClearBufferfi")
    public static native void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil);

    @CFunction("glGetStringi")
    public static native CCharPointer glGetStringi(int name, int index);

    @CFunction("glCopyBufferSubData")
    public static native void glCopyBufferSubData(int readTarget, int writeTarget, SignedWord readOffset,
            SignedWord writeOffset, SignedWord size);

    @CFunction("glGetUniformIndices")
    public static native void glGetUniformIndices(int program, int uniformCount, CCharPointerPointer uniformNames,
            PointerBase uniformIndices);

    @CFunction("glGetActiveUniformsiv")
    public static native void glGetActiveUniformsiv(int program, int uniformCount, PointerBase uniformIndices,
            int pname, PointerBase params);

    @CFunction("glGetUniformBlockIndex")
    public static native int glGetUniformBlockIndex(int program, CCharPointer uniformBlockName);

    @CFunction("glGetActiveUniformBlockiv")
    public static native void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname,
            PointerBase params);

    @CFunction("glGetActiveUniformBlockName")
    public static native void glGetActiveUniformBlockName(int program, int uniformBlockIndex, int bufSize,
            PointerBase length, PointerBase uniformBlockName);

    @CFunction("glUniformBlockBinding")
    public static native void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding);

    @CFunction("glDrawArraysInstanced")
    public static native void glDrawArraysInstanced(int mode, int first, int count, int instancecount);

    @CFunction("glDrawElementsInstanced")
    public static native void glDrawElementsInstanced(int mode, int count, int type, PointerBase indices,
            int instancecount);

    @CFunction("glFenceSync")
    public static native PointerBase glFenceSync(int condition, int flags);

    @CFunction("glIsSync")
    public static native boolean glIsSync(PointerBase sync);

    @CFunction("glDeleteSync")
    public static native void glDeleteSync(PointerBase sync);

    @CFunction("glClientWaitSync")
    public static native int glClientWaitSync(PointerBase sync, int flags, long timeout);

    @CFunction("glWaitSync")
    public static native void glWaitSync(PointerBase sync, int flags, long timeout);

    @CFunction("glGetInteger64v")
    public static native void glGetInteger64v(int pname, PointerBase data);

    @CFunction("glGetSynciv")
    public static native void glGetSynciv(PointerBase sync, int pname, int bufSize, PointerBase length,
            PointerBase values);

    @CFunction("glGetInteger64i_v")
    public static native void glGetInteger64i_v(int target, int index, PointerBase data);

    @CFunction("glGetBufferParameteri64v")
    public static native void glGetBufferParameteri64v(int target, int pname, PointerBase params);

    @CFunction("glGenSamplers")
    public static native void glGenSamplers(int count, PointerBase samplers);

    @CFunction("glDeleteSamplers")
    public static native void glDeleteSamplers(int count, PointerBase samplers);

    @CFunction("glIsSampler")
    public static native boolean glIsSampler(int sampler);

    @CFunction("glBindSampler")
    public static native void glBindSampler(int unit, int sampler);

    @CFunction("glSamplerParameteri")
    public static native void glSamplerParameteri(int sampler, int pname, int param);

    @CFunction("glSamplerParameteriv")
    public static native void glSamplerParameteriv(int sampler, int pname, PointerBase param);

    @CFunction("glSamplerParameterf")
    public static native void glSamplerParameterf(int sampler, int pname, float param);

    @CFunction("glSamplerParameterfv")
    public static native void glSamplerParameterfv(int sampler, int pname, PointerBase param);

    @CFunction("glGetSamplerParameteriv")
    public static native void glGetSamplerParameteriv(int sampler, int pname, PointerBase params);

    @CFunction("glGetSamplerParameterfv")
    public static native void glGetSamplerParameterfv(int sampler, int pname, PointerBase params);

    @CFunction("glVertexAttribDivisor")
    public static native void glVertexAttribDivisor(int index, int divisor);

    @CFunction("glBindTransformFeedback")
    public static native void glBindTransformFeedback(int target, int id);

    @CFunction("glDeleteTransformFeedbacks")
    public static native void glDeleteTransformFeedbacks(int n, PointerBase ids);

    @CFunction("glGenTransformFeedbacks")
    public static native void glGenTransformFeedbacks(int n, PointerBase ids);

    @CFunction("glIsTransformFeedback")
    public static native boolean glIsTransformFeedback(int id);

    @CFunction("glPauseTransformFeedback")
    public static native void glPauseTransformFeedback();

    @CFunction("glResumeTransformFeedback")
    public static native void glResumeTransformFeedback();

    @CFunction("glGetProgramBinary")
    public static native void glGetProgramBinary(int program, int bufSize, PointerBase length, PointerBase binaryFormat,
            PointerBase binary);

    @CFunction("glProgramBinary")
    public static native void glProgramBinary(int program, int binaryFormat, PointerBase binary, int length);

    @CFunction("glProgramParameteri")
    public static native void glProgramParameteri(int program, int pname, int value);

    @CFunction("glInvalidateFramebuffer")
    public static native void glInvalidateFramebuffer(int target, int numAttachments, PointerBase attachments);

    @CFunction("glInvalidateSubFramebuffer")
    public static native void glInvalidateSubFramebuffer(int target, int numAttachments, PointerBase attachments, int x,
            int y, int width, int height);

    @CFunction("glTexStorage2D")
    public static native void glTexStorage2D(int target, int levels, int internalformat, int width, int height);

    @CFunction("glTexStorage3D")
    public static native void glTexStorage3D(int target, int levels, int internalformat, int width, int height,
            int depth);

    @CFunction("glGetInternalformativ")
    public static native void glGetInternalformativ(int target, int internalformat, int pname, int bufSize,
            PointerBase params);

    @CFunction("glDispatchCompute")
    public static native void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z);

    @CFunction("glDispatchComputeIndirect")
    public static native void glDispatchComputeIndirect(SignedWord indirect);

    @CFunction("glDrawArraysIndirect")
    public static native void glDrawArraysIndirect(int mode, PointerBase indirect);

    @CFunction("glDrawElementsIndirect")
    public static native void glDrawElementsIndirect(int mode, int type, PointerBase indirect);

    @CFunction("glFramebufferParameteri")
    public static native void glFramebufferParameteri(int target, int pname, int param);

    @CFunction("glGetFramebufferParameteriv")
    public static native void glGetFramebufferParameteriv(int target, int pname, PointerBase params);

    @CFunction("glGetProgramInterfaceiv")
    public static native void glGetProgramInterfaceiv(int program, int programInterface, int pname, PointerBase params);

    @CFunction("glGetProgramResourceIndex")
    public static native int glGetProgramResourceIndex(int program, int programInterface, CCharPointer name);

    @CFunction("glGetProgramResourceName")
    public static native void glGetProgramResourceName(int program, int programInterface, int index, int bufSize,
            PointerBase length, PointerBase name);

    @CFunction("glGetProgramResourceiv")
    public static native void glGetProgramResourceiv(int program, int programInterface, int index, int propCount,
            PointerBase props, int bufSize, PointerBase length, PointerBase params);

    @CFunction("glGetProgramResourceLocation")
    public static native int glGetProgramResourceLocation(int program, int programInterface, CCharPointer name);

    @CFunction("glUseProgramStages")
    public static native void glUseProgramStages(int pipeline, int stages, int program);

    @CFunction("glActiveShaderProgram")
    public static native void glActiveShaderProgram(int pipeline, int program);

    @CFunction("glCreateShaderProgramv")
    public static native int glCreateShaderProgramv(int type, int count, CCharPointerPointer strings);

    @CFunction("glBindProgramPipeline")
    public static native void glBindProgramPipeline(int pipeline);

    @CFunction("glDeleteProgramPipelines")
    public static native void glDeleteProgramPipelines(int n, PointerBase pipelines);

    @CFunction("glGenProgramPipelines")
    public static native void glGenProgramPipelines(int n, PointerBase pipelines);

    @CFunction("glIsProgramPipeline")
    public static native boolean glIsProgramPipeline(int pipeline);

    @CFunction("glGetProgramPipelineiv")
    public static native void glGetProgramPipelineiv(int pipeline, int pname, PointerBase params);

    @CFunction("glProgramUniform1i")
    public static native void glProgramUniform1i(int program, int location, int v0);

    @CFunction("glProgramUniform2i")
    public static native void glProgramUniform2i(int program, int location, int v0, int v1);

    @CFunction("glProgramUniform3i")
    public static native void glProgramUniform3i(int program, int location, int v0, int v1, int v2);

    @CFunction("glProgramUniform4i")
    public static native void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3);

    @CFunction("glProgramUniform1ui")
    public static native void glProgramUniform1ui(int program, int location, int v0);

    @CFunction("glProgramUniform2ui")
    public static native void glProgramUniform2ui(int program, int location, int v0, int v1);

    @CFunction("glProgramUniform3ui")
    public static native void glProgramUniform3ui(int program, int location, int v0, int v1, int v2);

    @CFunction("glProgramUniform4ui")
    public static native void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3);

    @CFunction("glProgramUniform1f")
    public static native void glProgramUniform1f(int program, int location, float v0);

    @CFunction("glProgramUniform2f")
    public static native void glProgramUniform2f(int program, int location, float v0, float v1);

    @CFunction("glProgramUniform3f")
    public static native void glProgramUniform3f(int program, int location, float v0, float v1, float v2);

    @CFunction("glProgramUniform4f")
    public static native void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3);

    @CFunction("glProgramUniform1iv")
    public static native void glProgramUniform1iv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform2iv")
    public static native void glProgramUniform2iv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform3iv")
    public static native void glProgramUniform3iv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform4iv")
    public static native void glProgramUniform4iv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform1uiv")
    public static native void glProgramUniform1uiv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform2uiv")
    public static native void glProgramUniform2uiv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform3uiv")
    public static native void glProgramUniform3uiv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform4uiv")
    public static native void glProgramUniform4uiv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform1fv")
    public static native void glProgramUniform1fv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform2fv")
    public static native void glProgramUniform2fv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform3fv")
    public static native void glProgramUniform3fv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniform4fv")
    public static native void glProgramUniform4fv(int program, int location, int count, PointerBase value);

    @CFunction("glProgramUniformMatrix2fv")
    public static native void glProgramUniformMatrix2fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix3fv")
    public static native void glProgramUniformMatrix3fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix4fv")
    public static native void glProgramUniformMatrix4fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix2x3fv")
    public static native void glProgramUniformMatrix2x3fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix3x2fv")
    public static native void glProgramUniformMatrix3x2fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix2x4fv")
    public static native void glProgramUniformMatrix2x4fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix4x2fv")
    public static native void glProgramUniformMatrix4x2fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix3x4fv")
    public static native void glProgramUniformMatrix3x4fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glProgramUniformMatrix4x3fv")
    public static native void glProgramUniformMatrix4x3fv(int program, int location, int count, boolean transpose,
            PointerBase value);

    @CFunction("glValidateProgramPipeline")
    public static native void glValidateProgramPipeline(int pipeline);

    @CFunction("glGetProgramPipelineInfoLog")
    public static native void glGetProgramPipelineInfoLog(int pipeline, int bufSize, PointerBase length,
            PointerBase infoLog);

    @CFunction("glBindImageTexture")
    public static native void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer,
            int access, int format);

    @CFunction("glGetBooleani_v")
    public static native void glGetBooleani_v(int target, int index, PointerBase data);

    @CFunction("glMemoryBarrier")
    public static native void glMemoryBarrier(int barriers);

    @CFunction("glMemoryBarrierByRegion")
    public static native void glMemoryBarrierByRegion(int barriers);

    @CFunction("glTexStorage2DMultisample")
    public static native void glTexStorage2DMultisample(int target, int samples, int internalformat, int width,
            int height, boolean fixedsamplelocations);

    @CFunction("glGetMultisamplefv")
    public static native void glGetMultisamplefv(int pname, int index, PointerBase val);

    @CFunction("glSampleMaski")
    public static native void glSampleMaski(int maskNumber, int mask);

    @CFunction("glGetTexLevelParameteriv")
    public static native void glGetTexLevelParameteriv(int target, int level, int pname, PointerBase params);

    @CFunction("glGetTexLevelParameterfv")
    public static native void glGetTexLevelParameterfv(int target, int level, int pname, PointerBase params);

    @CFunction("glBindVertexBuffer")
    public static native void glBindVertexBuffer(int bindingindex, int buffer, SignedWord offset, int stride);

    @CFunction("glVertexAttribFormat")
    public static native void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized,
            int relativeoffset);

    @CFunction("glVertexAttribIFormat")
    public static native void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset);

    @CFunction("glVertexAttribBinding")
    public static native void glVertexAttribBinding(int attribindex, int bindingindex);

    @CFunction("glVertexBindingDivisor")
    public static native void glVertexBindingDivisor(int bindingindex, int divisor);
}
