package com.teste.pratico.desafios.Desafio.Tecnico.dtos;

import java.util.Date;
import java.util.List;

public record TorneioDTO(Long id, String nome, Date data, boolean finalizado, List<String> jogadores) {}

