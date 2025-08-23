package com.example.iub.dto.requests

import com.example.iub.enums.RolesUsuario
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UsuarioRequest(
    @field:NotBlank @field:Size(max = 100)
    val nombre: String,

    @field:Email @field:NotBlank
    val email: String,

    @field:NotBlank @field:Size(min = 6, max = 100)
    val password: String,

    @field:NotNull
    val rol: RolesUsuario
)