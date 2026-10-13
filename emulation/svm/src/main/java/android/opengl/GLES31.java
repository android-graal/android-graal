// Generated from android.jar and the NDK's GLES headers.
package android.opengl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.androidgraal.android.CString;
import org.androidgraal.android.Pointer;
import org.androidgraal.gles.Counts;
import org.androidgraal.gles.GLESv3;
import org.androidgraal.gles.Handwritten;
import org.androidgraal.hosted.annotate.Emulated;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.c.type.CTypeConversion;

@Emulated
public class GLES31 extends GLES30 {
    public static final int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 0x92D9;

    public static final int GL_ACTIVE_PROGRAM = 0x8259;

    public static final int GL_ACTIVE_RESOURCES = 0x92F5;

    public static final int GL_ACTIVE_VARIABLES = 0x9305;

    public static final int GL_ALL_BARRIER_BITS = 0xFFFFFFFF;

    public static final int GL_ALL_SHADER_BITS = 0xFFFFFFFF;

    public static final int GL_ARRAY_SIZE = 0x92FB;

    public static final int GL_ARRAY_STRIDE = 0x92FE;

    public static final int GL_ATOMIC_COUNTER_BARRIER_BIT = 0x1000;

    public static final int GL_ATOMIC_COUNTER_BUFFER = 0x92C0;

    public static final int GL_ATOMIC_COUNTER_BUFFER_BINDING = 0x92C1;

    public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 0x9301;

    public static final int GL_ATOMIC_COUNTER_BUFFER_SIZE = 0x92C3;

    public static final int GL_ATOMIC_COUNTER_BUFFER_START = 0x92C2;

    public static final int GL_BLOCK_INDEX = 0x92FD;

    public static final int GL_BUFFER_BINDING = 0x9302;

    public static final int GL_BUFFER_DATA_SIZE = 0x9303;

    public static final int GL_BUFFER_UPDATE_BARRIER_BIT = 0x0200;

    public static final int GL_BUFFER_VARIABLE = 0x92E5;

    public static final int GL_COMMAND_BARRIER_BIT = 0x0040;

    public static final int GL_COMPUTE_SHADER = 0x91B9;

    public static final int GL_COMPUTE_SHADER_BIT = 0x0020;

    public static final int GL_COMPUTE_WORK_GROUP_SIZE = 0x8267;

    public static final int GL_DEPTH_STENCIL_TEXTURE_MODE = 0x90EA;

    public static final int GL_DISPATCH_INDIRECT_BUFFER = 0x90EE;

    public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 0x90EF;

    public static final int GL_DRAW_INDIRECT_BUFFER = 0x8F3F;

    public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 0x8F43;

    public static final int GL_ELEMENT_ARRAY_BARRIER_BIT = 2;

    public static final int GL_FRAGMENT_SHADER_BIT = 2;

    public static final int GL_FRAMEBUFFER_BARRIER_BIT = 0x0400;

    public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 0x9314;

    public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 0x9311;

    public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 0x9313;

    public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 0x9310;

    public static final int GL_IMAGE_2D = 0x904D;

    public static final int GL_IMAGE_2D_ARRAY = 0x9053;

    public static final int GL_IMAGE_3D = 0x904E;

    public static final int GL_IMAGE_BINDING_ACCESS = 0x8F3E;

    public static final int GL_IMAGE_BINDING_FORMAT = 0x906E;

    public static final int GL_IMAGE_BINDING_LAYER = 0x8F3D;

    public static final int GL_IMAGE_BINDING_LAYERED = 0x8F3C;

    public static final int GL_IMAGE_BINDING_LEVEL = 0x8F3B;

    public static final int GL_IMAGE_BINDING_NAME = 0x8F3A;

    public static final int GL_IMAGE_CUBE = 0x9050;

    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 0x90C9;

    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 0x90C8;

    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 0x90C7;

    public static final int GL_INT_IMAGE_2D = 0x9058;

    public static final int GL_INT_IMAGE_2D_ARRAY = 0x905E;

    public static final int GL_INT_IMAGE_3D = 0x9059;

    public static final int GL_INT_IMAGE_CUBE = 0x905B;

    public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 0x9109;

    public static final int GL_IS_ROW_MAJOR = 0x9300;

    public static final int GL_LOCATION = 0x930E;

    public static final int GL_MATRIX_STRIDE = 0x92FF;

    public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 0x92DC;

    public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 0x92D8;

    public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 0x910E;

