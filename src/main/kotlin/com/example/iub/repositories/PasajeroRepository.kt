package com.example.iub.repositories

import com.example.iub.entities.Pasajero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PasajeroRepository : JpaRepository<Pasajero, Long>
