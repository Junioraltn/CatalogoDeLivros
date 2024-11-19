package com.challenge.chellenge.dados;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPessoa(@JsonAlias("birth_year") String nascimento,
                          @JsonAlias("death_year") String morte,
                          @JsonAlias("name") String nome) {
}
