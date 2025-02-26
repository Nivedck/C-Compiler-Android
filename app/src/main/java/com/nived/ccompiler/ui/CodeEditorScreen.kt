package com.nived.ccompiler.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nived.ccompiler.compiler.CompilerManager
import com.nived.ccompiler.compiler.ExecutionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun CodeEditorScreen(context: Context) {
    var code by remember { mutableStateOf("#include <stdio.h>\nint main() { printf(\"Hello, World!\\n\"); return 0; }") }
    var output by remember { mutableStateOf("") }
    val compilerManager = remember { CompilerManager(context) }
    val executionManager = remember { ExecutionManager() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("C Code") },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val sourcePath = saveCodeToFile(context, code)
                    val binaryPath = compilerManager.compileCCode(sourcePath)
                    val result = executionManager.runBinary(binaryPath)
                    
                    output = result // Update UI with output
                }
            },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        ) {
            Text("Compile & Run")
        }

        Text(text = "Output:\n$output", modifier = Modifier.padding(top = 16.dp))
    }
}

/**
 * Saves the given C code to a temporary file.
 */
fun saveCodeToFile(context: Context, code: String): String {
    val sourceFile = File(context.filesDir, "temp.c")
    sourceFile.writeText(code)
    return sourceFile.absolutePath
}
