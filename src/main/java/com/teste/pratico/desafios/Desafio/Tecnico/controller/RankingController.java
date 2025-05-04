package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.services.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@Tag(name = "Ranking", description = "Operações relacionadas ao Ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Operation(summary = "Obter ranking global de todos os jogadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking global retornado com sucesso")
    })
    @GetMapping("/global")
    public ResponseEntity<List<RankingResponse>> rankingGlobal() {
        return ResponseEntity.ok(rankingService.getRankingGlobal());
    }

    @Operation(summary = "Obter ranking de jogadores por torneio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking do torneio retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Torneio não encontrado")
    })
    @GetMapping("/torneio/{id}")
    public ResponseEntity<List<RankingResponse>> rankingPorTorneio(
            @Parameter(description = "ID do torneio") @PathVariable Long id) {

        return ResponseEntity.ok(rankingService.getRankingPorTorneio(id));
    }
}
