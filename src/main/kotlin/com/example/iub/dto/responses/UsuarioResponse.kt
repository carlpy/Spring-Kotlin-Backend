package com.example.iub.dto.responses

data class UsuarioResponse(
    val idUsuario: Long,
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String,
    val fechaCreacion: String
)