package android.app;

import android.os.Parcelable;

/**
 * The LocaleConfig of an application.
 * There are two sources. One is from an XML resource file with an {@code <locale-config>} element
 * and referenced in the manifest via {@code android:localeConfig} on {@code <application>}. The
 * other is that the application dynamically provides an override version which is persisted in
 * {@link LocaleManager#setOverrideLocaleConfig(LocaleConfig)}.
 *
 * <p>For more information about the LocaleConfig from an XML resource file, see
 * <a href="https://developer.android.com/about/versions/13/features/app-languages#use-localeconfig">
 * the section on per-app language preferences</a>.
 *
 * @attr ref android.R.styleable#LocaleConfig_Locale_name
 * @attr ref android.R.styleable#AndroidManifestApplication_localeConfig
 */
public final class LocaleConfig implements Parcelable {
    /**
     * No android:localeConfig tag on <application>.
     */
    public static final int STATUS_NOT_SPECIFIED = 1;

    /**
     * Malformed input in the XML file where the LocaleConfig was stored.
     */
    public static final int STATUS_PARSING_FAILED = 2;

    /**
     * succeeded reading the LocaleConfig structure stored in an XML file.
     */
    public static final int STATUS_SUCCESS = 0;

    public static final String TAG_LOCALE = "locale";

    public static final String TAG_LOCALE_CONFIG = "locale-config";

    private LocaleConfig() {
    }
}
