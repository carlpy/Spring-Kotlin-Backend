package com.example.iub.controllers

import com.example.iub.controllers.contracts.AuthController
import com.example.iub.dto.requests.LoginRequest
import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.LoginResponse
import com.example.iub.dto.responses.UsuarioResponse
import com.example.iub.services.contracts.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthControllerImpl(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): LoginResponse =
        authService.login(req)

    @PostMapping("/register")
    fun register(@RequestBody req: UsuarioRequest): UsuarioResponse =
        authService.register(req)
}
