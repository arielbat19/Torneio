package com.teste.pratico.desafios.Desafio.Tecnico.controller;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.*;
import com.teste.pratico.desafios.Desafio.Tecnico.services.DesafioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/desafios")
public class DesafioController {

    private final DesafioService desafioService;

    public DesafioController(DesafioService desafioService) {
        this.desafioService = desafioService;
    }

    @PostMapping("/fibonacci")
    public ResponseEntity<FibonacciResponse> executarFibonacci(@RequestBody FibonacciRequest request) {
        var response = desafioService.executarFibonacci(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/palindromo")
    public ResponseEntity<PalindromeResponse> executarPalindromo(@RequestBody PalindromeRequest request) {
        var response = desafioService.executarPalindromo(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenar")
    public ResponseEntity<List<Integer>> ordenarNumeros(@RequestBody SortingRequest request) {
        List<Integer> resultado = desafioService.ordenarArray(request);
        return ResponseEntity.ok(resultado);
    }
}

