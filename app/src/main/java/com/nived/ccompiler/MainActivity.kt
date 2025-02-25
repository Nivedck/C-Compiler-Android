package com.nived.ccompiler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nived.ccompiler.utils.TCCExtractor
import com.nived.ccompiler.ui.CodeEditorScreen  // Make sure this import is present

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract TCC binaries
        val tccPath = TCCExtractor.extractTCC(this)
        if (tccPath.isEmpty()) {
            println("❌ Failed to extract TCC binaries!")
        } else {
            println("✅ TCC extracted successfully at: $tccPath")
        }

        // Set UI
        setContent {
            CodeEditorScreen()
        }
    }
}
