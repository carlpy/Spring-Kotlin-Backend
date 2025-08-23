package com.example.iub.services.contracts

import com.example.iub.dto.requests.ReservaPasajeroRequest
import com.example.iub.dto.responses.ReservaResponse

interface ReservaService {
    fun crearConPasajeros(req: ReservaPasajeroRequest): ReservaResponse
    fun cancelarPropia(reservaId: Int, userId: Int)
    fun mias(userId: Int): List<ReservaResponse>
    fun todas(): List<ReservaResponse>
}