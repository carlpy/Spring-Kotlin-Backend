package com.example.iub.controllers

import com.example.iub.entities.Usuario
import com.example.iub.services.UsuarioService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {

    @GetMapping
    fun listarUsuarios(): ResponseEntity<List<Usuario>> {
        return ResponseEntity.ok(usuarioService.listarTodos())
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("¡Hola, mundo!")
    }

    @GetMapping("/{id}")
    fun obtenerUsuario(@PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario = usuarioService.obtenerPorId(id)
        return ResponseEntity.ok(usuario)
    }

    @PostMapping
    fun crearUsuario(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val nuevo = usuarioService.crear(usuario)
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo)
    }

    @PutMapping("/{id}")
    fun actualizarUsuario(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val actualizado = usuarioService.actualizar(id, usuario)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: Long): ResponseEntity<Void> {
        usuarioService.eliminar(id)
        return ResponseEntity.noContent().build()
    }
}
