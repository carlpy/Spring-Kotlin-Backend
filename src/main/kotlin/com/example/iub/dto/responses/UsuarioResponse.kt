package com.example.iub.dto.responses

import com.example.iub.enums.RolesUsuario
import java.time.LocalDateTime

data class UsuarioResponse(
    val idUsuario: Int,
    val nombre: String,
    val email: String,
    val rol: RolesUsuario,
    val fechaCreacion: LocalDateTime
)