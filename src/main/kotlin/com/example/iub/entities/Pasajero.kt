package com.example.iub.entities

import jakarta.persistence.*

@Entity
@Table(name = "pasajeros")
data class Pasajero(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idPasajero: Int = 0,

    @Column(nullable = false, length = 100)
    val nombre: String,

    @Column(nullable = false, length = 50)
    val documento: String,

    @Column(nullable = false)
    val edad: Int,

    @ManyToOne @JoinColumn(name = "id_reserva", nullable = false)
    val reserva: Reserva
)



