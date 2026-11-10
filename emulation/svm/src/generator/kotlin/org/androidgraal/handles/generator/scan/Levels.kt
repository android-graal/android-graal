package org.androidgraal.handles.generator.scan

import org.androidgraal.handles.generator.model.fail
import java.io.File
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants

internal class Levels(private val classes: Map<String, ClassLevels>) {
    fun sinceOf(owner: String): Int = entryOf(owner).since

    fun sinceOf(owner: String, member: String): Int {
        val levels = entryOf(owner)
        return levels.members[member] ?: levels.since
    }

    private fun entryOf(owner: String): ClassLevels = classes[owner] ?: fail("$owner is not in api-versions.xml")
}

internal class ClassLevels(val since: Int, val members: Map<String, Int>)

internal fun levelsOf(file: File): Levels {
    val classes = HashMap<String, ClassLevels>()
    file.inputStream().use { input ->
        val reader = XMLInputFactory.newInstance().createXMLStreamReader(input)
        var owner = ""
        var since = 1
        var members = HashMap<String, Int>()
        while (reader.hasNext()) {
            when (reader.next()) {
                XMLStreamConstants.START_ELEMENT -> when (reader.localName) {
                    "class" -> {
                        owner = reader.getAttributeValue(null, "name")
                        since = reader.getAttributeValue(null, "since")?.toInt()
                            ?: fail("api-versions.xml gives $owner no level")
                        members = HashMap()
                    }

                    "method" -> {
                        val level = reader.getAttributeValue(null, "since")
                        if (level != null) members[reader.getAttributeValue(null, "name")] = level.toInt()
                    }
                }

                XMLStreamConstants.END_ELEMENT -> if (reader.localName == "class") {
                    classes[owner] = ClassLevels(since, members)
                }
            }
        }
    }
    return Levels(classes)
}
