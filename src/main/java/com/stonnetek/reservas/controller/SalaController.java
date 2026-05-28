package com.stonnetek.reservas.controller;

import com.stonnetek.reservas.dto.mapper.SalaMapper;
import com.stonnetek.reservas.dto.request.SalaRequestDTO;
import com.stonnetek.reservas.dto.response.SalaResponseDTO;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
@Tag(name = "Salas")
public class SalaController {

    private final SalaService salaService;

    @Operation(summary = "Criar nova sala")
    @PostMapping
    public ResponseEntity<SalaResponseDTO> criar(@Valid @RequestBody SalaRequestDTO dto) {
        Sala sala = SalaMapper.toEntity(dto);
        SalaResponseDTO response = SalaMapper.toDTO(salaService.salvar(sala));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Listar salas")
    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listar() {
        List<SalaResponseDTO> response =
                salaService.listar()
                        .stream()
                        .map(SalaMapper::toDTO)
                        .toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar salas livres")
    @GetMapping("/livres")
    public ResponseEntity<List<SalaResponseDTO>> buscarSalasLivres(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaInicio,
                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
                                                                   LocalTime horaFim) {
        List<SalaResponseDTO> response = salaService.buscarSalasLivres(data, horaInicio, horaFim)
                        .stream()
                        .map(SalaMapper::toDTO)
                        .toList();

        return ResponseEntity.ok(response);
    }
}
