package org.androidgraal.gles.generator

import com.palantir.javapoet.ClassName
import com.palantir.javapoet.TypeName
import org.androidgraal.gles.generator.Count.Literal
import org.androidgraal.gles.generator.Count.Param
import org.androidgraal.gles.generator.Count.Query
import org.androidgraal.gles.generator.CountQuery.BASE_VERTEX_INDEX_BYTES
import org.androidgraal.gles.generator.CountQuery.CLEAR_BUFFER_COUNT
import org.androidgraal.gles.generator.CountQuery.INDEXED_STATE_COUNT
import org.androidgraal.gles.generator.CountQuery.INDEX_BYTES
import org.androidgraal.gles.generator.CountQuery.PARAMETER_COUNT
import org.androidgraal.gles.generator.CountQuery.PROGRAM_COUNT
import org.androidgraal.gles.generator.CountQuery.STATE_COUNT
import org.androidgraal.gles.generator.CountQuery.UNIFORM_BLOCK_COUNT
import org.androidgraal.gles.generator.CountQuery.VERTEX_ATTRIB_COUNT
import org.androidgraal.gles.generator.Passing.Counted
import org.androidgraal.gles.generator.Passing.Direct
import org.androidgraal.gles.generator.Passing.Pinned

internal data class ParamKey(val function: String, val param: String)

internal sealed interface Count {
    class Literal(val times: Int) : Count

    class Param(val name: String, val times: Int = 1) : Count

    class Query(val query: CountQuery, vararg val params: String) : Count
}

internal enum class CountQuery(val method: String) {
    STATE_COUNT("stateCount"),
    VERTEX_ATTRIB_COUNT("vertexAttribCount"),
    CLEAR_BUFFER_COUNT("clearBufferCount"),
    UNIFORM_BLOCK_COUNT("uniformBlockCount"),
    INDEX_BYTES("indexBytes"),
    INDEXED_STATE_COUNT("indexedStateCount"),
    PARAMETER_COUNT("parameterCount"),
    PROGRAM_COUNT("programCount"),
    BASE_VERTEX_INDEX_BYTES("baseVertexIndexBytes"),
}

internal sealed interface Passing {
    class Pinned(val count: Count? = null, val isNullable: Boolean = false) : Passing

    object Direct : Passing

    class Counted(val length: String, val isNullable: Boolean = false) : Passing
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
    "glCreateShaderProgramv(I[Ljava/lang/String;)I" to listOf("type", "strings"),
    "glGetProgramPipelineInfoLog(I)Ljava/lang/String;" to listOf("pipeline"),
    "glGetProgramResourceName(III)Ljava/lang/String;" to listOf("program", "programInterface", "index"),
    "glGetDebugMessageLog(II[II[II[II[II[II[BI)I" to listOf(
        "count",
        "bufSize",
        "sources",
        "sourcesOffset",
        "types",
        "typesOffset",
        "ids",
        "idsOffset",
        "severities",
        "severitiesOffset",
        "lengths",
        "lengthsOffset",
        "messageLog",
        "messageLogOffset",
    ),
    "glGetDebugMessageLog(I[II[II[II[II)[Ljava/lang/String;" to listOf(
        "count",
        "sources",
        "sourcesOffset",
        "types",
        "typesOffset",
        "ids",
        "idsOffset",
        "severities",
        "severitiesOffset",
    ),
    "glGetDebugMessageLog(ILjava/nio/IntBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;)" +
        "[Ljava/lang/String;" to listOf("count", "sources", "types", "ids", "severities"),
    "glGetDebugMessageLog(ILjava/nio/IntBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;Ljava/nio/IntBuffer;" +
        "Ljava/nio/IntBuffer;Ljava/nio/ByteBuffer;)I" to
        listOf("count", "sources", "types", "ids", "severities", "lengths", "messageLog"),
    "glGetObjectLabel(II)Ljava/lang/String;" to listOf("identifier", "name"),
    "glObjectPtrLabel(JLjava/lang/String;)V" to listOf("ptr", "label"),
    "glGetObjectPtrLabel(J)Ljava/lang/String;" to listOf("ptr"),
    "glGetPointerv(I)J" to listOf("pname"),
    "glDebugMessageCallback(Landroid/opengl/GLES32\$DebugProc;)V" to listOf("callback"),
)

internal class Callback(val name: String, val method: String, val parameters: List<Pair<TypeName, String>>)

