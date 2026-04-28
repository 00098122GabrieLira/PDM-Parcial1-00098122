package com.pdm0126.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pdm0126.parcial1.ui.theme.Parcial1Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Parcial1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          PupusasApp(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}