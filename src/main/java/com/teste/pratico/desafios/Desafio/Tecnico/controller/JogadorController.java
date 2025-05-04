package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.JogadorDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.services.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Criar um novo jogador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jogador criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<JogadorDTO> criar(@RequestBody JogadorDTO dto) {
        return ResponseEntity.status(201).body(jogadorService.criarJogador(dto));
    }

    @Operation(summary = "Listar todos os jogadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<JogadorDTO>> listar() {
        return ResponseEntity.ok(jogadorService.listarJogadores());
    }

    @Operation(summary = "Buscar jogador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(
            @Parameter(description = "ID do jogador") @PathVariable Long id) {
        return jogadorService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Jogador com ID " + id + " não encontrado."));
    }

    @Operation(summary = "Buscar jogador por ID ou nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador encontrado"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorIdOuNome(
            @Parameter(description = "ID ou nome do jogador") @RequestParam String valor) {
        try {
            JogadorDTO jogador = jogadorService.buscarPorIdOuNome(valor);
            return ResponseEntity.ok(jogador);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }
    }

    @Operation(summary = "Atualizar jogador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @Parameter(description = "ID do jogador") @PathVariable Long id,
            @RequestBody JogadorDTO dto) {

        Optional<JogadorDTO> resultado = jogadorService.atualizarJogador(id, dto);
        return resultado
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Jogador com ID " + id + " não encontrado."));
    }

    @Operation(summary = "Excluir jogador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Jogador excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Jogador não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(
            @Parameter(description = "ID do jogador") @PathVariable Long id) {

        boolean excluido = jogadorService.excluirJogador(id);
        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Jogador com ID " + id + " não encontrado.");
        }
    }
}
