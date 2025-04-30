package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.*;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Resultado;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Torneio;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.ResultadoDesafioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesafioService {

    private static final int PESO_FIBONACCI = 10;
    private static final int PESO_PALINDROMO = 5;
    private static final int PESO_SORT = 7;

    private final JogadorRepository jogadorRepository;
    private final TorneioRepository torneioRepository;
    private final ResultadoDesafioRepository resultadoRepository;

    public DesafioService(JogadorRepository jogadorRepository,
                          TorneioRepository torneioRepository,
                          ResultadoDesafioRepository resultadoRepository) {
        this.jogadorRepository = jogadorRepository;
        this.torneioRepository = torneioRepository;
        this.resultadoRepository = resultadoRepository;
    }

    public FibonacciResponse executarFibonacci(FibonacciRequest request) {
        var jogador = jogadorRepository.findById(request.jogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        var torneio = torneioRepository.findById(request.torneioId())
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        int resultado = calcularFibonacciIterativo(request.n());

        var resultadoDesafio = new Resultado();
        resultadoDesafio.setJogador(jogador);
        resultadoDesafio.setTorneio(torneio);
        resultadoDesafio.setPontuacao(PESO_FIBONACCI);
        resultadoDesafio.setTipoDesafio("FIBONACCI");

        resultadoRepository.save(resultadoDesafio);

        return new FibonacciResponse(resultado, PESO_FIBONACCI);
    }

    private int calcularFibonacciIterativo(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    public PalindromeResponse executarPalindromo(PalindromeRequest request) {
        var jogador = jogadorRepository.findById(request.jogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        var torneio = torneioRepository.findById(request.torneioId())
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        boolean ehPalindromo = isPalindromo(request.texto());

        if (ehPalindromo) {
            var resultadoDesafio = new Resultado();
            resultadoDesafio.setJogador(jogador);
            resultadoDesafio.setTorneio(torneio);
            resultadoDesafio.setPontuacao(PESO_PALINDROMO);
            resultadoDesafio.setTipoDesafio("PALINDROMO");

            resultadoRepository.save(resultadoDesafio);
        }

        return new PalindromeResponse(ehPalindromo, ehPalindromo ? PESO_PALINDROMO : 0);
    }

    private boolean isPalindromo(String texto) {
        String normalizado = texto.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return new StringBuilder(normalizado).reverse().toString().equals(normalizado);
    }

    public List<Integer> ordenarArray(SortingRequest request) {
        List<Integer> numeros = new ArrayList<>(request.numeros());
        boolean trocou = true;
        int n = numeros.size();

        while (trocou) {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                if (numeros.get(i) > numeros.get(i + 1)) {
                    int temp = numeros.get(i);
                    numeros.set(i, numeros.get(i + 1));
                    numeros.set(i + 1, temp);
                    trocou = true;
                }
            }
        }

        Jogador jogador = jogadorRepository.findById(request.jogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        Torneio torneio = torneioRepository.findById(request.torneioId())
                .orElseThrow(() -> new RuntimeException("Torneio não encontrado"));

        Resultado resultado = new Resultado();
        resultado.setJogador(jogador);
        resultado.setTorneio(torneio);
        resultado.setPontuacao(PESO_SORT);
        resultado.setTipoDesafio("SORTING");

        resultadoRepository.save(resultado);

        return numeros;
    }
}

