package org.androidgraal.gles.generator

import org.androidgraal.gles.generator.Count.Literal
import org.androidgraal.gles.generator.Count.Param
import org.androidgraal.gles.generator.Count.Query
import org.androidgraal.gles.generator.CountQuery.CLEAR_BUFFER
import org.androidgraal.gles.generator.CountQuery.GET
import org.androidgraal.gles.generator.CountQuery.INDEXED
import org.androidgraal.gles.generator.CountQuery.INDICES
import org.androidgraal.gles.generator.CountQuery.PROGRAM
import org.androidgraal.gles.generator.CountQuery.TEX_PARAMETER
import org.androidgraal.gles.generator.CountQuery.UNIFORM_BLOCK
import org.androidgraal.gles.generator.CountQuery.VERTEX_ATTRIB
import org.androidgraal.gles.generator.Passing.Direct
import org.androidgraal.gles.generator.Passing.Pinned

internal data class ParamKey(val function: String, val param: String)

internal sealed interface Count {
    class Literal(val times: Int) : Count

    class Param(val name: String, val times: Int = 1) : Count

    class Query(val query: CountQuery, vararg val params: String) : Count
}

internal enum class CountQuery(val method: String) {
    GET("get"),
    VERTEX_ATTRIB("vertexAttrib"),
    CLEAR_BUFFER("clearBuffer"),
    UNIFORM_BLOCK("uniformBlock"),
    INDICES("indices"),
    INDEXED("indexed"),
    TEX_PARAMETER("texParameter"),
    PROGRAM("program"),
}

internal sealed interface Passing {
    class Pinned(val count: Count? = null, val isNullable: Boolean = false) : Passing

    object Direct : Passing
}

internal val BY_HAND: Map<String, List<String>> = mapOf(
    "glGetActiveAttrib(IILjava/nio/IntBuffer;Ljava/nio/IntBuffer;)Ljava/lang/String;" to
        listOf("program", "index", "size", "type"),
    "glGetActiveAttrib(II[II[II)Ljava/lang/String;" to
        listOf("program", "index", "size", "sizeOffset", "type", "typeOffset"),
    "glGetActiveUniform(IILjava/nio/IntBuffer;Ljava/nio/IntBuffer;)Ljava/lang/String;" to
        listOf("program", "index", "size", "type"),
    "glGetActiveUniform(II[II[II)Ljava/lang/String;" to
        listOf("program", "index", "size", "sizeOffset", "type", "typeOffset"),
    "glGetProgramInfoLog(I)Ljava/lang/String;" to listOf("program"),
    "glGetShaderInfoLog(I)Ljava/lang/String;" to listOf("shader"),
    "glGetShaderSource(I)Ljava/lang/String;" to listOf("shader"),
    "glShaderSource(ILjava/lang/String;)V" to listOf("shader", "string"),
    "glGetActiveUniformBlockName(II)Ljava/lang/String;" to listOf("program", "uniformBlockIndex"),
    "glGetActiveUniformBlockName(IILjava/nio/Buffer;Ljava/nio/Buffer;)V" to
        listOf("program", "uniformBlockIndex", "length", "uniformBlockName"),
    "glGetBufferPointerv(II)Ljava/nio/Buffer;" to listOf("target", "pname"),
    "glMapBufferRange(IIII)Ljava/nio/Buffer;" to listOf("target", "offset", "length", "access"),
    "glGetTransformFeedbackVarying(IIILjava/nio/IntBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;B)V" to
        listOf("program", "index", "bufSize", "length", "size", "type", "name"),
    "glGetTransformFeedbackVarying(II[II[II)Ljava/lang/String;" to
        listOf("program", "index", "size", "sizeOffset", "type", "typeOffset"),
    "glGetTransformFeedbackVarying(IILjava/nio/IntBuffer;Ljava/nio/IntBuffer;)Ljava/lang/String;" to
        listOf("program", "index", "size", "type"),
    "glGetUniformIndices(I[Ljava/lang/String;[II)V" to
        listOf("program", "uniformNames", "uniformIndices", "uniformIndicesOffset"),
    "glGetUniformIndices(I[Ljava/lang/String;Ljava/nio/IntBuffer;)V" to
        listOf("program", "uniformNames", "uniformIndices"),
    "glTransformFeedbackVaryings(I[Ljava/lang/String;I)V" to listOf("program", "varyings", "bufferMode"),
)

