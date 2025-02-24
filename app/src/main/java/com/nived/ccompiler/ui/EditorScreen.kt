package com.example.myccompilerapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditorScreen(code: String, onCodeChange: (String) -> Unit) {
    Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth().height(300.dp)) {
        BasicTextField(
            value = code,
            onValueChange = onCodeChange,
            modifier = Modifier.padding(16.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black)
        )
    }
}
