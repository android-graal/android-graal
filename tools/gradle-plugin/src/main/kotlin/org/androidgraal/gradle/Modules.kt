package org.androidgraal.gradle

import org.androidgraal.common.Side

internal object Modules {
    const val HOSTED = "$PLUGIN_GROUP:hosted:$PLUGIN_VERSION"
    const val GRAAL_WORD = "$PLUGIN_GROUP.graal:word:$PLUGIN_VERSION"
    const val GRAAL_NATIVEIMAGE = "$PLUGIN_GROUP.graal:nativeimage:$PLUGIN_VERSION"
    const val TOOLCHAIN = "$PLUGIN_GROUP:toolchain:$PLUGIN_VERSION"
    const val PROCESSOR = "$PLUGIN_GROUP:processor:$PLUGIN_VERSION"
    const val SVM_RUNTIME = "$PLUGIN_GROUP:svm:$PLUGIN_VERSION"
    const val ART_RUNTIME = "$PLUGIN_GROUP:art:$PLUGIN_VERSION"
    const val EMULATION_SVM = "$PLUGIN_GROUP.emulation:svm:$PLUGIN_VERSION"
    const val EMULATION_ART = "$PLUGIN_GROUP.emulation:art:$PLUGIN_VERSION"

    fun runtime(side: Side): String = when (side) {
        Side.SVM -> SVM_RUNTIME
        Side.ART -> ART_RUNTIME
    }
}
