package com.teste.pratico.desafios.Desafio.Tecnico.dtos;

public record RankingResponse(Long jogadorId, String jogadorNome, Long pontuacaoTotal) {

    public RankingResponse(Long jogadorId, String jogadorNome, Long pontuacaoTotal) {
        this.jogadorId = jogadorId;
        this.jogadorNome = jogadorNome;
        this.pontuacaoTotal = pontuacaoTotal;
    }
}

