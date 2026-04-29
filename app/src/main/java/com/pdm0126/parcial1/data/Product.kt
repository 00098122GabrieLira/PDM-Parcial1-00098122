package com.pdm0126.parcial1.data

data class Producto(
  val id: Int,
  val nombre: String,
  val precio: Double,
  val imagenUrl: String,
  val tipo: TipoProducto
)