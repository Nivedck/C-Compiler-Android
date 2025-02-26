package com.nived.ccompiler.compiler

import android.util.Log  // ✅ Import Log
import java.io.File
import java.io.IOException

class ExecutionManager {

    fun runBinary(binaryPath: String): String {
        val binaryFile = File(binaryPath) // ✅ Only declare once

        if (!binaryFile.exists()) {
            Log.e("ExecutionManager", "Binary file does not exist: $binaryPath")
            return "Error: Binary file does not exist at $binaryPath"
        }

        try {
            // Ensure execution permission
            binaryFile.setExecutable(true)

            val process = ProcessBuilder()
                .command(binaryFile.absolutePath) // ✅ Use binaryFile.absolutePath
                .redirectErrorStream(true)
                .start()

            val output = process.inputStream.bufferedReader().readText()
            process.waitFor()

            return output
        } catch (e: IOException) {
            Log.e("ExecutionManager", "Execution error: ${e.message}")
            return "Execution error: ${e.message}"
        }
    }
}
