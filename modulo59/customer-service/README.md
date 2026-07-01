# Customer Service

## Descrição
O Customer Service é um microsserviço independente responsável pelo gerenciamento de dados de usuários na arquitetura. Ele segue o padrão **Banco de Dados por Serviço** (*Database per Service*), utilizando um banco de dados H2 em memória isolado (`customerdb`).

## Funcionalidades
* Cadastra novos clientes.
* Valida se um cliente existe e está ativo para prosseguir com as operações do sistema.

## Conexões
Este é um serviço *downstream* (consumido por outros). Ele não realiza chamadas HTTP externas para outros microsserviços. É consumido pelo **Serviço de Pedidos** (*Order Service*).

## Documentação da API
Acesse a documentação interativa da API via Swagger UI:
`http://localhost:8081/swagger-ui/index.html`