package com.pdm0126.parcial1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToOrden: (Int) -> Unit) {
  
  var cantidades by rememberSaveable { mutableStateOf<Map<Int, Int>>(emptyMap()) }
  val totalItems = cantidades.values.sum()
  
  fun agregarProducto(productoId: Int) {
    cantidades = cantidades.toMutableMap().apply {
      this[productoId] = (this[productoId] ?: 0) + 1
    }
  }
  
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Menú", fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
          BadgedBox(badge = {
            if (totalItems > 0) {
              Badge { Text(totalItems.toString()) }
            }
          }) {
            Button(
              onClick = { navigateToOrden(0) },
              colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
              )
            ) {
              Text(
                text = "Orden"
              )
            }
            
          }
        }
      )
    }
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(menu) { producto ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { agregarProducto(producto.id) },
          shape = RoundedCornerShape(12.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            AsyncImage(
              model = producto.imagenUrl,
              contentDescription = producto.nombre,
              modifier = Modifier
                .size(80.dp)
                .weight(0.3f),
              contentScale = ContentScale.Crop
            )
            
            Column(
              modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
              verticalArrangement = Arrangement.Center
            ) {
              Text(
                text = producto.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "$${String.format("%.2f", producto.precio)}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
              )
              Text(
                text = if (producto.tipo == TipoProducto.PUPUSA) "Pupusa" else "Bebida",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary
              )
            }
            
            val cantidad = cantidades[producto.id] ?: 0
            if (cantidad > 0) {
              Surface(
                modifier = Modifier
                  .align(Alignment.CenterVertically)
                  .size(40.dp),
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = cantidad.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}