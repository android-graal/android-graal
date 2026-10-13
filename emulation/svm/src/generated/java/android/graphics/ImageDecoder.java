package android.graphics;

/**
 *  <p>A class for converting encoded images (like {@code PNG}, {@code JPEG},
 *  {@code WEBP}, {@code GIF}, or {@code HEIF}) into {@link Drawable} or
 *  {@link Bitmap} objects.
 *
 *  <p>To use it, first create a {@link Source Source} using one of the
 *  {@code createSource} overloads. For example, to decode from a {@link Uri}, call
 *  {@link #createSource(ContentResolver, Uri)} and pass the result to
 *  {@link #decodeDrawable(Source)} or {@link #decodeBitmap(Source)}:
 *
 *  <pre class="prettyprint">
 *  File file = new File(...);
 *  ImageDecoder.Source source = ImageDecoder.createSource(file);
 *  Drawable drawable = ImageDecoder.decodeDrawable(source);
 *  </pre>
 *
 *  <p>To change the default settings, pass the {@link Source Source} and an
 *  {@link OnHeaderDecodedListener OnHeaderDecodedListener} to
 *  {@link #decodeDrawable(Source, OnHeaderDecodedListener)} or
 *  {@link #decodeBitmap(Source, OnHeaderDecodedListener)}. For example, to
 *  create a sampled image with half the width and height of the original image,
 *  call {@link #setTargetSampleSize setTargetSampleSize(2)} inside
 *  {@link OnHeaderDecodedListener#onHeaderDecoded onHeaderDecoded}:
 *
 *  <pre class="prettyprint">
 *  OnHeaderDecodedListener listener = new OnHeaderDecodedListener() {
 *      public void onHeaderDecoded(ImageDecoder decoder, ImageInfo info, Source source) {
 *          decoder.setTargetSampleSize(2);
 *      }
 *  };
 *  Drawable drawable = ImageDecoder.decodeDrawable(source, listener);
 *  </pre>
 *
 *  <p>The {@link ImageInfo ImageInfo} contains information about the encoded image, like
 *  its width and height, and the {@link Source Source} can be used to match to a particular
 *  {@link Source Source} if a single {@link OnHeaderDecodedListener OnHeaderDecodedListener}
 *  is used with multiple {@link Source Source} objects.
 *
 *  <p>The {@link OnHeaderDecodedListener OnHeaderDecodedListener} can also be implemented
 *  as a lambda:
 *
 *  <pre class="prettyprint">
 *  Drawable drawable = ImageDecoder.decodeDrawable(source, (decoder, info, src) -&gt; {
 *      decoder.setTargetSampleSize(2);
 *  });
 *  </pre>
 *
 *  <p>If the encoded image is an animated {@code GIF} or {@code WEBP},
 *  {@link #decodeDrawable decodeDrawable} will return an {@link AnimatedImageDrawable}. To
 *  start its animation, call {@link AnimatedImageDrawable#start AnimatedImageDrawable.start()}:
 *
 *  <pre class="prettyprint">
 *  Drawable drawable = ImageDecoder.decodeDrawable(source);
 *  if (drawable instanceof AnimatedImageDrawable) {
 *      ((AnimatedImageDrawable) drawable).start();
 *  }
 *  </pre>
 *
 *  <p>By default, a {@link Bitmap} created by {@link ImageDecoder} (including
 *  one that is inside a {@link Drawable}) will be immutable (i.e.
 *  {@link Bitmap#isMutable Bitmap.isMutable()} returns {@code false}), and it
 *  will typically have {@code Config} {@link Bitmap.Config#HARDWARE}. Although
 *  these properties can be changed with {@link #setMutableRequired setMutableRequired(true)}
 *  (which is only compatible with {@link #decodeBitmap(Source)} and
 *  {@link #decodeBitmap(Source, OnHeaderDecodedListener)}) and {@link #setAllocator},
 *  it is also possible to apply custom effects regardless of the mutability of
 *  the final returned object by passing a {@link PostProcessor} to
 *  {@link #setPostProcessor setPostProcessor}. A {@link PostProcessor} can also be a lambda:
 *
 *  <pre class="prettyprint">
 *  Drawable drawable = ImageDecoder.decodeDrawable(source, (decoder, info, src) -&gt; {
 *      decoder.setPostProcessor((canvas) -&gt; {
 *              // This will create rounded corners.
 *              Path path = new Path();
 *              path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
 *              int width = canvas.getWidth();
 *              int height = canvas.getHeight();
 *              path.addRoundRect(0, 0, width, height, 20, 20, Path.Direction.CW);
 *              Paint paint = new Paint();
 *              paint.setAntiAlias(true);
 *              paint.setColor(Color.TRANSPARENT);
 *              paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
 *              canvas.drawPath(path, paint);
 *              return PixelFormat.TRANSLUCENT;
 *      });
 *  });
 *  </pre>
 *
 *  <p>If the encoded image is incomplete or contains an error, or if an
 *  {@link Exception} occurs during decoding, a {@link DecodeException DecodeException}
 *  will be thrown. In some cases, the {@link ImageDecoder} may have decoded part of
 *  the image. In order to display the partial image, an
 *  {@link OnPartialImageListener OnPartialImageListener} must be passed to
 *  {@link #setOnPartialImageListener setOnPartialImageListener}. For example:
 *
 *  <pre class="prettyprint">
 *  Drawable drawable = ImageDecoder.decodeDrawable(source, (decoder, info, src) -&gt; {
 *      decoder.setOnPartialImageListener((DecodeException e) -&gt; {
 *              // Returning true indicates to create a Drawable or Bitmap even
 *              // if the whole image could not be decoded. Any remaining lines
 *              // will be blank.
 *              return true;
 *      });
 *  });
 *  </pre>
 */
