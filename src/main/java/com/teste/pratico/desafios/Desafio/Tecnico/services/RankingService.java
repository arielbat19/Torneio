package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final ResultadoRepository resultadoRepository;

    public RankingService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public List<RankingResponse> getRankingGlobal() {
        return resultadoRepository.buscarRankingGlobal();
    }

    public List<RankingResponse> getRankingPorTorneio(Long torneioId) {
        return resultadoRepository.buscarRankingPorTorneio(torneioId);
    }
}

