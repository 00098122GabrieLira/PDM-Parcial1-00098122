package com.pdm0126.parcial1.orden

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdenTopBar(
  onNavigateToBack: () -> Unit
) {
  TopAppBar(
    title = { Text("Mi orden", fontWeight = FontWeight.Bold, color = Color.Black) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    navigationIcon = {
      Button(
        onClick = onNavigateToBack,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color.Black,
          contentColor = Color.White
        )
      ) {
        Text(text = "<- Atras")
      }
    }
  )
}