# Name List API - Spring Boot & Docker
## Finalidade
 Esta aplicação é uma API RESTful desenvolvida em Java utilizando o framework Spring Boot. O objetivo principal do sistema é gerenciar uma lista de nomes, oferecendo endpoints para inserir, modificar em massa (atualizando todos os registros idênticos simultaneamente) e deletar em massa. O projeto serve como um ambiente prático para demonstrar a integração de uma aplicação backend com um banco de dados MySQL, sendo toda a infraestrutura conteinerizada e orquestrada através do Docker Compose.

## Cuidados e Possíveis Erros (Troubleshooting)
Antes de executar a aplicação, verifique os seguintes pontos para garantir que o ambiente suba sem problemas de conexão ou conflitos:

* Docker em execução: O motor do Docker (como o Docker Desktop) precisa estar aberto e rodando em segundo plano antes de executar qualquer comando.

* Conflito na porta do Banco de Dados: É muito comum que o MySQL Server local ou o MySQL Workbench estejam rodando em segundo plano na sua máquina. Eles ocupam a porta 3306 nativamente, o que causará um erro fatal ao tentar subir o container do MySQL. Certifique-se de fechar completamente o Workbench e parar serviços locais do MySQL.

* Portas Ocupadas: A aplicação exige que as portas 8080 (para a API) e 3306 (para o banco) estejam livres.

Para verificar se estas portas estão livres no Windows, abra o terminal e execute os comandos abaixo. Se não retornarem nenhuma informação, as portas estão livres. Se retornarem um processo com o status "LISTENING", você precisará encerrar a tarefa que está usando a porta:
```
netstat -ano | findstr :8080

netstat -ano | findstr :3306
```

## How to Execute
Siga os passos abaixo para iniciar a aplicação e o banco de dados:

* Abra o seu terminal e navegue até a pasta raiz do projeto (o mesmo diretório onde o arquivo docker-compose.yml está localizado).

* Execute o comando de orquestração para construir a imagem do Spring Boot e iniciar ambos os containers (API e Database) em conjunto:
```
docker-compose up --build
```

Aguarde o download das imagens (na primeira vez) e a inicialização. O terminal exibirá os logs do MySQL e, em seguida, os logs do Spring Boot. O sistema estará pronto quando você visualizar a mensagem de que a aplicação iniciou na porta 8080.

## Testando no Postman

Com ambos os containers rodando, abra o Postman e faça os testes:

* Inserir um Nome (POST)
```
URL: http://localhost:8080/api/names
```
* Body (JSON):

```
{
    "name": "PlayerOne"
}
```
##### (Dica: Insira o mesmo nome umas 3 vezes para testar a lógica dos próximos endpoints).

* Modificar um Nome (PUT)

```
URL: http://localhost:8080/api/names/PlayerOne?newName=Hero
```

##### Ação: Isso vai procurar todos os registros "PlayerOne" no banco e alterar para "Hero". A resposta retornará o número de linhas afetadas.

* Deletar um Nome (DELETE)

```
URL: http://localhost:8080/api/names/Hero
```

##### Ação: Vai deletar todos os registros que contenham o nome "Hero".

## Encerrando o Docker


Para desligar o servidor, parar os containers e liberar as portas, pressione Ctrl+C no terminal onde os logs estão rodando ou execute no seu terminal:

```
docker-compose down
```