package com.teste.pratico.desafios.Desafio.Tecnico.services;

import com.teste.pratico.desafios.Desafio.Tecnico.dtos.FibonacciRequest;
import com.teste.pratico.desafios.Desafio.Tecnico.dtos.FibonacciResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.dtos.PalindromeRequest;
import com.teste.pratico.desafios.Desafio.Tecnico.dtos.PalindromeResponse;
import com.teste.pratico.desafios.Desafio.Tecnico.entities.Resultado;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.JogadorRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.ResultadoDesafioRepository;
import com.teste.pratico.desafios.Desafio.Tecnico.repositories.TorneioRepository;
import org.springframework.stereotype.Service;

@Service
public class DesafioService {

    private static final int PESO_FIBONACCI = 10;
    private static final int PESO_PALINDROMO = 5;

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
}

