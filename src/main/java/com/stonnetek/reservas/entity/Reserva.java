package com.stonnetek.reservas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stonnetek.reservas.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String responsavel;

    @Column(name = "data_reserva", nullable = false)

    private LocalDate data;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusReserva status;


    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @JsonBackReference
    private Sala sala;

}






