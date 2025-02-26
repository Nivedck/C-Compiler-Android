package com.nived.ccompiler.utils

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object TCCExtractor {

    fun extractTCC(context: Context): String {
        val tccDir = File(context.filesDir, "tcc")
        val tccBinary = File(tccDir, "tcc")

        if (!tccBinary.exists()) {
            if (!tccDir.exists() && !tccDir.mkdirs()) {
                Log.e("TCCExtractor", "❌ Failed to create TCC directory")
                return ""
            }

            try {
                // Copy TCC from assets to internal storage
                context.assets.open("tcc/tcc").use { input ->
                    FileOutputStream(tccBinary).use { output ->
                        input.copyTo(output)
                    }
                }

                // Set executable permissions
                tccBinary.setReadable(true, false)
                tccBinary.setExecutable(true, false)

                Log.d("TCCExtractor", "✅ TCC extracted successfully at: ${tccBinary.absolutePath}")

            } catch (e: IOException) {
                Log.e("TCCExtractor", "❌ Failed to extract TCC: ${e.message}")
                return ""
            }
        }

        return tccBinary.absolutePath
    }
}
