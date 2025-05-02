package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.RankingResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.services.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/global")
    public ResponseEntity<List<RankingResponse>> rankingGlobal() {
        return ResponseEntity.ok(rankingService.getRankingGlobal());
    }

    @GetMapping("/torneio/{id}")
    public ResponseEntity<List<RankingResponse>> rankingPorTorneio(@PathVariable Long id) {
        return ResponseEntity.ok(rankingService.getRankingPorTorneio(id));
    }
}

