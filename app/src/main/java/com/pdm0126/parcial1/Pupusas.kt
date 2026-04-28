package com.pdm0126.parcial1

data class Producto(
  val id: Int,
  val nombre: String,
  val precio: Double,
  val imagenUrl: String,
  val tipo: TipoProducto
)

enum class TipoProducto {
  PUPUSA,
  BEBIDA
}