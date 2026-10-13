package android.graphics;

import android.os.Parcelable;

/**
 * Gainmap represents a mechanism for augmenting an SDR image to produce an HDR one with variable
 * display adjustment capability. It is a combination of a set of metadata describing how to apply
 * the gainmap, as well as either a 1 (such as {@link android.graphics.Bitmap.Config#ALPHA_8} or 3
 * (such as {@link android.graphics.Bitmap.Config#ARGB_8888} with the alpha channel ignored)
 * channel Bitmap that represents the gainmap data itself.
 * <p>
 * When rendering to an {@link android.content.pm.ActivityInfo#COLOR_MODE_HDR} activity, the
 * hardware accelerated {@link Canvas} will automatically apply the gainmap when sufficient
 * HDR headroom is available.
 *
 * <h3>Gainmap Structure</h3>
 *
 * The logical whole of a gainmap'd image consists of a base Bitmap that represents the original
 * image as would be displayed without gainmap support in addition to a gainmap with a second
 * enhancement image. In the case of a JPEG, the base image would be the typical 8-bit SDR image
 * that the format is commonly associated with. The gainmap image is embedded alongside the base
 * image, often at a lower resolution (such as 1/4th), along with some metadata to describe
 * how to apply the gainmap. The gainmap image itself is then a greyscale image representing
 * the transformation to apply onto the base image to reconstruct an HDR rendition of it.
 * <p>
 * As such these "gainmap images" consist of 3 parts - a base {@link Bitmap} with a
 * {@link Bitmap#getGainmap()} that returns an instance of this class which in turn contains
 * the enhancement layer represented as another Bitmap, accessible via {@link #getGainmapContents()}
 *
 * <h3>Applying a gainmap manually</h3>
 *
 * When doing custom rendering such as to an OpenGL ES or Vulkan context, the gainmap is not
 * automatically applied. In such situations, the following steps are appropriate to render the
 * gainmap in combination with the base image.
 * <p>
 * Suppose our display has HDR to SDR ratio of H, and we wish to display an image with gainmap on
 * this display. Let B be the pixel value from the base image in a color space that has the
 * primaries of the base image and a linear transfer function. Let G be the pixel value from the
 * gainmap. Let D be the output pixel in the same color space as B. The value of D is computed
 * as follows:
 * <p>
 * First, let W be a weight parameter determining how much the gainmap will be applied.
 * <pre class="prettyprint">
 *   W = clamp((log(H)                      - log(minDisplayRatioForHdrTransition)) /
 *             (log(displayRatioForFullHdr) - log(minDisplayRatioForHdrTransition), 0, 1)</pre>
 *
 * Next, let L be the gainmap value in log space. We compute this from the value G that was
 * sampled from the texture as follows:
 * <pre class="prettyprint">
 *   L = mix(log(ratioMin), log(ratioMax), pow(G, gamma))</pre>
 * Finally, apply the gainmap to compute D, the displayed pixel. If the base image is SDR then
 * compute:
 * <pre class="prettyprint">
 *   D = (B + epsilonSdr) * exp(L * W) - epsilonHdr</pre>
 * <p>
 * In the above math, log() is a natural logarithm and exp() is natural exponentiation. The base
 * for these functions cancels out and does not affect the result, so other bases may be used
 * if preferred.
 */
public final class Gainmap implements Parcelable {
    /**
     * The gainmap will be applied as if the base image were HDR, and fully applying the gainmap
     * results in an SDR image.
     */
    public static final int GAINMAP_DIRECTION_HDR_TO_SDR = 1;

    /**
     * The gainmap will be applied as if the base image were SDR, and fully applying the gainmap
     * results in an HDR image.
     */
    public static final int GAINMAP_DIRECTION_SDR_TO_HDR = 0;

    private Gainmap() {
    }
}
