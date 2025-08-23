package com.example.iub.dto.responses

import com.example.iub.enums.EstadoVuelo
import java.math.BigDecimal
import java.time.LocalDateTime

data class VueloResponse(
    val idVuelo: Int,
    val origen: String,
    val destino: String,
    val fecha: LocalDateTime,
    val precio: BigDecimal,
    val estado: EstadoVuelo,
    val idAeronave: Int
)