public final class ImageDecoder {
    /**
     *  Use the default allocation for the pixel memory.
     *
     *  Will typically result in a {@link Bitmap.Config#HARDWARE}
     *  allocation, but may be software for small images. In addition, this will
     *  switch to software when HARDWARE is incompatible, e.g.
     *  {@link #setMutableRequired setMutableRequired(true)} or
     *  {@link #setDecodeAsAlphaMaskEnabled setDecodeAsAlphaMaskEnabled(true)}.
     */
    public static final int ALLOCATOR_DEFAULT = 0;

    /**
     *  Require a {@link Bitmap.Config#HARDWARE} {@link Bitmap}.
     *
     *  <p>When this is combined with incompatible options, like
     *  {@link #setMutableRequired setMutableRequired(true)} or
     *  {@link #setDecodeAsAlphaMaskEnabled setDecodeAsAlphaMaskEnabled(true)},
     *  {@link #decodeDrawable decodeDrawable} or {@link #decodeBitmap decodeBitmap}
     *  will throw an {@link java.lang.IllegalStateException}.
     */
    public static final int ALLOCATOR_HARDWARE = 3;

    /**
     *  Use shared memory for the pixel memory.
     *
     *  <p>Useful for sharing across processes.
     */
    public static final int ALLOCATOR_SHARED_MEMORY = 2;

    /**
     *  Use a software allocation for the pixel memory.
     *
     *  <p>Useful for drawing to a software {@link Canvas} or for
     *  accessing the pixels on the final output.
     */
    public static final int ALLOCATOR_SOFTWARE = 1;

    /**
     * Use the most natural {@link Bitmap.Config} for the internal {@link Bitmap}.
     *
     * <p>This is the recommended default for most applications and usages. This
     * will use the closest {@link Bitmap.Config} for the encoded source. If the
     * encoded source does not exactly match any {@link Bitmap.Config}, the next
     * highest quality {@link Bitmap.Config} will be used avoiding any loss in
     * image quality.
     */
    public static final int MEMORY_POLICY_DEFAULT = 1;

    /**
     * Save memory if possible by using a denser {@link Bitmap.Config} at the
     * cost of some image quality.
     *
     * <p>For example an opaque 8-bit image may be compressed into an
     * {@link Bitmap.Config#RGB_565} configuration, sacrificing image
     * quality to save memory.
     */
    public static final int MEMORY_POLICY_LOW_RAM = 0;

    private ImageDecoder() {
    }

    /**
     *  Information about an interrupted decode.
     */
    public static final class DecodeException {
        /**
         *  An Exception was thrown reading the {@link Source}.
         */
        public static final int SOURCE_EXCEPTION = 1;

        /**
         *  The encoded data was incomplete.
         */
        public static final int SOURCE_INCOMPLETE = 2;

        /**
         *  The encoded data contained an error.
         */
        public static final int SOURCE_MALFORMED_DATA = 3;

        private DecodeException() {
        }
    }
}
