package com.teste.pratico.desafios.Desafio.Tecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Torneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "torneio_jogador",
            joinColumns = @JoinColumn(name = "torneio_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private List<Jogador> jogadores;

    @OneToMany(mappedBy = "torneio")
    private List<Desafio> desafios;

}

