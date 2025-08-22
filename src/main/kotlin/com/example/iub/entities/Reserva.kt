package com.example.iub.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reserva")
class Reserva(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    var idReserva: Long = 0,

    @Column(name = "fecha_reserva", nullable = false)
    var fechaReserva: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var estado: EstadoReserva,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    var usuario: Usuario,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vuelo", nullable = false)
    var vuelo: Vuelo
)

enum class EstadoReserva {
    ACTIVA, CANCELADA
}
