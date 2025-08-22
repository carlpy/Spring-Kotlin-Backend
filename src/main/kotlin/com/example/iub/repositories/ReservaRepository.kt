package com.example.iub.repositories

import com.example.iub.entities.Reserva
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservaRepository : JpaRepository<Reserva, Long>
