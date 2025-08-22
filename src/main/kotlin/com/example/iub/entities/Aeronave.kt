package com.example.iub.entities

import jakarta.persistence.*

@Entity
@Table(name = "aeronave")
class Aeronave(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeronave")
    var idAeronave: Long = 0,

    @Column(nullable = false, length = 100)
    var modelo: String,

    @Column(nullable = false)
    var capacidad: Int
)

