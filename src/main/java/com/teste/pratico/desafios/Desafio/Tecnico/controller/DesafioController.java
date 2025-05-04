package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.*;
import com.teste.pratico.desafios.Desafio.Tecnico.services.DesafioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desafios")
@Tag(name = "Desafios", description = "Operações relacionadas a desafios de lógica")
public class DesafioController {

    private final DesafioService desafioService;

    public DesafioController(DesafioService desafioService) {
        this.desafioService = desafioService;
    }

    @Operation(summary = "Executa a sequência de Fibonacci", description = "Recebe um número e retorna a sequência de Fibonacci até esse número.")
    @PostMapping("/fibonacci")
    public ResponseEntity<FibonacciResponse> executarFibonacci(@RequestBody FibonacciRequest request) {
        var response = desafioService.executarFibonacci(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Verifica se é palíndromo", description = "Recebe uma palavra e verifica se é um palíndromo.")
    @PostMapping("/palindromo")
    public ResponseEntity<PalindromeResponse> executarPalindromo(@RequestBody PalindromeRequest request) {
        var response = desafioService.executarPalindromo(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ordena números", description = "Recebe um array de números e retorna o array ordenado.")
    @PostMapping("/ordenar")
    public ResponseEntity<SortingResponse> ordenarNumeros(@RequestBody SortingRequest request) {
        SortingResponse resultado = desafioService.ordenarArray(request);
        return ResponseEntity.ok(resultado);
    }
}
