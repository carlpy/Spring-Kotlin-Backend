package com.example.iub.controllers

import com.example.iub.dto.requests.AeronaveRequest
import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.AeronaveResponse
import com.example.iub.dto.responses.UsuarioResponse
import com.example.iub.services.contracts.AeronaveService
import com.example.iub.services.contracts.UsuarioService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
class AdminControllerImpl(
    private val usuarioService: UsuarioService,
    private val aeronaveService: AeronaveService
) {
    // Gestión de usuarios (crear empleados)
    @PostMapping("/usuarios")
    fun crearEmpleado(@RequestBody req: UsuarioRequest): UsuarioResponse =
        usuarioService.crearEmpleado(req)

    @GetMapping("/usuarios")
    fun listarUsuarios(): List<UsuarioResponse> =
        usuarioService.listar()

    // Gestión de aeronaves
    @PostMapping("/aeronaves")
    fun crearAeronave(@RequestBody req: AeronaveRequest): AeronaveResponse =
        aeronaveService.crear(req)

    @GetMapping("/aeronaves")
    fun listarAeronaves(): List<AeronaveResponse> =
        aeronaveService.listar()
}
