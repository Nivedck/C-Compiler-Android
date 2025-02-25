package com.nived.ccompiler.compiler

import android.content.Context
import com.nived.ccompiler.utils.TCCExtractor
import java.io.File

object CompilerManager {
    fun saveCodeToFile(context: Context, code: String): String {
        val file = File(context.filesDir, "temp.c")
        file.writeText(code)
        return file.absolutePath
    }

    fun compileCode(context: Context, sourcePath: String): String {
        val tccPath = "${TCCExtractor.getTCCPath(context)}/tcc"
        val outputBinary = File(context.filesDir, "a.out").absolutePath
        val process = Runtime.getRuntime().exec("$tccPath $sourcePath -o $outputBinary")
        process.waitFor()
        return outputBinary
    }
}
