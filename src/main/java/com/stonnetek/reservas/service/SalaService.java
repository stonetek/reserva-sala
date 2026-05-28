package com.stonnetek.reservas.service;

import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.exception.RecursoNaoEncontradoException;
import com.stonnetek.reservas.repository.ReservaRepository;
import com.stonnetek.reservas.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;
    private final ReservaRepository reservaRepository;

    public Sala salvar(Sala sala) {
        return salaRepository.save(sala);
    }

    public List<Sala> listar() {
        return salaRepository.findAll();
    }

    public Sala buscarPorId(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Sala não encontrada."));
    }

    public List<Sala> buscarSalasLivres(LocalDate data, LocalTime horaInicio, LocalTime horaFim) {
        List<Sala> todasSalas = salaRepository.findAll();
        return todasSalas.stream()
                .filter(sala -> !reservaRepository.existeConflito(
                        sala.getId(),
                        data,
                        horaInicio,
                        horaFim
                ))
                .toList();
    }
}
