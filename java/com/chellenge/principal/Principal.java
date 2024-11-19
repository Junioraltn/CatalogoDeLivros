package com.challenge.chellenge.principal;

import com.challenge.chellenge.dados.*;
import com.challenge.chellenge.repository.LivroRepository;
import com.challenge.chellenge.repository.PessoaRepository;
import com.challenge.chellenge.service.ConsumirApi;
import com.challenge.chellenge.service.ConverterDados;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner ler = new Scanner(System.in);
    private String opcao;
    private ConsumirApi consumirApi = new ConsumirApi();
    private ConverterDados conversor = new ConverterDados();
    private LivroRepository livroRepositorio;
    private PessoaRepository pessoaRepositorio;

    public Principal(LivroRepository repositorio, PessoaRepository pessoaRepositorio) {
        this.livroRepositorio = repositorio;
        this.pessoaRepositorio = pessoaRepositorio;
    }

    public void menu(){

        System.out.println("""
                Oções:
                1- Bucar livro pelo título
                2- Listar livros
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                6- Exibir a quantidade de livros em um determinado idioma
                7- Estatistica
                8- Top Livros Baixados
                9- Autor por nome
                0- Sair""");

    }
    public void interagir(){
        menu();
        System.out.println("Digite a opção a ser executada!");
        opcao = ler.nextLine();

        execucao (opcao);

        if (!Objects.equals(opcao, "0")){
        System.out.println("Deseja ver o menu? (S)\t" +
                "Deseja executar alguma ação? (opções do menu)");
        opcao = ler.nextLine();
        switch (opcao) {
            case "s", "S", "Sim", "sim", "com certeza":
                interagir();
                break;
            case "1","2","3","4","5","6","7","8","9":
                execucao (opcao);
                interagir();
            default:
                break;
        }
        }
    }
    private void execucao (String opcao){

        switch (opcao){
            case "1":
                //1- Bucar livro pelo título
                pesquisar();
                break;
            case "2":
                //2- Listar livros
                todosOsLivros();
                break;
            case "3":
                //3- Listar autores registrados
                todosOsAutores();
                break;
            case "4":
                //4- Listar autores vivos em um determinado ano
                buscarAutoresVivos();
                break;
            case "5":
                //5- Listar livros em um determinado idioma
                buscarLivrosPorIdioma();
                break;
            case "6":
                //6- Exibir a quantidade de livros em um determinado idioma
                estatisticaIdiomas();
                break;
            case "7":
                //7- Estatistica
                estatistica();
                break;
            case "8":
                //8- Top Livros Baixados
                livroRepositorio.topLivro().forEach(System.out::println);
                break;
            case "9":
                //9- Autor por nome;
                buscarAutores();
                break;
            case "0":
                break;
            default:
                System.out.println("Por favor verifique se "+opcao+" está entre as opções válidas!");
                break;
        }
    }

    private void pesquisar(){
        System.out.println("Digite o Titúlo: ");
        var entrada = ler.nextLine();

        List<Livro> livros = livroRepositorio.livroPorTrecho(entrada);
        if (!livros.isEmpty()){
            livros.forEach(System.out::println);
        }
        else{
            List<DadosLivro> resposta = getDadosLivros(entrada).results();
            List<Livro> livros2 = resposta.stream()
                    .map(Livro::new)
                    .collect(Collectors.toList());

            livros2.forEach(l -> salvarLivro(l));

            livros2.forEach(System.out::println);
        }
    }
    public void salvarLivro(Livro livro){
        Pessoa autor = livro.getAutor();
        Optional<Pessoa> existir = pessoaRepositorio.findByNome(autor.getNome());

        existir.ifPresent(System.out::println);

        if (existir.isPresent()){
            livro.setAutores(existir.get());
        }
        else {
            pessoaRepositorio.save(autor);
        }
        livroRepositorio.save(livro);
    }

    private DadosRespostaApi getDadosLivros(String entrada) {

        String endereco = "https://gutendex.com/books?search="+URLEncoder.encode(entrada);
        String json = null;
        try {
            json = consumirApi.obterDados(endereco);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return conversor.obterDados(json, DadosRespostaApi .class);
    }

    private void todosOsLivros(){
        livroRepositorio.findAll().forEach(System.out::println);
    }

    private void todosOsAutores(){
        pessoaRepositorio.findAll().forEach(System.out::println);
    }

    private void buscarAutoresVivos() {
        System.out.println("Digite o ano para descobrir quais autores estão vivos nesse ano:");

        try {
            int ano = ler.nextInt();
            ler.nextLine();
            List<Pessoa> autores = pessoaRepositorio.autoresVivos(ano);
            autores.forEach(System.out::println);
            if (autores.isEmpty()){
                System.out.println("Nenhum autor vivo no ano "+ano);
            }

        }catch (InputMismatchException e){
            System.out.println("Valor Inválido");

        }
    }

    public void buscarLivrosPorIdioma(){

        System.out.println("Disponiveis:");
        for (Idiomas i : Idiomas.values()){
            System.out.println(i.getIdioma());
        }

        System.out.println("Digite o idioma:");
        var entrada = ler.nextLine();

        var entrada2 = String.valueOf(Idiomas.fromString(entrada));

        if (entrada2.equals("null")){
            System.out.println(entrada+" não é um idioma disponível, desculpe-nos!");
        }else {
           List<Livro> livros = livroRepositorio.livroPorIdioma(entrada2);
            if (livros.isEmpty()) {
                System.out.println("Nada Encontrado");
            } else {
                livros.forEach(System.out::println);
            }
        }

    }

    public void estatisticaIdiomas(){
        Arrays.stream(Idiomas.values()).forEach(
                i -> {
                    Integer n = livroRepositorio.contagemLivroPorIdioma(i.toString());
                    if (n>1) {
                        System.out.printf("São %d livros em %s\n", n, i.getIdioma());
                    }else if (n==1){
                        System.out.printf("Há %d livro em %s\n", n, i.getIdioma());
                    }
                }
        );

}
    public void estatistica(){

        pessoaRepositorio.findAll()
                .forEach(a -> {
                    DoubleSummaryStatistics est = a.getLivros().stream()
                                    .collect(Collectors.summarizingDouble(Livro::getDownloads));
                            System.out.println(a);
                            if (est.getCount()>1) {
                                System.out.println("Média de Dowload: " + est.getAverage());
                                System.out.println("Livro com o maior número de Dowloads: " + est.getMax());
                                System.out.println("Livro com o menor número de Dowloads: " + est.getMin());
                            }
                            else {
                                System.out.println("Número de Dowloads: " + est.getAverage());
                            }
                            System.out.println("Quantidade de livros: " + est.getCount());
                        }
                );
    }

    private void buscarAutores(){
        System.out.println("Digite o nome do Autor");
        var entrada = ler.nextLine();
        pessoaRepositorio.BucarAutores(entrada).forEach(System.out::println);
    }

}
