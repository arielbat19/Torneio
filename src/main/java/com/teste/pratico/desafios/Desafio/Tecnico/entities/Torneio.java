package com.teste.pratico.desafios.Desafio.Tecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Torneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Temporal(TemporalType.DATE)
    private Date data;
    private boolean finalizado;

    @ManyToMany
    @JoinTable(
            name = "torneio_jogador",
            joinColumns = @JoinColumn(name = "torneio_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private List<Jogador> jogadores;

    @OneToMany(mappedBy = "torneio")
    private List<Desafio> desafios;

}

