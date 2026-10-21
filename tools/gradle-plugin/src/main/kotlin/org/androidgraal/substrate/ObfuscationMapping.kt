package org.androidgraal.substrate

import java.security.MessageDigest

/** An R8 mapping: its header is the comment lines R8 writes before the first class. */
class ObfuscationMapping(text: String) {

    val header: List<String>

    val body: String

    init {
        val lines = text.split("\n")
        header = lines.takeWhile { it.startsWith("#") }
        body = lines.drop(header.size).joinToString("\n").trim()
    }

    companion object {
        /** R8's `pg_map_hash` is the SHA-256 of the text after the header. */
        const val MAP_HASH = "# pg_map_hash: SHA-256 "

        fun merge(app: String, image: String): String {
            val appMapping = ObfuscationMapping(app)
            val appBody = appMapping.body

            val imageMapping = ObfuscationMapping(image)
            val imageBody = imageMapping.body
            val newBody = (appBody + "\n" + imageBody).trim()
            val newHeader = appMapping.header.map {
                if (it.startsWith(MAP_HASH)) MAP_HASH + sha256(newBody) else it
            }

            return newHeader.joinToString("\n") + "\n" + newBody
        }

        fun sha256(text: String): String {
            val digest = MessageDigest.getInstance("SHA-256").digest(text.toByteArray())
            return digest.joinToString("") { "%02x".format(it) }
        }
    }
}
