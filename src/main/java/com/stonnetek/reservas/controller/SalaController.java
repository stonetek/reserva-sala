package com.stonnetek.reservas.controller;

import com.stonnetek.reservas.entity.Sala;
import com.stonnetek.reservas.service.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @PostMapping
    public Sala criar(@RequestBody Sala sala) {
        return salaService.salvar(sala);
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listar();
    }
}