internal val PASSINGS: Map<ParamKey, Passing> = mapOf(
    ParamKey("glBufferData", "data") to Pinned(Param("size"), isNullable = true),
    ParamKey("glBufferSubData", "data") to Pinned(Param("size")),
    ParamKey("glCompressedTexImage2D", "data") to Pinned(Param("imageSize")),
    ParamKey("glCompressedTexSubImage2D", "data") to Pinned(Param("imageSize")),
    ParamKey("glDeleteBuffers", "buffers") to Pinned(Param("n")),
    ParamKey("glDeleteFramebuffers", "framebuffers") to Pinned(Param("n")),
    ParamKey("glDeleteRenderbuffers", "renderbuffers") to Pinned(Param("n")),
    ParamKey("glDeleteTextures", "textures") to Pinned(Param("n")),
    ParamKey("glDrawElements", "indices") to Pinned(Query(INDICES, "count", "type")),
    ParamKey("glGenBuffers", "buffers") to Pinned(Param("n")),
    ParamKey("glGenFramebuffers", "framebuffers") to Pinned(Param("n")),
    ParamKey("glGenRenderbuffers", "renderbuffers") to Pinned(Param("n")),
    ParamKey("glGenTextures", "textures") to Pinned(Param("n")),
    ParamKey("glGetActiveAttrib", "length") to Pinned(Literal(1)),
    ParamKey("glGetActiveAttrib", "size") to Pinned(Literal(1)),
    ParamKey("glGetActiveAttrib", "type") to Pinned(Literal(1)),
    ParamKey("glGetActiveAttrib", "name") to Pinned(Param("bufSize")),
    ParamKey("glGetActiveUniform", "length") to Pinned(Literal(1)),
    ParamKey("glGetActiveUniform", "size") to Pinned(Literal(1)),
    ParamKey("glGetActiveUniform", "type") to Pinned(Literal(1)),
    ParamKey("glGetActiveUniform", "name") to Pinned(Param("bufSize")),
    ParamKey("glGetAttachedShaders", "count") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetAttachedShaders", "shaders") to Pinned(Param("maxCount")),
    ParamKey("glGetBooleanv", "data") to Pinned(Query(GET, "pname")),
    ParamKey("glGetBufferParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetFloatv", "data") to Pinned(Query(GET, "pname")),
    ParamKey("glGetFramebufferAttachmentParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetIntegerv", "data") to Pinned(Query(GET, "pname")),
    ParamKey("glGetProgramiv", "params") to Pinned(Query(PROGRAM, "pname")),
    ParamKey("glGetRenderbufferParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetShaderiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetShaderPrecisionFormat", "range") to Pinned(Literal(2)),
    ParamKey("glGetShaderPrecisionFormat", "precision") to Pinned(Literal(1)),
    ParamKey("glGetShaderSource", "length") to Pinned(Literal(1)),
    ParamKey("glGetShaderSource", "source") to Pinned(Param("bufSize")),
    ParamKey("glGetTexParameterfv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glGetTexParameteriv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glGetUniformfv", "params") to Pinned(Literal(1)),
    ParamKey("glGetUniformiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetVertexAttribfv", "params") to Pinned(Query(VERTEX_ATTRIB, "pname")),
    ParamKey("glGetVertexAttribiv", "params") to Pinned(Query(VERTEX_ATTRIB, "pname")),
    ParamKey("glReadPixels", "pixels") to Pinned(),
    ParamKey("glShaderBinary", "shaders") to Pinned(Param("count")),
    ParamKey("glShaderBinary", "binary") to Pinned(Param("length")),
    ParamKey("glTexImage2D", "pixels") to Pinned(isNullable = true),
    ParamKey("glTexParameterfv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glTexParameteriv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glTexSubImage2D", "pixels") to Pinned(isNullable = true),
    ParamKey("glUniform1fv", "value") to Pinned(Param("count")),
    ParamKey("glUniform1iv", "value") to Pinned(Param("count")),
    ParamKey("glUniform2fv", "value") to Pinned(Param("count", 2)),
    ParamKey("glUniform2iv", "value") to Pinned(Param("count", 2)),
    ParamKey("glUniform3fv", "value") to Pinned(Param("count", 3)),
    ParamKey("glUniform3iv", "value") to Pinned(Param("count", 3)),
    ParamKey("glUniform4fv", "value") to Pinned(Param("count", 4)),
    ParamKey("glUniform4iv", "value") to Pinned(Param("count", 4)),
    ParamKey("glUniformMatrix2fv", "value") to Pinned(Param("count", 4)),
    ParamKey("glUniformMatrix3fv", "value") to Pinned(Param("count", 9)),
    ParamKey("glUniformMatrix4fv", "value") to Pinned(Param("count", 16)),
    ParamKey("glVertexAttrib1fv", "v") to Pinned(Literal(1)),
    ParamKey("glVertexAttrib2fv", "v") to Pinned(Literal(2)),
    ParamKey("glVertexAttrib3fv", "v") to Pinned(Literal(3)),
    ParamKey("glVertexAttrib4fv", "v") to Pinned(Literal(4)),
    ParamKey("glVertexAttribPointer", "pointer") to Direct,
    ParamKey("glDrawRangeElements", "indices") to Pinned(Query(INDICES, "count", "type")),
    ParamKey("glTexImage3D", "pixels") to Pinned(isNullable = true),
    ParamKey("glTexSubImage3D", "pixels") to Pinned(),
    ParamKey("glCompressedTexImage3D", "data") to Pinned(Param("imageSize")),
    ParamKey("glCompressedTexSubImage3D", "data") to Pinned(Param("imageSize")),
    ParamKey("glGenQueries", "ids") to Pinned(Param("n")),
    ParamKey("glDeleteQueries", "ids") to Pinned(Param("n")),
    ParamKey("glGetQueryiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetQueryObjectuiv", "params") to Pinned(Literal(1)),
    ParamKey("glDrawBuffers", "bufs") to Pinned(Param("n")),
    ParamKey("glUniformMatrix2x3fv", "value") to Pinned(Param("count", 6)),
    ParamKey("glUniformMatrix3x2fv", "value") to Pinned(Param("count", 6)),
    ParamKey("glUniformMatrix2x4fv", "value") to Pinned(Param("count", 8)),
    ParamKey("glUniformMatrix4x2fv", "value") to Pinned(Param("count", 8)),
    ParamKey("glUniformMatrix3x4fv", "value") to Pinned(Param("count", 12)),
    ParamKey("glUniformMatrix4x3fv", "value") to Pinned(Param("count", 12)),
    ParamKey("glDeleteVertexArrays", "arrays") to Pinned(Param("n")),
    ParamKey("glGenVertexArrays", "arrays") to Pinned(Param("n")),
    ParamKey("glGetIntegeri_v", "data") to Pinned(Query(INDEXED, "target")),
    ParamKey("glGetTransformFeedbackVarying", "length") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "size") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "type") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "name") to Pinned(Param("bufSize")),
    ParamKey("glVertexAttribIPointer", "pointer") to Direct,
    ParamKey("glGetVertexAttribIiv", "params") to Pinned(Query(VERTEX_ATTRIB, "pname")),
    ParamKey("glGetVertexAttribIuiv", "params") to Pinned(Query(VERTEX_ATTRIB, "pname")),
    ParamKey("glVertexAttribI4iv", "v") to Pinned(Literal(4)),
    ParamKey("glVertexAttribI4uiv", "v") to Pinned(Literal(4)),
    ParamKey("glGetUniformuiv", "params") to Pinned(Literal(1)),
    ParamKey("glUniform1uiv", "value") to Pinned(Param("count")),
    ParamKey("glUniform2uiv", "value") to Pinned(Param("count", 2)),
    ParamKey("glUniform3uiv", "value") to Pinned(Param("count", 3)),
    ParamKey("glUniform4uiv", "value") to Pinned(Param("count", 4)),
    ParamKey("glClearBufferiv", "value") to Pinned(Query(CLEAR_BUFFER, "buffer")),
    ParamKey("glClearBufferuiv", "value") to Pinned(Query(CLEAR_BUFFER, "buffer")),
    ParamKey("glClearBufferfv", "value") to Pinned(Query(CLEAR_BUFFER, "buffer")),
    ParamKey("glGetActiveUniformsiv", "uniformIndices") to Pinned(Param("uniformCount")),
    ParamKey("glGetActiveUniformsiv", "params") to Pinned(Param("uniformCount")),
    ParamKey("glGetActiveUniformBlockiv", "params") to
        Pinned(Query(UNIFORM_BLOCK, "program", "uniformBlockIndex", "pname")),
    ParamKey("glGetActiveUniformBlockName", "length") to Pinned(Literal(1)),
    ParamKey("glGetActiveUniformBlockName", "uniformBlockName") to Pinned(Param("bufSize")),
    ParamKey("glDrawElementsInstanced", "indices") to Pinned(Query(INDICES, "count", "type")),
    ParamKey("glGetInteger64v", "data") to Pinned(Query(GET, "pname")),
    ParamKey("glGetSynciv", "length") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetSynciv", "values") to Pinned(Param("bufSize")),
    ParamKey("glGetInteger64i_v", "data") to Pinned(Query(INDEXED, "target")),
    ParamKey("glGetBufferParameteri64v", "params") to Pinned(Literal(1)),
    ParamKey("glGenSamplers", "samplers") to Pinned(Param("count")),
    ParamKey("glDeleteSamplers", "samplers") to Pinned(Param("count")),
    ParamKey("glSamplerParameteriv", "param") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glSamplerParameterfv", "param") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glGetSamplerParameteriv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glGetSamplerParameterfv", "params") to Pinned(Query(TEX_PARAMETER, "pname")),
    ParamKey("glDeleteTransformFeedbacks", "ids") to Pinned(Param("n")),
    ParamKey("glGenTransformFeedbacks", "ids") to Pinned(Param("n")),
    ParamKey("glGetProgramBinary", "length") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetProgramBinary", "binaryFormat") to Pinned(Literal(1)),
    ParamKey("glGetProgramBinary", "binary") to Pinned(Param("bufSize")),
    ParamKey("glProgramBinary", "binary") to Pinned(Param("length")),
    ParamKey("glInvalidateFramebuffer", "attachments") to Pinned(Param("numAttachments")),
    ParamKey("glInvalidateSubFramebuffer", "attachments") to Pinned(Param("numAttachments")),
    ParamKey("glGetInternalformativ", "params") to Pinned(Param("bufSize")),
)
