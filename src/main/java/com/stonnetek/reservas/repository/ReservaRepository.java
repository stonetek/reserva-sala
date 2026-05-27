package com.stonnetek.reservas.repository;

import com.stonnetek.reservas.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByData(LocalDate data);

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
        FROM Reserva r
        WHERE r.sala.id = :salaId
        AND r.status = 'ATIVA'
        AND r.data = :data
        AND (
            :horaInicio < r.horaFim
            AND :horaFim > r.horaInicio
        )
    """)
    boolean existeConflito(
            Long salaId,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim
    );
}
