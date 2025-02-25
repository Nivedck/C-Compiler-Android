package com.nived.ccompiler.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OutputScreen(output: String) {
    Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth().height(150.dp)) {
        Text(text = output, modifier = Modifier.padding(16.dp))
    }
}
