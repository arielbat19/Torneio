package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.TorneioDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Torneio;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TorneioService {

    private final TorneioRepository torneioRepository;

    private final JogadorRepository jogadorRepository;

    public TorneioService(TorneioRepository torneioRepository, JogadorRepository jogadorRepository) {
        this.torneioRepository = torneioRepository;
        this.jogadorRepository = jogadorRepository;
    }


    public TorneioDTO criar(String nome, Date data) {
        Torneio t = new Torneio();
        t.setNome(nome);
        t.setData(data);
        t.setFinalizado(false);
        return toDTO(torneioRepository.save(t));
    }

    public TorneioDTO adicionarJogador(Long torneioId, Long jogadorId) {
        Torneio t = torneioRepository.findById(torneioId).orElseThrow();
        Jogador j = jogadorRepository.findById(jogadorId).orElseThrow();
        t.getJogadores().add(j);
        return toDTO(torneioRepository.save(t));
    }

    public TorneioDTO removerJogador(Long torneioId, Long jogadorId) {
        Torneio t = torneioRepository.findById(torneioId).orElseThrow();
        Jogador j = jogadorRepository.findById(jogadorId).orElseThrow();
        t.getJogadores().remove(j);
        return toDTO(torneioRepository.save(t));
    }

    public List<String> listarJogadores(Long torneioId) {
        Torneio t = torneioRepository.findById(torneioId).orElseThrow();
        return t.getJogadores().stream().map(Jogador::getNome).collect(Collectors.toList());
    }

    public TorneioDTO finalizarTorneio(Long torneioId) {
        Torneio t = torneioRepository.findById(torneioId).orElseThrow();
        t.setFinalizado(true);
        return toDTO(torneioRepository.save(t));
    }

    private TorneioDTO toDTO(Torneio t) {
        List<String> nomes = t.getJogadores().stream().map(Jogador::getNome).collect(Collectors.toList());
        return new TorneioDTO(t.getId(), t.getNome(), t.getData(), t.isFinalizado(), nomes);
    }
}

