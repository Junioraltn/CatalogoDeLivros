package com.challenge.chellenge.dados;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(Long id,
                         @JsonAlias("title") String titulo,
                         @JsonAlias("subjects") List<String> assuntos,
                         @JsonAlias("authors") List<Pessoa> autores,
                         @JsonAlias("bookshelves") List<String> topicos,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("download_count") Long downloads) {
}