internal val CALLBACKS: Map<ClassName, List<Callback>> = mapOf(
    JavaNames.GLES32 to listOf(
        Callback(
            "DebugProc",
            "onMessage",
            listOf(
                TypeName.INT to "source",
                TypeName.INT to "type",
                TypeName.INT to "id",
                TypeName.INT to "severity",
                ClassName.get(String::class.java) to "message",
            ),
        ),
    ),
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
    ParamKey("glDrawElements", "indices") to Pinned(Query(INDEX_BYTES, "count", "type")),
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
    ParamKey("glGetBooleanv", "data") to Pinned(Query(STATE_COUNT, "pname")),
    ParamKey("glGetBufferParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetFloatv", "data") to Pinned(Query(STATE_COUNT, "pname")),
    ParamKey("glGetFramebufferAttachmentParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetIntegerv", "data") to Pinned(Query(STATE_COUNT, "pname")),
    ParamKey("glGetProgramiv", "params") to Pinned(Query(PROGRAM_COUNT, "pname")),
    ParamKey("glGetRenderbufferParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetShaderiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetShaderPrecisionFormat", "range") to Pinned(Literal(2)),
    ParamKey("glGetShaderPrecisionFormat", "precision") to Pinned(Literal(1)),
    ParamKey("glGetShaderSource", "length") to Pinned(Literal(1)),
    ParamKey("glGetShaderSource", "source") to Pinned(Param("bufSize")),
    ParamKey("glGetTexParameterfv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetTexParameteriv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetUniformfv", "params") to Pinned(Literal(1)),
    ParamKey("glGetUniformiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetVertexAttribfv", "params") to Pinned(Query(VERTEX_ATTRIB_COUNT, "pname")),
    ParamKey("glGetVertexAttribiv", "params") to Pinned(Query(VERTEX_ATTRIB_COUNT, "pname")),
    ParamKey("glReadPixels", "pixels") to Pinned(),
    ParamKey("glShaderBinary", "shaders") to Pinned(Param("count")),
    ParamKey("glShaderBinary", "binary") to Pinned(Param("length")),
    ParamKey("glTexImage2D", "pixels") to Pinned(isNullable = true),
    ParamKey("glTexParameterfv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glTexParameteriv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
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
    ParamKey("glDrawRangeElements", "indices") to Pinned(Query(INDEX_BYTES, "count", "type")),
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
    ParamKey("glGetIntegeri_v", "data") to Pinned(Query(INDEXED_STATE_COUNT, "target")),
    ParamKey("glGetTransformFeedbackVarying", "length") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "size") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "type") to Pinned(Literal(1)),
    ParamKey("glGetTransformFeedbackVarying", "name") to Pinned(Param("bufSize")),
    ParamKey("glVertexAttribIPointer", "pointer") to Direct,
    ParamKey("glGetVertexAttribIiv", "params") to Pinned(Query(VERTEX_ATTRIB_COUNT, "pname")),
    ParamKey("glGetVertexAttribIuiv", "params") to Pinned(Query(VERTEX_ATTRIB_COUNT, "pname")),
    ParamKey("glVertexAttribI4iv", "v") to Pinned(Literal(4)),
    ParamKey("glVertexAttribI4uiv", "v") to Pinned(Literal(4)),
    ParamKey("glGetUniformuiv", "params") to Pinned(Literal(1)),
    ParamKey("glUniform1uiv", "value") to Pinned(Param("count")),
    ParamKey("glUniform2uiv", "value") to Pinned(Param("count", 2)),
    ParamKey("glUniform3uiv", "value") to Pinned(Param("count", 3)),
    ParamKey("glUniform4uiv", "value") to Pinned(Param("count", 4)),
    ParamKey("glClearBufferiv", "value") to Pinned(Query(CLEAR_BUFFER_COUNT, "buffer")),
    ParamKey("glClearBufferuiv", "value") to Pinned(Query(CLEAR_BUFFER_COUNT, "buffer")),
    ParamKey("glClearBufferfv", "value") to Pinned(Query(CLEAR_BUFFER_COUNT, "buffer")),
    ParamKey("glGetActiveUniformsiv", "uniformIndices") to Pinned(Param("uniformCount")),
    ParamKey("glGetActiveUniformsiv", "params") to Pinned(Param("uniformCount")),
    ParamKey("glGetActiveUniformBlockiv", "params") to
        Pinned(Query(UNIFORM_BLOCK_COUNT, "program", "uniformBlockIndex", "pname")),
    ParamKey("glGetActiveUniformBlockName", "length") to Pinned(Literal(1)),
    ParamKey("glGetActiveUniformBlockName", "uniformBlockName") to Pinned(Param("bufSize")),
    ParamKey("glDrawElementsInstanced", "indices") to Pinned(Query(INDEX_BYTES, "count", "type")),
    ParamKey("glGetInteger64v", "data") to Pinned(Query(STATE_COUNT, "pname")),
    ParamKey("glGetSynciv", "length") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetSynciv", "values") to Pinned(Param("bufSize")),
    ParamKey("glGetInteger64i_v", "data") to Pinned(Query(INDEXED_STATE_COUNT, "target")),
    ParamKey("glGetBufferParameteri64v", "params") to Pinned(Literal(1)),
    ParamKey("glGenSamplers", "samplers") to Pinned(Param("count")),
    ParamKey("glDeleteSamplers", "samplers") to Pinned(Param("count")),
    ParamKey("glSamplerParameteriv", "param") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glSamplerParameterfv", "param") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetSamplerParameteriv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetSamplerParameterfv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glDeleteTransformFeedbacks", "ids") to Pinned(Param("n")),
    ParamKey("glGenTransformFeedbacks", "ids") to Pinned(Param("n")),
    ParamKey("glGetProgramBinary", "length") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetProgramBinary", "binaryFormat") to Pinned(Literal(1)),
    ParamKey("glGetProgramBinary", "binary") to Pinned(Param("bufSize")),
    ParamKey("glProgramBinary", "binary") to Pinned(Param("length")),
    ParamKey("glInvalidateFramebuffer", "attachments") to Pinned(Param("numAttachments")),
    ParamKey("glInvalidateSubFramebuffer", "attachments") to Pinned(Param("numAttachments")),
    ParamKey("glGetInternalformativ", "params") to Pinned(Param("bufSize")),
    ParamKey("glDeleteProgramPipelines", "pipelines") to Pinned(Param("n")),
    ParamKey("glGenProgramPipelines", "pipelines") to Pinned(Param("n")),
    ParamKey("glGetBooleani_v", "data") to Pinned(Query(INDEXED_STATE_COUNT, "target")),
    ParamKey("glGetFramebufferParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glGetMultisamplefv", "val") to Pinned(Literal(2)),
    ParamKey("glGetProgramInterfaceiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetProgramPipelineiv", "params") to Pinned(Literal(1)),
    ParamKey("glGetProgramResourceiv", "props") to Pinned(Param("propCount")),
    ParamKey("glGetProgramResourceiv", "length") to Pinned(Literal(1), isNullable = true),
    ParamKey("glGetProgramResourceiv", "params") to Pinned(Param("bufSize")),
    ParamKey("glGetTexLevelParameterfv", "params") to Pinned(Literal(1)),
    ParamKey("glGetTexLevelParameteriv", "params") to Pinned(Literal(1)),
    ParamKey("glProgramUniform1fv", "value") to Pinned(Param("count")),
    ParamKey("glProgramUniform1iv", "value") to Pinned(Param("count")),
    ParamKey("glProgramUniform1uiv", "value") to Pinned(Param("count")),
    ParamKey("glProgramUniform2fv", "value") to Pinned(Param("count", 2)),
    ParamKey("glProgramUniform2iv", "value") to Pinned(Param("count", 2)),
    ParamKey("glProgramUniform2uiv", "value") to Pinned(Param("count", 2)),
    ParamKey("glProgramUniform3fv", "value") to Pinned(Param("count", 3)),
    ParamKey("glProgramUniform3iv", "value") to Pinned(Param("count", 3)),
    ParamKey("glProgramUniform3uiv", "value") to Pinned(Param("count", 3)),
    ParamKey("glProgramUniform4fv", "value") to Pinned(Param("count", 4)),
    ParamKey("glProgramUniform4iv", "value") to Pinned(Param("count", 4)),
    ParamKey("glProgramUniform4uiv", "value") to Pinned(Param("count", 4)),
    ParamKey("glProgramUniformMatrix2fv", "value") to Pinned(Param("count", 4)),
    ParamKey("glProgramUniformMatrix3fv", "value") to Pinned(Param("count", 9)),
    ParamKey("glProgramUniformMatrix4fv", "value") to Pinned(Param("count", 16)),
    ParamKey("glProgramUniformMatrix2x3fv", "value") to Pinned(Param("count", 6)),
    ParamKey("glProgramUniformMatrix3x2fv", "value") to Pinned(Param("count", 6)),
    ParamKey("glProgramUniformMatrix2x4fv", "value") to Pinned(Param("count", 8)),
    ParamKey("glProgramUniformMatrix4x2fv", "value") to Pinned(Param("count", 8)),
    ParamKey("glProgramUniformMatrix3x4fv", "value") to Pinned(Param("count", 12)),
    ParamKey("glProgramUniformMatrix4x3fv", "value") to Pinned(Param("count", 12)),
    ParamKey("glDebugMessageControl", "ids") to Pinned(Param("count")),
    ParamKey("glDebugMessageInsert", "buf") to Counted("length"),
    ParamKey("glPushDebugGroup", "message") to Counted("length"),
    ParamKey("glObjectLabel", "label") to Counted("length", isNullable = true),
    ParamKey("glDrawElementsBaseVertex", "indices") to
        Pinned(Query(BASE_VERTEX_INDEX_BYTES, "count", "type", "basevertex")),
    ParamKey("glDrawRangeElementsBaseVertex", "indices") to
        Pinned(Query(BASE_VERTEX_INDEX_BYTES, "count", "type", "basevertex")),
    ParamKey("glDrawElementsInstancedBaseVertex", "indices") to
        Pinned(Query(BASE_VERTEX_INDEX_BYTES, "count", "type", "basevertex")),
    ParamKey("glReadnPixels", "data") to Pinned(Param("bufSize")),
    ParamKey("glGetnUniformfv", "params") to Pinned(Param("bufSize")),
    ParamKey("glGetnUniformiv", "params") to Pinned(Param("bufSize")),
    ParamKey("glGetnUniformuiv", "params") to Pinned(Param("bufSize")),
    ParamKey("glTexParameterIiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glTexParameterIuiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetTexParameterIiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetTexParameterIuiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glSamplerParameterIiv", "param") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glSamplerParameterIuiv", "param") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetSamplerParameterIiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
    ParamKey("glGetSamplerParameterIuiv", "params") to Pinned(Query(PARAMETER_COUNT, "pname")),
)
