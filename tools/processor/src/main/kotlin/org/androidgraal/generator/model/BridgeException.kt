package org.androidgraal.generator.model

class BridgeException(message: String) : RuntimeException(message)

internal fun fail(message: String): Nothing = throw BridgeException("android-graal: $message")
