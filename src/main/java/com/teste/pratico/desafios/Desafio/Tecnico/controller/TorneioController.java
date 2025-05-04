package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.TorneioDTO;
import com.teste.pratico.desafios.Desafio.Tecnico.services.TorneioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/torneios")
@Tag(name = "Torneios", description = "Operações relacionadas aos torneios")
public class TorneioController {

    @Autowired
    private TorneioService torneioService;

    @Operation(summary = "Criar um novo torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneio criado com sucesso")
    })
    @PostMapping
    public TorneioDTO criar(
            @RequestParam @Parameter(description = "Nome do torneio") String nome,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Parameter(description = "Data do torneio no formato yyyy-MM-dd") Date data) {
        return torneioService.criar(nome, data);
    }

    @Operation(summary = "Adicionar jogador ao torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador adicionado com sucesso")
    })
    @PutMapping("/{torneioId}/adicionar-jogador/{jogadorId}")
    public TorneioDTO adicionarJogador(
            @PathVariable @Parameter(description = "ID do torneio") Long torneioId,
            @PathVariable @Parameter(description = "ID do jogador") Long jogadorId) {
        return torneioService.adicionarJogador(torneioId, jogadorId);
    }

    @Operation(summary = "Remover jogador do torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jogador removido com sucesso")
    })
    @PutMapping("/{torneioId}/remover-jogador/{jogadorId}")
    public TorneioDTO removerJogador(
            @PathVariable @Parameter(description = "ID do torneio") Long torneioId,
            @PathVariable @Parameter(description = "ID do jogador") Long jogadorId) {
        return torneioService.removerJogador(torneioId, jogadorId);
    }

    @Operation(summary = "Listar jogadores de um torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de jogadores retornada com sucesso")
    })
    @GetMapping("/{torneioId}/jogadores")
    public List<String> listarJogadores(
            @PathVariable @Parameter(description = "ID do torneio") Long torneioId) {
        return torneioService.listarJogadores(torneioId);
    }

    @Operation(summary = "Finalizar um torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Torneio finalizado com sucesso")
    })
    @PutMapping("/{torneioId}/finalizar")
    public TorneioDTO finalizar(
            @PathVariable @Parameter(description = "ID do torneio") Long torneioId) {
        return torneioService.finalizarTorneio(torneioId);
    }
}
