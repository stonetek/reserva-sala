package com.stonnetek.reservas.dto.response;

import com.stonnetek.reservas.enums.TipoSala;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaResponseDTO {

    private Long id;

    private String nome;

    private TipoSala tipo;

    private Integer capacidade;
}
