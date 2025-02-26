package com.nived.ccompiler.compiler

import android.content.Context
import android.util.Log
import java.io.File
import java.io.IOException

class CompilerManager(private val context: Context) {

    fun compileCCode(sourceCode: String): String {
        val tccPath = com.nived.ccompiler.utils.TCCExtractor.extractTCC(context)
        val sourceFile = File(context.filesDir, "temp.c")
        val outputFile = File(context.filesDir, "output")

        sourceFile.writeText(sourceCode)

        // ✅ Check if TCC exists and is executable
        val tccBinary = File(tccPath)
        if (!tccBinary.exists() || !tccBinary.canExecute()) {
            Log.e("CompilerManager", "TCC binary is missing or not executable: $tccPath")
            return "Error: TCC compiler is not executable!"
        }

        return try {
            val process = ProcessBuilder()
                .command(tccPath, sourceFile.absolutePath, "-o", outputFile.absolutePath)
                .redirectErrorStream(true)
                .start()

            val errorOutput = process.inputStream.bufferedReader().readText()
            process.waitFor()

            if (outputFile.exists()) {
                return outputFile.absolutePath
            } else {
                return "Compilation failed:\n$errorOutput"
            }
        } catch (e: IOException) {
            return "Error running compiler: ${e.message}"
        }
    }
}
