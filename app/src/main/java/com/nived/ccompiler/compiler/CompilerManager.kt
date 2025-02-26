package com.nived.ccompiler.compiler

import android.content.Context
import com.nived.ccompiler.utils.TCCExtractor
import java.io.File
import java.io.IOException

class CompilerManager(private val context: Context) {

    fun saveCodeToFile(code: String): String {
        val sourceFile = File(context.filesDir, "temp.c")
        sourceFile.writeText(code)
        return sourceFile.absolutePath
    }

    fun compileCCode(sourcePath: String): String {
        val tccPath = TCCExtractor.extractTCC(context)
        
        if (tccPath.isEmpty()) {
            return "Error: TCC binary extraction failed!"
        }

        val outputFile = File(context.filesDir, "output")

        return try {
            val process = ProcessBuilder()
                .command(tccPath, sourcePath, "-o", outputFile.absolutePath)
                .redirectErrorStream(true)
                .start()

            val errorOutput = process.inputStream.bufferedReader().readText()
            process.waitFor()

            if (outputFile.exists()) {
                outputFile.setExecutable(true) // Ensure binary is executable
                return outputFile.absolutePath
            } else {
                return "Compilation failed:\n$errorOutput"
            }
        } catch (e: IOException) {
            return "Error running compiler: ${e.message}"
        }
    }
}
