package com.example.iub.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "usuario")
class Usuario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    var idUsuario: Long = 0,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(nullable = false, unique = true, length = 100)
    var email: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var rol: RolUsuario,

    @Column(name = "fecha_creacion", nullable = false)
    var fechaCreacion: LocalDateTime = LocalDateTime.now()
)

enum class RolUsuario {
    ADMIN, CLIENTE, EMPLEADO
}

