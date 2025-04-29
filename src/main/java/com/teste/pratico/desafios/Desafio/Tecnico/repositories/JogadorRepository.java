package com.teste.pratico.desafios.Desafio.Tecnico.repositories;

import com.teste.pratico.desafios.Desafio.Tecnico.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
