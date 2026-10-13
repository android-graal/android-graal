// Generated from android.jar and the NDK's GLES headers.
package android.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.androidgraal.android.CString;
import org.androidgraal.android.Pointer;
import org.androidgraal.gles.Counts;
import org.androidgraal.gles.GLESv2;
import org.androidgraal.gles.Handwritten;
import org.androidgraal.hosted.annotate.Emulated;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.c.type.CTypeConversion;

@Emulated
public class GLES20 {
    public static final int GL_ACTIVE_ATTRIBUTES = 0x8B89;

    public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 0x8B8A;

    public static final int GL_ACTIVE_TEXTURE = 0x84E0;

    public static final int GL_ACTIVE_UNIFORMS = 0x8B86;

    public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 0x8B87;

    public static final int GL_ALIASED_LINE_WIDTH_RANGE = 0x846E;

    public static final int GL_ALIASED_POINT_SIZE_RANGE = 0x846D;

    public static final int GL_ALPHA = 0x1906;

    public static final int GL_ALPHA_BITS = 0x0D55;

    public static final int GL_ALWAYS = 0x0207;

    public static final int GL_ARRAY_BUFFER = 0x8892;

    public static final int GL_ARRAY_BUFFER_BINDING = 0x8894;

    public static final int GL_ATTACHED_SHADERS = 0x8B85;

    public static final int GL_BACK = 0x0405;

    public static final int GL_BLEND = 0x0BE2;

    public static final int GL_BLEND_COLOR = 0x8005;

    public static final int GL_BLEND_DST_ALPHA = 0x80CA;

    public static final int GL_BLEND_DST_RGB = 0x80C8;

    public static final int GL_BLEND_EQUATION = 0x8009;

    public static final int GL_BLEND_EQUATION_ALPHA = 0x883D;

    public static final int GL_BLEND_EQUATION_RGB = 0x8009;

    public static final int GL_BLEND_SRC_ALPHA = 0x80CB;

    public static final int GL_BLEND_SRC_RGB = 0x80C9;

    public static final int GL_BLUE_BITS = 0x0D54;

    public static final int GL_BOOL = 0x8B56;

    public static final int GL_BOOL_VEC2 = 0x8B57;

    public static final int GL_BOOL_VEC3 = 0x8B58;

    public static final int GL_BOOL_VEC4 = 0x8B59;

    public static final int GL_BUFFER_SIZE = 0x8764;

    public static final int GL_BUFFER_USAGE = 0x8765;

    public static final int GL_BYTE = 0x1400;

    public static final int GL_CCW = 0x0901;

    public static final int GL_CLAMP_TO_EDGE = 0x812F;

    public static final int GL_COLOR_ATTACHMENT0 = 0x8CE0;

    public static final int GL_COLOR_BUFFER_BIT = 0x4000;

    public static final int GL_COLOR_CLEAR_VALUE = 0x0C22;

    public static final int GL_COLOR_WRITEMASK = 0x0C23;

    public static final int GL_COMPILE_STATUS = 0x8B81;

    public static final int GL_COMPRESSED_TEXTURE_FORMATS = 0x86A3;

    public static final int GL_CONSTANT_ALPHA = 0x8003;

    public static final int GL_CONSTANT_COLOR = 0x8001;

    public static final int GL_CULL_FACE = 0x0B44;

    public static final int GL_CULL_FACE_MODE = 0x0B45;

    public static final int GL_CURRENT_PROGRAM = 0x8B8D;

    public static final int GL_CURRENT_VERTEX_ATTRIB = 0x8626;

    public static final int GL_CW = 0x0900;

    public static final int GL_DECR = 0x1E03;

    public static final int GL_DECR_WRAP = 0x8508;

    public static final int GL_DELETE_STATUS = 0x8B80;

    public static final int GL_DEPTH_ATTACHMENT = 0x8D00;

    public static final int GL_DEPTH_BITS = 0x0D56;

    public static final int GL_DEPTH_BUFFER_BIT = 0x0100;

    public static final int GL_DEPTH_CLEAR_VALUE = 0x0B73;

    public static final int GL_DEPTH_COMPONENT = 0x1902;

    public static final int GL_DEPTH_COMPONENT16 = 0x81A5;

    public static final int GL_DEPTH_FUNC = 0x0B74;

    public static final int GL_DEPTH_RANGE = 0x0B70;

    public static final int GL_DEPTH_TEST = 0x0B71;

    public static final int GL_DEPTH_WRITEMASK = 0x0B72;

    public static final int GL_DITHER = 0x0BD0;

    public static final int GL_DONT_CARE = 0x1100;

    public static final int GL_DST_ALPHA = 0x0304;

    public static final int GL_DST_COLOR = 0x0306;

    public static final int GL_DYNAMIC_DRAW = 0x88E8;

    public static final int GL_ELEMENT_ARRAY_BUFFER = 0x8893;

    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING = 0x8895;

    public static final int GL_EQUAL = 0x0202;

    public static final int GL_EXTENSIONS = 0x1F03;

    public static final int GL_FALSE = 0;

    public static final int GL_FASTEST = 0x1101;

    public static final int GL_FIXED = 0x140C;

    public static final int GL_FLOAT = 0x1406;

    public static final int GL_FLOAT_MAT2 = 0x8B5A;

    public static final int GL_FLOAT_MAT3 = 0x8B5B;

    public static final int GL_FLOAT_MAT4 = 0x8B5C;

    public static final int GL_FLOAT_VEC2 = 0x8B50;

    public static final int GL_FLOAT_VEC3 = 0x8B51;

    public static final int GL_FLOAT_VEC4 = 0x8B52;

    public static final int GL_FRAGMENT_SHADER = 0x8B30;

    public static final int GL_FRAMEBUFFER = 0x8D40;

    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 0x8CD1;

    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 0x8CD0;

    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 0x8CD3;

    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 0x8CD2;

    public static final int GL_FRAMEBUFFER_BINDING = 0x8CA6;

    public static final int GL_FRAMEBUFFER_COMPLETE = 0x8CD5;

    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 0x8CD6;

    public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS = 0x8CD9;

    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 0x8CD7;

    public static final int GL_FRAMEBUFFER_UNSUPPORTED = 0x8CDD;

    public static final int GL_FRONT = 0x0404;

    public static final int GL_FRONT_AND_BACK = 0x0408;

