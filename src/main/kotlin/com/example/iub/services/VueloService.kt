package com.example.iub.services

import com.example.iub.entities.Vuelo
import com.example.iub.enums.EstadoVuelo
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

        val updatedExistente = existente.copy(
            origen = vuelo.origen,
            destino = vuelo.destino,
            fecha = vuelo.fecha,
            precio = vuelo.precio,
            estado = vuelo.estado,
            aeronave = vuelo.aeronave
        )
        return vueloRepository.save(updatedExistente)
    }

    fun eliminar(id: Long) {
        val vuelo = obtenerPorId(id)
        vueloRepository.delete(vuelo)
    }
    // Actualizar estado de un vuelo
    fun actualizarEstado(id: Long, nuevoEstado: String): Vuelo {
        val vueloExiste = obtenerPorId(id)
        var updatedVuelo: Vuelo? = null
        try {
            val estadoEnum = EstadoVuelo.valueOf(nuevoEstado.uppercase())

            updatedVuelo = vueloExiste.copy(estado = estadoEnum)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(
                "Estado inválido: $nuevoEstado. Valores permitidos: ${EstadoVuelo.values().joinToString(", ")}"
            )
        }
        return vueloRepository.save(updatedVuelo)
    }
}
