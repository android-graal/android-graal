package org.androidgraal.substrate

import java.io.File

fun interface ProcessRunner {
    fun run(command: List<String>, workingDir: File): Int

    fun runOrFail(tool: String, command: List<String>, workingDir: File) {
        val exit = run(command, workingDir)
        check(exit == 0) { "$tool failed with exit code $exit" }
    }
}

internal fun argsIf(condition: Boolean, vararg args: String): Array<out String> = if (condition) args else emptyArray()