    public static final int GL_FRONT_FACE = 0x0B46;

    public static final int GL_FUNC_ADD = 0x8006;

    public static final int GL_FUNC_REVERSE_SUBTRACT = 0x800B;

    public static final int GL_FUNC_SUBTRACT = 0x800A;

    public static final int GL_GENERATE_MIPMAP_HINT = 0x8192;

    public static final int GL_GEQUAL = 0x0206;

    public static final int GL_GREATER = 0x0204;

    public static final int GL_GREEN_BITS = 0x0D53;

    public static final int GL_HIGH_FLOAT = 0x8DF2;

    public static final int GL_HIGH_INT = 0x8DF5;

    public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 0x8B9B;

    public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 0x8B9A;

    public static final int GL_INCR = 0x1E02;

    public static final int GL_INCR_WRAP = 0x8507;

    public static final int GL_INFO_LOG_LENGTH = 0x8B84;

    public static final int GL_INT = 0x1404;

    public static final int GL_INT_VEC2 = 0x8B53;

    public static final int GL_INT_VEC3 = 0x8B54;

    public static final int GL_INT_VEC4 = 0x8B55;

    public static final int GL_INVALID_ENUM = 0x0500;

    public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 0x0506;

    public static final int GL_INVALID_OPERATION = 0x0502;

    public static final int GL_INVALID_VALUE = 0x0501;

    public static final int GL_INVERT = 0x150A;

    public static final int GL_KEEP = 0x1E00;

    public static final int GL_LEQUAL = 0x0203;

    public static final int GL_LESS = 0x0201;

    public static final int GL_LINEAR = 0x2601;

    public static final int GL_LINEAR_MIPMAP_LINEAR = 0x2703;

    public static final int GL_LINEAR_MIPMAP_NEAREST = 0x2701;

    public static final int GL_LINES = 1;

    public static final int GL_LINE_LOOP = 2;

    public static final int GL_LINE_STRIP = 3;

    public static final int GL_LINE_WIDTH = 0x0B21;

    public static final int GL_LINK_STATUS = 0x8B82;

    public static final int GL_LOW_FLOAT = 0x8DF0;

    public static final int GL_LOW_INT = 0x8DF3;

    public static final int GL_LUMINANCE = 0x1909;

    public static final int GL_LUMINANCE_ALPHA = 0x190A;

    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 0x8B4D;

    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 0x851C;

    public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 0x8DFD;

    public static final int GL_MAX_RENDERBUFFER_SIZE = 0x84E8;

    public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 0x8872;

    public static final int GL_MAX_TEXTURE_SIZE = 0x0D33;

    public static final int GL_MAX_VARYING_VECTORS = 0x8DFC;

    public static final int GL_MAX_VERTEX_ATTRIBS = 0x8869;

    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 0x8B4C;

    public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 0x8DFB;

    public static final int GL_MAX_VIEWPORT_DIMS = 0x0D3A;

    public static final int GL_MEDIUM_FLOAT = 0x8DF1;

    public static final int GL_MEDIUM_INT = 0x8DF4;

    public static final int GL_MIRRORED_REPEAT = 0x8370;

    public static final int GL_NEAREST = 0x2600;

    public static final int GL_NEAREST_MIPMAP_LINEAR = 0x2702;

    public static final int GL_NEAREST_MIPMAP_NEAREST = 0x2700;

    public static final int GL_NEVER = 0x0200;

    public static final int GL_NICEST = 0x1102;

    public static final int GL_NONE = 0;

    public static final int GL_NOTEQUAL = 0x0205;

    public static final int GL_NO_ERROR = 0;

    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 0x86A2;

    public static final int GL_NUM_SHADER_BINARY_FORMATS = 0x8DF9;

    public static final int GL_ONE = 1;

    public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 0x8004;

    public static final int GL_ONE_MINUS_CONSTANT_COLOR = 0x8002;

    public static final int GL_ONE_MINUS_DST_ALPHA = 0x0305;

    public static final int GL_ONE_MINUS_DST_COLOR = 0x0307;

    public static final int GL_ONE_MINUS_SRC_ALPHA = 0x0303;

    public static final int GL_ONE_MINUS_SRC_COLOR = 0x0301;

    public static final int GL_OUT_OF_MEMORY = 0x0505;

    public static final int GL_PACK_ALIGNMENT = 0x0D05;

    public static final int GL_POINTS = 0;

    public static final int GL_POLYGON_OFFSET_FACTOR = 0x8038;

    public static final int GL_POLYGON_OFFSET_FILL = 0x8037;

    public static final int GL_POLYGON_OFFSET_UNITS = 0x2A00;

    public static final int GL_RED_BITS = 0x0D52;

    public static final int GL_RENDERBUFFER = 0x8D41;

    public static final int GL_RENDERBUFFER_ALPHA_SIZE = 0x8D53;

    public static final int GL_RENDERBUFFER_BINDING = 0x8CA7;

    public static final int GL_RENDERBUFFER_BLUE_SIZE = 0x8D52;

    public static final int GL_RENDERBUFFER_DEPTH_SIZE = 0x8D54;

    public static final int GL_RENDERBUFFER_GREEN_SIZE = 0x8D51;

    public static final int GL_RENDERBUFFER_HEIGHT = 0x8D43;

    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 0x8D44;

    public static final int GL_RENDERBUFFER_RED_SIZE = 0x8D50;

    public static final int GL_RENDERBUFFER_STENCIL_SIZE = 0x8D55;

    public static final int GL_RENDERBUFFER_WIDTH = 0x8D42;

    public static final int GL_RENDERER = 0x1F01;

    public static final int GL_REPEAT = 0x2901;

    public static final int GL_REPLACE = 0x1E01;

    public static final int GL_RGB = 0x1907;

    public static final int GL_RGB565 = 0x8D62;

    public static final int GL_RGB5_A1 = 0x8057;

    public static final int GL_RGBA = 0x1908;

    public static final int GL_RGBA4 = 0x8056;

    public static final int GL_SAMPLER_2D = 0x8B5E;

    public static final int GL_SAMPLER_CUBE = 0x8B60;

    public static final int GL_SAMPLES = 0x80A9;

    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 0x809E;

    public static final int GL_SAMPLE_BUFFERS = 0x80A8;

