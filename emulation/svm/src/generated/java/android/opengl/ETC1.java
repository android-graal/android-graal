package android.opengl;

/**
 * Methods for encoding and decoding ETC1 textures.
 * <p>
 * The standard for the ETC1 texture format can be found at
 * http://www.khronos.org/registry/gles/extensions/OES/OES_compressed_ETC1_RGB8_texture.txt
 * <p>
 * The PKM file format is of a 16-byte header that describes the image bounds
 * followed by the encoded ETC1 texture data.
 * <p>
 * @see ETC1Util
 */
public final class ETC1 {
    /**
     * Size in bytes of a decoded block.
     */
    public static final int DECODED_BLOCK_SIZE = 48;

    /**
     * Size in bytes of an encoded block.
     */
    public static final int ENCODED_BLOCK_SIZE = 8;

    /**
     * Accepted by the internalformat parameter of glCompressedTexImage2D.
     */
    public static final int ETC1_RGB8_OES = 36196;

    /**
     * Size of a PKM file header, in bytes.
     */
    public static final int ETC_PKM_HEADER_SIZE = 16;

    private ETC1() {
    }
}
