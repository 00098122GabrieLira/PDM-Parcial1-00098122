package com.pdm0126.parcial1.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pdm0126.parcial1.data.menu

@Composable
fun HomeScreen(
  navigateToOrden: () -> Unit,
  cantidades: Map<Int, Int>,
  onCantidadesChange: (Map<Int, Int>) -> Unit
) {
  val totalItems = cantidades.values.sum()
  
  fun agregarProducto(productoId: Int) {
    val nuevasCantidades = cantidades.toMutableMap()
    nuevasCantidades[productoId] = (nuevasCantidades[productoId] ?: 0) + 1
    onCantidadesChange(nuevasCantidades)
  }
  
  Scaffold(
    topBar = {
      TopBar(
        totalItems = totalItems,
        onNavigateToOrden = navigateToOrden
      )
    }
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(Color(0XFFFFFFFF))
        .padding(paddingValues),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(menu) { producto ->
        ProductCard(
          producto = producto,
          cantidad = cantidades[producto.id] ?: 0,
          onProductoClick = { agregarProducto(producto.id) }
        )
      }
    }
  }
}