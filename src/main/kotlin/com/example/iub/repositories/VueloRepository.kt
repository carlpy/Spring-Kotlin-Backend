package com.example.iub.repositories

import com.example.iub.entities.Vuelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VueloRepository : JpaRepository<Vuelo, Long>
