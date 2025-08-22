package com.example.iub.controllers

import com.example.iub.entities.Aeronave
import com.example.iub.services.AeronaveService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aeronaves")
class AeronaveController(
    private val aeronaveService: AeronaveService
) {

    @GetMapping
    fun listarAeronaves(): ResponseEntity<List<Aeronave>> {
        return ResponseEntity.ok(aeronaveService.listarTodas())
    }

    @GetMapping("/{id}")
    fun obtenerAeronave(@PathVariable id: Long): ResponseEntity<Aeronave> {
        val aeronave = aeronaveService.obtenerPorId(id)
        return ResponseEntity.ok(aeronave)
    }

    @PostMapping
    fun crearAeronave(@RequestBody aeronave: Aeronave): ResponseEntity<Aeronave> {
        val nueva = aeronaveService.crear(aeronave)
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva)
    }

    @PutMapping("/{id}")
    fun actualizarAeronave(@PathVariable id: Long, @RequestBody aeronave: Aeronave): ResponseEntity<Aeronave> {
        val actualizada = aeronaveService.actualizar(id, aeronave)
        return ResponseEntity.ok(actualizada)
    }

    @DeleteMapping("/{id}")
    fun eliminarAeronave(@PathVariable id: Long): ResponseEntity<Void> {
        aeronaveService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
