package com.nived.ccompiler.compiler

import java.io.File
import java.io.IOException

class ExecutionManager {

    fun runBinary(binaryPath: String): String {
        val binaryFile = File(binaryPath)

        if (!binaryFile.exists()) {
            return "Error: Binary file does not exist."
        }

        try {
            // Ensure execution permission
            binaryFile.setExecutable(true)

            val process = ProcessBuilder()
                .command(binaryPath)
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            process.waitFor()

            return output
        } catch (e: IOException) {
            return "Execution error: ${e.message}"
        }
    }
}
