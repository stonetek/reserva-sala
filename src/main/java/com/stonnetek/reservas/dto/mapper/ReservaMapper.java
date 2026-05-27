package com.stonnetek.reservas.dto.mapper;

import com.stonnetek.reservas.dto.request.ReservaRequestDTO;
import com.stonnetek.reservas.dto.response.ReservaResponseDTO;
import com.stonnetek.reservas.entity.Reserva;
import com.stonnetek.reservas.entity.Sala;

public class ReservaMapper {

    public static Reserva toEntity(
            ReservaRequestDTO dto,
            Sala sala
    ) {

        Reserva reserva = new Reserva();

        reserva.setResponsavel(dto.getResponsavel());
        reserva.setData(dto.getData());
        reserva.setHoraInicio(dto.getHoraInicio());
        reserva.setHoraFim(dto.getHoraFim());
        reserva.setSala(sala);

        return reserva;
    }

    public static ReservaResponseDTO toDTO(Reserva reserva) {

        return ReservaResponseDTO.builder()
                .id(reserva.getId())
                .responsavel(reserva.getResponsavel())
                .data(reserva.getData())
                .horaInicio(reserva.getHoraInicio())
                .horaFim(reserva.getHoraFim())
                .status(reserva.getStatus())
                .salaId(reserva.getSala().getId())
                .nomeSala(reserva.getSala().getNome())
                .build();
    }
}
