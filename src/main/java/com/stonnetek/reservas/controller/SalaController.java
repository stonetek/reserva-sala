package com.stonnetek.reservas.controller;

import com.stonnetek.reservas.dto.mapper.SalaMapper;
import com.stonnetek.reservas.dto.request.SalaRequestDTO;
import com.stonnetek.reservas.dto.response.SalaResponseDTO;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping
    public SalaResponseDTO criar(@Valid @RequestBody SalaRequestDTO dto) {
        Sala sala = SalaMapper.toEntity(dto);
        return SalaMapper.toDTO( salaService.salvar(sala));
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listar();
    }
}
