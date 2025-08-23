package com.example.iub.controllers

import com.example.iub.dto.requests.ReservaPasajeroRequest
import com.example.iub.dto.responses.ReservaResponse
import com.example.iub.dto.responses.VueloResponse
import com.example.iub.services.contracts.ReservaService
import com.example.iub.services.contracts.VueloService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/cliente")
@PreAuthorize("hasRole('CLIENTE')")
class ClienteControllerImpl(
    private val reservaService: ReservaService,
    private val vueloService: VueloService
) {
    // Buscar vuelos (cliente puede ver)
    @GetMapping("/vuelos")
    fun buscarVuelos(
        @RequestParam(required = false) origen: String?,
        @RequestParam(required = false) destino: String?,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        fecha: LocalDateTime?
    ): List<VueloResponse> = vueloService.buscar(origen, destino, fecha)

    // Crear reserva (con pasajeros) para el usuario autenticado
    @PostMapping("/reservas")
    fun crearReserva(
        @RequestBody body: ReservaPasajeroRequest,
        auth: Authentication
    ): ReservaResponse {
        // opcionalmente, valida que body.reserva.idUsuario == currentUserId(auth)
        return reservaService.crearConPasajeros(body)
    }

    // Ver mis reservas
    @GetMapping("/reservas")
    fun misReservas(auth: Authentication): List<ReservaResponse> =
        reservaService.mias(currentUserId(auth))

    // Cancelar mi reserva
    @DeleteMapping("/reservas/{id}")
    fun cancelarMiReserva(
        @PathVariable id: Int,
        auth: Authentication
    ) {
        reservaService.cancelarPropia(id, currentUserId(auth))
    }
}
