package com.challenge.chellenge.dados;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalInt;

@Entity
@Table(name = "Autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private @JsonAlias("name") String nome;
    private @JsonAlias("birth_year") Integer nascimento;
    private @JsonAlias("death_year") Integer morte;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)//fetch - usado na consulta JPQL para carregar a coleção livro junto com as entidade pessoa
    private List<Livro> livros;

    public Pessoa(){}
    public Pessoa(DadosPessoa pessoa){
        this.nome = pessoa.nome();
        this.nascimento = OptionalInt.of(Integer.valueOf(pessoa.nascimento())).orElse(0);
        this.morte = OptionalInt.of(Integer.valueOf(pessoa.morte())).orElse(0);
    }
    public Integer getNascimento() {
        return nascimento;
    }
    public Integer getMorte() {
        return morte;
    }
    public String getNome() {
        return nome;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNascimento(int nascimento) {
        this.nascimento = nascimento;
    }

    public void setMorte(int morte) {
        this.morte = morte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return  " nome='" + nome +
                ": " + nascimento +
                " a " + morte;
    }
}
