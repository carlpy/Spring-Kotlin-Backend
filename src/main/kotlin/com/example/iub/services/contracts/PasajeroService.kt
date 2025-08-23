package com.example.iub.services.contracts

import com.example.iub.dto.responses.PasajeroResponse

interface PasajeroService {
    fun porVuelo(idVuelo: Int): List<PasajeroResponse>
}
