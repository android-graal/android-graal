package org.androidgraal.gles.generator

import com.palantir.javapoet.ParameterSpec
import org.androidgraal.gles.generator.Passing.Direct
import org.androidgraal.gles.generator.Passing.Pinned
import java.lang.constant.ClassDesc
import java.lang.constant.ConstantDescs.CD_String
import java.lang.constant.ConstantDescs.CD_void

internal fun memberOf(method: JavaMethod, functions: Map<String, CFunction>): Member {
    val names = BY_HAND[method.key]
    if (names != null) return handwrittenOf(method, names)
    val function = functions.getValue(method.name)
    val returns = typeNameOf(method.type.returnType())
    return Wrapper(method.name, returns, function, argsOf(method, function), resultOf(method))
}

private fun handwrittenOf(method: JavaMethod, names: List<String>): Member {
    val types = method.type.parameterList()
    check(names.size == types.size)
    val parameters = types.zip(names) { type, name -> ParameterSpec.builder(typeNameOf(type), name).build() }
    return ByHand(method.name, typeNameOf(method.type.returnType()), parameters)
}

private fun argsOf(method: JavaMethod, function: CFunction): List<Arg> {
    val types = method.type.parameterList()
    val positions = types.filterIndexed { index, _ -> index == 0 || !types[index - 1].isArray }
    check(function.params.size == positions.size)
    return function.params.zip(positions) { param, type -> argOf(function, param, type) }
}

private fun argOf(function: CFunction, param: CParam, type: ClassDesc): Arg {
    val typeName = typeNameOf(type)
    if (!param.type.isPointer) return Scalar(param, typeName)
    if (param.type.isString) return Text(param, typeName)
    if (type.isPrimitive) return Offset(param)
    return when (val passing = PASSINGS.getValue(ParamKey(function.name, param.name))) {
        Direct -> DirectBuffer(param, typeName)
        is Pinned -> if (type.isArray) PinnedArray(param, typeName, passing) else PinnedBuffer(param, typeName, passing)
    }
}

private fun resultOf(method: JavaMethod): Result = when (method.type.returnType()) {
    CD_void -> Result.VOID
    CD_String -> Result.TEXT
    else -> Result.VALUE
}
