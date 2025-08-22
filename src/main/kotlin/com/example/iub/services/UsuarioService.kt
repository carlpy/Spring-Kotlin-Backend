package com.example.iub.services

import com.example.iub.entities.Usuario
import com.example.iub.repositories.UsuarioRepository
import com.example.iub.exceptions.UsuarioNotFoundException
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun listarTodos(): List<Usuario> = usuarioRepository.findAll()

    fun obtenerPorId(id: Long): Usuario =
        usuarioRepository.findById(id).orElseThrow {
            UsuarioNotFoundException("Usuario con id $id no encontrado")
        }

    fun crear(usuario: Usuario): Usuario = usuarioRepository.save(usuario)

    fun actualizar(id: Long, usuario: Usuario): Usuario {
        val existente = obtenerPorId(id)
        existente.nombre = usuario.nombre
        existente.email = usuario.email
        existente.password = usuario.password
        existente.rol = usuario.rol
        return usuarioRepository.save(existente)
    }

    fun eliminar(id: Long) {
        val usuario = obtenerPorId(id)
        usuarioRepository.delete(usuario)
    }
}
