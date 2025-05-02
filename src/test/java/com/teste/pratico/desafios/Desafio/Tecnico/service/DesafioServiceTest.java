package com.teste.pratico.desafios.Desafio.Tecnico.service;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.*;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Resultado;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Torneio;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.ResultadoDesafioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.services.DesafioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DesafioServiceTest {

    private DesafioService service;
    private JogadorRepository jogadorRepo;
    private TorneioRepository torneioRepo;
    private ResultadoDesafioRepository resultadoRepo;

    private Jogador jogador;
    private Torneio torneio;

    @BeforeEach
    void setUp() {
        jogadorRepo = mock(JogadorRepository.class);
        torneioRepo = mock(TorneioRepository.class);
        resultadoRepo = mock(ResultadoDesafioRepository.class);

        service = new DesafioService(jogadorRepo, torneioRepo, resultadoRepo);

        jogador = new Jogador();
        jogador.setId(1L);

        torneio = new Torneio();
        torneio.setId(1L);
    }

    @Test
    void testExecutarFibonacci() {
        when(jogadorRepo.findById(1L)).thenReturn(Optional.of(jogador));
        when(torneioRepo.findById(1L)).thenReturn(Optional.of(torneio));

        FibonacciRequest req = new FibonacciRequest(1L, 1L, 10);
        FibonacciResponse res = service.executarFibonacci(req);

        assertEquals(55, res.resultado());
        assertEquals(10, res.pontosGanhos());
        verify(resultadoRepo).save(any(Resultado.class));
    }

    @Test
    void testExecutarPalindromoComSucesso() {
        when(jogadorRepo.findById(1L)).thenReturn(Optional.of(jogador));
        when(torneioRepo.findById(1L)).thenReturn(Optional.of(torneio));

        PalindromeRequest req = new PalindromeRequest(1L, 1L, "Ame a ema");
        PalindromeResponse res = service.executarPalindromo(req);

        assertTrue(res.ehPalindromo());
        assertEquals(5, res.pontosGanhos());
        verify(resultadoRepo).save(any(Resultado.class));
    }

    @Test
    void testExecutarPalindromoFalso() {
        when(jogadorRepo.findById(1L)).thenReturn(Optional.of(jogador));
        when(torneioRepo.findById(1L)).thenReturn(Optional.of(torneio));

        PalindromeRequest req = new PalindromeRequest(1L, 1L, "OpenAI");
        PalindromeResponse res = service.executarPalindromo(req);

        assertFalse(res.ehPalindromo());
        assertEquals(0, res.pontosGanhos());
        verify(resultadoRepo, never()).save(any());
    }

    @Test
    void testOrdenarArray() {
        when(jogadorRepo.findById(1L)).thenReturn(Optional.of(jogador));
        when(torneioRepo.findById(1L)).thenReturn(Optional.of(torneio));

        SortingRequest req = new SortingRequest(1L, 1L, Arrays.asList(5, 3, 1));
        SortingResponse res = service.ordenarArray(req);

        assertEquals(Arrays.asList(1, 3, 5), res.resultado());
        assertEquals(7, res.pontosGanhos());
        verify(resultadoRepo).save(any(Resultado.class));
    }
}

