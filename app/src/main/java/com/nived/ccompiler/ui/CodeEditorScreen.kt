package com.nived.ccompiler.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nived.ccompiler.compiler.CompilerManager
import com.nived.ccompiler.compiler.ExecutionManager

@Composable
fun CodeEditorScreen(context: Context) {
    var code by remember { mutableStateOf("#include <stdio.h>\nint main() { printf(\"Hello, World!\"); return 0; }") }
    var output by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("C Code") },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Button(
            onClick = {
                val sourcePath = CompilerManager.saveCodeToFile(context, code)
                val binaryPath = CompilerManager.compileCode(context, sourcePath)
                output = ExecutionManager.runBinary(binaryPath)
            },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        ) {
            Text("Compile & Run")
        }

        Text(text = "Output:\n$output", modifier = Modifier.padding(top = 16.dp))
    }
}
