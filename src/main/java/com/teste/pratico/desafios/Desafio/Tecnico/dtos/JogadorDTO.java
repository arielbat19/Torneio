package com.teste.pratico.desafios.Desafio.Tecnico.dtos;

import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;

public record JogadorDTO(Long id, String nome, String email) {

    public static JogadorDTO fromEntity(Jogador jogador) {
        return new JogadorDTO(jogador.getId(), jogador.getNome(), jogador.getEmail());
    }

    public Jogador toEntity() {
        Jogador jogador = new Jogador();
        jogador.setId(id);
        jogador.setNome(nome);
        jogador.setEmail(email);
        return jogador;
    }
}
