package com.example.iub.dto.requests

data class UsuarioRequest(
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String
)