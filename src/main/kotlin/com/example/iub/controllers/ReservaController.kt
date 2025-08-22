package com.example.iub.controllers

import com.example.iub.entities.Reserva
import com.example.iub.services.ReservaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reservas")
class ReservaController(
    private val reservaService: ReservaService
) {

    @GetMapping
    fun listarReservas(): ResponseEntity<List<Reserva>> {
        return ResponseEntity.ok(reservaService.listarTodas())
    }

    @GetMapping("/{id}")
    fun obtenerReserva(@PathVariable id: Long): ResponseEntity<Reserva> {
        val reserva = reservaService.obtenerPorId(id)
        return ResponseEntity.ok(reserva)
    }

    @PostMapping
    fun crearReserva(@RequestBody reserva: Reserva): ResponseEntity<Reserva> {
        val nueva = reservaService.crear(reserva)
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva)
    }

    @PutMapping("/{id}")
    fun actualizarReserva(@PathVariable id: Long, @RequestBody reserva: Reserva): ResponseEntity<Reserva> {
        val actualizada = reservaService.actualizar(id, reserva)
        return ResponseEntity.ok(actualizada)
    }

    @DeleteMapping("/{id}")
    fun eliminarReserva(@PathVariable id: Long): ResponseEntity<Void> {
        reservaService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
