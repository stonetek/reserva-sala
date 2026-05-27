package com.stonnetek.reservas.controller;

import com.stonnetek.reservas.dto.mapper.ReservaMapper;
import com.stonnetek.reservas.dto.request.ReservaRequestDTO;
import com.stonnetek.reservas.dto.response.ReservaResponseDTO;
import com.stonnetek.reservas.entity.Reserva;
import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.service.ReservaService;
import com.stonnetek.reservas.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final SalaService salaService;

    @PostMapping
    public ReservaResponseDTO criar (@Valid @RequestBody ReservaRequestDTO dto) {
        Sala sala = salaService.buscarPorId(dto.getSalaId());
        Reserva reserva = ReservaMapper.toEntity(dto, sala);
        return ReservaMapper.toDTO( reservaService.criar(reserva));
    }

    @PatchMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable Long id) {
        return reservaService.cancelar(id);
    }

    @GetMapping("/agenda")
    public List<Reserva> agendaDiaria( @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return reservaService.agendaDiaria(data);
    }
}
