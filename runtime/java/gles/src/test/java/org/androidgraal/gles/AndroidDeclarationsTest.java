package org.androidgraal.gles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.classfile.Attributes;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.classfile.FieldModel;
import java.lang.classfile.MethodModel;
import java.lang.classfile.constantpool.ClassEntry;
import java.lang.reflect.AccessFlag;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipFile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AndroidDeclarationsTest {

    @ParameterizedTest
    @ValueSource(strings = {"android/opengl/GLES20", "android/opengl/GLES30"})
    void theClassDeclaresWhatAndroidDeclares(String internalName) throws IOException {
        Path androidJar = Path.of(System.getProperty("gles.androidJar"));
        assumeTrue(Files.isRegularFile(androidJar), "the compileSdk's platform is not installed");
        ClassModel android;
        try (ZipFile jar = new ZipFile(androidJar.toFile())) {
            android = ClassFile.of().parse(jar.getInputStream(jar.getEntry(internalName + ".class")).readAllBytes());
        }
        ClassModel ours;
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(internalName + ".class")) {
            ours = ClassFile.of().parse(in.readAllBytes());
        }

        assertEquals(declarations(android), declarations(ours));
    }

    private static Set<String> declarations(ClassModel model) {
        Set<String> declared = new TreeSet<>();
        declared.add("class " + flags(model.flags().flags()) + " extends "
                + model.superclass().map(ClassEntry::asInternalName).orElse("-"));
        for (FieldModel field : model.fields()) {
            if (field.flags().has(AccessFlag.PUBLIC)) {
                String value = field.findAttribute(Attributes.constantValue())
                        .map(attribute -> attribute.constant().constantValue().toString()).orElse("-");
                declared.add("field " + flags(field.flags().flags()) + " " + field.fieldName().stringValue() + " "
                        + field.fieldType().stringValue() + " = " + value);
            }
        }
        for (MethodModel method : model.methods()) {
            if (method.flags().has(AccessFlag.PUBLIC)) {
                declared.add("method " + flags(method.flags().flags()) + " " + method.methodName().stringValue()
                        + method.methodType().stringValue());
            }
        }
        return declared;
    }

    private static String flags(Set<AccessFlag> flags) {
        EnumSet<AccessFlag> kept = flags.isEmpty() ? EnumSet.noneOf(AccessFlag.class) : EnumSet.copyOf(flags);
        kept.removeAll(Set.of(AccessFlag.NATIVE, AccessFlag.SUPER));
        return kept.toString();
    }
}
