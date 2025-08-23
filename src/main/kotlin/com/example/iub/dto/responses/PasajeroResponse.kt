package com.example.iub.dto.responses

data class PasajeroResponse(
    val idPasajero: Int,
    val nombre: String,
    val documento: String,
    val edad: Int,
    val idReserva: Int
)

