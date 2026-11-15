package org.androidgraal.handles.generator.scan

import org.androidgraal.handles.generator.model.fail
import java.io.File
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants

internal class Levels(private val classes: Map<String, ClassLevels>) {
    fun sinceOf(owner: String): Int = entryOf(owner).since

    fun lists(owner: String, member: String): Boolean = member in entryOf(owner).members

    fun sinceOf(owner: String, member: String, declaring: String?): Int {
        val levels = entryOf(owner)
        val own = levels.members[member]
        if (own != null || declaring == null) return own ?: levels.since
        return levels.implemented[declaring] ?: fail("api-versions.xml does not list $declaring on $owner")
    }

    private fun entryOf(owner: String): ClassLevels = classes[owner] ?: fail("$owner is not in api-versions.xml")
}

internal class ClassLevels(val since: Int, val members: Map<String, Int>, val implemented: Map<String, Int>)

internal fun levelsOf(file: File): Levels {
    val classes = HashMap<String, ClassLevels>()
    file.inputStream().use { input ->
        val reader = XMLInputFactory.newInstance().createXMLStreamReader(input)
        var owner = ""
        var since = 1
        var members = HashMap<String, Int>()
        var implemented = HashMap<String, Int>()
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamConstants.START_ELEMENT -> when (reader.localName) {
                    "class" -> {
                        owner = reader.getAttributeValue(null, "name")
                        since = reader.getAttributeValue(null, "since")?.toInt()
                            ?: fail("api-versions.xml gives $owner no level")
                        members = HashMap()
                        implemented = HashMap()
                    }

                    "method" -> members[reader.getAttributeValue(null, "name")] =
                        reader.getAttributeValue(null, "since")?.toInt() ?: since

                    "implements" -> implemented[reader.getAttributeValue(null, "name")] =
                        reader.getAttributeValue(null, "since")?.toInt() ?: since
                }

                XMLStreamConstants.END_ELEMENT -> if (reader.localName == "class") {
                    classes[owner] = ClassLevels(since, members, implemented)
                }
            }
        }
    }
    return Levels(classes)
}
