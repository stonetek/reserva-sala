package com.stonnetek.reservas.service;

import com.stonnetek.reservas.entity.Reserva;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.enums.StatusReserva;
import com.stonnetek.reservas.exception.RecursoNaoEncontradoException;
import com.stonnetek.reservas.exception.RegraNegocioException;
import com.stonnetek.reservas.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaService salaService;

    public Reserva criar(Reserva reserva) {

        if (reserva.getHoraFim().isBefore(reserva.getHoraInicio())
                || reserva.getHoraFim().equals(reserva.getHoraInicio())) {

            throw new RegraNegocioException(
                    "Hora fim deve ser maior que hora início."
            );
        }

        Sala sala = salaService.buscarPorId(reserva.getSala().getId());

        boolean conflito = reservaRepository.existeConflito(
                sala.getId(),
                reserva.getData(),
                reserva.getHoraInicio(),
                reserva.getHoraFim()
        );

        if (conflito) {
            throw new RegraNegocioException(
                    "Já existe uma reserva nesse horário."
            );
        }

        reserva.setSala(sala);
        reserva.setStatus(StatusReserva.ATIVA);

        return reservaRepository.save(reserva);
    }

    public Reserva cancelar(Long id) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Reserva não encontrada."));

        reserva.setStatus(StatusReserva.CANCELADA);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> agendaDiaria(LocalDate data) {
        return reservaRepository.findByData(data);
    }
}
