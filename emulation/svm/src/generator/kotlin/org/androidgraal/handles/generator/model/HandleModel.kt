package org.androidgraal.handles.generator.model

import java.nio.ByteBuffer
import java.security.MessageDigest

internal class HandleModel(private val boundary: ProxyBoundary, val roots: List<Root>) {
    val fingerprint: Long = ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(report().toByteArray())).long

    private fun report(): String {
        val out = StringBuilder("tag id << 16 | ordinal\n")
        boundary.entries.forEach { out.append("boundary ${it.line}\n") }
        for (root in roots) {
            for (forwarded in root.forwarded) {
                val wire = forwarded.wire
                out.append("wire ${root.internalName} ${forwarded.method.key} ${wire.endPoint} ${wire.jniDescriptor}")
                out.append(" ${forwarded.level ?: "-"}\n")
            }
        }
        return out.toString()
    }
}
