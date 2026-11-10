package org.androidgraal.handles.generator.model

internal const val UNWRITTEN = "a shape this generator does not write yet"

internal class HandleException(message: String) : RuntimeException(message)

internal fun fail(message: String): Nothing = throw HandleException(message)
