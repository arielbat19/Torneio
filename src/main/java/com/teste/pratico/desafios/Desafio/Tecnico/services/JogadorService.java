package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

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

    public Optional<JogadorDTO> atualizarJogador(Long id, JogadorDTO dto) {
        return jogadorRepository.findById(id).map(jogadorExistente -> {
            jogadorExistente.setNome(dto.nome());
            jogadorExistente.setEmail(dto.email());
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
