package com.example.iub.services.contracts

import com.example.iub.dto.requests.AeronaveRequest
import com.example.iub.dto.responses.AeronaveResponse

interface AeronaveService {
    fun crear(req: AeronaveRequest): AeronaveResponse
    fun listar(): List<AeronaveResponse>
}