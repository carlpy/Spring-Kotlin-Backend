package com.example.iub.repositories

import com.example.iub.entities.Aeronave
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AeronaveRepository : JpaRepository<Aeronave, Long>
