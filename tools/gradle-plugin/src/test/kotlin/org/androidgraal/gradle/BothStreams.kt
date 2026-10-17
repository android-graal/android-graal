@file:JvmName("BothStreams")

package org.androidgraal.gradle

fun main() {
    System.out.println("to standard output")
    System.err.println("to standard error")
    System.exit(1)
}
