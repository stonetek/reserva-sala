package com.stonnetek.reservas.controller;

import com.stonnetek.reservas.dto.mapper.ReservaMapper;
import com.stonnetek.reservas.dto.request.ReservaRequestDTO;
import com.stonnetek.reservas.dto.response.ReservaResponseDTO;
import com.stonnetek.reservas.entity.Reserva;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.service.ReservaService;
import com.stonnetek.reservas.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final SalaService salaService;

    @Operation(summary = "Criar nova reserva")
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar (@Valid @RequestBody ReservaRequestDTO dto) {
        Sala sala = salaService.buscarPorId(dto.getSalaId());
        Reserva reserva = ReservaMapper.toEntity(dto, sala);
        ReservaResponseDTO response = ReservaMapper.toDTO(reservaService.criar(reserva));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Cancelar reserva")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelar(@PathVariable Long id) {
        Reserva reserva = reservaService.cancelar(id);
        return ResponseEntity.ok(ReservaMapper.toDTO(reserva));
    }

    @Operation(summary = "Consultar agenda diária")
    @GetMapping("/agenda")
    public ResponseEntity<List<ReservaResponseDTO>> agendaDiaria( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<ReservaResponseDTO> response =
                reservaService.agendaDiaria(data)
                        .stream()
                        .map(ReservaMapper::toDTO)
                        .toList();
        return ResponseEntity.ok(response);
    }
}
