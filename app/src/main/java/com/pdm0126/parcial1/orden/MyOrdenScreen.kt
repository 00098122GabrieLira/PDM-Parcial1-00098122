package com.pdm0126.parcial1.orden

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.parcial1.data.menu
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun MyOrdenScreen(
  navigateToBack: () -> Unit,
  cantidades: Map<Int, Int>,
  onCantidadesChange: (Map<Int, Int>) -> Unit
) {
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()
  
  val ordenItems = menu.filter { producto ->
    (cantidades[producto.id] ?: 0) > 0
  }.map { producto ->
    producto to (cantidades[producto.id] ?: 0)
  }
  
  val totalGeneral = ordenItems.sumOf { it.first.precio * it.second }
  
  fun eliminarProducto(productoId: Int) {
    val nuevasCantidades = cantidades.toMutableMap().apply {
      remove(productoId)
    }
    onCantidadesChange(nuevasCantidades)
  }
  
  fun confirmarOrden() {
    val total = totalGeneral
    onCantidadesChange(emptyMap())
    scope.launch {
      val result = snackbarHostState.showSnackbar(
        message = "¡Orden confirmada! Total: $${String.format(Locale.US, "%.2f", total)}",
        withDismissAction = true
      )
      
      when (result) {
        SnackbarResult.ActionPerformed -> {
          navigateToBack()
        }
        
        SnackbarResult.Dismissed -> {}
      }
    }
  }
  
  Scaffold(
    topBar = {
      OrdenTopBar(onNavigateToBack = navigateToBack)
    },
    snackbarHost = { SnackbarHost(snackbarHostState) }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(paddingValues)
    ) {
      if (ordenItems.isEmpty()) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "No hay productos en la orden",
            fontSize = 18.sp,
            color = Color.Black
          )
        }
      } else {
        LazyColumn(
          modifier = Modifier.weight(1f),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          items(ordenItems) { (producto, cantidad) ->
            OrdenItemCard(
              producto = producto,
              cantidad = cantidad,
              onEliminar = { eliminarProducto(producto.id) }
            )
          }
        }
        
        TotalCard(
          totalGeneral = totalGeneral,
          onConfirmar = { confirmarOrden() }
        )
      }
    }
  }
}