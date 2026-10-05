package org.androidgraal.generator.scan

import javax.lang.model.element.Element

internal class Errors {

    private val found = linkedMapOf<String, Element?>()

    val all: List<Pair<String, Element?>>
        get() = found.toList()

    fun isEmpty(): Boolean = found.isEmpty()

    fun isNotEmpty(): Boolean = found.isNotEmpty()

    fun add(message: String, at: Element? = null) {
        found.putIfAbsent(message, at)
    }
}
