package com.example.iub.dto.requests

import com.example.iub.enums.EstadoReserva
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

data class ReservaRequest(
    @field:NotNull
    val fechaReserva: LocalDateTime,

    @field:NotNull
    val estado: EstadoReserva,

    @field:NotNull
    val idUsuario: Int,

    @field:NotNull
    val idVuelo: Int
)
