CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    numero_conta VARCHAR(6) UNIQUE NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL
);

CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conta_origem VARCHAR(6) NOT NULL,
    conta_destino VARCHAR(6) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data_transacao TIMESTAMP NOT NULL
);

