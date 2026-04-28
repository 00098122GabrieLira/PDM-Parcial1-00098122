package com.pdm0126.parcial1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay

@Composable
fun PupusasApp(modifier: Modifier = Modifier) {
  
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(Color.White),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
  ) {
    
    val backStack = rememberNavBackStack(Routes.Menu)
    
    NavDisplay(
      backStack = backStack,
      onBack = { backStack.removeLastOrNull() },
      entryProvider = entryProvider {
        entry<Routes.Menu> {
          HomeScreen(
            navigateToOrden = { id ->
              backStack.add(Routes.MiOrden(id))
            }
          )
        }
        entry<Routes.MiOrden> {
          MiOrden(
            navigateToBack = {
              backStack.removeLastOrNull()
            }
          )
        }
      }
    )
  }
}