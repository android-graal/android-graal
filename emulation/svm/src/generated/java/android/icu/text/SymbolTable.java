package android.icu.text;

/**
 * An interface that defines both lookup protocol and parsing of
 * symbolic names.
 *
 * <p>This interface is used by UnicodeSet to resolve $Variable style
 * references that appear in set patterns.  RBBI and Transliteration
 * both independently implement this interface.
 *
 * <p>A symbol table maintains two kinds of mappings.  The first is
 * between symbolic names and their values.  For example, if the
 * variable with the name "start" is set to the value "alpha"
 * (perhaps, though not necessarily, through an expression such as
 * "$start=alpha"), then the call lookup("start") will return the
 * char[] array ['a', 'l', 'p', 'h', 'a'].
 *
 * <p>The second kind of mapping is between character values and
 * UnicodeMatcher objects.  This is used by RuleBasedTransliterator,
 * which uses characters in the private use area to represent objects
 * such as UnicodeSets.  If U+E015 is mapped to the UnicodeSet [a-z],
 * then lookupMatcher(0xE015) will return the UnicodeSet [a-z].
 *
 * <p>Finally, a symbol table defines parsing behavior for symbolic
 * names.  All symbolic names start with the SYMBOL_REF character.
 * When a parser encounters this character, it calls parseReference()
 * with the position immediately following the SYMBOL_REF.  The symbol
 * table parses the name, if there is one, and returns it.
 */
public interface SymbolTable {
    /**
     * The character preceding a symbol reference name.
     */
    char SYMBOL_REF = '$';
}
