package com.teste.pratico.desafios.Desafio.Tecnico.service;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.TorneioDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Torneio;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.services.TorneioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class TorneioServiceTest {

    @InjectMocks
    private TorneioService torneioService;

    @Mock
    private TorneioRepository torneioRepository;

    @Mock
    private JogadorRepository jogadorRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarTorneio_DeveSalvarETornarDTO() {
        Torneio torneio = new Torneio(1L, "Torneio Teste", new Date(), false, new ArrayList<>());
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.criar("Torneio Teste", new Date());

        assertNotNull(dto);
        assertEquals("Torneio Teste", dto.nome());
    }

    @Test
    void adicionarJogador_DeveAdicionarJogador() {
        Torneio torneio = new Torneio(1L, "Torneio", new Date(), false, new ArrayList<>());
        Jogador jogador = new Jogador(2L, "João", "teste@gmail", LocalDate.now());

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.adicionarJogador(1L, 2L);

        assertTrue(dto.jogadores().contains("João"));
    }

    @Test
    void adicionarJogador_DeveLancarErroSeTorneioFinalizado() {
        Torneio torneio = new Torneio(1L, "Torneio", new Date(), true, new ArrayList<>());
        Jogador jogador = new Jogador(2L, "João", "teste@gmail", LocalDate.now());

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            torneioService.adicionarJogador(1L, 2L);
        });

        assertEquals("Torneio finalizado, não é possível adicionar jogadores", ex.getMessage());
    }

    @Test
    void removerJogador_DeveRemoverJogador() {
        Jogador jogador = new Jogador(2L, "João", "teste@gmail", LocalDate.now());
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(jogador);
        Torneio torneio = new Torneio(1L, "Torneio", new Date(), false, jogadores);

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(jogadorRepository.findById(2L)).thenReturn(Optional.of(jogador));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.removerJogador(1L, 2L);

        assertFalse(dto.jogadores().contains("João"));
    }

    @Test
    void listarJogadores_DeveRetornarNomes() {
        Jogador jogador1 = new Jogador(1L, "Alice", "teste@gmail", LocalDate.now());
        Jogador jogador2 = new Jogador(2L, "Bob", "teste@gmail", LocalDate.now());
        Torneio torneio = new Torneio(1L, "Torneio", new Date(), false, Arrays.asList(jogador1, jogador2));

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));

        List<String> nomes = torneioService.listarJogadores(1L);

        assertEquals(2, nomes.size());
        assertTrue(nomes.contains("Alice"));
        assertTrue(nomes.contains("Bob"));
    }

    @Test
    void finalizarTorneio_DeveSetarFinalizadoComoTrue() {
        Torneio torneio = new Torneio(1L, "Torneio", new Date(), false, new ArrayList<>());

        when(torneioRepository.findById(1L)).thenReturn(Optional.of(torneio));
        when(torneioRepository.save(any())).thenReturn(torneio);

        TorneioDTO dto = torneioService.finalizarTorneio(1L);

        assertTrue(dto.finalizado());
    }
}
