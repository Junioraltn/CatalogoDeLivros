package com.challenge.chellenge.repository;

import com.challenge.chellenge.dados.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome);

    @Query("SELECT p FROM Pessoa p WHERE p.nome ILIKE %:nome%")
    List<Pessoa> BucarAutores (String nome);

    @Query("SELECT p FROM Pessoa p WHERE p.nascimento <= :ano AND p.morte > :ano")
    List<Pessoa> autoresVivos(Integer ano);

}
