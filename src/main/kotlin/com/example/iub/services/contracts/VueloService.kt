package com.example.iub.services.contracts

import com.example.iub.dto.requests.VueloRequest
import com.example.iub.dto.responses.VueloResponse
import com.example.iub.enums.EstadoVuelo
import java.time.LocalDateTime

interface VueloService {
    fun buscar(origen: String?, destino: String?, fecha: LocalDateTime?): List<VueloResponse>
    fun crear(req: VueloRequest): VueloResponse
    fun actualizar(id: Int, req: VueloRequest): VueloResponse
    fun cambiarEstado(id: Int, nuevoEstado: EstadoVuelo): VueloResponse
}