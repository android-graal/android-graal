package android.text.util;

/**
 *  Linkify take a piece of text and a regular expression and turns all of the
 *  regex matches in the text into clickable links.  This is particularly
 *  useful for matching things like email addresses, web URLs, etc. and making
 *  them actionable.
 *
 *  Alone with the pattern that is to be matched, a URL scheme prefix is also
 *  required.  Any pattern match that does not begin with the supplied scheme
 *  will have the scheme prepended to the matched text when the clickable URL
 *  is created.  For instance, if you are matching web URLs you would supply
 *  the scheme <code>http://</code>. If the pattern matches example.com, which
 *  does not have a URL scheme prefix, the supplied scheme will be prepended to
 *  create <code>http://example.com</code> when the clickable URL link is
 *  created.
 *
 *  <p class="note"><b>Note:</b> When using {@link #MAP_ADDRESSES} or {@link #ALL}
 *  to match street addresses on API level {@link android.os.Build.VERSION_CODES#O_MR1}
 *  and earlier, methods in this class may throw
 *  {@link android.util.AndroidRuntimeException} or other exceptions if the
 *  device's WebView implementation is currently being updated, because
 *  {@link android.webkit.WebView#findAddress} is required to match street
 *  addresses.
 *
 * @see MatchFilter
 * @see TransformFilter
 */
public final class Linkify {
    /**
     *  Bit mask indicating that all available patterns should be matched in methods
     *  that take an options mask. Note that this should be avoided, as the {@link
     *  #MAP_ADDRESSES} field uses the {@link android.webkit.WebView#findAddress(
     *  String)} method, which has various limitations and has been deprecated: see
     *  the documentation for {@link android.webkit.WebView#findAddress(String)} for
     *  more information.
     *
     *  @deprecated use {@link android.view.textclassifier.TextClassifier#generateLinks(
     *  TextLinks.Request)} instead, and avoid {@link #ALL} even when targeting API levels where no
     *  alternative is available.
     */
    @Deprecated
    public static final int ALL = 15;

    /**
     *  Bit field indicating that email addresses should be matched in methods
     *  that take an options mask
     */
    public static final int EMAIL_ADDRESSES = 2;

    /**
     *  Bit field indicating that street addresses should be matched in methods that
     *  take an options mask. Note that this should be avoided, as it uses the
     *  {@link android.webkit.WebView#findAddress(String)} method, which has various
     *  limitations and has been deprecated: see the documentation for
     *  {@link android.webkit.WebView#findAddress(String)} for more information.
     *
     *  @deprecated use {@link android.view.textclassifier.TextClassifier#generateLinks(
     *  TextLinks.Request)} instead, and avoid {@link #MAP_ADDRESSES} even when targeting API levels
     *  where no alternative is available.
     */
    @Deprecated
    public static final int MAP_ADDRESSES = 8;

    /**
     *  Bit field indicating that phone numbers should be matched in methods that
     *  take an options mask
     */
    public static final int PHONE_NUMBERS = 4;

    /**
     *  Bit field indicating that web URLs should be matched in methods that
     *  take an options mask
     */
    public static final int WEB_URLS = 1;

    private Linkify() {
    }
}
