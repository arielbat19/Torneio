package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return jogadorService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Jogador com ID " + id + " não encontrado."));
    }


    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorIdOuNome(@RequestParam String valor) {
        try {
            JogadorDTO jogador = jogadorService.buscarPorIdOuNome(valor);
            return ResponseEntity.ok(jogador);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody JogadorDTO dto) {
        Optional<JogadorDTO> resultado = jogadorService.atualizarJogador(id, dto);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(resultado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Jogador com ID " + id + " não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        boolean excluido = jogadorService.excluirJogador(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Jogador com ID " + id + " não encontrado.");
        }
    }

}

