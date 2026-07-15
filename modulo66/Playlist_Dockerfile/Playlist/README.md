# Spring Boot & Docker: Playlist API

Este é um projeto prático para iniciantes focados em aprender o ecossistema **Java com Spring Boot** e **Containerização com Docker**. O exercício consiste em uma API REST simples que retorna dados estruturados de uma playlist focada em produtividade.

A aplicação foi desenvolvida seguindo boas práticas de organização de código em camadas (Controller, Service, DTO).

---

## Tecnologias Utilizadas

*   **Java 17**
*   **Spring Boot** (Spring Web)
*   **Maven** (Build tool e dependências)
*   **Docker** (Containerização)

---

## Estrutura do Projeto

O código está estruturado nas seguintes camadas para separar responsabilidades:

*   **`controller/`**: Gerencia as requisições HTTP e roteamento (`PlaylistController`).
*   **`service/`**: Contém a lógica de negócios e a construção dos dados estáticos (`PlaylistService`).
*   **`dto/`**: Objetos de Transferência de Dados, definindo o formato exato da resposta JSON (`PlaylistDto`, `TrackDto`).

```text
src/main/java/com/example/demo/
├── controller/
│   └── PlaylistController.java
├── dto/
│   └── PlaylistDto.java
├── service/
│   └── PlaylistService.java
└── Application.java
```

## Como Executar o Projeto com Docker
Para testar este projeto em sua máquina, certifique-se de ter o Docker Desktop instalado e rodando em segundo plano.

1. Clonar e Compilar
Abra o terminal na raiz do projeto e utilize o Maven Wrapper (mvnw) embutido para gerar o artefato compilado (.jar):

```Bash
# Em sistemas Linux/macOS ou terminais Bash no Windows
./mvnw clean package
```

# No prompt de comando do Windows (CMD)
```bash
mvnw.cmd clean package
```
2. Construir a Imagem Docker
Com o arquivo .jar gerado na pasta target/, crie a imagem Docker da aplicação:

```Bash
docker build -t access-playlist-api .
```

3. Rodar o Container
Inicie o container mapeando a porta 8080 local para a porta 8080 do Spring Boot dentro do container:

```Bash
docker run -p 8080:8080 access-playlist-api
```
## Testando a API
Com o container em execução, abra um novo terminal ou seu navegador e acesse o endpoint:
``` bash
GET http://localhost:8080/api/v1/playlists/spotify
```

Retorno Esperado:

``` bash
JSON
{
  "name": "Dev Focus Playlist",
  "description": "Instrumental music for deep work and coding sessions.",
  "spotifyUrl": "[https://open.spotify.com/playlist/0Ka9lnk0gpqBtr8pQr7k5n?si=bd185ceb086a47c8](https://open.spotify.com/playlist/0Ka9lnk0gpqBtr8pQr7k5n?si=bd185ceb086a47c8)",
}
```

## Solução de Problemas Comuns
``` bash
Erro: ERROR: failed to connect to the docker API at npipe:////./pipe/dockerDesktopLinuxEngine...
```

Causa: O Docker Daemon não está rodando.
Solução: Abra o aplicativo do Docker Desktop no seu computador, aguarde o "Engine" iniciar completamente e tente rodar o comando novamente.
