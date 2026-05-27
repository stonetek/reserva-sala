package com.stonnetek.reservas.dto.response;

import com.stonnetek.reservas.enums.StatusReserva;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ReservaResponseDTO {

    private Long id;

    private String responsavel;

    private LocalDate data;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private StatusReserva status;

    private Long salaId;

    private String nomeSala;
}
