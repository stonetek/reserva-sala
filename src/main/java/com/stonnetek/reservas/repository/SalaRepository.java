package com.stonnetek.reservas.repository;

import com.stonnetek.reservas.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {

}
