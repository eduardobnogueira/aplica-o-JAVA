
## API de Gerenciamento de Usuários e Transações Financeiras
Este é um projeto de exemplo para gerenciar usuários e transações financeiras. Ele implementa uma API RESTful desenvolvida em Java utilizando o framework Spring Boot. O banco de dados em memória H2 é utilizado para armazenamento.

Funcionalidades
Cadastro de Usuários

Usuários com idade mínima de 18 anos podem ser cadastrados.
CPF único é obrigatório.
Cada usuário tem um número de conta gerado automaticamente.
Saldo inicial é definido como 0.00.
Consulta de Usuários

Obtenha dados de um usuário por ID.
Liste todos os usuários cadastrados.
Transferência de Valores

Transferências podem ser realizadas entre contas existentes.
O saldo do remetente deve ser suficiente para a transação.
Tratamento de Erros

Retorno de mensagens claras e status HTTP apropriados para entradas inválidas:
400: Requisição inválida (ex.: CPF duplicado ou saldo insuficiente).
404: Conta ou usuário não encontrado.
Tecnologias Utilizadas
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
Banco de Dados H2 (em memória)
Lombok (para redução de boilerplate code)
Maven (gerenciador de dependências)
Configuração e Execução
Pré-requisitos
Certifique-se de que as seguintes ferramentas estão instaladas:

Java 17+
Maven
Git
Como executar o projeto
Clone o repositório:

git clone <URL_DO_REPOSITORIO>
cd UserTransactionAPI
Configure as dependências do projeto:

mvn install
Execute a aplicação:

mvn spring-boot:run
Acesse a API no navegador ou ferramentas como Postman:

URL base: http://localhost:8080
Opcional: Acesse o console H2 para visualizar os dados:

URL: http://localhost:8080/h2-console
Credenciais:
JDBC URL: jdbc:h2:mem:testdb
Usuário: sa
Senha: (em branco)
Endpoints da API
1. Cadastro de Usuário
POST /api/users
Cadastra um novo usuário no sistema.

Corpo da Requisição
{
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10"
}
Resposta de Sucesso (200 OK)
{
    "id": 1,
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10",
    "numeroConta": "000123",
    "saldo": 0.0
}
Resposta de Erro (400 Bad Request)
CPF duplicado:
{ "error": "CPF já cadastrado." }
Idade menor que 18:
{ "error": "Usuário deve ter 18 anos ou mais." }
2. Consulta de Usuários
GET /api/users/{id}
Retorna os dados de um usuário pelo ID.

Resposta de Sucesso (200 OK)
{
    "id": 1,
    "nome": "João Silva",
    "idade": 25,
    "cpf": "123.456.789-10",
    "numeroConta": "000123",
    "saldo": 150.0
}
GET /api/users
Retorna uma lista de todos os usuários cadastrados.

3. Transferência de Valores
POST /api/transactions
Realiza uma transferência de valores entre duas contas.

Corpo da Requisição
{
    "contaOrigem": "000123",
    "contaDestino": "000456",
    "valor": 100.0
}
