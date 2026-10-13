package org.androidgraal.gradle

import org.apache.commons.io.FileUtils
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.work.DisableCachingByDefault

@DisableCachingByDefault(because = "writes two small files")
abstract class GenerateNativeImageTask : BaseTask("generateNativeImage") {

    @get:Input
    abstract val imageName: Property<String>

    @get:Input
    abstract val packageName: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:OutputFile
    abstract val manifestFile: RegularFileProperty

    fun setup(variantName: String, extension: AndroidGraalExtension, namespace: Provider<String>) {
        group = "build"
        description = "Generates NativeImage in the app's namespace, which starts the image, and the manifest of the" +
            " provider that hands the application to it ($variantName)."
        this.variantName.convention(variantName)
        this.imageName.convention(extension.imageName)
        this.packageName.convention(namespace)
    }

    override fun execute() {
        val dir = outputDir.get().asFile
        FileUtils.deleteDirectory(dir)

        val packageName = packageName.get()
        val file = dir.resolve(packageName.replace('.', '/')).resolve("NativeImage.java")
        file.parentFile.mkdirs()
        file.writeText(source(packageName, imageName.get()))
        log.write("wrote $file\n".toByteArray())

        val manifest = manifestFile.get().asFile
        manifest.parentFile.mkdirs()
        manifest.writeText(MANIFEST)
        log.write("wrote $manifest\n".toByteArray())
    }

    companion object {
        fun source(packageName: String, imageName: String): String = """
            |package $packageName;
            |
            |import org.androidgraal.art.Image;
            |
            |public final class NativeImage {
            |
            |    public static final String NAME = "$imageName";
            |
            |    private NativeImage() {
            |    }
            |
            |    public static void start() {
            |        Image.start(NAME);
            |    }
            |
            |    public static boolean isStarted() {
            |        return Image.started();
            |    }
            |
            |    public static <T> T createRoot(Class<T> root) {
            |        return Image.createRoot(root);
            |    }
            |
            |    public static void releaseProxy(Object proxy) {
            |        Image.handles().release(proxy);
            |    }
            |}
            |
        """.trimMargin()

        // AGP's manifest merger fills in ${applicationId} in every manifest it merges.
        val MANIFEST = """
            |<manifest xmlns:android="http://schemas.android.com/apk/res/android">
            |    <application>
            |        <provider
            |            android:name="org.androidgraal.art.ApplicationProvider"
            |            android:authorities="${'$'}{applicationId}.androidgraal"
            |            android:exported="false" />
            |    </application>
            |</manifest>
            |
        """.trimMargin()
    }
}
