package com.example.iub.entities

import com.example.iub.enums.EstadoReserva
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reservas")
data class Reserva(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idReserva: Int = 0,

    @Column(nullable = false)
    val fechaReserva: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var estado: EstadoReserva,

    @ManyToOne @JoinColumn(name = "id_usuario", nullable = false)
    val usuario: Usuario,

    @ManyToOne @JoinColumn(name = "id_vuelo", nullable = false)
    val vuelo: Vuelo,

    @OneToMany(mappedBy = "reserva")
    val pasajeros: List<Pasajero> = emptyList()
)
