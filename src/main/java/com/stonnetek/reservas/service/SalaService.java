package com.stonnetek.reservas.service;

import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.exception.RecursoNaoEncontradoException;
import com.stonnetek.reservas.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

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
}
