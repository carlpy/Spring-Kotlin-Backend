package com.example.iub.utils

import com.example.iub.dto.responses.AeronaveResponse
import com.example.iub.dto.responses.PasajeroResponse
import com.example.iub.dto.responses.ReservaResponse
import com.example.iub.dto.responses.UsuarioResponse
import com.example.iub.dto.responses.VueloResponse
import com.example.iub.entities.Aeronave
import com.example.iub.entities.Pasajero
import com.example.iub.entities.Reserva
import com.example.iub.entities.Usuario
import com.example.iub.entities.Vuelo

fun Reserva.toDto() = ReservaResponse(
    idReserva = this.idReserva,
    fechaReserva = this.fechaReserva,
    estado = this.estado,
    idUsuario = this.usuario.idUsuario,
    idVuelo = this.vuelo.idVuelo
)

fun Pasajero.toResponse() = PasajeroResponse(
    idPasajero = idPasajero,
    nombre = nombre,
    documento = documento,
    edad = edad,
    idReserva = reserva.idReserva
)

fun Usuario.toResponse() = UsuarioResponse(
    idUsuario = idUsuario,
    nombre = nombre,
    email = email,
    rol = rol,
    fechaCreacion = fechaCreacion
)

fun Aeronave.toResponse() = AeronaveResponse(
    idAeronave = idAeronave,
    modelo = modelo,
    capacidad = capacidad
)

fun Vuelo.toResponse() = VueloResponse(
    idVuelo = idVuelo,
    origen = origen,
    destino = destino,
    fecha = fecha,
    precio = precio,
    estado = estado,
    idAeronave = aeronave.idAeronave
)