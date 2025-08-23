package com.example.iub.dto.requests

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AeronaveRequest(
    @field:NotBlank @field:Size(max = 50)
    val modelo: String,

    @field:Min(1)
    val capacidad: Int,
)

