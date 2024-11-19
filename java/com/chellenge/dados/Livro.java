package com.challenge.chellenge.dados;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private List<String> assuntos;
    //@Enumerated(EnumType.STRING)
    private List<String> topicos;
    //private List<String> idiomas;
    private String idioma;
    private Long downloads;
    //@ManyToMany(mappedBy = "livros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne
    //private List<Pessoa> autores;
    private Pessoa autor;

    public Livro(){}
    public Livro(DadosLivro livro){
        this.id = livro.id();
        this.titulo = livro.titulo();
        this.assuntos = livro.assuntos();
        if (livro.autores().isEmpty()){
            Pessoa autorX = new Pessoa();
            autorX.setNome("Desconhecido");
            autorX.setNascimento(0);
            autorX.setMorte(0);
            this.autor = autorX;
        }else {
            this.autor = livro.autores().getFirst();
        }
        this.topicos = livro.topicos();
        this.idioma = livro.idiomas().getFirst();
        this.downloads = livro.downloads();
    }
    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public List<String> getAssuntos() {
        return assuntos;
    }
    public Pessoa getAutor() {
        return autor;
    }
    public List<String> getTopicos() {
        return topicos;
    }
    public String getIdioma() {
        return idioma;
    }
    public Long getDownloads() {
        return downloads;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public void setIdiomas(String idioma) {
        this.idioma = idioma;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public void setAutores(Pessoa autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "idiomas=" + idioma +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", downloads=" + downloads;
    }
}
