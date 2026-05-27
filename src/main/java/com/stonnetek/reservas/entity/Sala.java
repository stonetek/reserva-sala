package com.stonnetek.reservas.entity;

import com.stonnetek.reservas.enums.TipoSala;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoSala tipo;

    @Column(nullable = false)
    private Integer capacidade;


    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;


}
