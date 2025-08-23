package com.example.iub.controllers.contracts

import com.example.iub.dto.requests.LoginRequest
import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.LoginResponse
import com.example.iub.dto.responses.UsuarioResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/auth")
interface AuthController {
    @PostMapping("/login")
    fun login(@RequestBody @Valid req: LoginRequest): LoginResponse

    @PostMapping("/register")
    fun register(@RequestBody @Valid req: UsuarioRequest): UsuarioResponse
}
