package com.example.iub.entities

import com.example.iub.enums.RolesUsuario
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idUsuario: Int = 0,

    @Column(nullable = false, length = 100)
    val nombre: String,

    @Column(nullable = false, unique = true, length = 100)
    val email: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val rol: RolesUsuario,

    @Column(nullable = false)
    val fechaCreacion: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "usuario")
    val reservas: List<Reserva> = emptyList()
)

