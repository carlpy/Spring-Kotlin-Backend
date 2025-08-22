package com.example.iub.services

import com.example.iub.entities.Aeronave
import com.example.iub.repositories.AeronaveRepository
import com.example.iub.exceptions.AeronaveNotFoundException
import org.springframework.stereotype.Service

@Service
class AeronaveService(private val aeronaveRepository: AeronaveRepository) {

    fun listarTodas(): List<Aeronave> = aeronaveRepository.findAll()

    fun obtenerPorId(id: Long): Aeronave =
        aeronaveRepository.findById(id).orElseThrow {
            AeronaveNotFoundException("Aeronave con id $id no encontrada")
        }

    fun crear(aeronave: Aeronave): Aeronave = aeronaveRepository.save(aeronave)

    fun actualizar(id: Long, aeronave: Aeronave): Aeronave {
        val existente = obtenerPorId(id)
        existente.modelo = aeronave.modelo
        existente.capacidad = aeronave.capacidad
        return aeronaveRepository.save(existente)
    }

    fun eliminar(id: Long) {
        val aeronave = obtenerPorId(id)
        aeronaveRepository.delete(aeronave)
    }
}
