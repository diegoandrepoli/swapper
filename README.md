# Swapper API

Implementação das chaves de retorno dos documentos para idioma português da API [Swapi](https://swapi.dev/) nos seguintes endpoints:

* [Films](https://swapi.dev/documentation#films)
* [People](https://swapi.dev/documentation#people)
* [Planets](https://swapi.dev/documentation#planets)


## Requisitos

Para rodar esta API você precisa da JDK Java e do Apache Maven.
* [Java Open JDK versão 21](https://jdk.java.net/archive/).
* [Apache maven](https://maven.apache.org/).

## Executar projeto

Para executar o projeto faça o clone deste repositório com o comando git clone ou baixe o código-fonte.

```console
git@github.com:diegoandrepoli/swapper.git
```

No console (bash) entre no diretório do projeto execute o comando Maven para rodar a API.

```console
mvn spring-boot:run
```

## Executar teste unitários

Para executar os testes unitários entre no diretório do projeto com o console (bash) e execute o comando Maven com a opção ```test```.

```console
mvn test
```
## Endpoints

Você usar a API esta API com endpoints:

* POST ```/api/account/register``` criar usuário.
* POST ```/api/account/login``` fazer login.
* POST ```/api/account/password``` alterar a senha.


* GET ```/api/planets``` retorna lista de planetas.
* GET ```/api/planets/{id}``` retorna planeta a partir do id.
* GET ```/api/people``` retorna lista de pessoas.
* GET ```/api/people/{id}``` retorna pessoa a partir do id.
* GET ```/api/films``` retorna lista de filmes.
* GET ```/api/films/{id}``` retorna filme a partir do id.

Exceto para os endpoints de registro e login você precisa adicionar o token retornado no endpoint de login no header de cada chamada da API, exemplo ```Authorization: Bearer XXXXXX```.


## Documentação

A documentação dos endpoints desta API está no Swagger, com a API em execução você pode acessar pelo navegador de internet a partir no path ```/swagger-ui/index.html```.

```console
http://localhost:8080/swagger-ui/index.html
```

## Console do banco de dados
Esta API foi implementada com o banco de dados H2 (em memória), toda vez que você parar a execução da API o banco de dados é perdido. As credenciais para acessar o banco de dados são:

* URL: ```jdbc:h2:mem:swapper```
* usuário: ``sa``
* senha: ``sa``

Voce pode acessar o banco de dados pelo navegador de internet através do console do banco de dados H2 no path ```h2-console```.

```console
http://localhost:8080/h2-console
```





