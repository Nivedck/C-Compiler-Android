package com.nived.ccompiler.compiler

import java.io.File

object ExecutionManager {
    fun runBinary(binaryPath: String): String {
        val process = Runtime.getRuntime().exec(binaryPath)
        val output = process.inputStream.bufferedReader().readText()
        process.waitFor()
        return output
    }
}
