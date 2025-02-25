package com.nived.ccompiler.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream

object TCCExtractor {

    fun extractTCC(context: Context): String {
        val tccDir = File(context.filesDir, "tcc")
        val tccBinary = File(tccDir, "tcc")

        if (!tccBinary.exists()) {
            tccDir.mkdirs()

            // Copy TCC from assets to internal storage
            context.assets.open("tcc/tcc").use { input ->
                FileOutputStream(tccBinary).use { output ->
                    input.copyTo(output)
                }
            }

            // Make it executable
            tccBinary.setExecutable(true)
        }

        return tccBinary.absolutePath
    }
}
