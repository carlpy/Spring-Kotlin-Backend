package com.example.iub.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.math.BigDecimal

@Entity
@Table(name = "vuelo")
class Vuelo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    var idVuelo: Long = 0,

    @Column(nullable = false, length = 100)
    var origen: String,

    @Column(nullable = false, length = 100)
    var destino: String,

    @Column(name = "fecha_salida", nullable = false)
    var fechaSalida: LocalDateTime,

    @Column(name = "fecha_llegada", nullable = false)
    var fechaLlegada: LocalDateTime,

    @Column(nullable = false, precision = 10, scale = 2)
    var precio: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var estado: EstadoVuelo,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aeronave", nullable = false)
    var aeronave: Aeronave
)

enum class EstadoVuelo {
    PROGRAMADO, RETRASADO, CANCELADO, COMPLETADO
}
