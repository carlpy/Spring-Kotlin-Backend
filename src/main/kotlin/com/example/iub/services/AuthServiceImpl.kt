package com.example.iub.services

import com.example.iub.dto.requests.LoginRequest
import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.LoginResponse
import com.example.iub.dto.responses.UsuarioResponse
import com.example.iub.entities.Usuario
import com.example.iub.repositories.UsuarioRepository
import com.example.iub.services.contracts.AuthService
import com.example.iub.services.contracts.JWTService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val usuarioRepo: UsuarioRepository, // your JPA repo
    private val passwordEncoder: PasswordEncoder,
    private val jwt: JWTService
) : AuthService {

    override fun login(req: LoginRequest): LoginResponse {
        var user = usuarioRepo.findByEmail(req.email)

        if (user == null) {
            throw IllegalArgumentException("Usuario no encontrado")
        }

        require(passwordEncoder.matches(req.password, user.password)) { "Credenciales inválidas" }

        val token = jwt.generateToken(
            uid = user.idUsuario,
            email = user.email,
            role = "ROLE_${user.rol.name}" // keep Spring style "ROLE_X"
        )
        return LoginResponse(token)
    }

    override fun register(req: UsuarioRequest): UsuarioResponse {
        if (usuarioRepo.findByEmail(req.email) != null) {
            throw IllegalArgumentException("El email ya está en uso")
        }

        val saved = usuarioRepo.save(
            Usuario(
                nombre = req.nombre,
                email = req.email,
                password = passwordEncoder.encode(req.password),
                rol = req.rol,
                fechaCreacion = LocalDateTime.now()
            )
        )
        return UsuarioResponse(
            idUsuario = saved.idUsuario,
            nombre = saved.nombre,
            email = saved.email,
            rol = saved.rol,
            fechaCreacion = saved.fechaCreacion
        )
    }
}
