package com.nived.ccompiler.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream

object TCCExtractor {
    fun extractTCCBinaries(context: Context): Boolean {
        return try {
            val tccDir = File(context.filesDir, "tcc/bin")
            if (!tccDir.exists()) tccDir.mkdirs()

            val assetManager = context.assets
            val binaries = assetManager.list("tcc/bin") ?: return false

            for (file in binaries) {
                val outFile = File(tccDir, file)
                if (!outFile.exists()) {
                    assetManager.open("tcc/bin/$file").use { input ->
                        FileOutputStream(outFile).use { output -> input.copyTo(output) }
                    }
                    outFile.setExecutable(true)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getTCCPath(context: Context): String {
        return File(context.filesDir, "tcc/bin").absolutePath
    }
}
