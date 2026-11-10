package org.androidgraal.handles.generator.model

internal sealed interface Fate

internal sealed interface FieldFate : Fate

internal sealed interface TypeFate : Fate

internal sealed interface MethodFate : Fate {
    val note: String?
}

internal sealed interface Declared : MethodFate {
    val method: Method
    val level: Int?
}

internal object Kept : FieldFate, TypeFate

internal class Forwarded(override val method: Method, override val level: Int?, val wire: Wire) : Declared {
    override val note: String? = null
}

internal class Unsupported(override val method: Method, override val level: Int?) : Declared {
    override val note: String? = null
}

internal object Verbatim : MethodFate {
    override val note: String = "is hidden and kept verbatim"
}

internal object Stubbed : MethodFate {
    override val note: String = "is hidden and stubbed"
}

internal enum class Removed(override val note: String?) :
    FieldFate,
    TypeFate,
    MethodFate {
    NOT_KEPT(null),
    LACKING_TYPE("names a type the emulation lacks"),
    HIDDEN_LACKING_TYPE("is hidden and names a type the emulation lacks"),
}
