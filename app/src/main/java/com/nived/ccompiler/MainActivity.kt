package com.nived.ccompiler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nived.ccompiler.utils.TCCExtractor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract TCC binaries
        val success = TCCExtractor.extractTCCBinaries(this)
        if (!success) {
            println("❌ Failed to extract TCC binaries!")
        }

        // Set UI
        setContent {
            CodeEditorScreen()
        }
    }
}
