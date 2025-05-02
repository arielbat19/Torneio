package com.teste.pratico.desafios.Desafio.Tecnico.service;
import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.services.JogadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JogadorServiceTest {

    private JogadorRepository jogadorRepository;
    private JogadorService jogadorService;

    @BeforeEach
    void setUp() {
        jogadorRepository = mock(JogadorRepository.class);
        jogadorService = new JogadorService(jogadorRepository);
    }

    @Test
    void testCriarJogador() {
        JogadorDTO dto = new JogadorDTO(null, "João", "joao@email.com");
        Jogador jogador = new Jogador(null, "João", "joao@email.com", LocalDate.now());
        Jogador salvo = new Jogador(1L, "João", "joao@email.com", LocalDate.now());

        when(jogadorRepository.save(ArgumentMatchers.any())).thenReturn(salvo);

        JogadorDTO result = jogadorService.criarJogador(dto);

        assertNotNull(result);
        assertEquals("João", result.nome());
    }

    @Test
    void testListarJogadores() {
        List<Jogador> jogadores = List.of(new Jogador(1L, "Ana", "ana@email.com", LocalDate.now()));
        when(jogadorRepository.findAll()).thenReturn(jogadores);

        List<JogadorDTO> result = jogadorService.listarJogadores();

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).nome());
    }

    @Test
    void testBuscarPorId() {
        Jogador jogador = new Jogador(1L, "Carlos", "carlos@email.com", LocalDate.now());
        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogador));

        Optional<JogadorDTO> result = jogadorService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Carlos", result.get().nome());
    }

    @Test
    void testBuscarPorIdOuNome_ComId() {
        Jogador jogador = new Jogador(1L, "Lucas", "lucas@email.com", LocalDate.now());
        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogador));

        JogadorDTO result = jogadorService.buscarPorIdOuNome("1");

        assertNotNull(result);
        assertEquals("Lucas", result.nome());
    }

    @Test
    void testBuscarPorIdOuNome_ComNome() {
        Jogador jogador = new Jogador(2L, "Mario", "mario@email.com", LocalDate.now());
        when(jogadorRepository.findByNomeIgnoreCase("Mario")).thenReturn(Optional.of(jogador));

        JogadorDTO result = jogadorService.buscarPorIdOuNome("Mario");

        assertNotNull(result);
        assertEquals("Mario", result.nome());
    }

    @Test
    void testAtualizarJogador() {
        Jogador jogadorExistente = new Jogador(1L, "VelhoNome", "velho@email.com", LocalDate.now());
        JogadorDTO dto = new JogadorDTO(1L, "NovoNome", "novo@email.com");

        when(jogadorRepository.findById(1L)).thenReturn(Optional.of(jogadorExistente));
        when(jogadorRepository.save(jogadorExistente)).thenReturn(jogadorExistente);

        Optional<JogadorDTO> result = jogadorService.atualizarJogador(1L, dto);

        assertTrue(result.isPresent());
        assertEquals("NovoNome", result.get().nome());
    }

    @Test
    void testExcluirJogador_Existente() {
        when(jogadorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(jogadorRepository).deleteById(1L);

        boolean result = jogadorService.excluirJogador(1L);

        assertTrue(result);
    }

    @Test
    void testExcluirJogador_Inexistente() {
        when(jogadorRepository.existsById(1L)).thenReturn(false);

        boolean result = jogadorService.excluirJogador(1L);

        assertFalse(result);
    }
}
