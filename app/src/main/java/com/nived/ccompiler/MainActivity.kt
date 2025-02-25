package com.nived.ccompiler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nived.ccompiler.compiler.CompilerManager
import com.nived.ccompiler.compiler.ExecutionManager
import com.nived.ccompiler.ui.EditorScreen
import com.nived.ccompiler.ui.OutputScreen
import com.nived.ccompiler.utils.LLVMExtractor
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract LLVM binaries
        val extractionSuccess = LLVMExtractor.extractLLVMBinaries(this)
        if (!extractionSuccess) {
            // Show error message to user
            Toast.makeText(this, "Failed to extract LLVM binaries", Toast.LENGTH_LONG).show()
            return
        }

        setContent {
            MyCCompilerApp()
        }
    }
}

@Composable
fun MyCCompilerApp() {
    var codeText by remember { mutableStateOf("#include <stdio.h>\nint main() { printf(\"Hello, World!\"); return 0; }") }
    var outputText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("C Compiler") }) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            EditorScreen(codeText) { codeText = it }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val filePath = CompilerManager.saveCodeToFile(context, codeText)
                        val binaryPath = CompilerManager.compileCode(context, filePath)
                        outputText = ExecutionManager.runExecutable(binaryPath)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Run")
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutputScreen(outputText)
        }
    }
}
