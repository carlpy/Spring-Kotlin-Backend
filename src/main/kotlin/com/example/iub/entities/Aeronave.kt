package com.example.iub.entities

import jakarta.persistence.*

@Entity
@Table(name = "aeronaves")
data class Aeronave(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idAeronave: Int = 0,

    @Column(nullable = false, length = 50)
    val modelo: String,

    @Column(nullable = false)
    val capacidad: Int,

    @OneToMany(mappedBy = "aeronave")
    val vuelos: List<Vuelo> = emptyList()
)

