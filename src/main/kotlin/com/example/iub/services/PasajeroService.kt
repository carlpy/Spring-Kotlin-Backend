package com.example.iub.services

import com.example.iub.entities.Pasajero
import com.example.iub.repositories.PasajeroRepository
import com.example.iub.exceptions.PasajeroNotFoundException
import org.springframework.stereotype.Service

@Service
class PasajeroService(private val pasajeroRepository: PasajeroRepository) {

    fun listarTodos(): List<Pasajero> = pasajeroRepository.findAll()

    fun obtenerPorId(id: Long): Pasajero =
        pasajeroRepository.findById(id).orElseThrow {
            PasajeroNotFoundException("Pasajero con id $id no encontrado")
        }

    fun crear(pasajero: Pasajero): Pasajero = pasajeroRepository.save(pasajero)

    fun actualizar(id: Long, pasajero: Pasajero): Pasajero {
        val existente = obtenerPorId(id)

        val updatedExistente = existente.copy(
            nombre = pasajero.nombre,
            documento = pasajero.documento,
            edad = pasajero.edad,
            reserva = pasajero.reserva
        )

        return pasajeroRepository.save(existente)
    }

    fun eliminar(id: Long) {
        val pasajero = obtenerPorId(id)
        pasajeroRepository.delete(pasajero)
    }
}
