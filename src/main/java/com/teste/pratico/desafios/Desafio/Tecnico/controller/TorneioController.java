package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.TorneioDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.services.TorneioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/torneios")
public class TorneioController {

    @Autowired
    private TorneioService torneioService;

    @PostMapping
    public TorneioDTO criar(@RequestParam String nome, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) {
        return torneioService.criar(nome, data);
    }

    @PutMapping("/{torneioId}/adicionar-jogador/{jogadorId}")
    public TorneioDTO adicionarJogador(@PathVariable Long torneioId, @PathVariable Long jogadorId) {
        return torneioService.adicionarJogador(torneioId, jogadorId);
    }

    @PutMapping("/{torneioId}/remover-jogador/{jogadorId}")
    public TorneioDTO removerJogador(@PathVariable Long torneioId, @PathVariable Long jogadorId) {
        return torneioService.removerJogador(torneioId, jogadorId);
    }

    @GetMapping("/{torneioId}/jogadores")
    public List<String> listarJogadores(@PathVariable Long torneioId) {
        return torneioService.listarJogadores(torneioId);
    }

    @PutMapping("/{torneioId}/finalizar")
    public TorneioDTO finalizar(@PathVariable Long torneioId) {
        return torneioService.finalizarTorneio(torneioId);
    }
}

