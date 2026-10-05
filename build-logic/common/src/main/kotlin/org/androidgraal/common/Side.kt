package org.androidgraal.common

/** The two sides of the interop boundary. */
enum class Side(
    /** The side's name in the SDK and plugin. */
    val feature: String,
    val release: Int,
    val label: String,
) {
    ART("art", 17, "ART"),
    SVM("svm", 25, "the image"),
    ;

    val title: String = feature.replaceFirstChar(Char::uppercase)

    fun other(): Side = if (this == ART) SVM else ART

    companion object {
        fun sharedRelease(): Int = entries.minOf { it.release }
    }
}
