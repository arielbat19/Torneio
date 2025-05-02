package com.teste.pratico.desafios.Desafio.Tecnico.repositories;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    @Query("SELECT new com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse(r.jogador.id, r.jogador.nome, SUM(r.pontuacao)) " +
            "FROM Resultado r " +
            "GROUP BY r.jogador.id, r.jogador.nome " +
            "ORDER BY SUM(r.pontuacao) DESC")
    List<RankingResponse> buscarRankingGlobal();

    @Query("SELECT new com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse(r.jogador.id, r.jogador.nome, SUM(r.pontuacao)) " +
            "FROM Resultado r " +
            "WHERE r.torneio.id = :torneioId " +
            "GROUP BY r.jogador.id, r.jogador.nome " +
            "ORDER BY SUM(r.pontuacao) DESC")
    List<RankingResponse> buscarRankingPorTorneio(@Param("torneioId") Long torneioId);
}