    public static final int GL_SAMPLE_COVERAGE = 0x80A0;

    public static final int GL_SAMPLE_COVERAGE_INVERT = 0x80AB;

    public static final int GL_SAMPLE_COVERAGE_VALUE = 0x80AA;

    public static final int GL_SCISSOR_BOX = 0x0C10;

    public static final int GL_SCISSOR_TEST = 0x0C11;

    public static final int GL_SHADER_BINARY_FORMATS = 0x8DF8;

    public static final int GL_SHADER_COMPILER = 0x8DFA;

    public static final int GL_SHADER_SOURCE_LENGTH = 0x8B88;

    public static final int GL_SHADER_TYPE = 0x8B4F;

    public static final int GL_SHADING_LANGUAGE_VERSION = 0x8B8C;

    public static final int GL_SHORT = 0x1402;

    public static final int GL_SRC_ALPHA = 0x0302;

    public static final int GL_SRC_ALPHA_SATURATE = 0x0308;

    public static final int GL_SRC_COLOR = 0x0300;

    public static final int GL_STATIC_DRAW = 0x88E4;

    public static final int GL_STENCIL_ATTACHMENT = 0x8D20;

    public static final int GL_STENCIL_BACK_FAIL = 0x8801;

    public static final int GL_STENCIL_BACK_FUNC = 0x8800;

    public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 0x8802;

    public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 0x8803;

    public static final int GL_STENCIL_BACK_REF = 0x8CA3;

    public static final int GL_STENCIL_BACK_VALUE_MASK = 0x8CA4;

    public static final int GL_STENCIL_BACK_WRITEMASK = 0x8CA5;

    public static final int GL_STENCIL_BITS = 0x0D57;

    public static final int GL_STENCIL_BUFFER_BIT = 0x0400;

    public static final int GL_STENCIL_CLEAR_VALUE = 0x0B91;

    public static final int GL_STENCIL_FAIL = 0x0B94;

    public static final int GL_STENCIL_FUNC = 0x0B92;

    @Deprecated
    public static final int GL_STENCIL_INDEX = 0x1901;

    public static final int GL_STENCIL_INDEX8 = 0x8D48;

    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 0x0B95;

    public static final int GL_STENCIL_PASS_DEPTH_PASS = 0x0B96;

    public static final int GL_STENCIL_REF = 0x0B97;

    public static final int GL_STENCIL_TEST = 0x0B90;

    public static final int GL_STENCIL_VALUE_MASK = 0x0B93;

    public static final int GL_STENCIL_WRITEMASK = 0x0B98;

    public static final int GL_STREAM_DRAW = 0x88E0;

    public static final int GL_SUBPIXEL_BITS = 0x0D50;

    public static final int GL_TEXTURE = 0x1702;

    public static final int GL_TEXTURE0 = 0x84C0;

    public static final int GL_TEXTURE1 = 0x84C1;

    public static final int GL_TEXTURE10 = 0x84CA;

    public static final int GL_TEXTURE11 = 0x84CB;

    public static final int GL_TEXTURE12 = 0x84CC;

    public static final int GL_TEXTURE13 = 0x84CD;

    public static final int GL_TEXTURE14 = 0x84CE;

    public static final int GL_TEXTURE15 = 0x84CF;

    public static final int GL_TEXTURE16 = 0x84D0;

    public static final int GL_TEXTURE17 = 0x84D1;

    public static final int GL_TEXTURE18 = 0x84D2;

    public static final int GL_TEXTURE19 = 0x84D3;

    public static final int GL_TEXTURE2 = 0x84C2;

    public static final int GL_TEXTURE20 = 0x84D4;

    public static final int GL_TEXTURE21 = 0x84D5;

    public static final int GL_TEXTURE22 = 0x84D6;

    public static final int GL_TEXTURE23 = 0x84D7;

    public static final int GL_TEXTURE24 = 0x84D8;

    public static final int GL_TEXTURE25 = 0x84D9;

    public static final int GL_TEXTURE26 = 0x84DA;

    public static final int GL_TEXTURE27 = 0x84DB;

    public static final int GL_TEXTURE28 = 0x84DC;

    public static final int GL_TEXTURE29 = 0x84DD;

    public static final int GL_TEXTURE3 = 0x84C3;

    public static final int GL_TEXTURE30 = 0x84DE;

    public static final int GL_TEXTURE31 = 0x84DF;

    public static final int GL_TEXTURE4 = 0x84C4;

    public static final int GL_TEXTURE5 = 0x84C5;

    public static final int GL_TEXTURE6 = 0x84C6;

    public static final int GL_TEXTURE7 = 0x84C7;

    public static final int GL_TEXTURE8 = 0x84C8;

    public static final int GL_TEXTURE9 = 0x84C9;

    public static final int GL_TEXTURE_2D = 0x0DE1;

    public static final int GL_TEXTURE_BINDING_2D = 0x8069;

    public static final int GL_TEXTURE_BINDING_CUBE_MAP = 0x8514;

    public static final int GL_TEXTURE_CUBE_MAP = 0x8513;

    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 0x8516;

    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 0x8518;

    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 0x851A;

    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 0x8515;

    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 0x8517;

    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 0x8519;

    public static final int GL_TEXTURE_MAG_FILTER = 0x2800;

    public static final int GL_TEXTURE_MIN_FILTER = 0x2801;

    public static final int GL_TEXTURE_WRAP_S = 0x2802;

    public static final int GL_TEXTURE_WRAP_T = 0x2803;

    public static final int GL_TRIANGLES = 4;

    public static final int GL_TRIANGLE_FAN = 6;

    public static final int GL_TRIANGLE_STRIP = 5;

    public static final int GL_TRUE = 1;

    public static final int GL_UNPACK_ALIGNMENT = 0x0CF5;

    public static final int GL_UNSIGNED_BYTE = 0x1401;

    public static final int GL_UNSIGNED_INT = 0x1405;

    public static final int GL_UNSIGNED_SHORT = 0x1403;

    public static final int GL_UNSIGNED_SHORT_4_4_4_4 = 0x8033;

    public static final int GL_UNSIGNED_SHORT_5_5_5_1 = 0x8034;

    public static final int GL_UNSIGNED_SHORT_5_6_5 = 0x8363;

    public static final int GL_VALIDATE_STATUS = 0x8B83;

    public static final int GL_VENDOR = 0x1F00;

