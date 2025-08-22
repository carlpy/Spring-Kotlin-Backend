package com.example.iub.dto.responses

data class VueloResponse (
    val id: Long,
    val numeroVuelo: String,
    val origen: String,
    val destino: String,
    val fechaSalida: String,
    val fechaLlegada: String,
    val estado: String,
    val aerolineaId: Long
)