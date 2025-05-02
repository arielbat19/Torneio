package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public JogadorDTO criarJogador(JogadorDTO dto) {
        Jogador jogador = dto.toEntity();
        Jogador salvo = jogadorRepository.save(jogador);
        return JogadorDTO.fromEntity(salvo);
    }

    public List<JogadorDTO> listarJogadores() {
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<JogadorDTO> buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .map(JogadorDTO::fromEntity);
    }

    public JogadorDTO buscarPorIdOuNome(String valor) {
        try {
            Long id = Long.parseLong(valor);
            return jogadorRepository.findById(id)
                    .map(j -> new JogadorDTO(j.getId(), j.getNome(), j.getEmail()))
                    .orElseThrow(() -> new NoSuchElementException("Jogador não encontrado com ID: " + id));
        } catch (NumberFormatException e) {
            Jogador jogador = jogadorRepository.findByNomeIgnoreCase(valor)
                    .orElseThrow(() -> new NoSuchElementException("Jogador não encontrado com nome: " + valor));
            return new JogadorDTO(jogador.getId(), jogador.getNome(), jogador.getEmail());
        }
    }

    public Optional<JogadorDTO> atualizarJogador(Long id, JogadorDTO dto) {
        return jogadorRepository.findById(id).map(jogadorExistente -> {
            if (dto.nome() != null && !dto.nome().isBlank()) {
                jogadorExistente.setNome(dto.nome());
            }
            if (dto.email() != null && !dto.email().isBlank()) {
                jogadorExistente.setEmail(dto.email());
            }
            Jogador atualizado = jogadorRepository.save(jogadorExistente);
            return JogadorDTO.fromEntity(atualizado);
        });
    }


    public boolean excluirJogador(Long id) {
        if (jogadorRepository.existsById(id)) {
            jogadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
