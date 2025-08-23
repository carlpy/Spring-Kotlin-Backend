package com.example.iub.services.contracts

import com.example.iub.dto.requests.UsuarioRequest
import com.example.iub.dto.responses.UsuarioResponse

interface UsuarioService {
    fun crearEmpleado(req: UsuarioRequest): UsuarioResponse   // empleados no pueden crear ADMIN
    fun listar(): List<UsuarioResponse>
}