    public static final int GL_MAX_COMBINED_ATOMIC_COUNTERS = 0x92D7;

    public static final int GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 0x92D1;

    public static final int GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 0x8266;

    public static final int GL_MAX_COMBINED_IMAGE_UNIFORMS = 0x90CF;

    public static final int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 0x8F39;

    public static final int GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 0x90DC;

    public static final int GL_MAX_COMPUTE_ATOMIC_COUNTERS = 0x8265;

    public static final int GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 0x8264;

    public static final int GL_MAX_COMPUTE_IMAGE_UNIFORMS = 0x91BD;

    public static final int GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 0x90DB;

    public static final int GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 0x8262;

    public static final int GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 0x91BC;

    public static final int GL_MAX_COMPUTE_UNIFORM_BLOCKS = 0x91BB;

    public static final int GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 0x8263;

    public static final int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 0x91BE;

    public static final int GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 0x90EB;

    public static final int GL_MAX_COMPUTE_WORK_GROUP_SIZE = 0x91BF;

    public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 0x910F;

    public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 0x92D6;

    public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 0x92D0;

    public static final int GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 0x90CE;

    public static final int GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 0x90DA;

    public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 0x9316;

    public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 0x9318;

    public static final int GL_MAX_FRAMEBUFFER_WIDTH = 0x9315;

    public static final int GL_MAX_IMAGE_UNITS = 0x8F38;

    public static final int GL_MAX_INTEGER_SAMPLES = 0x9110;

    public static final int GL_MAX_NAME_LENGTH = 0x92F6;

    public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 0x92F7;

    public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET = 0x8E5F;

    public static final int GL_MAX_SAMPLE_MASK_WORDS = 0x8E59;

    public static final int GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 0x90DE;

    public static final int GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 0x90DD;

    public static final int GL_MAX_UNIFORM_LOCATIONS = 0x826E;

    public static final int GL_MAX_VERTEX_ATOMIC_COUNTERS = 0x92D2;

    public static final int GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 0x92CC;

    public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 0x82DA;

    public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D9;

    public static final int GL_MAX_VERTEX_ATTRIB_STRIDE = 0x82E5;

    public static final int GL_MAX_VERTEX_IMAGE_UNIFORMS = 0x90CA;

    public static final int GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 0x90D6;

    public static final int GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET = 0x8E5E;

    public static final int GL_NAME_LENGTH = 0x92F9;

    public static final int GL_NUM_ACTIVE_VARIABLES = 0x9304;

    public static final int GL_OFFSET = 0x92FC;

    public static final int GL_PIXEL_BUFFER_BARRIER_BIT = 0x0080;

    public static final int GL_PROGRAM_INPUT = 0x92E3;

    public static final int GL_PROGRAM_OUTPUT = 0x92E4;

    public static final int GL_PROGRAM_PIPELINE_BINDING = 0x825A;

    public static final int GL_PROGRAM_SEPARABLE = 0x8258;

    public static final int GL_READ_ONLY = 0x88B8;

    public static final int GL_READ_WRITE = 0x88BA;

    public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 0x930B;

    public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 0x930A;

    public static final int GL_REFERENCED_BY_VERTEX_SHADER = 0x9306;

    public static final int GL_SAMPLER_2D_MULTISAMPLE = 0x9108;

    public static final int GL_SAMPLE_MASK = 0x8E51;

    public static final int GL_SAMPLE_MASK_VALUE = 0x8E52;

    public static final int GL_SAMPLE_POSITION = 0x8E50;

    public static final int GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 0x0020;

    public static final int GL_SHADER_STORAGE_BARRIER_BIT = 0x2000;

    public static final int GL_SHADER_STORAGE_BLOCK = 0x92E6;

    public static final int GL_SHADER_STORAGE_BUFFER = 0x90D2;

    public static final int GL_SHADER_STORAGE_BUFFER_BINDING = 0x90D3;

    public static final int GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 0x90DF;

    public static final int GL_SHADER_STORAGE_BUFFER_SIZE = 0x90D5;

    public static final int GL_SHADER_STORAGE_BUFFER_START = 0x90D4;

    public static final int GL_STENCIL_INDEX = 0x1901;

    public static final int GL_TEXTURE_2D_MULTISAMPLE = 0x9100;

    public static final int GL_TEXTURE_ALPHA_SIZE = 0x805F;

    public static final int GL_TEXTURE_ALPHA_TYPE = 0x8C13;

