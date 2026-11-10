package org.androidgraal.android;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import com.sun.source.tree.AnnotatedTypeTree;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ArrayTypeTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.NewArrayTree;
import com.sun.source.tree.ParameterizedTypeTree;
import com.sun.source.tree.ParenthesizedTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TypeCastTree;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.tree.WildcardTree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.TreeScanner;
import com.sun.source.util.Trees;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.FieldModel;
import java.lang.classfile.MethodModel;
import java.lang.classfile.MethodSignature;
import java.lang.classfile.Signature;
import java.lang.classfile.attribute.InnerClassInfo;
import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.lang.model.element.Modifier;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandleClassesTest {

    private static final String FIRST_LINE = "// Generated from android.jar, its api-versions.xml, its sources and the "
            + "proxy boundary.";

    private static final String HANDLE = "handle";

    private static final String VALUE = "value";

    private static final String STUB = "throw new UnsupportedOperationException(\"hidden on ART\");";

    private static final Pattern ADDED_IMPORT = Pattern.compile("android\\.os\\.Build"
            + "|org\\.androidgraal\\.hosted\\.annotate\\.(Added|Emulated)|org\\.androidgraal\\.shared\\.Proxy"
            + "|org\\.androidgraal\\.android\\.handles\\.\\w+Binding");

    private static final Path COPIES = Path.of(System.getProperty("android.copies"));

    private static final Path SDK_SOURCES = Path.of(System.getProperty("android.sdkSources"));

    private static final Path ANDROID_JAR = Path.of(System.getProperty("android.androidJar"));

    private static final Path CLASSES = Path.of(System.getProperty("android.emulations"));

    private static final Path PROXY_BOUNDARY = Path.of(System.getProperty("android.proxyBoundary"));

    private static final Path API_VERSIONS = Path.of(System.getProperty("android.apiVersions"));

    private static final int MIN_API = Integer.parseInt(System.getProperty("android.minApi"));

    private static final Map<String, String> VERDICTS = verdicts();

    private static final Map<String, Levels> LEVELS = levels();

    private static Map<String, String> verdicts() {
        Map<String, String> verdicts = new LinkedHashMap<>();
        try {
            for (String line : Files.readAllLines(PROXY_BOUNDARY, StandardCharsets.UTF_8)) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.trim().split(" ");
                if (parts.length != 2 || !parts[1].equals(HANDLE) && !parts[1].equals(VALUE)) {
                    throw new IllegalStateException("a proxy boundary line is a binary name and a verdict: " + line);
                }
                verdicts.put(parts[0].replace('.', '/'), parts[1]);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return verdicts;
    }

    private record Levels(int since, Map<String, Integer> members) {

        private int sinceOf(String key) {
            return members.getOrDefault(key, since);
        }
    }

    private static Map<String, Levels> levels() {
        Map<String, Levels> levels = new HashMap<>();
        try (InputStream input = Files.newInputStream(API_VERSIONS)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(input);
            String owner = "";
            int since = 1;
            Map<String, Integer> members = new HashMap<>();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("class")) {
                    owner = reader.getAttributeValue(null, "name");
                    String level = reader.getAttributeValue(null, "since");
                    since = level == null ? 1 : Integer.parseInt(level);
                    members = new HashMap<>();
                } else if (event == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("method")) {
                    String level = reader.getAttributeValue(null, "since");
                    if (level != null) {
                        members.put(reader.getAttributeValue(null, "name"), Integer.parseInt(level));
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("class")
                        && HANDLE.equals(VERDICTS.get(owner))) {
                    levels.put(owner, new Levels(since, members));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (XMLStreamException e) {
            throw new IllegalStateException(e);
        }
        return levels;
    }

    private static List<String> handles() {
        return VERDICTS.entrySet().stream()
                .filter(entry -> entry.getValue().equals(HANDLE) && !entry.getKey().contains("$"))
                .map(entry -> entry.getKey() + ".java").toList();
    }

    @Test
    void everyGeneratedHandleClassIsARootOfTheProxyBoundary() throws IOException {
        List<String> roots = handles();
        List<String> strays = new ArrayList<>();
        try (Stream<Path> files = Files.walk(COPIES)) {
            for (Path file : files.filter(file -> file.toString().endsWith(".java")).sorted().toList()) {
                String path = COPIES.relativize(file).toString().replace(file.getFileSystem().getSeparator(), "/");
                if (!path.startsWith("org/androidgraal/") && Files.readString(file).startsWith(FIRST_LINE + "\n")
                        && !roots.contains(path)) {
                    strays.add(path);
                }
            }
        }
        assertTrue(strays.isEmpty(), "files starting with the handle generator's line the proxy boundary "
                + PROXY_BOUNDARY + " does not name as a root: " + strays);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("handles")
    void everyHunkIsARuleOfTheTransform(String path) throws IOException {
        assertTrue(Files.isRegularFile(COPIES.resolve(path)),
                "the proxy boundary's root " + path + " has no file under " + COPIES);
        assertTrue(Files.isRegularFile(SDK_SOURCES.resolve(path)),
                "the proxy boundary's root " + path + " has no SDK source");
        try (ZipFile jar = new ZipFile(ANDROID_JAR.toFile())) {
            Check check = new Check(path, jar);
            check.run();
            List<String> stock = normalized(check.source.applied(check.sourceEdits));
            List<String> handle = normalized(check.handle.applied(check.handleEdits));
            Patch<String> patch = DiffUtils.diff(stock, handle);
            if (!check.violations.isEmpty() || !patch.getDeltas().isEmpty()) {
                List<String> hunks = UnifiedDiffUtils.generateUnifiedDiff("sdk/" + path, path, stock, patch, 3);
                fail(path + " breaks the transform's rules:\n" + String.join("\n", check.violations)
                        + "\nhunks no rule covers:\n" + String.join("\n", hunks));
            }
        }
    }

    private static List<String> normalized(String text) {
        List<String> lines = new ArrayList<>();
        for (String line : text.split("\n", -1)) {
            if (line.isBlank()) {
                if (lines.isEmpty() || lines.getLast().isBlank() || lines.getLast().trim().endsWith("{")) {
                    continue;
                }
            } else if (line.trim().startsWith("}")) {
                while (!lines.isEmpty() && lines.getLast().isBlank()) {
                    lines.removeLast();
                }
            }
            lines.add(line);
        }
        return lines;
    }

    private enum Mode {
        ROOT, VERBATIM
    }

    private record Edit(int start, int end, String text) {
    }

    private record HiddenMethod(MethodTree stock, MethodTree ours, RootClass root, boolean stub) {
    }

    private static final class Parsed {

        private final String text;

        private final CompilationUnitTree unit;

        private final SourcePositions positions;

        private final List<Integer> starts = new ArrayList<>();

        private Parsed(String text) {
            this.text = text;
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            JavaFileObject file = new SimpleJavaFileObject(URI.create("string:///Source.java"),
                    JavaFileObject.Kind.SOURCE) {
                @Override
                public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                    return text;
                }
            };
            JavacTask task = (JavacTask) compiler.getTask(null, null, null, List.of("-proc:none"), null, List.of(file));
            try {
                unit = task.parse().iterator().next();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            positions = Trees.instance(task).getSourcePositions();
            starts.add(0);
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    starts.add(i + 1);
                }
            }
        }

        private int start(Tree tree) {
            return (int) positions.getStartPosition(unit, tree);
        }

        private int end(Tree tree) {
            return (int) positions.getEndPosition(unit, tree);
        }

        private String textOf(Tree tree) {
            return text.substring(start(tree), end(tree));
        }

        private int lineOf(int offset) {
            int index = Collections.binarySearch(starts, offset);
            return index >= 0 ? index : -index - 2;
        }

        private int lineStart(int line) {
            return line < starts.size() ? starts.get(line) : text.length();
        }

        private String lineText(int line) {
            String full = text.substring(lineStart(line), lineStart(line + 1));
            return full.endsWith("\n") ? full.substring(0, full.length() - 1) : full;
        }

        private Edit lines(int first, int last) {
            return new Edit(lineStart(first), lineStart(last + 1), "");
        }

        private ClassTree top() {
            return (ClassTree) unit.getTypeDecls().stream().filter(ClassTree.class::isInstance).findFirst()
                    .orElseThrow();
        }

        private List<Tree> declarationAt(List<? extends Tree> members, int index) {
            Tree first = members.get(index);
            List<Tree> declaration = new ArrayList<>(List.of(first));
            while (first instanceof VariableTree && index + declaration.size() < members.size()
                    && members.get(index + declaration.size()) instanceof VariableTree next
                    && start(next) == start(first)) {
                declaration.add(next);
            }
            return declaration;
        }

        private Edit regionOf(ClassTree owner, int first, int last) {
            List<? extends Tree> members = owner.getMembers();
            int previous = first == 0
                    ? lineOf(text.indexOf('{', bodyAfter(owner)))
                    : lineOf(end(members.get(first - 1)) - 1);
            int line = previous + 1;
            int start = lineOf(start(members.get(first)));
            while (line < start && lineText(line).isBlank()) {
                line++;
            }
            return lines(line, lineOf(end(members.get(last)) - 1));
        }

        private int bodyAfter(ClassTree owner) {
            int after = start(owner);
            for (Tree tree : Stream
                    .concat(Stream.of(owner.getModifiers(), owner.getExtendsClause()),
                            Stream.concat(owner.getTypeParameters().stream(), owner.getImplementsClause().stream()))
                    .toList()) {
                if (tree != null) {
                    after = Math.max(after, end(tree));
                }
            }
            return after;
        }

        private List<Edit> variablesRemoved(List<Tree> declaration, Set<Tree> gone) {
            List<Integer> names = new ArrayList<>();
            for (Tree variable : declaration) {
                names.add(nameOf((VariableTree) variable));
            }
            List<Edit> removals = new ArrayList<>();
            int index = 0;
            while (index < declaration.size()) {
                if (!gone.contains(declaration.get(index))) {
                    index++;
                    continue;
                }
                int last = index;
                while (last + 1 < declaration.size() && gone.contains(declaration.get(last + 1))) {
                    last++;
                }
                removals.add(last + 1 < declaration.size()
                        ? new Edit(names.get(index), names.get(last + 1), "")
                        : new Edit(end(declaration.get(index - 1)) - 1, end(declaration.get(last)) - 1, ""));
                index = last + 1;
            }
            return removals;
        }

        private int nameOf(VariableTree variable) {
            String name = variable.getName().toString();
            int at = (variable.getInitializer() == null ? end(variable) - 1 : start(variable.getInitializer()))
                    - name.length();
            while (at >= start(variable)) {
                at = text.lastIndexOf(name, at);
                if (at < start(variable)) {
                    break;
                }
                if (!Character.isJavaIdentifierPart(text.charAt(at - 1))
                        && !Character.isJavaIdentifierPart(text.charAt(at + name.length()))) {
                    return at;
                }
                at--;
            }
            throw new IllegalStateException(variable.getName() + " has no name to split its declaration at");
        }

        private String applied(List<Edit> edits) {
            List<Edit> sorted = new ArrayList<>(edits);
            sorted.sort((a, b) -> a.start() != b.start()
                    ? Integer.compare(b.start(), a.start())
                    : Integer.compare(b.end(), a.end()));
            StringBuilder result = new StringBuilder(text);
            int limit = Integer.MAX_VALUE;
            for (Edit edit : sorted) {
                if (edit.end() > limit) {
                    throw new IllegalStateException("two rules edit the text at offset " + edit.start());
                }
                result.replace(edit.start(), edit.end(), edit.text());
                limit = edit.start();
            }
            return result.toString();
        }
    }

    private static final class RootClass {

        private final String internalName;

        private final ClassTree stock;

        private final RootClass outer;

        private final ClassModel android;

        private final Map<String, List<Tree>> hidden = new LinkedHashMap<>();

        private final List<Tree> verbatim = new ArrayList<>();

        private final List<Tree> constants = new ArrayList<>();

        private RootClass(String internalName, ClassTree stock, RootClass outer, ClassModel android) {
            this.internalName = internalName;
            this.stock = stock;
            this.outer = outer;
            this.android = android;
        }

        private void hide(String name, Tree member) {
            hidden.computeIfAbsent(name, key -> new ArrayList<>()).add(member);
        }

        private List<RootClass> chain() {
            List<RootClass> chain = new ArrayList<>();
            for (RootClass root = this; root != null; root = root.outer) {
                chain.add(root);
            }
            return chain;
        }

        private boolean declares(String name) {
            return stock.getMembers().stream().anyMatch(member -> isNamed(member, name));
        }
    }

    private static References referencesOf(Tree tree) {
        References references = new References();
        if (tree instanceof MethodTree method) {
            references.scan(method.getTypeParameters(), null);
            references.scan(method.getParameters(), null);
            references.scan(method.getBody(), null);
        } else {
            references.scan(tree, null);
        }
        return references;
    }

    private static final class References extends TreeScanner<Void, Void> {

        private final Set<String> used = new LinkedHashSet<>();

        private final Set<String> declared = new HashSet<>();

        private final List<List<String>> selected = new ArrayList<>();

        @Override
        public Void visitVariable(VariableTree tree, Void unused) {
            declared.add(tree.getName().toString());
            return super.visitVariable(tree, unused);
        }

        @Override
        public Void visitMethod(MethodTree tree, Void unused) {
            declared.add(tree.getName().toString());
            return super.visitMethod(tree, unused);
        }

        @Override
        public Void visitClass(ClassTree tree, Void unused) {
            declared.add(tree.getSimpleName().toString());
            return super.visitClass(tree, unused);
        }

        @Override
        public Void visitIdentifier(IdentifierTree tree, Void unused) {
            used.add(tree.getName().toString());
            return null;
        }

        @Override
        public Void visitMemberSelect(MemberSelectTree tree, Void unused) {
            if (tree.getExpression() instanceof IdentifierTree qualifier) {
                selected.add(List.of(qualifier.getName().toString(), tree.getIdentifier().toString()));
            }
            return super.visitMemberSelect(tree, unused);
        }

        @Override
        public Void visitAnnotation(AnnotationTree tree, Void unused) {
            return null;
        }
    }

    private static final class Check {

        private final String path;

        private final ZipFile jar;

        private final Parsed source;

        private final Parsed handle;

        private final String packagePath;

        private final List<Edit> sourceEdits = new ArrayList<>();

        private final List<Edit> handleEdits = new ArrayList<>();

        private final List<String> violations = new ArrayList<>();

        private final Map<String, ClassTree> handleClasses = new HashMap<>();

        private final List<RootClass> roots = new ArrayList<>();

        private final Set<Tree> removedAnnotations = Collections.newSetFromMap(new IdentityHashMap<>());

        private final List<HiddenMethod> hiddenMethods = new ArrayList<>();

        private final List<Map.Entry<Tree, RootClass>> keptTexts = new ArrayList<>();

        private Check(String path, ZipFile jar) throws IOException {
            this.path = path;
            this.jar = jar;
            source = new Parsed(Files.readString(SDK_SOURCES.resolve(path), StandardCharsets.UTF_8));
            handle = new Parsed(Files.readString(COPIES.resolve(path), StandardCharsets.UTF_8));
            packagePath = path.substring(0, path.lastIndexOf('/'));
        }

        private void run() {
            if (handle.lineText(0).equals(FIRST_LINE)) {
                handleEdits.add(handle.lines(0, 0));
            } else {
                violations.add("the first line is not the generator's");
            }
            String internalName = path.substring(0, path.length() - ".java".length());
            collect(handle.top(), internalName);
            imports();
            ClassTree top = handle.top();
            Optional<? extends AnnotationTree> emulated = top.getModifiers().getAnnotations().stream()
                    .filter(annotation -> handle.textOf(annotation).equals("@Emulated")).findFirst();
            if (emulated.isEmpty()) {
                violations.add(internalName + " carries no @Emulated");
            } else {
                removeAnnotation(handle, handleEdits, emulated.get());
            }
            compare(source.top(), top, internalName, Mode.ROOT, null);
            close();
            resolveHiddenMethods();
            namedMethods();
        }

        private void collect(ClassTree type, String internalName) {
            handleClasses.put(internalName, type);
            for (Tree member : type.getMembers()) {
                if (member instanceof ClassTree nested) {
                    collect(nested, internalName + "$" + nested.getSimpleName());
                }
            }
        }

        private void imports() {
            Map<String, ImportTree> kept = new HashMap<>();
            for (ImportTree tree : handle.unit.getImports()) {
                kept.put(importKeyOf(tree), tree);
            }
            for (ImportTree tree : source.unit.getImports()) {
                if (kept.remove(importKeyOf(tree)) != null) {
                    continue;
                }
                if (isPresent(tree)) {
                    violations.add("removed the import of a present type: " + importKeyOf(tree));
                }
                int line = source.lineOf(source.start(tree));
                sourceEdits.add(source.lines(line, line));
            }
            for (ImportTree tree : kept.values()) {
                if (tree.isStatic() || !ADDED_IMPORT.matcher(tree.getQualifiedIdentifier().toString()).matches()) {
                    violations.add("added the import " + importKeyOf(tree));
                }
                int line = handle.lineOf(handle.start(tree));
                handleEdits.add(handle.lines(line, line));
            }
        }

        private String importKeyOf(ImportTree tree) {
            return (tree.isStatic() ? "static " : "") + tree.getQualifiedIdentifier();
        }

        private boolean isPresent(ImportTree tree) {
            List<String> segments = segmentsOf(tree.getQualifiedIdentifier());
            boolean onDemand = segments.getLast().equals("*");
            if (!tree.isStatic()) {
                return onDemand || qualifiedType(segments) != null;
            }
            String owner = qualifiedType(segments.subList(0, segments.size() - 1));
            return owner != null && (onDemand || hasMember(owner, segments.getLast()));
        }

        private void compare(ClassTree stock, ClassTree ours, String internalName, Mode mode, RootClass outer) {
            RootClass root = null;
            removeAbsentAnnotations(stock.getModifiers(), internalName);
            if (mode == Mode.ROOT) {
                ClassModel android = androidModel(internalName);
                if (android == null) {
                    violations.add(internalName + " is a handle class android.jar lacks");
                    return;
                }
                if (!LEVELS.containsKey(internalName)) {
                    violations.add(internalName + " is a handle class " + API_VERSIONS + " lacks");
                }
                root = new RootClass(internalName, stock, outer, android);
                roots.add(root);
                proxy(stock, ours, internalName);
            }
            List<Tree> unmatched = new ArrayList<>(ours.getMembers());
            Set<Tree> gone = Collections.newSetFromMap(new IdentityHashMap<>());
            for (Tree member : stock.getMembers()) {
                Tree counterpart = counterpartOf(member, unmatched);
                if (counterpart == null) {
                    removed(member, internalName, mode, root);
                    gone.add(member);
                } else {
                    unmatched.remove(counterpart);
                    kept(member, counterpart, internalName, mode, root);
                }
            }
            removals(stock, gone);
            if (mode == Mode.ROOT) {
                added(ours, unmatched, internalName);
            } else {
                for (Tree member : unmatched) {
                    violations.add(internalName + " is verbatim and gains "
                            + handle.textOf(member).lines().findFirst().orElse(""));
                }
            }
        }

        private void removals(ClassTree stock, Set<Tree> gone) {
            List<? extends Tree> members = stock.getMembers();
            for (int index = 0; index < members.size();) {
                List<Tree> declaration = source.declarationAt(members, index);
                long removed = declaration.stream().filter(gone::contains).count();
                if (removed == declaration.size()) {
                    sourceEdits.add(source.regionOf(stock, index, index + declaration.size() - 1));
                } else if (removed > 0) {
                    sourceEdits.addAll(source.variablesRemoved(declaration, gone));
                }
                index += declaration.size();
            }
        }

        private void proxy(ClassTree stock, ClassTree ours, String internalName) {
            List<? extends Tree> theirs = stock.getImplementsClause();
            List<? extends Tree> implemented = ours.getImplementsClause();
            if (implemented.size() != theirs.size() + 1 || !handle.textOf(implemented.getLast()).equals("Proxy")) {
                violations.add(internalName + " does not implement Proxy last");
                return;
            }
            int end = handle.end(implemented.getLast());
            int start = theirs.isEmpty()
                    ? handle.text.lastIndexOf("implements", end)
                    : handle.end(implemented.get(implemented.size() - 2));
            while (theirs.isEmpty() && Character.isWhitespace(handle.text.charAt(start - 1))) {
                start--;
            }
            handleEdits.add(new Edit(start, end, ""));
        }

        private Tree counterpartOf(Tree member, List<Tree> candidates) {
            for (Tree candidate : candidates) {
                if (member.getKind() != candidate.getKind()) {
                    continue;
                }
                boolean same = switch (member) {
                    case VariableTree field -> field.getName().contentEquals(((VariableTree) candidate).getName());
                    case MethodTree method -> keyOf(method).equals(keyOf((MethodTree) candidate));
                    case ClassTree type -> type.getSimpleName().contentEquals(((ClassTree) candidate).getSimpleName());
                    default -> source.textOf(member).equals(handle.textOf(candidate));
                };
                if (same) {
                    return candidate;
                }
            }
            return null;
        }

        private void removed(Tree member, String internalName, Mode mode, RootClass root) {
            String what = describe(member);
            if (mode == Mode.VERBATIM) {
                violations.add(internalName + " is verbatim and loses " + what);
                return;
            }
            switch (member) {
                case VariableTree field -> {
                    Optional<FieldModel> visible = visibleField(root.android, field.getName().toString());
                    if (field.getModifiers().getFlags().contains(Modifier.STATIC) && visible.isPresent()
                            && visible.get().findAttribute(Attributes.constantValue()).isPresent()) {
                        violations.add(internalName + " loses the constant " + what);
                    } else if (visible.isEmpty() && isPublicConstant(field)) {
                        violations.add(internalName + " loses the hidden constant " + what);
                    }
                }
                case MethodTree method -> {
                    Optional<MethodModel> visible = visibleMethod(root.android, keyOf(method));
                    if (visible.isPresent() && !namesAbsentType(visible.get())) {
                        violations.add(internalName + " loses " + what
                                + ", which android.jar declares and the emulation can name");
                    } else if (visible.isEmpty() && !method.getName().contentEquals("<init>")
                            && !method.getModifiers().getFlags().contains(Modifier.NATIVE)
                            && isPresentHead(method, internalName)) {
                        violations.add(
                                internalName + " loses the hidden " + what + ", whose head the emulation can name");
                    }
                }
                case ClassTree type -> {
                    if (type.getKind() == Tree.Kind.ANNOTATION_TYPE
                            || isVisibleNested(root.android, internalName + "$" + type.getSimpleName())) {
                        violations.add(internalName + " loses the nested type " + what);
                    }
                }
                case BlockTree block -> {
                }
                default -> violations.add(internalName + " loses " + what);
            }
        }

        private void kept(Tree member, Tree counterpart, String internalName, Mode mode, RootClass root) {
            switch (member) {
                case VariableTree field -> keptField(field, (VariableTree) counterpart, internalName, root);
                case MethodTree method -> keptMethod(method, (MethodTree) counterpart, internalName, root);
                case ClassTree type -> keptType(type, (ClassTree) counterpart, internalName, root);
                case BlockTree block when mode == Mode.ROOT -> violations
                        .add(internalName + " keeps an initializer block, which the transform never keeps");
                default -> removeAbsentAnnotations(member, internalName);
            }
        }

        private void keptType(ClassTree type, ClassTree ours, String internalName, RootClass root) {
            String nestedName = internalName + "$" + type.getSimpleName();
            if (root == null) {
                compare(type, ours, nestedName, Mode.VERBATIM, null);
                return;
            }
            String verdict = VERDICTS.get(nestedName);
            if (HANDLE.equals(verdict)) {
                compare(type, ours, nestedName, Mode.ROOT, root);
                return;
            }
            if (VALUE.equals(verdict) || isVisibleInterface(root.android, nestedName)
                    || type.getKind() == Tree.Kind.ANNOTATION_TYPE) {
                root.verbatim.add(type);
            } else if (isVisibleNested(root.android, nestedName)) {
                violations.add(internalName + " keeps the nested type " + type.getSimpleName()
                        + ", which the proxy boundary does not classify");
            } else {
                root.hide(type.getSimpleName().toString(), type);
            }
            compare(type, ours, nestedName, Mode.VERBATIM, null);
        }

        private void keptField(VariableTree field, VariableTree ours, String internalName, RootClass root) {
            removeAbsentAnnotations(field.getModifiers(), internalName);
            removeAbsentAnnotations(field.getType(), internalName);
            String name = field.getName().toString();
            if (root != null) {
                Optional<FieldModel> visible = visibleField(root.android, name);
                if (visible.isEmpty() && isPublicConstant(field)) {
                    root.constants.add(field);
                } else if (visible.isEmpty()) {
                    root.hide(name, field);
                } else if (!field.getModifiers().getFlags().contains(Modifier.STATIC)
                        || visible.get().findAttribute(Attributes.constantValue()).isEmpty()) {
                    violations.add(internalName + " keeps the field " + name + ", which android.jar declares as no "
                            + "constant");
                } else {
                    root.constants.add(field);
                }
            }
            ExpressionTree initializer = field.getInitializer();
            ExpressionTree replacement = ours.getInitializer();
            if (initializer == null || replacement == null
                    || source.textOf(initializer).equals(handle.textOf(replacement))) {
                if (initializer != null) {
                    removeAbsentAnnotations(initializer, internalName);
                }
                return;
            }
            violations.add(internalName + " replaces the initializer of " + name + " with " + handle.textOf(replacement)
                    + ", where the generator writes the stock one");
            sourceEdits.add(new Edit(source.start(initializer), source.end(initializer), handle.textOf(replacement)));
        }

        private void keptMethod(MethodTree method, MethodTree ours, String internalName, RootClass root) {
            removeAbsentAnnotations(method.getModifiers(), internalName);
            removeAbsentAnnotations(method.getReturnType(), internalName);
            removeAbsentAnnotations(method.getTypeParameters(), internalName);
            removeAbsentAnnotations(method.getParameters(), internalName);
            removeAbsentAnnotations(method.getThrows(), internalName);
            BlockTree body = method.getBody();
            BlockTree replacement = ours.getBody();
            Optional<MethodModel> visible = root == null
                    ? Optional.empty()
                    : visibleMethod(root.android, keyOf(method));
            if (visible.isEmpty()) {
                if (root != null && method.getName().contentEquals("<init>")) {
                    violations.add(internalName + " keeps the hidden " + keyOf(method)
                            + ", a constructor the transform never keeps");
                    return;
                }
                boolean stub = root != null && isStub(ours);
                if (root != null) {
                    hiddenMethods.add(new HiddenMethod(method, ours, root, stub));
                }
                if (stub && body != null) {
                    sourceEdits.add(new Edit(source.start(body), source.end(body), "{}"));
                    handleEdits.add(new Edit(handle.start(replacement), handle.end(replacement), "{}"));
                } else if (body != null) {
                    removeAbsentAnnotations(body, internalName);
                }
                return;
            }
            if (body == null || replacement == null) {
                violations.add(internalName + " " + keyOf(method) + " has no body to replace");
                return;
            }
            List<String> expected = bodyOf(method, visible.get(), root);
            List<String> actual = replacement.getStatements().stream().map(Check::collapsed).toList();
            if (!actual.equals(expected)) {
                violations.add(internalName + " " + keyOf(method) + " has the body " + actual
                        + ", where the generator writes " + expected);
            }
            sourceEdits.add(new Edit(source.start(body), source.end(body), "{}"));
            handleEdits.add(new Edit(handle.start(replacement), handle.end(replacement), "{}"));
        }

        private List<String> bodyOf(MethodTree method, MethodModel target, RootClass root) {
            String key = target.methodName().stringValue() + target.methodTypeSymbol().descriptorString();
            String message = "\"" + key + "\"";
            List<String> statements = new ArrayList<>();
            Levels levels = LEVELS.get(root.internalName);
            int since = levels == null ? MIN_API : levels.sinceOf(key);
            if (since > MIN_API) {
                statements.add(
                        "if (Build.VERSION.SDK_INT < " + since + ") { throw new NoSuchMethodError(" + message + "); }");
            }
            String endPoint = endPointOf(root.android, target);
            if (endPoint == null) {
                statements.add("throw new UnsupportedOperationException(" + message + ");");
                return statements;
            }
            List<String> arguments = new ArrayList<>();
            for (VariableTree parameter : method.getParameters()) {
                arguments.add(parameter.getName().toString());
            }
            arguments.addFirst(arguments.contains("id") ? "this.id" : "id");
            String binding = root.internalName.substring(root.internalName.lastIndexOf('/') + 1).replace("$", "")
                    + "Binding";
            String result = target.methodTypeSymbol().returnType().descriptorString().equals("V") ? "" : "return ";
            statements.add(result + binding + "." + endPoint + "(" + String.join(", ", arguments) + ");");
            return statements;
        }

        private String endPointOf(ClassModel android, MethodModel target) {
            if (!isShaped(target)) {
                return null;
            }
            String name = target.methodName().stringValue();
            List<String> overloads = android.methods().stream().filter(Check::isDeclared)
                    .filter(method -> method.methodName().equalsString(name))
                    .filter(method -> !namesAbsentType(method) && isShaped(method))
                    .map(method -> method.methodTypeSymbol().descriptorString()).sorted(Comparator.naturalOrder())
                    .toList();
            int index = overloads.indexOf(target.methodTypeSymbol().descriptorString());
            return overloads.size() == 1 ? name : name + "$" + index;
        }

        private boolean isShaped(MethodModel method) {
            Set<AccessFlag> flags = method.flags().flags();
            if (method.methodName().equalsString("<init>") || flags.contains(AccessFlag.STATIC)
                    || !flags.contains(AccessFlag.PUBLIC) || !isCrossed(method.methodTypeSymbol().returnType(), true)) {
                return false;
            }
            return method.methodTypeSymbol().parameterList().stream().allMatch(type -> isCrossed(type, false));
        }

        private boolean isCrossed(ClassDesc type, boolean isResult) {
            if (type.descriptorString().equals("V")) {
                return isResult;
            }
            if (type.isPrimitive() || type.descriptorString().equals("Ljava/lang/String;")) {
                return true;
            }
            if (type.isArray()) {
                return isResult && type.componentType().isPrimitive();
            }
            return HANDLE.equals(VERDICTS.get(internalNameOf(type)));
        }

        private static String collapsed(Tree tree) {
            return tree.toString().replaceAll("\\s+", " ").trim();
        }

        private static boolean isStub(MethodTree method) {
            BlockTree body = method.getBody();
            return body != null && body.getStatements().size() == 1
                    && collapsed(body.getStatements().getFirst()).equals(STUB);
        }

        private void close() {
            Deque<Map.Entry<Tree, RootClass>> work = new ArrayDeque<>();
            for (RootClass root : roots) {
                root.verbatim.forEach(type -> work.add(Map.entry(type, root)));
                root.constants.forEach(field -> work.add(Map.entry(field, root)));
            }
            for (HiddenMethod hidden : hiddenMethods) {
                if (!hidden.stub()) {
                    work.add(Map.entry(hidden.stock(), hidden.root()));
                }
            }
            Set<Tree> reached = Collections.newSetFromMap(new IdentityHashMap<>());
            while (!work.isEmpty()) {
                Map.Entry<Tree, RootClass> item = work.removeFirst();
                keptTexts.add(item);
                for (Map.Entry<String, RootClass> want : wanted(item.getKey(), item.getValue())) {
                    Optional<RootClass> node = want.getValue().chain().stream()
                            .filter(root -> root.declares(want.getKey())).findFirst();
                    for (Tree member : node.map(root -> root.hidden.get(want.getKey())).orElse(List.of())) {
                        if (reached.add(member)) {
                            work.add(Map.entry(member, node.get()));
                        }
                    }
                }
            }
            for (RootClass root : roots) {
                for (List<Tree> members : root.hidden.values()) {
                    for (Tree member : members) {
                        if (!isKeepable(member)) {
                            violations.add(root.internalName + " keeps the hidden " + describe(member)
                                    + ", a member the transform never keeps");
                        } else if (!reached.contains(member)) {
                            violations.add(root.internalName + " keeps the hidden " + describe(member)
                                    + ", which no kept text names");
                        }
                    }
                }
            }
        }

        private List<Map.Entry<String, RootClass>> wanted(Tree tree, RootClass owner) {
            References references = referencesOf(tree);
            List<Map.Entry<String, RootClass>> wanted = new ArrayList<>();
            for (String name : references.used) {
                if (!references.declared.contains(name)) {
                    wanted.add(Map.entry(name, owner));
                }
            }
            for (List<String> selected : references.selected) {
                owner.chain().stream().filter(root -> root.stock.getSimpleName().contentEquals(selected.getFirst()))
                        .findFirst().ifPresent(root -> wanted.add(Map.entry(selected.getLast(), root)));
            }
            return wanted;
        }

        private void namedMethods() {
            Set<String> named = new LinkedHashSet<>();
            for (Map.Entry<Tree, RootClass> text : keptTexts) {
                for (Map.Entry<String, RootClass> want : wanted(text.getKey(), text.getValue())) {
                    Optional<RootClass> node = want.getValue().chain().stream()
                            .filter(root -> root.declares(want.getKey())).findFirst();
                    if (node.isEmpty()) {
                        continue;
                    }
                    List<Tree> ours = new ArrayList<>(handleClasses.get(node.get().internalName).getMembers());
                    for (Tree member : node.get().stock.getMembers()) {
                        if (!(member instanceof MethodTree method) || !isNamed(method, want.getKey())) {
                            continue;
                        }
                        Tree counterpart = counterpartOf(method, ours);
                        if (counterpart == null || isStub((MethodTree) counterpart)) {
                            named.add(node.get().internalName + " " + keyOf(method)
                                    + " is named by text the handle keeps and is "
                                    + (counterpart == null ? "cut" : "stubbed"));
                        }
                    }
                }
            }
            violations.addAll(named);
        }

        private void resolveHiddenMethods() {
            Set<Tree> stubbed = Collections.newSetFromMap(new IdentityHashMap<>());
            boolean changed = true;
            while (changed) {
                changed = false;
                for (HiddenMethod hidden : hiddenMethods) {
                    if (!stubbed.contains(hidden.ours()) && !isResolved(hidden, stubbed)) {
                        stubbed.add(hidden.ours());
                        changed = true;
                    }
                }
            }
            for (HiddenMethod hidden : hiddenMethods) {
                boolean resolved = !stubbed.contains(hidden.ours());
                if (resolved && hidden.stub()) {
                    violations.add(hidden.root().internalName + " stubs the hidden " + keyOf(hidden.stock())
                            + ", whose body names only what the handle keeps");
                } else if (!resolved && !hidden.stub()) {
                    violations.add(hidden.root().internalName + " keeps the hidden " + keyOf(hidden.stock())
                            + " verbatim, whose body names what the handle lacks");
                }
            }
        }

        private boolean isResolved(HiddenMethod hidden, Set<Tree> stubbed) {
            if (hidden.stock().getBody() == null) {
                return false;
            }
            References references = referencesOf(hidden.stock());
            for (String name : references.used) {
                if (!references.declared.contains(name) && !isResolved(name, hidden.root(), stubbed)) {
                    return false;
                }
            }
            for (List<String> selected : references.selected) {
                if (!references.declared.contains(selected.getFirst())
                        && !isResolved(selected.getFirst(), selected.getLast(), hidden.root(), stubbed)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isResolved(String name, RootClass context, Set<Tree> stubbed) {
            for (RootClass root : context.chain()) {
                if (root.outer == null && root.stock.getSimpleName().contentEquals(name)) {
                    return true;
                }
                if (root.declares(name)) {
                    return isKept(root, name, stubbed, context);
                }
                if (inherits(root.stock, root.internalName, name)) {
                    return true;
                }
            }
            return isStaticallyImported(name) || typeOf(List.of(name), context.internalName) != null;
        }

        private boolean isResolved(String qualifier, String name, RootClass context, Set<Tree> stubbed) {
            String owner = typeOf(List.of(qualifier), context.internalName);
            if (owner == null) {
                return true;
            }
            for (RootClass root : roots) {
                if (root.internalName.equals(owner)) {
                    return root.declares(name)
                            ? isKept(root, name, stubbed, context)
                            : inherits(root.stock, owner, name);
                }
            }
            ClassTree tree = handleClasses.get(owner);
            if (tree == null) {
                return hasMember(owner, name);
            }
            return tree.getMembers().stream().anyMatch(member -> isNamed(member, name)) || inherits(tree, owner, name);
        }

        private boolean isKept(RootClass root, String name, Set<Tree> stubbed, RootClass context) {
            List<Tree> ours = new ArrayList<>(handleClasses.get(root.internalName).getMembers());
            for (Tree member : root.stock.getMembers()) {
                if (isNamed(member, name)) {
                    Tree counterpart = counterpartOf(member, ours);
                    if (counterpart == null ? !isRevivable(member, root, context) : stubbed.contains(counterpart)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isRevivable(Tree member, RootClass root, RootClass context) {
            boolean hidden = switch (member) {
                case VariableTree field -> visibleField(root.android, field.getName().toString()).isEmpty();
                case ClassTree type -> !isVisibleNested(root.android, root.internalName + "$" + type.getSimpleName());
                default -> false;
            };
            return hidden && isKeepable(member) && context.chain().contains(root);
        }

        private boolean inherits(ClassTree tree, String internalName, String name) {
            String scope = outerOf(internalName) == null ? internalName : outerOf(internalName);
            for (Tree supertype : supertypesOf(tree)) {
                String resolved = typeOf(supertype, scope);
                if (resolved != null && hasField(resolved, name)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isPresentHead(MethodTree method, String owner) {
            Set<String> variables = new HashSet<>();
            List<Tree> types = new ArrayList<>();
            for (TypeParameterTree parameter : method.getTypeParameters()) {
                variables.add(parameter.getName().toString());
                types.addAll(parameter.getBounds());
            }
            if (method.getReturnType() != null) {
                types.add(method.getReturnType());
            }
            method.getParameters().forEach(parameter -> types.add(parameter.getType()));
            types.addAll(method.getThrows());
            return types.stream().allMatch(type -> isPresentType(type, owner, variables));
        }

        private boolean isPresentType(Tree type, String owner, Set<String> variables) {
            return switch (type) {
                case PrimitiveTypeTree primitive -> true;
                case ArrayTypeTree array -> isPresentType(array.getType(), owner, variables);
                case AnnotatedTypeTree annotated -> isPresentType(annotated.getUnderlyingType(), owner, variables);
                case ParameterizedTypeTree parameterized -> isPresentType(parameterized.getType(), owner, variables)
                        && parameterized.getTypeArguments().stream()
                                .allMatch(argument -> isPresentType(argument, owner, variables));
                case WildcardTree wildcard -> wildcard.getBound() == null
                        || isPresentType(wildcard.getBound(), owner, variables);
                case IdentifierTree identifier -> variables.contains(identifier.getName().toString())
                        || typeOf(identifier, owner) != null;
                default -> typeOf(type, owner) != null;
            };
        }

        private static boolean isKeepable(Tree member) {
            return switch (member) {
                case VariableTree field -> field.getModifiers().getFlags()
                        .containsAll(Set.of(Modifier.STATIC, Modifier.FINAL)) && field.getInitializer() != null
                        && isConstantType(field.getType()) && isConstantExpression(field.getInitializer());
                case ClassTree type -> type.getModifiers().getFlags().contains(Modifier.STATIC)
                        || type.getKind() != Tree.Kind.CLASS;
                default -> false;
            };
        }

        private static boolean isPublicConstant(VariableTree field) {
            return field.getModifiers().getFlags().contains(Modifier.PUBLIC) && isKeepable(field);
        }

        private static boolean isConstantExpression(ExpressionTree tree) {
            return switch (tree) {
                case LiteralTree literal -> true;
                case IdentifierTree identifier -> true;
                case MemberSelectTree select -> isConstantExpression(select.getExpression());
                case ParenthesizedTree parenthesized -> isConstantExpression(parenthesized.getExpression());
                case UnaryTree unary -> isConstantExpression(unary.getExpression());
                case TypeCastTree cast -> isConstantExpression(cast.getExpression());
                case BinaryTree binary -> isConstantExpression(binary.getLeftOperand())
                        && isConstantExpression(binary.getRightOperand());
                case ConditionalExpressionTree conditional -> isConstantExpression(conditional.getCondition())
                        && isConstantExpression(conditional.getTrueExpression())
                        && isConstantExpression(conditional.getFalseExpression());
                default -> false;
            };
        }

        private static boolean isConstantType(Tree type) {
            return type instanceof PrimitiveTypeTree || type.toString().equals("String")
                    || type.toString().equals("java.lang.String");
        }

        private void added(ClassTree ours, List<Tree> unmatched, String internalName) {
            String simpleName = ours.getSimpleName().toString();
            List<String> expected = List.of(
                    "@Added(reason = \"the id of the ART object this handle forwards to\") private final long id;",
                    "@Added(reason = \"the handle of the ART object received under its id\") public " + simpleName
                            + "(long id) { this.id = id; }",
                    "@Added(reason = \"the id this handle crosses to ART as\") @Override public long foreignId() { "
                            + "return id; }");
            List<? extends Tree> members = ours.getMembers();
            List<? extends Tree> last = members.subList(Math.max(0, members.size() - 3), members.size());
            if (!last.equals(unmatched)) {
                violations.add(internalName + " gains members other than the three added ones at its end: "
                        + unmatched.stream().map(this::describeOurs).toList());
                return;
            }
            for (int i = 0; i < last.size(); i++) {
                String text = handle.textOf(last.get(i)).replaceAll("\\s+", " ").trim();
                if (!text.equals(expected.get(i))) {
                    violations.add(internalName + " has an added member of another shape: " + text);
                }
                int index = members.size() - 3 + i;
                handleEdits.add(handle.regionOf(ours, index, index));
            }
        }

        private void removeAbsentAnnotations(Tree tree, String internalName) {
            if (tree == null) {
                return;
            }
            new TreeScanner<Void, String>() {
                @Override
                public Void visitClass(ClassTree type, String owner) {
                    String name = type.getSimpleName().isEmpty() ? owner : owner + "$" + type.getSimpleName();
                    return super.visitClass(type, handleClasses.containsKey(name) ? name : owner);
                }

                @Override
                public Void visitAnnotation(AnnotationTree annotation, String owner) {
                    if (!isPresent(annotation, owner)) {
                        removeAnnotation(source, sourceEdits, annotation);
                    }
                    return null;
                }
            }.scan(tree, internalName);
        }

        private void removeAbsentAnnotations(List<? extends Tree> trees, String internalName) {
            for (Tree tree : trees) {
                removeAbsentAnnotations(tree, internalName);
            }
        }

        private void removeAnnotation(Parsed parsed, List<Edit> edits, AnnotationTree annotation) {
            if (!removedAnnotations.add(annotation)) {
                return;
            }
            int start = parsed.start(annotation);
            int end = parsed.end(annotation);
            while (end < parsed.text.length() && (parsed.text.charAt(end) == ' ' || parsed.text.charAt(end) == '\t')) {
                end++;
            }
            int first = parsed.lineOf(start);
            int last = parsed.lineOf(parsed.end(annotation) - 1);
            boolean alone = parsed.text.substring(parsed.lineStart(first), start).isBlank()
                    && (end == parsed.text.length() || parsed.text.charAt(end) == '\n');
            edits.add(alone ? parsed.lines(first, last) : new Edit(start, end, ""));
        }

        private boolean isPresent(AnnotationTree annotation, String owner) {
            if (typeOf(annotation.getAnnotationType(), owner) == null) {
                return false;
            }
            for (ExpressionTree argument : annotation.getArguments()) {
                if (!isPresent(argument instanceof AssignmentTree assignment ? assignment.getExpression() : argument,
                        owner)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isPresent(ExpressionTree tree, String owner) {
            return switch (tree) {
                case NewArrayTree array -> array.getInitializers() == null
                        || array.getInitializers().stream().allMatch(element -> isPresent(element, owner));
                case ParenthesizedTree parenthesized -> isPresent(parenthesized.getExpression(), owner);
                case UnaryTree unary -> isPresent(unary.getExpression(), owner);
                case BinaryTree binary -> isPresent(binary.getLeftOperand(), owner)
                        && isPresent(binary.getRightOperand(), owner);
                case ConditionalExpressionTree conditional -> isPresent(conditional.getCondition(), owner)
                        && isPresent(conditional.getTrueExpression(), owner)
                        && isPresent(conditional.getFalseExpression(), owner);
                case TypeCastTree cast -> isPresent(cast.getExpression(), owner);
                case AnnotationTree annotation -> isPresent(annotation, owner);
                case IdentifierTree identifier -> hasValue(identifier.getName().toString(), owner);
                case MemberSelectTree select -> select.getIdentifier().contentEquals("class")
                        ? typeOf(select.getExpression(), owner) != null
                        : hasMember(typeOf(select.getExpression(), owner), select.getIdentifier().toString());
                default -> true;
            };
        }

        private boolean hasValue(String name, String owner) {
            for (String type = owner; type != null; type = outerOf(type)) {
                ClassTree tree = handleClasses.get(type);
                if (tree == null) {
                    continue;
                }
                if (tree.getMembers().stream().anyMatch(
                        member -> member instanceof VariableTree field && field.getName().contentEquals(name))) {
                    return true;
                }
                if (inherits(tree, type, name)) {
                    return true;
                }
            }
            return isStaticallyImported(name);
        }

        private boolean isStaticallyImported(String name) {
            for (ImportTree tree : handle.unit.getImports()) {
                List<String> segments = segmentsOf(tree.getQualifiedIdentifier());
                if (tree.isStatic() && (segments.getLast().equals(name) || segments.getLast().equals("*")
                        && hasMember(qualifiedType(segments.subList(0, segments.size() - 1)), name))) {
                    return true;
                }
            }
            return false;
        }

        private static String outerOf(String internalName) {
            return internalName.contains("$") ? internalName.substring(0, internalName.lastIndexOf('$')) : null;
        }

        private static List<Tree> supertypesOf(ClassTree tree) {
            List<Tree> supertypes = new ArrayList<>();
            if (tree.getExtendsClause() != null) {
                supertypes.add(tree.getExtendsClause());
            }
            supertypes.addAll(tree.getImplementsClause());
            return supertypes;
        }

        private String typeOf(Tree tree, String owner) {
            List<String> segments = segmentsOf(tree);
            return segments == null ? null : typeOf(segments, owner);
        }

        private String typeOf(List<String> segments, String owner) {
            String first = segments.getFirst();
            String base = null;
            for (String type = owner; type != null && base == null; type = outerOf(type)) {
                if (!type.contains("$") && type.endsWith("/" + first)) {
                    base = type;
                } else if (handleClasses.containsKey(type + "$" + first)) {
                    base = type + "$" + first;
                }
            }
            if (base == null) {
                for (ImportTree imported : handle.unit.getImports()) {
                    List<String> importSegments = segmentsOf(imported.getQualifiedIdentifier());
                    if (!imported.isStatic() && importSegments.getLast().equals(first)) {
                        base = qualifiedType(importSegments);
                    }
                }
            }
            if (base == null && exists(packagePath + "/" + first)) {
                base = packagePath + "/" + first;
            }
            if (base == null && exists("java/lang/" + first)) {
                base = "java/lang/" + first;
            }
            if (base == null) {
                return qualifiedType(segments);
            }
            for (String segment : segments.subList(1, segments.size())) {
                base = base + "$" + segment;
                if (!exists(base)) {
                    return null;
                }
            }
            return base;
        }

        private String qualifiedType(List<String> segments) {
            for (int split = segments.size() - 1; split >= 1; split--) {
                String name = String.join("/", segments.subList(0, split)) + "/"
                        + String.join("$", segments.subList(split, segments.size()));
                if (exists(name)) {
                    return name;
                }
            }
            return null;
        }

        private boolean exists(String internalName) {
            if (handleClasses.containsKey(internalName)
                    || Files.isRegularFile(CLASSES.resolve(internalName + ".class"))) {
                return true;
            }
            return jdkClass(internalName) != null;
        }

        private boolean hasMember(String owner, String name) {
            if (owner == null) {
                return false;
            }
            ClassTree tree = handleClasses.get(owner);
            if (tree != null) {
                return tree.getMembers().stream()
                        .anyMatch(member -> member instanceof VariableTree field && field.getName().contentEquals(name)
                                || member instanceof ClassTree type && type.getSimpleName().contentEquals(name));
            }
            ClassModel model = emulationModel(owner);
            if (model != null) {
                return hasField(owner, name)
                        || model.methods().stream().anyMatch(method -> method.methodName().equalsString(name))
                        || exists(owner + "$" + name);
            }
            Class<?> type = jdkClass(owner);
            return type != null && (Stream.of(type.getFields()).anyMatch(field -> field.getName().equals(name))
                    || Stream.of(type.getMethods()).anyMatch(method -> method.getName().equals(name))
                    || Stream.of(type.getClasses()).anyMatch(nested -> nested.getSimpleName().equals(name)));
        }

        private boolean hasField(String owner, String name) {
            ClassTree tree = handleClasses.get(owner);
            if (tree != null) {
                return tree.getMembers().stream().anyMatch(
                        member -> member instanceof VariableTree field && field.getName().contentEquals(name));
            }
            ClassModel model = emulationModel(owner);
            if (model == null) {
                Class<?> type = jdkClass(owner);
                return type != null && Stream.of(type.getFields()).anyMatch(field -> field.getName().equals(name));
            }
            if (model.fields().stream().anyMatch(field -> field.fieldName().equalsString(name))) {
                return true;
            }
            List<ClassDesc> supertypes = new ArrayList<>();
            model.superclass().ifPresent(superclass -> supertypes.add(superclass.asSymbol()));
            model.interfaces().forEach(type -> supertypes.add(type.asSymbol()));
            return supertypes.stream().anyMatch(type -> hasField(internalNameOf(type), name));
        }

        private ClassModel emulationModel(String internalName) {
            Path file = CLASSES.resolve(internalName + ".class");
            if (!Files.isRegularFile(file)) {
                return null;
            }
            try {
                return ClassFile.of().parse(file);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        private Class<?> jdkClass(String internalName) {
            try {
                return Class.forName(internalName.replace('/', '.'), false, ClassLoader.getPlatformClassLoader());
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        private ClassModel androidModel(String internalName) {
            ZipEntry entry = jar.getEntry(internalName + ".class");
            if (entry == null) {
                return null;
            }
            try {
                return ClassFile.of().parse(jar.getInputStream(entry).readAllBytes());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        private Optional<FieldModel> visibleField(ClassModel android, String name) {
            return android.fields().stream()
                    .filter(field -> isVisible(field.flags().flags()) && field.fieldName().equalsString(name))
                    .findFirst();
        }

        private static boolean isDeclared(MethodModel method) {
            return isVisible(method.flags().flags()) && !method.flags().has(AccessFlag.SYNTHETIC)
                    && !method.flags().has(AccessFlag.BRIDGE);
        }

        private Optional<MethodModel> visibleMethod(ClassModel android, String key) {
            return android.methods().stream().filter(Check::isDeclared)
                    .filter(method -> androidKeyOf(android, method).equals(key)).findFirst();
        }

        private boolean isVisibleNested(ClassModel android, String internalName) {
            return innerClassOf(android, internalName).map(info -> isVisible(info.flags())).orElse(false);
        }

        private boolean isVisibleInterface(ClassModel android, String internalName) {
            return innerClassOf(android, internalName)
                    .map(info -> isVisible(info.flags()) && info.flags().contains(AccessFlag.INTERFACE)).orElse(false);
        }

        private Optional<InnerClassInfo> innerClassOf(ClassModel android, String internalName) {
            return android.findAttribute(Attributes.innerClasses()).flatMap(attribute -> attribute.classes().stream()
                    .filter(info -> info.innerClass().asInternalName().equals(internalName)).findFirst());
        }

        private boolean namesAbsentType(MethodModel method) {
            MethodSignature signature = method.findAttribute(Attributes.signature())
                    .map(attribute -> attribute.asMethodSignature())
                    .orElseGet(() -> MethodSignature.of(method.methodTypeSymbol()));
            List<Signature> signatures = new ArrayList<>(signature.arguments());
            signatures.add(signature.result());
            if (signature.throwableSignatures().isEmpty()) {
                method.findAttribute(Attributes.exceptions()).ifPresent(attribute -> attribute.exceptions()
                        .forEach(entry -> signatures.add(Signature.ClassTypeSig.of(entry.asSymbol()))));
            } else {
                signatures.addAll(signature.throwableSignatures());
            }
            List<ClassDesc> types = new ArrayList<>();
            signatures.forEach(each -> collect(each, types));
            for (Signature.TypeParam parameter : signature.typeParameters()) {
                parameter.classBound().ifPresent(bound -> collect(bound, types));
                parameter.interfaceBounds().forEach(bound -> collect(bound, types));
            }
            for (ClassDesc type : types) {
                ClassDesc element = type;
                while (element.isArray()) {
                    element = element.componentType();
                }
                if (!element.isPrimitive() && !exists(internalNameOf(element))) {
                    return true;
                }
            }
            return false;
        }

        private void collect(Signature signature, List<ClassDesc> types) {
            switch (signature) {
                case Signature.ArrayTypeSig array -> collect(array.componentSignature(), types);
                case Signature.ClassTypeSig type -> {
                    types.add(type.classDesc());
                    for (Signature.TypeArg argument : type.typeArgs()) {
                        if (argument instanceof Signature.TypeArg.Bounded bounded) {
                            collect(bounded.boundType(), types);
                        }
                    }
                }
                default -> {
                }
            }
        }

        private String androidKeyOf(ClassModel android, MethodModel method) {
            MethodSignature signature = method.findAttribute(Attributes.signature())
                    .map(attribute -> attribute.asMethodSignature())
                    .orElseGet(() -> MethodSignature.of(method.methodTypeSymbol()));
            List<String> parameters = new ArrayList<>();
            for (Signature argument : signature.arguments()) {
                parameters.add(simpleNameOf(android, argument));
            }
            return method.methodName().stringValue() + "(" + String.join(",", parameters) + ")";
        }

        private String simpleNameOf(ClassModel android, Signature signature) {
            return switch (signature) {
                case Signature.BaseTypeSig base -> ClassDesc.ofDescriptor(base.signatureString()).displayName();
                case Signature.ArrayTypeSig array -> simpleNameOf(android, array.componentSignature()) + "[]";
                case Signature.TypeVarSig variable -> variable.identifier();
                case Signature.ClassTypeSig type -> {
                    String internalName = internalNameOf(type.classDesc());
                    yield innerClassOf(android, internalName).flatMap(InnerClassInfo::innerName)
                            .map(name -> name.stringValue())
                            .orElse(internalName.substring(internalName.lastIndexOf('/') + 1));
                }
            };
        }

        private static String describe(Tree member) {
            return switch (member) {
                case VariableTree field -> field.getName().toString();
                case MethodTree method -> keyOf(method);
                case ClassTree type -> type.getSimpleName().toString();
                default -> member.getKind().toString();
            };
        }

        private String describeOurs(Tree member) {
            return handle.textOf(member).lines().filter(line -> !line.isBlank() && !line.trim().startsWith("@"))
                    .findFirst().orElse("").trim();
        }
    }

    private static boolean isNamed(Tree member, String name) {
        return switch (member) {
            case VariableTree field -> field.getName().contentEquals(name);
            case MethodTree method -> method.getName().contentEquals(name);
            case ClassTree type -> type.getSimpleName().contentEquals(name);
            default -> false;
        };
    }

    private static boolean isVisible(Set<AccessFlag> flags) {
        return flags.contains(AccessFlag.PUBLIC) || flags.contains(AccessFlag.PROTECTED);
    }

    private static String internalNameOf(ClassDesc type) {
        String descriptor = type.descriptorString();
        return descriptor.substring(1, descriptor.length() - 1);
    }

    private static String keyOf(MethodTree method) {
        List<String> parameters = new ArrayList<>();
        for (VariableTree parameter : method.getParameters()) {
            parameters.add(keyOf(parameter.getType()));
        }
        return method.getName() + "(" + String.join(",", parameters) + ")";
    }

    private static String keyOf(Tree type) {
        return switch (type) {
            case PrimitiveTypeTree primitive -> primitive.getPrimitiveTypeKind().name().toLowerCase(Locale.ROOT);
            case ArrayTypeTree array -> keyOf(array.getType()) + "[]";
            case ParameterizedTypeTree parameterized -> keyOf(parameterized.getType());
            case AnnotatedTypeTree annotated -> keyOf(annotated.getUnderlyingType());
            case MemberSelectTree select -> select.getIdentifier().toString();
            case IdentifierTree identifier -> identifier.getName().toString();
            default -> type.toString();
        };
    }

    private static List<String> segmentsOf(Tree tree) {
        return switch (tree) {
            case IdentifierTree identifier -> new ArrayList<>(List.of(identifier.getName().toString()));
            case MemberSelectTree select -> {
                List<String> segments = segmentsOf(select.getExpression());
                if (segments != null) {
                    segments.add(select.getIdentifier().toString());
                }
                yield segments;
            }
            case ParameterizedTypeTree parameterized -> segmentsOf(parameterized.getType());
            case AnnotatedTypeTree annotated -> segmentsOf(annotated.getUnderlyingType());
            default -> null;
        };
    }
}
