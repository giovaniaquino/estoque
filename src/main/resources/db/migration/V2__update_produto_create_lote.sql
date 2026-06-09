ALTER TABLE produto DROP CONSTRAINT chk_validade_fabricacao;

ALTER TABLE produto DROP COLUMN lote;
ALTER TABLE produto DROP COLUMN fabricacao;
ALTER TABLE produto DROP COLUMN validade;

CREATE TABLE lotes(
    id_lote SERIAL PRIMARY KEY,
    lote VARCHAR(20) NOT NULL UNIQUE,
    fabricacao DATE NOT NULL,
    validade DATE NOT NULL,
    id_produto INT NOT NULL,

    CONSTRAINT chk_validade_fabricacao CHECK ( validade > fabricacao ),
    CONSTRAINT fk_produto_lote FOREIGN KEY (id_produto) REFERENCES produto(id)
);