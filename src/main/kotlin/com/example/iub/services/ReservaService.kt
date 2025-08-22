package com.example.iub.services

import com.example.iub.entities.Reserva
import com.example.iub.repositories.ReservaRepository
import com.example.iub.exceptions.ReservaNotFoundException
import org.springframework.stereotype.Service

@Service
class ReservaService(private val reservaRepository: ReservaRepository) {

    fun listarTodas(): List<Reserva> = reservaRepository.findAll()

    fun obtenerPorId(id: Long): Reserva =
        reservaRepository.findById(id).orElseThrow {
            ReservaNotFoundException("Reserva con id $id no encontrada")
        }

    fun crear(reserva: Reserva): Reserva = reservaRepository.save(reserva)

    fun actualizar(id: Long, reserva: Reserva): Reserva {
        val existente = obtenerPorId(id)
        existente.fechaReserva = reserva.fechaReserva
        existente.estado = reserva.estado
        existente.usuario = reserva.usuario
        existente.vuelo = reserva.vuelo
        return reservaRepository.save(existente)
    }

    fun eliminar(id: Long) {
        val reserva = obtenerPorId(id)
        reservaRepository.delete(reserva)
    }
}
