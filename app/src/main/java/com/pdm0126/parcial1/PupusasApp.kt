package com.pdm0126.parcial1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.parcial1.menu.HomeScreen
import com.pdm0126.parcial1.routes.Routes
import com.pdm0126.parcial1.orden.MyOrdenScreen

@Composable
fun PupusasApp(modifier: Modifier = Modifier) {
  
  val backStack = rememberNavBackStack(Routes.Menu)
  var cantidades by rememberSaveable { mutableStateOf<Map<Int, Int>>(emptyMap()) }
  
  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Routes.Menu> {
        HomeScreen(
          navigateToOrden = {
            backStack.add(Routes.MiOrden)
          },
          cantidades = cantidades,
          onCantidadesChange = { nuevasCantidades ->
            cantidades = nuevasCantidades
          }
        )
      }
      entry<Routes.MiOrden> {
        MyOrdenScreen(
          navigateToBack = {
            backStack.removeLastOrNull()
          },
          cantidades = cantidades,
          onCantidadesChange = { nuevasCantidades ->
            cantidades = nuevasCantidades
          }
        )
      }
    }
  )
}