package org.androidgraal.handles.generator.scan

import org.androidgraal.handles.generator.model.BoundaryEntry
import org.androidgraal.handles.generator.model.ProxyBoundary
import org.androidgraal.handles.generator.model.UNWRITTEN
import org.androidgraal.handles.generator.model.Verdict
import org.androidgraal.handles.generator.model.fail
import java.io.File

internal fun proxyBoundaryOf(file: File): ProxyBoundary {
    val boundary = ProxyBoundary(file.readLines().map(String::trim).map(::entryOf))
    for (entry in boundary.entries) {
        val outer = entry.internalName.substringBeforeLast('$', "")
        when (entry.verdict) {
            Verdict.HANDLE -> if (outer.isNotEmpty()) {
                fail("${entry.line} is a handle nested in a class, $UNWRITTEN")
            }

            Verdict.VALUE -> if (outer !in boundary.handles) {
                fail("${entry.line} is not nested in a root of the proxy boundary, $UNWRITTEN")
            }
        }
    }
    return boundary
}

private fun entryOf(line: String): BoundaryEntry {
    val parts = line.split(' ')
    val verdict = Verdict.entries.firstOrNull { parts.size == 2 && it.word == parts[1] }
        ?: fail("a proxy boundary line is a binary name and handle or value: $line")
    return BoundaryEntry(parts[0].replace('.', '/'), verdict)
}
