package com.teste.pratico.desafios.Desafio.Tecnico.service;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.ResultadoRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.services.RankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RankingServiceTest {

    private ResultadoRepository resultadoRepository;
    private RankingService rankingService;

    @BeforeEach
    void setUp() {
        resultadoRepository = mock(ResultadoRepository.class);
        rankingService = new RankingService(resultadoRepository);
    }

    @Test
    void testGetRankingGlobal() {
        List<RankingResponse> mockRanking = Arrays.asList(
                new RankingResponse(1L, "Jogador A", 100L),
                new RankingResponse(2L, "Jogador B", 80L)
        );

        when(resultadoRepository.buscarRankingGlobal()).thenReturn(mockRanking);

        List<RankingResponse> result = rankingService.getRankingGlobal();

        assertEquals(2, result.size());
        assertEquals("Jogador A", result.get(0).jogadorNome());
        verify(resultadoRepository, times(1)).buscarRankingGlobal();
    }

    @Test
    void testGetRankingPorTorneio() {
        Long torneioId = 5L;
        List<RankingResponse> mockRanking = Arrays.asList(
                new RankingResponse(1L, "Jogador X", 150L)
        );

        when(resultadoRepository.buscarRankingPorTorneio(torneioId)).thenReturn(mockRanking);

        List<RankingResponse> result = rankingService.getRankingPorTorneio(torneioId);

        assertEquals(1, result.size());
        assertEquals("Jogador X", result.get(0).jogadorNome());
        verify(resultadoRepository, times(1)).buscarRankingPorTorneio(torneioId);
    }
}
