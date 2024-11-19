# 📚Consulta de Livros
Bem-vindo(a) ao _challenge_ que resolvi. Espero que lhe seja útil!
## Sobre o Propósito do Projeto

Trata-se de um desafio de programação, no qual era necessário construir um catálogo de livros.
Este desafio objetivava exercicitar conhecimentos, tais como:

- Realizar solicitações a uma API de livros;
- Manipular dados JSON e Armazená-los em um banco de dados; e
- Filtrar e mostrar os livros e autores de interesse.

### Requisitos:

#### Técnicos

|           |                      |
|-----------|----------------------|
| Java JDK: | versão: 17 em diante |
| Maven:    | versão 4 em diante   |
|Spring:|versão 3.2.3|
|Postgres:|versão16 em diante
|IDEA:|IntelliJ (_opcional_)
|Java:|(versão 17 em diante)
|Maven:|(Initializr utiliza a versão 4)
|Spring Boot| (versão 3.2.3)
| Spring Data JPA||

#### Funcionais
1. Ofereçer interação textual (via console) com os usuários.
2. Proporcionando no mínimo 5 opções de interação.
3. O usuário deverá selecionar um número que corresponderá à opção numérica.
4. Garantir tratamento de erros de entrada de dados.
5. Possibilitar ao usuário ver uma listagem com base no idioma em que um ou mais livros foram escritos.
6. Listar autores vivos em determinado ano.
7. Aproveite os recursos de Streams do Java e derived queries para fornecer ao seu usuário estatísticas sobre a quantidade de livros em um determinado idioma no banco de dados.

### Restrições
1. Projeto em JAR.
2. Os livros serão buscados através de uma API específica (_Gutendex_).
3. O livro terá que ter os seguintes atributos: Título; Autor; Idiomas; e Número de Downloads.
4. Será considerado que um livro possui apenas um idioma e um autor.
5. Cada autor possui três características: Nome; Ano de nascimento; e Ano de falecimento.
6. Deve ser Realizado a conversão dos atributos do livro presentes no resultado JSON para um objeto Java correspondente ao livro.
7. O livro e o autor devem ser inseridos juntos o no banco de dados.

### Etapas do Desafio
- Configuração do ambiente Java;
- Criação do Projeto;
- Consumo da API;
- Análise da Resposta JSON;
- Inserção e consulta no banco de dados;
- Exibição de resultados aos usuários;

#### Detalhes do Projeto
-  A consulta feita pela API não é necessita de chave de acesso;

## 🏁Sobre o Projeto

### 📝Descrição
Trata-se de um projeto desenvolvido em Java utilizando Spring Boot e Spring Data JPA. A aplicação consome uma API REST para obter dados de livros e armazena as informações em um banco de dados PostgreSQL. As tecnologias utilizadas permitem uma rápida prototipação e escalabilidade.

### 🛠️Funcionalidades:
* **Pesquisa:** Permite realizar buscas por livros através de trechos do título ou do nome do autor.
* **Interface simples:** A interface do usuário é projetada para ser fácil de usar, com uma navegação clara e intuitiva.
* **Gerador Estatistico:** Informa o usuário sobre média, valor máximo e mínimo de dowloads, e quantidade de livros por idioma.

#### Sobre Requisitos Funcionais
Todos foram atendidos. Para atender o item 2 foi usado recursividade.

### Sobre as Restrições
Todas foram obedecidas.
- O item 3 além dos atributos estabelecidos foram acrescentados os atributos: assuntos e topicos. Visando trabalhos futuros (além deste desafio)!
- Para o item 4 foi utilizado ".getFirst()" para obedecê-la.
- Para o item 7 foi usado o recurso "CascadeType.PERSIST" para que a operação de "PERSIST" seja propagada para a sua referência "Livros".

### 🛰️Tecnologias utilizadas:
* **Java:** Linguagem de programação principal.
* **Maven:** Gerenciador de build para automatizar a construção, testes e distribuição do projeto.
* **Spring Boot:** Framework para desenvolvimento de aplicações Java, simplificando a configuração e o desenvolvimento.
* **Spring Data JPA:** Framework para acesso a dados, facilitando a interação com o banco de dados.
* **PostgreSQL:** Banco de dados relacional utilizado para armazenar os dados dos livros e autores.
* **Jackson:** Biblioteca para serialização e desserialização de JSON, utilizada para converter a resposta da API em objetos Java.
* **Java Records:** Estrutura de dados imutável para representar os dados dos livros e autores.

