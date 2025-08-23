package com.example.iub.dto.requests

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PasajeroRequest(
    @field:NotBlank @field:Size(max = 100)
    val nombre: String,

    @field:NotBlank @field:Size(max = 50)
    val documento: String,

    @field:Min(1)
    val edad: Int,

    @field:NotNull
    val idReserva: Int
)

