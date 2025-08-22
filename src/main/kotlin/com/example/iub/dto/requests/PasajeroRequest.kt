package com.example.iub.dto.requests

import com.example.iub.entities.Reserva

data class PasajeroRequest(
    val nombre: String,
    val documento: String,
    val reserva: Reserva
)
