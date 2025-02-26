package com.nived.ccompiler.compiler

import android.content.Context
import java.io.File
import java.io.IOException

class CompilerManager(private val context: Context) {

    fun compileCCode(sourceCode: String): String {
        val tccPath = com.nived.ccompiler.utils.TCCExtractor.extractTCC(context)
        val sourceFile = File(context.filesDir, "temp.c")
        val outputFile = File(context.filesDir, "output")

        sourceFile.writeText(sourceCode)

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
