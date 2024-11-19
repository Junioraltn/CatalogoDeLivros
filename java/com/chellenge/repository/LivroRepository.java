package com.challenge.chellenge.repository;

import com.challenge.chellenge.dados.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    @Query("SELECT l FROM Livro l WHERE l.titulo ILIKE %:trechoLivro")// ILIKE - faz a parte do ignoreCase; % - faz a parte do containing
    List<Livro> livroPorTrecho(String trechoLivro);

    @Query("SELECT l FROM Livro l WHERE l.idioma LIKE %:idioma")
    List<Livro> livroPorIdioma(String idioma);

    @Query("SELECT l FROM Livro l ORDER BY l.downloads DESC LIMIT 5")
    List<Livro> topLivro();

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.idioma = :idioma")
    Integer contagemLivroPorIdioma(String idioma);

}
