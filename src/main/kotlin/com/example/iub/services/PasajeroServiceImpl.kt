package com.example.iub.services


import com.example.iub.dto.responses.PasajeroResponse
import com.example.iub.repositories.PasajeroRepository
import com.example.iub.services.contracts.PasajeroService
import com.example.iub.utils.toResponse
import org.springframework.stereotype.Service

@Service
class PasajeroServiceImpl(
    private val pasajeroRepository: PasajeroRepository
) : PasajeroService {

    override fun porVuelo(idVuelo: Int): List<PasajeroResponse> =
        pasajeroRepository.findByReservaVueloId(idVuelo)
            .map { it.toResponse() }
}

