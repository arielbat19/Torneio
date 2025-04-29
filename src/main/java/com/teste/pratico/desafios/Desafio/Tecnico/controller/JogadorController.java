package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JogadorDTO> criar(@RequestBody JogadorDTO dto) {
        return ResponseEntity.status(201).body(jogadorService.criarJogador(dto));
    }

    @GetMapping
    public ResponseEntity<List<JogadorDTO>> listar() {
        return ResponseEntity.ok(jogadorService.listarJogadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogadorDTO> buscar(@PathVariable Long id) {
        return jogadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<JogadorDTO> buscarPorIdOuNome(@RequestParam String valor) {
        JogadorDTO jogador = jogadorService.buscarPorIdOuNome(valor);
        return ResponseEntity.ok(jogador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JogadorDTO> atualizar(@PathVariable Long id, @RequestBody JogadorDTO dto) {
        return jogadorService.atualizarJogador(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return jogadorService.excluirJogador(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

