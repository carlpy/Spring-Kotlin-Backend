package com.example.iub.services.contracts

import com.example.iub.dto.requests.LoginRequest
import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.LoginResponse
import com.example.iub.dto.responses.UsuarioResponse

interface AuthService {
    fun login(req: LoginRequest): LoginResponse
    fun register(req: UsuarioRequest): UsuarioResponse
}
