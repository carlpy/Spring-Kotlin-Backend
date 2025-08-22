package com.example.iub.entities

import jakarta.persistence.*

@Entity
@Table(name = "pasajero")
class Pasajero(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    var idPasajero: Long = 0,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(nullable = false, length = 50)
    var documento: String,

    @Column(nullable = false)
    var edad: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    var reserva: Reserva
)

