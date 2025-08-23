package com.example.iub.repositories

import com.example.iub.entities.Pasajero
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Query

@Repository
interface PasajeroRepository : JpaRepository<Pasajero, Int> {

    // Asientos ocupados por vuelo
    @Query("""
        select count(p) 
        from Pasajero p 
        where p.reserva.vuelo.idVuelo = :idVuelo
    """)
    fun countByVueloId(idVuelo: Int): Int

    // Listar pasajeros por vuelo
    @Query("""
        select p
        from Pasajero p
        where p.reserva.vuelo.idVuelo = :idVuelo
    """)
    fun findByReservaVueloId(idVuelo: Int): List<Pasajero>
}
