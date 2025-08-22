package com.example.iub.services

import com.example.iub.entities.EstadoVuelo
import com.example.iub.entities.Vuelo
import com.example.iub.repositories.VueloRepository
import com.example.iub.exceptions.VueloNotFoundException
import org.springframework.stereotype.Service

@Service
class VueloService(private val vueloRepository: VueloRepository) {

    fun listarTodos(): List<Vuelo> = vueloRepository.findAll()

    fun obtenerPorId(id: Long): Vuelo =
        vueloRepository.findById(id).orElseThrow {
            VueloNotFoundException("Vuelo con id $id no encontrado")
        }

    fun crear(vuelo: Vuelo): Vuelo = vueloRepository.save(vuelo)

    fun actualizar(id: Long, vuelo: Vuelo): Vuelo {
        val existente = obtenerPorId(id)
        existente.origen = vuelo.origen
        existente.destino = vuelo.destino
        existente.fechaSalida = vuelo.fechaSalida
        existente.fechaLlegada = vuelo.fechaLlegada
        existente.precio = vuelo.precio
        existente.estado = vuelo.estado
        existente.aeronave = vuelo.aeronave
        return vueloRepository.save(existente)
    }

    fun eliminar(id: Long) {
        val vuelo = obtenerPorId(id)
        vueloRepository.delete(vuelo)
    }
    // Actualizar estado de un vuelo
    fun actualizarEstado(id: Long, nuevoEstado: String): Vuelo {
        val vueloExiste = obtenerPorId(id)
        try {
            val estadoEnum = EstadoVuelo.valueOf(nuevoEstado.uppercase())
            vueloExiste.estado = estadoEnum
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(
                "Estado inválido: $nuevoEstado. Valores permitidos: ${EstadoVuelo.values().joinToString(", ")}"
            )
        }
        return vueloRepository.save(vueloExiste)
    }
}
