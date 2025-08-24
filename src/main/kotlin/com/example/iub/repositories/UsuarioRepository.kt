package com.example.iub.repositories

import com.example.iub.entities.Usuario
import com.example.iub.enums.RolesUsuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Int> {
    fun findByEmail(email: String): Usuario?
    fun existsByRol(rol: RolesUsuario): Boolean
}
