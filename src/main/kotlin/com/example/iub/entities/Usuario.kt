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
    var nombre: String,

    @Column(nullable = false, unique = true, length = 100)
    var email: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var rol: RolesUsuario,

    @Column(nullable = false)
    var fechaCreacion: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "usuario")
    val reservas: List<Reserva> = emptyList()
)

