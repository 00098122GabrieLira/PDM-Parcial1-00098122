package com.pdm0126.parcial1

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
  @Serializable
  data object Menu : Routes()
  
  
  @Serializable
  data class MiOrden(val ordenId: Int) : Routes()
}
