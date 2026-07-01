# E-Commerce Microservices Architecture Lab

## Visão Geral do Projeto
Este projeto é uma prova de conceito (PoC) que demonstra a implementação de uma arquitetura de microsserviços utilizando o ecossistema Spring Boot e Spring Cloud. O objetivo principal é ilustrar a separação de domínios, a comunicação síncrona entre serviços e a aplicação de mecanismos de resiliência em sistemas distribuídos.

O fluxo simula um processo de *checkout* de e-commerce, onde um orquestrador centraliza a regra de negócio e se comunica com domínios isolados para validar clientes, abater estoque e processar pagamentos.

## Padrões Arquiteturais Aplicados

* **Database per Service:** Cada microsserviço possui seu próprio banco de dados em memória (H2), garantindo forte isolamento. Não existem chaves estrangeiras (Foreign Keys) físicas entre os domínios; os relacionamentos são mantidos apenas por referências lógicas (IDs).
* **API Composition (Orquestração):** O `Order Service` atua como principal da transação, agregando as chamadas para os demais serviços.
* **Circuit Breaker & Fallback:** Implementado via **Resilience4j** no orquestrador. Caso um serviço dependente apresente instabilidade ou indisponibilidade, o circuito intercepta a falha e aciona uma rota de degradação graciosa (*Graceful Degradation*), salvando o pedido com o status `PENDING_MANUAL_REVIEW` ao invés de retornar um erro em cascata.

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.x**
* **Spring Cloud OpenFeign** (Comunicação HTTP Declarativa)
* **Resilience4j** (Tolerância a Falhas)
* **H2 Database** (Persistência em Memória)
* **Springdoc OpenAPI** (Documentação e testes via Swagger UI)

## Topologia de Serviços
| Serviço | Porta | Responsabilidade | Banco de Dados |
| :--- | :--- | :--- | :--- |
| **Order Service** | `8080` | Orquestrador de checkout | `orderdb` |
| **Customer Service** | `8081` | Validação de usuários | `customerdb` |
| **Inventory Service** | `8082` | Gerenciamento de estoque | `inventorydb` |
| **Payment Service** | `8083` | Processamento de transações | `paymentdb` |

## Guia de Execução e Testes

### Pré-requisitos
* JDK instalado e configurado.
* Maven.

### Passo 1: Inicialização
Compile e inicie os quatro microsserviços de forma independente. Certifique-se de que cada um subiu em sua respectiva porta consultando os logs do terminal.

### Passo 2: Preparação de Dados (Seed)
Como os bancos são em memória, é necessário criar os dados iniciais via Swagger:
1.  Acesse o **Customer Service** (`http://localhost:8081/swagger-ui/index.html`) e faça um `POST /api/customers` para criar um cliente ativo. *(Anote o ID gerado).*
2.  Acesse o **Inventory Service** (`http://localhost:8082/swagger-ui/index.html`) e faça um `POST /api/inventory` para registrar um produto e sua quantidade disponível. *(Anote o ID do produto).*

### Passo 3: Testando o Fluxo Feliz (Caminho Principal)
1.  Acesse o **Order Service** (`http://localhost:8080/swagger-ui/index.html`).
2.  Execute um `POST /api/orders` enviando o ID do cliente e do produto criados no Passo 2.
3.  **Validação:** O retorno deve ser HTTP 200 com status `APPROVED`. Você pode verificar no Inventory Service que a quantidade foi subtraída e no Payment Service que a transação foi registrada.

### Passo 4: Testando a Resiliência (Circuit Breaker)
1.  Derrube o processo do **Payment Service** (porta `8083`).
2.  Refaça a requisição de compra no **Order Service**.
3.  **Validação:** A aplicação não deve retornar Erro 500. O Resilience4j acionará o fallback, retornando HTTP 200 com o pedido salvo no status `PENDING_MANUAL_REVIEW`.