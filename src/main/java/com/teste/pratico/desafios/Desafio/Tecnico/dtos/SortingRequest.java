package com.teste.pratico.desafios.Desafio.Tecnico.dtos;

import java.util.List;

public record SortingRequest(Long jogadorId, Long torneioId, List<Integer> numeros) {}