**Como as tecnologias se relacionam:**

* **Spring Boot** fornece a estrutura básica da aplicação, utilizando **Spring Data JPA** para interagir com o **PostgreSQL**.
* **Jackson** é utilizado pelo **Spring** para converter a resposta JSON da API em objetos **Java**.
* **Java Records** são utilizados para representar os dados dos livros e autores de forma concisa e imutável.
* **Maven** gerencia as dependências do projeto e o processo de build.

### 🏛️Estrutura do projeto:
O projeto está organizado da seguinte forma:

* **model:** Contém as classes que representam os dados do domínio, como `Livro`, `Autor` e `RespostaApi`.
* **repository:** Define as interfaces que interagem com o banco de dados, utilizando Spring Data JPA.
* **service:** Encapsula a lógica de negócio, como a consulta à API, a conversão de JSON e a persistência de dados.
* **principal:** Contém a classe que possui as funções que realiza as ações da aplicação.

**Diagrama de Classes:**
[Devo incluir o diagrama de classes simplificado aqui]

### Como Utilizar

1. **Pré-requisitos:**
  * **Java:** Instalar o JDK 17 ou superior.
  * **Maven:** Instalar o Apache Maven 4 ou superior.
  * **Banco de dados:** Configurar um banco de dados PostgreSQL 16 ou superior.
  * **IDE:** Recomenda-se utilizar o IntelliJ IDEA.

2. **Clonagem do Repositório:**
   ```bash
   git clone https://github.com/Junioraltn/ConsultaDeLivros.git

3. **Configuração do Projeto:**

* Arquivo de Propriedades: Configurar o arquivo application.properties com as credenciais do banco de dados e outras configurações necessárias.
* IDE: Importar o projeto como um projeto Maven no IntelliJ IDEA.

4. **Execução:**

* IDE: Executar a classe principal "ChallengeApplication".


### 🧾Como utilizar:

* **Clonar o repositório:** Clone este repositório para sua máquina local utilizando o Git.
* **Abrir a pasta em uma IDE:** Execute o ChallengeApplication.
* **Selecione uma das opções do menu:** São 10 opções entre elas a de sair ("0"), siga as instruções e pronto.

### Desafios
* Tratamento de livros sem autores.

_Erro ao criar o objeto._
 
**SOLUÇÃO** >> No construtor de Livro:
````
if (livro.autores().isEmpty()){
            Pessoa autorX = new Pessoa();
            autorX.setNome("Desconhecido");
            autorX.setNascimento(0);
            autorX.setMorte(0);
            this.autor = autorX;
        }else {
            this.autor = livro.autores().getFirst();
        }
````
* Tratamento dos autores já cadastrados.

_Erro1: object references an unsaved transient instance..._
_Erro2: duplicar valor da chave viola a restrição de unicidade..._

**SOLUÇÃO** >> Na função salvarLivro em Principal:
````
if (existir.isPresent()){
            livro.setAutores(existir.get());
        }
        else {
            pessoaRepositorio.save(autor);
        }
````
* Minimizar número de requisições a API.

**SOLUÇÃO** >> Buscar os livros primeiro no repositório antes de consultar a API.

### 🧷Trabalhos Futuros:
* **Melhoria da interface do usuário:** A interface do usuário será aprimorada para oferecer uma experiência ainda mais agradável.
* **Realizar buscas por tópicos e assunto** A ideia é acrescentar mais essas funcionalidades, dando mais opções e versatilidade ao usuário.
* **Mais dados estatístico** Para dar opção ao usuário da quantidade de livros por tópico, tópicos com mais downloads realizado etc.

**Observação:** Este projeto está em constante desenvolvimento. Novas funcionalidades e melhorias serão adicionadas regularmente.

### 🫡Contribuições:

Contribuições são bem-vindas! Se você encontrar algum bug ou tiver alguma sugestão de melhoria, por favor, abra um issue neste repositório.

### 🔓Licença:

Este projeto está licenciado sob a licença MIT.

### 🤍Agradecimentos:

Agradeço a todos que contribuíram para este projeto.