    public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 0x9104;

    public static final int GL_TEXTURE_BLUE_SIZE = 0x805E;

    public static final int GL_TEXTURE_BLUE_TYPE = 0x8C12;

    public static final int GL_TEXTURE_COMPRESSED = 0x86A1;

    public static final int GL_TEXTURE_DEPTH = 0x8071;

    public static final int GL_TEXTURE_DEPTH_SIZE = 0x884A;

    public static final int GL_TEXTURE_DEPTH_TYPE = 0x8C16;

    public static final int GL_TEXTURE_FETCH_BARRIER_BIT = 8;

    public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 0x9107;

    public static final int GL_TEXTURE_GREEN_SIZE = 0x805D;

    public static final int GL_TEXTURE_GREEN_TYPE = 0x8C11;

    public static final int GL_TEXTURE_HEIGHT = 0x1001;

    public static final int GL_TEXTURE_INTERNAL_FORMAT = 0x1003;

    public static final int GL_TEXTURE_RED_SIZE = 0x805C;

    public static final int GL_TEXTURE_RED_TYPE = 0x8C10;

    public static final int GL_TEXTURE_SAMPLES = 0x9106;

    public static final int GL_TEXTURE_SHARED_SIZE = 0x8C3F;

    public static final int GL_TEXTURE_STENCIL_SIZE = 0x88F1;

    public static final int GL_TEXTURE_UPDATE_BARRIER_BIT = 0x0100;

    public static final int GL_TEXTURE_WIDTH = 0x1000;

    public static final int GL_TOP_LEVEL_ARRAY_SIZE = 0x930C;

    public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 0x930D;

    public static final int GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 0x0800;

    public static final int GL_TRANSFORM_FEEDBACK_VARYING = 0x92F4;

    public static final int GL_TYPE = 0x92FA;

    public static final int GL_UNIFORM = 0x92E1;

    public static final int GL_UNIFORM_BARRIER_BIT = 4;

    public static final int GL_UNIFORM_BLOCK = 0x92E2;

    public static final int GL_UNSIGNED_INT_ATOMIC_COUNTER = 0x92DB;

    public static final int GL_UNSIGNED_INT_IMAGE_2D = 0x9063;

    public static final int GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 0x9069;

    public static final int GL_UNSIGNED_INT_IMAGE_3D = 0x9064;

    public static final int GL_UNSIGNED_INT_IMAGE_CUBE = 0x9066;

    public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 0x910A;

    public static final int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 1;

    public static final int GL_VERTEX_ATTRIB_BINDING = 0x82D4;

    public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 0x82D5;

    public static final int GL_VERTEX_BINDING_BUFFER = 0x8F4F;

    public static final int GL_VERTEX_BINDING_DIVISOR = 0x82D6;

    public static final int GL_VERTEX_BINDING_OFFSET = 0x82D7;

    public static final int GL_VERTEX_BINDING_STRIDE = 0x82D8;

    public static final int GL_VERTEX_SHADER_BIT = 1;

    public static final int GL_WRITE_ONLY = 0x88B9;

    GLES31() {
    }

    public static void glActiveShaderProgram(int pipeline, int program) {
        GLESv3.glActiveShaderProgram(pipeline, program);
    }

    public static void glBindImageTexture(int unit, int texture, int level, boolean layered, int layer, int access,
            int format) {
        GLESv3.glBindImageTexture(unit, texture, level, layered, layer, access, format);
    }

    public static void glBindProgramPipeline(int pipeline) {
        GLESv3.glBindProgramPipeline(pipeline);
    }

    public static void glBindVertexBuffer(int bindingindex, int buffer, long offset, int stride) {
        GLESv3.glBindVertexBuffer(bindingindex, buffer, offset, stride);
    }

    public static int glCreateShaderProgramv(int type, String[] strings) {
        return Handwritten.glCreateShaderProgramv(type, strings);
    }

    public static void glDeleteProgramPipelines(int n, IntBuffer pipelines) {
        try (PinnedObject pipelinesPin = Pointer.pin(pipelines, n * 4L)) {
            GLESv3.glDeleteProgramPipelines(n, Pointer.address(pipelines, pipelinesPin));
        }
    }

    public static void glDeleteProgramPipelines(int n, int[] pipelines, int pipelinesOffset) {
        try (PinnedObject pipelinesPin = Pointer.pin(pipelines, pipelinesOffset, n * 4L)) {
            GLESv3.glDeleteProgramPipelines(n, Pointer.address(pipelinesPin, pipelinesOffset));
        }
    }

