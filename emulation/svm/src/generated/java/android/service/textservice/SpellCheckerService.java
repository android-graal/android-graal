package android.service.textservice;

/**
 * SpellCheckerService provides an abstract base class for a spell checker.
 * This class combines a service to the system with the spell checker service interface that
 * spell checker must implement.
 *
 * <p>In addition to the normal Service lifecycle methods, this class
 * introduces a new specific callback that subclasses should override
 * {@link #createSession()} to provide a spell checker session that is corresponding
 * to requested language and so on. The spell checker session returned by this method
 * should extend {@link SpellCheckerService.Session}.
 * </p>
 *
 * <h3>Returning spell check results</h3>
 *
 * <p>{@link SpellCheckerService.Session#onGetSuggestions(TextInfo, int)}
 * should return spell check results.
 * It receives {@link android.view.textservice.TextInfo} and returns
 * {@link android.view.textservice.SuggestionsInfo} for the input.
 * You may want to override
 * {@link SpellCheckerService.Session#onGetSuggestionsMultiple(TextInfo[], int, boolean)} for
 * better performance and quality.
 * </p>
 *
 * <p>Please note that {@link SpellCheckerService.Session#getLocale()} does not return a valid
 * locale before {@link SpellCheckerService.Session#onCreate()} </p>
 */
public final class SpellCheckerService {
    public static final String SERVICE_INTERFACE = "android.service.textservice.SpellCheckerService";

    private SpellCheckerService() {
    }
}
