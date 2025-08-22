package com.example.iub.controllers

import com.example.iub.entities.Vuelo
import com.example.iub.services.VueloService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vuelos")
class VueloController(
    private val vueloService: VueloService
) {

    @GetMapping
    fun listarVuelos(): ResponseEntity<List<Vuelo>> {
        return ResponseEntity.ok(vueloService.listarTodos())
    }

    @GetMapping("/{id}")
    fun obtenerVuelo(@PathVariable id: Long): ResponseEntity<Vuelo> {
        val vuelo = vueloService.obtenerPorId(id)
        return ResponseEntity.ok(vuelo)
    }

    @PostMapping
    fun crearVuelo(@RequestBody vuelo: Vuelo): ResponseEntity<Vuelo> {
        val nuevo = vueloService.crear(vuelo)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo)
    }

    @PutMapping("/{id}")
    fun actualizarVuelo(@PathVariable id: Long, @RequestBody vuelo: Vuelo): ResponseEntity<Vuelo> {
        val actualizado = vueloService.actualizar(id, vuelo)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminarVuelo(@PathVariable id: Long): ResponseEntity<Void> {
        vueloService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
    //Actualizar estado de un vuelo
    @PatchMapping("/{id}/estado")
    fun actualizarEstadoVuelo(@PathVariable id: Long, @RequestParam estado: String): ResponseEntity<Vuelo> {
        val vueloActualizado = vueloService.actualizarEstado(id, estado)
        return ResponseEntity.ok(vueloActualizado)
    }
}
