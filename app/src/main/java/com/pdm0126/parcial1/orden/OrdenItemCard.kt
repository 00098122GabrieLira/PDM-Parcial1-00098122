package com.pdm0126.parcial1.orden

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.parcial1.data.Producto
import java.util.Locale

@Composable
fun OrdenItemCard(
  producto: Producto,
  cantidad: Int,
  onEliminar: () -> Unit
) {
  val subtotal = producto.precio * cantidad
  
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color(0xFF565353)
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = producto.nombre,
          color = Color.White,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "Precio unitario: $${String.format(Locale.US, "%.2f", producto.precio)}",
          color = Color.White,
          fontSize = 14.sp
        )
        Text(
          text = "Cantidad: $cantidad",
          fontSize = 14.sp,
          color = Color.White
        )
      }
      
      Column(horizontalAlignment = Alignment.End) {
        Text(text = "Subtotal:", color = Color.White, fontSize = 14.sp)
        Text(
          text = "$${String.format(Locale.US, "%.2f", subtotal)}",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
          onClick = onEliminar,
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
          )
        ) {
          Text(text = "Eliminar")
        }
      }
    }
  }
}