    public static void glDispatchCompute(int num_groups_x, int num_groups_y, int num_groups_z) {
        GLESv3.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
    }

    public static void glDispatchComputeIndirect(long indirect) {
        GLESv3.glDispatchComputeIndirect(indirect);
    }

    public static void glDrawArraysIndirect(int mode, long indirectOffset) {
        GLESv3.glDrawArraysIndirect(mode, Pointer.address(indirectOffset));
    }

    public static void glDrawElementsIndirect(int mode, int type, long indirectOffset) {
        GLESv3.glDrawElementsIndirect(mode, type, Pointer.address(indirectOffset));
    }

    public static void glFramebufferParameteri(int target, int pname, int param) {
        GLESv3.glFramebufferParameteri(target, pname, param);
    }

    public static void glGenProgramPipelines(int n, IntBuffer pipelines) {
        try (PinnedObject pipelinesPin = Pointer.pin(pipelines, n * 4L)) {
            GLESv3.glGenProgramPipelines(n, Pointer.address(pipelines, pipelinesPin));
        }
    }

    public static void glGenProgramPipelines(int n, int[] pipelines, int pipelinesOffset) {
        try (PinnedObject pipelinesPin = Pointer.pin(pipelines, pipelinesOffset, n * 4L)) {
            GLESv3.glGenProgramPipelines(n, Pointer.address(pipelinesPin, pipelinesOffset));
        }
    }