    public static final int GL_VERSION = 0x1F02;

    public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 0x889F;

    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 0x8622;

    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 0x886A;

    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 0x8645;

    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 0x8623;

    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 0x8624;

    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 0x8625;

    public static final int GL_VERTEX_SHADER = 0x8B31;

    public static final int GL_VIEWPORT = 0x0BA2;

    public static final int GL_ZERO = 0;

    public GLES20() {
    }

    public static void glActiveTexture(int texture) {
        GLESv2.glActiveTexture(texture);
    }

    public static void glAttachShader(int program, int shader) {
        GLESv2.glAttachShader(program, shader);
    }

    public static void glBindAttribLocation(int program, int index, String name) {
        try (CTypeConversion.CCharPointerHolder nameCString = CString.of(name)) {
            GLESv2.glBindAttribLocation(program, index, nameCString.get());
        }
    }

    public static void glBindBuffer(int target, int buffer) {
        GLESv2.glBindBuffer(target, buffer);
    }

    public static void glBindFramebuffer(int target, int framebuffer) {
        GLESv2.glBindFramebuffer(target, framebuffer);
    }

    public static void glBindRenderbuffer(int target, int renderbuffer) {
        GLESv2.glBindRenderbuffer(target, renderbuffer);
    }

    public static void glBindTexture(int target, int texture) {
        GLESv2.glBindTexture(target, texture);
    }

    public static void glBlendColor(float red, float green, float blue, float alpha) {
        GLESv2.glBlendColor(red, green, blue, alpha);
    }

    public static void glBlendEquation(int mode) {
        GLESv2.glBlendEquation(mode);
    }

