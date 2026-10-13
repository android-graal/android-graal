package android.webkit;

public final class WebChromeClient {
    private WebChromeClient() {
    }

    /**
     * Parameters used in the {@link #onShowFileChooser} method.
     */
    public static final class FileChooserParams {
        /**
         * Open single file. Requires that the file exists before allowing the user to pick it.
         */
        public static final int MODE_OPEN = 0;

        /**
         * Like Open but allows multiple files to be selected.
         */
        public static final int MODE_OPEN_MULTIPLE = 1;

        /**
         * Allows picking a nonexistent file and saving it.
         */
        public static final int MODE_SAVE = 3;

        private FileChooserParams() {
        }
    }
}
