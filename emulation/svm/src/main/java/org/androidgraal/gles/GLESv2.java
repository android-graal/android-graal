// Generated from android.jar and the NDK's GLES headers.
package org.androidgraal.gles;

import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CCharPointerPointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.SignedWord;

@CLibrary("GLESv2")
public final class GLESv2 {
    private GLESv2() {
    }

    @CFunction("glActiveTexture")
    public static native void glActiveTexture(int texture);

    @CFunction("glAttachShader")
    public static native void glAttachShader(int program, int shader);

    @CFunction("glBindAttribLocation")
    public static native void glBindAttribLocation(int program, int index, CCharPointer name);

    @CFunction("glBindBuffer")
    public static native void glBindBuffer(int target, int buffer);

    @CFunction("glBindFramebuffer")
    public static native void glBindFramebuffer(int target, int framebuffer);

    @CFunction("glBindRenderbuffer")
    public static native void glBindRenderbuffer(int target, int renderbuffer);

    @CFunction("glBindTexture")
    public static native void glBindTexture(int target, int texture);

    @CFunction("glBlendColor")
    public static native void glBlendColor(float red, float green, float blue, float alpha);

    @CFunction("glBlendEquation")
    public static native void glBlendEquation(int mode);

    @CFunction("glBlendEquationSeparate")
    public static native void glBlendEquationSeparate(int modeRGB, int modeAlpha);

    @CFunction("glBlendFunc")
    public static native void glBlendFunc(int sfactor, int dfactor);