    public static void glGetBooleani_v(int target, int index, IntBuffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, Counts.indexedStateCount(target))) {
            GLESv3.glGetBooleani_v(target, index, Pointer.address(data, dataPin));
        }
    }

    public static void glGetBooleani_v(int target, int index, boolean[] data, int dataOffset) {
        try (PinnedObject dataPin = Pointer.pin(data, dataOffset, Counts.indexedStateCount(target))) {
            GLESv3.glGetBooleani_v(target, index, Pointer.address(dataPin, dataOffset));
        }
    }

    public static void glGetFramebufferParameteriv(int target, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv3.glGetFramebufferParameteriv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetFramebufferParameteriv(int target, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv3.glGetFramebufferParameteriv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetMultisamplefv(int pname, int index, FloatBuffer val) {
        try (PinnedObject valPin = Pointer.pin(val, 8)) {
            GLESv3.glGetMultisamplefv(pname, index, Pointer.address(val, valPin));
        }
    }

    public static void glGetMultisamplefv(int pname, int index, float[] val, int valOffset) {
        try (PinnedObject valPin = Pointer.pin(val, valOffset, 8)) {
            GLESv3.glGetMultisamplefv(pname, index, Pointer.address(valPin, valOffset));
        }
    }

    public static void glGetProgramInterfaceiv(int program, int programInterface, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv3.glGetProgramInterfaceiv(program, programInterface, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetProgramInterfaceiv(int program, int programInterface, int pname, int[] params,
            int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv3.glGetProgramInterfaceiv(program, programInterface, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static String glGetProgramPipelineInfoLog(int pipeline) {
        return Handwritten.glGetProgramPipelineInfoLog(pipeline);
    }

    public static void glGetProgramPipelineiv(int pipeline, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv3.glGetProgramPipelineiv(pipeline, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetProgramPipelineiv(int pipeline, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv3.glGetProgramPipelineiv(pipeline, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static int glGetProgramResourceIndex(int program, int programInterface, String name) {
        try (CTypeConversion.CCharPointerHolder nameCString = CString.of(name)) {
            return GLESv3.glGetProgramResourceIndex(program, programInterface, nameCString.get());
        }
    }

    public static int glGetProgramResourceLocation(int program, int programInterface, String name) {
        try (CTypeConversion.CCharPointerHolder nameCString = CString.of(name)) {
            return GLESv3.glGetProgramResourceLocation(program, programInterface, nameCString.get());
        }
    }

    public static String glGetProgramResourceName(int program, int programInterface, int index) {
        return Handwritten.glGetProgramResourceName(program, programInterface, index);
    }

    public static void glGetProgramResourceiv(int program, int programInterface, int index, int propCount,
            IntBuffer props, int bufSize, IntBuffer length, IntBuffer params) {
        try (PinnedObject propsPin = Pointer.pin(props, propCount * 4L);
                PinnedObject lengthPin = Pointer.pinNullable(length, 4);
                PinnedObject paramsPin = Pointer.pin(params, bufSize * 4L)) {
            GLESv3.glGetProgramResourceiv(program, programInterface, index, propCount, Pointer.address(props, propsPin),
                    bufSize, Pointer.address(length, lengthPin), Pointer.address(params, paramsPin));
        }
    }

    public static void glGetProgramResourceiv(int program, int programInterface, int index, int propCount, int[] props,
            int propsOffset, int bufSize, int[] length, int lengthOffset, int[] params, int paramsOffset) {
        try (PinnedObject propsPin = Pointer.pin(props, propsOffset, propCount * 4L);
                PinnedObject lengthPin = Pointer.pinNullable(length, lengthOffset, 4);
                PinnedObject paramsPin = Pointer.pin(params, paramsOffset, bufSize * 4L)) {
            GLESv3.glGetProgramResourceiv(program, programInterface, index, propCount,
                    Pointer.address(propsPin, propsOffset), bufSize, Pointer.address(lengthPin, lengthOffset),
                    Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv3.glGetTexLevelParameterfv(target, level, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetTexLevelParameterfv(int target, int level, int pname, float[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv3.glGetTexLevelParameterfv(target, level, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv3.glGetTexLevelParameteriv(target, level, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetTexLevelParameteriv(int target, int level, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv3.glGetTexLevelParameteriv(target, level, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static boolean glIsProgramPipeline(int pipeline) {
        return GLESv3.glIsProgramPipeline(pipeline);
    }

    public static void glMemoryBarrier(int barriers) {
        GLESv3.glMemoryBarrier(barriers);
    }

    public static void glMemoryBarrierByRegion(int barriers) {
        GLESv3.glMemoryBarrierByRegion(barriers);
    }

    public static void glProgramUniform1f(int program, int location, float v0) {
        GLESv3.glProgramUniform1f(program, location, v0);
    }

    public static void glProgramUniform1fv(int program, int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 4L)) {
            GLESv3.glProgramUniform1fv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform1fv(int program, int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 4L)) {
            GLESv3.glProgramUniform1fv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform1i(int program, int location, int v0) {
        GLESv3.glProgramUniform1i(program, location, v0);
    }

    public static void glProgramUniform1iv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 4L)) {
            GLESv3.glProgramUniform1iv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform1iv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 4L)) {
            GLESv3.glProgramUniform1iv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform1ui(int program, int location, int v0) {
        GLESv3.glProgramUniform1ui(program, location, v0);
    }

    public static void glProgramUniform1uiv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 4L)) {
            GLESv3.glProgramUniform1uiv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform1uiv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 4L)) {
            GLESv3.glProgramUniform1uiv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform2f(int program, int location, float v0, float v1) {
        GLESv3.glProgramUniform2f(program, location, v0, v1);
    }

    public static void glProgramUniform2fv(int program, int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 8L)) {
            GLESv3.glProgramUniform2fv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform2fv(int program, int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 8L)) {
            GLESv3.glProgramUniform2fv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform2i(int program, int location, int v0, int v1) {
        GLESv3.glProgramUniform2i(program, location, v0, v1);
    }

    public static void glProgramUniform2iv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 8L)) {
            GLESv3.glProgramUniform2iv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform2iv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 8L)) {
            GLESv3.glProgramUniform2iv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform2ui(int program, int location, int v0, int v1) {
        GLESv3.glProgramUniform2ui(program, location, v0, v1);
    }

    public static void glProgramUniform2uiv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 8L)) {
            GLESv3.glProgramUniform2uiv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform2uiv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 8L)) {
            GLESv3.glProgramUniform2uiv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform3f(int program, int location, float v0, float v1, float v2) {
        GLESv3.glProgramUniform3f(program, location, v0, v1, v2);
    }

    public static void glProgramUniform3fv(int program, int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 12L)) {
            GLESv3.glProgramUniform3fv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform3fv(int program, int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 12L)) {
            GLESv3.glProgramUniform3fv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform3i(int program, int location, int v0, int v1, int v2) {
        GLESv3.glProgramUniform3i(program, location, v0, v1, v2);
    }

    public static void glProgramUniform3iv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 12L)) {
            GLESv3.glProgramUniform3iv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform3iv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 12L)) {
            GLESv3.glProgramUniform3iv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform3ui(int program, int location, int v0, int v1, int v2) {
        GLESv3.glProgramUniform3ui(program, location, v0, v1, v2);
    }

    public static void glProgramUniform3uiv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 12L)) {
            GLESv3.glProgramUniform3uiv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform3uiv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 12L)) {
            GLESv3.glProgramUniform3uiv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform4f(int program, int location, float v0, float v1, float v2, float v3) {
        GLESv3.glProgramUniform4f(program, location, v0, v1, v2, v3);
    }

    public static void glProgramUniform4fv(int program, int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv3.glProgramUniform4fv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform4fv(int program, int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv3.glProgramUniform4fv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform4i(int program, int location, int v0, int v1, int v2, int v3) {
        GLESv3.glProgramUniform4i(program, location, v0, v1, v2, v3);
    }

    public static void glProgramUniform4iv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv3.glProgramUniform4iv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform4iv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv3.glProgramUniform4iv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniform4ui(int program, int location, int v0, int v1, int v2, int v3) {
        GLESv3.glProgramUniform4ui(program, location, v0, v1, v2, v3);
    }

    public static void glProgramUniform4uiv(int program, int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv3.glProgramUniform4uiv(program, location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniform4uiv(int program, int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv3.glProgramUniform4uiv(program, location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix2fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv3.glProgramUniformMatrix2fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix2fv(int program, int location, int count, boolean transpose, float[] value,
            int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv3.glProgramUniformMatrix2fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix2x3fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 24L)) {
            GLESv3.glProgramUniformMatrix2x3fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix2x3fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 24L)) {
            GLESv3.glProgramUniformMatrix2x3fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix2x4fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 32L)) {
            GLESv3.glProgramUniformMatrix2x4fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix2x4fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 32L)) {
            GLESv3.glProgramUniformMatrix2x4fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix3fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 36L)) {
            GLESv3.glProgramUniformMatrix3fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix3fv(int program, int location, int count, boolean transpose, float[] value,
            int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 36L)) {
            GLESv3.glProgramUniformMatrix3fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix3x2fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 24L)) {
            GLESv3.glProgramUniformMatrix3x2fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix3x2fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 24L)) {
            GLESv3.glProgramUniformMatrix3x2fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix3x4fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 48L)) {
            GLESv3.glProgramUniformMatrix3x4fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix3x4fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 48L)) {
            GLESv3.glProgramUniformMatrix3x4fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix4fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 64L)) {
            GLESv3.glProgramUniformMatrix4fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix4fv(int program, int location, int count, boolean transpose, float[] value,
            int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 64L)) {
            GLESv3.glProgramUniformMatrix4fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix4x2fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 32L)) {
            GLESv3.glProgramUniformMatrix4x2fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix4x2fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 32L)) {
            GLESv3.glProgramUniformMatrix4x2fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glProgramUniformMatrix4x3fv(int program, int location, int count, boolean transpose,
            FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 48L)) {
            GLESv3.glProgramUniformMatrix4x3fv(program, location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glProgramUniformMatrix4x3fv(int program, int location, int count, boolean transpose,
            float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 48L)) {
            GLESv3.glProgramUniformMatrix4x3fv(program, location, count, transpose,
                    Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glSampleMaski(int maskNumber, int mask) {
        GLESv3.glSampleMaski(maskNumber, mask);
    }

    public static void glTexStorage2DMultisample(int target, int samples, int internalformat, int width, int height,
            boolean fixedsamplelocations) {
        GLESv3.glTexStorage2DMultisample(target, samples, internalformat, width, height, fixedsamplelocations);
    }

    public static void glUseProgramStages(int pipeline, int stages, int program) {
        GLESv3.glUseProgramStages(pipeline, stages, program);
    }

    public static void glValidateProgramPipeline(int pipeline) {
        GLESv3.glValidateProgramPipeline(pipeline);
    }

    public static void glVertexAttribBinding(int attribindex, int bindingindex) {
        GLESv3.glVertexAttribBinding(attribindex, bindingindex);
    }

    public static void glVertexAttribFormat(int attribindex, int size, int type, boolean normalized,
            int relativeoffset) {
        GLESv3.glVertexAttribFormat(attribindex, size, type, normalized, relativeoffset);
    }

    public static void glVertexAttribIFormat(int attribindex, int size, int type, int relativeoffset) {
        GLESv3.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
    }

    public static void glVertexBindingDivisor(int bindingindex, int divisor) {
        GLESv3.glVertexBindingDivisor(bindingindex, divisor);
    }
}
