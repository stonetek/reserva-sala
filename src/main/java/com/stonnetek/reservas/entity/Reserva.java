package com.stonnetek.reservas.entity;

import com.stonnetek.reservas.enums.StatusReserva;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String responsavel;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusReserva status;

    // Relacionamento: cada reserva pertence a uma sala
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;


}