    @CFunction("glBlendFuncSeparate")
    public static native void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);

    @CFunction("glBufferData")
    public static native void glBufferData(int target, SignedWord size, PointerBase data, int usage);

    @CFunction("glBufferSubData")
    public static native void glBufferSubData(int target, SignedWord offset, SignedWord size, PointerBase data);

    @CFunction("glCheckFramebufferStatus")
    public static native int glCheckFramebufferStatus(int target);

    @CFunction("glClear")
    public static native void glClear(int mask);

    @CFunction("glClearColor")
    public static native void glClearColor(float red, float green, float blue, float alpha);

    @CFunction("glClearDepthf")
    public static native void glClearDepthf(float d);

    @CFunction("glClearStencil")
    public static native void glClearStencil(int s);

    @CFunction("glColorMask")
    public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);

    @CFunction("glCompileShader")
    public static native void glCompileShader(int shader);

    @CFunction("glCompressedTexImage2D")
    public static native void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height,
            int border, int imageSize, PointerBase data);

    @CFunction("glCompressedTexSubImage2D")
    public static native void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width,
            int height, int format, int imageSize, PointerBase data);

    @CFunction("glCopyTexImage2D")
    public static native void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width,
            int height, int border);

    @CFunction("glCopyTexSubImage2D")
    public static native void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y,
            int width, int height);

    @CFunction("glCreateProgram")
    public static native int glCreateProgram();

    @CFunction("glCreateShader")
    public static native int glCreateShader(int type);

    @CFunction("glCullFace")
    public static native void glCullFace(int mode);

    @CFunction("glDeleteBuffers")
    public static native void glDeleteBuffers(int n, PointerBase buffers);

    @CFunction("glDeleteFramebuffers")
    public static native void glDeleteFramebuffers(int n, PointerBase framebuffers);

    @CFunction("glDeleteProgram")
    public static native void glDeleteProgram(int program);

    @CFunction("glDeleteRenderbuffers")
    public static native void glDeleteRenderbuffers(int n, PointerBase renderbuffers);

    @CFunction("glDeleteShader")
    public static native void glDeleteShader(int shader);

    @CFunction("glDeleteTextures")
    public static native void glDeleteTextures(int n, PointerBase textures);

    @CFunction("glDepthFunc")
    public static native void glDepthFunc(int func);

    @CFunction("glDepthMask")
    public static native void glDepthMask(boolean flag);

    @CFunction("glDepthRangef")
    public static native void glDepthRangef(float n, float f);

    @CFunction("glDetachShader")
    public static native void glDetachShader(int program, int shader);

    @CFunction("glDisable")
    public static native void glDisable(int cap);

    @CFunction("glDisableVertexAttribArray")
    public static native void glDisableVertexAttribArray(int index);

    @CFunction("glDrawArrays")
    public static native void glDrawArrays(int mode, int first, int count);

    @CFunction("glDrawElements")
    public static native void glDrawElements(int mode, int count, int type, PointerBase indices);

    @CFunction("glEnable")
    public static native void glEnable(int cap);

    @CFunction("glEnableVertexAttribArray")
    public static native void glEnableVertexAttribArray(int index);

    @CFunction("glFinish")
    public static native void glFinish();

    @CFunction("glFlush")
    public static native void glFlush();

    @CFunction("glFramebufferRenderbuffer")
    public static native void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget,
            int renderbuffer);

    @CFunction("glFramebufferTexture2D")
    public static native void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level);

    @CFunction("glFrontFace")
    public static native void glFrontFace(int mode);

    @CFunction("glGenBuffers")
    public static native void glGenBuffers(int n, PointerBase buffers);

    @CFunction("glGenerateMipmap")
    public static native void glGenerateMipmap(int target);

    @CFunction("glGenFramebuffers")
    public static native void glGenFramebuffers(int n, PointerBase framebuffers);

    @CFunction("glGenRenderbuffers")
    public static native void glGenRenderbuffers(int n, PointerBase renderbuffers);

    @CFunction("glGenTextures")
    public static native void glGenTextures(int n, PointerBase textures);

    @CFunction("glGetActiveAttrib")
    public static native void glGetActiveAttrib(int program, int index, int bufSize, PointerBase length,
            PointerBase size, PointerBase type, PointerBase name);

    @CFunction("glGetActiveUniform")
    public static native void glGetActiveUniform(int program, int index, int bufSize, PointerBase length,
            PointerBase size, PointerBase type, PointerBase name);

    @CFunction("glGetAttachedShaders")
    public static native void glGetAttachedShaders(int program, int maxCount, PointerBase count, PointerBase shaders);

    @CFunction("glGetAttribLocation")
    public static native int glGetAttribLocation(int program, CCharPointer name);

    @CFunction("glGetBooleanv")
    public static native void glGetBooleanv(int pname, PointerBase data);

    @CFunction("glGetBufferParameteriv")
    public static native void glGetBufferParameteriv(int target, int pname, PointerBase params);

    @CFunction("glGetError")
    public static native int glGetError();

    @CFunction("glGetFloatv")
    public static native void glGetFloatv(int pname, PointerBase data);

    @CFunction("glGetFramebufferAttachmentParameteriv")
    public static native void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname,
            PointerBase params);

    @CFunction("glGetIntegerv")
    public static native void glGetIntegerv(int pname, PointerBase data);

    @CFunction("glGetProgramiv")
    public static native void glGetProgramiv(int program, int pname, PointerBase params);

    @CFunction("glGetProgramInfoLog")
    public static native void glGetProgramInfoLog(int program, int bufSize, PointerBase length, PointerBase infoLog);

    @CFunction("glGetRenderbufferParameteriv")
    public static native void glGetRenderbufferParameteriv(int target, int pname, PointerBase params);

    @CFunction("glGetShaderiv")
    public static native void glGetShaderiv(int shader, int pname, PointerBase params);

    @CFunction("glGetShaderInfoLog")
    public static native void glGetShaderInfoLog(int shader, int bufSize, PointerBase length, PointerBase infoLog);

    @CFunction("glGetShaderPrecisionFormat")
    public static native void glGetShaderPrecisionFormat(int shadertype, int precisiontype, PointerBase range,
            PointerBase precision);

    @CFunction("glGetShaderSource")
    public static native void glGetShaderSource(int shader, int bufSize, PointerBase length, PointerBase source);

    @CFunction("glGetString")
    public static native CCharPointer glGetString(int name);

    @CFunction("glGetTexParameterfv")
    public static native void glGetTexParameterfv(int target, int pname, PointerBase params);

    @CFunction("glGetTexParameteriv")
    public static native void glGetTexParameteriv(int target, int pname, PointerBase params);

    @CFunction("glGetUniformfv")
    public static native void glGetUniformfv(int program, int location, PointerBase params);

    @CFunction("glGetUniformiv")
    public static native void glGetUniformiv(int program, int location, PointerBase params);

    @CFunction("glGetUniformLocation")
    public static native int glGetUniformLocation(int program, CCharPointer name);

    @CFunction("glGetVertexAttribfv")
    public static native void glGetVertexAttribfv(int index, int pname, PointerBase params);

    @CFunction("glGetVertexAttribiv")
    public static native void glGetVertexAttribiv(int index, int pname, PointerBase params);

    @CFunction("glGetVertexAttribPointerv")
    public static native void glGetVertexAttribPointerv(int index, int pname, PointerBase pointer);

    @CFunction("glHint")
    public static native void glHint(int target, int mode);

    @CFunction("glIsBuffer")
    public static native boolean glIsBuffer(int buffer);

    @CFunction("glIsEnabled")
    public static native boolean glIsEnabled(int cap);

    @CFunction("glIsFramebuffer")
    public static native boolean glIsFramebuffer(int framebuffer);

    @CFunction("glIsProgram")
    public static native boolean glIsProgram(int program);

    @CFunction("glIsRenderbuffer")
    public static native boolean glIsRenderbuffer(int renderbuffer);

    @CFunction("glIsShader")
    public static native boolean glIsShader(int shader);

    @CFunction("glIsTexture")
    public static native boolean glIsTexture(int texture);

    @CFunction("glLineWidth")
    public static native void glLineWidth(float width);

    @CFunction("glLinkProgram")
    public static native void glLinkProgram(int program);

    @CFunction("glPixelStorei")
    public static native void glPixelStorei(int pname, int param);

    @CFunction("glPolygonOffset")
    public static native void glPolygonOffset(float factor, float units);

    @CFunction("glReadPixels")
    public static native void glReadPixels(int x, int y, int width, int height, int format, int type,
            PointerBase pixels);

    @CFunction("glReleaseShaderCompiler")
    public static native void glReleaseShaderCompiler();

    @CFunction("glRenderbufferStorage")
    public static native void glRenderbufferStorage(int target, int internalformat, int width, int height);

    @CFunction("glSampleCoverage")
    public static native void glSampleCoverage(float value, boolean invert);

    @CFunction("glScissor")
    public static native void glScissor(int x, int y, int width, int height);

    @CFunction("glShaderBinary")
    public static native void glShaderBinary(int count, PointerBase shaders, int binaryformat, PointerBase binary,
            int length);

    @CFunction("glShaderSource")
    public static native void glShaderSource(int shader, int count, CCharPointerPointer string, PointerBase length);

    @CFunction("glStencilFunc")
    public static native void glStencilFunc(int func, int ref, int mask);

    @CFunction("glStencilFuncSeparate")
    public static native void glStencilFuncSeparate(int face, int func, int ref, int mask);

    @CFunction("glStencilMask")
    public static native void glStencilMask(int mask);

    @CFunction("glStencilMaskSeparate")
    public static native void glStencilMaskSeparate(int face, int mask);

    @CFunction("glStencilOp")
    public static native void glStencilOp(int fail, int zfail, int zpass);

    @CFunction("glStencilOpSeparate")
    public static native void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass);

    @CFunction("glTexImage2D")
    public static native void glTexImage2D(int target, int level, int internalformat, int width, int height, int border,
            int format, int type, PointerBase pixels);

    @CFunction("glTexParameterf")
    public static native void glTexParameterf(int target, int pname, float param);

    @CFunction("glTexParameterfv")
    public static native void glTexParameterfv(int target, int pname, PointerBase params);

    @CFunction("glTexParameteri")
    public static native void glTexParameteri(int target, int pname, int param);

    @CFunction("glTexParameteriv")
    public static native void glTexParameteriv(int target, int pname, PointerBase params);

    @CFunction("glTexSubImage2D")
    public static native void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
            int format, int type, PointerBase pixels);

    @CFunction("glUniform1f")
    public static native void glUniform1f(int location, float v0);

    @CFunction("glUniform1fv")
    public static native void glUniform1fv(int location, int count, PointerBase value);

    @CFunction("glUniform1i")
    public static native void glUniform1i(int location, int v0);

    @CFunction("glUniform1iv")
    public static native void glUniform1iv(int location, int count, PointerBase value);

    @CFunction("glUniform2f")
    public static native void glUniform2f(int location, float v0, float v1);

    @CFunction("glUniform2fv")
    public static native void glUniform2fv(int location, int count, PointerBase value);

    @CFunction("glUniform2i")
    public static native void glUniform2i(int location, int v0, int v1);

    @CFunction("glUniform2iv")
    public static native void glUniform2iv(int location, int count, PointerBase value);

    @CFunction("glUniform3f")
    public static native void glUniform3f(int location, float v0, float v1, float v2);

    @CFunction("glUniform3fv")
    public static native void glUniform3fv(int location, int count, PointerBase value);

    @CFunction("glUniform3i")
    public static native void glUniform3i(int location, int v0, int v1, int v2);

    @CFunction("glUniform3iv")
    public static native void glUniform3iv(int location, int count, PointerBase value);

    @CFunction("glUniform4f")
    public static native void glUniform4f(int location, float v0, float v1, float v2, float v3);

    @CFunction("glUniform4fv")
    public static native void glUniform4fv(int location, int count, PointerBase value);

    @CFunction("glUniform4i")
    public static native void glUniform4i(int location, int v0, int v1, int v2, int v3);

    @CFunction("glUniform4iv")
    public static native void glUniform4iv(int location, int count, PointerBase value);

    @CFunction("glUniformMatrix2fv")
    public static native void glUniformMatrix2fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix3fv")
    public static native void glUniformMatrix3fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUniformMatrix4fv")
    public static native void glUniformMatrix4fv(int location, int count, boolean transpose, PointerBase value);

    @CFunction("glUseProgram")
    public static native void glUseProgram(int program);

    @CFunction("glValidateProgram")
    public static native void glValidateProgram(int program);

    @CFunction("glVertexAttrib1f")
    public static native void glVertexAttrib1f(int index, float x);

    @CFunction("glVertexAttrib1fv")
    public static native void glVertexAttrib1fv(int index, PointerBase v);

    @CFunction("glVertexAttrib2f")
    public static native void glVertexAttrib2f(int index, float x, float y);

    @CFunction("glVertexAttrib2fv")
    public static native void glVertexAttrib2fv(int index, PointerBase v);

    @CFunction("glVertexAttrib3f")
    public static native void glVertexAttrib3f(int index, float x, float y, float z);

    @CFunction("glVertexAttrib3fv")
    public static native void glVertexAttrib3fv(int index, PointerBase v);

    @CFunction("glVertexAttrib4f")
    public static native void glVertexAttrib4f(int index, float x, float y, float z, float w);

    @CFunction("glVertexAttrib4fv")
    public static native void glVertexAttrib4fv(int index, PointerBase v);

    @CFunction("glVertexAttribPointer")
    public static native void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride,
            PointerBase pointer);

    @CFunction("glViewport")
    public static native void glViewport(int x, int y, int width, int height);
}
