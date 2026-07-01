# Payment Service

## Descrição
O Payment Service gerencia o registro de transações financeiras do sistema. Nesta PoC, ele simula o processamento de pagamentos, registrando as transações em um banco de dados H2 isolado (`paymentdb`) com um status imutável.

## Funcionalidades
* Recebe solicitações de transação via JSON estruturado.
* Simula o processamento e salva o registro da transação.
* Fornece um endpoint para auditoria de transações.

## Conexões
Este é um serviço *downstream*. Ele não realiza chamadas HTTP externas para outros microsserviços. É consumido pelo **Serviço de Pedidos** (Order Service) na etapa final da orquestração do checkout.

## Documentação da API
Acesse a documentação interativa da API via Swagger UI:
`http://localhost:8083/swagger-ui/index.html`