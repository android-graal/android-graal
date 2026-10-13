package android.icu.text;

/**
 * Abstract base class for IDNA processing.
 * See http://www.unicode.org/reports/tr46/
 * and http://www.ietf.org/rfc/rfc3490.txt
 * <p>
 * The IDNA class is not intended for public subclassing.
 * <p>
 * The non-static methods implement UTS #46 and IDNA2008.
 * IDNA2008 is implemented according to UTS #46, see {@link #getUTS46Instance(int)}.
 * <p>
 * IDNA2003 is obsolete. The static methods implement IDNA2003. They are all deprecated.
 * <p>
 * IDNA2003 API Overview:
 * <p>
 * The static IDNA API methods implement the IDNA protocol as defined in the
 * <a href="http://www.ietf.org/rfc/rfc3490.txt">IDNA RFC</a>.
 * The draft defines 2 operations: ToASCII and ToUnicode. Domain labels
 * containing non-ASCII code points are required to be processed by
 * ToASCII operation before passing it to resolver libraries. Domain names
 * that are obtained from resolver libraries are required to be processed by
 * ToUnicode operation before displaying the domain name to the user.
 * IDNA requires that implementations process input strings with
 * <a href="http://www.ietf.org/rfc/rfc3491.txt">Nameprep</a>,
 * which is a profile of <a href="http://www.ietf.org/rfc/rfc3454.txt">Stringprep</a> ,
 * and then with <a href="http://www.ietf.org/rfc/rfc3492.txt">Punycode</a>.
 * Implementations of IDNA MUST fully implement Nameprep and Punycode;
 * neither Nameprep nor Punycode are optional.
 * The input and output of ToASCII and ToUnicode operations are Unicode
 * and are designed to be chainable, i.e., applying ToASCII or ToUnicode operations
 * multiple times to an input string will yield the same result as applying the operation
 * once.
 * ToUnicode(ToUnicode(ToUnicode...(ToUnicode(string)))) == ToUnicode(string)
 * ToASCII(ToASCII(ToASCII...(ToASCII(string))) == ToASCII(string).
 *
 * @author Ram Viswanadha, Markus Scherer
 */
public final class IDNA {
    /**
     * IDNA option to check for whether the input conforms to the BiDi rules.
     * For use in static worker and factory methods.
     * <p>This option is ignored by the IDNA2003 implementation.
     * (IDNA2003 always performs a BiDi check.)
     */
    public static final int CHECK_BIDI = 4;

    /**
     * IDNA option to check for whether the input conforms to the CONTEXTJ rules.
     * For use in static worker and factory methods.
     * <p>This option is ignored by the IDNA2003 implementation.
     * (The CONTEXTJ check is new in IDNA2008.)
     */
    public static final int CHECK_CONTEXTJ = 8;

    /**
     * IDNA option to check for whether the input conforms to the CONTEXTO rules.
     * For use in static worker and factory methods.
     * <p>This option is ignored by the IDNA2003 implementation.
     * (The CONTEXTO check is new in IDNA2008.)
     * <p>This is for use by registries for IDNA2008 conformance.
     * UTS #46 does not require the CONTEXTO check.
     */
    public static final int CHECK_CONTEXTO = 64;

    /**
     * Default options value: None of the other options are set.
     */
    public static final int DEFAULT = 0;

    /**
     * IDNA option for nontransitional processing in ToASCII().
     * For use in static worker and factory methods.
     *
     * <p>By default, ToASCII() uses transitional processing.
     * Unicode 15.1 UTS #46 deprecated transitional processing.
     *
     * <p>This option is ignored by the IDNA2003 implementation.
     * (This is only relevant for compatibility of newer IDNA implementations with IDNA2003.)
     */
    public static final int NONTRANSITIONAL_TO_ASCII = 16;

    /**
     * IDNA option for nontransitional processing in ToUnicode().
     * For use in static worker and factory methods.
     *
     * <p>By default, ToUnicode() uses transitional processing.
     * Unicode 15.1 UTS #46 deprecated transitional processing.
     *
     * <p>This option is ignored by the IDNA2003 implementation.
     * (This is only relevant for compatibility of newer IDNA implementations with IDNA2003.)
     */
    public static final int NONTRANSITIONAL_TO_UNICODE = 32;

    /**
     * Option to check whether the input conforms to the STD3 ASCII rules,
     * for example the restriction of labels to LDH characters
     * (ASCII Letters, Digits and Hyphen-Minus).
     * For use in static worker and factory methods.
     */
    public static final int USE_STD3_RULES = 2;

    private IDNA() {
    }
}