    public static void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        GLESv2.glBlendEquationSeparate(modeRGB, modeAlpha);
    }

    public static void glBlendFunc(int sfactor, int dfactor) {
        GLESv2.glBlendFunc(sfactor, dfactor);
    }

    public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
        GLESv2.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }

    public static void glBufferData(int target, int size, Buffer data, int usage) {
        try (PinnedObject dataPin = Pointer.pinNullable(data, size)) {
            GLESv2.glBufferData(target, size, Pointer.address(data, dataPin), usage);
        }
    }

    public static void glBufferSubData(int target, int offset, int size, Buffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, size)) {
            GLESv2.glBufferSubData(target, offset, size, Pointer.address(data, dataPin));
        }
    }

    public static int glCheckFramebufferStatus(int target) {
        return GLESv2.glCheckFramebufferStatus(target);
    }

    public static void glClear(int mask) {
        GLESv2.glClear(mask);
    }

    public static void glClearColor(float red, float green, float blue, float alpha) {
        GLESv2.glClearColor(red, green, blue, alpha);
    }

    public static void glClearDepthf(float d) {
        GLESv2.glClearDepthf(d);
    }

    public static void glClearStencil(int s) {
        GLESv2.glClearStencil(s);
    }

    public static void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        GLESv2.glColorMask(red, green, blue, alpha);
    }

    public static void glCompileShader(int shader) {
        GLESv2.glCompileShader(shader);
    }

    public static void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height,
            int border, int imageSize, Buffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, imageSize)) {
            GLESv2.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize,
                    Pointer.address(data, dataPin));
        }
    }

    public static void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
            int format, int imageSize, Buffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, imageSize)) {
            GLESv2.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize,
                    Pointer.address(data, dataPin));
        }
    }

    public static void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height,
            int border) {
        GLESv2.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    public static void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width,
            int height) {
        GLESv2.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    public static int glCreateProgram() {
        return GLESv2.glCreateProgram();
    }

    public static int glCreateShader(int type) {
        return GLESv2.glCreateShader(type);
    }

    public static void glCullFace(int mode) {
        GLESv2.glCullFace(mode);
    }

    public static void glDeleteBuffers(int n, IntBuffer buffers) {
        try (PinnedObject buffersPin = Pointer.pin(buffers, n * 4L)) {
            GLESv2.glDeleteBuffers(n, Pointer.address(buffers, buffersPin));
        }
    }

    public static void glDeleteBuffers(int n, int[] buffers, int buffersOffset) {
        try (PinnedObject buffersPin = Pointer.pin(buffers, buffersOffset, n * 4L)) {
            GLESv2.glDeleteBuffers(n, Pointer.address(buffersPin, buffersOffset));
        }
    }

    public static void glDeleteFramebuffers(int n, IntBuffer framebuffers) {
        try (PinnedObject framebuffersPin = Pointer.pin(framebuffers, n * 4L)) {
            GLESv2.glDeleteFramebuffers(n, Pointer.address(framebuffers, framebuffersPin));
        }
    }

    public static void glDeleteFramebuffers(int n, int[] framebuffers, int framebuffersOffset) {
        try (PinnedObject framebuffersPin = Pointer.pin(framebuffers, framebuffersOffset, n * 4L)) {
            GLESv2.glDeleteFramebuffers(n, Pointer.address(framebuffersPin, framebuffersOffset));
        }
    }

    public static void glDeleteProgram(int program) {
        GLESv2.glDeleteProgram(program);
    }

    public static void glDeleteRenderbuffers(int n, IntBuffer renderbuffers) {
        try (PinnedObject renderbuffersPin = Pointer.pin(renderbuffers, n * 4L)) {
            GLESv2.glDeleteRenderbuffers(n, Pointer.address(renderbuffers, renderbuffersPin));
        }
    }

    public static void glDeleteRenderbuffers(int n, int[] renderbuffers, int renderbuffersOffset) {
        try (PinnedObject renderbuffersPin = Pointer.pin(renderbuffers, renderbuffersOffset, n * 4L)) {
            GLESv2.glDeleteRenderbuffers(n, Pointer.address(renderbuffersPin, renderbuffersOffset));
        }
    }

    public static void glDeleteShader(int shader) {
        GLESv2.glDeleteShader(shader);
    }

    public static void glDeleteTextures(int n, IntBuffer textures) {
        try (PinnedObject texturesPin = Pointer.pin(textures, n * 4L)) {
            GLESv2.glDeleteTextures(n, Pointer.address(textures, texturesPin));
        }
    }

    public static void glDeleteTextures(int n, int[] textures, int texturesOffset) {
        try (PinnedObject texturesPin = Pointer.pin(textures, texturesOffset, n * 4L)) {
            GLESv2.glDeleteTextures(n, Pointer.address(texturesPin, texturesOffset));
        }
    }

    public static void glDepthFunc(int func) {
        GLESv2.glDepthFunc(func);
    }

    public static void glDepthMask(boolean flag) {
        GLESv2.glDepthMask(flag);
    }

    public static void glDepthRangef(float n, float f) {
        GLESv2.glDepthRangef(n, f);
    }

    public static void glDetachShader(int program, int shader) {
        GLESv2.glDetachShader(program, shader);
    }

    public static void glDisable(int cap) {
        GLESv2.glDisable(cap);
    }

    public static void glDisableVertexAttribArray(int index) {
        GLESv2.glDisableVertexAttribArray(index);
    }

    public static void glDrawArrays(int mode, int first, int count) {
        GLESv2.glDrawArrays(mode, first, count);
    }

    public static void glDrawElements(int mode, int count, int type, int indicesOffset) {
        GLESv2.glDrawElements(mode, count, type, Pointer.address(indicesOffset));
    }

    public static void glDrawElements(int mode, int count, int type, Buffer indices) {
        try (PinnedObject indicesPin = Pointer.pin(indices, Counts.indexBytes(count, type))) {
            GLESv2.glDrawElements(mode, count, type, Pointer.address(indices, indicesPin));
        }
    }

    public static void glEnable(int cap) {
        GLESv2.glEnable(cap);
    }

    public static void glEnableVertexAttribArray(int index) {
        GLESv2.glEnableVertexAttribArray(index);
    }

    public static void glFinish() {
        GLESv2.glFinish();
    }

    public static void glFlush() {
        GLESv2.glFlush();
    }

    public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        GLESv2.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
    }

    public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        GLESv2.glFramebufferTexture2D(target, attachment, textarget, texture, level);
    }

    public static void glFrontFace(int mode) {
        GLESv2.glFrontFace(mode);
    }

    public static void glGenBuffers(int n, IntBuffer buffers) {
        try (PinnedObject buffersPin = Pointer.pin(buffers, n * 4L)) {
            GLESv2.glGenBuffers(n, Pointer.address(buffers, buffersPin));
        }
    }

    public static void glGenBuffers(int n, int[] buffers, int buffersOffset) {
        try (PinnedObject buffersPin = Pointer.pin(buffers, buffersOffset, n * 4L)) {
            GLESv2.glGenBuffers(n, Pointer.address(buffersPin, buffersOffset));
        }
    }

    public static void glGenFramebuffers(int n, IntBuffer framebuffers) {
        try (PinnedObject framebuffersPin = Pointer.pin(framebuffers, n * 4L)) {
            GLESv2.glGenFramebuffers(n, Pointer.address(framebuffers, framebuffersPin));
        }
    }

    public static void glGenFramebuffers(int n, int[] framebuffers, int framebuffersOffset) {
        try (PinnedObject framebuffersPin = Pointer.pin(framebuffers, framebuffersOffset, n * 4L)) {
            GLESv2.glGenFramebuffers(n, Pointer.address(framebuffersPin, framebuffersOffset));
        }
    }

    public static void glGenRenderbuffers(int n, IntBuffer renderbuffers) {
        try (PinnedObject renderbuffersPin = Pointer.pin(renderbuffers, n * 4L)) {
            GLESv2.glGenRenderbuffers(n, Pointer.address(renderbuffers, renderbuffersPin));
        }
    }

    public static void glGenRenderbuffers(int n, int[] renderbuffers, int renderbuffersOffset) {
        try (PinnedObject renderbuffersPin = Pointer.pin(renderbuffers, renderbuffersOffset, n * 4L)) {
            GLESv2.glGenRenderbuffers(n, Pointer.address(renderbuffersPin, renderbuffersOffset));
        }
    }

    public static void glGenTextures(int n, IntBuffer textures) {
        try (PinnedObject texturesPin = Pointer.pin(textures, n * 4L)) {
            GLESv2.glGenTextures(n, Pointer.address(textures, texturesPin));
        }
    }

    public static void glGenTextures(int n, int[] textures, int texturesOffset) {
        try (PinnedObject texturesPin = Pointer.pin(textures, texturesOffset, n * 4L)) {
            GLESv2.glGenTextures(n, Pointer.address(texturesPin, texturesOffset));
        }
    }

    public static void glGenerateMipmap(int target) {
        GLESv2.glGenerateMipmap(target);
    }

    public static void glGetActiveAttrib(int program, int index, int bufSize, int[] length, int lengthOffset,
            int[] size, int sizeOffset, int[] type, int typeOffset, byte[] name, int nameOffset) {
        try (PinnedObject lengthPin = Pointer.pin(length, lengthOffset, 4);
                PinnedObject sizePin = Pointer.pin(size, sizeOffset, 4);
                PinnedObject typePin = Pointer.pin(type, typeOffset, 4);
                PinnedObject namePin = Pointer.pin(name, nameOffset, bufSize)) {
            GLESv2.glGetActiveAttrib(program, index, bufSize, Pointer.address(lengthPin, lengthOffset),
                    Pointer.address(sizePin, sizeOffset), Pointer.address(typePin, typeOffset),
                    Pointer.address(namePin, nameOffset));
        }
    }

    public static String glGetActiveAttrib(int program, int index, IntBuffer size, IntBuffer type) {
        return Handwritten.glGetActiveAttrib(program, index, size, type);
    }

    public static String glGetActiveAttrib(int program, int index, int[] size, int sizeOffset, int[] type,
            int typeOffset) {
        return Handwritten.glGetActiveAttrib(program, index, size, sizeOffset, type, typeOffset);
    }

    public static void glGetActiveUniform(int program, int index, int bufSize, int[] length, int lengthOffset,
            int[] size, int sizeOffset, int[] type, int typeOffset, byte[] name, int nameOffset) {
        try (PinnedObject lengthPin = Pointer.pin(length, lengthOffset, 4);
                PinnedObject sizePin = Pointer.pin(size, sizeOffset, 4);
                PinnedObject typePin = Pointer.pin(type, typeOffset, 4);
                PinnedObject namePin = Pointer.pin(name, nameOffset, bufSize)) {
            GLESv2.glGetActiveUniform(program, index, bufSize, Pointer.address(lengthPin, lengthOffset),
                    Pointer.address(sizePin, sizeOffset), Pointer.address(typePin, typeOffset),
                    Pointer.address(namePin, nameOffset));
        }
    }

    public static String glGetActiveUniform(int program, int index, IntBuffer size, IntBuffer type) {
        return Handwritten.glGetActiveUniform(program, index, size, type);
    }

    public static String glGetActiveUniform(int program, int index, int[] size, int sizeOffset, int[] type,
            int typeOffset) {
        return Handwritten.glGetActiveUniform(program, index, size, sizeOffset, type, typeOffset);
    }

    public static void glGetAttachedShaders(int program, int maxCount, IntBuffer count, IntBuffer shaders) {
        try (PinnedObject countPin = Pointer.pinNullable(count, 4);
                PinnedObject shadersPin = Pointer.pin(shaders, maxCount * 4L)) {
            GLESv2.glGetAttachedShaders(program, maxCount, Pointer.address(count, countPin),
                    Pointer.address(shaders, shadersPin));
        }
    }

    public static void glGetAttachedShaders(int program, int maxCount, int[] count, int countOffset, int[] shaders,
            int shadersOffset) {
        try (PinnedObject countPin = Pointer.pinNullable(count, countOffset, 4);
                PinnedObject shadersPin = Pointer.pin(shaders, shadersOffset, maxCount * 4L)) {
            GLESv2.glGetAttachedShaders(program, maxCount, Pointer.address(countPin, countOffset),
                    Pointer.address(shadersPin, shadersOffset));
        }
    }

    public static int glGetAttribLocation(int program, String name) {
        try (CTypeConversion.CCharPointerHolder nameCString = CString.of(name)) {
            return GLESv2.glGetAttribLocation(program, nameCString.get());
        }
    }

    public static void glGetBooleanv(int pname, IntBuffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, Counts.stateCount(pname))) {
            GLESv2.glGetBooleanv(pname, Pointer.address(data, dataPin));
        }
    }

    public static void glGetBooleanv(int pname, boolean[] data, int dataOffset) {
        try (PinnedObject dataPin = Pointer.pin(data, dataOffset, Counts.stateCount(pname))) {
            GLESv2.glGetBooleanv(pname, Pointer.address(dataPin, dataOffset));
        }
    }

    public static void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetBufferParameteriv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetBufferParameteriv(int target, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetBufferParameteriv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static int glGetError() {
        return GLESv2.glGetError();
    }

    public static void glGetFloatv(int pname, FloatBuffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, Counts.stateCount(pname) * 4L)) {
            GLESv2.glGetFloatv(pname, Pointer.address(data, dataPin));
        }
    }

    public static void glGetFloatv(int pname, float[] data, int dataOffset) {
        try (PinnedObject dataPin = Pointer.pin(data, dataOffset, Counts.stateCount(pname) * 4L)) {
            GLESv2.glGetFloatv(pname, Pointer.address(dataPin, dataOffset));
        }
    }

    public static void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetFramebufferAttachmentParameteriv(target, attachment, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, int[] params,
            int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetFramebufferAttachmentParameteriv(target, attachment, pname,
                    Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetIntegerv(int pname, IntBuffer data) {
        try (PinnedObject dataPin = Pointer.pin(data, Counts.stateCount(pname) * 4L)) {
            GLESv2.glGetIntegerv(pname, Pointer.address(data, dataPin));
        }
    }

    public static void glGetIntegerv(int pname, int[] data, int dataOffset) {
        try (PinnedObject dataPin = Pointer.pin(data, dataOffset, Counts.stateCount(pname) * 4L)) {
            GLESv2.glGetIntegerv(pname, Pointer.address(dataPin, dataOffset));
        }
    }

    public static String glGetProgramInfoLog(int program) {
        return Handwritten.glGetProgramInfoLog(program);
    }

    public static void glGetProgramiv(int program, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.programCount(pname) * 4L)) {
            GLESv2.glGetProgramiv(program, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetProgramiv(int program, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.programCount(pname) * 4L)) {
            GLESv2.glGetProgramiv(program, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetRenderbufferParameteriv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetRenderbufferParameteriv(int target, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetRenderbufferParameteriv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static String glGetShaderInfoLog(int shader) {
        return Handwritten.glGetShaderInfoLog(shader);
    }

    public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range,
            IntBuffer precision) {
        try (PinnedObject rangePin = Pointer.pin(range, 8); PinnedObject precisionPin = Pointer.pin(precision, 4)) {
            GLESv2.glGetShaderPrecisionFormat(shadertype, precisiontype, Pointer.address(range, rangePin),
                    Pointer.address(precision, precisionPin));
        }
    }

    public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype, int[] range, int rangeOffset,
            int[] precision, int precisionOffset) {
        try (PinnedObject rangePin = Pointer.pin(range, rangeOffset, 8);
                PinnedObject precisionPin = Pointer.pin(precision, precisionOffset, 4)) {
            GLESv2.glGetShaderPrecisionFormat(shadertype, precisiontype, Pointer.address(rangePin, rangeOffset),
                    Pointer.address(precisionPin, precisionOffset));
        }
    }

    public static String glGetShaderSource(int shader) {
        return Handwritten.glGetShaderSource(shader);
    }

    public static void glGetShaderSource(int shader, int bufSize, int[] length, int lengthOffset, byte[] source,
            int sourceOffset) {
        try (PinnedObject lengthPin = Pointer.pin(length, lengthOffset, 4);
                PinnedObject sourcePin = Pointer.pin(source, sourceOffset, bufSize)) {
            GLESv2.glGetShaderSource(shader, bufSize, Pointer.address(lengthPin, lengthOffset),
                    Pointer.address(sourcePin, sourceOffset));
        }
    }

    public static void glGetShaderiv(int shader, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetShaderiv(shader, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetShaderiv(int shader, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetShaderiv(shader, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static String glGetString(int name) {
        return CString.toJava(GLESv2.glGetString(name));
    }

    public static void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glGetTexParameterfv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetTexParameterfv(int target, int pname, float[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glGetTexParameterfv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glGetTexParameteriv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetTexParameteriv(int target, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glGetTexParameteriv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static int glGetUniformLocation(int program, String name) {
        try (CTypeConversion.CCharPointerHolder nameCString = CString.of(name)) {
            return GLESv2.glGetUniformLocation(program, nameCString.get());
        }
    }

    public static void glGetUniformfv(int program, int location, FloatBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetUniformfv(program, location, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetUniformfv(int program, int location, float[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetUniformfv(program, location, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetUniformiv(int program, int location, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, 4)) {
            GLESv2.glGetUniformiv(program, location, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetUniformiv(int program, int location, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, 4)) {
            GLESv2.glGetUniformiv(program, location, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.vertexAttribCount(pname) * 4L)) {
            GLESv2.glGetVertexAttribfv(index, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetVertexAttribfv(int index, int pname, float[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.vertexAttribCount(pname) * 4L)) {
            GLESv2.glGetVertexAttribfv(index, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.vertexAttribCount(pname) * 4L)) {
            GLESv2.glGetVertexAttribiv(index, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glGetVertexAttribiv(int index, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.vertexAttribCount(pname) * 4L)) {
            GLESv2.glGetVertexAttribiv(index, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glHint(int target, int mode) {
        GLESv2.glHint(target, mode);
    }

    public static boolean glIsBuffer(int buffer) {
        return GLESv2.glIsBuffer(buffer);
    }

    public static boolean glIsEnabled(int cap) {
        return GLESv2.glIsEnabled(cap);
    }

    public static boolean glIsFramebuffer(int framebuffer) {
        return GLESv2.glIsFramebuffer(framebuffer);
    }

    public static boolean glIsProgram(int program) {
        return GLESv2.glIsProgram(program);
    }

    public static boolean glIsRenderbuffer(int renderbuffer) {
        return GLESv2.glIsRenderbuffer(renderbuffer);
    }

    public static boolean glIsShader(int shader) {
        return GLESv2.glIsShader(shader);
    }

    public static boolean glIsTexture(int texture) {
        return GLESv2.glIsTexture(texture);
    }

    public static void glLineWidth(float width) {
        GLESv2.glLineWidth(width);
    }

    public static void glLinkProgram(int program) {
        GLESv2.glLinkProgram(program);
    }

    public static void glPixelStorei(int pname, int param) {
        GLESv2.glPixelStorei(pname, param);
    }

    public static void glPolygonOffset(float factor, float units) {
        GLESv2.glPolygonOffset(factor, units);
    }

    public static void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        try (PinnedObject pixelsPin = Pointer.pin(pixels)) {
            GLESv2.glReadPixels(x, y, width, height, format, type, Pointer.address(pixels, pixelsPin));
        }
    }

    public static void glReleaseShaderCompiler() {
        GLESv2.glReleaseShaderCompiler();
    }

    public static void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        GLESv2.glRenderbufferStorage(target, internalformat, width, height);
    }

    public static void glSampleCoverage(float value, boolean invert) {
        GLESv2.glSampleCoverage(value, invert);
    }

    public static void glScissor(int x, int y, int width, int height) {
        GLESv2.glScissor(x, y, width, height);
    }

    public static void glShaderBinary(int count, IntBuffer shaders, int binaryformat, Buffer binary, int length) {
        try (PinnedObject shadersPin = Pointer.pin(shaders, count * 4L);
                PinnedObject binaryPin = Pointer.pin(binary, length)) {
            GLESv2.glShaderBinary(count, Pointer.address(shaders, shadersPin), binaryformat,
                    Pointer.address(binary, binaryPin), length);
        }
    }

    public static void glShaderBinary(int count, int[] shaders, int shadersOffset, int binaryformat, Buffer binary,
            int length) {
        try (PinnedObject shadersPin = Pointer.pin(shaders, shadersOffset, count * 4L);
                PinnedObject binaryPin = Pointer.pin(binary, length)) {
            GLESv2.glShaderBinary(count, Pointer.address(shadersPin, shadersOffset), binaryformat,
                    Pointer.address(binary, binaryPin), length);
        }
    }

    public static void glShaderSource(int shader, String string) {
        Handwritten.glShaderSource(shader, string);
    }

    public static void glStencilFunc(int func, int ref, int mask) {
        GLESv2.glStencilFunc(func, ref, mask);
    }

    public static void glStencilFuncSeparate(int face, int func, int ref, int mask) {
        GLESv2.glStencilFuncSeparate(face, func, ref, mask);
    }

    public static void glStencilMask(int mask) {
        GLESv2.glStencilMask(mask);
    }

    public static void glStencilMaskSeparate(int face, int mask) {
        GLESv2.glStencilMaskSeparate(face, mask);
    }

    public static void glStencilOp(int fail, int zfail, int zpass) {
        GLESv2.glStencilOp(fail, zfail, zpass);
    }

    public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
        GLESv2.glStencilOpSeparate(face, sfail, dpfail, dppass);
    }

    public static void glTexImage2D(int target, int level, int internalformat, int width, int height, int border,
            int format, int type, Buffer pixels) {
        try (PinnedObject pixelsPin = Pointer.pinNullable(pixels)) {
            GLESv2.glTexImage2D(target, level, internalformat, width, height, border, format, type,
                    Pointer.address(pixels, pixelsPin));
        }
    }

    public static void glTexParameterf(int target, int pname, float param) {
        GLESv2.glTexParameterf(target, pname, param);
    }

    public static void glTexParameterfv(int target, int pname, FloatBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glTexParameterfv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glTexParameterfv(int target, int pname, float[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glTexParameterfv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glTexParameteri(int target, int pname, int param) {
        GLESv2.glTexParameteri(target, pname, param);
    }

    public static void glTexParameteriv(int target, int pname, IntBuffer params) {
        try (PinnedObject paramsPin = Pointer.pin(params, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glTexParameteriv(target, pname, Pointer.address(params, paramsPin));
        }
    }

    public static void glTexParameteriv(int target, int pname, int[] params, int paramsOffset) {
        try (PinnedObject paramsPin = Pointer.pin(params, paramsOffset, Counts.parameterCount(pname) * 4L)) {
            GLESv2.glTexParameteriv(target, pname, Pointer.address(paramsPin, paramsOffset));
        }
    }

    public static void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height,
            int format, int type, Buffer pixels) {
        try (PinnedObject pixelsPin = Pointer.pinNullable(pixels)) {
            GLESv2.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type,
                    Pointer.address(pixels, pixelsPin));
        }
    }

    public static void glUniform1f(int location, float v0) {
        GLESv2.glUniform1f(location, v0);
    }

    public static void glUniform1fv(int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 4L)) {
            GLESv2.glUniform1fv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform1fv(int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 4L)) {
            GLESv2.glUniform1fv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform1i(int location, int v0) {
        GLESv2.glUniform1i(location, v0);
    }

    public static void glUniform1iv(int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 4L)) {
            GLESv2.glUniform1iv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform1iv(int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 4L)) {
            GLESv2.glUniform1iv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform2f(int location, float v0, float v1) {
        GLESv2.glUniform2f(location, v0, v1);
    }

    public static void glUniform2fv(int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 8L)) {
            GLESv2.glUniform2fv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform2fv(int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 8L)) {
            GLESv2.glUniform2fv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform2i(int location, int v0, int v1) {
        GLESv2.glUniform2i(location, v0, v1);
    }

    public static void glUniform2iv(int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 8L)) {
            GLESv2.glUniform2iv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform2iv(int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 8L)) {
            GLESv2.glUniform2iv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform3f(int location, float v0, float v1, float v2) {
        GLESv2.glUniform3f(location, v0, v1, v2);
    }

    public static void glUniform3fv(int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 12L)) {
            GLESv2.glUniform3fv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform3fv(int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 12L)) {
            GLESv2.glUniform3fv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform3i(int location, int v0, int v1, int v2) {
        GLESv2.glUniform3i(location, v0, v1, v2);
    }

    public static void glUniform3iv(int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 12L)) {
            GLESv2.glUniform3iv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform3iv(int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 12L)) {
            GLESv2.glUniform3iv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform4f(int location, float v0, float v1, float v2, float v3) {
        GLESv2.glUniform4f(location, v0, v1, v2, v3);
    }

    public static void glUniform4fv(int location, int count, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv2.glUniform4fv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform4fv(int location, int count, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv2.glUniform4fv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniform4i(int location, int v0, int v1, int v2, int v3) {
        GLESv2.glUniform4i(location, v0, v1, v2, v3);
    }

    public static void glUniform4iv(int location, int count, IntBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv2.glUniform4iv(location, count, Pointer.address(value, valuePin));
        }
    }

    public static void glUniform4iv(int location, int count, int[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv2.glUniform4iv(location, count, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 16L)) {
            GLESv2.glUniformMatrix2fv(location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 16L)) {
            GLESv2.glUniformMatrix2fv(location, count, transpose, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 36L)) {
            GLESv2.glUniformMatrix3fv(location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 36L)) {
            GLESv2.glUniformMatrix3fv(location, count, transpose, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
        try (PinnedObject valuePin = Pointer.pin(value, count * 64L)) {
            GLESv2.glUniformMatrix4fv(location, count, transpose, Pointer.address(value, valuePin));
        }
    }

    public static void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int valueOffset) {
        try (PinnedObject valuePin = Pointer.pin(value, valueOffset, count * 64L)) {
            GLESv2.glUniformMatrix4fv(location, count, transpose, Pointer.address(valuePin, valueOffset));
        }
    }

    public static void glUseProgram(int program) {
        GLESv2.glUseProgram(program);
    }

    public static void glValidateProgram(int program) {
        GLESv2.glValidateProgram(program);
    }

    public static void glVertexAttrib1f(int index, float x) {
        GLESv2.glVertexAttrib1f(index, x);
    }

    public static void glVertexAttrib1fv(int index, FloatBuffer v) {
        try (PinnedObject vPin = Pointer.pin(v, 4)) {
            GLESv2.glVertexAttrib1fv(index, Pointer.address(v, vPin));
        }
    }

    public static void glVertexAttrib1fv(int index, float[] v, int vOffset) {
        try (PinnedObject vPin = Pointer.pin(v, vOffset, 4)) {
            GLESv2.glVertexAttrib1fv(index, Pointer.address(vPin, vOffset));
        }
    }

    public static void glVertexAttrib2f(int index, float x, float y) {
        GLESv2.glVertexAttrib2f(index, x, y);
    }

    public static void glVertexAttrib2fv(int index, FloatBuffer v) {
        try (PinnedObject vPin = Pointer.pin(v, 8)) {
            GLESv2.glVertexAttrib2fv(index, Pointer.address(v, vPin));
        }
    }

    public static void glVertexAttrib2fv(int index, float[] v, int vOffset) {
        try (PinnedObject vPin = Pointer.pin(v, vOffset, 8)) {
            GLESv2.glVertexAttrib2fv(index, Pointer.address(vPin, vOffset));
        }
    }

    public static void glVertexAttrib3f(int index, float x, float y, float z) {
        GLESv2.glVertexAttrib3f(index, x, y, z);
    }

    public static void glVertexAttrib3fv(int index, FloatBuffer v) {
        try (PinnedObject vPin = Pointer.pin(v, 12)) {
            GLESv2.glVertexAttrib3fv(index, Pointer.address(v, vPin));
        }
    }

    public static void glVertexAttrib3fv(int index, float[] v, int vOffset) {
        try (PinnedObject vPin = Pointer.pin(v, vOffset, 12)) {
            GLESv2.glVertexAttrib3fv(index, Pointer.address(vPin, vOffset));
        }
    }

    public static void glVertexAttrib4f(int index, float x, float y, float z, float w) {
        GLESv2.glVertexAttrib4f(index, x, y, z, w);
    }

    public static void glVertexAttrib4fv(int index, FloatBuffer v) {
        try (PinnedObject vPin = Pointer.pin(v, 16)) {
            GLESv2.glVertexAttrib4fv(index, Pointer.address(v, vPin));
        }
    }

    public static void glVertexAttrib4fv(int index, float[] v, int vOffset) {
        try (PinnedObject vPin = Pointer.pin(v, vOffset, 16)) {
            GLESv2.glVertexAttrib4fv(index, Pointer.address(vPin, vOffset));
        }
    }

    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride,
            int pointerOffset) {
        GLESv2.glVertexAttribPointer(index, size, type, normalized, stride, Pointer.address(pointerOffset));
    }

    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride,
            Buffer pointer) {
        GLESv2.glVertexAttribPointer(index, size, type, normalized, stride, Pointer.direct(pointer));
    }

    public static void glViewport(int x, int y, int width, int height) {
        GLESv2.glViewport(x, y, width, height);
    }
}
