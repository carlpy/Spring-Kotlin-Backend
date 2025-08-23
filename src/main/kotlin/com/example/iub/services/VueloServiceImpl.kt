package com.example.iub.services

import com.example.iub.dto.requests.VueloRequest
import com.example.iub.dto.responses.VueloResponse
import com.example.iub.entities.Vuelo
import com.example.iub.enums.EstadoVuelo
import com.example.iub.repositories.VueloRepository
import com.example.iub.repositories.AeronaveRepository
import com.example.iub.services.contracts.VueloService
import com.example.iub.utils.toResponse
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class VueloServiceImpl(
    private val vueloRepo: VueloRepository,
    private val aeronaveRepo: AeronaveRepository
): VueloService {
    override fun buscar(origen: String?, destino: String?, fecha: LocalDateTime?): List<VueloResponse> {
        val fechaHasta = fecha?.plusDays(1)
        return vueloRepo.buscar(origen, destino, fecha, fechaHasta).map { it.toResponse() }
    }

    override fun crear(req: VueloRequest): VueloResponse {
        validarVuelo(req)
        val aeronave = aeronaveRepo.findById(req.idAeronave)
            .orElseThrow { IllegalArgumentException("Aeronave ${req.idAeronave} no existe") }
        val v = Vuelo(
            origen = req.origen,
            destino = req.destino,
            fecha = req.fecha,
            precio = req.precio,
            estado = req.estado,
            aeronave = aeronave
        )
        return vueloRepo.save(v).toDto()
    }

    override fun actualizar(id: Int, req: VueloRequest): VueloResponse {
        validarVuelo(req)
        val v = vueloRepo.findById(id).orElseThrow { IllegalArgumentException("Vuelo $id no existe") }
        val aeronave = aeronaveRepo.findById(req.idAeronave)
            .orElseThrow { IllegalArgumentException("Aeronave ${req.idAeronave} no existe") }
        v.origen = req.origen
        v.destino = req.destino
        v.fecha = req.fecha
        v.precio = req.precio
        v.estado = req.estado
        return vueloRepo.save(v).toDto()
    }

    override fun cambiarEstado(id: Int, nuevoEstado: EstadoVuelo): VueloResponse {
        val v = vueloRepo.findById(id).orElseThrow { IllegalArgumentException("Vuelo $id no existe") }
        v.estado = nuevoEstado
        return vueloRepo.save(v).toDto()
    }

    private fun validarVuelo(req: VueloRequest) {
        require(req.origen != req.destino) { "Origen y destino deben ser diferentes" }
        require(req.precio > BigDecimal.ZERO) { "El precio debe ser > 0" }
    }

    private fun Vuelo.toDto() = VueloResponse(
        idVuelo = this.idVuelo,
        origen = this.origen,
        destino = this.destino,
        fecha = this.fecha,
        precio = this.precio,
        estado = this.estado,
        idAeronave = this.aeronave.idAeronave
    )
}
