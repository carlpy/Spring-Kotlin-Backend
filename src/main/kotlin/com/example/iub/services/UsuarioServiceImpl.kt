package com.example.iub.services

import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.UsuarioResponse
import com.example.iub.repositories.UsuarioRepository
import com.example.iub.services.contracts.UsuarioService
import org.springframework.stereotype.Service
import com.example.iub.entities.Usuario
import com.example.iub.enums.RolesUsuario
import com.example.iub.utils.toResponse
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Service
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder
) : UsuarioService {

    override fun crearEmpleado(req: UsuarioRequest): UsuarioResponse {
        require(req.rol != RolesUsuario.ADMIN) {
            "No se permite crear usuarios con rol ADMIN desde este endpoint"
        }
        require(usuarioRepository.findByEmail(req.email) == null) {
            "El email ya está en uso"
        }

        val nuevo = Usuario(
            nombre = req.nombre,
            email = req.email,
            password = passwordEncoder.encode(req.password),
            rol = req.rol,
            fechaCreacion = LocalDateTime.now()
        )
        val saved = usuarioRepository.save(nuevo)
        return saved.toResponse()
    }

    override fun listar(): List<UsuarioResponse> =
        usuarioRepository.findAll().map { it.toResponse() }
}

