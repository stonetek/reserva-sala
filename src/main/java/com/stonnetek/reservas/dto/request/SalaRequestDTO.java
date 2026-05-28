package com.stonnetek.reservas.dto.request;

import com.stonnetek.reservas.enums.TipoSala;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SalaRequestDTO {

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotNull(message = "Tipo é obrigatório.")
    private TipoSala tipo;

    @NotNull(message = "Capacidade é obrigatória.")
    @Min(value = 1, message = "Capacidade deve ser maior que zero.")
    private Integer capacidade;
}
