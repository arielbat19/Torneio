package com.teste.pratico.desafios.Desafio.Tecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pontuacao;

    @ManyToOne
    @JoinColumn(name = "desafio_id")
    private Desafio desafio;

    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;

    private LocalDate dataRegistro;

}

