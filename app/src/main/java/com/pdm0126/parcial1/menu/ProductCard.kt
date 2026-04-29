package com.pdm0126.parcial1.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pdm0126.parcial1.data.Producto
import com.pdm0126.parcial1.data.TipoProducto
import java.util.Locale

@Composable
fun ProductCard(
  producto: Producto,
  cantidad: Int,
  onProductoClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onProductoClick() },
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF565353))
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
          color = Color.White,
          text = producto.nombre,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          color = Color.White,
          text = "$${String.format(Locale.US, "%.2f", producto.precio)}",
          fontSize = 16.sp
        )
        Text(
          text = if (producto.tipo == TipoProducto.PUPUSA) "Pupusa" else "Bebida",
          fontSize = 12.sp,
          color = Color.White
        )
      }
      
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