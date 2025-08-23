package com.example.iub.repositories

import com.example.iub.entities.Vuelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface VueloRepository : JpaRepository<Vuelo, Int> {

    @Query(
        """
        select v
        from Vuelo v
        where (:origen is null or lower(v.origen) = lower(:origen))
          and (:destino is null or lower(v.destino) = lower(:destino))
          and (
                :fecha is null 
                or (v.fecha >= :fecha and v.fecha < :fechaHasta)
              )
        """
    )
    fun buscar(
        @Param("origen") origen: String?,
        @Param("destino") destino: String?,
        @Param("fecha") fecha: LocalDateTime?,
        @Param("fechaHasta") fechaHasta: LocalDateTime?
    ): List<Vuelo>
}

