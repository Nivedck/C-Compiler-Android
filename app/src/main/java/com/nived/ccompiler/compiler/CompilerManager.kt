package com.nived.ccompiler.compiler

import android.content.Context
import java.io.File

class CompilerManager(private val context: Context) {

    fun compileCCode(sourceCode: String): String {
        val tccPath = com.nived.ccompiler.utils.TCCExtractor.extractTCC(context)
        val sourceFile = File(context.filesDir, "temp.c")
        val outputFile = File(context.filesDir, "output")

        sourceFile.writeText(sourceCode)

        val process = ProcessBuilder()
            .command(tccPath, sourceFile.absolutePath, "-o", outputFile.absolutePath)
            .redirectErrorStream(true)
            .start()

        process.waitFor()
        return outputFile.absolutePath
    }
}
