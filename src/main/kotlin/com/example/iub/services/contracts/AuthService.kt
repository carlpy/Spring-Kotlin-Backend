package com.example.iub.services.contracts

import com.example.iub.dto.responses.UsuarioResponse

interface AuthService {
    fun login(email: String, password: String): String
    fun registerCliente(nombre: String, email: String, rawPwd: String): UsuarioResponse
}
