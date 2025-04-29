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
public class Desafio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Integer pontuacaoMaxima;


    @ManyToOne
    @JoinColumn(name = "torneio_id")
    private Torneio torneio;

    @OneToMany(mappedBy = "desafio")
    private List<Resultado> resultados;

}

