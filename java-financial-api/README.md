# API Financeira

API RESTful para gerenciar usuários e transações financeiras.

## Tecnologias

- Java 17
- Spring Boot
- H2 Database
- Maven

## Executar o Projeto

1. Clone o repositório
2. Execute: `mvn spring-boot:run`
3. API disponível em `http://localhost:8080`

## Endpoints

### Criar Usuário
- POST `/api/users`
- Corpo: `{"nome": "João Silva", "idade": 25, "cpf": "123.456.789-10"}`

### Consultar Usuário
- GET `/api/users/{id}`

### Listar Usuários
- GET `/api/users`

### Transferir Dinheiro
- POST `/api/transactions`
- Corpo: `{"contaOrigem": "000123", "contaDestino": "000456", "valor": 100.00}`

## Regras de Negócio

- Usuários devem ter 18 anos ou mais
- CPF deve ser único
- Saldo deve ser suficiente para transferências

## Estrutura do Projeto

- `controller/`: Endpoints da API
- `service/`: Lógica de negócio
- `dao/`: Acesso ao banco de dados
- `model/`: Entidades
- `exception/`: Exceções personalizadas
