package com.stonnetek.reservas.dto.mapper;

import com.stonnetek.reservas.dto.request.SalaRequestDTO;
import com.stonnetek.reservas.dto.response.SalaResponseDTO;
import com.stonnetek.reservas.entity.Sala;

public class SalaMapper {

    public static Sala toEntity(SalaRequestDTO dto) {

        Sala sala = new Sala();

        sala.setNome(dto.getNome());
        sala.setTipo(dto.getTipo());
        sala.setCapacidade(dto.getCapacidade());

        return sala;
    }

    public static SalaResponseDTO toDTO(Sala sala) {

        return SalaResponseDTO.builder()
                .id(sala.getId())
                .nome(sala.getNome())
                .tipo(sala.getTipo())
                .capacidade(sala.getCapacidade())
                .build();
    }
}
