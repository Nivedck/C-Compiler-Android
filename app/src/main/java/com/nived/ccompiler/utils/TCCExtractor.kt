package com.nived.ccompiler.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object TCCExtractor {

    fun extractTCC(context: Context): String {
        val tccDir = File(context.filesDir, "tcc")
        val tccBinary = File(tccDir, "tcc")

        if (!tccBinary.exists()) {
            tccDir.mkdirs()
            try {
                val inputStream = context.assets.open("tcc") // Make sure tcc binary is in assets
                val outputStream = FileOutputStream(tccBinary)
                inputStream.copyTo(outputStream)
                inputStream.close()
                outputStream.close()

                // ✅ Grant execute permission
                tccBinary.setExecutable(true, false)

            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
        }
        return tccBinary.absolutePath
    }
}
