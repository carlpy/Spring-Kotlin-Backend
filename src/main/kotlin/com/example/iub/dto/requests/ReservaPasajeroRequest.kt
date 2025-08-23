package com.example.iub.dto.requests

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty

data class ReservaPasajeroRequest(
    @field:Valid val reserva: ReservaRequest,
    @field:NotEmpty @field:Valid val pasajeros: List<PasajeroRequest>
)
