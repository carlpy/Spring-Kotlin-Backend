package com.example.iub.services

import com.example.iub.dto.requests.ReservaPasajeroRequest
import com.example.iub.dto.responses.ReservaResponse
import com.example.iub.entities.Pasajero
import com.example.iub.entities.Reserva
import com.example.iub.enums.EstadoReserva
import com.example.iub.enums.EstadoVuelo
import com.example.iub.repositories.PasajeroRepository
import com.example.iub.repositories.ReservaRepository
import com.example.iub.repositories.UsuarioRepository
import com.example.iub.repositories.VueloRepository
import com.example.iub.services.contracts.ReservaService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservaServiceImpl(
    private val reservaRepo: ReservaRepository,
    private val vueloRepo: VueloRepository,
    private val usuarioRepo: UsuarioRepository,
    private val pasajeroRepo: PasajeroRepository
): ReservaService {
    override fun crearConPasajeros(req: ReservaPasajeroRequest): ReservaResponse {
        val (res, pax) = req.reserva to req.pasajeros

        val usuario = usuarioRepo.findById(res.idUsuario)
            .orElseThrow { IllegalArgumentException("Usuario ${res.idUsuario} no existe") }

        val vuelo = vueloRepo.findById(res.idVuelo)
            .orElseThrow { IllegalArgumentException("Vuelo ${res.idVuelo} no existe") }

        require(vuelo.estado == EstadoVuelo.PROGRAMADO) { "Solo se puede reservar vuelos PROGRAMADOS" }

        val paxActuales = pasajeroRepo.countByVueloId(vuelo.idVuelo)
        require(paxActuales + pax.size <= vuelo.aeronave.capacidad) { "Sin asientos disponibles" }

        val r = Reserva(
            fechaReserva = res.fechaReserva,
            estado = res.estado,
            usuario = usuario,
            vuelo = vuelo
        )
        val guardada = reservaRepo.save(r)

        val pasajeros = pax.map {
            Pasajero(
                nombre = it.nombre,
                documento = it.documento,
                edad = it.edad,
                reserva = guardada
            )
        }
        pasajeroRepo.saveAll(pasajeros)

        return guardada.toDto()
    }

    override fun cancelarPropia(reservaId: Int, userId: Int) {
        val r = reservaRepo.findById(reservaId)
            .orElseThrow { IllegalArgumentException("Reserva $reservaId no existe") }

        require(r.usuario.idUsuario == userId) { "Solo puedes cancelar tus reservas" }

        val limite = r.vuelo.fecha.minusHours(2)
        require(LocalDateTime.now().isBefore(limite)) { "No se puede cancelar con menos de 2h" }

        r.estado = EstadoReserva.CANCELADA
        reservaRepo.save(r)
    }

    override fun mias(userId: Int): List<ReservaResponse> =
        reservaRepo.findByUsuario_IdUsuario(userId).map { it.toDto() }

    override fun todas(): List<ReservaResponse> =
        reservaRepo.findAll().map { it.toDto() }

    private fun Reserva.toDto() = ReservaResponse(
        idReserva = this.idReserva,
        fechaReserva = this.fechaReserva,
        estado = this.estado,
        idUsuario = this.usuario.idUsuario,
        idVuelo = this.vuelo.idVuelo
    )
}
