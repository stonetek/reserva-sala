package com.stonnetek.reservas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaRequestDTO {

    @NotBlank(message = "Responsável é obrigatório.")
    private String responsavel;

    @NotNull(message = "Data é obrigatória.")
    private LocalDate data;

    @NotNull(message = "Hora início é obrigatória.")
    private LocalTime horaInicio;

    @NotNull(message = "Hora fim é obrigatória.")
    private LocalTime horaFim;

    @NotNull(message = "Sala é obrigatória.")
    private Long salaId;
}
