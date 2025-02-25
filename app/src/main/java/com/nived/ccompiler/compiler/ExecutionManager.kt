package com.nived.ccompiler.compiler

import java.io.File

class ExecutionManager {

    fun runBinary(binaryPath: String): String {
        val process = ProcessBuilder()
            .command(binaryPath)
            .redirectErrorStream(true)
            .start()

        return process.inputStream.bufferedReader().readText()
    }
}
