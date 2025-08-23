package com.example.iub.services

import com.example.iub.dto.requests.AeronaveRequest
import com.example.iub.dto.responses.AeronaveResponse
import com.example.iub.repositories.AeronaveRepository
import com.example.iub.services.contracts.AeronaveService
import org.springframework.stereotype.Service
import com.example.iub.entities.Aeronave
import com.example.iub.utils.toResponse

@Service
class AeronaveServiceImpl(   // <- note: Impl (not Imp)
    private val aeronaveRepository: AeronaveRepository
) : AeronaveService {

    override fun crear(req: AeronaveRequest): AeronaveResponse {
        require(req.capacidad > 0) { "La capacidad debe ser mayor a 0" }
        val saved = aeronaveRepository.save(
            Aeronave(
                modelo = req.modelo,
                capacidad = req.capacidad
            )
        )
        return saved.toResponse()
    }

    override fun listar(): List<AeronaveResponse> =
        aeronaveRepository.findAll().map { it.toResponse() }
}
