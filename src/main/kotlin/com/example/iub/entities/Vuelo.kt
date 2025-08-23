package com.example.iub.entities

import com.example.iub.enums.EstadoVuelo
import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "vuelos")
data class Vuelo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idVuelo: Int = 0,

    @Column(nullable = false, length = 100)
    var origen: String,

    @Column(nullable = false, length = 100)
    var destino: String,

    @Column(nullable = false)
    var fecha: LocalDateTime,

    @Column(nullable = false, precision = 10, scale = 2)
    var precio: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var estado: EstadoVuelo,

    @ManyToOne @JoinColumn(name = "id_aeronave", nullable = false)
    val aeronave: Aeronave,

    @OneToMany(mappedBy = "vuelo")
    val reservas: List<Reserva> = emptyList()
)