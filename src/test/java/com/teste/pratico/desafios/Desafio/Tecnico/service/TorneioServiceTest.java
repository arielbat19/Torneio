package com.teste.pratico.desafios.Desafio.Tecnico.service;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.TorneioDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Torneio;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.services.TorneioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TorneioServiceTest {

    private TorneioRepository torneioRepository;
    private JogadorRepository jogadorRepository;
    private TorneioService torneioService;

    @BeforeEach
    void setUp() {
        torneioRepository = mock(TorneioRepository.class);
        jogadorRepository = mock(JogadorRepository.class);
        torneioService = new TorneioService(torneioRepository, jogadorRepository);
    }

    @Test
    void testCriar() {
        Torneio torneio = new Torneio();
        torneio.setId(1L);
        torneio.setNome("Torneio 1");
        torneio.setData(new Date());
        torneio.setFinalizado(false);
        torneio.setJogadores(new ArrayList<>());

        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.criar("Torneio 1", torneio.getData());

        assertEquals("Torneio 1", dto.nome());
        assertFalse(dto.finalizado());
        verify(torneioRepository, times(1)).save(any());
    }

    @Test
    void testAdicionarJogador() {
        Torneio torneio = new Torneio();
        torneio.setId(1L);
        torneio.setNome("Torneio");
        torneio.setJogadores(new ArrayList<>());

        Jogador jogador = new Jogador();
        jogador.setId(2L);
        jogador.setNome("Jogador 1");

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.adicionarJogador(1L, 2L);

        assertTrue(dto.jogadores().contains("Jogador 1"));
        verify(torneioRepository).save(torneio);
    }

    @Test
    void testRemoverJogador() {
        Jogador jogador = new Jogador();
        jogador.setId(2L);
        jogador.setNome("Jogador 1");

        Torneio torneio = new Torneio();
        torneio.setId(1L);
        torneio.setNome("Torneio");
        torneio.setJogadores(new ArrayList<>(List.of(jogador)));

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.removerJogador(1L, 2L);

        assertFalse(dto.jogadores().contains("Jogador 1"));
        verify(torneioRepository).save(torneio);
    }

    @Test
    void testListarJogadores() {
        Jogador jogador1 = new Jogador();
        jogador1.setNome("Jogador 1");

        Jogador jogador2 = new Jogador();
        jogador2.setNome("Jogador 2");

        Torneio torneio = new Torneio();
        torneio.setJogadores(Arrays.asList(jogador1, jogador2));

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));

        List<String> nomes = torneioService.listarJogadores(1L);

        assertEquals(2, nomes.size());
        assertTrue(nomes.contains("Jogador 1"));
    }

    @Test
    void testFinalizarTorneio() {
        Torneio torneio = new Torneio();
        torneio.setId(1L);
        torneio.setNome("Torneio");
        torneio.setJogadores(new ArrayList<>());

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.finalizarTorneio(1L);

        assertTrue(dto.finalizado());
        verify(torneioRepository).save(torneio);
    }
}
