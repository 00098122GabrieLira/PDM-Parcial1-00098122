package com.pdm0126.parcial1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiOrden(navigateToBack: () -> Unit) {
  
  var ordenItems by remember { mutableStateOf<List<Pair<Producto, Int>>>(emptyList()) }
  var showSuccess by remember { mutableStateOf(false) }
  
  val totalGeneral = ordenItems.sumOf { it.first.precio * it.second }
  
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Mi Orden", fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
          Button(
            onClick = { navigateToBack },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color.Black,
              contentColor = Color.White
            )
          ) {
            Text(
              text = "Back"
            )
          }
        }
      )
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      if (ordenItems.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxSize()
            .weight(1f),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "No hay productos en la orden",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      } else {
        LazyColumn(
          modifier = Modifier.weight(1f),
          contentPadding = PaddingValues(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          items(ordenItems) { (producto, cantidad) ->
            val subtotal = producto.precio * cantidad
            
            Card(
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(12.dp),
              elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column(
                  modifier = Modifier.weight(1f)
                ) {
                  Text(
                    text = producto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                  )
                  Text(
                    text = "Precio unitario: $${String.format("%.2f", producto.precio)}",
                    fontSize = 14.sp
                  )
                  Text(
                    text = "Cantidad: $cantidad",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                  )
                }
                
                Column(
                  horizontalAlignment = Alignment.End
                ) {
                  Text(
                    text = "Subtotal:",
                    fontSize = 14.sp
                  )
                  Text(
                    text = "$${String.format("%.2f", subtotal)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                  )
                  
                  Spacer(modifier = Modifier.height(8.dp))
                  
                  Button(
                    onClick = {
                      ordenItems = ordenItems.filter { it.first.id != producto.id }
                    },
                    colors = ButtonDefaults.buttonColors(
                      containerColor = MaterialTheme.colorScheme.error
                    )
                  ) {
                    Text("Eliminar")
                  }
                }
              }
            }
          }
        }
        
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          shape = RoundedCornerShape(12.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
          )
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "Total General:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "$${String.format("%.2f", totalGeneral)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
              )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
              onClick = {
                showSuccess = true
                ordenItems = emptyList()
              },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("Confirmar Orden", fontSize = 16.sp)
            }
          }
        }
      }
    }
  }
  
  if (showSuccess) {
    AlertDialog(
      onDismissRequest = { showSuccess = false },
      title = { Text("¡Orden Confirmada!") },
      text = {
        Text(
          "La orden se ha completado exitosamente. Total: $${
            String.format(
              "%.2f",
              totalGeneral
            )
          }"
        )
      },
      confirmButton = {
        TextButton(onClick = { showSuccess = false }) {
          Text("Aceptar")
        }
      }
    )
  }
}