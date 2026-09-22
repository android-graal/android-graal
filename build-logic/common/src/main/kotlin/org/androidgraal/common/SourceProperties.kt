package org.androidgraal.common

import org.semver4j.Semver
import java.io.File
import java.util.Properties

object SourceProperties {

    const val FILE_NAME = "source.properties"

    /** `Pkg.Revision` of the `source.properties` at [file]. */
    fun revision(file: File): Semver = revisionOrNull(file)
        ?: error("$REVISION \"${text(file)}\" in $file is not a version (major.minor.patch[-prerelease])")

    fun revisionOrNull(file: File): Semver? = Semver.parse(text(file))

    private fun text(file: File): String {
        if (!file.isFile) {
            error("no $file")
        }
        val properties = Properties()
        file.bufferedReader().use(properties::load)
        return properties.getProperty(REVISION) ?: error("no $REVISION in $file")
    }

    private const val REVISION = "Pkg.Revision"
}
