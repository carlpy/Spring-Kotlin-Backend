package com.example.iub.controllers

import com.example.iub.entities.Pasajero
import com.example.iub.services.PasajeroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pasajeros")
class PasajeroController(
    private val pasajeroService: PasajeroService
) {

    @GetMapping
    fun listarPasajeros(): ResponseEntity<List<Pasajero>> {
        return ResponseEntity.ok(pasajeroService.listarTodos())
    }

    @GetMapping("/{id}")
    fun obtenerPasajero(@PathVariable id: Long): ResponseEntity<Pasajero> {
        val pasajero = pasajeroService.obtenerPorId(id)
        return ResponseEntity.ok(pasajero)
    }

    @PostMapping
    fun crearPasajero(@RequestBody pasajero: Pasajero): ResponseEntity<Pasajero> {
        val nuevo = pasajeroService.crear(pasajero)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo)
    }

    @PutMapping("/{id}")
    fun actualizarPasajero(@PathVariable id: Long, @RequestBody pasajero: Pasajero): ResponseEntity<Pasajero> {
        val actualizado = pasajeroService.actualizar(id, pasajero)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminarPasajero(@PathVariable id: Long): ResponseEntity<Void> {
        pasajeroService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
