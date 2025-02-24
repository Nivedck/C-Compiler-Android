package com.example.myccompilerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myccompilerapp.compiler.CompilerManager
import com.example.myccompilerapp.compiler.ExecutionManager
import com.example.myccompilerapp.ui.EditorScreen
import com.example.myccompilerapp.ui.OutputScreen
import com.example.myccompilerapp.utils.LLVMExtractor
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Extract LLVM binaries
        val extractionSuccess = LLVMExtractor.extractLLVMBinaries(this)
        if (!extractionSuccess) {
            // Handle failure
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
        topBar = { TopAppBar(title = { Text("C Compiler") }) },
        content = {
            Column(Modifier.fillMaxSize().padding(16.dp)) {
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
    )
}
