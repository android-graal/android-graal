package org.androidgraal.handles.generator.model

internal enum class Verdict(val word: String) {
    HANDLE("handle"),
    VALUE("value"),
}

internal class BoundaryEntry(val internalName: String, val verdict: Verdict) {
    val line: String = "${internalName.replace('/', '.')} ${verdict.word}"
}

internal class ProxyBoundary(val entries: List<BoundaryEntry>) {
    val handles: List<String> = entries.filter { it.verdict == Verdict.HANDLE }.map { it.internalName }

    val values: Set<String> = entries.filter { it.verdict == Verdict.VALUE }.map { it.internalName }.toSet()
}
