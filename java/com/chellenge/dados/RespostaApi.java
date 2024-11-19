package com.challenge.chellenge.dados;

import java.util.ArrayList;
import java.util.List;

public class RespostaApi {
    private List<DadosLivro> livros = new ArrayList<>();

    public RespostaApi() {
    }
    public RespostaApi(DadosRespostaApi resposta) {
        this.livros = resposta.results();
    }

}
