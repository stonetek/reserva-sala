package com.stonnetek.reservas.repository;

import com.stonnetek.reservas.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    @Query("""
        SELECT s
        FROM Sala s
        WHERE s.id NOT IN (
            SELECT r.sala.id
            FROM Reserva r
            WHERE r.status = 'ATIVA'
            AND r.data = :data
            AND (
                :horaInicio < r.horaFim
                AND :horaFim > r.horaInicio
            )
        )
    """)
    List<Sala> buscarSalasLivres(
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim
    );
}
