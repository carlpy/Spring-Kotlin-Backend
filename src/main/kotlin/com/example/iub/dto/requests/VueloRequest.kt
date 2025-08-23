package com.example.iub.dto.requests

import com.example.iub.enums.EstadoVuelo
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import jakarta.validation.constraints.DecimalMin

import java.math.BigDecimal
import java.time.LocalDateTime

data class VueloRequest(
    @field:NotBlank @field:Size(max = 100)
    val origen: String,

    @field:NotBlank @field:Size(max = 100)
    val destino: String,

    @field:NotNull
    val fecha: LocalDateTime,

    @field:DecimalMin("0.0", inclusive = false)
    val precio: BigDecimal,

    @field:NotNull
    val estado: EstadoVuelo,

    @field:NotNull
    val idAeronave: Int
)
