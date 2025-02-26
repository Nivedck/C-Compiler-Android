package com.nived.ccompiler.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutputScreen(output: String) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 200.dp) // Allows flexibility
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black) // Terminal-like background
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Enables scrolling
        ) {
            Text(text = output, color = Color.Green) // Green text for terminal feel
        }
    }
}
