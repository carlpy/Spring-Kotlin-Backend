package com.example.iub.controllers

import com.example.iub.dto.requests.VueloRequest
import com.example.iub.dto.responses.PasajeroResponse
import com.example.iub.dto.responses.ReservaResponse
import com.example.iub.dto.responses.VueloResponse
import com.example.iub.enums.EstadoVuelo
import com.example.iub.services.contracts.PasajeroService
import com.example.iub.services.contracts.ReservaService
import com.example.iub.services.contracts.VueloService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/empleados")
@PreAuthorize("hasRole('EMPLEADO') or hasRole('ADMIN')")
class EmpleadoControllerImpl(
    private val vueloService: VueloService,
    private val reservaService: ReservaService,
    private val pasajeroService: PasajeroService
) {
    // Gestión de vuelos
    @PostMapping("/vuelos")
    fun crearVuelo(@RequestBody req: VueloRequest): VueloResponse =
        vueloService.crear(req)

    @PutMapping("/vuelos/{id}")
    fun actualizarVuelo(@PathVariable id: Int, @RequestBody req: VueloRequest): VueloResponse =
        vueloService.actualizar(id, req)

    @PatchMapping("/vuelos/{id}/estado")
    fun cambiarEstado(@PathVariable id: Int, @RequestParam estado: EstadoVuelo): VueloResponse =
        vueloService.cambiarEstado(id, estado)

    // Consultas
    @GetMapping("/vuelos")
    fun listarVuelos(
        @RequestParam(required = false) origen: String?,
        @RequestParam(required = false) destino: String?,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        fecha: LocalDateTime?
    ): List<VueloResponse> = vueloService.buscar(origen, destino, fecha)

    @GetMapping("/reservas")
    fun todasLasReservas(): List<ReservaResponse> = reservaService.todas()

    @GetMapping("/vuelos/{idVuelo}/pasajeros")
    fun pasajerosPorVuelo(@PathVariable idVuelo: Int): List<PasajeroResponse> =
        pasajeroService.porVuelo(idVuelo)
}
