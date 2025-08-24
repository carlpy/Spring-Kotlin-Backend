package com.example.iub.config

import com.example.iub.enums.RolesUsuario

import com.example.iub.entities.Usuario
import com.example.iub.repositories.UsuarioRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Configuration
class AdminBootstrap {

    @Bean
    fun initAdmin(
        repo: UsuarioRepository,
        encoder: PasswordEncoder,
        @Value("\${ADMIN_EMAIL:admin@aerotech.local}") adminEmail: String,
        @Value("\${ADMIN_PASSWORD:Admin123!}") adminPassword: String,
        @Value("\${ADMIN_NAME:Administrador}") adminName: String
    ): CommandLineRunner = CommandLineRunner {
        if (!repo.existsByRol(RolesUsuario.ADMIN)) {
            val admin = Usuario(
                nombre = adminName,
                email = adminEmail,
                password = encoder.encode(adminPassword),
                rol = RolesUsuario.ADMIN,
                fechaCreacion = LocalDateTime.now()
            )
            repo.save(admin)
            println("✅ Admin bootstrap: creado admin '$adminEmail'")
        } else {
            println("ℹ️ Admin bootstrap: ya existe un ADMIN, no se crea otro.")
        }
    }
}
