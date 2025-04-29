package com.teste.pratico.desafios.Desafio.Tecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDesafio;

    private int pontuacao;

    @ManyToOne
    private Jogador jogador;

    @ManyToOne
    private Torneio torneio;

    private LocalDateTime dataRegistro = LocalDateTime.now();

}

