# Inventory Service

## Descrição
O Inventory Service gerencia os níveis de estoque de produtos. Ele garante a disponibilidade dos produtos antes da conclusão de uma transação, evitando a venda de itens sem estoque suficiente (*overselling*). O serviço opera utilizando um esquema de banco de dados H2 próprio e isolado (`inventorydb`).

## Funcionalidades
* Adiciona novos produtos e suas respectivas quantidades em estoque.
* Valida a disponibilidade de estoque e subtrai a quantidade solicitada em uma única transação.
* Rejeita solicitações caso a quantidade desejada exceda o estoque disponível.

## Conexões
Este é um serviço *downstream*. Ele não realiza chamadas HTTP externas para outros microsserviços. É consumido pelo **Serviço de Pedidos** (*Order Service*) durante o fluxo de finalização da compra (*checkout*).

## Documentação da API
Acesse a documentação interativa da API via Swagger UI:
`http://localhost:8082/swagger-ui/index.html`