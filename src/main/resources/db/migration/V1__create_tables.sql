CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    quantidade INT,
    lote VARCHAR(20),
    fabricacao DATE NOT NULL,
    validade DATE NOT NULL

    CONSTRAINT chk_validade_fabricacao
        CHECK ( validade > fabricacao )
)