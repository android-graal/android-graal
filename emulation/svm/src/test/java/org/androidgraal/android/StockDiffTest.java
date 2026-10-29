package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.fail;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * A copy's stored diff is the unified diff java-diff-utils writes from its SDK source's lines to its own, split at
 * every {@code \n} and compared as they are, with three lines of context and the copy's path from the repository root
 * after {@code a/} and {@code b/} in its header, so that {@code git apply} and {@code patch} turn the SDK source into
 * the copy.
 */
class StockDiffTest {

    private static final int CONTEXT = 3;

    private static final List<String> TOP_LEVEL = List.of("android/", "libcore/", "com/", "dalvik/", "org/json/",
            "org/xmlpull/");

    private static final List<String> EMULATED = List.of("", "@Emulated",
            "import org.androidgraal.hosted.annotate.Emulated;");

    private static final Path ACTUAL = Path.of("build/golden-actual/stock-diff").toAbsolutePath();

    private static final Path COPIES = Path.of(System.getProperty("android.copies"));

    private static final String COPIES_PATH = "emulation/svm/src/main/java";

    private static final Path SDK_SOURCES = Path.of(System.getProperty("android.sdkSources"));

    private static final Path GOLDENS = Path.of(System.getProperty("android.stockDiffs"));

    private static final Set<String> COPIED = copied();

    private static final Map<String, String> DIFFS = diffs();

    private static final Map<String, String> STORED = stored();

    @BeforeAll
    static void writeTheActualDiffsWhenOneDiffers() throws IOException {
        if (STORED.equals(DIFFS)) {
            return;
        }
        if (Files.exists(ACTUAL)) {
            try (Stream<Path> files = Files.walk(ACTUAL)) {
                for (Path file : files.sorted(Comparator.reverseOrder()).toList()) {
                    Files.delete(file);
                }
            }
        }
        for (Map.Entry<String, String> diff : DIFFS.entrySet()) {
            Path file = ACTUAL.resolve(diff.getKey());
            Files.createDirectories(file.getParent());
            Files.writeString(file, diff.getValue());
        }
    }

    private static Stream<String> copies() {
        return COPIED.stream();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("copies")
    void theCopyDiffersFromItsSdkSourceByItsGolden(String path) {
        if (read(COPIES.resolve(path)).endsWith("\n") != read(SDK_SOURCES.resolve(path)).endsWith("\n")) {
            fail(path + " and its SDK source differ in the newline at the end, which the patch cannot show");
        }
        String golden = path + ".diff";
        String actual = DIFFS.get(golden);
        String stored = STORED.get(golden);
        if (actual == null && stored != null) {
            fail(path + " differs from its SDK source by the @Emulated lines at most, its golden is stale, the actual "
                    + "files are in " + ACTUAL);
        } else if (actual != null && stored == null) {
            fail(path + " has no golden, the actual files are in " + ACTUAL);
        } else if (actual != null && !actual.equals(stored)) {
            fail(path + " differs from its golden, the actual files are in " + ACTUAL);
        }
    }

    @Test
    void everyGoldenHasACopy() {
        Set<String> stale = new TreeSet<>(STORED.keySet());
        stale.removeIf(golden -> golden.endsWith(".diff")
                && COPIED.contains(golden.substring(0, golden.length() - ".diff".length())));
        if (!stale.isEmpty()) {
            fail("stale goldens without a copy: " + stale + ", the actual files are in " + ACTUAL);
        }
    }

    private static Set<String> copied() {
        Set<String> copied = new TreeSet<>();
        for (String path : relativeFiles(COPIES)) {
            if (path.endsWith(".java") && TOP_LEVEL.stream().anyMatch(path::startsWith)
                    && !path.startsWith("android/opengl/GLES") && Files.isRegularFile(SDK_SOURCES.resolve(path))) {
                copied.add(path);
            }
        }
        return copied;
    }

    private static Map<String, String> diffs() {
        Map<String, String> diffs = new TreeMap<>();
        for (String path : COPIED) {
            List<String> stock = lines(read(SDK_SOURCES.resolve(path)));
            Patch<String> patch = DiffUtils.diff(stock, lines(read(COPIES.resolve(path))));
            if (!differsByEmulatedAtMost(patch)) {
                String name = COPIES_PATH + "/" + path;
                List<String> diff = UnifiedDiffUtils.generateUnifiedDiff("a/" + name, "b/" + name, stock, patch,
                        CONTEXT);
                diffs.put(path + ".diff", String.join("\n", diff) + "\n");
            }
        }
        return diffs;
    }

    private static List<String> lines(String text) {
        List<String> lines = List.of(text.split("\n", -1));
        return text.endsWith("\n") ? lines.subList(0, lines.size() - 1) : lines;
    }

    private static boolean differsByEmulatedAtMost(Patch<String> patch) {
        List<String> added = new ArrayList<>();
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            if (!delta.getSource().getLines().isEmpty()) {
                return false;
            }
            added.addAll(delta.getTarget().getLines());
        }
        added.sort(null);
        return added.isEmpty() || added.equals(EMULATED);
    }

    private static Map<String, String> stored() {
        Map<String, String> stored = new TreeMap<>();
        for (String path : relativeFiles(GOLDENS)) {
            stored.put(path, read(GOLDENS.resolve(path)));
        }
        return stored;
    }

    private static List<String> relativeFiles(Path root) {
        try (Stream<Path> files = Files.walk(root)) {
            return files.filter(Files::isRegularFile)
                    .map(file -> root.relativize(file).toString().replace(root.getFileSystem().getSeparator(), "/"))
                    .sorted().toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String read(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
