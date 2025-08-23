package com.example.iub.dto.responses

import com.example.iub.enums.EstadoReserva
import java.time.LocalDateTime

data class ReservaResponse(
    val idReserva: Int,
    val fechaReserva: LocalDateTime,
    val estado: EstadoReserva,
    val idUsuario: Int,
    val idVuelo: Int
)
